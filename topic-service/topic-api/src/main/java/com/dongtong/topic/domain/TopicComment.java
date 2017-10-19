package com.dongtong.topic.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.topic.domain.TopicComment.java
 * @Description: 生意圈评论
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/04 16:16
 * version v1.0.0
 */
public class TopicComment extends BaseBean {
    /**
     * 生意圈id
     */
    private Long businessId;

    /**
     * 评论人id
     */
    private Long commentatorId;

    /**
     * 评论人类型：0-商户 1-业务员
     */
    private Integer commentatorType;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 回复的评论id
     */
    private Long parentId;

    /**
     * 1-评论 2-回复评论
     */
    private Integer isReply;

    /**
     * 1-未删除 2-删除
     */
    private Integer isDel;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getReply() {
        return isReply;
    }

    public void setReply(Integer reply) {
        isReply = reply;
    }

    public Integer getDel() {
        return isDel;
    }

    public void setDel(Integer del) {
        isDel = del;
    }

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
     * 获取评论人id
     *
     * @return commentator_id
     */
    public Long getCommentatorId() {
        return commentatorId;
    }

    /**
     * 设置评论人id
     *
     * @param commentatorId
     */
    public void setCommentatorId(Long commentatorId) {
        this.commentatorId = commentatorId;
    }

    /**
     * 获取评论人类型：0-商户 1-业务员
     *
     * @return commentator_type
     */
    public Integer getCommentatorType() {
        return commentatorType;
    }

    /**
     * 设置评论人类型：0-商户 1-业务员
     *
     * @param commentatorType
     */
    public void setCommentatorType(Integer commentatorType) {
        this.commentatorType = commentatorType;
    }

    /**
     * 获取评论内容
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/04 16:16
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
        sb.append(", commentatorId=").append(commentatorId);
        sb.append(", commentatorType=").append(commentatorType);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}