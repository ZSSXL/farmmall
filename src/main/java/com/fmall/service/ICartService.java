package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.Cart;
import com.github.pagehelper.PageInfo;

/**
 * @author ZSS
 * @description cart service
 */
public interface ICartService {

    /**
     * 添加购物车
     *
     * @param cart 购物车实体
     * @return ServerResponse
     */
    ServerResponse<String> addCart(Cart cart);

    /**
     * 分页查询购物车
     *
     * @param userId 用户id
     * @param pn     当前页
     * @return ServerResponse
     */
    ServerResponse<PageInfo> showCarts(Integer userId, Integer pn);

    /**
     * 通过userid和cartid删除指定的购物车商品
     *
     * @param userId 用户id
     * @param cartId 购物车id
     * @return ServerResponse
     */
    ServerResponse<String> deleteCartByUserIdCartId(Integer userId, Integer cartId);

    /**
     * 修改购物车中的商品
     *
     * @param cart 购物车实体
     * @return ServerResponse
     */
    ServerResponse<String> editCart(Cart cart);

    /**
     * 修改单个购物车商品的状态
     *
     * @param userId 用户id
     * @param cartId 购物车id
     * @param type   类型
     * @return ServerResponse
     */
    ServerResponse<String> select(Integer userId, Integer cartId, Integer type);

    /**
     * 修改该用户所有购物车商品状态
     *
     * @param userId 用户id
     * @param type   类型
     * @return ServerResponse
     */
    ServerResponse<String> updateAllChecked(Integer userId, Integer type);

    /**
     * 购物车商品数量加一
     *
     * @param userId userId
     * @param cartId cartId
     * @return ServerResponse
     */
    ServerResponse addQuantity(Integer userId, Integer cartId);

    /**
     * 购物车商品数量减一
     *
     * @param userId userId
     * @param cartId cartId
     * @return ServerResponse2
     */
    ServerResponse decQuantity(Integer userId, Integer cartId);

    /**
     * 计算购物车中已经选中的商品的总价钱
     *
     * @param userId 用户id
     * @return ServerResponse
     */
    ServerResponse<String> calcuTotalPrice(Integer userId);

    /**
     * 删除购物车中的商品
     *
     * @param id        id
     * @param productId 商品id
     * @param quantity  数量
     * @return ServeResponse
     */
    ServerResponse<String> deleteCartByUserIdProductIdQuantity(Integer id, Integer productId, Integer quantity);
}
