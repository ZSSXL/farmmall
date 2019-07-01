package com.fmall.controller.portal;

import com.fmall.common.Const;
import com.fmall.common.ResponseCode;
import com.fmall.common.ServerResponse;
import com.fmall.pojo.Cart;
import com.fmall.pojo.User;
import com.fmall.service.ICartService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    private ICartService iCartService;

    /**
     * 展示购物车
     * @param session
     * @param pn
     * @return
     */
    @RequestMapping(value = "show_cart.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> showCart(HttpSession session,@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("请先登录");
        }
        ServerResponse<PageInfo> serverResponse = iCartService.showCarts(user.getId(), pn);
        return serverResponse;
    }

    /**
     * 添加购物车
     * @param session
     * @param productId
     * @return
     */
    @RequestMapping(value = "add_cart.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> addCart(HttpSession session,Integer quantity,Integer productId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请先登录");
        }
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        // 默认已勾选
        cart.setChecked(Const.Check.CHECKED);
        ServerResponse<String> serverResponse = iCartService.addCart(cart);
        return serverResponse;
    }

    /**
     * 添加userId双向验证，防止横向越权
     * @param session
     * @param cartId
     * @return
     */
    @RequestMapping(value = "delete_cart_by_cartId.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> deleteCartByCartId(HttpSession session,Integer cartId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ServerResponse<String> serverResponse = iCartService.deleteCartByUserIdCartId(user.getId(), cartId);
        return serverResponse;
    }

    /**
     * 修改购物车商品信息
     * @param quantity
     * @param checked
     * @return
     */
    @RequestMapping(value = "edit_cart.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> editCart(HttpSession session,Integer cartId, Integer quantity,Integer checked){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        cart.setId(cartId);
        cart.setQuantity(quantity);
        cart.setChecked(checked);
        ServerResponse<String> serverResponse = iCartService.editCart(cart);
        return serverResponse;
    }

    /**
     * 选择修改订单状态
     * @param session
     * @param cartId
     * @return
     */
    @RequestMapping("select_checked.do")
    @ResponseBody
    public ServerResponse<String> selectChecked(HttpSession session,Integer cartId,Integer type){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请登录");
        }
        ServerResponse<String> serverResponse = iCartService.select(user.getId(), cartId, type);
        return serverResponse;
    }

    /**
     * 修改所有购物车订单状态
     * @param session
     * @return
     */
    @RequestMapping(value = "select_check_all.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> selectCheckedALl(HttpSession session,Integer checked){
        System.out.println("checked:"+checked);
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ServerResponse<String> stringServerResponse = iCartService.updateAllChecked(user.getId(), checked);
        return stringServerResponse;
    }

    /**
     * 购物车商品加一
     * @param session
     * @param cartId
     * @return
     */
    @RequestMapping(value = "add_quantity.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addQuantity(HttpSession session,Integer cartId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ServerResponse serverResponse = iCartService.addQuantity(user.getId(), cartId);
        return serverResponse;
    }

    /**
     * 计算已选中的商品的总价钱
     * @return
     */
    @RequestMapping(value = "calcu_total_price.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> calcuTotalPrice(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请登录");
        }
        // 计算总价钱
        ServerResponse<String> stringServerResponse = iCartService.calcuTotalPrice(user.getId());
        return stringServerResponse;
    }

    /**
     * 购物车商品减一
     * @param session
     * @param cartId
     * @return
     */
    @RequestMapping(value = "dec_quantity.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse decQuantity(HttpSession session,Integer cartId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ServerResponse serverResponse = iCartService.decQuantity(user.getId(), cartId);
        return serverResponse;
    }

    // todo 完成个购物车商品修改状态
    // 全选
   /* @RequestMapping("select_all.do")
    @ResponseBody
    public ServerResponse<CartVo> selectAll(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectOrUnSelect(user.getId(),Const.Cart.CHECKED,null);
    }

    // 全反选
    @RequestMapping("un_select_all.do")
    @ResponseBody
    public ServerResponse<CartVo> unSelectAll(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectOrUnSelect(user.getId(),Const.Cart.UN_CHECKED,null);
    }*/

}
