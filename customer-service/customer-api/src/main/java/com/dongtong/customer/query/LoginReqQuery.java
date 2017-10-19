package com.dongtong.customer.query;

import java.io.Serializable;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/4 上午10:34.
 */

public class LoginReqQuery implements Serializable {
    private String userPhone;//手机号
    private String smsVerifyCode;//短信验证码
    private String picVerifyCode;//图片验证码
    private Long messageId;//消息id
    private Long picVerifyId;//图片验证码id
    private String inviteCode;//邀请码
    private Integer osType;//0-iOS 1-Android
    private String deviceId;//设备id

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getSmsVerifyCode() {
        return smsVerifyCode;
    }

    public void setSmsVerifyCode(String smsVerifyCode) {
        this.smsVerifyCode = smsVerifyCode;
    }

    public String getPicVerifyCode() {
        return picVerifyCode;
    }

    public void setPicVerifyCode(String picVerifyCode) {
        this.picVerifyCode = picVerifyCode;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getPicVerifyId() {
        return picVerifyId;
    }

    public void setPicVerifyId(Long picVerifyId) {
        this.picVerifyId = picVerifyId;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Integer getOsType() {
        return osType;
    }

    public void setOsType(Integer osType) {
        this.osType = osType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
