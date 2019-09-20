package com.book.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.common.ServerResponse;
import com.book.ov.OrderVo;
import com.fegin.service.OrderService;
@RestController
@RequestMapping("/consumer/order")
public class OrderServiceController {
	@Autowired
	OrderService orderService = null;
	@RequestMapping("/addOrder")
	public ServerResponse<OrderVo> addOrder(String cartIdlist,Integer shippingId,HttpSession session){
		return orderService.addOrder(cartIdlist, shippingId);
	}
       
    @RequestMapping("/payment")
    public ServerResponse payment(Long orderNo) {
    	return orderService.payment(orderNo);
    }
    @RequestMapping("/cancel")
    public ServerResponse orderCanel(Integer orderid) {
    	return orderCanel(orderid);
    }
    @RequestMapping("/getorders")
    public ServerResponse getOrders(@RequestParam(value = "pageNum",
    defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,HttpSession session) {
    	return orderService.getOrders(pageNum, pageSize);
    }

    @RequestMapping("/getorderinfo")
    public ServerResponse getOrderInfo(Long orderNo) {
    	return orderService.getOrderInfo(orderNo);
    }
    @RequestMapping("/sendOver")
    public ServerResponse sendOver(Long orderNo) {
    	return orderService.sendOver(orderNo);
    }

    @RequestMapping("/appraise")
    public ServerResponse Appraise(String desc,Integer bookId,HttpSession session,Long orderNo) {
    	return orderService.Appraise(desc, bookId, orderNo);
    }
   

    @RequestMapping("/orderOve")
    public ServerResponse orderOver(Long orderNo) {
    	return orderService.orderOver(orderNo);
    }
    @RequestMapping("/user/getappraises")
    public ServerResponse getappraises(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,HttpSession session) {
    	return orderService.getappraises(pageNum, pageSize);
    }
      
    @RequestMapping("/user/deleteappraise")
    public ServerResponse deleteappraise(Integer id) {
    	return orderService.deleteappraise(id);
    }
}
