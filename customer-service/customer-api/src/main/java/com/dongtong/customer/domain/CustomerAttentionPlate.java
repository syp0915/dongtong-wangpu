package com.dongtong.customer.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.shop.domain.CustomerAttentionPlate.java
 * @Description: 用户关注板块表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author xiehb
 * @date 2017/08/03 20:05
 * version v1.2.0
 */
public class CustomerAttentionPlate extends BaseBean {
    /**
     * 用户账户表ID
     */
    private Long customerId;

    /**
     * 关注的省份ID
     */
    private Long provinceId;

    /**
     * 关注的省份名称
     */
    private String provinceName;

    /**
     * 关注的城市ID
     */
    private Long cityId;

    /**
     * 关注的城市名称
     */
    private String cityName;

    /**
     * 关注的区域ID
     */
    private Long areaId;

    /**
     * 关注的区域名称
     */
    private String areaName;

    /**
     * 关注的板块ID
     */
    private Long plateId;

    /**
     * 关注的板块名称
     */
    private String plateName;

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
     * 获取关注的省份ID
     *
     * @return province_id
     */
    public Long getProvinceId() {
        return provinceId;
    }

    /**
     * 设置关注的省份ID
     *
     * @param provinceId
     */
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取关注的省份名称
     *
     * @return province_name
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 设置关注的省份名称
     *
     * @param provinceName
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * 获取关注的城市ID
     *
     * @return city_id
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     * 设置关注的城市ID
     *
     * @param cityId
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取关注的城市名称
     *
     * @return city_name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置关注的城市名称
     *
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * 获取关注的区域ID
     *
     * @return area_id
     */
    public Long getAreaId() {
        return areaId;
    }

    /**
     * 设置关注的区域ID
     *
     * @param areaId
     */
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取关注的区域名称
     *
     * @return area_name
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 设置关注的区域名称
     *
     * @param areaName
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * 获取关注的板块ID
     *
     * @return plate_id
     */
    public Long getPlateId() {
        return plateId;
    }

    /**
     * 设置关注的板块ID
     *
     * @param plateId
     */
    public void setPlateId(Long plateId) {
        this.plateId = plateId;
    }

    /**
     * 获取关注的板块名称
     *
     * @return plate_name
     */
    public String getPlateName() {
        return plateName;
    }

    /**
     * 设置关注的板块名称
     *
     * @param plateName
     */
    public void setPlateName(String plateName) {
        this.plateName = plateName == null ? null : plateName.trim();
    }

    /**
     * @Title toString
     * @Author xiehb
     * @Date 2017/08/03 20:05
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
        sb.append(", provinceId=").append(provinceId);
        sb.append(", provinceName=").append(provinceName);
        sb.append(", cityId=").append(cityId);
        sb.append(", cityName=").append(cityName);
        sb.append(", areaId=").append(areaId);
        sb.append(", areaName=").append(areaName);
        sb.append(", plateId=").append(plateId);
        sb.append(", plateName=").append(plateName);
        sb.append("]");
        return sb.toString();
    }
}