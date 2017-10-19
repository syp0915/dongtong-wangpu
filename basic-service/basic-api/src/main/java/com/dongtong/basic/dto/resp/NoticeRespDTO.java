package com.dongtong.basic.dto.resp;

import com.dongtong.basic.domain.BaseNotification;

import java.io.Serializable;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.dto.resp
 * @Description
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-23 18:33
 * version V1.0.0
 **/
public class NoticeRespDTO extends BaseNotification implements Serializable{

    /**
     * 格式化后的日期
     */
    private String creatTimeFormat;

    public String getCreatTimeFormat() {
        return creatTimeFormat;
    }

    public void setCreatTimeFormat(String creatTimeFormat) {
        this.creatTimeFormat = creatTimeFormat;
    }
}
