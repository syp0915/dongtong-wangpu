package com.dongtong.customer.domain;

import com.shfc.common.httpbean.BaseBean;

import java.util.Date;

/**
 * @Package: com.dongtong.customer.domain.CustomerSignTimeHistory.java
 * @Description: 商铺签约时间变更记录
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:46
 * version v1.0.0
 */
public class CustomerSignTimeHistory extends BaseBean {
    /**
     * 签约id
     */
    private Long signId;

    /**
     * 约见时间
     */
    private Date appointTime;

    /**
     * 获取签约id
     *
     * @return sign_id
     */
    public Long getSignId() {
        return signId;
    }

    /**
     * 设置签约id
     *
     * @param signId
     */
    public void setSignId(Long signId) {
        this.signId = signId;
    }

    /**
     * 获取约见时间
     *
     * @return appoint_time
     */
    public Date getAppointTime() {
        return appointTime;
    }

    /**
     * 设置约见时间
     *
     * @param appointTime
     */
    public void setAppointTime(Date appointTime) {
        this.appointTime = appointTime;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 14:46
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", signId=").append(signId);
        sb.append(", appointTime=").append(appointTime);
        sb.append("]");
        return sb.toString();
    }
}