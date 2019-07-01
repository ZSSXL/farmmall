package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.Cart;
import com.github.pagehelper.PageInfo;

public interface ICartService {

    /**
     * 添加购物车
     * @param cart
     * @return
     */
    ServerResponse<String> addCart(Cart cart);

    /**
     * 分页查询购物车
     * @param userId
     * @param pn
     * @return
     */
    ServerResponse<PageInfo> showCarts(Integer userId, Integer pn);

    /**
     * 通过userid和cartid删除指定的购物车商品
     * @param userId
     * @param cartId
     * @return
     */
    ServerResponse<String> deleteCartByUserIdCartId(Integer userId,Integer cartId);

    /**
     * 修改购物车中的商品
     * @param cart
     * @return
     */
    ServerResponse<String> editCart(Cart cart);

    /**
     * 修改单个购物车商品的状态
     * @param userId
     * @param cartId
     * @param type
     * @return
     */
    ServerResponse<String> select(Integer userId,Integer cartId,Integer type);

    /**
     * 修改该用户所有购物车商品状态
     * @param userId
     * @param type
     * @return
     */
    ServerResponse<String> updateAllChecked(Integer userId,Integer type);

    /**
     * 购物车商品数量加一
     * @param userId
     * @param cartId
     * @return
     */
    ServerResponse addQuantity(Integer userId,Integer cartId);

    /**
     * 购物车商品数量减一
     * @param userId
     * @param cartId
     * @return
     */
    ServerResponse decQuantity(Integer userId,Integer cartId);

    /**
     * 计算购物车中已经选中的商品的总价钱
     * @param userId
     * @return
     */
    ServerResponse<String> calcuTotalPrice(Integer userId);

    /**
     * 删除购物车中的商品
     * @param id
     * @param productId
     * @param quantity
     * @return
     */
    ServerResponse<String> deleteCartByUserIdProductIdQuantity(Integer id, Integer productId, Integer quantity);
}
