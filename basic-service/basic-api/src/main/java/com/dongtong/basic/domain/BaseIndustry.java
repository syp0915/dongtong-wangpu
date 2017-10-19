package com.dongtong.basic.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.basic.domain.BaseIndustry.java
 * @Description: 业态
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:06
 * version v1.0.0
 */
public class BaseIndustry extends BaseBean {
    /**
     * 业态名称
     */
    private String name;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 获取业态名称
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置业态名称
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取父id
     *
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父id
     *
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
        sb.append(", name=").append(name);
        sb.append(", parentId=").append(parentId);
        sb.append("]");
        return sb.toString();
    }
}