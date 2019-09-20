package com.book.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.common.ServerResponse;
import com.book.pojo.Book;
import com.book.util.JsonUtil;
import com.fegin.service.BookService;
@RestController
@RequestMapping("/consumer")
public class BookServiceController {
	@Autowired
	BookService bookService = null;
	@RequestMapping("/book/getList")
    public ServerResponse getBook(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
    		@RequestParam(value = "categoryId",defaultValue = "-1")Integer categoryId){
		ServerResponse result = bookService.getBook(pageNum, pageSize, categoryId);
		System.out.println(JsonUtil.obj2String(result));
		return result;
	}
       
    @RequestMapping("/book/getbookInfo")
    public ServerResponse<Book> getBookInfo(@RequestParam("id") int id){
    	return bookService.getBookInfo(id);
    }
    @RequestMapping("/book/getbookappraise")
    public ServerResponse<Book> getAppraise(Integer id){
    	return bookService.getAppraise(id);
    }

    @RequestMapping("/book/getbookByCategory")
    public ServerResponse<Book> getbookBycategoryId(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                                    @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,Integer categoryId){
    	return bookService.getbookBycategoryId(pageNum, pageSize, categoryId);
    }
      
    @RequestMapping("/book/getCategory")
    public List<HashMap<Object,Object>> getcategory(){
    	return bookService.getcategory();
    }
}
