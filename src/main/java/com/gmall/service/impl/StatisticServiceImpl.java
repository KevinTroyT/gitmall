package com.gmall.service.impl;/**
 * Created by troykevin on 2018/12/20.
 */

import com.gmall.common.ServerResponse;
import com.gmall.dao.OrderMapper;
import com.gmall.dao.ProductMapper;
import com.gmall.dao.UserMapper;
import com.gmall.service.IStatisticService;
import com.gmall.vo.BaseCountVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ program : gmall
 * @ author : troykevin
 * @ create : 2018-12-20 19:34
 **/
public class StatisticServiceImpl implements IStatisticService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    UserMapper userMapper;

    public ServerResponse baseCount(){
        BaseCountVo baseCountVo = assembleBaseCountVo();
        if (null == baseCountVo) {
            ServerResponse.createByErrorMessage("查询统计错误");
        }
        return ServerResponse.createBySuccess(baseCountVo);
    }
    private BaseCountVo assembleBaseCountVo(){
        BaseCountVo baseCountVo = new BaseCountVo();
        int orderCount = orderMapper.selectCountOrder();
        int productCount = productMapper.selectCountProduct();
        int userCount = userMapper.selectCountUsers();
        baseCountVo.setOrderCount(orderCount);
        baseCountVo.setProductCount(productCount);
        baseCountVo.setUserCount(userCount);
        return baseCountVo;
    }
}
