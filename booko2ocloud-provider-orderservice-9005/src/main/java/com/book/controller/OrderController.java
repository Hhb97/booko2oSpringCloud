package com.book.controller;

import com.book.common.ServerResponse;
import com.book.ov.OrderVo;
import com.book.pojo.User;
import com.book.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
@RequestMapping("/portal/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @RequestMapping("/addOrder")
    @ResponseBody
    public ServerResponse<OrderVo> addOrder(String cartIdlist,Integer shippingId,HttpSession session){
        User user = (User)session.getAttribute("user");
      return orderService.addOrder(user.getId(),cartIdlist,shippingId);
    }
    @RequestMapping("/payment")
    @ResponseBody
    public ServerResponse payment(Long orderNo, HttpSession session) {
        System.out.println("payment");
       return orderService.payment(orderNo,1,session);

    }

    @RequestMapping("/cancel")
    @ResponseBody
    public ServerResponse orderCanel(Integer orderid) {
    return orderService.orderCanel(orderid);
    }
    @RequestMapping("/getorders")
    @ResponseBody
    public ServerResponse getOrders(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,HttpSession session) {
        return orderService.getOrders(session,pageNum,pageSize);
    }
    @RequestMapping("/getorderinfo")
    @ResponseBody
    public ServerResponse getOrderInfo(Long orderNo) {
        return orderService.getOrderInfo(orderNo);
    }
    @RequestMapping("/sendOver")
    @ResponseBody
    public ServerResponse sendOver(Long orderNo) {
        return orderService.sendOver(orderNo);
    }

    @RequestMapping("/appraise")
    @ResponseBody
    public ServerResponse Appraise(String desc,Integer bookId,HttpSession session,Long orderNo) {
        return orderService.bookApperaise(desc,bookId,session,orderNo);
    }

    @RequestMapping("/orderOve")
    @ResponseBody
    public ServerResponse orderOver(Long orderNo) {
        return orderService.orderOver(orderNo);
    }
    @RequestMapping("/user/getappraises")
    @ResponseBody
    public ServerResponse getappraises(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,HttpSession session) {
        return orderService.getAppraises(pageNum,pageSize,session);
    }
    @RequestMapping("/user/deleteappraise")
    @ResponseBody
    public ServerResponse deleteappraise(Integer id) {
        return orderService.deleteAppraise(id);
    }
}
