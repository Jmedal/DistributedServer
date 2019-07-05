package com.example.worknet.common.persistence;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeControllerNew3 {
    @ResponseBody
    @RequestMapping(value = "/get/my-resume")
    //获取用户投递过的简历
    public String getMyResume(HttpServletRequest request, HttpServletResponse response){
        HashMap<String,Object> map = new HashMap<>();
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));//每页条数
        int page = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        String searchText = request.getParameter("searchText");
        System.out.println("关键字："+searchText);
        map.put("total",155);//数据总条数
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        for(int i = 0; i < pageSize; i++){
            HashMap<String,Object> obj = new HashMap<>();
            obj.put("companyName","百度");
            obj.put("companyId",(i+page*pageSize+1));
            obj.put("title","实习招聘凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数"+(i+page*pageSize+1));//应聘的信息标题
            obj.put("employId",(i+page*pageSize+100));//招聘信息的id
            obj.put("resumeId",(i+page*pageSize+1));//投放简历的id
            obj.put("status",i%3);//3种状态，待审核，通过，拒绝
            obj.put("lastEditTime","2019-08-05 15:33:25");//上次操作时间（待审核状态的操作时间即上传的时间）
            list.add(obj);
        }
        map.put("rows",list);//list集合
        return JSON.toJSONString(map);
    }
    @ResponseBody
    //获取邀请用户的公司及其招聘信息
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
    @ResponseBody
    public String agreeWelcome(@PathVariable int welcomeId){
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }
    //拒绝邀请
    @RequestMapping(value = "/reject-welcome/{welcomeId}")
    @ResponseBody
    public String rejectWelcome(@PathVariable int welcomeId){
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }
}
