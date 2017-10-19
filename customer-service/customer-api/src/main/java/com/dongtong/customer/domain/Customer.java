package com.dongtong.customer.domain;

import com.shfc.common.httpbean.BaseBean;

import java.util.Date;

/**
 * @Package: com.dongtong.customer.domain.Customer.java
 * @Description: 用户账户表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author Jianguo Li
 * @date 2017/05/17 10:02
 * version v1.0.0
 */
public class Customer extends BaseBean {
    /**
     * 手机号(登录账户)
     */
    private String phone;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 邀请码
     */
    private String invitationCode;
    /**
     * 是否禁言(0否;1是)
     */
    private Integer isBanned;

    /**
     * 账号状态(0有效;1失效)
     */
    private Integer status;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 操作系统类型0-iOS 1-Android
     */
    private Integer osType;

    /**
     * 账户来源(1-app;2-扫码)
     */
    private Integer source;

    private String nickName;

    private String signature;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 获取手机号(登录账户)
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号(登录账户)
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取真实姓名
     *
     * @return real_name
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * 获取头像
     *
     * @return head_portrait
     */
    public String getHeadPortrait() {
        return headPortrait;
    }

    /**
     * 设置头像
     *
     * @param headPortrait
     */
    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait == null ? null : headPortrait.trim();
    }

    /**
     * 获取邀请码
     *
     * @return invitation_code
     */
    public String getInvitationCode() {
        return invitationCode;
    }

    /**
     * 设置邀请码
     *
     * @param invitationCode
     */
    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode == null ? null : invitationCode.trim();
    }

    /**
     * 获取账号状态(0有效;1失效)
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置账号状态(0有效;1失效)
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取设备id
     *
     * @return device_id
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备id
     *
     * @param deviceId
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    /**
     * 获取操作系统类型0-iOS 1-Android
     *
     * @return os_type
     */
    public Integer getOsType() {
        return osType;
    }

    /**
     * 设置操作系统类型0-iOS 1-Android
     *
     * @param osType
     */
    public void setOsType(Integer osType) {
        this.osType = osType;
    }

    public Integer getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(Integer isBanned) {
        this.isBanned = isBanned;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "phone='" + phone + '\'' +
                ", realName='" + realName + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", invitationCode='" + invitationCode + '\'' +
                ", isBanned=" + isBanned +
                ", status=" + status +
                ", deviceId='" + deviceId + '\'' +
                ", osType=" + osType +
                ", source=" + source +
                ", nickName='" + nickName + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}