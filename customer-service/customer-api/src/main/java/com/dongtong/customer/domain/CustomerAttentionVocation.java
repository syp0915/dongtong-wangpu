package com.dongtong.customer.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.shop.domain.CustomerAttentionVocation.java
 * @Description: 用户关注行业表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author xiehb
 * @date 2017/08/03 18:32
 * version v1.2.0
 */
public class CustomerAttentionVocation extends BaseBean {
    /**
     * 用户账户表ID
     */
    private Long customerId;

    /**
     * 行业ID
     */
    private Long vocationId;

    /**
     * 行业名称
     */
    private String vocationName;

    /**
     * 获取用户账户表ID
     *
     * @return customer_id
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置用户账户表ID
     *
     * @param customerId
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取行业ID
     *
     * @return vocation_id
     */
    public Long getVocationId() {
        return vocationId;
    }

    /**
     * 设置行业ID
     *
     * @param vocationId
     */
    public void setVocationId(Long vocationId) {
        this.vocationId = vocationId;
    }

    /**
     * 获取行业名称
     *
     * @return vocation_name
     */
    public String getVocationName() {
        return vocationName;
    }

    /**
     * 设置行业名称
     *
     * @param vocationName
     */
    public void setVocationName(String vocationName) {
        this.vocationName = vocationName == null ? null : vocationName.trim();
    }

    /**
     * @Title toString
     * @Author xiehb
     * @Date 2017/08/03 18:32
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", customerId=").append(customerId);
        sb.append(", vocationId=").append(vocationId);
        sb.append(", vocationName=").append(vocationName);
        sb.append("]");
        return sb.toString();
    }
}