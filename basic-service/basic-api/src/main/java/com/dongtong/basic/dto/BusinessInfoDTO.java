package com.dongtong.basic.dto;

import java.io.Serializable;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 * 经营范围
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-05 16:34
 **/
public class BusinessInfoDTO implements Serializable {
    private String businessId;//经营业态Id
    private String businessName;//经营业态名


    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
