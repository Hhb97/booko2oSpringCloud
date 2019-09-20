package com.book.service.imp;

import com.book.common.Const;
import com.book.common.ServerResponse;
import com.book.common.TokenCache;
import com.book.dao.UserMapper;
import com.book.pojo.User;
import com.book.service.UserService;
import com.book.util.CookieUtil;
import com.book.util.JsonUtil;
import com.book.util.JwtTokenUtil;
import com.book.util.MD5Util;
import com.book.util.jedisUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    UserMapper userMapper;
    public ServerResponse<User> login(String username , String password, HttpServletResponse response,HttpSession session) throws Exception{
            System.out.println(username);
             int ResultCount = userMapper.selectByUsername(username);
             if(ResultCount<=0){
                 return  ServerResponse.createByErrorMessage("用户不存在");
             }
            String MD5password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username,MD5password);
        if(user==null){
            return ServerResponse.createByErrorMessage("密码错误！");
        }else {
            user.setPassword(StringUtils.EMPTY);
            String token = JwtTokenUtil.createToken(user.getId().toString(),user.getUsername());
            System.out.println(token);
            //jedisUtil.set(session.getId(), JsonUtil.obj2String(user));
            CookieUtil.wirterCookie(response,token);
           
            
            
            


            return ServerResponse.createBySuccess(user);
        }

    }

    @Override
    public ServerResponse<String> register(User user) {
        user.setRole(1);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        ServerResponse<String> response = this.checkValid(user.getUsername(), Const.USERNAME);
        if(!response.isSuccess()){
            return  response;
        }
        response = this.checkValid(user.getEmail(),Const.EMAIL);
        if(!response.isSuccess()){
            return response;
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        String MD5password = MD5Util.MD5EncodeUtf8(user.getPassword());
        user.setPassword(MD5password);
        int resultCount = userMapper.insertSelective(user);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }
    public ServerResponse<String>checkValid(String str,String type){//校验用户名和email
        if(org.apache.commons.lang3.StringUtils.isNoneBlank(type)) {
            if (Const.USERNAME.equals(type)) {
                int resultCount = userMapper.checkByusername(str);
                if (resultCount > 0) {
                    System.out.println("geelyzai");
                    return ServerResponse.createByErrorMessage("用户已存在!");
                }
            }

        }
        else ServerResponse.createByErrorMessage("参数错误");
        return ServerResponse.createBySuccessMessage("校验成功！");
    }

    public ServerResponse<User> getUserInfo(HttpSession httpSession, HttpServletRequest request){
       User user = (User)httpSession.getAttribute("user");

        if(user==null){
            return  ServerResponse.createByErrorMessage("用户未登录，请登录");
       }
       return ServerResponse.createBySuccess(user);
    }

    public ServerResponse<String> SelectQuestion(String username){//获取用户问题
       if(checkValid(username,Const.USERNAME).isSuccess()){
                return ServerResponse.createByErrorMessage("用户不存在");
       }
        String Question = userMapper.SelectQuestionByUsername(username);
        if(org.apache.commons.lang3.StringUtils.isBlank(Question)){
            return ServerResponse.createByErrorMessage("用户未设置问题");
        }

        return  ServerResponse.createBySuccess(Question);
    }

    public ServerResponse<String> checkAnswer(String username,String question,String answer){
        if(!checkValid(username,Const.USERNAME).isSuccess()){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        int ResultConut = userMapper.SelectCheckAnswer(username,question,answer);
        if(ResultConut>0){
            String forget_token = UUID.randomUUID().toString();
            TokenCache.setKey("token_"+username,forget_token);
            return ServerResponse.createBySuccess(forget_token);
        }
        return ServerResponse.createByErrorMessage("问题回答错误");
    }

    public ServerResponse<String> forgetResetPassword(String forgettoken,String Newpassword,String username){//忘记密码根据用户名修改密码
        String Value = TokenCache.getKey("token_"+username);
        if(Value.equals("null")){
            return ServerResponse.createByErrorMessage("Token失效");

        }
        if(org.apache.commons.lang3.StringUtils.equals(forgettoken,Value)){
            String MD5password = MD5Util.MD5EncodeUtf8(Newpassword);
            int ResultCount = userMapper.updatepasswordByusername(username,MD5password);
            if(ResultCount>=0) {
                return ServerResponse.createBySuccess("修改密码成功");
            }
        }
        return ServerResponse.createByErrorMessage("修改密码操作实现");
    }
    public ServerResponse<String> ResetPassword(String Newpassword,String username,String Oldpassword,HttpSession session){

        int ResultCount = userMapper.selectByUsername(username);
        if(ResultCount<=0){
            return  ServerResponse.createByErrorMessage("用户不存在");
        }
        System.out.println(Oldpassword);
        String MD5password = MD5Util.MD5EncodeUtf8(Oldpassword);
        User user = userMapper.selectLogin(username,MD5password);
        if(user==null){
            return ServerResponse.createByErrorMessage("旧密码错误！");
        }else {
            user.setPassword(StringUtils.EMPTY);

            user.setPassword(MD5Util.MD5EncodeUtf8(Newpassword));
            session.removeAttribute("user");
            userMapper.updateByPrimaryKeySelective(user);
            return ServerResponse.createBySuccessMessage("修改密码操作实现");

        }

    }
    public ServerResponse<String> alterUserById(User user){
        if(user.getId()!=null){
            int ResultCount = userMapper.updateByPrimaryKeySelective(user);
            if(ResultCount>0){
                return  ServerResponse.createBySuccessMessage("修改用户信息成功");
            }
        }
        return ServerResponse.createByErrorMessage("修改用户信息失败");
    }
    public ServerResponse restUserInfo(String phone,String username,String email,HttpSession session){
        User user =(User)session.getAttribute("user");
        if(phone ==null||username==null||email==null){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        user.setUsername(username);
        user.setPhone(phone);
        user.setEmail(email);
      int count=  userMapper.updateByPrimaryKeySelective(user);
      if(count>0){
          user.setPassword("");
          return ServerResponse.createBySuccess(user);
      }
      else return ServerResponse.createByErrorMessage("修改信息错误");
    }
}
