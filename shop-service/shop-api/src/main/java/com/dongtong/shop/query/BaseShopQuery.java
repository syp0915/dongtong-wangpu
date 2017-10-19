package com.dongtong.shop.query;

import java.util.List;

/**
 * @Package com.dongtong.shop.query.BaseShopQuery
 * @Description: 商铺公共查询参数
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/8 13:23
 * version V1.0.0
 */
public class BaseShopQuery extends BaseQuery{
    private static final long serialVersionUID = -8692550117875661537L;

    /**
     * 当前位置经度
     */
    private String longitude;
    /**
     * 当前位置纬度
     */
    private String latitude;

    /**
     * 区域id
     */
    private Long districtId;
    /**
     * 板块id
     */
    private Long blockId;
    /**
     * 地铁线路id
     */
    private Long metroId;
    /**
     * 地铁站id
     */
    private Long stationId;
    /**
     * 租金集合
     * eg：[1,2]  1：五千以下  2：5千-1万 3:1-2万 4:2-5万 5：5-10万 6:10万以上
     */
    private List<Integer> rentList;
    /**
     * 转让费集合
     * eg：[1,2] 1：无转让费 2：5万以下 3:5-10万 4:10-20万 5：20万以上 6：面议
     */
    private List<Integer> transferList;
    /**
     * 面积集合
     * eg：[1,2] 1：20m²以下 2：20-50m²  3：51-100m² 4：101-200m²  5：200-500m²  6:500-1000m²  7:1000m²以上
     */
    private List<Integer> areaList;
    /**
     * 面宽数据
     */
    private Float width;
    /**
     * 特色标签集合
     * [1,2]
     */
    private List<Long> featureTagList;
    /**
     * 配套标签集合
     * [1,2]
     */
    private List<Long> supportTagList;


    /**
     * 获取属性 当前位置经度
     */
    public String getLongitude() {
        return this.longitude;
    }

    /**
     * 设置属性 当前位置经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取属性 当前位置纬度
     */
    public String getLatitude() {
        return this.latitude;
    }

    /**
     * 设置属性 当前位置纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取属性 区域id
     */
    public Long getDistrictId() {
        return this.districtId;
    }

    /**
     * 设置属性 区域id
     */
    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    /**
     * 获取属性 板块id
     */
    public Long getBlockId() {
        return this.blockId;
    }

    /**
     * 设置属性 板块id
     */
    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    /**
     * 获取属性 地铁线路id
     */
    public Long getMetroId() {
        return this.metroId;
    }

    /**
     * 设置属性 地铁线路id
     */
    public void setMetroId(Long metroId) {
        this.metroId = metroId;
    }

    /**
     * 获取属性 地铁站id
     */
    public Long getStationId() {
        return this.stationId;
    }

    /**
     * 设置属性 地铁站id
     */
    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    /**
     * 租金集合
     * eg：[1,2]  1：五千以下  2：5千-1万 3:1-2万 4:2-5万 5：5-10万 6:10万以上
     */
    public List<Integer> getRentList() {
        return this.rentList;
    }

    /**
     * 租金集合
     * eg：[1,2]  1：五千以下  2：5千-1万 3:1-2万 4:2-5万 5：5-10万 6:10万以上
     */
    public void setRentList(List<Integer> rentList) {
        this.rentList = rentList;
    }

    /**
     * 转让费集合
     * eg：[1,2] 1：无转让费 2：5万以下 3:5-10万 4:10-20万 5：20万以上 6：面议
     */
    public List<Integer> getTransferList() {
        return this.transferList;
    }

    /**
     * 转让费集合
     * eg：[1,2] 1：无转让费 2：5万以下 3:5-10万 4:10-20万 5：20万以上 6：面议
     */
    public void setTransferList(List<Integer> transferList) {
        this.transferList = transferList;
    }

    /**
     * 面积集合
     * eg：[1,2] 1：20m²以下 2：20-50m²  3：51-100m² 4：101-200m²  5：200-500m²  6:500-1000m²  7:1000m²以上
     */
    public List<Integer> getAreaList() {
        return this.areaList;
    }

    /**
     * 面积集合
     * eg：[1,2] 1：20m²以下 2：20-50m²  3：51-100m² 4：101-200m²  5：200-500m²  6:500-1000m²  7:1000m²以上
     */
    public void setAreaList(List<Integer> areaList) {
        this.areaList = areaList;
    }

    /**
     * 获取属性 面宽数据
     */
    public Float getWidth() {
        return this.width;
    }

    /**
     * 设置属性 面宽数据
     */
    public void setWidth(Float width) {
        this.width = width;
    }

    /**
     * 特色标签集合
     * [1,2]
     */
    public List<Long> getFeatureTagList() {
        return this.featureTagList;
    }

    /**
     * 特色标签集合
     * [1,2]
     */
    public void setFeatureTagList(List<Long> featureTagList) {
        this.featureTagList = featureTagList;
    }

    /**
     * 配套标签集合
     * [1,2]
     */
    public List<Long> getSupportTagList() {
        return this.supportTagList;
    }

    /**
     * 配套标签集合
     * [1,2]
     */
    public void setSupportTagList(List<Long> supportTagList) {
        this.supportTagList = supportTagList;
    }
}
