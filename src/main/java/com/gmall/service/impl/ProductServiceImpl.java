package com.gmall.service.impl;/**
 * Created by troykevin on 2018/11/30.
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gmall.common.Const;
import com.gmall.common.ResponseCode;
import com.gmall.common.ServerResponse;
import com.gmall.dao.CategoryMapper;
import com.gmall.dao.ProductMapper;
import com.gmall.pojo.Category;
import com.gmall.pojo.Product;
import com.gmall.service.ICategoryService;
import com.gmall.service.IProductService;
import com.gmall.util.DateTimeUtil;
import com.gmall.util.PropertiesUtil;
import com.gmall.vo.ProductDetailVo;
import com.gmall.vo.ProductListVo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ program : gmall
 * @ author : troykevin
 * @ create : 2018-11-30 16:42
 **/
@Service("iProductService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ICategoryService iCategoryService;


    public ServerResponse saveOrUpdateProduct(Product product){
        if(product != null){
            if(StringUtils.isNotBlank(product.getSubImages())){
                String[] subImageArray = product.getSubImages().split(",");
                if(subImageArray.length>0){
                    product.setMainImage(subImageArray[0]);
                }
            }
            if(product.getId() != null){
                int rowCount = productMapper.updateByPrimaryKey(product);
                if(rowCount > 0){
                    return ServerResponse.createBySuccess("更新产品成功");
                }
                return ServerResponse.createByErrorMessage("更新产品失败");
            }else{
                int rowCount = productMapper.insert(product);
                if(rowCount > 0) {
                    return ServerResponse.createBySuccess("新增产品成功");
                }
                return ServerResponse.createByErrorMessage("新增产品失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新参数错误");
    }

    public ServerResponse<String> setSaleStatus(Integer productId, Integer status){
        if(productId == null || status == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("修改状态成功");
        }
        return ServerResponse.createByErrorMessage("修改状态失败");
    }

    public ServerResponse<ProductDetailVo> manageProductDetail(Integer productId){
        if(productId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if(product == null){
            return ServerResponse.createByErrorMessage("产品已经下架或者删除");
        }
        ProductDetailVo productDetailVo = assemblePrductDetailVo(product);
        return ServerResponse.createBySuccess(productDetailVo);

    }

    /**
     * 装配vo
     * @param product
     * @return
     */
    private ProductDetailVo assemblePrductDetailVo(Product product){
        ProductDetailVo productDetailVo = new ProductDetailVo();
        productDetailVo.setId(product.getId());
        productDetailVo.setStatus(product.getStatus());
        productDetailVo.setMainImage(product.getMainImage());
        productDetailVo.setSubtitle(product.getSubtitle());
        productDetailVo.setPrice(productDetailVo.getPrice());
        productDetailVo.setCategoryId(product.getCategoryId());
        productDetailVo.setDetail(product.getDetail());
        productDetailVo.setName(product.getName());
        productDetailVo.setStock(product.getStock());

        // imageHost
        productDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","132.232.159.58"));
        // ParentCategoryId
        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        if(category == null){
            productDetailVo.setParentCategoryId(0);// 默认为空
        }else{
            productDetailVo.setParentCategoryId(category.getParentId());
        }
        // create time & update time
        productDetailVo.setCreateTime(DateTimeUtil.dateToStr(product.getCreateTime()));
        productDetailVo.setUpdateTime(DateTimeUtil.dateToStr(product.getUpdateTime()));
        return productDetailVo;
    }
    public ServerResponse<PageInfo> getProductList(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Product> productList = productMapper.selectList();
        List<ProductListVo> productListVoList = Lists.newArrayList();
        for(Product productItem : productList){
            ProductListVo productListVo = assembleProductListVo(productItem);
            productListVoList.add(productListVo);
        }
        PageInfo pageResult = new PageInfo(productList);
        pageResult.setList(productListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    /**
     * 装配vo
     * @param product
     * @return
     */
    private ProductListVo assembleProductListVo(Product product){
        ProductListVo productListVo = new ProductListVo();
        productListVo.setId(product.getId());
        productListVo.setName(product.getName());
        productListVo.setMainImage(product.getMainImage());
        productListVo.setCategoryId(product.getCategoryId());
        productListVo.setPrice(product.getPrice());
        productListVo.setStatus(product.getStatus());
        productListVo.setSubtitle(product.getSubtitle());

        productListVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","132.232.159.58"));
        return productListVo;
    }

    public ServerResponse<PageInfo> searchProduct(String productName,Integer productId,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(productName)){
            productName = new StringBuilder().append("%").append(productName).append("%").toString();
        }
        List<Product> productList = productMapper.selectByNameAndProductId(productName,productId);
        List<ProductListVo> productListVoList = Lists.newArrayList();
        for(Product productItem : productList){
            ProductListVo productListVo = assembleProductListVo(productItem);
            productListVoList.add(productListVo);
        }
        PageInfo pageResult = new PageInfo(productList);
        pageResult.setList(productListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }
    public ServerResponse<ProductDetailVo> getProductDetail(Integer productId){
        if(productId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if(product == null){
            return ServerResponse.createByErrorMessage("产品已经下架或者删除");
        }
        if(product.getStatus()!= Const.ProductStatusEnum.ON_SALE.getCode()){
            return ServerResponse.createByErrorMessage("产品已经下架或者删除");
        }
        ProductDetailVo productDetailVo = assemblePrductDetailVo(product);
        return ServerResponse.createBySuccess(productDetailVo);
    }
    public ServerResponse<PageInfo> getProductByKeywordCategory(String keyword,Integer categoryId,int pageNum,int pageSize,String orderBy){
        if(StringUtils.isBlank(keyword) && categoryId==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        List<Integer> categoryIdList = new ArrayList<Integer>();
        if(categoryId != null){
            Category category = categoryMapper.selectByPrimaryKey(categoryId);
            if(category == null && StringUtils.isBlank(keyword)){
                //没有分类，没有关键字，但不报错
                PageHelper.startPage(pageNum,pageSize);
                List<ProductListVo> productListVoList = Lists.newArrayList();
                PageInfo pageInfo = new PageInfo(productListVoList);
                return ServerResponse.createBySuccess(pageInfo);
            }
            categoryIdList = iCategoryService.selectCategoryAndChildrenById(category.getId()).getData();
        }
        if(StringUtils.isNotBlank(keyword)){
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }
        PageHelper.startPage(pageNum,pageSize);
        // sort
        if(StringUtils.isNotBlank(orderBy)){
            if(Const.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)){
                String[] orderByArray = orderBy.split("_");
                PageHelper.orderBy(orderByArray[0]+" "+orderByArray[1]);
            }
        }
        List<Product> productList = productMapper.selectByNameAndCategoryIds(StringUtils.isBlank(keyword)?null:keyword,categoryIdList.size()==0?null:categoryIdList);

        List<ProductListVo> productListVoList = Lists.newArrayList();
        for(Product product : productList){
            ProductListVo productListVo = assembleProductListVo(product);
            productListVoList.add(productListVo);
        }

        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productListVoList);
        return  ServerResponse.createBySuccess(pageInfo);
    }
}
