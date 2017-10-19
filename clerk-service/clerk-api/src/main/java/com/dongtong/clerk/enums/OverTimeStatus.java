package com.dongtong.clerk.enums;

/**
 * @Author zhoumin
 * @Package com.dongtong.clerk.enums
 * @Description：超时状态 0：未超时   1：超时
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-08-12 11:08
 **/
public enum OverTimeStatus {

    NO_OVER_TIME("未超时", 0),
    OVER_TIME("已超时",1),
    PROCESS("已处理",2);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    OverTimeStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (OverTimeStatus type : OverTimeStatus.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public OverTimeStatus getTypeByValue(int value) {
        for (OverTimeStatus type : OverTimeStatus.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }


}
