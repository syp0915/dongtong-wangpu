package com.dongtong.shop.enums;

/**
 * @Package com.dongtong.shop.enums.ShelfStatus
 * @Description: 上下架状态
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/8/4 16:06
 * version V1.0.0
 */
public enum ShelfStatus {
    PUT("已上架", 0),
    OUT("已下架", 1);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    ShelfStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (ShelfStatus status : ShelfStatus.values()) {
                if (status.value == value) {
                    return status.name;
                }
            }
        }
        return "";
    }

    public ShelfStatus getTypeByValue(int value) {
        for (ShelfStatus status : ShelfStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }
}
