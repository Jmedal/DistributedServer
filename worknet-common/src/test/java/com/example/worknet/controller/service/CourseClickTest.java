package com.example.worknet.controller.service;

import com.example.worknet.common.persistence.affair.user.serivce.CompanyService;
import com.example.worknet.common.persistence.affair.user.serivce.UserService;
import com.example.worknet.common.persistence.template.modal.Company;
import com.example.worknet.common.persistence.template.modal.User;
import com.example.worknet.core.utils.date.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试API
 * Service的测试类
 * @Author: YunJieJiang
 * @Date: Created in 2:20 2018/8/24 0024
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CourseClickTest {


//    @Autowired
//    private CourseClickService courseClickService;
//
    @Autowired
    private UserService userService;
//    @Autowired
//    private LearnerCvService learnerCvService;

    @Autowired
    private CompanyService companyService;
//
//    @Autowired
//    private AdministratorService administratorService;
//
//    @Autowired
//    private TeacherInfoService teacherInfoService;
//
//    @Autowired
//    private CourseService courseService;
//
//    @Autowired
//    private CompanyContestApplyMapper companyContestApplyMapper;
//
//    @Autowired
//    private CourseStudiedController courseStudiedController;

    @Test
    public void test() throws Exception {
        User user = new User();
        user.setAccount("baidu");
        user.setPassword("baidu");
        user.setActivity(1);
        user.setRole(1);
        System.out.println(userService.insert(user));
        Company company = new Company();
        company.setUserId(user.getId());
        company.setRegisterTime(DateUtil.getSqlNowDate());
        companyService.insert(company);

        //System.out.println(learnerCvService.getLearnerCvInfo((long)1,(long)3));


//        User user = new  User();
//        user.setAccount("123456");
//        user.setPassword("123");
//        user.setRole(3);
//        user.setActivity(1);
//        userService.insert(user);
//        System.out.println(user.getId());

//        System.out.println("3".hashCode());
//
//        TeacherInfo teacherInfo = teacherInfoService.selectById(1);
//
//        System.out.println(teacherInfo.toString());
//
//            User user = userService.selectList(new EntityWrapper<User>().eq("id",teacherInfo.getUserId())).get(0);
//
//        System.out.println(user);
//
//        System.out.println(teacherInfoService.selectList(new EntityWrapper<TeacherInfo>().eq("user_id",user.getId())));
//
//        System.out.println(administratorService.verify("admin","admin"));

//        User user = userService.selectList(new EntityWrapper<User>().eq("id",1)).get(0);
//        System.out.println(userService.getUserInfo(user));


        //Page<HashMap<String,Object>> questionPage = courseService.getCoursePage(new Page<>(0 ,9), CourseConst.COURSE_DEfAULT,"");
        //HashMap<String,Object> map = new HashMap<>();
        //map.put("errorCode","00");
        //map.put("returnObject",questionPage);

//        System.out.println(companyContestApplyMapper.getUserCompanyContestPage(new Page<>(1,8),(long)3));


    }
}