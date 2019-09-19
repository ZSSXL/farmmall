package com.fmall.dao;

import com.fmall.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectProductBySelective(@Param("categoryId") Integer categoryId);

    int selectByProductId(Integer productId);

    int updateStock(@Param("productId") Integer productId, @Param("stock") Integer stock);

    Product selectMsg(Integer productId);

    List<Product> selectProductByUserId(Integer sellerId);

    int updateStatusBySellerId(@Param("userId") Integer userId, @Param("status") Integer status,@Param("productId") Integer productId);

    Integer selectStockByProductId(Integer id);

}