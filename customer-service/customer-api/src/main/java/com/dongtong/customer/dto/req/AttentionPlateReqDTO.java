package com.dongtong.customer.dto.req;

import java.io.Serializable;

/**
 * @description 关注区域
 * @package com.dongtong.customer.dto.req
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/8/10 0010 16:39
 * @version v1.0.0
 */
public class AttentionPlateReqDTO implements Serializable {

    private Long provinceId;

    private String provinceName;

    private Long cityId;

    private String cityName;

    private Long districtId;

    private String districtName;

    private Long plateId;

    private String plateName;

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

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
        return "AttentionPlateReqDTO{" +
                "provinceId=" + provinceId +
                ", provinceName='" + provinceName + '\'' +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", districtId=" + districtId +
                ", districtName='" + districtName + '\'' +
                ", plateId=" + plateId +
                ", plateName='" + plateName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttentionPlateReqDTO that = (AttentionPlateReqDTO) o;

        if (provinceId != null ? !provinceId.equals(that.provinceId) : that.provinceId != null) return false;
        if (provinceName != null ? !provinceName.equals(that.provinceName) : that.provinceName != null) return false;
        if (cityId != null ? !cityId.equals(that.cityId) : that.cityId != null) return false;
        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null) return false;
        if (districtId != null ? !districtId.equals(that.districtId) : that.districtId != null) return false;
        if (districtName != null ? !districtName.equals(that.districtName) : that.districtName != null) return false;
        if (plateId != null ? !plateId.equals(that.plateId) : that.plateId != null) return false;
        return plateName != null ? plateName.equals(that.plateName) : that.plateName == null;
    }

    @Override
    public int hashCode() {
        int result = provinceId != null ? provinceId.hashCode() : 0;
        result = 31 * result + (provinceName != null ? provinceName.hashCode() : 0);
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (districtId != null ? districtId.hashCode() : 0);
        result = 31 * result + (districtName != null ? districtName.hashCode() : 0);
        result = 31 * result + (plateId != null ? plateId.hashCode() : 0);
        result = 31 * result + (plateName != null ? plateName.hashCode() : 0);
        return result;
    }
}
