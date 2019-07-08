package com.example.worknet.common.persistence.affair.employment.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.worknet.common.persistence.affair.employment.service.CompanyProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;


import static com.example.worknet.common.constant.ProfessionConst.PROFESSION_DEFAULT;
import static com.example.worknet.common.constant.ProfessionConst.PROFESSION_NEW;
import static com.example.worknet.common.constant.ProfessionConst.PROFESSION_SALARY;

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

    @Autowired
    private CompanyProfessionService companyProfessionService;

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
    public String getWorking(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "order", required = false) String order,
                             @RequestParam(value = "field", required = false) String field,
                             @RequestParam(value = "location", required = false) String location,
                             @RequestParam(value = "keyword", required = false) String keyword,
                             @RequestParam(value = "professionId", required = false) String professionId){
        HashMap<String,Object> map = new HashMap<>();
        if(page == null) page = 1;
        if(order == null) order = "";
        if(field == null || field.equals("null")) field = "";
        if(location == null || location.equals("null")) location = "";
        Page<HashMap<String, Object>> pager = null;
        switch (order){
            case "new":
                pager = companyProfessionService.getProfessionPage(new Page<>(page, 20), PROFESSION_NEW, professionId, location, field, keyword);
                break;
            case "salary":
                pager = companyProfessionService.getProfessionPage(new Page<>(page, 20), PROFESSION_SALARY, professionId, location, field, keyword);
                break;
            default:
                pager = companyProfessionService.getProfessionPage(new Page<>(page, 20), PROFESSION_DEFAULT, professionId, location, field, keyword);
                break;
        }
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
        HashMap<String,Object> obj = companyProfessionService.getJobInfo(employId);
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
        HashMap<String, Object> map = new HashMap<>();
        Page<HashMap<String, Object>> pager = companyProfessionService.getEmployeeList(new Page<>(page, 10),companyId);
        map.put("returnObject",pager);
        map.put("errorCode","00");
        return JSON.toJSONString(map);
    }
}

