package com.dongtong.clerk.enums;

/**
 * @Author zhoumin
 * @Package com.dongtong.clerk.enums
 * @Description：线索状态 1拓铺员待确认 2：待实堪
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-08-08 13:08
 **/
public enum OverTimeHintStatus {
    EXPAND_CLERK_SURE("拓铺员待确认",1),
    AWAIT_EXAMINE("待实堪", 2);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    OverTimeHintStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (OverTimeHintStatus type : OverTimeHintStatus.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public OverTimeHintStatus getTypeByValue(int value) {
        for (OverTimeHintStatus type : OverTimeHintStatus.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }


}
