package com.dongtong.topic.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.topic.domain.PretendUser.java
 * @Description: 
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author chen xiushen
 * @date 2017/08/14 10:12
 * version v1.0.0
 */
public class PretendUser extends BaseBean {
    /**
     * 头像
     */
    private String avatar;

    /**
     * 马甲名
     */
    private String name;

    /**
     * 签名
     */
    private String sign;

    /**
     * 马甲状态 1-可用 2不可用
     */
    private Boolean status;

    /**
     * 获取头像
     *
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像
     *
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * 获取马甲名
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置马甲名
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取签名
     *
     * @return sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * 设置签名
     *
     * @param sign
     */
    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    /**
     * 获取马甲状态 1-可用 2不可用
     *
     * @return status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置马甲状态 1-可用 2不可用
     *
     * @param status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }
}