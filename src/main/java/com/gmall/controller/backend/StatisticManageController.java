package com.gmall.controller.backend;/**
 * Created by troykevin on 2018/12/20.
 */

import com.gmall.common.Const;
import com.gmall.common.ResponseCode;
import com.gmall.common.ServerResponse;
import com.gmall.pojo.User;
import com.gmall.service.IStatisticService;
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
 * @ create : 2018-12-20 19:29
 **/
@Controller
@RequestMapping("/manage/statistic")
public class StatisticManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IStatisticService iStatisticService;


    @RequestMapping("base_count.do")
    @ResponseBody
    public ServerResponse baseCount(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        //校验权限
        if(iUserService.checkAdmin(user).isSuccess()){
            return iStatisticService.baseCount();
        }else{
            return ServerResponse.createByErrorMessage("无权限");
        }
    }
}
