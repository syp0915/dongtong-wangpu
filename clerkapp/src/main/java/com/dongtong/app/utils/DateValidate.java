package com.dongtong.app.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类注释
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/26 14:40
 * @since 1.0
 */
public class DateValidate {

    public static boolean isValidDate(String str,String format) {
        //String str = "2007-01-02";
        DateFormat formatter = new SimpleDateFormat(format);
        try{
            Date date = (Date)formatter.parse(str);
            return str.equals(formatter.format(date));
        }catch(Exception e){
            return false;
        }
    }
}
