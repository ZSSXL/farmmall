package com.fmall.dao;

import com.fmall.pojo.Cart;
import com.fmall.vo.CartProductVo;
import com.fmall.vo.ProductIdAndQuantiry;
import com.fmall.vo.TotalPriceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    List<CartProductVo> selectCartByUserId(Integer userId);

    int deleteCartByUserIdCartId(@Param("userId") Integer userId,@Param("cartId") Integer cartId);

    int selectChecked(@Param("userId")Integer userId,@Param("cartId")Integer cartId,@Param("type")Integer type);

    int updateAllCheckedByUserId(@Param("userId")Integer userId,@Param("type")Integer t);

    int addQuantity(@Param("userId")Integer userId,@Param("cartId") Integer cartId);

    int decQuantity(@Param("userId")Integer userId,@Param("cartId") Integer cartId);

    List<TotalPriceVo> selectPriceAndQUantityByUserId(Integer userId);

    List<ProductIdAndQuantiry> selectProductIdQuantiyByUserId(Integer userId);

    List<Integer> selectCheckedCartIdByUserId(Integer userId);

    int deleteCartByUserIdProductIdQuantity(@Param("userId")Integer userId,@Param("productId") Integer productId,@Param("quantity") Integer quantity);
}