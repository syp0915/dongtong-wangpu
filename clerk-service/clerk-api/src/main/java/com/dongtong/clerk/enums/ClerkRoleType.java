package com.dongtong.clerk.enums;

/**
 * @Package com.dongtong.clerk.enums.ClerkRoleType
 * @Description: 业务员-角色类型
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/6/26 15:25
 * version V1.0.0
 */
public enum ClerkRoleType {
    DEAL_CLERK("业务员(交易)", 0),CLERK("运营", 1), EXPAND_CLERK("业务员(拓铺)", 2);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    ClerkRoleType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (ClerkRoleType type : ClerkRoleType.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public ClerkRoleType getTypeByValue(int value) {
        for (ClerkRoleType type : ClerkRoleType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
