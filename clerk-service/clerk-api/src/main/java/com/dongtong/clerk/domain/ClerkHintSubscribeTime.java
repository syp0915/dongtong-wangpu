package com.dongtong.clerk.domain;

import com.shfc.common.httpbean.BaseBean;

import java.util.Date;

/**
 * @Package: com.dongtong.clerk.domain.ClerkHintSubscribeTime.java
 * @Description: 业务员修改线索预约时间记录表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:19
 * version v1.0.0
 */
public class ClerkHintSubscribeTime extends BaseBean {
    /**
     * 线索表ID
     */
    private Long hintId;

    /**
     * 线索约看时间
     */
    private Date subscribeTime;

    /**
     * 获取线索表ID
     *
     * @return hint_id
     */
    public Long getHintId() {
        return hintId;
    }

    /**
     * 设置线索表ID
     *
     * @param hintId
     */
    public void setHintId(Long hintId) {
        this.hintId = hintId;
    }

    /**
     * 获取线索约看时间
     *
     * @return subscribe_time
     */
    public Date getSubscribeTime() {
        return subscribeTime;
    }

    /**
     * 设置线索约看时间
     *
     * @param subscribeTime
     */
    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 14:19
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hintId=").append(hintId);
        sb.append(", subscribeTime=").append(subscribeTime);
        sb.append("]");
        return sb.toString();
    }
}