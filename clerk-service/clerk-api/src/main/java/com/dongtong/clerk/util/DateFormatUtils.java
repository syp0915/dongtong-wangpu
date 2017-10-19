package com.dongtong.clerk.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Package com.dongtong.shop.remote.DateFormatUtils
 * @Description: DateFormatUtils
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/4 16:21
 * version V1.0.0
 */
public class DateFormatUtils {

    /**
     * 传入yyyy-MM-dd HH:mm:ss格式字符串，发布时间显示规则：
     * 1小时内，x分钟前
     * 24小时以内，显示x小时前
     * 超过一天显示 具体发布日期 2016-11-11
     * @param
     * @return
     */
    public static String parseDateShow(String beginDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(new Date());
        String returnText = null;

        try {
            long from = sdf.parse(beginDate).getTime();
            long end = sdf.parse(now).getTime();
            int days = (int) ((end - from) / (1000 * 60 * 60 * 24));
            if (days == 0) {// 24小时以内，显示x小时前
                int hours = (int) (end - from) / (1000 * 60 * 60);
                if (hours == 0) {// 1小时内，x分钟前
                    int minutes = (int) (end - from) / (1000 * 60);
                    if (minutes <= 0) {
                        returnText = "刚刚";
                    } else {
                        returnText = minutes + "分钟前";
                    }
                } else {
                    returnText = hours + "小时前";
                }
            } else {// 超过一天显示 具体发布日期 2016-11-11
                returnText = sdf.format(sdf.parse(beginDate)).substring(0, 10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnText;
    }

    /**
     * 时间转换
     * @param beginDate
     * @return
     */
    public static String parseDateShow(Date beginDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String returnText = null;

        try {
            long from = beginDate.getTime();
            long end = new Date().getTime();
            int days = (int) ((end - from) / (1000 * 60 * 60 * 24));
            if (days == 0) {// 24小时以内，显示x小时前
                int hours = (int) (end - from) / (1000 * 60 * 60);
                if (hours == 0) {// 1小时内，x分钟前
                    int minutes = (int) (end - from) / (1000 * 60);
                    if (minutes <= 0) {
                        returnText = "刚刚";
                    } else {
                        returnText = minutes + "分钟前";
                    }
                } else {
                    returnText = hours + "小时前";
                }
            } else {// 超过一天显示 具体发布日期 2016-11-11
                returnText = sdf.format(beginDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnText;
    }

    /**
     * 传入yyyy-MM-dd HH:mm:ss格式字符串，发布时间显示规则：
     * 昨天
     * 今天
     * 超过一天显示 具体发布日期 2016-11-11
     *
     * @param
     * @return
     */
    public static String parseDate(String beginDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = new Date();
        String returnText = null;
        try {
            Date date = sdf.parse(beginDate);
            Calendar calendar = Calendar.getInstance();
            long intervalDays = 0;
            calendar.setTime(date);
            long begin = calendar.getTimeInMillis();
            calendar.setTime(endDate);
            long end = calendar.getTimeInMillis();
            long totalM = end - begin;
            if (totalM > 0) {
                intervalDays = totalM / (24 * 60 * 60 * 1000);
                if (intervalDays == 0) {
                    returnText = "今天";
                } else if (intervalDays == 1) {
                    returnText = "昨天";
                } else {
                    returnText = sdf.format(sdf.parse(beginDate)).substring(0, 10);
                }
            } else {
                returnText = sdf.format(sdf.parse(beginDate)).substring(0, 10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnText;
    }

    /**
     * 传入yyyy-MM-dd HH:mm:ss格式，发布时间显示规则：
     * 昨天
     * 今天
     * 超过一天显示 具体发布日期 2016-11-11
     *
     * @param
     * @return
     */
    public static String parseDate(Date beginDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = new Date();
        String returnText = null;
        try {
            Calendar calendar = Calendar.getInstance();
            long intervalDays = 0;
            calendar.setTime(beginDate);
            long begin = calendar.getTimeInMillis();
            calendar.setTime(endDate);
            long end = calendar.getTimeInMillis();
            long totalM = end - begin;
            if (totalM > 0) {
                intervalDays = totalM / (24 * 60 * 60 * 1000);
                if (intervalDays == 0) {
                    returnText = "今天";
                } else if (intervalDays == 1) {
                    returnText = "昨天";
                } else {
                    returnText = sdf.format(beginDate);
                }
            } else {
                returnText = sdf.format(beginDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnText;
    }

    /**
     * 格式化时间，只获取时分秒
     *
     * @param beginDate
     */
    public static String parseDateHMS(Date beginDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(beginDate);
        return dateString.split(" ")[1];
    }

    public static String parseDateHMS(String beginDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String returnText = null;
        try {
            String dateString = sdf.format(sdf.parse(beginDate));
            returnText = dateString.split(" ")[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnText;
    }

    public static void main(String[] args) {
//        System.out.println(parseDateShow("2017-05-04 16:27:30"));
//        System.out.println(parseDateShow("2017-04-04 16:27:30"));
//        System.out.println(parseDateShow("2017-05-04 13:20:30"));
//        System.out.println(parseDateShow("2017-05-04 13:20:30"));
//        System.out.println(parseDateShow(new Date()));

//        System.out.println(parseDate("2017-08-05 00:00:00"));
//        System.out.println(parseDate(new Date()));
        System.out.println(parseDateHMS("2017-08-05 00:00:00"));
//        System.out.println(parseDateHMS(new Date()));
    }
}
