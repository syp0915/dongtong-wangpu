package com.dongtong.topic.enums;

public enum ContentTypeEnums {

    HEADLINE("行业头条", 1), BIBLE("生意宝典", 2),GUIDE("导购", 3);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    ContentTypeEnums(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (ContentTypeEnums item : ContentTypeEnums.values()) {
                if (item.value == value) {
                    return item.name;
                }
            }
        }
        return "";
    }

    public ContentTypeEnums getTypeByValue(int value) {
        for (ContentTypeEnums item : ContentTypeEnums.values()) {
            if (item.value == value) {
                return item;
            }
        }
        return null;
    }
}
