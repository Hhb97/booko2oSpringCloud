package com.book.dao;

import com.book.pojo.User;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int selectByUsername(@Param("username") String username);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    int checkByusername(@Param("username") String username);

    int checkByemail(@Param("email") String email);

    String SelectQuestionByUsername(@Param("username") String username);

    int SelectCheckAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String aswer);

    int updatepasswordByusername(@Param("useranme") String username, @Param("NewPassword") String password);

    List<User> selectList();

    List<User> selectBykeword(@Param("username") String username, @Param("userId") Integer userId, @Param("role") Integer role);
}