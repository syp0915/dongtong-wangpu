package com.dongtong.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Package com.shfc.adviser.utils.ValidDateUtils
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/1/12 15:24
 * version V1.0.0
 */
public class ValidDateUtils {

    public static boolean isValidDate(String sDate) {
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(sDate);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }

    public static void main(String[] args) {
        System.out.print(isValidDate("2004-02-12"));
    }
}
