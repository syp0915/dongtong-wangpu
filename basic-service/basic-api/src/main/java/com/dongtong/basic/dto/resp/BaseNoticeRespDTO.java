package com.dongtong.basic.dto.resp;

import java.io.Serializable;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.dto.resp
 * @Description :TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-11 19:49
 * version V1.0.0
 **/
public class BaseNoticeRespDTO implements Serializable{

    /**
     * 接收人Id
     */
    private Long receiveId;

    /**
     * 相应业务Id
     */
    private Long bussinessId;

    /**
     * 通知简要信息
     */
    private String digest;

    /**
     * 通知时间
     */
    private String notifyTime;

    /**
     * 通知状态
     */
    private Integer status;

    /**
     * 消息内容
     */
    private String Content;

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

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
