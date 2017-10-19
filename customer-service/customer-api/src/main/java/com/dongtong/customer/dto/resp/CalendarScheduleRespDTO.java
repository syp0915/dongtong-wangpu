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
public class CalendarScheduleRespDTO implements Serializable{

    private Integer undoScheduleCount;
    private List<DateScheduleBean> dateList;

    public Integer getUndoScheduleCount() {
        return undoScheduleCount;
    }

    public void setUndoScheduleCount(Integer undoScheduleCount) {
        this.undoScheduleCount = undoScheduleCount;
    }

    public List<DateScheduleBean> getDateList() {
        return dateList;
    }

    public void setDateList(List<DateScheduleBean> dateList) {
        this.dateList = dateList;
    }
}
