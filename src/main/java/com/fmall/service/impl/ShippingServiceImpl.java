package com.fmall.service.impl;

import com.fmall.common.ServerResponse;
import com.fmall.dao.ShippingMapper;
import com.fmall.pojo.Shipping;
import com.fmall.service.IShippingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZSS
 * @description shipping service implements
 */
@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService {

    private final ShippingMapper shippingMapper;

    @Autowired
    public ShippingServiceImpl(ShippingMapper shippingMapper) {
        this.shippingMapper = shippingMapper;
    }

    @Override
    public ServerResponse<String> addAddress(Shipping shipping){
        int resultCount = shippingMapper.insertSelective(shipping);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("添加收获地址失败");
        }
        return ServerResponse.createBySuccessMessage("添加收获地址成功");
    }

    @Override
    public ServerResponse<PageInfo> showAddresses(Integer userId,Integer pn){
        // 默认一页最多有六条数据
        PageHelper.startPage(pn,6);
        List<Shipping> shippingList = shippingMapper.selectAllByUserId(userId);
        if(shippingList == null){
            return ServerResponse.createByErrorMessage("暂时没有收获地址");
        }
        PageInfo pageInfo = new PageInfo(shippingList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<String> deleteShipping(Integer userId,Integer shippingId){
        int resultCount = shippingMapper.deleteByUserIdShippingId(userId,shippingId);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("删除地址失败");
        }
        return ServerResponse.createBySuccessMessage("删除地址成功");
    }

    @Override
    public ServerResponse<Shipping> showShippingById(Integer userId,Integer shippingId){
        Shipping shipping = shippingMapper.selectShippingByUseridShippingId(userId,shippingId);
        if(shipping == null){
            return ServerResponse.createByErrorMessage("没有这个地址信息");
        }
        return ServerResponse.createBySuccess("查询成功",shipping);
    }

    @Override
    public ServerResponse<String> editShipping(Shipping shipping){
        int resultCount = shippingMapper.updateByPrimaryKeySelective(shipping);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("修改地址失败");
        }
        return ServerResponse.createBySuccessMessage("修改地址成功");
    }

    @Override
    public ServerResponse<Shipping> selectShippingById(Integer shippingId) {
        Shipping shipping = shippingMapper.selectByPrimaryKey(shippingId);
        if(shipping == null){
            return ServerResponse.createByErrorMessage("查询失败");
        }
        return ServerResponse.createBySuccess(shipping);
    }

}
