package com.book.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    //private final  static String Cookiedoman="bo.console.takeware.cn";
    private final static String CookieName="BookO2O_Login";
    public static  void wirterCookie(HttpServletResponse response,String token) {//写入Cookie

        Cookie cookie = new Cookie(CookieName, token);
       // cookie.setDomain(Cookiedoman);
        //cookie.setDomain(Cookiedoman);
        cookie.setPath("/");
        cookie.setMaxAge(30 * 60 );
        response.addCookie(cookie);
    }
    public static String readCookie(HttpServletRequest request){//读取cookie
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length!=0){
            for(Cookie cookie:cookies){
                System.out.println(cookie.getName());
                if(StringUtils.equals(cookie.getName(),CookieName)){
                    return cookie.getValue();
                }

            }
        }
        return null;
    }
    public static void delCookie(HttpServletRequest request,HttpServletResponse response){//删除cookie
        Cookie[] cookies = request.getCookies();
        if(cookies.length!=0){
            for(Cookie cookie:cookies){
                if(StringUtils.equals(cookie.getName(),CookieName)){
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }

            }
        }

    }
}
