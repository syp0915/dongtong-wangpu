package com.dongtong.clerk.domain;

import com.shfc.common.httpbean.BaseBean;
import java.util.Date;

/**
 * @Package: com.dongtong.clerk.domain.HintOverTime.java
 * @Description: 线索超时表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author zhoumin
 * @date 2017/09/01 08:51
 * version v1.0.0
 */
public class HintOverTime extends BaseBean {
    /**
     * 线索id
     */
    private Long hintId;

    /**
     * 线索状态：1 拓铺员待核准  2 交易员待实勘
     */
    private Integer hintStatus;

    /**
     * 0：未超时 1：已超时  2:已处理
     */
    private Integer status;

    /**
     * 业务员id
     */
    private Long clerkId;

    /**
     * 线索超时时间
     */
    private Date overTime;

    /**
     * 线索超时提醒时间
     */
    private Date overTimeRemind;

    /**
     * 是否push：0 否 1是
     */
    private Integer isPush;

    /**
     * 过期业务员id
     */
    private Long expiredClerkId;

    /**
     * 获取线索id
     *
     * @return hint_id
     */
    public Long getHintId() {
        return hintId;
    }

    /**
     * 设置线索id
     *
     * @param hintId
     */
    public void setHintId(Long hintId) {
        this.hintId = hintId;
    }

    /**
     * 获取线索状态：1 拓铺员待核准  2 交易员待实勘
     *
     * @return hint_status
     */
    public Integer getHintStatus() {
        return hintStatus;
    }

    /**
     * 设置线索状态：1 拓铺员待核准  2 交易员待实勘
     *
     * @param hintStatus
     */
    public void setHintStatus(Integer hintStatus) {
        this.hintStatus = hintStatus;
    }

    /**
     * 获取0：未超时 1：已超时  2:已处理
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0：未超时 1：已超时  2:已处理
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取业务员id
     *
     * @return clerk_id
     */
    public Long getClerkId() {
        return clerkId;
    }

    /**
     * 设置业务员id
     *
     * @param clerkId
     */
    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    /**
     * 获取线索超时时间
     *
     * @return over_time
     */
    public Date getOverTime() {
        return overTime;
    }

    /**
     * 设置线索超时时间
     *
     * @param overTime
     */
    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    /**
     * 获取线索超时提醒时间
     *
     * @return over_time_remind
     */
    public Date getOverTimeRemind() {
        return overTimeRemind;
    }

    /**
     * 设置线索超时提醒时间
     *
     * @param overTimeRemind
     */
    public void setOverTimeRemind(Date overTimeRemind) {
        this.overTimeRemind = overTimeRemind;
    }

    /**
     * 获取是否push：0 否 1是
     *
     * @return is_push
     */
    public Integer getIsPush() {
        return isPush;
    }

    /**
     * 设置是否push：0 否 1是
     *
     * @param isPush
     */
    public void setIsPush(Integer isPush) {
        this.isPush = isPush;
    }

    /**
     * 获取过期业务员id
     *
     * @return expired_clerk_id
     */
    public Long getExpiredClerkId() {
        return expiredClerkId;
    }

    /**
     * 设置过期业务员id
     *
     * @param expiredClerkId
     */
    public void setExpiredClerkId(Long expiredClerkId) {
        this.expiredClerkId = expiredClerkId;
    }
}