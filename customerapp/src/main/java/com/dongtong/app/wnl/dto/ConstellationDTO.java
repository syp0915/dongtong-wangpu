package com.dongtong.app.wnl.dto;

import java.io.Serializable;

/**
 * @Package com.shfc.adviser.wnl.dto.ConstellationDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/1/4 10:12
 * version V1.0.0
 */
public class ConstellationDTO implements Serializable {
    private static final long serialVersionUID = -2356719060128344113L;

    private String status;//状态

    private String msg;//返回成功信息

    private ConstellationResultDTO result;//结果信息

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

    public ConstellationResultDTO getResult() {
        return result;
    }

    public void setResult(ConstellationResultDTO result) {
        this.result = result;
    }
}
