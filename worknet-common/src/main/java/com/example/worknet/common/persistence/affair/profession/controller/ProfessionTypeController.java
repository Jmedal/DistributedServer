package com.example.worknet.common.persistence.affair.profession.controller;


import com.alibaba.fastjson.JSON;
import com.example.worknet.common.persistence.affair.profession.serivce.ProfessionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 职业分类 前端控制器
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
@Controller
@ResponseBody
public class ProfessionTypeController {

    @Autowired
    private ProfessionTypeService professionTypeService;

    /**
     * 获取职业分类列表
     * @return
     */
    @RequestMapping(value = "/getVocationType", method = RequestMethod.GET)
    public String vocationLst(){
        HashMap<String, Object> obj = new HashMap<>();
        List<HashMap<String,Object>> list = professionTypeService.getProfessionType();
        obj.put("returnObject",list);
        obj.put("errorCode","00");
        return JSON.toJSONString(obj);
    }


}

