package com.example.worknet.common.persistence.affair.employment.controller;


import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 获取邀请用户的公司及其招聘信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/get/welcome-company")
    public String getWelcome(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json;charset=utf-8");
        HashMap<String,Object> map = new HashMap<>();
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));//每页条数
        int page = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        String searchText = request.getParameter("searchText");
        System.out.println("关键字："+searchText);
        map.put("total",145);//数据总条数
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        for(int i = 0; i < pageSize; i++){
            HashMap<String,Object> obj = new HashMap<>();
            obj.put("id",(i+page*pageSize+1));//邀请id，用来同意邀请时使用，唯一标识一份邀请
            obj.put("companyName","百度");
            obj.put("companyId",(i+page*pageSize+1));
            obj.put("title","实习招聘凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数"+(i+page*pageSize+1));//应聘的信息标题
            obj.put("employId",(i+page*pageSize+100));//招聘信息的id
            obj.put("status",i%3);//3种状态，未读，同意，拒绝
            obj.put("inviteTime","2019-08-05 15:33:25");//上次操作时间（待审核状态的操作时间即上传的时间）
            list.add(obj);
        }
        map.put("rows",list);//list集合
        return JSON.toJSONString(map);
    }


    //同意邀请
    @RequestMapping(value = "/agree-welcome/{welcomeId}")
    public String agreeWelcome(@PathVariable int welcomeId){
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }

    //拒绝邀请
    @RequestMapping(value = "/reject-welcome/{welcomeId}")
    public String rejectWelcome(@PathVariable int welcomeId){
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }
}

