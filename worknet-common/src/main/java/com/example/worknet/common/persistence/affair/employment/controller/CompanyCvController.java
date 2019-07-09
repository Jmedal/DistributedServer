package com.example.worknet.common.persistence.affair.employment.controller;

import com.alibaba.fastjson.JSON;
import com.example.worknet.common.constant.Const;
import com.example.worknet.common.persistence.affair.employment.service.CompanyCvService;
import com.example.worknet.common.persistence.template.modal.CompanyCv;
import com.example.worknet.core.utils.date.DateUtil;
import com.example.worknet.core.utils.file.HttpFileUnils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;

/**
 * <p>
 * 简历表 前端控制器
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
@Controller
@ResponseBody
public class CompanyCvController {

    private final static Logger logger = LoggerFactory.getLogger(CompanyCvController.class);

    @Autowired
    private CompanyCvService companyCvService;

    @Autowired
    private final ResourceLoader resourceLoader;

    @Autowired
    public CompanyCvController(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    /**
     * 用户投递简历
     * @param request
     * @param name
     * @param name
     * @param sex
     * @param birth
     * @param nativePlace
     * @param identity
     * @param qualification
     * @param specialty
     * @param university
     * @param tel
     * @param experience
     * @param mailbox
     * @param introduction
     * @param diploma
     * @param currentLocation
     * @param inJobTime
     * @return
     */
    @RequestMapping(value = "/resume/deliver", method = RequestMethod.POST)
    public String deliverResume(HttpServletRequest request,
                                @RequestParam(value = "employId") Long companyProfessionId,
                                @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "sex", required = false) Integer sex,
                                @RequestParam(value = "birth", required = false) String birth,
                                @RequestParam(value = "nativePlace", required = false) String nativePlace,
                                @RequestParam(value = "identity", required = false) String identity,
                                @RequestParam(value = "qualification", required = false) String qualification,
                                @RequestParam(value = "speciality", required = false) String specialty,
                                @RequestParam(value = "university", required = false) String university,
                                @RequestParam(value = "tel", required = false) String tel,
                                @RequestParam(value = "experience", required = false) String experience,
                                @RequestParam(value = "mailbox", required = false) String mailbox,
                                @RequestParam(value = "introduction", required = false) String introduction,
                                @RequestParam(value = "diploma", required = false) String diploma,
                                @RequestParam(value = "currentLocation", required = false) String currentLocation,
                                @RequestParam(value = "inJobTime", required = false) String inJobTime,
                                @RequestParam(value = "headPath", required = false) String headPath){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long) request.getSession(true).getAttribute("userId");
            CompanyCv companyCv=new CompanyCv();
            companyCv.setCompanyProfessionId(companyProfessionId);
            companyCv.setUserId(userId);
            companyCv.setName(name);
            companyCv.setSex(sex);
            companyCv.setBirth(DateUtil.getSqlDateTime(birth,DateUtil.YMD_TIME));
            companyCv.setNativePlace(nativePlace);
            companyCv.setIdentity(identity);
            companyCv.setQualification(qualification);
            companyCv.setSpeciality(specialty);
            companyCv.setUniversity(university);
            companyCv.setTel(tel);
            companyCv.setExperience(experience);
            companyCv.setMailbox(mailbox);
            companyCv.setIntroduction(introduction);
            companyCv.setDiploma(diploma);
            companyCv.setCurrentLocation(currentLocation);
            companyCv.setInJobTime(inJobTime);
            companyCv.setHeadPath(headPath);
            companyCv.setStatus(0);
            Long companyCvId = companyCvService.deliverCompanyCv(companyCv);
            if(companyCvId != null){
                map.put("returnObject", companyCvId);
                map.put("errorCode", "00");
            }else
                map.put("errorCode", "error");
        }else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 加载学习者简历的头像
     * @param companyCvId
     * @param request
     * @return
     */
    @RequestMapping(value = "/get/resume/avatar/{companyCvId}", method = RequestMethod.POST)
    public ResponseEntity getAvatar(@PathVariable(value = "companyCvId") Long companyCvId,
                                    HttpServletRequest request) {
        try {
            String headPath = companyCvService.getCompanyCvAvatarPath(companyCvId);
            logger.info(headPath);
            String strDirPath = request.getSession().getServletContext().getRealPath("/");
            logger.info(strDirPath);
            return ResponseEntity.ok(HttpFileUnils.responseFile(resourceLoader, headPath, strDirPath));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 上传或者修改简历头像
     * @param companyCvId
     * @param request
     * @return
     */
    @RequestMapping(value = "/resume/deliver/avatar/{companyCvId}", method = RequestMethod.POST)
    public String deliverAvatar(@PathVariable(value = "companyCvId") Long companyCvId,
                                MultipartHttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            String savePath = Const.FILE_SEPARATOR + Const.COMPANY_CV_HEAD_PATH + Const.FILE_SEPARATOR + Calendar.getInstance().get(Calendar.YEAR);
            String headPath = HttpFileUnils.requestFile(request, savePath, companyCvService.selectById(companyCvId).getHeadPath(), null);
            if(headPath != null && companyCvService.setCompanyCvAvatarPath(companyCvId, headPath)){
                map.put("errorCode", "00");
            }else
                map.put("errorCode", "error");
        }else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 加载用户简历
     * @param companyCvId
     * @param request
     * @return
     */
    @RequestMapping(value = "/resume/get/{companyCvId}", method = RequestMethod.GET)
    public String getResume(@PathVariable(value = "companyCvId") Long companyCvId, HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long) request.getSession(true).getAttribute("userId");
            HashMap<String,Object> companyCv = companyCvService.getCompanyCvInfo(companyCvId, userId);
            if(companyCv != null){
                map.put("returnObject", companyCv);
                map.put("errorCode", "00");
            }else
                map.put("errorCode", "error");
        }else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 加载用户投递的简历
     * @param page
     * @param pageSize
     * @param keyword
     * @param request
     * @return
     */
    @RequestMapping(value = "/get/my-resume", method = RequestMethod.GET)
    public String getMyResume(@RequestParam(value = "pageNumber") Integer page,
                              @RequestParam(value = "pageSize") Integer pageSize,
                              @RequestParam(value = "searchText", required = false) String keyword,
                              HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null){
            Long userId = (long) request.getSession(true).getAttribute("userId");
            System.out.println(keyword);
            Page<HashMap<String,Object>> pager = companyCvService.getCompanyCvPage(new Page<>(page,pageSize), userId, keyword);
            map.put("total",pager.getTotal());
            map.put("rows",pager.getRecords());
            map.put("errorCode", "00");
        }else {
            map.put("errorCode", "error");
        }
        return JSON.toJSONString(map);
    }
}

