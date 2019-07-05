package com.example.worknet.common.persistence;

import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class HomeControllerNew2 {

//    //获取默认的简历模板
//    @RequestMapping(value = "/getFavorResumeMode/")
//    @ResponseBody
//    public String getFavorResumeMode(){
//        //如果没有默认的简历模板，则返回空
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        HashMap<String,Object> obj = new HashMap<>();
//        obj.put("id",55);//简历模板的id！！！
//        obj.put("resumeName","简历名称");//简历的名字
//        obj.put("learnerId",131);
//        obj.put("name","张萨姆");
//        obj.put("sex",1);
//        obj.put("birth","1997-05");
//        obj.put("nativePlace","上海市");
//        obj.put("identity","124124124");
//        obj.put("qualification",3);//存0-7的数字
//        obj.put("speciality","计算机");
//        obj.put("university","上海大学");
//        obj.put("tel","18888888888");
//        obj.put("experience","2008-2011 白宫洗碗三年");//格式为字符串，存入格式为时间+内容
//        obj.put("mailbox","88888888@qq.com");
//        obj.put("introduction","我是一个xxxxxx\n\n哈哈哈");
//        obj.put("diploma","2018-03-21 洗碗全国大奖");
//        obj.put("headPath","http://www.baidu.com");
//        map.put("returnObject",obj);
//        return JSON.toJSONString(map);
//    }
//
//
//    //限定为post方法，保证resumeId隐秘
//    @RequestMapping(value = "/resume/deliver",method = RequestMethod.POST)
//    @ResponseBody
//    //后端创建完简历之后需要返回给前端简历id,用于发送图片给后端
//    public String deliverResume(HttpServletRequest request){
//        //传输投递的所有消息，如：
//        //birth: "1997-05"
//        //diploma: "2018-03-21 洗碗全国大奖"
//        //experience: "2008-2011 白宫洗碗三年"
//        //headPath: "http://www.baidu.com"
//        //identity: "124124124"
//        //introduction: "我是一个xxxxxx"
//        //learnerId: 131
//        //mailbox: "88888888@qq.com"
//        //name: "张三"
//        //nativePlace: "上海市"
//        //qualification: "5"
//        //sex: "0"
//        //speciality: "计算机"
//        //tel: "18888888888"
//        //university: "上海大学"
//        //inJobTime:"三周内"
//        //currentLocation:"上海"
//        System.out.println(request.getParameter("introduction"));//测试下是否会分行
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        map.put("returnObject",12421);//需要返回创建的简历的id
//        //修改对应简历的状态，投递简历。
//        return JSON.toJSONString(map);
//    }
//
//
//    //根据简历模板的id获取学习者简历的头像
//    @ResponseBody
//    @RequestMapping(value = "/get/resume-mode/avatar/{resumeId}",produces = MediaType.IMAGE_JPEG_VALUE)
//    public byte[] getModeAvatar(@PathVariable Integer resumeId,
//                               HttpServletResponse response) throws IOException {
//        File file = new File("D:\\SoftwareXieTong\\src\\main\\resources\\static\\images\\profile1.png");
//        FileInputStream inputStream = new FileInputStream(file);
//        byte[] bytes = new byte[(int)file.length()];
//        inputStream.read(bytes, 0, inputStream.available());
//        return bytes;
//    }
//
//    //根据简历的id获取学习者简历的头像（注意和上面的不同）
//    @ResponseBody
//    @RequestMapping(value = "/get/resume/avatar/{resumeId}",produces = MediaType.IMAGE_JPEG_VALUE)
//    public byte[] getAvatar(@PathVariable Integer resumeId,
//                            HttpServletResponse response) throws IOException {
//        File file = new File("D:\\SoftwareXieTong\\src\\main\\resources\\static\\images\\profile1.png");
//        FileInputStream inputStream = new FileInputStream(file);
//        byte[] bytes = new byte[(int)file.length()];
//        inputStream.read(bytes, 0, inputStream.available());
//        return bytes;
//    }
//
//    //上传对应简历id的头像
//    @ResponseBody
//    @RequestMapping(value = "/resume/deliver/avatar/{resumeId}",method = RequestMethod.POST)
//    public String deliverAvatar(@PathVariable int resumeId){
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        return JSON.toJSONString(map);
//    }
//
//    //上传对应模板简历id的头像
//    @ResponseBody
//    @RequestMapping(value = "/resume-mode/deliver/avatar/{resumeId}",method = RequestMethod.POST)
//    public String deliverModeAvatar(@PathVariable int resumeId){
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        return JSON.toJSONString(map);
//    }
//
//
//    @ResponseBody
//    //用户的默认头像
//    @RequestMapping(value = "/user/default/avatar",produces = MediaType.IMAGE_JPEG_VALUE)
//    public byte[] getDefaultAvatar() throws IOException {
//        File file = new File("D:\\SoftwareXieTong\\src\\main\\resources\\static\\images\\profile1.png");
//        FileInputStream inputStream = new FileInputStream(file);
//        byte[] bytes = new byte[(int)file.length()];
//        inputStream.read(bytes, 0, inputStream.available());
//        return bytes;
//    }
//
//
//    @ResponseBody
//    @RequestMapping(value = "/create-resume-mode",method = RequestMethod.POST)
//    public String createResumeMode(){
//        //创建简历模板
//        //传输投递的所有消息，如：
//        //birth: "1997-05"
//        //resumeName:'简历名称'
//        //diploma: "2018-03-21 洗碗全国大奖"
//        //experience: "2008-2011 白宫洗碗三年"
//        //headPath: "http://www.baidu.com"
//        //identity: "124124124"
//        //introduction: "我是一个xxxxxx"
//        //mailbox: "88888888@qq.com"
//        //name: "张三"
//        //nativePlace: "上海市"
//        //qualification: "5"
//        //sex: "0"
//        //speciality: "计算机"
//        //tel: "18888888888"
//        //university: "上海大学"
//        //lastEditTime:"2018-03-04 09:12:44"
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        map.put("returnObject",12421);//需要返回创建的简历的id
//        return JSON.toJSONString(map);
//    }
//
//
//    @ResponseBody
//    @RequestMapping(value = "/get/resume-mode/list")
//    public String getResumeModeList(){
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
//        for(int i = 0; i < 3; i++){
//            HashMap<String,Object> objectHashMap = new HashMap<>();
//            objectHashMap.put("resumeId","10"+i);
//            objectHashMap.put("resumeName","简历模板"+i);
//            objectHashMap.put("resumePercent","3"+i+"%");//存百分号的字符串形式
//            objectHashMap.put("lastEditTime","2019-04-15 05:43:1"+i);//以类似形式存储，前端直接显示字符串
//            objectHashMap.put("isFavor",i%2==1);//true or false，表示该模板是否是默认模板
//            list.add(objectHashMap);
//        }
//        map.put("returnObject",list);
//        return JSON.toJSONString(map);
//    }
//
//
//    @ResponseBody
//    //需要注意，当删除的简历模板为该用户的默认模板时，需要从该用户其他模板中选取一份作为默认模板
//    @RequestMapping(value = "/resume-mode/delete/{resumeId}")
//    public String deleteResumeMode(@PathVariable int resumeId){
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        return JSON.toJSONString(map);
//    }
//
//
//    @ResponseBody
//    //根据模板id获取模板的内容
//    @RequestMapping(value = "/resume-mode/get/{resumeId}")
//    public String getResumeMode(@PathVariable int resumeId){
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        HashMap<String,Object> obj = new HashMap<>();
//        obj.put("id",55);//简历模板的id！！！
//        obj.put("resumeName","简历名称1231");//简历的名字
//        obj.put("learnerId",131);
//        obj.put("name","张萨姆");
//        obj.put("sex",1);
//        obj.put("birth","1997-05");
//        obj.put("nativePlace","上海市");
//        obj.put("identity","124124124");
//        obj.put("qualification",3);//存0-7的数字
//        obj.put("speciality","计算机");
//        obj.put("university","上海大学");
//        obj.put("tel","18888888888");
//        obj.put("experience","2008-2011 白宫洗碗三年");//格式为字符串，存入格式为时间+内容
//        obj.put("mailbox","88888888@qq.com");
//        obj.put("introduction","我是一个xxxxxx");
//        obj.put("diploma","2018-03-21 洗碗全国大奖");
//        obj.put("headPath","http://www.baidu.com");
//        map.put("returnObject",obj);
//        return JSON.toJSONString(map);
//    }
//
//    @ResponseBody
//    //根据简历id获取具体的简历（不是模板！！）
//    @RequestMapping(value = "/resume/get/{resumeId}")
//    public String getResume(@PathVariable int resumeId){
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        HashMap<String,Object> obj = new HashMap<>();
//        obj.put("id",55);//简历模板的id！！！
//        obj.put("resumeName","简历名称1231");//简历的名字
//        obj.put("learnerId",131);
//        obj.put("name","张萨姆");
//        obj.put("sex",1);
//        obj.put("birth","1997-05");
//        obj.put("nativePlace","上海市");
//        obj.put("identity","124124124");
//        obj.put("qualification",3);//存0-7的数字
//        obj.put("speciality","计算机");
//        obj.put("university","上海大学");
//        obj.put("tel","18888888888");
//        obj.put("experience","2008-2011 白宫洗碗三年");//格式为字符串，存入格式为时间+内容
//        obj.put("mailbox","88888888@qq.com");
//        obj.put("introduction","我是一个xxxxxx");
//        obj.put("diploma","2018-03-21 洗碗全国大奖");
//        obj.put("headPath","http://www.baidu.com");
//        map.put("returnObject",obj);
//        return JSON.toJSONString(map);
//    }
//
//
//    @ResponseBody
//    @RequestMapping(value = "/change-resume-mode/{resumeId}",method = RequestMethod.POST)
//    public String changeResumeMode(@PathVariable int resumeId){
//        //传输投递的所有消息，如：
//        //birth: "1997-05"
//        //diploma: "2018-03-21 洗碗全国大奖"
//        //experience: "2008-2011 白宫洗碗三年"
//        //headPath: "http://www.baidu.com"
//        //identity: "124124124"
//        //introduction: "我是一个xxxxxx"
//        //mailbox: "88888888@qq.com"
//        //name: "张三"
//        //nativePlace: "上海市"
//        //qualification: "5"
//        //sex: "0"
//        //speciality: "计算机"
//        //tel: "18888888888"
//        //university: "上海大学"
//        //lastEditTime:"2018-03-04 09:12:44"
//        //resumeName:'简历名称'
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        return JSON.toJSONString(map);
//    }
//
//
//    @ResponseBody
//    @RequestMapping(value = "/setFavorResume/{resumeId}")
//    //用户设置默认简历，
//    public String setFavorResume(@PathVariable int resumeId){
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("errorCode","00");
//        return JSON.toJSONString(map);
//    }

}
