package com.dongtong.basic.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.basic.domain.BaseBlock.java
 * @Description: 板块
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/04 16:09
 * version v1.0.0
 */
public class BaseBlock extends BaseBean {
    /**
     * 板块名称
     */
    private String blockName;

    private Long districtId;

    /**
     * 经纬度
     */
    private String lonLat;

    /**
     * 经度
     */
    private String baiduLon;

    /**
     * 纬度
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
     * 获取板块名称
     *
     * @return block_name
     */
    public String getBlockName() {
        return blockName;
    }

    /**
     * 设置板块名称
     *
     * @param blockName
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName == null ? null : blockName.trim();
    }

    /**
     * @return district_id
     */
    public Long getDistrictId() {
        return districtId;
    }

    /**
     * @param districtId
     */
    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
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
     * 获取经度
     *
     * @return baidu_lon
     */
    public String getBaiduLon() {
        return baiduLon;
    }

    /**
     * 设置经度
     *
     * @param baiduLon
     */
    public void setBaiduLon(String baiduLon) {
        this.baiduLon = baiduLon == null ? null : baiduLon.trim();
    }

    /**
     * 获取纬度
     *
     * @return baidu_lat
     */
    public String getBaiduLat() {
        return baiduLat;
    }

    /**
     * 设置纬度
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
     * @Date 2017/05/04 16:09
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", blockName=").append(blockName);
        sb.append(", districtId=").append(districtId);
        sb.append(", lonLat=").append(lonLat);
        sb.append(", baiduLon=").append(baiduLon);
        sb.append(", baiduLat=").append(baiduLat);
        sb.append(", abbreviation=").append(abbreviation);
        sb.append(", fullName=").append(fullName);
        sb.append("]");
        return sb.toString();
    }
}