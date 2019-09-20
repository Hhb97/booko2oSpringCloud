package com.book.service;

import com.book.common.ServerResponse;
import com.book.pojo.User;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface UserService {
    public ServerResponse<User> login(String username, String password, HttpServletResponse response,HttpSession session) throws Exception;
    public ServerResponse<String> register(User user);
    public ServerResponse<String>checkValid(String str,String type);
    public ServerResponse<User>getUserInfo(HttpSession httpSession, HttpServletRequest request);
    public ServerResponse<String> SelectQuestion(String username);
    public ServerResponse<String> checkAnswer(String username,String question,String answer);
    public ServerResponse<String> forgetResetPassword(String forgettoken,String Newpassword,String username);//忘记密码根据用户名修改密码
    public ServerResponse<String> alterUserById(User user);
    public ServerResponse<String> ResetPassword(String Newpassword,String username,String Oldpassword,HttpSession session);//修改密码
    public ServerResponse restUserInfo(String phone,String username,String email,HttpSession session);
}
