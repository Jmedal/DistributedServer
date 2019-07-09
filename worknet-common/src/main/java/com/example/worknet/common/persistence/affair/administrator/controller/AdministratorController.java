package com.example.worknet.common.persistence.affair.administrator.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.worknet.common.persistence.affair.administrator.service.AdministratorService;
import com.example.worknet.common.persistence.template.modal.Company;
import com.example.worknet.common.persistence.template.modal.LearnerInfo;
import com.example.worknet.common.persistence.template.modal.ProfessionType;
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
     * 管理员加载学生帐号列表
     * @param page
     * @param pageSize
     * @param keyword   关键字用于搜索 用户名|学生id
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/get/stu-account", method = RequestMethod.GET)
    public String getStuAccount(@RequestParam(value = "pageNumber") Integer page,
                                @RequestParam(value = "pageSize") Integer pageSize,
                                @RequestParam(value = "searchText", required = false) String keyword,
                                HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user") != null
                && (request.getSession(true).getAttribute("user")).equals("administrator")) {
            Page<HashMap<String,Object>> pager = administratorService.getUserPage(new Page<>(page,pageSize), STUDENT, keyword);
            map.put("total",pager.getTotal());
            map.put("rows",pager.getRecords());
            map.put("errorCode", "00");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 管理员加载公司帐号列表
     * @param page
     * @param pageSize
     * @param keyword 关键字用于搜索 用户名|公司id
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/get/com-account", method = RequestMethod.GET)
    public String getComAccount(@RequestParam(value = "pageNumber") Integer page,
                                @RequestParam(value = "pageSize") Integer pageSize,
                                @RequestParam(value = "searchText", required = false) String keyword,    //关键字用于搜索用户名和学生id
                                HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user") != null
                && (request.getSession(true).getAttribute("user")).equals("administrator")) {
            Page<HashMap<String,Object>> pager = administratorService.getUserPage(new Page<>(page,pageSize), COMPANY, keyword);
            map.put("total",pager.getTotal());
            map.put("rows",pager.getRecords());
            map.put("errorCode", "00");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 管理员创建公司帐号
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/create-com",method = RequestMethod.POST)
    public String creatCompanyAccount(@RequestParam(value = "username") String username,
                                      @RequestParam(value = "password") String password,
                                      HttpServletRequest request){
        System.out.println(username);
        System.out.println(password);
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user") != null
                && (request.getSession(true).getAttribute("user")).equals("administrator")) {
            if(administratorService.userRegister(username, password, COMPANY))
                map.put("errorCode", "00");
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);

    }

    /**
     * 管理员解封用户帐号
     * @param userId
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/resume/{userId}", method = RequestMethod.GET)
    public String resumeUser(@PathVariable(value = "userId") Long userId,
                             HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user") != null
                && (request.getSession(true).getAttribute("user")).equals("administrator")) {
            if(administratorService.changeUserActivity(userId,1))
                map.put("errorCode", "00");
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 管理员封用户帐号
     * @param userId
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/ban/{userId}", method = RequestMethod.GET)
    public String banUser(@PathVariable(value = "userId") Long userId,
                          HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user") != null
                && (request.getSession(true).getAttribute("user")).equals("administrator")) {
            if(administratorService.changeUserActivity(userId,0))
                map.put("errorCode", "00");
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 管理员修改帐号密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/change-pass",method = RequestMethod.POST)
    public String changeStuPass(@RequestParam(value = "userId") Long userId,
                                @RequestParam(value = "password") String password,
                                HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user") != null
                && (request.getSession(true).getAttribute("user")).equals("administrator")) {
            if(administratorService.changeUserPassword(userId,password))
                map.put("errorCode", "00");
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 管理员加载学习者账户信息列表
     * @param page
     * @param pageSize
     * @param keyword   关键字用于搜索 姓名|id|昵称|手机号
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/get/stu-info", method = RequestMethod.GET)
    public String getStuInfo(@RequestParam(value = "pageNumber") Integer page,
                             @RequestParam(value = "pageSize") Integer pageSize,
                             @RequestParam(value = "searchText", required = false) String keyword,
                             HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user") != null
                && (request.getSession(true).getAttribute("user")).equals("administrator")) {
            Page<HashMap<String, Object>> pager = administratorService.getUserInfoPage(new Page<>(page, pageSize), STUDENT, keyword);
            map.put("total", pager.getTotal());
            map.put("rows", pager.getRecords());
            map.put("errorCode", "00");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 管理员加载公司账户信息列表
     * @param page
     * @param pageSize
     * @param keyword   关键字用于搜索 公司名称|公司id
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/get/com-info", method = RequestMethod.GET)
    public String getComInfo(@RequestParam(value = "pageNumber") Integer page,
                             @RequestParam(value = "pageSize") Integer pageSize,
                             @RequestParam(value = "searchText", required = false) String keyword,
                             HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user") != null
                && (request.getSession(true).getAttribute("user")).equals("administrator")) {
            Page<HashMap<String, Object>> pager = administratorService.getUserInfoPage(new Page<>(page, pageSize), COMPANY, keyword);
            map.put("total",pager.getTotal());//数据总条数
            map.put("rows",pager.getRecords());
            map.put("errorCode", "00");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 管理员修改公司信息
     * @param id
     * @param field
     * @param name
     * @param introduction
     * @param address
     * @param communication
     * @param website
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/com/change-info",method = RequestMethod.POST)
    public String changeComInfo(@RequestParam(value = "id") Long id,
                                @RequestParam(value = "field", required = false) String field,
                                @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "introduction", required = false) String introduction,
                                @RequestParam(value = "address", required = false) String address,
                                @RequestParam(value = "communication", required = false) String communication,
                                @RequestParam(value = "website", required = false) String website,
                                HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user") != null
                && (request.getSession(true).getAttribute("user")).equals("administrator")) {
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

    /**
     * 管理员修改学生信息
     * @param id
     * @param nickname
     * @param realname
     * @param sex
     * @param age
     * @param signature
     * @param vacation
     * @param github
     * @param email
     * @param phone
     * @param hobby
     * @param professional
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/change-info", method = RequestMethod.POST)
    public String changeStuInfo(@RequestParam(value = "id") Long id,
                                @RequestParam(value = "nickname", required = false) String nickname,
                                @RequestParam(value = "realname", required = false) String realname,
                                @RequestParam(value = "sex", required = false) String sex,
                                @RequestParam(value = "age", required = false) Integer age,
                                @RequestParam(value = "signature", required = false) String signature,
                                @RequestParam(value = "vacation", required = false) String vacation,
                                @RequestParam(value = "github", required = false) String github,
                                @RequestParam(value = "email", required = false) String email,
                                @RequestParam(value = "phone", required = false) String phone,
                                @RequestParam(value = "hobby", required = false) String hobby,
                                @RequestParam(value = "professional", required = false) String professional,
                                HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user") != null
                && (request.getSession(true).getAttribute("user")).equals("administrator")) {
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

    /**
     * 管理员加载学生投递简历信息
     * @param page
     * @param pageSize
     * @param keyword   关键字用于搜索 学生id|学生真实姓名|公司id|公司名称|招聘信息title
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/get/stu-employ", method = RequestMethod.GET)
    public String getStuEmploy(@RequestParam(value = "pageNumber") Integer page,
                               @RequestParam(value = "pageSize") Integer pageSize,
                               @RequestParam(value = "searchText", required = false) String keyword,
                               HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user") != null
                && (request.getSession(true).getAttribute("user")).equals("administrator")) {
            Page<HashMap<String, Object>> pager = administratorService.getCompanyCvPage(new Page<>(page, pageSize), keyword);
            map.put("total",pager.getTotal());
            map.put("rows",pager.getRecords());
            map.put("errorCode", "00");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    //resumeId companyProfessionId realname userId companyId companyName status
    /**
     * 管理员加载公司招聘邀请列表
     * @param page
     * @param pageSize
     * @param keyword    关键字用于搜索 学生id|学生真实姓名|公司id|公司名称|招聘信息title
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/get/stu-welcome", method = RequestMethod.GET)
    public String getStuWelcome(@RequestParam(value = "pageNumber") Integer page,
                                @RequestParam(value = "pageSize") Integer pageSize,
                                @RequestParam(value = "searchText", required = false) String keyword, //关键字：学生id，学生真实姓名，公司id，公司名称，招聘信息title
                                HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user") != null
                && (request.getSession(true).getAttribute("user")).equals("administrator")) {
            Page<HashMap<String, Object>> pager = administratorService.getCompanyInvitationPage(new Page<>(page, pageSize), keyword);
            map.put("total",pager.getTotal());
            map.put("rows",pager.getRecords());
            map.put("errorCode", "00");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 管理员加载公司招聘信息列表
     * @param page
     * @param pageSize
     * @param keyword   关键字用于搜索 招聘id|招聘标题|公司id|公司名称|职业类型id|职业类型名称|公司所在地|内容|要求
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/get/com-employ", method = RequestMethod.GET)
    public String adminGetEmployInfo(@RequestParam(value = "pageNumber") Integer page,
                                     @RequestParam(value = "pageSize") Integer pageSize,
                                     @RequestParam(value = "searchText", required = false) String keyword,
                                     HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user") != null
                && (request.getSession(true).getAttribute("user")).equals("administrator")) {
            Page<HashMap<String, Object>> pager = administratorService.getCompanyProfessionPage(new Page<>(page, pageSize), keyword);
            map.put("total",pager.getTotal());
            map.put("rows",pager.getRecords());
            map.put("errorCode", "00");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 管理员获取职业列表
     * @param page
     * @param pageSize
     * @param keyword   关键字用于搜索 职业类型id|职业类型名称
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/get/profession", method = RequestMethod.GET)
    public String adminGetProfession(@RequestParam(value = "pageNumber") Integer page,
                                     @RequestParam(value = "pageSize") Integer pageSize,
                                     @RequestParam(value = "searchText", required = false) String keyword,
                                     HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user") != null
                && (request.getSession(true).getAttribute("user")).equals("administrator")) {
            Page<HashMap<String, Object>> pager = administratorService.getProfessionTypePage(new Page<>(page, pageSize), keyword);
            map.put("total",pager.getTotal());
            map.put("rows",pager.getRecords());
            map.put("errorCode", "00");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 管理员修改职业
     * @param professionTypeId
     * @param professionTypeName
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/change-profession",method = RequestMethod.POST)
    public String adminChangeProfession(@RequestParam(value = "professionTypeId") Long professionTypeId,
                                        @RequestParam(value = "professionType") String professionTypeName,
                                        HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user") != null
                && (request.getSession(true).getAttribute("user")).equals("administrator")) {
            ProfessionType professionType = new ProfessionType();
            professionType.setId(professionTypeId);
            professionType.setTypeName(professionTypeName);
            if(administratorService.changeProfessionType(professionType))
                map.put("errorCode", "00");
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 管理员创建职业
     * @param professionTypeName
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/create-profession",method = RequestMethod.POST)
    public String adminCreateProfession(@RequestParam(value = "professionType") String professionTypeName,
                                        HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user") != null
                && (request.getSession(true).getAttribute("user")).equals("administrator")) {
            ProfessionType professionType = new ProfessionType();
            professionType.setTypeName(professionTypeName);
            if(administratorService.changeProfessionType(professionType))
                map.put("errorCode", "00");
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 管理员删除职业
     * @param professionTypeId
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/delete-profession",method = RequestMethod.POST)
    public String adminDeleteProfession(@RequestParam(value = "professionTypeId") Long professionTypeId,
                                        HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("user") != null
                && (request.getSession(true).getAttribute("user")).equals("administrator")) {
            try {
                if(administratorService.deleteProfessionType(professionTypeId))
                    map.put("errorCode", "00");
                else
                    map.put("errorCode", "error");
            }catch (Exception e){
                map.put("errorCode", "error");
            }
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

}

