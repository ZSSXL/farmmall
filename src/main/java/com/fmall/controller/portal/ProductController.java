package com.fmall.controller.portal;

import com.fmall.common.Const;
import com.fmall.common.ResponseCode;
import com.fmall.common.ServerResponse;
import com.fmall.pojo.Livestock;
import com.fmall.pojo.Product;
import com.fmall.pojo.User;
import com.fmall.service.IFileService;
import com.fmall.service.IProductService;
import com.fmall.service.IUserService;
import com.fmall.util.CookieUtil;
import com.fmall.util.JsonUtil;
import com.fmall.util.PropertiesUtil;
import com.fmall.util.RedisPoolUtil;
import com.github.pagehelper.PageInfo;
import com.google.api.Http;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ZSS
 * @description product controller
 */
@Controller
@RequestMapping("/product/")
public class ProductController {

    private final IProductService iProductService;
    private final IUserService iUserService;
    private final IFileService iFileService;

    @Autowired
    public ProductController(IProductService iProductService, IUserService iUserService, IFileService iFileService) {
        this.iProductService = iProductService;
        this.iUserService = iUserService;
        this.iFileService = iFileService;
    }

    /**
     * 上传产品
     */
    @RequestMapping(value = "release_product.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> releaseProduct(Product product, @RequestParam(value = "file", required = false) MultipartFile file
            , HttpServletRequest request) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        // 判断是否为卖家
        if (iUserService.checkAdminRole(user.getUsername()).isSuccess()) {
            // 填充业务
            // 先填写其他信息
            product.setUserId(user.getId());
            // 上传主图和副图
            // 上传主图
            String path = request.getSession().getServletContext().getRealPath("upload");
            System.out.println(path);
            if (file != null) {
                String targetFileName = iFileService.upload(file, path);
                String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
                // 提交给product
                product.setMainImage(url);
            } else {
                product.setMainImage("null");
            }
            // 上传副图
            CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            // 判断是否有文件上传
            if (commonsMultipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                List<MultipartFile> multipartFileList = multipartHttpServletRequest.getFiles("subImageFiles");
                StringBuilder subImgUrls = new StringBuilder();
                for (MultipartFile img : multipartFileList) {
                    String targetFileNames = iFileService.upload(img, path);
                    String urls = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileNames;
                    subImgUrls.append(urls).append(",");
                }
                product.setSubImages(subImgUrls.toString());
            }
            return iProductService.uploadProduct(product);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    /**
     * 展示产品
     *
     * @param type       类型
     * @param categoryId 分类id
     * @param pn         当前页
     * @return ServerResponse
     */
    @RequestMapping(value = "show_product.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> showProduct(@RequestParam(value = "type", defaultValue = "1") Integer type
            , @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId
            , @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        return iProductService.selectProductByChoice(type, categoryId, pn);
    }

    /**
     * 获取产品信息，在session中储存，在另一个页面展示，只需要返回成功或者失败
     *
     * @param session   session
     * @param productId 产品id
     * @return ServerResponse
     */
    @RequestMapping(value = "show_product_by_id.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse showProductById(HttpSession session, Integer productId) {
        Product product = iProductService.showProductById(productId);
        if (product == null) {
            return ServerResponse.createByError();
        }
        // 将查询出来的产品信息存到数据库中
        session.setAttribute(Const.CURRENT_PRODUCT, product);
        return ServerResponse.createBySuccessMessage("查询成功");
    }

    /**
     * 添加收藏
     *
     * @param request   请求
     * @param productId 产品id
     * @return ServerResponse
     */
    @RequestMapping(value = "add_collection.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> addCollection(HttpServletRequest request, Integer productId) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请先登录");
        }
        // 收藏商品
        return iProductService.addCollection(user.getId(), productId);
    }

    /**
     * 删除收藏
     *
     * @param request   请求
     * @param productId 产品id
     * @return ServerResponse
     */
    @RequestMapping(value = "delete_collection.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> deleteCollection(HttpServletRequest request, Integer productId) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        // 删除该商品
        return iProductService.deleteCollection(user.getId(), productId);
    }

    /**
     * 展示收藏
     *
     * @param request 请求
     * @param pn      每页大小
     * @return ServerResponse
     */
    @RequestMapping(value = "show_collection.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> showCollection(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") int pn) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }

        // 展示搜藏的商品
        return iProductService.showCollection(user.getId(), pn);
    }

    /**
     * 展示牲畜信息
     *
     * @param livestock 牲畜
     * @return ServerResponse
     */
    @RequestMapping(value = "show_livestock.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Livestock> showLivestock(Integer livestock) {
        return iProductService.selectLivestock(livestock);
    }

    /**
     * 卖家展示产品
     *
     * @param request 请求
     * @return ServerResponse
     */
    @RequestMapping(value = "show_product_by_seller.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<List<Product>> showProductBySellerId(HttpServletRequest request) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请登录");
        }
        if (user.getRole() != 1) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "权限不足");
        }
        // 展示商品
        return iProductService.showProductBySellerId(user.getId());
    }

    /**
     * 店家修改商品状态
     *
     * @param request   请求
     * @param productId 产品id
     * @param status    状态
     * @return ServerResponse
     */
    @RequestMapping(value = "update_product_status.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> updateProductStatus(HttpServletRequest request, Integer status, Integer productId) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByError();
        }
        // 判断是否未卖家身份
        if (user.getRole() != 1) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "权限不足");
        }
        // 查询商品
        return iProductService.updateProductStatusByUserId(user.getId(), status, productId);
    }

    /**
     * 获取token
     *
     * @param request 请求
     * @return String
     */
    private String getLoginToken(HttpServletRequest request) {
        return CookieUtil.readLoginToken(request);
    }

}
