package com.dongtong.basic.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.basic.domain.BaseMetroStation.java
 * @Description: 地铁站表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/04 16:10
 * version v1.0.0
 */
public class BaseMetroStation extends BaseBean {
    /**
     * 站名
     */
    private String stationName;

    /**
     * 所属地铁线路id
     */
    private Long metroid;

    /**
     * 创建者
     */
    private Long creator;

    /**
     * 地铁站经度
     */
    private Double baidulon;

    /**
     * 地铁站纬度
     */
    private Double baidulat;

    private String cityid;

    /**
     * 获取站名
     *
     * @return station_name
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * 设置站名
     *
     * @param stationName
     */
    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    /**
     * 获取所属地铁线路id
     *
     * @return metroId
     */
    public Long getMetroid() {
        return metroid;
    }

    /**
     * 设置所属地铁线路id
     *
     * @param metroid
     */
    public void setMetroid(Long metroid) {
        this.metroid = metroid;
    }

    /**
     * 获取创建者
     *
     * @return creator
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * 获取地铁站经度
     *
     * @return baiduLon
     */
    public Double getBaidulon() {
        return baidulon;
    }

    /**
     * 设置地铁站经度
     *
     * @param baidulon
     */
    public void setBaidulon(Double baidulon) {
        this.baidulon = baidulon;
    }

    /**
     * 获取地铁站纬度
     *
     * @return baiduLat
     */
    public Double getBaidulat() {
        return baidulat;
    }

    /**
     * 设置地铁站纬度
     *
     * @param baidulat
     */
    public void setBaidulat(Double baidulat) {
        this.baidulat = baidulat;
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
        sb.append(", stationName=").append(stationName);
        sb.append(", metroid=").append(metroid);
        sb.append(", creator=").append(creator);
        sb.append(", baidulon=").append(baidulon);
        sb.append(", baidulat=").append(baidulat);
        sb.append(", cityid=").append(cityid);
        sb.append("]");
        return sb.toString();
    }
}