package com.gmall.controller.backend;/**
 * Created by troykevin on 2018/11/29.
 */

import com.gmall.common.Const;
import com.gmall.common.ResponseCode;
import com.gmall.common.ServerResponse;
import com.gmall.pojo.User;
import com.gmall.service.ICategoryService;
import com.gmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @ program : gmall
 * @ author : troykevin
 * @ create : 2018-11-29 16:55
 **/
@Controller
@RequestMapping("/manage/category/")
public class CategoryManageController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session,String categoryName,@RequestParam(value = "parentId", defaultValue = "0") int parentId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        //校验权限
        if(iUserService.checkAdmin(user).isSuccess()){
            return iCategoryService.addCategory(categoryName,parentId);
        }else{
            return ServerResponse.createByErrorMessage("无权限");
        }
    }
    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session,Integer categoryId,String categoryName){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        //校验权限
        if(iUserService.checkAdmin(user).isSuccess()){
            return iCategoryService.updateCategoryName(session,categoryId,categoryName);
        }else{
            return ServerResponse.createByErrorMessage("无权限");
        }
    }
    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session,@RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        //校验权限
        if(iUserService.checkAdmin(user).isSuccess()){
            return iCategoryService.getChildrenParallelCategory(categoryId);
        }else{
            return ServerResponse.createByErrorMessage("无权限");
        }
    }

    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildren(HttpSession session,@RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        //校验权限
        if(iUserService.checkAdmin(user).isSuccess()){
           // 查询当前节点以及递归查询子节点id
           // 0->100001->100002
           // 当查询0，返回100001,100002
           // 当查询100001,返回100002
            return iCategoryService.selectCategoryAndChildrenById(categoryId);
        }else{
            return ServerResponse.createByErrorMessage("无权限");
        }
    }
}
