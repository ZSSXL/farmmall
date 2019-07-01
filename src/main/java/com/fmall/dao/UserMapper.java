package com.fmall.dao;

import com.fmall.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    int checkEmail(String email);

    User selectLogin(@Param("username") String username,@Param("password") String password);

    String getQuestion(String username);

    int checkAnswer(@Param("username") String username,@Param("question") String question,@Param("answer") String answer);

    int checkPasswordOld(@Param("username") String username,@Param("passwordOld") String passwordOld);

    int resetPassword(@Param("username")String username,@Param("passwordNew") String passwordNew);

    int checkRole(String username);

    User doAjaxLogin(User user);


    List<User> queryBuyer();


    List<User> querySeller();

    List<User> querySellerByName(@Param("username") String username);

    List<User> queryBuyerByName(@Param("username") String username);

    List<User> queryAllApply();

    List<User> queryApplyByName(@Param("username") String username);

    void applyById(@Param("id") String id);

    void deleteBuyerById(@Param("id") String id);

    void deleteSellerById(@Param("id")String id);

    void ReApplyById(@Param("id")String id);
}