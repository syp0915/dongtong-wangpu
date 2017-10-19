package com.dongtong.shop.query;

import com.shfc.common.encrypt.MD5Utils;

import java.util.List;

/**
 * @Package com.dongtong.shop.query.ShopCustomerQuery
 * @Description: 用户端商铺查询
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/4 15:10
 * version V1.0.0
 */
public class ShopCustomerQuery extends BaseShopQuery{
    private static final long serialVersionUID = -1769123847713169581L;

    /**
     * 筛选类型
     * M
     * 0-用户关注条件（需登录且不包含经营范围）  1-非用户关注条件
     */
    private Integer queryType;
    /**
     * 街铺主题类型
     * N
     * 0-今日更新商铺 1-无转让费  2-百平小铺（80-120）  3-临近地铁（1km)
     */
    private Integer shopType;
    /**
     * 排序方式
     * N
     * 1-最近更新  2-距离最近  3-联络最多 4-查看最多
     */
    private Integer sortType;
    /**
     * 用户关注的面积
     */
    private List<Integer> followAreaList;
    /**
     * 用户关注的行业
     */
    private List<Long> followVocationList;
    /**
     * 用户关注的板块
     */
    private List<String> followPlateList;
    /**
     * 筛选类型
     * M
     * 0-用户关注条件（需登录且不包含经营范围）  1-非用户关注条件
     */
    public Integer getQueryType() {
        return this.queryType;
    }

    /**
     * 筛选类型
     * M
     * 0-用户关注条件（需登录且不包含经营范围）  1-非用户关注条件
     */
    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    /**
     * 街铺主题类型
     * N
     * 0-今日更新商铺 1-无转让费  2-百平小铺（80-120）  3-临近地铁（1km)
     */
    public Integer getShopType() {
        return this.shopType;
    }

    /**
     * 街铺主题类型
     * N
     * 0-今日更新商铺 1-无转让费  2-百平小铺（80-120）  3-临近地铁（1km)
     */
    public void setShopType(Integer shopType) {
        this.shopType = shopType;
    }

    /**
     * 排序方式
     * N
     * 1-最近更新  2-距离最近  3-联络最多 4-查看最多
     */
    public Integer getSortType() {
        return this.sortType;
    }

    public List<Integer> getFollowAreaList() {
        return followAreaList;
    }

    public void setFollowAreaList(List<Integer> followAreaList) {
        this.followAreaList = followAreaList;
    }

    public List<Long> getFollowVocationList() {
        return followVocationList;
    }

    public void setFollowVocationList(List<Long> followVocationList) {
        this.followVocationList = followVocationList;
    }

    public List<String> getFollowPlateList() {
        return followPlateList;
    }

    public void setFollowPlateList(List<String> followPlateList) {
        this.followPlateList = followPlateList;
    }

    /**
     * 排序方式
     * N
     * 1-最近更新  2-距离最近  3-联络最多 4-查看最多
     */
    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }

    @Override
    public String toString() {
        return "ShopCustomerQuery{" +
                "queryType=" + queryType +
                ", shopType=" + shopType +
                ", sortType=" + sortType +
                ", longitude='" + getLongitude() + '\'' +
                ", latitude='" + getLatitude() + '\'' +
                ", districtId=" + getDistrictId() +
                ", blockId=" + getBlockId() +
                ", metroId=" + getMetroId() +
                ", stationId=" + getStationId() +
                ", rentList=" + getRentList() +
                ", transferList=" + getTransferList() +
                ", areaList=" + getAreaList() +
                ", width=" + getWidth() +
                ", featureTagList=" + getFeatureTagList() +
                ", supportTagList=" + getSupportTagList() +
                ", pageNumber=" + getPageNumber() +
                ", pageSize=" + getPageSize() +
                '}';
    }

    /**
     * 获取缓存id
     * @return
     */
    public String cacheKey(){
        return "ShopCustomerQuery_" + MD5Utils.encrypt(this.toString(), MD5Utils.MD5_KEY);
    }
}
