package com.gmall.common;/**
 * Created by troykevin on 2018/11/25.
 */

/**
 * @ program : gmall
 * @ author : troykevin
 * @ create : 2018-11-25 21:34
 **/
public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL="email";

    public static final String USERNAME="username";

    public interface Role{
        int ROLE_CUSTOMER = 0;  //普通用户
        int ROLE_ADMIN = 1;    //管理员
    }
}
