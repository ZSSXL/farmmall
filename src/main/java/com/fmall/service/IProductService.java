package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.Livestock;
import com.fmall.pojo.Product;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author ZSS
 * @description product service
 */
public interface IProductService {

    /**
     * 上传商品
     *
     * @param product 产品实体
     * @return ServerResponse
     */
    ServerResponse<String> uploadProduct(Product product);

    /**
     * 分类展示商品
     *
     * @param type       类型
     * @param categoryId 分类
     * @param pn         当前页
     * @return ServerResponse
     */
    ServerResponse<PageInfo> selectProductByChoice(Integer type, Integer categoryId, Integer pn);

    /**
     * 显示商品详情
     *
     * @param productId 产品id
     * @return Product
     */
    Product showProductById(Integer productId);

    /**
     * 收藏商品
     *
     * @param userId    userId
     * @param productId productId
     * @return ServerResponse
     */
    ServerResponse<String> addCollection(Integer userId, Integer productId);

    /**
     * 根据收藏的id删除收藏
     *
     * @param id        id
     * @param productId 产品id
     * @return ServerResponse
     */
    ServerResponse<String> deleteCollection(Integer id, Integer productId);

    /**
     * 展示购物车中的商品
     *
     * @param id id
     * @param pn 当前页
     * @return ServerResponse
     */
    ServerResponse<PageInfo> showCollection(Integer id, int pn);

    /**
     * 显示最新的牲畜信息
     *
     * @param livestock livestock
     * @return ServerResponse
     */
    ServerResponse<Livestock> selectLivestock(Integer livestock);

    /**
     * 查询所有
     *
     * @param productId 产品id
     * @return Product
     */
    Product queryByProductId(int productId);

    /**
     * 展示卖家发布的产品
     *
     * @param id id
     * @return ServerResponse
     */
    ServerResponse<List<Product>> showProductBySellerId(Integer id);

    /**
     * 店家修改产品的状态
     *
     * @param id        id
     * @param status    状态
     * @param productId 产品id
     * @return ServerResponse
     */
    ServerResponse<String> updateProductStatusByUserId(Integer id, Integer status, Integer productId);
}
