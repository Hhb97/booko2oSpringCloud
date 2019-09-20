package com.book.dao;

import com.book.pojo.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    List<Category> selectByparentId(@Param("parentId") Integer parentId);
    List<Category> selectAllCategory();
    int batchDeleteCategory(@Param("idarray") String[] idarray);

}