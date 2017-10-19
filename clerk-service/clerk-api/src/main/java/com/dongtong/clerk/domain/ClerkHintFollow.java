package com.dongtong.clerk.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.clerk.domain.ClerkHintFollow.java
 * @Description: 线索跟进信息
 * @Company: ultimate leader
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author zhanghz
 * @date 2017/08/02 17:55
 * version v1.0.0
 */
public class ClerkHintFollow extends BaseBean {
    /**
     * 线索id
     */
    private Long hintId;

    /**
     * 业务员id
     */
    private Long clerkId;

    /**
     * 跟进内容
     */
    private String content;

    /**
     * 是否删除 1:已删除 0:未删除
     */
    private Integer deleteFlag;

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
     * 获取跟进内容
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置跟进内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}