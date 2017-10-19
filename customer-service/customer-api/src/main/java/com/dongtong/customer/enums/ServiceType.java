package com.dongtong.customer.enums;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *日程类型
 * @author wky
 * @version V1.0
 * @create 2017-05-10 10:11
 **/
public enum ServiceType {
    //类型：1-旺铺寻租(线索)2-旺铺寻租(实勘)3-预约看铺(租客) 4-租客看铺(房东) 5-签约租铺(租客) 6-租客签约(房东)
    SHOP_FOR_CHECK("旺铺寻租(线索)",0),
    VISIT_TENANT("预约看铺(租客)",1),
    SIGN_TENANT("签约租铺(租客)",2);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    ServiceType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (ServiceType status : ServiceType.values()) {
                if (status.value == value) {
                    return status.name;
                }
            }
        }
        return "";
    }

    public ServiceType getTypeByValue(int value) {
        for (ServiceType status : ServiceType.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }
}

