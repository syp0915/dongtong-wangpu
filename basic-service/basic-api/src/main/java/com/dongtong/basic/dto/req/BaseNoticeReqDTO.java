package com.dongtong.basic.dto.req;

import java.io.Serializable;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.dto.resp
 * @Description
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-10 10:00
 * version V1.0.0
 **/
public class BaseNoticeReqDTO implements Serializable{
    /**
     * 接收类型
     */
    private Integer receiveType;

    /**
     * 接收人Id
     */
    private Long receiveId;

    /**
     * 所对应业务的Id
     */
    private Long bussinessId;

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    public Long getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Long receiveId) {
        this.receiveId = receiveId;
    }

    public Long getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(Long bussinessId) {
        this.bussinessId = bussinessId;
    }
}
