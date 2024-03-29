package com.example.worknet.core.utils.date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取时间
 * @Author: YunJieJiang
 * @Date: Created in 21:45 2018/12/11 0011
 */
public class DateUtil {

    public static final String YMD_TIME = "yyyy-MM-dd";

    public static final String YMDHMS_TIME = "yyyy-MM-dd HH:mm:ss";


    public static java.sql.Date getSqlNowDate(){
        Date utilDate  =new Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return  sqlDate;
    }

    public static Timestamp getSqlNowDateTime(){
        Date utilDate = new Date();
        Timestamp sqlDateTime = new Timestamp(utilDate.getTime());
        return  sqlDateTime;
    }

    public static Date getSqlDate(String date, String type){
        if(date == null) return null;
        DateFormat dateFormat = new SimpleDateFormat(type);
        Date myDate = null;
        try {
            myDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myDate;
    }

    /**
     * 将时间字符串 转换为 时间戳
     * @param date
     * @param type
     * @return
     */
    public static Timestamp getSqlDateTime(String date, String type) {
        if(date == null) return null;
        DateFormat dateFormat = new SimpleDateFormat(type);
        Date myDate = null;
        try {
            myDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp sqlDateTime = new Timestamp(myDate.getTime());
        return sqlDateTime;
    }


    /**
     * 将时间字符串 转换为 时间戳字符串
     * @param s
     * @param type
     * @return
     * @throws ParseException
     */
    public static String dateToStamp(String s, String type) {
        if(s == null) return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(date.getTime());
    }

    /**
     * 将时间戳 转换为 时间字符串
     * @param date
     * @param type
     * @return
     */
    public static String stampToDate(Date date, String type){
        if(date == null) return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
        return simpleDateFormat.format(date);
    }
}
