package com.book.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.common.ServerResponse;
import com.book.pojo.User;
import com.fegin.service.UserService;
@RestController
@RequestMapping("consumer/user")
public class UserServiceController {
	@Autowired
	UserService userService;
	@RequestMapping("/login")
	public ServerResponse login( User user){
		System.out.println(user.getUsername());
		return userService.login(user);
	}
       
    @RequestMapping("/register")
    public ServerResponse register(User user) {
    	return userService.register(user);
    }
    @RequestMapping("/check_valid")
    public ServerResponse<String> check_valid(String str,String type){
    	return userService.check_valid(str, type);
    }

    @RequestMapping("/get_user_info")
    public ServerResponse<User> getUserInfo( ){//获取用户详细信息
    	return userService.getUserInfo();
    }
    @RequestMapping("/forget_question")
    public ServerResponse<String > get_forget_Question(String username){
    	return userService.get_forget_Question(username);
    }

  


    

    @RequestMapping("/reset_userInfo")
    public ServerResponse<String >ResetInfo(String phone,String username,String email){
    	//修改用户信息
    	return userService.ResetInfo(phone, username, email);
    }

        

}
