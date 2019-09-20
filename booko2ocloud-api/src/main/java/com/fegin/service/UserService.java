package com.fegin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.common.ServerResponse;
import com.book.pojo.User;

@FeignClient(value = "BOOKO2OCLOUD-USERSERVICE")

public interface UserService {
	@RequestMapping(value= "/user/login",method = RequestMethod.POST)
	public ServerResponse login( User user); 
    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    public ServerResponse register(@RequestBody User user);
    @RequestMapping(value = "/user/check_valid",method = RequestMethod.GET)
    public ServerResponse<String> check_valid(@RequestParam("strr")String str,@RequestParam("type")String type);
    @RequestMapping(value = "/user/get_user_info",method = RequestMethod.GET)
    public ServerResponse<User> getUserInfo();//获取用户详细信息
    @RequestMapping(value = "/user/forget_question",method = RequestMethod.GET)
    public ServerResponse<String > get_forget_Question(String username );
    @RequestMapping(value = "/user/reset_userInfo",method = RequestMethod.GET)
    public ServerResponse<String >ResetInfo(@RequestParam("phone")String phone,@RequestParam("username")String username,@RequestParam("email")String email );//修改用户信息 


    
}
