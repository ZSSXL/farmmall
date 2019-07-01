package com.fmall.dao;

import com.fmall.pojo.MyCollection;
import com.fmall.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyCollection record);

    int insertSelective(MyCollection record);

    MyCollection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyCollection record);

    int updateByPrimaryKey(MyCollection record);

    int deleteByUserIdCollectionId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    List<Product> selectProductByUserId(Integer userId);

    int selectExist(@Param("userId") Integer userId,@Param("productId") Integer productId);
}