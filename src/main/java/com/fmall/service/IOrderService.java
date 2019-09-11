package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.Order;
import com.fmall.vo.OrderItemShippingVo;
import com.fmall.vo.ProductIdAndQuantiry;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author ZSS
 * @description order service
 */
public interface IOrderService {

    /**
     * 生成订单
     *
     * @param userId     用户id
     * @param productId  商品id
     * @param shippingId 收货地址id
     * @param quantity   数量
     * @return ServerResponse
     */
    ServerResponse<String> createOrder(Integer userId, Integer productId, Integer shippingId, Integer quantity);

    /**
     * 删除订单
     *
     * @param userId  用户id
     * @param orderNo 订单号
     * @return ServerResponse
     */
    ServerResponse<String> deleteOrder(Integer userId, Long orderNo);

    /**
     * 查询个人订单
     *
     * @param userId 用户id
     * @param pn     当前页
     * @param status 状态
     * @return ServerResponse
     */
    ServerResponse<PageInfo> showOrder(Integer userId, Integer pn, Integer status);

    /**
     * 完成支付
     *
     * @param userId  用户id
     * @param orderNo 订单号
     * @param path    路径
     * @return ServerResponse
     */
    ServerResponse pay(Integer userId, Long orderNo, String path);

    /**
     * 支付宝回调接口的实现
     *
     * @param params 参数
     * @return ServerResponse
     */
    ServerResponse alipayCallback(Map<String, String> params);

    /**
     * 轮询
     *
     * @param userId  用户id
     * @param orderNo 订单号
     * @return ServerResponse
     */
    ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);

    /**
     * 查看个人购物车中已勾选的商品
     *
     * @param userId 用户id
     * @return ServerResponse
     */
    List<ProductIdAndQuantiry> selectCheckedProductFromCart(Integer userId);

    /**
     * 查出指定用户被勾选的购物车好，然后将这些删除
     *
     * @param id id
     * @return List<Integer>
     */
    List<Integer> selectCheckedCarIdByUserId(Integer id);

    /**
     * 根据id删除购物车中的商品
     *
     * @param cartId 购物车id
     * @return int
     */
    int deleteCartById(Integer cartId);

    /**
     * 商家查看订单
     *
     * @param buyyerId 买家id
     * @param status   状态
     * @param pn       当前页面
     * @return ServerResponse
     */
    ServerResponse<PageInfo> selectOrderBySellerIdAndStatus(Integer buyyerId, Integer status, Integer pn);

    /**
     * 卖家单方面删除订单
     *
     * @param orderNo 订单号
     * @return ServerResponse
     */
    ServerResponse<String> deleteOrderBySeller(Long orderNo);

    /**
     * 展示订单详情
     *
     * @param userId  用户id
     * @param orderNo 订单号
     * @return OrderItemShippingVo
     */
    OrderItemShippingVo selectOrderDetail(Integer userId, Long orderNo);

    /**
     * 卖家发货
     *
     * @param orderNo 订单号
     * @param boxId   盒子id
     * @return ServerResponse
     */
    ServerResponse<String> sendOrder(Long orderNo, Integer boxId);

    /**
     * 通过订单号查询
     *
     * @param orderNo 订单号
     * @return int
     */
    int queryByOrderNo(Long orderNo);

    /**
     * 查询所有订单
     *
     * @param page 当前页
     * @param size 分页大小
     * @return List<Order>
     */
    List<Order> queryAllOrders(int page, int size);

    /**
     * 通过订单号查询订单
     *
     * @param page    当前页
     * @param size    分页大小
     * @param orderNo 订单编号
     * @return List<Order>
     */
    List<Order> queryOrdersByOrderNo(int page, int size, Long orderNo);

    /**
     * 删除订单
     *
     * @param id 订单id
     */
    void deleteOrderById(String id);
}
