package com.example.worknet.common.persistence.pageController;

import com.example.worknet.common.persistence.affair.administrator.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class AdminController {

    @RequestMapping(value = {"/admin/login","/admin"})
    public String adminLogin(){
        return "admin/login.html";
    }

    @RequestMapping(value = "/admin/")
    public String home(){
        return "admin/home.html";
    }

    @RequestMapping(value = "/admin/stu-account")
    public String sa(){
        return "admin/stu-account.html";
    }

    @RequestMapping(value = "/admin/stu-info")
    public String si(){
        return "admin/stu-info.html";
    }

    @RequestMapping(value = "/admin/stu-employ")
    public String se(){
        return "admin/stu-employ.html";
    }

    @RequestMapping(value = "/admin/stu-welcome")
    public String sw(){
        return "admin/stu-welcome.html";
    }

    @RequestMapping(value = "/admin/com-account")
    public String ca(){
        return "admin/com-account.html";
    }

    @RequestMapping(value = "/admin/com-info")
    public String ci(){
        return "admin/com-info.html";
    }

    @RequestMapping(value = "/admin/com-employ")
    public String ace(){
        return "admin/com-employ.html";
    }

    @RequestMapping(value = "/admin/profession")
    public String pf(){
        return "admin/profession.html";
    }

    @RequestMapping(value = "/admin/login-confirm",method = RequestMethod.POST)
    public String confirmLogin(@RequestParam(value = "password") String password,
                               HttpServletRequest request){
        if(administratorService.verify("admin",password)){
            request.getSession().setAttribute("user","administrator");
            return "admin/index.html";
        }
        return "admin/login.html";
    }

    @Autowired
    private AdministratorService administratorService;
}
