package com.dongtong.clerk.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.customer.domain.Clerk.java
 * @Description: 业务员账户表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author Jianguo Li
 * @date 2017/05/17 10:02
 * version v1.0.0
 */
public class Clerk extends BaseBean {
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
     * 角色类型(0:业务员(交易) 1:运营 2:业务员(拓铺))
     */
    private Integer roleType;

    /**
     * 账号状态(0待审核;1审核通过;2不通过;3.审核中)
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
     * 获取角色类型(0业务员1管理员)
     *
     * @return role_type
     */
    public Integer getRoleType() {
        return roleType;
    }

    /**
     * 设置角色类型(0业务员1管理员)
     *
     * @param roleType
     */
    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    /**
     * 获取账号状态(0待审核;1审核通过;2不通过;3.审核中)
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置账号状态(0待审核;1审核通过;2不通过;3.审核中)
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

    /**
     * @Title toString
     * @Author Jianguo Li
     * @Date 2017/05/17 10:02
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", phone=").append(phone);
        sb.append(", realName=").append(realName);
        sb.append(", headPortrait=").append(headPortrait);
        sb.append(", invitationCode=").append(invitationCode);
        sb.append(", roleType=").append(roleType);
        sb.append(", status=").append(status);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", osType=").append(osType);
        sb.append("]");
        return sb.toString();
    }
}