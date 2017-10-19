package com.dongtong.app.wnl.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Package Util.AstroUtil
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/1/3 14:59
 * version V1.0.0
 */
public class AstroUtil {

    /**
     *  dateTime :yyyy-MM-dd
     * @return
     */
    public static int QueryAstroCode(String dateTime){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = new Date(format.parse(dateTime).getTime());
            Calendar can1 = Calendar.getInstance();
            can1.setTime(date);
            int month = can1.get(Calendar.MONTH)+1;
            int days=can1.get(Calendar.DATE);
            String code = getConstellationCode(month,days);
            return Integer.parseInt(code);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 根据月日判断星座
     * @param m
     * @param d
     * @return int
     */
    public static String getConstellation(int m,int d){

        final String[] constellationArr = {"魔羯座" ,"水瓶座", "双鱼座", "牡羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座","天蝎座", "射手座", "魔羯座" };

        final int[] constellationEdgeDay = { 20,18,20,20,20,21,22,22,22,22,21,21};
        int month=m;
        int day =d;
        if (day <= constellationEdgeDay[month-1]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArr[month];
        }
        //default to return 魔羯
        return constellationArr[11];

    }

    /**
     * 根据月日判断星座
     * @param m
     * @param d
     * @return int
     */
    public static String getConstellationCode(int m,int d){

        final String[] constellationArr = {"10" ,"11", "12", "1", "2", "3", "4", "5", "6", "7","8", "9", "10" };

        final int[] constellationEdgeDay = { 20,18,20,20,20,21,22,22,22,22,21,21};
        int month=m;
        int day =d;
        if (day <= constellationEdgeDay[month-1]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArr[month];
        }
        //default to return 魔羯
        return constellationArr[11];

    }
    public static void main(String[] args) {
        System.out.println(QueryAstroCode("2016-03-20")+"-----------");
    }
}
