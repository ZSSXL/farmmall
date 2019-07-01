package com.fmall.service.impl;

import com.fmall.common.Const;
import com.fmall.common.ServerResponse;
import com.fmall.dao.CollectionMapper;
import com.fmall.dao.LivestockMapper;
import com.fmall.dao.ProductMapper;
import com.fmall.pojo.MyCollection;
import com.fmall.pojo.Livestock;
import com.fmall.pojo.Product;
import com.fmall.service.IProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iProductService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private LivestockMapper livestockMapper;

    public ServerResponse<String> uploadProduct(Product product){
        int resultCount = productMapper.insert(product);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("商品上传失败");
        }
        return ServerResponse.createBySuccessMessage("商品上传成功");
    }

    /**
     * 按不同参数展示商品展示商品
     * @param pn
     * @return
     */
    public ServerResponse<PageInfo> selectProductByChoice(Integer type,Integer categoryId,Integer pn){

        PageHelper.startPage(pn,12);
        List<Product> productList;
        if(type == Const.Type.DEFAULT){
            // 默认查询
            productList = productMapper.selectProductBySelective(categoryId);
        }else if(type == Const.Type.CATEGORY){
            // 按肉分类查询
            productList = productMapper.selectProductBySelective(categoryId);
        }else{
            return ServerResponse.createBySuccessMessage("参数错误");
        }

        PageInfo pageInfo = new PageInfo(productList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    public Product showProductById(Integer productId){
        Product product = productMapper.selectByPrimaryKey(productId);
        return product;
    }

    public ServerResponse<String> addCollection(Integer userId,Integer productId){
        MyCollection myCollection = new MyCollection();
        myCollection.setUserId(userId);
        myCollection.setProductId(productId);
        // 1、判断是否已经收藏了这个商品
        int result = collectionMapper.selectExist(userId,productId);
        if(result != 0){
            return ServerResponse.createByErrorMessage("您已经收藏该商品");
        }

        int resultCount = collectionMapper.insert(myCollection);
        if(resultCount > 0){
            return ServerResponse.createBySuccessMessage("收藏成功");
        }
        return ServerResponse.createByErrorMessage("收藏失败");
    }

    public ServerResponse<String> deleteCollection(Integer userId, Integer productId){
        int resultCount = collectionMapper.deleteByUserIdCollectionId(userId,productId);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    public ServerResponse<PageInfo> showCollection(Integer userId, int pn){
        PageHelper.startPage(pn,50);
        List<Product> productList = collectionMapper.selectProductByUserId(userId);
        if(productList == null){
            return ServerResponse.createByErrorMessage("你没有收藏任何商品");
        }
        PageInfo pageInfo = new PageInfo(productList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse<Livestock> selectLivestock(Integer label){
        Livestock livestock = livestockMapper.selectByLabel(label);
        if(livestock == null){
            return ServerResponse.createByErrorMessage("查询牲畜信息失败");
        }
        return ServerResponse.createBySuccess(livestock);
    }

    public Product queryByProductId(int productId) {
        return productMapper.selectByPrimaryKey(productId);
    }

    @Override
    public ServerResponse<List<Product>> showProductBySellerId(Integer sellerId) {
        List<Product> productList = productMapper.selectProductByUserId(sellerId);
        if(productList == null){
            return ServerResponse.createByErrorMessage("查询失败");
        }
        return ServerResponse.createBySuccess(productList);
    }

    public ServerResponse<String> updateProductStatusByUserId(Integer userId, Integer status,Integer productId) {
        int resultCount = productMapper.updateStatusBySellerId(userId,status,productId);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("更改商品状态失败");
        }
        return ServerResponse.createBySuccessMessage("更新商品状态成功");
    }

}

