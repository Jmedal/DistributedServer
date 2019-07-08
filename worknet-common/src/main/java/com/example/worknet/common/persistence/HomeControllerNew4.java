package com.example.worknet.common.persistence;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class HomeControllerNew4 {
//
//    @RequestMapping(value = "/admin/login-confirm",method = RequestMethod.POST)
//    public String confirmLogin(HttpServletRequest request){
//        String password = request.getParameter("password");
//        if(password.equals("admin")){
//            return "admin/index.html";
//        }
//        return "admin/login.html";
//    }
//    //关键字用于搜索用户名和学生id
//    @ResponseBody
//    @RequestMapping(value = "/admin/get/stu-account")
//    public String getStuAccount(HttpServletRequest request){
//        HashMap<String,Object> map = new HashMap<>();
//        int pageSize = Integer.parseInt(request.getParameter("pageSize"));//每页条数
//        int page = Integer.parseInt(request.getParameter("pageNumber"));//当前页
//        String searchText = request.getParameter("searchText");
//        System.out.println("关键字："+searchText);//关键字用于搜索用户名和学生id
//        map.put("total",145);//数据总条数
//        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
//        for(int i = 0; i < pageSize; i++){
//            HashMap<String,Object> obj = new HashMap<>();
//            obj.put("id",(i+page*pageSize+1));//学生id
//            obj.put("account","student"+(i+page*pageSize+1));//用户名
//            obj.put("password","123456");
//            obj.put("activity",i%2);
//            list.add(obj);
//        }
//        map.put("rows",list);//list集合
//        return JSON.toJSONString(map);
//    }
//    //关键字用于搜索用户名和公司id
//    @ResponseBody
//    @RequestMapping(value = "/admin/get/com-account")
//    public String getComAccount(HttpServletRequest request){
//        HashMap<String,Object> map = new HashMap<>();
//        int pageSize = Integer.parseInt(request.getParameter("pageSize"));//每页条数
//        int page = Integer.parseInt(request.getParameter("pageNumber"));//当前页
//        String searchText = request.getParameter("searchText");
//        map.put("total",145);//数据总条数
//        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
//        for(int i = 0; i < pageSize; i++){
//            HashMap<String,Object> obj = new HashMap<>();
//            obj.put("id",(i+page*pageSize+1));//学生id
//            obj.put("account","company"+(i+page*pageSize+1));//用户名
//            obj.put("password","123456");
//            obj.put("activity",i%2);
//            list.add(obj);
//        }
//        map.put("rows",list);//list集合
//        return JSON.toJSONString(map);
//    }
//    //创建公司账号，username,password,registerTime3个参数
//    @RequestMapping(value = "/admin/create-com",method = RequestMethod.POST)
//    @ResponseBody
//    public String creatCompanyAccount(){
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        return JSON.toJSONString(map);
//    }
//    //恢复用户
//    @RequestMapping(value = "/admin/resume/{uid}")
//    @ResponseBody
//    public String resumeUser(@PathVariable int uid){
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        return JSON.toJSONString(map);
//    }
//    //禁用用户
//    @RequestMapping(value = "/admin/ban/{uid}")
//    @ResponseBody
//    public String banUser(@PathVariable int uid){
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        return JSON.toJSONString(map);
//    }
//    //修改学生密码
//    @RequestMapping(value = "/admin/change-pass",method = RequestMethod.POST)
//    @ResponseBody
//    public String changeStuPass(HttpServletRequest request){
//        int uid = Integer.parseInt(request.getParameter("userId"));
//        String password = request.getParameter("password");//一共两个参数
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        return JSON.toJSONString(map);
//    }
//    //关键字用于搜索姓名/id/昵称/手机号
//    //id userId nickname realname sex age signature vacation github email phone hobby professional
//    @ResponseBody
//    @RequestMapping(value = "/admin/get/stu-info")
//    public String getStuInfo(HttpServletRequest request){
//        HashMap<String,Object> map = new HashMap<>();
//        int pageSize = Integer.parseInt(request.getParameter("pageSize"));//每页条数
//        int page = Integer.parseInt(request.getParameter("pageNumber"));//当前页
//        String searchText = request.getParameter("searchText");//关键字用于搜索姓名/id/昵称/手机号
//
//        map.put("total",145);//数据总条数
//        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
//        for(int i = 0; i < pageSize; i++){
//            HashMap<String,Object> obj = new HashMap<>();
//            obj.put("id",(i+page*pageSize+1));//信息id
//            obj.put("userId",(i+page*pageSize+1));//学生id
//            obj.put("nickname","昵称"+(i+page*pageSize+1));
//            obj.put("realname","张三"+i);
//            obj.put("sex","男");
//            obj.put("age",24);
//            obj.put("signature",(i+page*pageSize+1));
//            obj.put("vacation",(i+page*pageSize+1));
//            obj.put("github","github.com/"+(i+page*pageSize+1));
//            obj.put("email",(i+page*pageSize+1)+"asdasdasdsa@qq.com");
//            obj.put("phone","142141241");
//            obj.put("hobby","唱歌");
//            obj.put("professional","java");
//            list.add(obj);
//        }
//        map.put("rows",list);//list集合
//        return JSON.toJSONString(map);
//    }
//
//
//    //关键字用于搜索公司名称/id
//    //registerTime website communication address introduction name field id userId
//    //其中id是信息的id,userId是公司账号的id
//    @ResponseBody
//    @RequestMapping(value = "/admin/get/com-info")
//    public String getComInfo(HttpServletRequest request){
//        HashMap<String,Object> map = new HashMap<>();
//        int pageSize = Integer.parseInt(request.getParameter("pageSize"));//每页条数
//        int page = Integer.parseInt(request.getParameter("pageNumber"));//当前页
//        String searchText = request.getParameter("searchText");//关键字用于搜索公司名称/id
//
//        map.put("total",145);//数据总条数
//        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
//        for(int i = 0; i < pageSize; i++){
//            HashMap<String,Object> obj = new HashMap<>();
//            obj.put("registerTime","2019-04-05 05:21:23");
//            obj.put("website","www.baidu.com");
//            obj.put("communication","12313244");
//            obj.put("address","上海市xx路"+i);
//            obj.put("introduction","介绍要很长。。。。。。。。\n包含换行\n\t再试一下\r\n哈哈哈\n\r换行");
//            obj.put("name","百度");
//            obj.put("field","大数据");
//            obj.put("id",(i+page*pageSize+1));
//            obj.put("userId",(i+page*pageSize+1));
//            list.add(obj);
//        }
//        map.put("rows",list);//list集合
//        return JSON.toJSONString(map);
//    }
//    //修改以下信息
//    // website communication address introduction name field
//    @RequestMapping(value = "/admin/com/change-info",method = RequestMethod.POST)
//    @ResponseBody
//    public String changeComInfo(HttpServletRequest request){
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        return JSON.toJSONString(map);
//    }
//    //修改学生信息
//    // nickname realname sex age signature vacation github email phone hobby professional
//    //参数和数据库中一一对应
//    @RequestMapping(value = "/admin/change-info",method = RequestMethod.POST)
//    @ResponseBody
//    public String changeStuInfo(HttpServletRequest request){
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        return JSON.toJSONString(map);
//    }
//    //resumeId companyProfessionId realname userId companyId companyName status
//    //获取学生投递简历信息
//    //关键字：学生id，学生真实姓名，公司id，公司名称，招聘信息title
//    @ResponseBody
//    @RequestMapping(value = "/admin/get/stu-employ")
//    public String getStuEmploy(HttpServletRequest request){
//        HashMap<String,Object> map = new HashMap<>();
//        int pageSize = Integer.parseInt(request.getParameter("pageSize"));//每页条数
//        int page = Integer.parseInt(request.getParameter("pageNumber"));//当前页
//        String searchText = request.getParameter("searchText");//关键字：学生id，学生真实姓名，公司id，公司名称
//
//        map.put("total",145);//数据总条数
//        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
//        for(int i = 0; i < pageSize; i++){
//            HashMap<String,Object> obj = new HashMap<>();
//            obj.put("resumeId",(i+page*pageSize+1));//简历ID
//            obj.put("companyProfessionId",(i+page*pageSize+1));//招聘id
//            obj.put("userId",(i+page*pageSize+1));
//            obj.put("realname","张三"+i);
//            obj.put("companyName","百度");
//            obj.put("employTitle","【2019】暑期实习生");//应聘的标题
//            obj.put("companyId",24);
//            obj.put("status",i%3);//3种状态，待审核，通过，拒绝
//            list.add(obj);
//        }
//        map.put("rows",list);//list集合
//        return JSON.toJSONString(map);
//    }
//    //resumeId companyProfessionId realname userId companyId companyName status
//    //获取学生被邀请的情况
//    //关键字：学生id，学生真实姓名，公司id，公司名称，招聘信息title
//    @ResponseBody
//    @RequestMapping(value = "/admin/get/stu-welcome")
//    public String getStuWelcome(HttpServletRequest request){
//        HashMap<String,Object> map = new HashMap<>();
//        int pageSize = Integer.parseInt(request.getParameter("pageSize"));//每页条数
//        int page = Integer.parseInt(request.getParameter("pageNumber"));//当前页
//        String searchText = request.getParameter("searchText");//关键字：学生id，学生真实姓名，公司id，公司名称
//
//        map.put("total",145);//数据总条数
//        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
//        for(int i = 0; i < pageSize; i++){
//            HashMap<String,Object> obj = new HashMap<>();
//            obj.put("companyProfessionId",(i+page*pageSize+1));//招聘id
//            obj.put("userId",(i+page*pageSize+1));
//            obj.put("realname","张三"+i);
//            obj.put("companyName","百度");
//            obj.put("employTitle","【2019】暑期实习生");//应聘的标题
//            obj.put("companyId",24);
//            obj.put("status",i%3);//3种邀请状态，未读，同意，拒绝
//            list.add(obj);
//        }
//        map.put("rows",list);//list集合
//        return JSON.toJSONString(map);
//    }
}
