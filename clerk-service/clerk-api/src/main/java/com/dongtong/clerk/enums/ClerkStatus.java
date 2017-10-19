package com.dongtong.clerk.enums;

/**
 * @author sunyaping
 * @Package com.dongtong.clerk.enums
 * @Description：线索状态（0待认领—> 1待实堪——>2已收铺-->3已废弃）
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-10 15:08
 * version V1.0.0
 **/
public enum ClerkStatus {

    SHELTERS("待认领", 0),
    AWAIT_EXAMINE("待实堪", 1),
    SHOP_CLOSED("已收铺",2),
    ABANDON("已废弃",3);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    ClerkStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (ClerkStatus type : ClerkStatus.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public ClerkStatus getTypeByValue(int value) {
        for (ClerkStatus type : ClerkStatus.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }


}
