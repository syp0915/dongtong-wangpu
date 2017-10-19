package com.dongtong.customer.dto;

import java.io.Serializable;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-16 10:00
 **/
public class VerifyDTO implements Serializable {

    private String smsVerifyCode;//短信验证码
    private Long messageId;
    private String picVerifyCode;//图片验证码
    private Long picVerifyId;


    public String getSmsVerifyCode() {
        return smsVerifyCode;
    }

    public void setSmsVerifyCode(String smsVerifyCode) {
        this.smsVerifyCode = smsVerifyCode;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getPicVerifyCode() {
        return picVerifyCode;
    }

    public void setPicVerifyCode(String picVerifyCode) {
        this.picVerifyCode = picVerifyCode;
    }

    public Long getPicVerifyId() {
        return picVerifyId;
    }

    public void setPicVerifyId(Long picVerifyId) {
        this.picVerifyId = picVerifyId;
    }
}
