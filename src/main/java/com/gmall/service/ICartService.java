package com.gmall.service;

import com.gmall.common.ServerResponse;
import com.gmall.vo.CartVo;

/**
 * Created by troykevin on 2018/12/9.
 */
public interface ICartService {
    /**
     * 添加购物车
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    /**
     * 更新购物车
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    ServerResponse<CartVo> update(Integer userId,Integer productId,Integer count);

    /**
     * 购物车中删除产品
     * @param userId
     * @param productIds
     * @return
     */
    ServerResponse<CartVo> deleteProduct(Integer userId,String productIds);

    /**
     * 查询购物车
     * @param userId
     * @return
     */
    ServerResponse<CartVo> list(Integer userId);

    /**
     * 全选/全反选/选择/反选
     * @param userId
     * @param checked
     * @return
     */
    ServerResponse<CartVo> selectOrUnSelect(Integer userId,Integer checked,Integer productId);

    /**
     * 获得购物车产品数量
     * @param userId
     * @return
     */
    ServerResponse<Integer> getCartProductCount(Integer userId);
}
