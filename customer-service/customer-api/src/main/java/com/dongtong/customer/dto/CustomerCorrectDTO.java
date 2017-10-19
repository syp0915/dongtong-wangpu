package com.dongtong.customer.dto;

import com.dongtong.customer.domain.CustomerShopCorrect;

/**
 * @Package com.dongtong.customer.dto.req
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhoumin
 * @date 17/5/15 下午1:38
 * version V1.0.0
 */
public class CustomerCorrectDTO extends CustomerShopCorrect {
    private Long messageId;//短信验证码id
    private String smsVerifyCode;//短信验证码
    private Long picVerifyId;//图片验证码id
    private String picVerifyCode;//图片验证码

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getSmsVerifyCode() {
        return smsVerifyCode;
    }

    public void setSmsVerifyCode(String smsVerifyCode) {
        this.smsVerifyCode = smsVerifyCode;
    }

    public Long getPicVerifyId() {
        return picVerifyId;
    }

    public void setPicVerifyId(Long picVerifyId) {
        this.picVerifyId = picVerifyId;
    }

    public String getPicVerifyCode() {
        return picVerifyCode;
    }

    public void setPicVerifyCode(String picVerifyCode) {
        this.picVerifyCode = picVerifyCode;
    }
}
