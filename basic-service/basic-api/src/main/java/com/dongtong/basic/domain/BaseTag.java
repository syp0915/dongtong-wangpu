package com.dongtong.basic.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.basic.domain.BaseTag.java
 * @Description: 标签表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:09
 * version v1.0.0
 */
public class BaseTag extends BaseBean {
    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 标签颜色
     */
    private String tagColor;

    /**
     * 类型id
     */
    private Long tagTypeId;

    /**
     * 获取标签名称
     *
     * @return tag_name
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 设置标签名称
     *
     * @param tagName
     */
    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    /**
     * 获取标签颜色
     *
     * @return tag_color
     */
    public String getTagColor() {
        return tagColor;
    }

    /**
     * 设置标签颜色
     *
     * @param tagColor
     */
    public void setTagColor(String tagColor) {
        this.tagColor = tagColor == null ? null : tagColor.trim();
    }

    /**
     * 获取类型id
     *
     * @return tag_type_id
     */
    public Long getTagTypeId() {
        return tagTypeId;
    }

    /**
     * 设置类型id
     *
     * @param tagTypeId
     */
    public void setTagTypeId(Long tagTypeId) {
        this.tagTypeId = tagTypeId;
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 14:09
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tagName=").append(tagName);
        sb.append(", tagColor=").append(tagColor);
        sb.append(", tagTypeId=").append(tagTypeId);
        sb.append("]");
        return sb.toString();
    }
}