package com.dongtong.basic.enums;

public enum Participate {

    YES_PARTICIPATE("参与", 0),
    NO_PARTICIPATE("未参与", 1);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    Participate(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (Participate status : Participate.values()) {
                if (status.value == value) {
                    return status.name;
                }
            }
        }
        return "";
    }

    public Participate getTypeByValue(int value) {
        for (Participate status : Participate.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }
}
