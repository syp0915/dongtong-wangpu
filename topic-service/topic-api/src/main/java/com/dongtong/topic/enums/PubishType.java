package com.dongtong.topic.enums;

/**
 * @version V1.0.0
 * @Package com.dongtong.topic.enums
 * @Description:
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/9
 */
public enum PubishType {

    TOPIC("帖子", 0), NEWS("转载", 1), PICCONTENT("长图文",2);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    PubishType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (PubishType pubishType : PubishType.values()) {
                if (pubishType.value == value) {
                    return pubishType.name;
                }
            }
        }
        return "";
    }

    public PubishType getTypeByValue(int value) {
        for (PubishType pubishType : PubishType.values()) {
            if (pubishType.value == value) {
                return pubishType;
            }
        }
        return null;
    }
}
