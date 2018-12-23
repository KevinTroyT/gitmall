package com.gmall.service;
/**
 * Created by troykevin on 2018/11/23.
 */

import com.github.pagehelper.PageInfo;
import com.gmall.common.ServerResponse;
import com.gmall.pojo.User;

/**
 * @ program : gmall
 * @ author : troykevin
 * @ create : 2018-11-23 14:26
 **/
public interface IUserService {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    ServerResponse<User> login(String username, String password);

    /**
     * 注册
     * @param user
     * @return
     */
    ServerResponse<String> register(User user);

    /**
     * 检查是否合法，type有两种 username/email
     * @param str
     * @param type
     * @return
     */
    ServerResponse<String> checkValid(String str,String type);

    /**
     * 查询安全问题
     * @param username
     * @return
     */
    ServerResponse selectQuestion(String username);

    /**
     * 检查答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
    ServerResponse<String> checkAnswer(String username,String question,String answer);

    /**
     * 忘记密码
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

    /**
     * 登录状态重设密码
     * @param passwordOld
     * @param passwordNew
     * @param user
     * @return
     */
    ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user);

    /**
     * 更新个人信息
     * @param user
     * @return
     */
    ServerResponse<User> updateInformation(User user);

    /**
     * 获得个人信息
     * @param userId
     * @return
     */
    ServerResponse<User> getInformation(Integer userId);

    /**
     * 检查用户管理员权限
     * @param user
     * @return
     */
    ServerResponse checkAdmin(User user);

    /**
     * 后台用，获得所有用户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> getUserList(int pageNum, int pageSize);

}
