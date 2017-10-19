package com.dongtong.customer.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.customer.domain.CustomerAttentionArea.java
 * @Description: 用户关注面积表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:41
 * version v1.0.0
 */
public class CustomerAttentionArea extends BaseBean {
    /**
     * 用户账户表ID
     */
    private Long customerId;

    /**
     * 最小值
     */
    private String minArea;

    /**
     * 最大值
     */
    private String maxArea;

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
     * 获取最小值
     *
     * @return min_area
     */
    public String getMinArea() {
        return minArea;
    }

    /**
     * 设置最小值
     *
     * @param minArea
     */
    public void setMinArea(String minArea) {
        this.minArea = minArea == null ? null : minArea.trim();
    }

    /**
     * 获取最大值
     *
     * @return max_area
     */
    public String getMaxArea() {
        return maxArea;
    }

    /**
     * 设置最大值
     *
     * @param maxArea
     */
    public void setMaxArea(String maxArea) {
        this.maxArea = maxArea == null ? null : maxArea.trim();
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 14:41
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
        sb.append(", minArea=").append(minArea);
        sb.append(", maxArea=").append(maxArea);
        sb.append("]");
        return sb.toString();
    }
}