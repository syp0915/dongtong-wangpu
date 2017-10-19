package com.dongtong.basic.enums;

/**
 * @Package com.dongtong.basic.enums.RankServiceType
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/14 15:03
 * version V1.0.0
 */
public enum RankServiceType {

    DEVELOPMENT("核准", 0),
    ORDER_SEE("约看", 1),
    SIGN_CONTRACT("签约", 2),
    REGISTERED("注册", 3),
    HINT("线索", 4);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    RankServiceType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (RankServiceType type : RankServiceType.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public static RankServiceType getTypeByValue(int value) {
        for (RankServiceType type : RankServiceType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
