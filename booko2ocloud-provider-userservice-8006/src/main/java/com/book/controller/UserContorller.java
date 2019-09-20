package com.book.controller;

import com.book.common.ServerResponse;
import com.book.pojo.User;
import com.book.service.UserService;
import com.book.util.CookieUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserContorller {
    @Autowired
    UserService userService;
    @RequestMapping("/login")
    @ResponseBody
    public ServerResponse login(User user, HttpSession session, HttpServletResponse response,HttpServletRequest request){
        ServerResponse result =userService.login(user.getUsername(),user.getPassword(),response,session);
       // User u = result.getData();
        //Cookie cookie = new Cookie("time","token");
       // response.addCookie(cookie);
        return  result;
    }
    @RequestMapping("/register")
    @ResponseBody
    public ServerResponse register(User user){
       ServerResponse result= userService.register(user);
        return  result;
    }
    @RequestMapping("/check_valid")
    @ResponseBody
    public ServerResponse<String> check_valid(String str,String type){
        return userService.checkValid(str,type);
    }

    @RequestMapping("/get_user_info")
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession httpSession, HttpServletRequest request){//获取用户详细信息
        return userService.getUserInfo(httpSession,request);
    }
    @RequestMapping("/forget_question")
    @ResponseBody
    public ServerResponse<String > get_forget_Question(String username,HttpSession session){

        return userService.SelectQuestion(username);
    }

    @RequestMapping("/forget_check_answer")
    @ResponseBody
    public ServerResponse<String > checkAnswer(String useranme,String question,String answer){//验证问题回答

        return userService.checkAnswer(useranme,question,answer);
    }

    @RequestMapping("/forget_reset_password")
    @ResponseBody
    public ServerResponse<String >forgetResetAnswer(String forgettoken,String Newpassword,String username){//验证问题回答

        return userService.forgetResetPassword(username,forgettoken,Newpassword);
    }
    @RequestMapping("/reset_password")
    @ResponseBody
    public ServerResponse<String >ResetAnswer(String Newpassword,String username,String Oldpasword,HttpSession session){//验证问题回答

        return userService.ResetPassword(Newpassword,username,Oldpasword,session);
    }

    @RequestMapping("/reset_userInfo")
    @ResponseBody
    public ServerResponse<String >ResetInfo(String phone,String username,String email,HttpSession session){//修改用户信息

        return userService.restUserInfo(phone,username,email,session);
    }
    @RequestMapping(value = "/loginout")
    public void loginout(HttpSession session, HttpServletRequest request,HttpServletResponse response){
        session.removeAttribute("user");
        try {
            request.getRequestDispatcher("/WEB-INF/jsp/portal/index.jsp").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
