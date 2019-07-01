package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.Livestock;
import com.fmall.pojo.Product;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IProductService {

    /**
     * 上传商品
     * @param product
     * @return
     */
    ServerResponse<String> uploadProduct(Product product);

    /**
     * 分类展示商品
     * @param type
     * @param categoryId
     * @param pn
     * @return
     */
    ServerResponse<PageInfo> selectProductByChoice(Integer type, Integer categoryId, Integer pn);

    /**
     * 显示商品详情
     * @param productId
     * @return
     */
    Product showProductById(Integer productId);

    /**
     * 收藏商品
     * @param userId
     * @param productId
     * @return
     */
    ServerResponse<String> addCollection(Integer userId,Integer productId);

    /**
     * 根据收藏的id删除收藏
     * @param id
     * @param productId
     * @return
     */
    ServerResponse<String> deleteCollection(Integer id, Integer productId);

    /**
     * 展示购物车中的商品
     * @param id
     * @param pn
     * @return
     */
    ServerResponse<PageInfo> showCollection(Integer id, int pn);

    /**
     * 显示最新的牲畜信息
     * @param livestock
     * @return
     */
    ServerResponse<Livestock> selectLivestock(Integer livestock);

    Product queryByProductId(int productId);

    /**
     * 展示卖家发布的产品
     * @param id
     * @return
     */
    ServerResponse<List<Product>> showProductBySellerId(Integer id);

    /**
     * 店家修改产品的状态
     * @param id
     * @param status
     * @return
     */
    ServerResponse<String> updateProductStatusByUserId(Integer id, Integer status,Integer productId);
}
