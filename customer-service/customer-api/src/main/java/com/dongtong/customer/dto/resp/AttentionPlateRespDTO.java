package com.dongtong.customer.dto.resp;

import java.io.Serializable;

/**
 * @description 关注区域
 * @package com.dongtong.customer.dto.resp
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/8/10 0010 16:40
 * @version v1.0.0
 */
public class AttentionPlateRespDTO implements Serializable {

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
    private Long districtId;

    /**
     * 关注的区域名称
     */
    private String districtName;

    /**
     * 关注的板块ID
     */
    private Long plateId;

    /**
     * 关注的板块名称
     */
    private String plateName;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Long getPlateId() {
        return plateId;
    }

    public void setPlateId(Long plateId) {
        this.plateId = plateId;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    @Override
    public String toString() {
        return "AttentionPlateRespDTO{" +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", districtId=" + districtId +
                ", districtName='" + districtName + '\'' +
                ", plateId=" + plateId +
                ", plateName='" + plateName + '\'' +
                '}';
    }
}
