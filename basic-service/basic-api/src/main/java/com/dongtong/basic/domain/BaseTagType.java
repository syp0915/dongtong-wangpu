package com.dongtong.basic.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.basic.domain.BaseTagType.java
 * @Description: 标签类型
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:10
 * version v1.0.0
 */
public class BaseTagType extends BaseBean {
    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 获取类型名称
     *
     * @return type_name
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置类型名称
     *
     * @param typeName
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/02 14:10
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", typeName=").append(typeName);
        sb.append("]");
        return sb.toString();
    }
}