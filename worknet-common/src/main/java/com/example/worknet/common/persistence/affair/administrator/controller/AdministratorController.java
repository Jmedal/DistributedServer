package com.example.worknet.common.persistence.affair.administrator.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.worknet.common.persistence.affair.administrator.service.AdministratorService;
import com.example.worknet.common.persistence.template.modal.Company;
import com.example.worknet.common.persistence.template.modal.LearnerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static com.example.worknet.common.constant.UserConst.COMPANY;
import static com.example.worknet.common.constant.UserConst.STUDENT;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
@Controller
@ResponseBody
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    /**
     * 加载学生帐号列表
     * @param page
     * @param pageSize
     * @param keyword
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/get/stu-account", method = RequestMethod.GET)
    public String getStuAccount(@RequestParam("pageNumber") Integer page,
                                @RequestParam("pageSize") Integer pageSize,
                                @RequestParam("searchText") String keyword,    //关键字用于搜索用户名和学生id
                                HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null
                && (request.getSession(true).getAttribute("userId")).equals("administrator")) {
            Page<HashMap<String,Object>> pager = administratorService.getUserPage(new Page<>(page,pageSize), STUDENT, keyword);
            map.put("total",pager.getTotal());
            map.put("rows",pager.getRecords());
            map.put("errorCode", "00");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }
    //            map.put("total",145);//数据总条数
//            ArrayList<HashMap<String,Object>> list = new ArrayList<>();
//            for(int i = 0; i < pageSize; i++){
//                HashMap<String,Object> obj = new HashMap<>();
//                obj.put("id",(i+page*pageSize+1));//学生id
//                obj.put("account","student"+(i+page*pageSize+1));//用户名
//                obj.put("password","123456");
//                obj.put("activity",i%2);
//                list.add(obj);
//            }
//            map.put("rows",list);//list集合

    /**
     * 加载公司帐号列表
     * @param page
     * @param pageSize
     * @param keyword
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/get/com-account", method = RequestMethod.GET)
    public String getComAccount(@RequestParam("pageNumber") Integer page,
                                @RequestParam("pageSize") Integer pageSize,
                                @RequestParam("searchText") String keyword,    //关键字用于搜索用户名和学生id
                                HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null
                && (request.getSession(true).getAttribute("userId")).equals("administrator")) {
            Page<HashMap<String,Object>> pager = administratorService.getUserPage(new Page<>(page,pageSize), COMPANY, keyword);
            map.put("total",pager.getTotal());
            map.put("rows",pager.getRecords());
            map.put("errorCode", "00");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 创建公司帐号
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/create-com",method = RequestMethod.POST) //创建公司账号，username,password,registerTime3个参数
    public String creatCompanyAccount(@RequestParam("username") String username,  //记得检查帐号是否存在，模仿UserController,记得在Company表中插入一条时间和其他信息
                                      @RequestParam("password") String password,
                                      HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null
                && (request.getSession(true).getAttribute("userId")).equals("administrator")) {
            if(administratorService.userRegister(username,password,COMPANY))
                map.put("errorCode", "00");
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);

    }

    /**
     * 解封用户帐号
     * @param userId
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/resume/{userId}", method = RequestMethod.GET)
    public String resumeUser(@PathVariable(value = "userId") Long userId,
                             HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null
                && (request.getSession(true).getAttribute("userId")).equals("administrator")) {
            if(administratorService.changeUserActivity(userId,1))
                map.put("errorCode", "00");
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 封帐号
     * @param userId
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/ban/{userId}", method = RequestMethod.GET)
    public String banUser(@PathVariable(value = "userId") Long userId,
                          HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null
                && (request.getSession(true).getAttribute("userId")).equals("administrator")) {
            if(administratorService.changeUserActivity(userId,0))
                map.put("errorCode", "00");
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 修改帐号密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/change-pass",method = RequestMethod.POST)
    public String changeStuPass(@RequestParam("userId") Long userId,
                                @RequestParam("password") String password,
                                HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null
                && (request.getSession(true).getAttribute("userId")).equals("administrator")) {
            if(administratorService.changeUserPassword(userId,password))
                map.put("errorCode", "00");
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }


    //关键字用于搜索姓名/id/昵称/手机号
    //id userId nickname realname sex age signature vacation github email phone hobby professional
    /**
     * 加载学习者账户信息列表
     * @param page
     * @param pageSize
     * @param keyword
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/get/stu-info", method = RequestMethod.GET)
    public String getStuInfo(@RequestParam("pageNumber") Integer page,
                             @RequestParam("pageSize") Integer pageSize,
                             @RequestParam("searchText") String keyword,    //关键字用于搜索姓名/id/昵称/手机号
                             HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null
                && (request.getSession(true).getAttribute("userId")).equals("administrator")) {
            Page<HashMap<String, Object>> pager = administratorService.getUserInfoPage(new Page<>(page, pageSize), STUDENT, keyword);
            map.put("total",pager.getTotal());//数据总条数
            map.put("rows",pager.getRecords());
            map.put("errorCode", "00");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }


    //registerTime website communication address introduction name field id userId
    //其中id是信息的id,userId是公司账号的id
    /**
     * 加载公司账户信息列表
     * @param page
     * @param pageSize
     * @param keyword
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/get/com-info", method = RequestMethod.GET)
    public String getComInfo(@RequestParam("pageNumber") Integer page,
                             @RequestParam("pageSize") Integer pageSize,
                             @RequestParam("searchText") String keyword,   //关键字用于搜索公司名称/id
                             HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null
                && (request.getSession(true).getAttribute("userId")).equals("administrator")) {
            Page<HashMap<String, Object>> pager = administratorService.getUserInfoPage(new Page<>(page, pageSize), COMPANY, keyword);
            map.put("total",pager.getTotal());//数据总条数
            map.put("rows",pager.getRecords());
            map.put("errorCode", "00");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }


    //修改以下信息
    // website communication address introduction name field
    //其中id是信息的id,userId是公司账号的id
    @RequestMapping(value = "/admin/com/change-info",method = RequestMethod.POST)
    public String changeComInfo(@RequestParam("id") Long id,
                                @RequestParam("field") String field,
                                @RequestParam("name") String name,
                                @RequestParam("introduction") String introduction,
                                @RequestParam("address") String address,
                                @RequestParam("communication") String communication,
                                @RequestParam("website") String website,
                                HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null
                && (request.getSession(true).getAttribute("userId")).equals("administrator")) {
            Company company = new Company();
            company.setId(id);
            company.setField(field);
            company.setName(name);
            company.setIntroduction(introduction);
            company.setAddress(address);
            company.setCommunication(communication);
            company.setWebsite(website);
            if(administratorService.changeCompanyInfo(company))
                map.put("errorCode", "00");
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    //修改学生信息
    // nickname realname sex age signature vacation github email phone hobby professional
    //参数和数据库中一一对应
    @RequestMapping(value = "/admin/change-info", method = RequestMethod.POST)
    public String changeStuInfo(@RequestParam("id") Long id,
                                @RequestParam("nickname") String nickname,
                                @RequestParam("realname") String realname,
                                @RequestParam("sex") String sex,
                                @RequestParam("age") Integer age,
                                @RequestParam("signature") String signature,
                                @RequestParam("vacation") String vacation,
                                @RequestParam("github") String github,
                                @RequestParam("email") String email,
                                @RequestParam("phone") String phone,
                                @RequestParam("hobby") String hobby,
                                @RequestParam("professional") String professional,
                                HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null
                && (request.getSession(true).getAttribute("userId")).equals("administrator")) {
            LearnerInfo learnerInfo = new LearnerInfo();
            learnerInfo.setId(id);
            learnerInfo.setNickname(nickname);
            learnerInfo.setRealname(realname);
            learnerInfo.setSex(sex);
            learnerInfo.setAge(age);
            learnerInfo.setSignature(signature);
            learnerInfo.setVacation(vacation);
            learnerInfo.setGithub(github);
            learnerInfo.setEmail(email);
            learnerInfo.setPhone(phone);
            learnerInfo.setHobby(hobby);
            learnerInfo.setProfessional(professional);
            if(administratorService.changeLearnerInfo(learnerInfo))
                map.put("errorCode", "00");
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }


    //resumeId companyProfessionId realname userId companyId companyName status
    /**
     * 加载学生投递简历信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/get/stu-employ", method = RequestMethod.GET)
    public String getStuEmploy(@RequestParam("pageNumber") Integer page,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam("searchText") String keyword, //关键字：学生id，学生真实姓名，公司id，公司名称，招聘信息title
                               HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null
                && (request.getSession(true).getAttribute("userId")).equals("administrator")) {
            Page<HashMap<String, Object>> pager = administratorService.getCompanyCvPage(new Page<>(page, pageSize), keyword);
            map.put("total",pager.getTotal());//数据总条数
            map.put("rows",pager.getRecords());
            map.put("errorCode", "00");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    //resumeId companyProfessionId realname userId companyId companyName status
    /**
     * 加载公司招聘邀请列表
     * @param page
     * @param pageSize
     * @param keyword
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/get/stu-welcome", method = RequestMethod.GET)
    public String getStuWelcome(@RequestParam("pageNumber") Integer page,
                                @RequestParam("pageSize") Integer pageSize,
                                @RequestParam("searchText") String keyword, //关键字：学生id，学生真实姓名，公司id，公司名称，招聘信息title
                                HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null
                && (request.getSession(true).getAttribute("userId")).equals("administrator")) {
            Page<HashMap<String, Object>> pager = administratorService.getCompanyInvitationPage(new Page<>(page, pageSize), keyword);
            map.put("total",pager.getTotal());//数据总条数
            map.put("rows",pager.getRecords());
            map.put("errorCode", "00");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

}

