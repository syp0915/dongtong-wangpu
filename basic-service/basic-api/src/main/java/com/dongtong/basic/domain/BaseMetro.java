package com.dongtong.basic.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.basic.domain.BaseMetro.java
 * @Description: 地铁
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/04 16:10
 * version v1.0.0
 */
public class BaseMetro extends BaseBean {
    private String cityid;

    /**
     * 地铁线路名称
     */
    private String name;

    /**
     * @return cityId
     */
    public String getCityid() {
        return cityid;
    }

    /**
     * @param cityid
     */
    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    /**
     * 获取地铁线路名称
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置地铁线路名称
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @Title toString
     * @Author zm
     * @Date 2017/05/04 16:10
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cityid=").append(cityid);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}