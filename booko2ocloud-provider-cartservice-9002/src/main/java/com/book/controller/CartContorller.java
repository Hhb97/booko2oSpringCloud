package com.book.controller;

import com.book.common.ServerResponse;
import com.book.ov.CartVo;
import com.book.pojo.Cart;
import com.book.pojo.User;
import com.book.service.CartService;

import net.sf.jsqlparser.schema.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/portal/cart")
public class CartContorller {
    @Autowired
    CartService cartService;
    @RequestMapping("/add")
    @ResponseBody
    public ServerResponse add(HttpSession session,Integer bookId,Integer count){
        return  cartService.add(session,bookId,count);
    }
    @RequestMapping("/getCart")
    @ResponseBody
    public ServerResponse getCart(HttpSession session){
        User user = (User)session.getAttribute("user");
        ServerResponse<CartVo> data =cartService.cartLimit(user.getId());
        CartVo cartVo = data.getData();
        if(cartVo.getCartProductVoList().size() ==0){
    return ServerResponse.createByErrorMessage("购物车为空");
        }else return data;
    }
    @RequestMapping("/update")
    @ResponseBody
    public ServerResponse update(HttpSession session,Integer bookId,Integer count){
        User user = (User)session.getAttribute("user");
        return cartService.update(user.getId(),bookId,count);
    }
    @RequestMapping("/delete")
    @ResponseBody
    public ServerResponse delete(Integer bookId,HttpSession session){
        User user = (User)session.getAttribute("user");

        return cartService.delete(user.getId(),bookId);
    }
    @RequestMapping("/isChecked")
    @ResponseBody
    public ServerResponse isChecked(Integer bookId,Integer checked,HttpSession session){
        User user = (User)session.getAttribute("user");

        return cartService.isChecked(user.getId(),bookId,checked);
    }
    @RequestMapping("/isNull")
    @ResponseBody
    public ServerResponse isNull(Integer bookId,Integer checked,HttpSession session){
        User user = (User)session.getAttribute("user");

        return ServerResponse.createBySuccess(cartService.isNull(user.getId()));
    }
}
