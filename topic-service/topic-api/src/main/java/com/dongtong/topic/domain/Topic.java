package com.dongtong.topic.domain;

import com.shfc.common.httpbean.BaseBean;

import java.util.Date;

/**
 * @Package: com.dongtong.topic.domain.Topic.java
 * @Description: 生意圈
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/04 16:16
 * version v1.0.0
 */
public class Topic extends BaseBean {
    /**
     * 发布人id
     */
    private Long publisherId;

    /**
     * 发布人类型：0-商户 1-业务员
     */
    private Integer publisherType;

    /**
     * 发布类型：0-帖子 1-资讯
     */
    private Integer publishType;

    /**
     * 标题
     */
    private String title;


    /**
     * 类型：0-普通 1-置顶
     */
    private Integer kind;

    /**
     * 超链接url
     */
    private String hyperlinkUrl;

    /**
     * 是否删除：0-是 1-否
     */
    private Integer isDel;

    /**
     * 内容
     */
    private String content;

    /**
     * 置顶时间
     */
    private Date kindTime;


    /**
     * v1.2新增
     */
    private Date timer;

    private Long thirdArticleId;

    public Date getTimer() {
        return timer;
    }

    public void setTimer(Date timer) {
        this.timer = timer;
    }

    public Date getKindTime() {
        return kindTime;
    }

    public void setKindTime(Date kindTime) {
        this.kindTime = kindTime;
    }

    public Long getThirdArticleId() {
        return thirdArticleId;
    }

    public void setThirdArticleId(Long thirdArticleId) {
        this.thirdArticleId = thirdArticleId;
    }

    /**
     * 获取发布人id
     *
     * @return publisher_id
     */
    public Long getPublisherId() {
        return publisherId;
    }

    /**
     * 设置发布人id
     *
     * @param publisherId
     */
    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    /**
     * 获取发布人类型：0-商户 1-业务员
     *
     * @return publisher_type
     */
    public Integer getPublisherType() {
        return publisherType;
    }

    /**
     * 设置发布人类型：0-商户 1-业务员
     *
     * @param publisherType
     */
    public void setPublisherType(Integer publisherType) {
        this.publisherType = publisherType;
    }

    /**
     * 获取发布类型：0-帖子 1-资讯
     *
     * @return publish_type
     */
    public Integer getPublishType() {
        return publishType;
    }

    /**
     * 设置发布类型：0-帖子 1-资讯
     *
     * @param publishType
     */
    public void setPublishType(Integer publishType) {
        this.publishType = publishType;
    }

    /**
     * 获取标题
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取类型：0-普通 1-置顶
     *
     * @return kind
     */
    public Integer getKind() {
        return kind;
    }

    /**
     * 设置类型：0-普通 1-置顶
     *
     * @param kind
     */
    public void setKind(Integer kind) {
        this.kind = kind;
    }

    /**
     * 获取超链接url
     *
     * @return hyperlink_url
     */
    public String getHyperlinkUrl() {
        return hyperlinkUrl;
    }

    /**
     * 设置超链接url
     *
     * @param hyperlinkUrl
     */
    public void setHyperlinkUrl(String hyperlinkUrl) {
        this.hyperlinkUrl = hyperlinkUrl == null ? null : hyperlinkUrl.trim();
    }

    /**
     * 获取是否删除：0-是 1-否
     *
     * @return is_del
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * 设置是否删除：0-是 1-否
     *
     * @param isDel
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * 获取内容
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
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
        sb.append(", publisherId=").append(publisherId);
        sb.append(", publisherType=").append(publisherType);
        sb.append(", publishType=").append(publishType);
        sb.append(", title=").append(title);
        sb.append(", kind=").append(kind);
        sb.append(", hyperlinkUrl=").append(hyperlinkUrl);
        sb.append(", isDel=").append(isDel);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}