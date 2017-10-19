package com.dongtong.customer.dto.resp;

import java.io.Serializable;

/**
 * @Package com.dongtong.customer.dto.resp.IndexStatisticsRespDTO
 * @Description: 首页统计DTO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/17 9:41
 * version V1.0.0
 */
public class IndexStatisticsRespDTO implements Serializable {
    private Integer issuerCount;	//发布数量
    private Integer visitCount;	// 约看数量
    private Integer collectedCount;	//收藏数量
    private Integer browseCount;	//浏览数量
    private Integer scheduleCount; //未来日程数量

    public Integer getIssuerCount() {
        return issuerCount;
    }

    public void setIssuerCount(Integer issuerCount) {
        this.issuerCount = issuerCount;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getCollectedCount() {
        return collectedCount;
    }

    public void setCollectedCount(Integer collectedCount) {
        this.collectedCount = collectedCount;
    }

    public Integer getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(Integer browseCount) {
        this.browseCount = browseCount;
    }

    public Integer getScheduleCount() {
        return scheduleCount;
    }

    public void setScheduleCount(Integer scheduleCount) {
        this.scheduleCount = scheduleCount;
    }
}
