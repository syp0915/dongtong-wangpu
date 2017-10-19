package com.dongtong.customer.query;

import java.io.Serializable;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/9 下午8:00.
 */
public class CalendarScheduleReqQuery implements Serializable {

    private Integer preDayCount;//前置天数

    public Integer getPreDayCount() {
        return preDayCount;
    }

    public void setPreDayCount(Integer preDayCount) {
        this.preDayCount = preDayCount;
    }
}
