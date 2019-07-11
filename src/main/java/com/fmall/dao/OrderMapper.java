package com.fmall.dao;

import com.fmall.pojo.Order;
import com.fmall.vo.OrderItemVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int deleteOrder(@Param("userId") Integer userId,@Param("orderNo") Long orderNo);

    List<OrderItemVo> showOrdersByUserId(@Param("userId") Integer userId,@Param("status") Integer status);

    Order selectExist(@Param("userId") Integer userId,@Param("orderNo") Long orderNo);

    int updateStatusByOrderNo(@Param("status") Integer status,@Param("orderNo") Long orderNo);

    Integer checkedShippingId(Long orderNo);

    int deleteOrderBySeller(Long orderNo);

    int updateBoxIdAndStatusByOrderNo(@Param("orderNo")Long orderNo,@Param("boxId") Integer boxId,@Param("status") int status);

    List<Order> queryAllOrders();

    List<Order> queryOrdersByOrderNo(@Param("orderNo") Long orderNo);

    void deleteOrderById(@Param("orderNo")String id);

    Order selectByOrderNo(Long orderNo);
}