package com.book.service.imp;

import com.book.common.ServerResponse;
import com.book.dao.AppraiseMapper;
import com.book.dao.BookMapper;
import com.book.dao.UserMapper;
import com.book.ov.bookAppraiseVo;
import com.book.pojo.Appraise;
import com.book.pojo.Book;
import com.book.pojo.User;
import com.book.service.ManageBookService;

import com.book.util.DateTimeUtil;
import com.book.util.FTPUtil;
import com.book.util.PropertiesUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ManageBookServiceImp implements ManageBookService {
    @Autowired
    BookMapper bookMapper;
    @Autowired
    AppraiseMapper appraiseMapper;
    @Autowired
    UserMapper userMapper;
    
    public ServerResponse getList(Integer pageNum,Integer pageSize,Integer categoryId){
        PageHelper.startPage(pageNum,pageSize);
        List<Book> list = bookMapper.getByCategoryId(categoryId);
        if(list==null){
                return  ServerResponse.createByErrorMessage("获取书籍列表失败");
        }
        PageInfo pageInfo = new PageInfo(list);
        return  ServerResponse.createBySuccess(pageInfo);

    }
    public ServerResponse<Book> getInfoById(Integer bookId){
       Book book  = bookMapper.selectByPrimaryKey(bookId);
        if(book==null){
            return ServerResponse.createByErrorMessage("获取信息失败！");
        }
        return ServerResponse.createBySuccess(book);
    }
    public ServerResponse<String> deletebookById(Integer bookId){
        int resultCount = bookMapper.deleteByPrimaryKey(bookId);
      if(resultCount<1){
          return  ServerResponse.createByErrorMessage("获取书籍信息失败");
      }
      return ServerResponse.createBySuccess("ok");
    }
    public ServerResponse<String> updateBookById(Integer bookId,Book book){
        System.out.println(book.getName());
        if(bookId==null||book ==null){
            return  ServerResponse.createByErrorMessage("参数错误");
        }
        book.setId(bookId);
        int ResultCount = bookMapper.updateByPrimaryKeySelective(book);
        if(ResultCount<1){
            return  ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createBySuccess("跟新成功");
    }
    public ServerResponse<String> addBook(Book book, HttpSession session){
        User user = (User)session.getAttribute("admin");
        if(book==null){
            return  ServerResponse.createByErrorMessage("参数错误 添加失败");
        }
        String [] imgs = book.getSubImages().split(",");
        for(String str : imgs){
            System.out.println(str);
        }
        if(imgs.length!=0) {
            book.setMainImage(imgs[0]);
        }
        book.setCreateTime(new Date());
        book.setUpdateTime((new Date()));
        int ResultCount = bookMapper.insert(book);
        if(ResultCount==0){
            return  ServerResponse.createByErrorMessage("添加图书失败");
        }

        String path = PropertiesUtil.getProperty("uploadpath")+user.getUsername()+"\\";
        System.out.println(path);
        File files = new File(path);
        List<File> fileList = new ArrayList<>(Arrays.asList(files.listFiles()));
        String [] strings = DateTimeUtil.dateToStr(new Date()).split(" ");

        try {
            FTPUtil.uploadFile(fileList, strings[0]);
            //uploadService.DeleteFile(session);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("添加失败");

        }
        return ServerResponse.createBySuccess("添加成功");

    }
    public ServerResponse getAppraise(Integer id){
        List<Appraise> appraises = appraiseMapper.selectBybookId(id);
        List<bookAppraiseVo> bookAppraiseVos = new ArrayList<>();
        for(Appraise appraise:appraises){
            bookAppraiseVo bookAppraiseVo = new bookAppraiseVo();
            bookAppraiseVo.setDesc(appraise.getAppraiseDesc());
            User user = (User)userMapper.selectByPrimaryKey(appraise.getUserId());
            bookAppraiseVo.setCreateTime(DateTimeUtil.dateToStr(appraise.getCreateTime()));
            bookAppraiseVo.setUsername(user.getUsername());
            bookAppraiseVos.add(bookAppraiseVo);
        }
        return ServerResponse.createBySuccess(bookAppraiseVos);
    }
    public  ServerResponse getbookBycategoryId(Integer pageNum,Integer pageSize,Integer categoryId){
        PageHelper.startPage(pageNum,pageSize);
        List<Book> books = bookMapper.getByCategoryId(categoryId);
        PageInfo pageInfo = new PageInfo(books);
        return ServerResponse.createBySuccess(pageInfo);
    }
    public ServerResponse deletebook(Integer bookId){
        int count = bookMapper.deleteByPrimaryKey(bookId);
        if(count>0){
            return  ServerResponse.createBySuccess("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }
}
