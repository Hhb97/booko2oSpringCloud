package com.book.dao;

import com.book.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    Cart selectByuserIdBybookId(@Param("bookid") Integer bookid, @Param("userid") Integer userid);

    List<Cart> selectByuserId(@Param("userId") Integer userId);

    List<Cart> selectBychecked(@Param("userId") Integer userId);

    int selectByuserIdChecked(@Param("userId") Integer userId);
    int DeleteByuserIdBybookId(@Param("userId") Integer userId, @Param("bookId") Integer bookId);
}