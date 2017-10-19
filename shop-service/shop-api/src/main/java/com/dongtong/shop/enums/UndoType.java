package com.dongtong.shop.enums;

/**
 * @Package com.dongtong.shop.enums.UndoType
 * @Description: 撤下人类型(0-业务员 1- 用户 2-系统)
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/5 19:21
 * version V1.0.0
 */
public enum UndoType {
    CLERK("业务员", 0),
    CUSTOMER("用户", 1),
    SYSTEM("系统", 2);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    UndoType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (UndoType type : UndoType.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public UndoType getTypeByValue(int value) {
        for (UndoType type : UndoType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
