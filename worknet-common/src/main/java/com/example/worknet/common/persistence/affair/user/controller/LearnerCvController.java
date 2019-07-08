package com.example.worknet.common.persistence.affair.user.controller;

import com.alibaba.fastjson.JSON;
import com.example.worknet.common.constant.UserConst;
import com.example.worknet.common.persistence.affair.user.serivce.LearnerCvService;
import com.example.worknet.common.persistence.affair.user.serivce.UserService;
import com.example.worknet.common.persistence.template.modal.LearnerCv;
import com.example.worknet.common.persistence.template.modal.User;
import com.example.worknet.core.utils.Date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
public class LearnerCvController {

    private final static Logger logger = LoggerFactory.getLogger(LearnerCvController.class);


    @Autowired
    private UserService userService;

    @Autowired
    private LearnerCvService learnerCvService;

    /**
     * 显示默认的简历模板(未结束)
     * @param request
     * @return
     */
    @RequestMapping(value = "/getFavorResumeMode", method = RequestMethod.GET)
    public String getFavorResumeMode(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            User user = userService.selectById(userId);
            if(user.getRole().equals(UserConst.STUDENT.getState())){
                map.put("returnObject", learnerCvService.getLearnerCvInfo(userId));
                map.put("errorCode", "00");
            }
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }
    //如果没有默认的简历模板，则返回空
//    HashMap<String, Object> map = new HashMap<>();
//        map.put("errorCode","00");
//    HashMap<String,Object> obj = new HashMap<>();
//        obj.put("id",55);//简历模板的id！！！
//        obj.put("resumeName","简历名称");//简历的名字
//        obj.put("learnerId",131);
//        obj.put("name","张萨姆");
//        obj.put("sex",1);
//        obj.put("birth","1997-05");
//        obj.put("nativePlace","上海市");
//        obj.put("identity","124124124");
//        obj.put("qualification",3);//存0-7的数字
//        obj.put("speciality","计算机");
//        obj.put("university","上海大学");
//        obj.put("tel","18888888888");
//        obj.put("experience","2008-2011 白宫洗碗三年");//格式为字符串，存入格式为时间+内容
//        obj.put("mailbox","88888888@qq.com");
//        obj.put("introduction","我是一个xxxxxx\n\n哈哈哈");
//        obj.put("diploma","2018-03-21 洗碗全国大奖");
//        obj.put("headPath","http://www.baidu.com");
//        map.put("returnObject",obj);


