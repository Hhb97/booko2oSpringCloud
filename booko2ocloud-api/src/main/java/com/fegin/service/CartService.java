package com.fegin.service;

import javax.servlet.http.HttpSession;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.common.ServerResponse;
import com.book.ov.CartVo;
import com.book.pojo.User;

@FeignClient(value="BOOKO2OCLOUD-CARTSERVICE")

public interface CartService {
		@RequestMapping(value = "/portal/cart/add",method = RequestMethod.GET)
	   
		public ServerResponse add(@RequestParam("bookId")int bookId,@RequestParam("count")int count);
	    @RequestMapping(value = "/portal/cart/getCart",method = RequestMethod.GET)
	   
	    public ServerResponse getCart();
	    @RequestMapping(value = "/portal/cart/update",method = RequestMethod.GET)
	    
	    public ServerResponse update(@RequestParam("bookId")int bookId,@RequestParam("count")int count);
	    @RequestMapping(value = "/portal/cart/delete",method = RequestMethod.GET)
	   
	    public ServerResponse delete(int bookId);
	    @RequestMapping(value = "/portal/cart/isChecked",method = RequestMethod.GET)
	   
	    public ServerResponse isChecked(@RequestParam("bookId")int bookId,@RequestParam("count")int checked);
	    @RequestMapping(value = "/portal/cart/isNull",method = RequestMethod.GET)
	   
	    public ServerResponse isNull(@RequestParam("bookId")int bookId,@RequestParam("checked")int checked);
}
