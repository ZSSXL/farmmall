package com.fmall.controller.backend;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.Order;
import com.fmall.pojo.Product;
import com.fmall.pojo.User;
import com.fmall.service.IOrderService;
import com.fmall.service.IProductService;
import com.fmall.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class AdminController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IProductService productService;

    @RequestMapping("/admin.do")
    public String login() {

        return "manage/login";
    }

    @RequestMapping(value = "/doAJAXLogin.do")
    @ResponseBody
    public ServerResponse<User> doAjaxLogin(User user, HttpSession session) {
        User buyer = userService.doAjaxLogin(user);

        if (buyer == null) {
            return ServerResponse.createByError();
        } else {
            session.setAttribute("loginUser", buyer);
            return ServerResponse.createBySuccess(buyer);
        }

    }

    @RequestMapping("/logout.do")
    public String logout(HttpSession session) {
        //session.removeAttribute("loginUser");
        session.invalidate();
        return "manage/login";
    }


    @RequestMapping("/queryAllBuyer.do")
    @ResponseBody
    public ModelAndView queryAllBuyer(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(value = "username", required = false) String username) {
        ModelAndView mv = new ModelAndView();
        if (username == null) {
            List<User> buyerList = userService.queryAllBuyer(page, size);
            PageInfo pageInfo = new PageInfo(buyerList);
            mv.addObject("pageInfo", pageInfo);
            mv.setViewName("manage/buyer_list");
            return mv;
        } else {
            List<User> buyerList = userService.queryBuyerByName(page, size, username);
            PageInfo pageInfo = new PageInfo(buyerList);
            mv.addObject("pageInfo", pageInfo);
            mv.setViewName("manage/buyer_list");
        }
        return mv;
    }

    @RequestMapping(value = "/queryAllSeller.do", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView queryAllSeller(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(value = "username", required = false) String username) {
        ModelAndView mv = new ModelAndView();
        if (username == null) {
            List<User> sellerList = userService.queryAllSeller(page, size);
            PageInfo pageInfo = new PageInfo(sellerList);
            mv.addObject("pageInfo", pageInfo);
            mv.setViewName("manage/seller_list");
            return mv;
        } else {
            List<User> sellerList = userService.querySellerByName(page, size, username);
            PageInfo pageInfo = new PageInfo(sellerList);
            mv.addObject("pageInfo", pageInfo);
            mv.setViewName("manage/seller_list");
        }
        return mv;
    }

    @RequestMapping("/queryAllOrders.do")
    @ResponseBody
    public ModelAndView queryAllOrders(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size,@RequestParam(value = "username",required = false)Long orderNo ) {
        ModelAndView mv = new ModelAndView();
        if (orderNo==null){
            List<Order> ordersList = orderService.queryAllOrders(page, size);
            PageInfo pageInfo = new PageInfo(ordersList);
            mv.addObject("pageInfo", pageInfo);
            mv.setViewName("manage/order_list");
        }else {
            List<Order> ordersList = orderService.queryOrdersByOrderNo(page, size,orderNo);
            PageInfo pageInfo = new PageInfo(ordersList);
            mv.addObject("pageInfo", pageInfo);
            mv.setViewName("manage/order_list");
        }

        return mv;
    }

    @RequestMapping("/queryAllApply.do")
    @ResponseBody
    public ModelAndView queryAllApply(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(value = "username", required = false) String username) {
        ModelAndView mv = new ModelAndView();
        if (username == null) {
            List<User> applyList = userService.queryAllApply(page, size);
            PageInfo pageInfo = new PageInfo(applyList);
            mv.addObject("pageInfo", pageInfo);
            mv.setViewName("manage/apply_list");
            return mv;
        } else {
            List<User> applyList = userService.queryApplyByName(page, size, username);
            PageInfo pageInfo = new PageInfo(applyList);
            mv.addObject("pageInfo", pageInfo);
            mv.setViewName("manage/buyer_list");
        }
        return mv;
    }

    @RequestMapping("/applyById.do")
    public void applyById(String id) {
        userService.applyById(id);
    }

    @RequestMapping("/ReApplyById.do")
    public void ReApplyById(String id){
        userService.ReApplyById(id);
    }

    @RequestMapping("/deleteBuyerById.do")
    public void deleteBuyerById(String id) {
        userService.deleteBuyerById(id);
    }

    @RequestMapping("/deleteSellerById.do")
    public void deleteSellerById(String id) {
        userService.deleteSellerById(id);
    }

    @RequestMapping("/deleteOrderById.do")
    public void deleteOrderById(String id){
        orderService.deleteOrderById(id);
    }

/*
* 订单详情
*
* */
    @RequestMapping("/queryByUserId.do")
    public ModelAndView queryByUserId(@RequestParam(value = "id")int id,@RequestParam(value = "orderNo")Long orderNo){
        ModelAndView mv = new ModelAndView();
        User buyer = userService.queryByUserId(id);
       int productId = orderService.queryByOrderNo(orderNo);
       Product product = productService.queryByProductId(productId);
        int userId = product.getUserId();
        User seller = userService.queryByUserId(userId);
        mv.addObject("buyer",buyer);
        mv.addObject("seller",seller);
        mv.addObject("product",product);
        mv.setViewName("manage/order_info");
        return mv;
    }
}
