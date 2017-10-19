package com.dongtong.customer.dto.req;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sunyaping
 * @Package com.dongtong.customer.dto.req
 * @Description :建议反馈请求参数
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-10 15:39
 * version V1.0.0
 **/
public class FeedBackReqDTO implements Serializable{

    /**
     * 用户ID
     */
    private Long customerId;

    /**
     * 反馈信息
     */
    private String feedback;

    /**
     * 创建时间
     */
    private Date createTime;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
