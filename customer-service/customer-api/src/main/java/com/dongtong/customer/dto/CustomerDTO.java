package com.dongtong.customer.dto;

import java.io.Serializable;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-10 17:04
 **/
public class CustomerDTO implements Serializable {

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
     * 账号状态(0有效;1失效)
     */
    private Integer status;

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
}
