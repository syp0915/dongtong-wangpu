package com.dongtong.shop.enums;

/**
 * @Package com.dongtong.shop.enums.LogType
 * @Description: 日志类型(1-上架;2-下架;3-跟进超时)
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhoumin
 * @date 2017/8/11 14:21
 * version V1.0.0
 */
public enum LogType {
    PUT("上架", 1),
    OUT("下架", 2),
    FOLLOW("跟进超时", 3);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    LogType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (LogType type : LogType.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public LogType getTypeByValue(int value) {
        for (LogType type : LogType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
