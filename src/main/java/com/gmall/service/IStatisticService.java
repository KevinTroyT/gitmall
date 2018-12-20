package com.gmall.service;

import com.gmall.common.ServerResponse;

/**
 * Created by troykevin on 2018/12/20.
 */
public interface IStatisticService {
    /**
     * 查询统计数据
     * @return
     */
    ServerResponse baseCount();
}
