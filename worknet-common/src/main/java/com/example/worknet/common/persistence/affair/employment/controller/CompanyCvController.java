package com.example.worknet.common.persistence.affair.employment.controller;


import com.alibaba.fastjson.JSON;
import com.example.worknet.common.persistence.affair.employment.service.CompanyCvService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    CompanyCvService companyCvService;
    //限定为post方法，保证resumeId隐秘
    @RequestMapping(value = "/resume/deliver",method = RequestMethod.POST)
    //后端创建完简历之后需要返回给前端简历id,用于发送图片给后端
    public String deliverResume(HttpServletRequest request,
                                @RequestParam("searchText") Long employId,
                                @RequestParam("searchText") String name,
                                @RequestParam("searchText") Integer sex,
                                @RequestParam("searchText") Date birth,
                                @RequestParam("searchText") String nativePlace,
                                @RequestParam("searchText") String identity,
                                @RequestParam("searchText") String qualification,
                                @RequestParam("searchText") String specialty,
                                @RequestParam("searchText") String university,
                                @RequestParam("searchText") String tel,
                                @RequestParam("searchText") String experience,
                                @RequestParam("searchText") String mailbox,
                                @RequestParam("searchText") String introduction,
                                @RequestParam("searchText") String diploma,
                                @RequestParam("searchText") String currentLocation,
                                @RequestParam("searchText") String inJobTime
                                ){
        HashMap<String,Object> map = new HashMap<>();
        if(request.getSession(true).getAttribute("userId") != null) {
            Long userId = (long) request.getSession(true).getAttribute("userId");
            Long id = companyCvService.deliverResume(employId, userId, name, sex, birth, nativePlace, identity, qualification, specialty,
                    university, tel, experience, mailbox, introduction, diploma, currentLocation, inJobTime);
            map.put("errorCode", "00");
            map.put("returnObject", id);//需要返回创建的简历的id
//        System.out.println(request.getParameter("introduction"));//测试下是否会分行
        }else{
            map.put("errorCode", "error");
        }
        //修改对应简历的状态，投递简历。
        return JSON.toJSONString(map);
    }

    //根据简历的id获取学习者简历的头像（注意和上面的不同）
    @RequestMapping(value = "/get/resume/avatar/{resumeId}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getAvatar(@PathVariable Integer resumeId,
                            HttpServletResponse response) throws IOException {
        File file = new File("D:\\SoftwareXieTong\\src\\main\\resources\\static\\images\\profile1.png");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }

    //上传对应简历id的头像
    @RequestMapping(value = "/resume/deliver/avatar/{resumeId}",method = RequestMethod.POST)
    public String deliverAvatar(@PathVariable int resumeId){
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }


    //根据简历id获取具体的简历（不是模板！！）
    @RequestMapping(value = "/resume/get/{resumeId}")
    public String getResume(@PathVariable int resumeId){
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String,Object> obj = companyCvService.getCvInfo((long)resumeId);
        map.put("returnObject",obj);
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }

    /**
     * 获取用户投递过的简历
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/get/my-resume")
    public String getMyResume(@RequestParam("pageSize") Integer pageSize,
                              @RequestParam("pageNumber") Integer page,
                              @RequestParam("searchText") String searchText,
                              HttpServletRequest request, HttpServletResponse response){
        HashMap<String,Object> map = new HashMap<>();
        System.out.println("关键字："+searchText);
        if(request.getSession(true).getAttribute("userId") != null){
            Long userId = (long) request.getSession(true).getAttribute("userId");
            Page<HashMap<String,Object>> pager = companyCvService.getMyResumePage(new Page<>(page,pageSize),userId.toString(),searchText);
            map.put("total",pager.getTotal());
            map.put("rows",pager.getRecords());
            map.put("errorCode", "00");
        }else {
            map.put("errorCode", "error");
        }
        return JSON.toJSONString(map);
    }

}

