package com.dongtong.shop.query;

import java.util.List;

/**
 * @Package com.dongtong.shop.query.MapClerkShopQuery
 * @Description: 业务端 商铺地图-店铺级别的地图
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/9 9:41
 * version V1.0.0
 */
public class MapClerkShopQuery extends BaseMapQuery {
    private static final long serialVersionUID = -8425810568653612434L;

    /**
     * 筛选条件-我的
     * 0-我的 1-全部
     */
    private Integer queryType;

    /**
     * 业务员id
     */
    private Long clerkId;

    /**
     * 筛选条件-状态
     * eg：[1,2] 1-出租中 2-暂不出租 3-已出租
     */
    private List<Integer> statusList;
    /**
     * 筛选条件-发布状态
     * [0,1] 0已上架、1已下架
     */
    private List<Integer> shelfList;
    /**
     * 板块id
     * 从板块点击查询商铺
     */
    private Long blockId;

    /**
     * 筛选条件-我的
     * 0-我的 1-全部
     */
    public Integer getQueryType() {
        return this.queryType;
    }

    /**
     * 筛选条件-我的
     * 0-我的 1-全部
     */
    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }
    /**
     * 获取属性 业务员id
     */
    public Long getClerkId() {
        return this.clerkId;
    }

    /**
     * 设置属性 业务员id
     */
    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }
    /**
     * 筛选条件-状态
     * eg：[1,2] 1：招租中 2：已下架  3：待认领 4：待出租
     */
    public List<Integer> getStatusList() {
        return this.statusList;
    }

    /**
     * 筛选条件-状态
     * eg：[1,2] 1：招租中 2：已下架  3：待认领 4：待出租
     */
    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }

    public List<Integer> getShelfList() {
        return shelfList;
    }

    public void setShelfList(List<Integer> shelfList) {
        this.shelfList = shelfList;
    }

    /**
     * 板块id
     * 从板块点击查询商铺
     */
    public Long getBlockId() {
        return this.blockId;
    }

    /**
     * 板块id
     * 从板块点击查询商铺
     */
    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

}
