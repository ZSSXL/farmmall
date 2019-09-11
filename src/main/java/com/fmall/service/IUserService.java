package com.fmall.service;

import com.fmall.common.ServerResponse;
import com.fmall.pojo.User;

import java.util.List;

/**
 * @author ZSS
 * @description user Service
 */
public interface IUserService {

    /**
     * 注册用户
     *
     * @param user 用户实体
     * @return ServerResponse
     */
    ServerResponse<String> register(User user);

    /**
     * 登录用户
     *
     * @param username 用户名
     * @param password 密码
     * @return ServerResponse
     */
    ServerResponse<User> login(String username, String password);

    /**
     * 通过用户名找到密保问题
     *
     * @param username 用户名
     * @return ServerResponse
     */
    ServerResponse<String> forgetGetQuestion(String username);

    /**
     * 校验密保问题是否正确
     *
     * @param username 用户名
     * @param question 密保问题
     * @param answer   答案
     * @return ServerResponse
     */
    ServerResponse<String> forgetCheckAnswer(String username, String question, String answer);

    /**
     * 重新找回密码
     *
     * @param username    用户名
     * @param passwordNew 新密码
     * @return ServerResponse
     */
    ServerResponse<String> forgetResetQuestion(String username, String passwordNew);

    /**
     * 重置密码
     *
     * @param username    用户名
     * @param passwordOld 旧密码
     * @param passwordNew 新密码
     * @return ServerResponse
     */
    ServerResponse<String> resetPassword(String username, String passwordOld, String passwordNew);

    /**
     * 查看是否为管理员
     *
     * @param username 用户名
     * @return ServerResponse
     */
    ServerResponse checkAdminRole(String username);

    /**
     * 申请成为卖家
     *
     * @param user 用户实体
     * @return ServerResponse
     */
    ServerResponse<String> applyShop(User user);

    /**
     * 查询店家
     *
     * @param userId 用户id
     * @return ServerResponse
     */
    ServerResponse<User> getSellerInfo(Integer userId);

    /**
     * 获取登录用户信息
     *
     * @param userId 用户id
     * @return ServerResponse<User>
     */
    ServerResponse<User> getUserInfoByUserId(Integer userId);

    /**
     * 登录
     *
     * @param user 用户实体
     * @return User
     */
    User doAjaxLogin(User user);

    /**
     * 查找所有的用户
     *
     * @param page 当前页
     * @param size 分页大小
     * @return List<User>
     */
    List<User> queryAllBuyer(int page, int size);

    /**
     * 查找所有的卖家
     *
     * @param page 当前页
     * @param size 分页大小
     * @return List<User>
     */
    List<User> queryAllSeller(int page, int size);

    /**
     * 通过名称查找卖家
     *
     * @param page     当前页
     * @param size     分页大小
     * @param username 用户名
     * @return List<User>
     */
    List<User> querySellerByName(int page, int size, String username);

    /**
     * 通过名称查找买家
     *
     * @param page     当前页
     * @param size     每页大小
     * @param username 用户名
     * @return List<User>
     */
    List<User> queryBuyerByName(int page, int size, String username);

    /**
     * 查看所有申请
     *
     * @param page 当前页
     * @param size 分页大小
     * @return List<User>
     */
    List<User> queryAllApply(int page, int size);

    /**
     * 通过名称查看申请
     *
     * @param page     当前页
     * @param size     分页大小
     * @param username 用户名
     * @return List<User>
     */
    List<User> queryApplyByName(int page, int size, String username);

    /**
     * 申请成为店家
     *
     * @param id 用户id
     */
    void applyById(String id);

    /**
     * 删除买家
     *
     * @param id 用户id
     */
    void deleteBuyerById(String id);

    /**
     * 通过用户id查询用户
     *
     * @param id 用户id
     * @return User
     */
    User queryByUserId(int id);

    /**
     * 通过用户id删除卖家
     *
     * @param id 用户id
     */
    void deleteSellerById(String id);

    /**
     * 重新申请
     *
     * @param id 用户id
     */
    void reApplyById(String id);
}
