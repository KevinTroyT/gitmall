package com.gmall.service;

import com.github.pagehelper.PageInfo;
import com.gmall.common.ServerResponse;
import com.gmall.pojo.Shipping;

/**
 * Created by troykevin on 2018/12/10.
 */
public interface IShippingService {
    /**
     * 新增地址
     * @param userId
     * @param shipping
     * @return
     */
    ServerResponse add(Integer userId, Shipping shipping);

    /**
     * 删除地址
     * @param userId
     * @param shippingId
     * @return
     */
    ServerResponse<String> del(Integer userId,Integer shippingId);

    /**
     * 更新地址
     * @param userId
     * @param shipping
     * @return
     */
    ServerResponse update(Integer userId, Shipping shipping);

    /**
     * 查询地址
     * @param userId
     * @param shippingId
     * @return
     */
    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    /**
     * 获得地址列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
