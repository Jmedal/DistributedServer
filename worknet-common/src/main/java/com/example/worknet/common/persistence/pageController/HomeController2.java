package com.example.worknet.common.persistence.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController2 {
    @RequestMapping(value = "/exam/{eid}/")
    public String examDetail(@PathVariable Integer eid){
        return "exam_detail.html";
    }

    @RequestMapping(value = "/working")
    public String working(){
        return "working.html";
    }

    //获取招聘详情
    @RequestMapping(value = "/working/{companyId}/job/{id}")
    public String getEmployment(@PathVariable int companyId, @PathVariable int id){
        return "employment.html";
    }

    @RequestMapping(value = "/working/{companyId}")
    public String companyEmploy(@PathVariable int companyId){
        return "company-employ.html";
    }
}