    /**
     * 根据简历模板id,加载学习者简历的头像
     * @param resumeId
     * @param request
     * @return
     */
    @RequestMapping(value = "/get/resume-mode/avatar/{resumeId}", method = RequestMethod.GET)
    public ResponseEntity getModeAvatar(@PathVariable(value = "resumeId") Long resumeId,
                                                HttpServletRequest request){
        String strDirPath = request.getSession().getServletContext().getRealPath("/");
        try {
            return ResponseEntity.ok(learnerCvService.getLearnerCvAvatar(resumeId,strDirPath));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 学习者创建简历模版(未结束)
     * @param resumeName
     * @param name
     * @param sex
     * @param birth
     * @param nativePlace
     * @param identity
     * @param qualification
     * @param speciality
     * @param university
     * @param tel
     * @param experience
     * @param mailbox
     * @param introduction
     * @param diploma
     * @param lastEditTime
     * @param request
     * @return
     */
    @RequestMapping(value = "/create-resume-mode", method = RequestMethod.POST)
    public String createResumeMode(@RequestParam(value = "resumeName") String resumeName,
                                   @RequestParam(value = "name") String name,
                                   @RequestParam(value = "sex") Integer sex,
                                   @RequestParam(value = "birth") String birth,
                                   @RequestParam(value = "nativePlace") String nativePlace,
                                   @RequestParam(value = "identity") String identity,
                                   @RequestParam(value = "qualification") String qualification,
                                   @RequestParam(value = "speciality") String speciality,
                                   @RequestParam(value = "university") String university,
                                   @RequestParam(value = "tel") String tel,
                                   @RequestParam(value = "experience") String experience,
                                   @RequestParam(value = "mailbox") String mailbox,
                                   @RequestParam(value = "introduction") String introduction,
                                   @RequestParam(value = "diploma") String diploma,
                                   @RequestParam(value = "lastEditTime") String lastEditTime,
                                   HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            if(userService.selectById(userId).getRole().equals(UserConst.STUDENT.getState())) {
                LearnerCv learnerCv = new LearnerCv();
                learnerCv.setResumeName(resumeName);
                learnerCv.setName(name);
                learnerCv.setLearnerId(userId);
                learnerCv.setSex(sex);
                learnerCv.setBirth(DateUtil.getSqlDateTime(birth,DateUtil.YMD_TIME));
                learnerCv.setNativePlace(nativePlace);
                learnerCv.setIdentity(identity);
                learnerCv.setQualification(qualification);
                learnerCv.setSpeciality(speciality);
                learnerCv.setUniversity(university);
                learnerCv.setTel(tel);
                learnerCv.setExperience(experience);
                learnerCv.setMailbox(mailbox);
                learnerCv.setIntroduction(introduction);
                learnerCv.setDiploma(diploma);
                learnerCv.setLastEditTime(DateUtil.getSqlDateTime(lastEditTime,DateUtil.YMDHMS_TIME));
                Long learnerCvId = learnerCvService.createLearnerCv(learnerCv,userId);
                if (learnerCvId != null) {
                    map.put("returnObject", learnerCvId);
                    map.put("errorCode", "00");
                } else
                    map.put("errorCode", "error");
            }
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }


    /**
     * 加载学习者简历模版列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/get/resume-mode/list", method = RequestMethod.GET)
    public String getResumeModeList(HttpServletRequest request) {
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            if(userService.selectById(userId).getRole().equals(UserConst.STUDENT.getState())) {
                map.put("returnObject", learnerCvService.getLearnerCvList(userId));
                map.put("errorCode", "00");
            }
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }


    /**
     * 删除简历模版(未结束)
     * @param resumeId
     * @return
     */
    @RequestMapping(value = "/resume-mode/delete/{resumeId}", method = RequestMethod.GET)
    public String deleteResumeMode(@PathVariable(value = "resumeId") Long resumeId,
                                   HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            if(userService.selectById(userId).getRole().equals(UserConst.STUDENT.getState())) {
                if(learnerCvService.deleteLearnerCv(resumeId,userId))
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

    /**
     * 根据模板id,加载简历模板
     * @param resumeId
     * @return
     */
    @RequestMapping(value = "/resume-mode/get/{resumeId}", method = RequestMethod.GET)
    public String getResumeMode(@PathVariable(value = "resumeId") Long resumeId,
                                HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            if(userService.selectById(userId).getRole().equals(UserConst.STUDENT.getState())) {
                LearnerCv learnerCv = learnerCvService.getLearnerCvInfo(resumeId,userId);
                if(learnerCv !=null){
                    map.put("returnObject",learnerCv);
                    map.put("errorCode", "00");
                }
                else
                    map.put("errorCode", "error");
            }
            else
                map.put("errorCode", "error");
        } else
            map.put("errorCode", "error");
        return JSON.toJSONString(map);
    }

    /**
     * 修改简历模版(未结束)
     * @param resumeId
     * @param resumeName
     * @param name
     * @param sex
     * @param birth
     * @param nativePlace
     * @param identity
     * @param qualification
     * @param speciality
     * @param university
     * @param tel
     * @param experience
     * @param mailbox
     * @param introduction
     * @param diploma
     * @param lastEditTime
     * @param request
     * @return
     */
    @RequestMapping(value = "/change-resume-mode/{resumeId}", method = RequestMethod.POST)
    public String changeResumeMode(@PathVariable(value = "resumeId") Long resumeId,
                                   @RequestParam(value = "resumeName") String resumeName,
                                   @RequestParam(value = "name") String name,
                                   @RequestParam(value = "sex") Integer sex,
                                   @RequestParam(value = "birth") String birth,
                                   @RequestParam(value = "nativePlace") String nativePlace,
                                   @RequestParam(value = "identity") String identity,
                                   @RequestParam(value = "qualification") String qualification,
                                   @RequestParam(value = "speciality") String speciality,
                                   @RequestParam(value = "university") String university,
                                   @RequestParam(value = "tel") String tel,
                                   @RequestParam(value = "experience") String experience,
                                   @RequestParam(value = "mailbox") String mailbox,
                                   @RequestParam(value = "introduction") String introduction,
                                   @RequestParam(value = "diploma") String diploma,
                                   @RequestParam(value = "lastEditTime") String lastEditTime,
                                   HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            if(userService.selectById(userId).getRole().equals(UserConst.STUDENT.getState())) {
                LearnerCv learnerCv = new LearnerCv();
                learnerCv.setId(resumeId);
                learnerCv.setLearnerId(userId);
                learnerCv.setResumeName(resumeName);
                learnerCv.setName(name);
                learnerCv.setLearnerId(userId);
                learnerCv.setSex(sex);
                learnerCv.setBirth(DateUtil.getSqlDateTime(birth,DateUtil.YMD_TIME));
                learnerCv.setNativePlace(nativePlace);
                learnerCv.setIdentity(identity);
                learnerCv.setQualification(qualification);
                learnerCv.setSpeciality(speciality);
                learnerCv.setUniversity(university);
                learnerCv.setTel(tel);
                learnerCv.setExperience(experience);
                learnerCv.setMailbox(mailbox);
                learnerCv.setIntroduction(introduction);
                learnerCv.setDiploma(diploma);
                learnerCv.setLastEditTime(DateUtil.getSqlDateTime(lastEditTime,DateUtil.YMDHMS_TIME));
                if(learnerCvService.updateLearnerCv(learnerCv))
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

    /**
     * 上传或更新简历模板的头像(未结束)
     * @param resumeId
     * @param request
     * @return
     */
    @RequestMapping(value = "/resume-mode/deliver/avatar/{resumeId}", method = RequestMethod.POST)
    public String deliverModeAvatar(@PathVariable(value = "resumeId") Long resumeId,
                                    MultipartHttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long)request.getSession(true).getAttribute("userId");
            if(userService.selectById(userId).getRole().equals(UserConst.STUDENT.getState())) {
                if(learnerCvService.insertOrUpdateCvAvatar(resumeId,request))
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

