package com.dongtong.basic.enums;

/**
 * @version V1.0.0
 * @Package com.dongtong.basic.enums
 * @Description:  榜单类型
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/12 13:54
 */
public enum RankType {

    WEEK("周榜单", 0),
    MONTH("月榜单", 1);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    RankType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (RankType type : RankType.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public static RankType getTypeByValue(int value) {
        for (RankType type : RankType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
