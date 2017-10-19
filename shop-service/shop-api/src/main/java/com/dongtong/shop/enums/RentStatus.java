package com.dongtong.shop.enums;

/**
 * @Package com.dongtong.shop.enums.RentStatus
 * @Description: 出租状态 0-待出租 1-出租中 2-已出租  3-已下架
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/5 19:37
 * version V1.0.0
 */
public enum RentStatus {

    STAY_RENT("待出租", 0),
    RENT("出租中", 1),
    NOT_RENT("暂不出租", 2),
    ALREADY_RENT("已出租", 3);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    RentStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (RentStatus status : RentStatus.values()) {
                if (status.value == value) {
                    return status.name;
                }
            }
        }
        return "";
    }

    public RentStatus getTypeByValue(int value) {
        for (RentStatus status : RentStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }
}
