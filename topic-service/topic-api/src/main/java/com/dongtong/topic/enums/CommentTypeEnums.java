package com.dongtong.topic.enums;

public enum CommentTypeEnums {

    COMMENT("评论", 1), REPLAY("回复", 2);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    CommentTypeEnums(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (CommentTypeEnums item : CommentTypeEnums.values()) {
                if (item.value == value) {
                    return item.name;
                }
            }
        }
        return "";
    }

    public CommentTypeEnums getTypeByValue(int value) {
        for (CommentTypeEnums item : CommentTypeEnums.values()) {
            if (item.value == value) {
                return item;
            }
        }
        return null;
    }
}
