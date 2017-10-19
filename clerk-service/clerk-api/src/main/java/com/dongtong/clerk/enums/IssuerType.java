package com.dongtong.clerk.enums;

/**
 * @author sunyaping
 * @Package com.dongtong.clerk.enums
 * @Description：线索发布人类型 （0-客户 1-业务员）
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-10 15:08
 * version V1.0.0
 **/
public enum IssuerType {

    CUSTOMER("客户", 0),
    CLERK("业务员", 1);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    IssuerType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (IssuerType type : IssuerType.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public IssuerType getTypeByValue(int value) {
        for (IssuerType type : IssuerType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }

}
