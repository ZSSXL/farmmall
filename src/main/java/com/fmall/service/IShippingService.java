package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.Shipping;
import com.github.pagehelper.PageInfo;

public interface IShippingService {

    /**
     * 添加收获地址
     * @param shipping
     * @return
     */
    ServerResponse<String> addAddress(Shipping shipping);

    /**
     * 显示收货地址
     * @param userId
     * @param pn
     * @return
     */
    ServerResponse<PageInfo> showAddresses(Integer userId, Integer pn);

    /**
     * 根据用户id删除个人地址
     * @param userId
     * @param shippingId
     * @return
     */
    ServerResponse<String> deleteShipping(Integer userId,Integer shippingId);

    /**
     * 根据用户id和收货地址id查询地址详细信息
     * @param userId
     * @param shippingId
     * @return
     */
    ServerResponse<Shipping> showShippingById(Integer userId,Integer shippingId);

    /**
     * 修改个人地址
     * @param shipping
     * @return
     */
    ServerResponse<String> editShipping(Shipping shipping);

    /**
     * 通过id查询收货地址的详情
     * @param shippingId
     * @return
     */
    ServerResponse<Shipping> selectShippingById(Integer shippingId);
}
