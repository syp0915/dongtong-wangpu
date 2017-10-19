package com.dongtong.customer.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.customer.domain.CustomerSignIn.java
 * @Description: 签到表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:45
 * version v1.0.0
 */
public class CustomerSignIn extends BaseBean {
    /**
     * 用户ID
     */
    private Long customerId;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 地址
     */
    private String address;

    /**
     * 获取用户ID
     *
     * @return customer_id
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置用户ID
     *
     * @param customerId
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取纬度
     *
     * @return latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    /**
     * 获取经度
     *
     * @return longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    /**
     * 获取地址
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 14:45
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
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", address=").append(address);
        sb.append("]");
        return sb.toString();
    }
}