package com.dongtong.basic.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.basic.domain.BaseConfigure.java
 * @Description: 配置表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author sunyaping
 * @date 2017/06/22 16:10
 * version v1.0.0
 */
public class BaseConfigure extends BaseBean {
    /**
     * 配置code
     */
    private Long code;

    /**
     * 配置名称
     */
    private String codeName;

    /**
     * 配置值
     */
    private String value;

    /**
     * 父级code
     */
    private Long parentCode;

    /**
     * 获取配置code
     *
     * @return code
     */
    public Long getCode() {
        return code;
    }

    /**
     * 设置配置code
     *
     * @param code
     */
    public void setCode(Long code) {
        this.code = code;
    }

    /**
     * 获取配置名称
     *
     * @return code_name
     */
    public String getCodeName() {
        return codeName;
    }

    /**
     * 设置配置名称
     *
     * @param codeName
     */
    public void setCodeName(String codeName) {
        this.codeName = codeName == null ? null : codeName.trim();
    }

    /**
     * 获取配置值
     *
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置配置值
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * 获取父级code
     *
     * @return parent_code
     */
    public Long getParentCode() {
        return parentCode;
    }

    /**
     * 设置父级code
     *
     * @param parentCode
     */
    public void setParentCode(Long parentCode) {
        this.parentCode = parentCode;
    }
}