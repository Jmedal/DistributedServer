package com.example.worknet.common.persistence.affair.employment.controller;


import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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

    //限定为post方法，保证resumeId隐秘
    @RequestMapping(value = "/resume/deliver",method = RequestMethod.POST)
    //后端创建完简历之后需要返回给前端简历id,用于发送图片给后端
    public String deliverResume(HttpServletRequest request){
        //传输投递的所有消息，如：
        //birth: "1997-05"
        //diploma: "2018-03-21 洗碗全国大奖"
        //experience: "2008-2011 白宫洗碗三年"
        //headPath: "http://www.baidu.com"
        //identity: "124124124"
        //introduction: "我是一个xxxxxx"
        //learnerId: 131
        //mailbox: "88888888@qq.com"
        //name: "张三"
        //nativePlace: "上海市"
        //qualification: "5"
        //sex: "0"
        //speciality: "计算机"
        //tel: "18888888888"
        //university: "上海大学"
        //inJobTime:"三周内"
        //currentLocation:"上海"
        //lastEditTime:"2010-03-04 14:33:23"
        System.out.println(request.getParameter("introduction"));//测试下是否会分行
        HashMap<String,Object> map = new HashMap<>();
        map.put("errorCode","00");
        map.put("returnObject",12421);//需要返回创建的简历的id
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
        map.put("errorCode","00");
        HashMap<String,Object> obj = new HashMap<>();
        obj.put("id",55);//简历模板的id！！！
        obj.put("resumeName","简历名称1231");//简历的名字
        obj.put("learnerId",131);
        obj.put("name","张萨姆");
        obj.put("sex",1);
        obj.put("birth","1997-05");
        obj.put("nativePlace","上海市");
        obj.put("identity","124124124");
        obj.put("qualification",3);//存0-7的数字
        obj.put("speciality","计算机");
        obj.put("university","上海大学");
        obj.put("tel","18888888888");
        obj.put("experience","2008-2011 白宫洗碗三年");//格式为字符串，存入格式为时间+内容
        obj.put("mailbox","88888888@qq.com");
        obj.put("introduction","我是一个xxxxxx");
        obj.put("diploma","2018-03-21 洗碗全国大奖");
        obj.put("headPath","http://www.baidu.com");
        map.put("returnObject",obj);
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
        map.put("total",155);//数据总条数
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        for(int i = 0; i < pageSize; i++){
            HashMap<String,Object> obj = new HashMap<>();
            obj.put("companyName","百度");
            obj.put("companyId",(i+page*pageSize+1));
            obj.put("title","实习招聘凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数"+(i+page*pageSize+1));//应聘的信息标题
            obj.put("employId",(i+page*pageSize+100));//招聘信息的id
            obj.put("resumeId",(i+page*pageSize+1));//投放简历的id
            obj.put("status",i%3);//3种状态，待审核，通过，拒绝
            obj.put("lastEditTime","2019-08-05 15:33:25");//上次操作时间（待审核状态的操作时间即上传的时间）
            list.add(obj);
        }
        map.put("rows",list);//list集合
        return JSON.toJSONString(map);
    }

}

