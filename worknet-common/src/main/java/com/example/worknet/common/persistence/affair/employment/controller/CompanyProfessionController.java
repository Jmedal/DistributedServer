package com.example.worknet.common.persistence.affair.employment.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.worknet.common.constant.ProfessionConst;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 企业发布岗位表 前端控制器
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
@Controller
@ResponseBody
public class CompanyProfessionController {

    /**
     * 筛选获得公司招聘列表
     * page,keyword,order(['new'|'salary'|'']),对应最新发布，薪水最高和默认排序
     * @param page
     * @param order
     * @param field
     * @param location
     * @param keyword
     * @param professionId
     * @return
     */
    @RequestMapping(value = "/get/working", method = RequestMethod.GET)
    public String getWorking(@RequestParam(value = "page") Integer page,
                             @RequestParam(value = "order") String order,
                             @RequestParam(value = "field") String field,
                             @RequestParam(value = "location") String location,
                             @RequestParam(value = "keyword") String keyword,
                             @RequestParam(value = "professionId") String professionId){
        //一页20条
        if(keyword == null) keyword = "";
        if(order == null) order = "";
        if(page == null) page = 1;
        Page<HashMap<String,Object>> questionPage = null;
//        switch (order){
//            case "new":
//                questionPage = courseService.getCoursePage(new Page<>(page, 9), ProfessionConst.PROFESSION_NEW,keyword);
//                break;
//            case "salary":
//                questionPage = courseService.getCoursePage(new Page<>(page, 9), CourseConst.COURSE_STAR,keyword);
//                break;
//            default:
//                questionPage = courseService.getCoursePage(new Page<>(page, 9), CourseConst.COURSE_DEFAULT,keyword);
//                break;
//        }
        HashMap<String,Object> map = new HashMap<>();
        HashMap<String,Object> pager = new HashMap<>();
        map.put("returnObject",pager);
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }

    /**
     * 获取公司招聘信息
     * @param employId
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/get/jobInfo/{employId}/company/{companyId}", method = RequestMethod.GET)
    public String getJobInfo(@PathVariable(value = "employId") Long employId,
                             @PathVariable(value = "companyId") Long companyId){
        HashMap<String,Object> map = new HashMap<>();
        HashMap<String,Object> obj = new HashMap<>();
        map.put("returnObject",obj);
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }

    /**
     * 根据公司id,获取公司招聘列表
     * @param companyId
     * @param page
     * @return
     */
    @RequestMapping(value = "/get/employ/{companyId}", method = RequestMethod.GET)
    public String getEmployList(@PathVariable(value = "companyId") Long companyId,
                                @RequestParam(value = "page") Integer page){
        //一页10条
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String,Object> pager = new HashMap<>();
        map.put("returnObject",pager);
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }
}

