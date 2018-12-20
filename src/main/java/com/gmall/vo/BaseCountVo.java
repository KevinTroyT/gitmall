package com.gmall.vo;/**
 * Created by troykevin on 2018/12/20.
 */

/**
 * @ program : gmall
 * @ author : troykevin
 * @ create : 2018-12-20 19:36
 **/
public class BaseCountVo {
    private int userCount;
    private int productCount;
    private int orderCount;

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }
}
