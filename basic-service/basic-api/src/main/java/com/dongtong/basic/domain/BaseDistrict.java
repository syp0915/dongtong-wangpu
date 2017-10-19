package com.dongtong.basic.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.basic.domain.BaseDistrict.java
 * @Description: 区域
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/04 16:10
 * version v1.0.0
 */
public class BaseDistrict extends BaseBean {
    /**
     * 区县名称
     */
    private String districtName;

    private String cityid;

    /**
     * 经纬度
     */
    private String lonLat;

    /**
     * 百度经度
     */
    private String baiduLon;

    /**
     * 百度纬度
     */
    private String baiduLat;

    /**
     * 简拼
     */
    private String abbreviation;

    /**
     * 全拼
     */
    private String fullName;

    /**
     * 获取区县名称
     *
     * @return district_name
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * 设置区县名称
     *
     * @param districtName
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    /**
     * @return cityId
     */
    public String getCityid() {
        return cityid;
    }

    /**
     * @param cityid
     */
    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    /**
     * 获取经纬度
     *
     * @return lon_lat
     */
    public String getLonLat() {
        return lonLat;
    }

    /**
     * 设置经纬度
     *
     * @param lonLat
     */
    public void setLonLat(String lonLat) {
        this.lonLat = lonLat == null ? null : lonLat.trim();
    }

    /**
     * 获取百度经度
     *
     * @return baidu_lon
     */
    public String getBaiduLon() {
        return baiduLon;
    }

    /**
     * 设置百度经度
     *
     * @param baiduLon
     */
    public void setBaiduLon(String baiduLon) {
        this.baiduLon = baiduLon == null ? null : baiduLon.trim();
    }

    /**
     * 获取百度纬度
     *
     * @return baidu_lat
     */
    public String getBaiduLat() {
        return baiduLat;
    }

    /**
     * 设置百度纬度
     *
     * @param baiduLat
     */
    public void setBaiduLat(String baiduLat) {
        this.baiduLat = baiduLat == null ? null : baiduLat.trim();
    }

    /**
     * 获取简拼
     *
     * @return abbreviation
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * 设置简拼
     *
     * @param abbreviation
     */
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation == null ? null : abbreviation.trim();
    }

    /**
     * 获取全拼
     *
     * @return full_name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置全拼
     *
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/04 16:10
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", districtName=").append(districtName);
        sb.append(", cityid=").append(cityid);
        sb.append(", lonLat=").append(lonLat);
        sb.append(", baiduLon=").append(baiduLon);
        sb.append(", baiduLat=").append(baiduLat);
        sb.append(", abbreviation=").append(abbreviation);
        sb.append(", fullName=").append(fullName);
        sb.append("]");
        return sb.toString();
    }
}