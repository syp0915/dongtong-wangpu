package com.dongtong.customer.query;

import com.dongtong.customer.dto.BasePageDTO;

import java.io.Serializable;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/4 下午2:53.
 */
public class ScheduleListReqQuery extends BasePageDTO implements Serializable {
    private Integer type;//0-未完成日程 1-历史日程

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
