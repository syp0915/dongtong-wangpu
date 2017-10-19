package com.dongtong.clerk.dto.resp;

import java.io.Serializable;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.dto.resp
 * @Description
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-23 18:47
 * version V1.0.0
 **/
public class ReleaseTotalRespDTO implements Serializable{
    /**
     * 寻租人数
     */
    private Integer releaseTotal;

    /**
     * 平均天人数
     */
    private Integer avgPeople;

    public Integer getReleaseTotal() {
        return releaseTotal;
    }

    public void setReleaseTotal(Integer releaseTotal) {
        this.releaseTotal = releaseTotal;
    }

    public Integer getAvgPeople() {
        return avgPeople;
    }

    public void setAvgPeople(Integer avgPeople) {
        this.avgPeople = avgPeople;
    }
}
