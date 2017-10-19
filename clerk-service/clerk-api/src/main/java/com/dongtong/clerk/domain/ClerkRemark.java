package com.dongtong.clerk.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.clerk.domain.ClerkRemark.java
 * @Description: 备注信息表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author liaozm
 * @date 2017/08/08 09:46
 * version v1.0.0
 */
public class ClerkRemark extends BaseBean {
    /**
     * 业务ID
     */
    private Long clerkId;

    /**
     * 类型 (0:签约 1:约看)
     */
    private Integer type;

    /**
     * 数据ID(签约ID或者约看ID)
     */
    private String dataId;

    /**
     * 内容
     */
    private String content;

    /**
     * 获取业务ID
     *
     * @return clerk_id
     */
    public Long getClerkId() {
        return clerkId;
    }

    /**
     * 设置业务ID
     *
     * @param clerkId
     */
    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    /**
     * 获取类型 (0:签约 1:约看)
     *
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型 (0:签约 1:约看)
     *
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取数据ID(签约ID或者约看ID)
     *
     * @return data_id
     */
    public String getDataId() {
        return dataId;
    }

    /**
     * 设置数据ID(签约ID或者约看ID)
     *
     * @param dataId
     */
    public void setDataId(String dataId) {
        this.dataId = dataId == null ? null : dataId.trim();
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
}