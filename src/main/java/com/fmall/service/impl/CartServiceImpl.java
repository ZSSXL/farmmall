package com.fmall.service.impl;

import com.fmall.common.Const;
import com.fmall.common.ServerResponse;
import com.fmall.dao.CartMapper;
import com.fmall.pojo.Cart;
import com.fmall.service.ICartService;
import com.fmall.util.BigDecimalUtil;
import com.fmall.vo.CartProductVo;
import com.fmall.vo.TotalPriceVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZSS
 * @description cart service impl
 */
@Service("iCartService")
public class CartServiceImpl implements ICartService {

    private final CartMapper cartMapper;

    @Autowired
    public CartServiceImpl(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    @Override
    public ServerResponse<String> addCart(Cart cart) {
        int resultCount = cartMapper.insert(cart);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("添加购物车成功");
        }
        return ServerResponse.createByErrorMessage("添加购物车失败");
    }

    @Override
    public ServerResponse<PageInfo> showCarts(Integer userId, Integer pn) {
        PageHelper.startPage(pn, 20);
        List<CartProductVo> cartList = cartMapper.selectCartByUserId(userId);
        if (cartList == null) {
            return ServerResponse.createByErrorMessage("你的购物车空空如也");
        }
        PageInfo pageInfo = new PageInfo(cartList);
        return ServerResponse.createBySuccess("查询成功", pageInfo);
    }

    @Override
    public ServerResponse<String> deleteCartByUserIdCartId(Integer userId, Integer cartId) {
        int resultCount = cartMapper.deleteCartByUserIdCartId(userId, cartId);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("删除该商品成功");
        }
        return ServerResponse.createByErrorMessage("删除该商品失败");
    }

    @Override
    public ServerResponse<String> editCart(Cart cart) {
        int resultCount = cartMapper.updateByPrimaryKeySelective(cart);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }

    @Override
    public ServerResponse<String> select(Integer userId, Integer cartId, Integer type) {
        if (type == 0) {
            // 设置成未勾选
            int resultCount = cartMapper.selectChecked(userId, cartId, Const.Check.UNCHECKED);
            if (resultCount > 0) {
                return ServerResponse.createBySuccessMessage("状态已选:" + Const.Check.UNCHECKED);
            }
        } else if (type == 1) {
            // 设置成已勾选
            int resultCount = cartMapper.selectChecked(userId, cartId, Const.Check.CHECKED);
            if (resultCount > 0) {
                return ServerResponse.createBySuccessMessage("状态已选:" + Const.Check.CHECKED);
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误");

        }
        return ServerResponse.createByErrorMessage("修改失败");
    }

    @Override
    public ServerResponse<String> updateAllChecked(Integer userId, Integer type) {
        int resultCount = cartMapper.updateAllCheckedByUserId(userId, type);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("恭喜你，找到了一个BUG");
        }
        return ServerResponse.createBySuccessMessage("修改状态成功");
    }

    @Override
    public ServerResponse addQuantity(Integer userId, Integer cartId) {
        int resultCount = cartMapper.addQuantity(userId, cartId);
        if (resultCount == 0) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse decQuantity(Integer userId, Integer cartId) {
        int resultCount = cartMapper.decQuantity(userId, cartId);
        if (resultCount == 0) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse<String> calcuTotalPrice(Integer userId) {
        List<TotalPriceVo> totalPriceVoList = new ArrayList<TotalPriceVo>();
        totalPriceVoList = cartMapper.selectPriceAndQUantityByUserId(userId);
        for (TotalPriceVo totalPriceVo : totalPriceVoList) {
            totalPriceVo.setTotalPrice(BigDecimalUtil.mul(totalPriceVo.getPrice().doubleValue(), totalPriceVo.getQuantity()));
        }
        BigDecimal totalPrice = new BigDecimal("0.00");
        for (TotalPriceVo totalPriceVo1 : totalPriceVoList) {
            totalPrice = BigDecimalUtil.add(totalPrice.doubleValue(), totalPriceVo1.getTotalPrice().doubleValue());
        }
        return ServerResponse.createBySuccessMessage(String.valueOf(totalPrice));
    }

    @Override
    public ServerResponse<String> deleteCartByUserIdProductIdQuantity(Integer userId, Integer productId, Integer quantity) {
        int resultCount = cartMapper.deleteCartByUserIdProductIdQuantity(userId, productId, quantity);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccess();
    }


}
