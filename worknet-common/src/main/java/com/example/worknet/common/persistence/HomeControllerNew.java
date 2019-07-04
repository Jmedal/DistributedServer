package com.example.worknet.common.persistence;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class HomeControllerNew {

//    @ResponseBody
//    @RequestMapping(value = "/getVocationType")
//    public String vocationLst(){
//        HashMap<String, Object> obj = new HashMap<>();
//        obj.put("errorCode","00");
//        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
//        String[] v = new String[]{"研发","测试","数据","算法","前端","产品","运营","其他"};
//        int i = 0;
//        for(String s:v){
//            HashMap<String,Object> map = new HashMap<>();
//            map.put("professionId",i++);
//            map.put("profession",s);
//            list.add(map);
//        }
//        obj.put("returnObject",list);
//        return JSON.toJSONString(obj);
//    }
//    @RequestMapping(value = "/get/working")
//    @ResponseBody
//    public String getWorking(){
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        HashMap<String,Object> obj = new HashMap<>();
//        obj.put("total",1000);//1000条
//        List<HashMap<String,Object>> list = new ArrayList<>();
//        for(int i = 0; i < 20; i++){//一页20条
//            HashMap<String,Object> map1 = new HashMap<>();
//            map1.put("companyId",i+"01");//公司id
//            map1.put("id",i);//简历id
//            map1.put("title", String.format("第%d个职业", i));
//            map1.put("isPractice",i%2==0);
//            map1.put("companyName", String.format("公司%d", i));
//            map1.put("salary","薪水面议");
//            map1.put("location","上海");
//            list.add(map1);
//        }//模拟添加数据
//        obj.put("records",list);
//        obj.put("pages",50);//符合条件的总条数
//        map.put("returnObject",obj);
//        return JSON.toJSONString(map);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/get/jobInfo/{employId}/company/{companyId}")
//    public String getJobInfo(@PathVariable int employId,@PathVariable int companyId){
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        HashMap<String,Object> obj = new HashMap<>();
//        obj.put("introduction","职位简介111111111111");
//        obj.put("requirement","职位需求111111111111");
//        obj.put("salary","薪酬面议");
//        obj.put("title","【2019实习】java web");
//        obj.put("location","上海");
//        obj.put("duration","5天/周，3月以上");
//        obj.put("chanceToFormal","有");//是否有转正机会
//        obj.put("isPractice",true);//是否是实习
//        obj.put("profession","前端");//职业分类
//        obj.put("state",1);//招聘状态，1代表正常，0代表关闭
//        map.put("returnObject",obj);
//        return JSON.toJSONString(map);
//    }
//
//    //获取公司的所有招聘信息
//    @ResponseBody
//    @RequestMapping(value = "/get/companyInfo/{companyId}")
//    public String getCompanyEmploy(@PathVariable int companyId){
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        HashMap<String,Object> obj = new HashMap<>();
//        obj.put("name","百度");
//        obj.put("introduction","百度公司是一家恰烂钱的垃圾公司，广告专门推广莆田系，搜索还差的要死。");
//        obj.put("address","上海市xx区xx路xxx号");
//        obj.put("communication","021-55555555");
//        obj.put("website","http://www.baidu.com");
//        obj.put("field","互联网");
//        map.put("returnObject",obj);
//        return JSON.toJSONString(map);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/get/employ/{companyId}")
//    public String getEmployList(@PathVariable int companyId, HttpServletRequest request){
//        //根据公司id返回招聘信息列表，一页10条
//        int page = Integer.parseInt(request.getParameter("page"));
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        HashMap<String,Object> pager = new HashMap<>();
//        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
//        for(int i = 0; i < 10; i++){
//            HashMap<String,Object> map1 = new HashMap<>();
//            map1.put("companyId",i+"01");//公司id
//            map1.put("id",i);//简历id
//            map1.put("title", String.format("第%d个实习生", i));
//            map1.put("companyName", "百度");
//            map1.put("salary","薪水面议");
//            map1.put("isPractice",true);//都是实习
//            map1.put("location","上海");
//            map1.put("state",0);//!!注意这里要传state
//            list.add(map1);
//        }
//        pager.put("records",list);
//        pager.put("pages",12);//一共12页
//        map.put("returnObject",pager);
//        return JSON.toJSONString(map);
//    }
}
