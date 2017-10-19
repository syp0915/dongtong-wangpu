package com.dongtong.customer.dto.req;

import java.io.Serializable;

public class UpdatePhoneVerifyReqDTO implements Serializable {

    private Long customerId;
    private String newPhone;
    private String oldPhone;
    private String smsVerifyCode;//短信验证码
    private Long messageId;
    private String picVerifyCode;//图片验证码
    private Long picVerifyId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

    public String getOldPhone() {
        return oldPhone;
    }

    public void setOldPhone(String oldPhone) {
        this.oldPhone = oldPhone;
    }

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

    @Override
    public String toString() {
        return "UpdatePhoneVerifyReqDTO{" +
                "customerId=" + customerId +
                ", newPhone='" + newPhone + '\'' +
                ", oldPhone='" + oldPhone + '\'' +
                ", smsVerifyCode='" + smsVerifyCode + '\'' +
                ", messageId=" + messageId +
                ", picVerifyCode='" + picVerifyCode + '\'' +
                ", picVerifyId=" + picVerifyId +
                '}';
    }
}
