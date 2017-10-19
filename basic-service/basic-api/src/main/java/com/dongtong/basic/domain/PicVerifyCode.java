package com.dongtong.basic.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.basic.domain.PicVerifyCode.java
 * @Description: 图片验证码表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author Jianguo Li
 * @date 2017/06/02 11:03
 * version v1.0.0
 */
public class PicVerifyCode extends BaseBean {
    /**
     * 验证码
     */
    private String verifyCode;

    /**
     * 接收手机号
     */
    private String userPhone;

    /**
     * 验证状态0-未验证 1-已验证 2-已过期
     */
    private Integer status;

    private Long creator;

    /**
     * 获取验证码
     *
     * @return verify_code
     */
    public String getVerifyCode() {
        return verifyCode;
    }

    /**
     * 设置验证码
     *
     * @param verifyCode
     */
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode == null ? null : verifyCode.trim();
    }

    /**
     * 获取接收手机号
     *
     * @return user_phone
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 设置接收手机号
     *
     * @param userPhone
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    /**
     * 获取验证状态0-未验证 1-已验证 2-已过期
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置验证状态0-未验证 1-已验证 2-已过期
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return creator
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * @Title toString
     * @Author Jianguo Li
     * @Date 2017/06/02 11:03
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", verifyCode=").append(verifyCode);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append("]");
        return sb.toString();
    }
}