package com.dongtong.shop.query;

/**
 * @Package com.dongtong.shop.query.MapClerkQuery
 * @Description: 业务端 区域板块层级店铺数量
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/9 9:10
 * version V1.0.0
 */
public class MapClerkQuery extends BaseMapQuery{
    private static final long serialVersionUID = 1365235850017491523L;

    /**
     * 查询类型
     * 0-（默认值）查询区域商铺数量
     * 1-查询板块商铺数量
     */
    private Integer type = 0;

    /**
     * 从区域点击查询区域下的板块
     */
    private Long districtId;


    /**
     * 0-（默认值）查询区域商铺数量
     * 1-查询板块商铺数量
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * 0-（默认值）查询区域商铺数量
     * 1-查询板块商铺数量
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 从区域点击查询区域下的板块
     */
    public Long getDistrictId() {
        return this.districtId;
    }

    /**
     * 从区域点击查询区域下的板块
     */
    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }
}
