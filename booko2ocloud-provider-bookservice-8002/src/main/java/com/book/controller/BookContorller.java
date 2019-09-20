package com.book.controller;

import com.book.common.ServerResponse;
import com.book.pojo.Book;
import com.book.service.ManageBookService;
import com.book.service.ManageCategoryService;
import net.sf.jsqlparser.schema.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/portal/")
public class BookContorller {
    @Autowired
    ManageBookService manageBookService;
    @Autowired
    ManageCategoryService manageCategoryService;
    @RequestMapping("/book/getList")
    @ResponseBody
    public ServerResponse<Book> getBook(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
    		@RequestParam(value = "categoryId",defaultValue = "-1")Integer categoryId){
        return manageBookService.getList(pageNum,pageSize,categoryId);
    }
    @RequestMapping("/book/getbookInfo")
    @ResponseBody
    public ServerResponse<Book> getBookInfo(Integer id){
        return  manageBookService.getInfoById(id);

    }
    @RequestMapping("/book/getbookappraise")
    @ResponseBody
    public ServerResponse<Book> getAppraise(Integer id){
        return  manageBookService.getAppraise(id);

    }

    @RequestMapping("/book/getbookByCategory")
    @ResponseBody
    public ServerResponse<Book> getbookBycategoryId(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                                    @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,Integer categoryId){
        return  manageBookService.getbookBycategoryId(pageNum,pageSize,categoryId);

    }
    @RequestMapping("/book/getCategory")
    @ResponseBody
    public List<HashMap<Object,Object>> getcategory(){
        return manageCategoryService.getAllCategory();

    }

}
