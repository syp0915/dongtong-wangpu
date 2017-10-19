package com.dongtong.customer.dto;

import java.io.Serializable;

/**
 * @Author:zhoumin
 * @Description:查询房东和租客
 * @Date:Created in 20:45 2017/8/16.
 */
public class LandlordDTO implements Serializable {

    private static final long serialVersionUID = 7564168199718435357L;

    /**
     * 房东电话
     */
    private String landlordPhone;

    /**
     * 房东设备id
     */
    private String landlordDeviceId;

    /**
     * 房东设备类型：0 IOS 1  android
     */
    private Integer landlordOsType;

    public String getLandlordPhone() {
        return landlordPhone;
    }

    public void setLandlordPhone(String landlordPhone) {
        this.landlordPhone = landlordPhone;
    }

    public String getLandlordDeviceId() {
        return landlordDeviceId;
    }

    public void setLandlordDeviceId(String landlordDeviceId) {
        this.landlordDeviceId = landlordDeviceId;
    }

    public Integer getLandlordOsType() {
        return landlordOsType;
    }

    public void setLandlordOsType(Integer landlordOsType) {
        this.landlordOsType = landlordOsType;
    }
}
