package com.example.worknet.common.persistence.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController2 {
    @RequestMapping(value = "/exam/{eid}/")
    public String examDetail(@PathVariable(value = "eid") Integer eid){
        return "exam_detail.html";
    }

    @RequestMapping(value = "/working")
    public String working(){
        return "working.html";
    }

    //获取招聘详情
    @RequestMapping(value = "/working/{companyId}/job/{id}")
    public String getEmployment(@PathVariable(value = "companyId") int companyId, @PathVariable(value = "id") int id){
        return "employment.html";
    }

    @RequestMapping(value = "/working/{companyId}")
    public String companyEmploy(@PathVariable(value = "companyId") int companyId){
        return "company-employ.html";
    }

    //预览简历or简历模板
    @RequestMapping(value = {"/resume/preview/{resumeId}", "/resume/preview/employ/{resumeId}"})
    public String previewResumeMode(@PathVariable(value = "resumeId") int resumeId){
        //控制器不必管，会有前端负责鉴别两者
        return "preview.html";
    }

}

