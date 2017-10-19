package com.dongtong.topic.enums;

/**
 * @Package com.dongtong.topic.enums
 * @Description: 是否
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author chenxs
 * @date 2017/5/9 10:46
 * version V1.0.0
 */
public enum LikeStatus {
    NO("未点赞", 0), YES("已点赞", 1);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    LikeStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (LikeStatus yesNo : LikeStatus.values()) {
                if (yesNo.value == value) {
                    return yesNo.name;
                }
            }
        }
        return "";
    }

    public LikeStatus getTypeByValue(int value) {
        for (LikeStatus yesNo : LikeStatus.values()) {
            if (yesNo.value == value) {
                return yesNo;
            }
        }
        return null;
    }
}
