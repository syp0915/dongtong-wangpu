package com.dongtong.basic.enums;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.enums
 * @Description：接收人类型 （0-客户 1-业务员）
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-08 17:21
 * version V1.0.0
 **/
public enum ReceiveType {
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

    ReceiveType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (ReceiveType type : ReceiveType.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public ReceiveType getTypeByValue(int value) {
        for (ReceiveType type : ReceiveType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }

}
