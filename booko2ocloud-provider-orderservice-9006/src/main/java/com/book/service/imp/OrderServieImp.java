package com.book.service.imp;

import com.book.common.ServerResponse;
import com.book.dao.*;
import com.book.ov.AppraiseVO;
import com.book.ov.OrderVo;
import com.book.ov.OrderVoTw;
import com.book.pojo.*;
import com.book.service.OrderService;
import com.book.util.BigDecimalUtil;
import com.book.util.DateTimeUtil;
import com.book.util.IDUUID;
import com.book.util.PropertiesUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
@Service
public class OrderServieImp implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    PayInfoMapper payInfoMapper;
    @Autowired
    AppraiseMapper appraiseMapper;
    @Autowired
    ShippingMapper shippingMapper;
    public ServerResponse<OrderVo> addOrder(Integer userId,String cartIdlist,Integer shippingId){
        System.out.println("下单");
        OrderVo orderVo = new OrderVo();
        List<OrderItem> orderItems =new ArrayList<>();
        BigDecimal totalPice = new BigDecimal("0");
        String postage = PropertiesUtil.getProperty("postage");
        if(userId == null || cartIdlist == null || shippingId ==null){
            return  ServerResponse.createByErrorMessage("参数错误");
        }
        String []cartList = cartIdlist.split(",");
        Order order = new Order();
        order.setUserId(userId);
        order.setCreateTime(new Date());
        order.setOrderNo(IDUUID.getOrderNo());
        System.out.println(order.getOrderNo());
        int count = orderMapper.insert(order);
        if(count == 0){
            return ServerResponse.createByErrorMessage("服务器异常 提交订单失败");
        }
        System.out.println(cartList.length);
        for (int i=0;i<cartList.length;i++){
            BigDecimal book_totalPrice = new BigDecimal("0");

            Cart cart = cartMapper.selectByPrimaryKey(Integer.parseInt(cartList[i]));

            if(cart != null) {
                Book book = bookMapper.selectByPrimaryKey(cart.getBookId());
                if(book!=null) {
                    book.setStock(book.getStock()-cart.getQuantity());
                    System.out.println(book.getName());
                    OrderItem orderItem = new OrderItem();
                    orderItem.setUserId(userId);
                    orderItem.setOrderNo(order.getOrderNo());
                    orderItem.setBookId(cart.getBookId());
                    orderItem.setBookName(book.getName());
                    orderItem.setBookImage(book.getMainImage());
                    orderItem.setQuantity(cart.getQuantity());
                    orderItem.setCurrentUnitPrice(book.getPrice());
                    orderItem.setCreateTime(new Date());
                    orderItem.setUpdateTime(new Date());
                    book_totalPrice = BigDecimalUtil.mul(book.getPrice().doubleValue(),cart.getQuantity().doubleValue());

                    if(book.getStock()<=0){
                        book.setStatus(0);
                    }
                    orderItem.setTotalPrice(book_totalPrice);
                    bookMapper.updateByPrimaryKeySelective(book);
                    orderItemMapper.insert(orderItem);
                    orderItems.add(orderItem);
                    totalPice = BigDecimalUtil.add(book_totalPrice.doubleValue(),totalPice.doubleValue());
                }

            }
            cartMapper.deleteByPrimaryKey(cart.getId());

        }
        order.setStatus(10);
        order.setShippingId(shippingId);
        order.setPayment(BigDecimalUtil.add(totalPice.doubleValue(),new BigDecimal(postage).doubleValue()));
        order.setPaymentType(1);
        order.setPostage(Integer.parseInt(postage));
        orderMapper.updateByPrimaryKeySelective(order);
        orderVo.setOrder(order);
        System.out.println(order.getOrderNo());
        orderVo.setOrderItems(orderItems);
        orderVo.setOrderNo(order.getOrderNo().toString());
        return  ServerResponse.createBySuccess(orderVo);
    }
    public ServerResponse payment(Long orderNo, Integer platform, HttpSession session){
        System.out.println("service");
        User user =(User) session.getAttribute("user");
        if(orderNo == null || platform ==null){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Order order = orderMapper.selectByorderNo(orderNo);
        if(order == null){
            return ServerResponse.createByErrorMessage("支付错误");
        }
        PayInfo payInfo = new PayInfo();
        payInfo.setUserId(user.getId());
        payInfo.setOrderNo(orderNo);
        payInfo.setPayPlatform(platform);
        payInfo.setCreateTime(new Date());
        payInfo.setUpdateTime(new Date());
        payInfoMapper.insertSelective(payInfo);
        order.setPaymentTime(payInfo.getCreateTime());
        order.setStatus(20);
        orderMapper.updateByPrimaryKeySelective(order);
        return ServerResponse.createBySuccess("支付成功");

    }

    public ServerResponse orderCanel(Integer orderid){
        if(orderid == null){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Order order = new Order();
        order.setId(orderid);
        order.setStatus(0);
        int Count = orderMapper.updateByPrimaryKeySelective(order);
        if(Count>0){
            return ServerResponse.createBySuccess("取消成功");
        }else return ServerResponse.createByErrorMessage("订单取消失败");
    }
    public ServerResponse getOrders(HttpSession session,Integer pageNum,Integer pageSize){
        User user = (User)session.getAttribute("user");
        List<OrderVoTw> orderVoTws = new ArrayList<>();
        PageHelper.startPage(pageNum,pageSize);

        List<Order> orders = orderMapper.selectByuserId(user.getId());
        if(orders!=null){
            for(Order order : orders){
                OrderVoTw orderVoTw = new OrderVoTw();
                if(order.getStatus()==10){
                    orderVoTw.setState("未付款");
                }else if(order.getStatus()==20){
                    orderVoTw.setState("已付款");
                }else if(order.getStatus()==40){
                    orderVoTw.setState("已发货");
                }else if(order.getStatus()==50){
                    orderVoTw.setState("交易成功");
                }else if(order.getStatus()==60){
                    orderVoTw.setState("交易关闭");
                }
                orderVoTw.setCreateTime(DateTimeUtil.dateToStr(order.getCreateTime()));
                orderVoTw.setOrderNo(order.getOrderNo().toString());
                orderVoTw.setOrder(order);
                orderVoTws.add(orderVoTw);
            }
        }



        PageInfo pageInfo = new PageInfo(orders);
        pageInfo.setList(orderVoTws);

        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse getOrderInfo(Long orderNo){
        if(orderNo == null){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        List<OrderItem> orderItems = orderItemMapper.selectByorderNo(orderNo);

        OrderVo orderVo = new OrderVo();
        Order order = orderMapper.selectByorderNo(orderNo);
        Shipping shipping = shippingMapper.selectByPrimaryKey(order.getShippingId());
        orderVo.setOrder(order);
        orderVo.setOrderNo(order.getOrderNo().toString());
        orderVo.setOrderItems(orderItems);
        if(shipping!=null) {
            orderVo.setAddress(shipping.toString());
        }
        return ServerResponse.createBySuccess(orderVo);
    }
    public ServerResponse sendOver(Long orderNo){
        if(orderNo == null){
            return  ServerResponse.createByErrorMessage("参数错误");
        }
        Order order = orderMapper.selectByorderNo(orderNo);
        if(order !=null){
           order.setCloseTime(new Date());

            order.setStatus(50);
            orderMapper.updateByPrimaryKeySelective(order);
            return ServerResponse.createBySuccess("收获成功");
        }
        return ServerResponse.createByErrorMessage("确认收获失败");
    }
    public ServerResponse bookApperaise(String desc,Integer bookId,HttpSession session,Long orderNo){
        if(desc == null || bookId ==null){
            return  ServerResponse.createByErrorMessage("参数错误");
        }
        User user = (User)session.getAttribute("user");
        Appraise appraise = new Appraise();
        appraise.setBookId(bookId);
        appraise.setUserId(user.getId());
        appraise.setAppraiseDesc(desc);
        appraise.setCreateTime(new Date());
        appraise.setUpdateTime(new Date());
        int Count = appraiseMapper.insertSelective(appraise);


        if(Count >0){
            return  ServerResponse.createBySuccess("评价成功");
        }
        return ServerResponse.createByErrorMessage("评价失败");

    }
    public ServerResponse orderOver(Long orderNo){
        if(orderNo == null){
            return  ServerResponse.createByErrorMessage("参数错误");
        }
        Order order = orderMapper.selectByorderNo(orderNo);
        if(order !=null){
            order.setEndTime(new Date());

            order.setStatus(60);
            orderMapper.updateByPrimaryKeySelective(order);
            return ServerResponse.createBySuccess("成功");
        }
        return ServerResponse.createByErrorMessage("失败");
    }
public ServerResponse getAppraises(Integer pageNum,Integer pageSize,HttpSession session){
        User user = (User)session.getAttribute("user");
        PageHelper.startPage(pageNum,pageSize);
        List<Appraise> appraises = appraiseMapper.selectByuserId(user.getId());
        List<AppraiseVO> appraiseVOS = new ArrayList<>();
        for(Appraise appraise:appraises){
            AppraiseVO appraiseVO = new AppraiseVO();
            appraiseVO.setAppraise(appraise);
            Book book = bookMapper.selectByPrimaryKey(appraise.getBookId());
            appraiseVO.setBookName(book.getName());
            appraiseVO.setCreateTime(DateTimeUtil.dateToStr(appraise.getCreateTime()));
            appraiseVOS.add(appraiseVO);
        }
        PageInfo pageInfo = new PageInfo(appraises);
        pageInfo.setList(appraiseVOS);
        return ServerResponse.createBySuccess(pageInfo);
}
public ServerResponse deleteAppraise(Integer id){
    if(id==null){
        return ServerResponse.createByErrorMessage("参数错误");
    }
    int count = appraiseMapper.deleteByPrimaryKey(id);
    if(count>0){
        return ServerResponse.createBySuccess("删除成功");
    }
    return ServerResponse.createByErrorMessage("删除失败");

}
}
