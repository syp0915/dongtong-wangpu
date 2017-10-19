package com.dongtong.basic.enums;

/**
 * Created by SYP on 2017/5/19.
 */
public enum DeviceType {
    IOS("ios",0),
    SYSTEM("android",1);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    DeviceType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (DeviceType type : DeviceType.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public DeviceType getTypeByValue(int value) {
        for (DeviceType type : DeviceType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
