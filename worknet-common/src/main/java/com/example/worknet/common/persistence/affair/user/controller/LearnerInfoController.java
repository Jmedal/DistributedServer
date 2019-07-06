package com.example.worknet.common.persistence.affair.user.controller;


import com.alibaba.fastjson.JSON;
import com.example.worknet.common.persistence.affair.user.serivce.LearnerInfoService;
import com.example.worknet.common.persistence.affair.user.serivce.UserService;
import com.example.worknet.common.persistence.template.modal.LearnerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>
 * 学习者信息表 前端控制器
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
@Controller
@ResponseBody
public class LearnerInfoController {

    @Autowired
    private UserService userService;

    @Autowired
    private LearnerInfoService learnerInfoService;

    /**
     * 学习者设置默认简历模版
     * @param resumeId
     * @return
     */
    @RequestMapping(value = "/setFavorResume/{resumeId}", method = RequestMethod.GET)
    public String setFavorResume(@PathVariable(value = "resumeId") Long resumeId,
                                 HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            if(userService.selectById(userId).getRole().equals(3)) {
                if(learnerInfoService.updateDefaultCvId(userId,resumeId))
                    map.put("errorCode", "00");
                else
                    map.put("errorCode", "error");
            }
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }
}

