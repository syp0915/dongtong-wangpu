package com.dongtong.clerk.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.clerk.domain.ClerkHintFollowTags.java
 * @Description: 线索跟进标签表
 * @Company: ultimate leader
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author zhanghz
 * @date 2017/08/02 17:55
 * version v1.0.0
 */
public class ClerkHintFollowTags extends BaseBean {
    /**
     * 线索id
     */
    private Long hintId;

    /**
     * 线索跟进编号
     */
    private Long hintFollowId;

    /**
     * 标签编号
     */
    private Long baseTagId;

    /**
     * 跟进内容
     */
    private String tagName;

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
     * 获取线索跟进编号
     *
     * @return hint_follow_id
     */
    public Long getHintFollowId() {
        return hintFollowId;
    }

    /**
     * 设置线索跟进编号
     *
     * @param hintFollowId
     */
    public void setHintFollowId(Long hintFollowId) {
        this.hintFollowId = hintFollowId;
    }

    /**
     * 获取标签编号
     *
     * @return base_tag_id
     */
    public Long getBaseTagId() {
        return baseTagId;
    }

    /**
     * 设置标签编号
     *
     * @param baseTagId
     */
    public void setBaseTagId(Long baseTagId) {
        this.baseTagId = baseTagId;
    }

    /**
     * 获取跟进内容
     *
     * @return tag_name
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 设置跟进内容
     *
     * @param tagName
     */
    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }
}