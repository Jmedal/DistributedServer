package com.example.worknet.common.persistence.pageController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.worknet.common.constant.UserConst;
import com.example.worknet.common.persistence.affair.user.serivce.UserService;
import com.example.worknet.common.persistence.template.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: YunJieJiang
 * @Date: Created in 12:38 2019/7/9 0009
 */

@Controller
public class ComController {

    @RequestMapping(value = {"/company/login", "/company"})
    public String login(){
        return "company/login.html";
    }

    @RequestMapping(value = "/company/")
    public String home(){
        return "company/home.html";
    }

    @RequestMapping(value = "/company/resume")
    public String cr(){
        return "company/resume.html";
    }

    @RequestMapping(value = "/company/account")
    public String sa(){
        return "company/stu-account.html";
    }

    @RequestMapping(value = "/company/info")
    public String si(){
        return "company/stu-info.html";
    }

    @RequestMapping(value = "/company/employ")
    public String se(){
        return "company/stu-employ.html";
    }

    @RequestMapping(value = "/company/welcome")
    public String sw(){
        return "company/stu-welcome.html";
    }

    @RequestMapping(value = "/company/welcome-info")
    public String swi(){
        return "company/welcome-info.html";
    }

    @RequestMapping(value = "/company/login-confirm")
    public String comfirm(@RequestParam(value = "username") String username,
                          @RequestParam(value = "password") String password,
                          HttpServletRequest request){
        if(userService.verify(username,password)){
            User user = userService.selectOne(new EntityWrapper<User>().eq("account",username));
            if(user.getRole().equals(UserConst.COMPANY.getState()) && user.getActivity().equals(1)){
                request.getSession().setAttribute("userId",user.getId());
                return "company/index.html";
            }
        }
        return "company/login.html";
    }

    @Autowired
    private UserService userService;
}

