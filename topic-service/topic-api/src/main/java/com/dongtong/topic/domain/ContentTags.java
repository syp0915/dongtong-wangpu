package com.dongtong.topic.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.topic.domain.ContentTags.java
 * @Description: 
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author chen xiushen
 * @date 2017/08/04 17:24
 * version v1.0.0
 */
public class ContentTags extends BaseBean {
    /**
     * 标签名
     */
    private String tagName;

    /**
     * 对应的内容类型 1-头条资讯 2-研究院内容管理 3-导购管理
     */
    private Boolean contentType;

    /**
     * 标签icon
     */
    private String icon;

    /**
     * 状态 1-有用 2-无用
     */
    private Boolean status;

    /**
     * 获取标签名
     *
     * @return tag_name
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 设置标签名
     *
     * @param tagName
     */
    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    /**
     * 获取对应的内容类型 1-头条资讯 2-研究院内容管理 3-导购管理
     *
     * @return content_type
     */
    public Boolean getContentType() {
        return contentType;
    }

    /**
     * 设置对应的内容类型 1-头条资讯 2-研究院内容管理 3-导购管理
     *
     * @param contentType
     */
    public void setContentType(Boolean contentType) {
        this.contentType = contentType;
    }

    /**
     * 获取标签icon
     *
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置标签icon
     *
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 获取状态 1-有用 2-无用
     *
     * @return status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置状态 1-有用 2-无用
     *
     * @param status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }
}