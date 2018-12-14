package com.gmall.service;

import com.gmall.common.ServerResponse;
import com.gmall.pojo.Category;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by troykevin on 2018/11/29.
 */
public interface ICategoryService {
    /**
     * 增加品类
     * @param categoryName
     * @param parentId
     * @return
     */
    ServerResponse addCategory(String categoryName, Integer parentId);

    /**
     * 更新品类
     * @param session
     * @param categoryId
     * @param categoryName
     * @return
     */
    ServerResponse updateCategoryName(HttpSession session, Integer categoryId, String categoryName);

    /**
     * 查询子品类
     * @param categoryId
     * @return
     */
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    /**
     * 递归查询子节点品类的id
     * @param categoryId
     * @return
     */
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
