package com.book.service.imp;

import com.book.common.Const;
import com.book.common.ServerResponse;
import com.book.dao.BookMapper;
import com.book.dao.CartMapper;
import com.book.ov.CartProductVo;
import com.book.ov.CartVo;
import com.book.pojo.Book;
import com.book.pojo.Cart;
import com.book.pojo.User;
import com.book.service.CartService;
import com.book.util.BigDecimalUtil;
import com.book.util.PropertiesUtil;


import net.sf.jsqlparser.schema.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    BookMapper bookMapper;
    @Override
    public ServerResponse add(HttpSession session, Integer bookId, Integer count) {
        User user = (User)session.getAttribute("user");
       if(bookId ==null || count == null){
           return  ServerResponse.createByErrorMessage("参数错误");
       }
        Cart cart = cartMapper.selectByuserIdBybookId(bookId,user.getId());
       if(cart == null){
           cart = new Cart();
           cart.setBookId(bookId);
           cart.setUserId(user.getId());
           cart.setQuantity(count);
           cart.setCreateTime(new Date());
           cart.setUpdateTime(new Date());
           cart.setChecked(Const.Cart.CHECKED);
           cartMapper.insertSelective(cart);
       }else {
           System.out.println("you");
           Integer quantity = cart.getQuantity()+count;
           cart.setQuantity(quantity);
           cart.setChecked(Const.Cart.CHECKED);
           cartMapper.updateByPrimaryKeySelective(cart);
       }
        return ServerResponse.createBySuccess("添加购物车成功");
    }
    public ServerResponse<CartVo> update(Integer userId, Integer bookId, Integer count){
        if(bookId ==null || count ==null){
            return  ServerResponse.createByErrorMessage("参数错误");
        }
        System.out.println(count);
      Cart cart = cartMapper.selectByuserIdBybookId(bookId,userId);
        cart.setQuantity(count);
        cartMapper.updateByPrimaryKeySelective(cart);
        return this.cartLimit(userId);
    }
    public ServerResponse<CartVo> delete(Integer userId,Integer bookId){//删除购物车 书籍
        if(bookId == null){
            return ServerResponse.createByErrorMessage("参数错误");
        }
      int Cont=  cartMapper.DeleteByuserIdBybookId(userId,bookId);
        if(Cont==0){
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return this.cartLimit(userId);
    }
public ServerResponse<CartVo> isChecked(Integer userId,Integer bookId,Integer checked){
        if(bookId == null || checked == null){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Cart cart = cartMapper.selectByuserIdBybookId(bookId,userId);
        if(cart ==null){
            return  ServerResponse.createByErrorMessage("isCheck失败");
        }
        cart.setChecked(checked);
        cartMapper.updateByPrimaryKeySelective(cart);
        return this.cartLimit(userId);
}
   public  ServerResponse<CartVo> cartLimit(Integer userid){
        CartVo cartVo = new CartVo();
        BigDecimal CartVototalPrice = new BigDecimal("0");
       List<CartProductVo> cartProductVos = new ArrayList<>();
        List<Cart> cartList = cartMapper.selectByuserId(userid);
        if(cartList!=null &&cartList.size()>0){
            for(Cart cart : cartList){
                CartProductVo cartProductVo = new CartProductVo();
                Book book = bookMapper.selectByPrimaryKey(cart.getBookId());
                cartProductVo.setUserId(cart.getUserId());
                cartProductVo.setId(cart.getId());
                cartProductVo.setProductStock(book.getStock());
                cartProductVo.setProductId(book.getId());
                cartProductVo.setProductMainImage(book.getMainImage());
                cartProductVo.setProductName(book.getName());
                cartProductVo.setProductChecked(cart.getChecked()==1);
                cartProductVo.setProductPrice(book.getPrice());
                cartProductVo.setProductSubtitle(book.getSubtitle());
                BigDecimal totalPrice = BigDecimalUtil.mul(book.getPrice().doubleValue(),cart.getQuantity().doubleValue());
                cartProductVo.setProductTotalPrice(totalPrice);
                int limtquantity =0;
                if(cart.getQuantity()<book.getStock()){
                    cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_SUCCESS);
                    cartProductVo.setQuantity(cart.getQuantity());

                }else {
                    cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_FAIL);
                    cart.setQuantity(book.getStock());
                    cartMapper.updateByPrimaryKeySelective(cart);
                    cartProductVo.setQuantity(book.getStock());
                }
                System.out.println("cartVototalprice:"+CartVototalPrice);
                System.out.println("bookToatalPrice:"+cartProductVo.getProductTotalPrice());
                if(cartProductVo.getProductChecked()) {
                    CartVototalPrice = BigDecimalUtil.add(CartVototalPrice.doubleValue(), cartProductVo.getProductTotalPrice().doubleValue());
                }
                cartProductVos.add(cartProductVo);
                System.out.println("cartVototalprice:"+CartVototalPrice);
            }
        }
            cartVo.setAllChecked(AllChecked(userid));
        cartVo.setCartProductVoList(cartProductVos);
        cartVo.setCartTotalPrice(CartVototalPrice);
        cartVo.setImageHost(PropertiesUtil.getProperty("Imagehost"));
        return ServerResponse.createBySuccess(cartVo);
   }

   public boolean AllChecked(Integer userid){
       return cartMapper.selectByuserIdChecked(userid)==0;
   }

   public boolean isNull(Integer userid){
      List<Cart> carts= cartMapper.selectByuserId(userid);
      if(carts==null || carts.size()==0){
          return true;
      }
      return false;
   }

}
