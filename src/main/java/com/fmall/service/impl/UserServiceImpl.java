package com.fmall.service.impl;


import com.fmall.common.Const;
import com.fmall.common.ServerResponse;
import com.fmall.dao.UserMapper;
import com.fmall.pojo.User;
import com.fmall.service.IUserService;
import com.fmall.util.MD5Util;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iUserservice")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public ServerResponse<User> login(String username,String password){
        int resultCount = userMapper.checkUsername(username);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        // 密码登录MD5
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username, md5Password);
        if(user == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功",user);
    }

    public ServerResponse<String> register(User user){
        // 验证用户名和邮箱
        ServerResponse<String> validResponse = this.checkVilid(user.getUsername(), Const.USERNAME);
        if(!validResponse.isSuccess()){
            return validResponse;
        }

        validResponse = this.checkVilid(user.getEmail(), Const.EMAIL);
        if(!validResponse.isSuccess()){
            return validResponse;
        }

        // todo MD5加密密码，注册用户
        user.setRole(Const.Role.ROLE_BUYER); // 新注册的用户默认未普通用户
        // MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = userMapper.insertSelective(user);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    public ServerResponse<String> forgetGetQuestion(String username){
        String question = userMapper.getQuestion(username);
        if(question == null){
            return ServerResponse.createByErrorMessage("没有密保问题");
        }
        return ServerResponse.createBySuccessMessage(question);
    }

    public ServerResponse<String> forgetCheckAnswer(String username,String question,String answer){
        int resultCount = userMapper.checkAnswer(username, question, answer);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("密保问题回答错误！！！Some was wrong!!!");
        }
        return ServerResponse.createBySuccessMessage("密保问题匹配正确");
    }

    public ServerResponse<String> forgetResetQuestion(String username,String passwordNew){
        int resultCout = userMapper.resetPassword(username, MD5Util.MD5EncodeUtf8(passwordNew));
        if(resultCout == 0){
            return ServerResponse.createByErrorMessage("密码找回失败");
        }
        return ServerResponse.createBySuccessMessage("密码找回成功！");
    }

    public ServerResponse<String> resetPassword(String username,String passwordOld,String passwordNew){
        // 匹配旧密码是否正确 防止横向越权
        int resultCount = userMapper.checkPasswordOld(username, MD5Util.MD5EncodeUtf8(passwordOld));
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("旧密码错误,请重新输入！");
        }
        int resultCout = userMapper.resetPassword(username, MD5Util.MD5EncodeUtf8(passwordNew));
        if(resultCout == 0){
            return ServerResponse.createByErrorMessage("密码重置失败");
        }
        return ServerResponse.createBySuccessMessage("密码重置成功");
    }

    public ServerResponse<String> checkAdminRole(String username){
        int resultCount = userMapper.checkRole(username);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("非卖家身份");
        }
        return ServerResponse.createBySuccessMessage("身份通过");
    }

    /**
     * 校验用户名或者邮箱是否重复
     * @param str
     * @param type
     * @return
     */
    private ServerResponse<String> checkVilid(String str,String type){
        if(StringUtils.isNotBlank(type)){
            // 开始校验
            // 校验用户名是否重复
            if(Const.USERNAME.equals(type)){
                int resultCount = userMapper.checkUsername(str);
                if(resultCount > 0){
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            // 校验邮箱是否存在
            if(Const.EMAIL.equals(type)){
                int resultCount = userMapper.checkEmail(str);
                if(resultCount > 0){
                    return ServerResponse.createByErrorMessage("邮箱已存在");
                }
            }
        }else{
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

     public ServerResponse<String> applyShop(User user){
        user.setPassword(null);
         int resultCount = userMapper.updateByPrimaryKeySelective(user);
         if(resultCount == 0){
             return ServerResponse.createByErrorMessage("申请失败");
         }
         return ServerResponse.createBySuccessMessage("提交申请成功！");
     }

     public ServerResponse<User> getSellerInfo(Integer userId){
         User user = userMapper.selectByPrimaryKey(userId);
         if(user == null){
             return ServerResponse.createByErrorMessage("这家店已经倒闭，店家已经跑路");
         }
         user.setPassword(StringUtils.EMPTY);
         user.setEmail(StringUtils.EMPTY);
         user.setPhone(StringUtils.EMPTY);
         user.setQuestion(StringUtils.EMPTY);
         user.setAnswer(StringUtils.EMPTY);
         return ServerResponse.createBySuccess(user);
     }

     public ServerResponse<User> getUserInfoByUserId(Integer userId){
         User user = userMapper.selectByPrimaryKey(userId);
         if(user == null){
             return ServerResponse.createByErrorMessage("系统出错了");
         }
         user.setPassword(StringUtils.EMPTY);
         user.setQuestion(StringUtils.EMPTY);
         user.setAnswer(StringUtils.EMPTY);
         return ServerResponse.createBySuccess(user);
     }

    /**
     * 管理员登录
     *
     */
    public User doAjaxLogin(User user) {
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        return userMapper.doAjaxLogin(user);
    }

    /**
     * 买家列表
     *
     */
    public List<User> queryAllBuyer(int page,int size) {
        PageHelper.startPage(page, size);
        return userMapper.queryBuyer();
    }

    /**
     * 卖家列表
     *
     */
    public List<User> queryAllSeller(int page, int size) {
        PageHelper.startPage(page,size);
        return  userMapper.querySeller();
    }


    @Override
    public List<User> querySellerByName(int page, int size, String username) {
        PageHelper.startPage(page,size);
        return userMapper.querySellerByName(username);
    }

    @Override
    public List<User> queryBuyerByName(int page, int size, String username) {
        PageHelper.startPage(page,size);
        return userMapper.queryBuyerByName(username);
    }

    @Override
    public List<User> queryAllApply(int page, int size) {
        PageHelper.startPage(page,size);
        return userMapper.queryAllApply();
    }

    @Override
    public List<User> queryApplyByName(int page, int size, String username) {
        PageHelper.startPage(page,size);
        return userMapper.queryApplyByName(username);
    }

    @Override
    public void applyById(String id) {
        userMapper.applyById(id);
    }

    @Override
    public void deleteBuyerById(String id) {
        userMapper.deleteBuyerById(id);
    }

    @Override
    public User queryByUserId(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteSellerById(String id) {
        userMapper.deleteSellerById(id);
    }

    @Override
    public void ReApplyById(String id) {
        userMapper.ReApplyById(id);
    }
}
