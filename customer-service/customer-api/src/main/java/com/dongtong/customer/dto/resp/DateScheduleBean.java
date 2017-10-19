package com.dongtong.customer.dto.resp;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/9 下午7:56.
 */
public class DateScheduleBean implements Serializable{
    private String dateString;//日期
    private List<ScheduleInfoBean> scheduleList;//日程列表

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public List<ScheduleInfoBean> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<ScheduleInfoBean> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
