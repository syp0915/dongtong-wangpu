package com.dongtong.customer.dto;

import java.io.Serializable;

/**
 * @Author:zhoumin
 * @Description:查询房东和租客
 * @Date:Created in 20:45 2017/8/16.
 */
public class TenantDTO implements Serializable {
    private static final long serialVersionUID = -4327422204938160547L;
    /**
     * 租客电话
     */
    private String tenantPhone;

    /**
     * 租客设备号
     */
    private String tenantDeviceId;

    /**
     * 租客设备类型：0 IOS 1  android
     */
    private Integer tenantOsType;

    public String getTenantPhone() {
        return tenantPhone;
    }

    public void setTenantPhone(String tenantPhone) {
        this.tenantPhone = tenantPhone;
    }

    public String getTenantDeviceId() {
        return tenantDeviceId;
    }

    public void setTenantDeviceId(String tenantDeviceId) {
        this.tenantDeviceId = tenantDeviceId;
    }

    public Integer getTenantOsType() {
        return tenantOsType;
    }

    public void setTenantOsType(Integer tenantOsType) {
        this.tenantOsType = tenantOsType;
    }

}
