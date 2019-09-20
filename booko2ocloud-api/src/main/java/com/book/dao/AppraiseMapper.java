package com.book.dao;

import com.book.pojo.Appraise;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppraiseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Appraise record);

    int insertSelective(Appraise record);

    Appraise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Appraise record);

    int updateByPrimaryKey(Appraise record);

    List<Appraise> selectByuserId(@Param("userId")Integer userId);
    List<Appraise> selectBybookId(@Param("bookId")Integer bookId);
}