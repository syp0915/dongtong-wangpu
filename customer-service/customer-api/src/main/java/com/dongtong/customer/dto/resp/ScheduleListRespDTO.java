package com.dongtong.customer.dto.resp;

import com.dongtong.customer.dto.BasePageDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/4 下午4:14.
 */
public class ScheduleListRespDTO extends BasePageDTO implements Serializable {
    private List<ScheduleInfoBean> scheduleList;

    public List<ScheduleInfoBean> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<ScheduleInfoBean> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
