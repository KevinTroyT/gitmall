package com.gmall.service;

import com.github.pagehelper.PageInfo;
import com.gmall.common.ServerResponse;
import com.gmall.vo.OrderVo;

import java.util.Map;

/**
 * Created by troykevin on 2018/12/12.
 */
public interface IOrderService {

    /**
     * 创建订单
     * @param userId
     * @param shippingId
     */
    ServerResponse createOrder(Integer userId,Integer shippingId);

    /**
     * 取消订单
     * @param userId
     * @param orderNo
     * @return
     */
    ServerResponse<String> cancel(Integer userId,Long orderNo);

    /**
     * 获得购物车内购买的商品
     * @param userId
     * @return
     */
    ServerResponse getOrderCartProduct(Integer userId);

    /**
     * 获得order详情
     * @param userId
     * @param orderNo
     * @return
     */
    ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo);

    /**
     * 前台获取订单列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> getOrderList(Integer usgiterId, int pageNum, int pageSize);

    /**
     * 后台详细搜索
     * @param orderNo
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> manageSearch(Long orderNo,int pageNum,int pageSize);

    /**
     * 后台发货
     * @param orderNo
     * @return
     */
    ServerResponse<String> manageSendGoods(Long orderNo);
    /**
     * 后台查询产品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> manageList(int pageNum,int pageSize);

    /**
     * 后台查询产品详情
     * @param orderNo
     * @return
     */
    ServerResponse<OrderVo> manageDetail(Long orderNo);

    /**
     * 支付并生成二维码
     * @param orderNo
     * @param userId
     * @param path
     * @return
     */
    ServerResponse pay(Long orderNo, Integer userId, String path);

    /**
     * 回调验证
     * @param params
     * @return
     */
    ServerResponse aliCallback(Map<String,String> params);

    /**
     * 查询支付状态
     * @param userId
     * @param orderNo
     * @return
     */
    ServerResponse queryOrderPay(Integer userId,Long orderNo);
}
