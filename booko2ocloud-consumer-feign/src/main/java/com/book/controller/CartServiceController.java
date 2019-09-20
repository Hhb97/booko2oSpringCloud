package com.book.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.common.ServerResponse;
import com.fegin.service.CartService;
@RestController
@RequestMapping("/consumer/cart")
public class CartServiceController {
	@Autowired
	CartService cartService = null;
	@RequestMapping("/add")
	public ServerResponse add(Integer bookId,Integer count) {
		return cartService.add( bookId, count);
	}
    @RequestMapping("/getCart")
    public ServerResponse getCart() {
    	return cartService.getCart();
    }
    @RequestMapping("/update")
    public ServerResponse update(Integer bookId,Integer count) {
    	return cartService.update( bookId, count);
    }
    @RequestMapping("/delete")
    public ServerResponse delete(Integer bookId ) {
    	return cartService.delete(bookId);
    }
    @RequestMapping("/isChecked")
    public ServerResponse isChecked(Integer bookId,Integer checked ) {
    	return cartService.isChecked(bookId, checked);
    }
    @RequestMapping("/isNull")
    public ServerResponse isNull(Integer bookId,Integer checked ) {
    	return cartService.isNull(bookId, checked);
    }
}
