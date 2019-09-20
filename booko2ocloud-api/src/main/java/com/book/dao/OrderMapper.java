package com.book.dao;

import com.book.ov.StatisticsVo;
import com.book.pojo.Order;
import org.apache.ibatis.annotations.Param;


import java.util.List;


public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Order selectByorderNo(@Param("orderNo") Long orderNo);

    List<Order> selectByuserId(@Param("userId") Integer userId);
    List<Order> selectList();
    List<StatisticsVo> selectByYear();
    List<StatisticsVo> selectBymonth();
    List<StatisticsVo> selectByweek();
    List<StatisticsVo> selectByday();

}