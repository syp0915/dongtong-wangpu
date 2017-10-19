package com.dongtong.topic.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.topic.domain.TopicLike.java
 * @Description: 生意圈点赞
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/04 16:17
 * version v1.0.0
 */
public class TopicLike extends BaseBean {
    /**
     * 生意圈id
     */
    private Long businessId;

    /**
     * 点赞人id
     */
    private Long likeId;

    /**
     * 点赞人类型：0-商户 1-业务员
     */
    private Integer likeType;

    /**
     * 获取生意圈id
     *
     * @return business_id
     */
    public Long getBusinessId() {
        return businessId;
    }

    /**
     * 设置生意圈id
     *
     * @param businessId
     */
    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    /**
     * 获取点赞人id
     *
     * @return like_id
     */
    public Long getLikeId() {
        return likeId;
    }

    /**
     * 设置点赞人id
     *
     * @param likeId
     */
    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    /**
     * 获取点赞人类型：0-商户 1-业务员
     *
     * @return like_type
     */
    public Integer getLikeType() {
        return likeType;
    }

    /**
     * 设置点赞人类型：0-商户 1-业务员
     *
     * @param likeType
     */
    public void setLikeType(Integer likeType) {
        this.likeType = likeType;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/04 16:17
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", businessId=").append(businessId);
        sb.append(", likeId=").append(likeId);
        sb.append(", likeType=").append(likeType);
        sb.append("]");
        return sb.toString();
    }
}