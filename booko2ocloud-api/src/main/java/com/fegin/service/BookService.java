package com.fegin.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.common.ServerResponse;
import com.book.pojo.Book;

@FeignClient(value = "BOOKO2OCLOUD-BOOKSERVICE")

public interface BookService {
	 	@RequestMapping(value ="/portal/book/getList",method = RequestMethod.GET)
	   
	    public ServerResponse getBook(@RequestParam(value = "pageNum")int pageNum, @RequestParam(value = "pageSize")int pageSize,
	    		@RequestParam(value = "categoryId")int categoryId);
	       
	    @RequestMapping(value = "portal/book/getbookInfo",method = RequestMethod.GET)
	 
	    public ServerResponse getBookInfo(@RequestParam("id")int id);
	    @RequestMapping(value = "/portal/book/getbookappraise",method = RequestMethod.GET)
	   
	    public ServerResponse getAppraise(@RequestParam("id")int id);

	    @RequestMapping(value = "/portal/book/getbookByCategory",method = RequestMethod.GET)
	 
	    public ServerResponse getbookBycategoryId(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
	                                                    @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,int categoryId);
	      
	    @RequestMapping(value = "/portal/book/getCategory",method = RequestMethod.GET)
	   
	    public List<HashMap<Object,Object>> getcategory();
}
