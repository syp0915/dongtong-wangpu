package com.dongtong.basic.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.basic.domain.BaseArticleInformation.java
 * @Description: 文章资讯
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:06
 * version v1.0.0
 */
public class BaseArticleInformation extends BaseBean {
    /**
     * 发布人id
     */
    private Long publisherId;

    /**
     * 发布人类型：0-商户 1-业务员
     */
    private Integer publisherType;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 状态：0-已发布 1-已撤回
     */
    private Integer status;

    /**
     * 图片
     */
    private String image;

    /**
     * 是否删除：0-是 1-否
     */
    private Integer isDel;

    private Integer isThirdParty;

    /**
     * 文章内容
     */
    private String content;

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
     * 获取文章标题
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置文章标题
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取状态：0-已发布 1-已撤回
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0-已发布 1-已撤回
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取图片
     *
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置图片
     *
     * @param image
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
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
     * 获取文章内容
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文章内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getIsThirdParty() {
        return isThirdParty;
    }

    public void setIsThirdParty(Integer isThirdParty) {
        this.isThirdParty = isThirdParty;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 14:06
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
        sb.append(", title=").append(title);
        sb.append(", status=").append(status);
        sb.append(", image=").append(image);
        sb.append(", isDel=").append(isDel);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}