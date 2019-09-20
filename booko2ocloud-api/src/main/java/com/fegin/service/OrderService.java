package com.fegin.service;

import javax.servlet.http.HttpSession;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.common.ServerResponse;
import com.book.ov.OrderVo;
import com.book.pojo.User;

@FeignClient(value = "BOOKO2OCLOUD-ORDERSERVICE")

public interface OrderService {
	@RequestMapping(value = "/portal/order/addOrder",method = RequestMethod.GET)
	public ServerResponse<OrderVo> addOrder(@RequestParam("cartIdlist")String cartIdlist,@RequestParam("shippingId")int shippingId );
       
    @RequestMapping(value = "/portal/order/payment",method = RequestMethod.GET)
    public ServerResponse payment(Long orderNo);
    @RequestMapping(value = "/portal/order/cancel",method = RequestMethod.GET)
    public ServerResponse orderCanel(int orderid);
    @RequestMapping(value = "/portal/order/getorders",method = RequestMethod.GET)
    public ServerResponse getOrders(@RequestParam(value = "pageNum",
    defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize );

    @RequestMapping(value = "/portal/order/getorderinfo",method = RequestMethod.GET)
    public ServerResponse getOrderInfo(Long orderNo) ;
    @RequestMapping(value = "/portal/order/sendOver",method = RequestMethod.GET)
    public ServerResponse sendOver(Long orderNo) ;

    @RequestMapping(value = "/portal/order/appraise",method = RequestMethod.GET)
    public ServerResponse Appraise(@RequestParam("desc")String desc,@RequestParam("bookId")int bookId ,@RequestParam("orderNo")Long orderNo) ;
   

    @RequestMapping(value = "/portal/order/orderOve",method = RequestMethod.GET)
    public ServerResponse orderOver(Long orderNo) ;
    @RequestMapping(value = "/portal/order/user/getappraises",method = RequestMethod.GET)
    public ServerResponse getappraises(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize ) ;
      
    @RequestMapping(value = "/portal/order/user/deleteappraise",method = RequestMethod.GET)
    public ServerResponse deleteappraise(int id) ;
}
