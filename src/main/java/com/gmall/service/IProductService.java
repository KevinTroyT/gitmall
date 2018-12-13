package com.gmall.service;

import com.github.pagehelper.PageInfo;
import com.gmall.common.ServerResponse;
import com.gmall.pojo.Product;
import com.gmall.vo.ProductDetailVo;

/**
 * Created by troykevin on 2018/11/30.
 */
public interface IProductService {

    /**
     * 保存商品
     * @param product
     * @return
     */
    ServerResponse saveOrUpdateProduct(Product product);

    /**
     * 设置商品状态
     * @param productId
     * @param status
     * @return
     */
    ServerResponse<String> setSaleStatus(Integer productId, Integer status);

    /**
     * 获取产品详情（后台用）
     * @param productId
     * @return
     */
    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    /**
     * 获取产品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    /**
     * 搜索商品
     * @param productName
     * @param productId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> searchProduct(String productName,Integer productId,int pageNum,int pageSize);

    /**
     * 获取商品详细信息（前台用）
     * @param productId
     * @return
     */
    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    /**
     * 搜索
     * @param keyword
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword,Integer categoryId,int pageNum,int pageSize,String orderBy);
}
