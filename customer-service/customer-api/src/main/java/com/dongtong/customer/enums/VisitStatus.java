package com.dongtong.customer.enums;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *签约状态
 * @author wky
 * @version V1.0
 * @create 2017-05-10 10:11
 **/
public enum VisitStatus {
    VISITING("待踩盘", 0),
    VISITED("已踩盘", 1),
    CANCELED("已取消",2);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    VisitStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (VisitStatus status : VisitStatus.values()) {
                if (status.value == value) {
                    return status.name;
                }
            }
        }
        return "";
    }

    public VisitStatus getTypeByValue(int value) {
        for (VisitStatus status : VisitStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }
}

