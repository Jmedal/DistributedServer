package com.example.worknet.common.persistence;

import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class HomeControllerNew5 {
    //公司的页面
    @RequestMapping(value = "/company/login")
    public String login(){
        return "company/login.html";
    }
    @RequestMapping(value = "/company/")
    public String home(){
        return "company/home.html";
    }
    @RequestMapping(value = "/company/resume")
    public String cr(){
        return "company/resume.html";
    }
    @RequestMapping(value = "/company/account")
    public String sa(){
        return "company/stu-account.html";
    }
    @RequestMapping(value = "/company/info")
    public String si(){
        return "company/stu-info.html";
    }
    @RequestMapping(value = "/company/employ")
    public String se(){
        return "company/stu-employ.html";
    }
    @RequestMapping(value = "/company/welcome")
    public String sw(){
        return "company/stu-welcome.html";
    }
    @RequestMapping(value = "/company/welcome-info")
    public String swi(){
        return "company/welcome-info.html";
    }
    @RequestMapping(value = "/company/login-confirm")
    public String comfirm(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(password.equals("123456"))//通过
            return "company/index.html";
        return "company/login.html";
    }
    //修改密码
    @ResponseBody
    @RequestMapping(value = "/company/change/password",method = RequestMethod.POST)
    public String changePass(HttpServletRequest request){
        //param:old_pass,new_pass,confirm_pass
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }
    //获取companyId
    @ResponseBody
    @RequestMapping(value = "/company/get/info")
    public String getCompanyId(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        HashMap<String,Object> obj = new HashMap<>();
        obj.put("name","百度");
        obj.put("introduction","百度公司是一家恰烂钱的垃圾公司，广告专门推广莆田系，搜索还差的要死。");
        obj.put("address","上海市xx区xx路xxx号");
        obj.put("communication","021-55555555");
        obj.put("website","http://www.baidu.com");
        obj.put("field","互联网");
        map.put("returnObject",obj);
        return JSON.toJSONString(map);
    }
    //修改以下信息
    // website communication address introduction name field
    @RequestMapping(value = "/company/change-info",method = RequestMethod.POST)
    @ResponseBody
    public String changeComInfo(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }
    //获取登录公司接受的所有简历
    //即简历的目标公司id为该登录公司
    //关键字：学生id，学生真实姓名
    @RequestMapping(value = "/company/get/resume")
    @ResponseBody
    public String getResume(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));//每页条数
        int page = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        String searchText = request.getParameter("searchText");//关键字：学生id，学生真实姓名
        map.put("total",145);//数据总条数
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        for(int i = 0; i < pageSize; i++){
            HashMap<String,Object> obj = new HashMap<>();
            obj.put("resumeId",(i+page*pageSize+1));//简历ID
            obj.put("companyId",(i+page*pageSize+1));//这个登录公司的id，用来锁定他的招聘网址
            obj.put("companyProfessionId",(i+page*pageSize+1));//招聘id
            obj.put("studentId",(i+page*pageSize+1));//学生的id
            obj.put("realname","张三"+i);//投递简历的学生姓名
            obj.put("employTitle","【2019】暑期实习生");//应聘的标题
            obj.put("status",i%3);//3种状态，待审核，通过，拒绝
            list.add(obj);
        }
        map.put("rows",list);//list集合
        return JSON.toJSONString(map);
    }
    //同意某简历
    //后端可以添加身份验证，也可以懒得做
    @RequestMapping(value = "/company/agree/{resumeId}")
    @ResponseBody
    public String agreeResume(@PathVariable int resumeId){
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }
    //拒绝某简历
    @RequestMapping(value = "/company/reject/{resumeId}")
    @ResponseBody
    public String rejectResume(@PathVariable int resumeId){
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }
    //作为公司获取学生信息
    //和管理员应该有所区别，因为公司获取的信息可能需要通过学生的设置同意
    //也可以一样，反正懒
    //id userId nickname realname sex age signature vacation github email phone hobby professional
    @ResponseBody
    @RequestMapping(value = "/company/get/stu-info")
    public String getStuInfo(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));//每页条数
        int page = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        String searchText = request.getParameter("searchText");//关键字用于搜索姓名/id/昵称/手机号

        map.put("total",145);//数据总条数
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        for(int i = 0; i < pageSize; i++){
            HashMap<String,Object> obj = new HashMap<>();
            obj.put("id",(i+page*pageSize+1));//信息id
            obj.put("studentId",(i+page*pageSize+1));//学生id
            obj.put("nickname","昵称"+(i+page*pageSize+1));
            obj.put("realname","张三"+i);
            obj.put("sex","男");
            obj.put("age",24);
            obj.put("signature",(i+page*pageSize+1));
            obj.put("vacation",(i+page*pageSize+1));
            obj.put("github","github.com/"+(i+page*pageSize+1));
            obj.put("email",(i+page*pageSize+1)+"asdasdasdsa@qq.com");
            obj.put("phone","142141241");
            obj.put("hobby","唱歌");
            obj.put("professional","java");
            list.add(obj);
        }
        map.put("rows",list);//list集合
        return JSON.toJSONString(map);
    }

    //获取该登录公司的招聘信息列表
    //不分页了，直接获取全部
    @ResponseBody
    @RequestMapping(value = "/company/get/employ-list")
    public String getComEmployList(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            HashMap<String,Object> objectHashMap = new HashMap<>();
            objectHashMap.put("companyProfessionId",i);//招聘信息的id
            objectHashMap.put("title","2019实习 java"+i);//标题
            list.add(objectHashMap);
        }
        map.put("returnObject",list);
        return JSON.toJSONString(map);
    }
    //公司邀请某个学生来某个招聘信息
    @RequestMapping(value = "/company/welcome",method = RequestMethod.POST)
    @ResponseBody
    public String welcomeStudent(HttpServletRequest request){
        //有companyProfessionId和studentId两个参数，代表招聘信息id和学生id
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }
    //获取公司的所有邀请信息
    //类似管理员的功能，除了需要设定邀请公司为当前登录公司
    @ResponseBody
    @RequestMapping(value = "/company/get/welcome-info")
    public String getWelcomeInfo(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));//每页条数
        int page = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        String searchText = request.getParameter("searchText");//关键字：学生id，学生真实姓名，公司id，公司名称

        map.put("total",145);//数据总条数
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        for(int i = 0; i < pageSize; i++){
            HashMap<String,Object> obj = new HashMap<>();
            obj.put("companyProfessionId",(i+page*pageSize+1));//招聘id
            obj.put("studentId",(i+page*pageSize+1));
            obj.put("realname","张三"+i);
            obj.put("employTitle","【2019】暑期实习生");//应聘的标题
            obj.put("companyId",24);
            obj.put("status",i%3);//3种邀请状态，未读，同意，拒绝
            list.add(obj);
        }
        map.put("rows",list);//list集合
        return JSON.toJSONString(map);
    }
    //获取登录公司的招聘信息
    //关键字：招聘标题，内容，要求
    //id companyId professionTypeId title introduction
    // requirement salary state location isPractice duration chanceToFormal
    @RequestMapping(value = "/company/get/employ-info")
    @ResponseBody
    public String getEmployInfo(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));//每页条数
        int page = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        String searchText = request.getParameter("searchText");//关键字：招聘标题，内容，要求

        map.put("total",145);//数据总条数
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        for(int i = 0; i < pageSize; i++){
            HashMap<String,Object> obj = new HashMap<>();
            obj.put("id",(i+page*pageSize+1));//招聘id
            obj.put("companyId",(i+page*pageSize+1));//登录公司id，用于链接跳转
            obj.put("professionTypeId",i);
            obj.put("state",(i+1)%2);//招聘状态，进行中，已结束
            obj.put("professionType","研发");
            obj.put("location","上海");
            obj.put("title","【2019】暑期实习生");//应聘的标题
            obj.put("salary","140元/天");//应聘的标题
            obj.put("introduction","关于这个职业的一些介绍\n包括换行\n哈哈");
            obj.put("requirement","关于这个职业的一些需求\n包括换行\n哈哈");
            obj.put("isPractice",i%2);//0表示不是实习
            obj.put("duration","5天/周，3月以上");//只有当是实习的时候才有效
            obj.put("chanceToFormal","有");//转正机会，同样只有是实习才有效
            list.add(obj);
        }
        map.put("rows",list);//list集合
        return JSON.toJSONString(map);
    }
    //id即招聘信息的id
    @RequestMapping(value = "/company/end/employ/{id}")
    @ResponseBody
    public String endEmploy(@PathVariable int id){
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }
    //修改公司招聘信息，包括下列参数：
    //id companyId professionTypeId title introduction professionType(这个可以无视)
    // requirement salary location isPractice duration chanceToFormal
    @RequestMapping(value = "/company/employ/change-info",method = RequestMethod.POST)
    @ResponseBody
    public String changeEmployInfo(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }
    //创建公司招聘信息，包括下列参数：
    //professionTypeId title introduction professionType(这个可以无视)
    // requirement salary location isPractice duration chanceToFormal
    //companyId即为登录公司的id
    @RequestMapping(value = "/company/employ/create-info",method = RequestMethod.POST)
    @ResponseBody
    public String createEmployInfo(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }
    //修改公司头像(这段代码的作用是接收文件并存储在指定的位置，fileName是文件名，可以用.截取后缀随机生成文件名)
    @RequestMapping(value = "/company/change/myAvatar",method = RequestMethod.POST)
    public String changeAvatar(@RequestParam MultipartFile avatar, HttpServletRequest request){
        String basePath  = "D:\\SoftwareXieTong\\src\\main\\resources\\";
        String fileName = request.getParameter("fileName");//avatar本身获取的name没有后缀，我又传了一次
        try {
            avatar.transferTo(new File(basePath + fileName));
        } catch (Exception e) {
            // TODO
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }
    //获取登录公司自己的头像
    @ResponseBody
    @RequestMapping(value = "/company/get/myAvatar",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getCoursePic(HttpServletResponse response) throws IOException {
        File file = new File("D:\\SoftwareXieTong\\src\\main\\resources\\static\\images\\course2.jpg");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }

    //管理员获取所有公司的招聘岗位信息
    //关键字：招聘标题，内容，要求
    //id companyId professionTypeId title introduction companyName
    // requirement salary state location isPractice duration chanceToFormal
    @RequestMapping(value = "/admin/get/com-employ")
    @ResponseBody
    public String adminGetEmployInfo(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));//每页条数
        int page = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        String searchText = request.getParameter("searchText");//关键字：招聘标题，内容，要求

        map.put("total",145);//数据总条数
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        for(int i = 0; i < pageSize; i++){
            HashMap<String,Object> obj = new HashMap<>();
            obj.put("id",(i+page*pageSize+1));//招聘id
            obj.put("companyId",(i+page*pageSize+1));//公司id
            obj.put("companyName","百度");
            obj.put("professionTypeId",i);
            obj.put("state",(i+1)%2);//招聘状态，进行中，已结束
            obj.put("professionType","研发");
            obj.put("location","上海");
            obj.put("title","【2019】暑期实习生");//应聘的标题
            obj.put("salary","140元/天");
            obj.put("introduction","关于这个职业的一些介绍\n包括换行\n哈哈");
            obj.put("requirement","关于这个职业的一些需求\n包括换行\n哈哈");
            obj.put("isPractice",i%2);//0表示不是实习
            obj.put("duration","5天/周，3月以上");//只有当是实习的时候才有效
            obj.put("chanceToFormal","有");//转正机会，同样只有是实习才有效
            list.add(obj);
        }
        map.put("rows",list);//list集合
        return JSON.toJSONString(map);
    }
    //管理员获取职业列表
    @RequestMapping(value = "/admin/get/profession")
    @ResponseBody
    public String adminGetProfession(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));//每页条数
        int page = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        String searchText = request.getParameter("searchText");//关键字：招聘标题，内容，要求

        map.put("total",145);//数据总条数
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        for(int i = 0; i < pageSize; i++){
            HashMap<String,Object> obj = new HashMap<>();
            obj.put("professionTypeId",(i+page*pageSize+1));//招聘id
            obj.put("professionType","数据"+i);
            list.add(obj);
        }
        map.put("rows",list);//list集合
        return JSON.toJSONString(map);
    }
    //管理员修改职业
    @RequestMapping(value = "/admin/change-profession",method = RequestMethod.POST)
    @ResponseBody
    public String adminChangeProfession(HttpServletRequest request){
        request.getParameter("professionTypeId");
        request.getParameter("professionType");//一共两个参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }
    //管理员创建职业
    @RequestMapping(value = "/admin/create-profession",method = RequestMethod.POST)
    @ResponseBody
    public String adminCreateProfession(HttpServletRequest request){
        request.getParameter("professionType");//只有一个参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }
    //管理员删除职业
    @RequestMapping(value = "/admin/delete-profession",method = RequestMethod.POST)
    @ResponseBody
    public String adminDeleteProfession(HttpServletRequest request){
        System.out.println(request.getParameter("professionTypeId"));//只有一个参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }
}
