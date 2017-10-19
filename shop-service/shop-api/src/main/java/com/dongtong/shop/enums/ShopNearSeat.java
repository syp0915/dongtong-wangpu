package com.dongtong.shop.enums;

/**
 * @Package com.dongtong.shop.enums.ShopNearSeat
 * @Description: 邻铺位置(0-左一 1-左二 2-右一 3-右二)
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/9 14:07
 * version V1.0.0
 */
public enum  ShopNearSeat {
    LEFT_ONE("左一",0),
    LEFT_TWO("左二",1),
    RIGHT_ONE("右一",2),
    RIGHT_TWO("右二",3);

    private final String name;
    private final int value;

    ShopNearSeat(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (ShopNearSeat type : ShopNearSeat.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public ShopNearSeat getTypeByValue(int value) {
        for (ShopNearSeat type : ShopNearSeat.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
