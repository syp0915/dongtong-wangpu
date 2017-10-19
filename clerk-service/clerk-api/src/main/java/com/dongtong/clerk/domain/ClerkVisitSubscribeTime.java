package com.dongtong.clerk.domain;

import com.shfc.common.httpbean.BaseBean;

import java.util.Date;

/**
 * @Package: com.dongtong.clerk.domain.ClerkVisitSubscribeTime.java
 * @Description: 业务员修改约看时间记录表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:21
 * version v1.0.0
 */
public class ClerkVisitSubscribeTime extends BaseBean {
    /**
     * 约看表ID
     */
    private Long visitId;

    /**
     * 踩盘约看时间
     */
    private Date subscribeTime;

    /**
     * 获取约看表ID
     *
     * @return visit_id
     */
    public Long getVisitId() {
        return visitId;
    }

    /**
     * 设置约看表ID
     *
     * @param visitId
     */
    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }

    /**
     * 获取踩盘约看时间
     *
     * @return subscribe_time
     */
    public Date getSubscribeTime() {
        return subscribeTime;
    }

    /**
     * 设置踩盘约看时间
     *
     * @param subscribeTime
     */
    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 14:21
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", visitId=").append(visitId);
        sb.append(", subscribeTime=").append(subscribeTime);
        sb.append("]");
        return sb.toString();
    }
}