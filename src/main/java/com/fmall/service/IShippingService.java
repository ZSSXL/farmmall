package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.Shipping;
import com.github.pagehelper.PageInfo;

/**
 * @author ZSS
 * @description shipping service
 */
public interface IShippingService {

    /**
     * 添加收获地址
     *
     * @param shipping 收货地址实体
     * @return ServerResponse
     */
    ServerResponse<String> addAddress(Shipping shipping);

    /**
     * 显示收货地址
     *
     * @param userId 用户id
     * @param pn     当前页
     * @return ServerResponse
     */
    ServerResponse<PageInfo> showAddresses(Integer userId, Integer pn);

    /**
     * 根据用户id删除个人地址
     *
     * @param userId     用户id
     * @param shippingId 收货地址详情
     * @return ServerResponse
     */
    ServerResponse<String> deleteShipping(Integer userId, Integer shippingId);

    /**
     * 根据用户id和收货地址id查询地址详细信息
     *
     * @param userId     用户id
     * @param shippingId 收获地址id
     * @return ServerResponse
     */
    ServerResponse<Shipping> showShippingById(Integer userId, Integer shippingId);

    /**
     * 修改个人地址
     *
     * @param shipping 收获地址实体
     * @return ServerResponse
     */
    ServerResponse<String> editShipping(Shipping shipping);

    /**
     * 通过id查询收货地址的详情
     *
     * @param shippingId 收货地址id
     * @return ServerResponse
     */
    ServerResponse<Shipping> selectShippingById(Integer shippingId);
}
