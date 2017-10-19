package com.dongtong.topic.enums;

/**
 * @version V1.0.0
 * @Package com.dongtong.topic.enums
 * @Description:
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/9 10:19
 */
public enum UserType {

    MERCHANT("商户", 0), @Deprecated CLERK("业务员", 1), PRETEND("马甲", 1);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    UserType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (UserType user : UserType.values()) {
                if (user.value == value) {
                    return user.name;
                }
            }
        }
        return "";
    }

    public UserType getTypeByValue(int value) {
        for (UserType user : UserType.values()) {
            if (user.value == value) {
                return user;
            }
        }
        return null;
    }
}
