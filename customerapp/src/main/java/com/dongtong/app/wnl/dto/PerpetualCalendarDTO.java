package com.dongtong.app.wnl.dto;

import java.io.Serializable;

/**
 *
 * 万年历DTO
 *
 *
 * @Package com.shfc.adviser.wnl.dto.PerpetualCalendarDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/1/4 9:49
 * version V1.0.0
 */
public class PerpetualCalendarDTO implements Serializable {

    private static final long serialVersionUID = -2356719060128344113L;

    private String status;//状态

    private String msg;//返回成功信息

    private PerpetualCalendarResultDTO result;//结果信息

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PerpetualCalendarResultDTO getResult() {
        return result;
    }

    public void setResult(PerpetualCalendarResultDTO result) {
        this.result = result;
    }
}
