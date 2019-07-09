package com.example.worknet.common.persistence.affair.user.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.worknet.common.constant.Const;
import com.example.worknet.common.constant.UserConst;
import com.example.worknet.common.persistence.affair.user.serivce.CompanyService;
import com.example.worknet.common.persistence.affair.user.serivce.UserService;
import com.example.worknet.common.persistence.template.modal.Company;
import com.example.worknet.common.persistence.template.modal.CompanyProfession;
import com.example.worknet.core.utils.file.HttpFileUnils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;


/**
 * <p>
 * 公司信息表 前端控制器
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-05-06
 */
@Controller
@ResponseBody
public class CompanyController {

    private final static Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    /**
     * 公司头像预览
     * @param companyId
     * @param request
     * @return
     */
    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/get/company/avatar/{cid}", method = RequestMethod.GET)
    public ResponseEntity getCoursePic(@PathVariable(value = "cid") long companyId,
                                       HttpServletRequest request) {
        String strDirPath = request.getSession().getServletContext().getRealPath("/");
        logger.info(strDirPath);
        try {
            return ResponseEntity.ok(userService.getCompanyAvatar(companyId,strDirPath));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 加载笔试公司的头像（作为笔试和求职logo)
     * @param companyId
     * @param request
     * @return
     */
    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/get/company/logo/{cid}", method = RequestMethod.GET)
    public ResponseEntity getCompanyLogo(@PathVariable(value = "cid") long companyId,
                                         HttpServletRequest request) {
        return getCoursePic(companyId, request);
    }

    /**
     * 加载公司信息
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/get/companyInfo/{companyId}", method = RequestMethod.GET)
    public String getCompanyInfo(@PathVariable(value = "companyId") Long companyId){
        HashMap<String,Object> map = new HashMap<>();
        map.put("returnObject",companyService.getCompanyInfo(companyId));
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }

    /***********************************下方为公司管理层接口*********************************************/
    /**
     * 加载公司头像
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/get/myAvatar", method = RequestMethod.GET)
    public ResponseEntity getCoursePic(HttpServletRequest request) {
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            String strDirPath = request.getSession().getServletContext().getRealPath("/");
            logger.info(strDirPath);
            try {
                return ResponseEntity.ok(userService.getAvatar(userId,strDirPath));
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        } else
            return ResponseEntity.badRequest().build();
    }

    /**
     * 加载公司帐号信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/get/info", method = RequestMethod.GET)
    public String getCompanyId(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            if(userService.selectById(userId).getRole().equals(UserConst.COMPANY.getState())){
                map.put("returnObject",companyService.getCompanyInfoByUserId(userId));
                map.put("errorCode","00");
            } else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 修改公司帐号信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/change-info", method = RequestMethod.POST)
    public String changeComInfo(@RequestParam(value = "website", required = false) String website,
                                @RequestParam(value = "communication", required = false) String communication,
                                @RequestParam(value = "address", required = false) String address,
                                @RequestParam(value = "introduction", required = false) String introduction,
                                @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "field", required = false) String field,
                                HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            if(userService.selectById(userId).getRole().equals(UserConst.COMPANY.getState())){
                Company company=new Company();
                company.setUserId(userId);
                company.setWebsite(website);
                company.setCommunication(communication);
                company.setAddress(address);
                company.setIntroduction(introduction);
                company.setName(name);
                company.setField(field);
                if(companyService.updateCompanyInfo(company))
                    map.put("errorCode","00");
                else
                    map.put("errorCode", "error");
            } else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 获取登录公司接受的所有简历
     * @param page
     * @param pageSize
     * @param keyword    关键字：学生id，学生真实姓名
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/get/resume", method = RequestMethod.GET)
    public String getResume(@RequestParam(value = "pageNumber", required = false) Integer page,
                            @RequestParam(value = "pageSize", required = false) Integer pageSize,
                            @RequestParam(value = "searchText", required = false) String keyword,   //关键字：学生id，学生真实姓名
                            HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            if(userService.selectById(userId).getRole().equals(UserConst.COMPANY.getState())) {
                Page<HashMap<String, Object>> pager = companyService.getResumePage(new Page<>(page, pageSize), userId, keyword);
                map.put("total", pager.getTotal());
                map.put("rows", pager.getRecords());
                map.put("errorCode", "00");
            }else
                map.put("errorCode", "error");
        }else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 同意学习者简历
     * @param companyCvId
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/agree/{companyCvId}", method = RequestMethod.GET)
    public String agreeResume(@PathVariable(value = "companyCvId") Long companyCvId,
                              HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            if(userService.selectById(userId).getRole().equals(UserConst.COMPANY.getState())
                    && companyService.changeResumeStatus(companyCvId, 1, userId))
                map.put("errorCode","00");
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 拒绝学习者简历
     * @param companyCvId
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/reject/{companyCvId}", method = RequestMethod.GET)
    public String rejectResume(@PathVariable(value = "companyCvId") Long companyCvId,
                               HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            if(userService.selectById(userId).getRole().equals(UserConst.COMPANY.getState())
                    && companyService.changeResumeStatus(companyCvId, 2, userId))
                map.put("errorCode","00");
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 公司管理加载学习者信息列表
     * @param page
     * @param pageSize
     * @param keyword
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/get/stu-info", method = RequestMethod.GET)
    public String getStuInfo(@RequestParam(value = "pageNumber", required = false) Integer page,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize,
                             @RequestParam(value = "searchText", required = false) String keyword,  //关键字用于搜索姓名/id/昵称/手机号
                             HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            if(userService.selectById(userId).getRole().equals(UserConst.COMPANY.getState())){
                Page<HashMap<String, Object>> pager = companyService.getLearnerInfoPage(new Page<>(page, pageSize), keyword);
                map.put("total", pager.getTotal());
                map.put("rows", pager.getRecords());
                map.put("errorCode", "00");
            }else
                map.put("errorCode", "error");
        }else
            map.put("errorCode", "error");

        return JSON.toJSONString(map);
    }

    /**
     * 公司管理加载招聘简略信息列表（非分页）
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/get/employ-list", method = RequestMethod.GET)
    public String getComEmployList(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        if (request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long) request.getSession(true).getAttribute("userId");
            if (userService.selectById(userId).getRole().equals(UserConst.COMPANY.getState())) {
                map.put("returnObject", companyService.getComEmployList(userId));
                map.put("errorCode", "00");
            }else
                map.put("errorCode", "error");
        }else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 公司管理发起邀请招聘
     * @param studentId
     * @param companyProfessionId
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/welcome", method = RequestMethod.POST)
    public String welcomeStudent(@RequestParam(value = "studentId", required = false) Long studentId,
                                 @RequestParam(value = "companyProfessionId", required = false) Long companyProfessionId,
                                 HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if (request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long) request.getSession(true).getAttribute("userId");
            if (userService.selectById(userId).getRole().equals(UserConst.COMPANY.getState())
                    && companyService.welcomeStudent(userId, studentId, companyProfessionId)) {
                map.put("errorCode", "00");
            } else
                map.put("errorCode", "error");
        }else
            map.put("errorCode","error");
        return JSON.toJSONString(map);
    }

    /**
     * 公司管理加载招聘邀请列表
     * @param page
     * @param pageSize
     * @param keyword   关键字用于搜索
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/get/welcome-info", method = RequestMethod.GET)
    public String getWelcomeInfo(@RequestParam(value = "pageNumber", required = false) Integer page,
                                 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(value = "searchText", required = false) String keyword,
                                 HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if (request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long) request.getSession(true).getAttribute("userId");
            if (userService.selectById(userId).getRole().equals(UserConst.COMPANY.getState())) {
                Page<HashMap<String, Object>> pager = companyService.getWelcomePage(new Page<>(page,pageSize), userId, keyword);
                map.put("total",pager.getTotal());
                map.put("rows",pager.getRecords());
                map.put("errorCode","00");
            }else
                map.put("errorCode","error");
        }else
            map.put("errorCode","error");
        return JSON.toJSONString(map);

    }

    /**
     * 公司管理加载招聘信息列表
     * @param page
     * @param pageSize
     * @param keyword   关键字用于搜索 招聘标题|学生id|内容|要求
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/get/employ-info", method = RequestMethod.GET)
    public String getEmployInfo(@RequestParam(value = "pageNumber", required = false) Integer page,
                                @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                @RequestParam(value = "searchText", required = false) String keyword,
                                HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if (request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long) request.getSession(true).getAttribute("userId");
            if (userService.selectById(userId).getRole().equals(UserConst.COMPANY.getState())) {
                Page<HashMap<String, Object>> pager = companyService.getCompanyEmployPage(new Page<>(page, pageSize), userId.toString(), keyword);
                map.put("total",pager.getTotal());
                map.put("rows",pager.getRecords());
                map.put("errorCode","00");
            }else
                map.put("errorCode","error");
        }else
            map.put("errorCode","error");
        return JSON.toJSONString(map);
    }

    /**
     * 公司管理结束招聘
     * @param companyProfessionId
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/end/employ/{companyProfessionId}", method = RequestMethod.GET)
    public String endEmploy(@PathVariable(value = "companyProfessionId") Long companyProfessionId,
                            HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if (request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long) request.getSession(true).getAttribute("userId");
            if (userService.selectById(userId).getRole().equals(UserConst.COMPANY.getState())
                    && companyService.endEmploy(companyProfessionId)) {
                map.put("errorCode","00");
            }else
                map.put("errorCode","error");
        }else
            map.put("errorCode","error");
        return JSON.toJSONString(map);
    }

    /**
     * 公司管理修改公司招聘信息
     * @param id
     * @param companyId
     * @param professionTypeId
     * @param title
     * @param introduction
     * @param requirement
     * @param salary
     * @param location
     * @param isPractice
     * @param duration
     * @param chanceToFormal
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/employ/change-info", method = RequestMethod.POST)
    public String changeEmployInfo(@RequestParam(value = "id", required = false) Long id,
                                   @RequestParam(value = "companyId", required = false) Long companyId ,
                                   @RequestParam(value = "professionTypeId", required = false) Long professionTypeId ,
                                   @RequestParam(value = "title", required = false) String title,
                                   @RequestParam(value = "introduction", required = false) String introduction ,
                                   @RequestParam(value = "requirement", required = false) String requirement ,
                                   @RequestParam(value = "salary", required = false) String salary ,
                                   @RequestParam(value = "location", required = false) String location ,
                                   @RequestParam(value = "isPractice", required = false) Integer isPractice ,
                                   @RequestParam(value = "duration", required = false) String duration,
                                   @RequestParam(value = "chanceToFormal", required = false) String chanceToFormal,
                                   HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>();
        if (request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long) request.getSession(true).getAttribute("userId");
            if (userService.selectById(userId).getRole().equals(UserConst.COMPANY.getState())) {
                CompanyProfession companyProfession = new CompanyProfession();
                companyProfession.setId(id);
                companyProfession.setCompanyId(companyId);
                companyProfession.setProfessionTypeId(professionTypeId);
                companyProfession.setTitle(title);
                companyProfession.setIntroduction(introduction);
                companyProfession.setRequirement(requirement);
                companyProfession.setSalary(salary);
                companyProfession.setLocation(location);
                companyProfession.setIsPractice(isPractice);
                companyProfession.setDuration(duration);
                companyProfession.setChanceToFormal(chanceToFormal);
                if(companyService.changeEmployInfo(companyProfession, userId))
                    map.put("errorCode", "00");
                else
                    map.put("errorCode", "error");
            } else
                map.put("errorCode", "error");
        }else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 公司管理创建公司招聘信息
     * @param professionTypeId
     * @param title
     * @param introduction
     * @param requirement
     * @param salary
     * @param location
     * @param isPractice
     * @param duration
     * @param chanceToFormal
     * @param request
     * @return
     */
    @RequestMapping(value = "/company/employ/create-info", method = RequestMethod.POST)
    public String createEmployInfo(@RequestParam(value = "professionTypeId", required = false) Long professionTypeId ,
                                   @RequestParam(value = "title", required = false) String title,
                                   @RequestParam(value = "introduction", required = false) String introduction ,
                                   @RequestParam(value = "requirement", required = false) String requirement ,
                                   @RequestParam(value = "salary", required = false) String salary ,
                                   @RequestParam(value = "location", required = false) String location ,
                                   @RequestParam(value = "isPractice", required = false) Integer isPractice ,
                                   @RequestParam(value = "duration", required = false) String duration,
                                   @RequestParam(value = "chanceToFormal", required = false) String chanceToFormal,
                                   HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>();
        if (request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long) request.getSession(true).getAttribute("userId");
            if (userService.selectById(userId).getRole().equals(UserConst.COMPANY.getState())) {
                CompanyProfession companyProfession = new CompanyProfession();
                companyProfession.setProfessionTypeId(professionTypeId);
                companyProfession.setTitle(title);
                companyProfession.setIntroduction(introduction);
                companyProfession.setRequirement(requirement);
                companyProfession.setSalary(salary);
                companyProfession.setLocation(location);
                companyProfession.setIsPractice(isPractice);
                companyProfession.setDuration(duration);
                companyProfession.setChanceToFormal(chanceToFormal);
                if(companyService.createEmployInfo(companyProfession, userId))
                    map.put("errorCode", "00");
                else
                    map.put("errorCode", "error");
            }else
                map.put("errorCode", "error");
        }else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

}