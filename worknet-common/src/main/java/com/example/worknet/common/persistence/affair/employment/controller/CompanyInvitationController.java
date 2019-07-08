package com.example.worknet.common.persistence.affair.employment.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.worknet.common.persistence.affair.employment.service.CompanyInvitationService;
import com.example.worknet.common.persistence.affair.user.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 面试邀约表 前端控制器
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
@Controller
@ResponseBody
public class CompanyInvitationController {

    @Autowired
    private CompanyInvitationService companyInvitationService;

    @Autowired
    private UserService userService;

    /**
     * 获取邀请用户的公司及其招聘信息
     * @param pageSize
     * @param page
     * @param keyword
     * @param request
     * @return
     */

    @RequestMapping(value = "/get/welcome-company", method = RequestMethod.GET)
    public String getWelcome(@RequestParam("pageNumber") Integer page,
                             @RequestParam("pageSize") Integer pageSize,
                             @RequestParam("searchText") String keyword,
                             HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            if(userService.selectById(userId).getRole().equals(3)) {
                Page<HashMap<String, Object>> pager = companyInvitationService.getCompanyInvitationPage(new Page<>(page,pageSize), userId, keyword);
                map.put("total",pager.getTotal());//数据总条数
                map.put("rows",pager.getRecords());//数据列表
                map.put("errorCode","00");
            }
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 同意公司招聘邀请 [status = 1]
     * @param inviteId
     * @param request
     * @return
     */
    @RequestMapping(value = "/agree-welcome/{welcomeId}", method = RequestMethod.GET)
    public String agreeWelcome(@PathVariable(value = "welcomeId") Long inviteId,
                               HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            if(userService.selectById(userId).getRole().equals(3)
                    && companyInvitationService.changeInvitationStatus(inviteId, userId, 1))
                map.put("errorCode","00");
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 拒绝公司招聘邀请 [status = 2]
     * @param inviteId
     * @param request
     * @return
     */
    @RequestMapping(value = "/reject-welcome/{welcomeId}", method = RequestMethod.GET)
    public String rejectWelcome(@PathVariable(value = "welcomeId") Long inviteId,
                                HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            if(userService.selectById(userId).getRole().equals(3)
                    && companyInvitationService.changeInvitationStatus(inviteId, userId, 2))
                map.put("errorCode","00");
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }
}

