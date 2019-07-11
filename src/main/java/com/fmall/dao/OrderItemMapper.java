package com.fmall.dao;

import com.fmall.pojo.OrderItem;
import com.fmall.vo.OrderItemShippingVo;
import com.fmall.vo.OrderItemUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    int deleteOrderItem(@Param("userId") Integer userId, @Param("orderNo") Long orderNo);

    List<OrderItemUserVo> selectOrderBySellerIdAndStatus(@Param("buyyerId") Integer buyyerId,@Param("status") Integer status);

    int deleteOrderItemBySeller(Long orderNo);

    OrderItemShippingVo selectByOrderNo(@Param("userId")Integer userId,@Param("orderNo")Long orderNo);

    int queryByOrderNo(@Param("orderNo") Long orderNo);

    List<OrderItem> getByOrderNoUserId(@Param("userId")Integer userId,@Param("orderNo")Long orderNo);
}