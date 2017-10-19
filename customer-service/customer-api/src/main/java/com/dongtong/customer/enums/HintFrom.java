package com.dongtong.customer.enums;

/**
 * @author zhoumin
 * @Package com.dongtong.clerk.enums.HintFrom
 * @Description：线索来源 1:扫街 2:客户 3:网站
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-08-16 15:08
 * version V1.0.0
 **/
public enum HintFrom {

    STREET("扫街",1),
    CUSTOMER("客户", 2),
    WEBSITE("网站", 3);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    HintFrom(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (HintFrom type : HintFrom.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public HintFrom getTypeByValue(int value) {
        for (HintFrom type : HintFrom.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }

}
