package com.dongtong.basic.enums;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.enums
 * @Description :生意圈通知类型 （0-评论通知 1-帖子被撤通知 2-评论被删通知）
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-08 17:50
 * version V1.0.0
 **/
public enum BussinessNoticeType {

    COMMENT_NOTICE("用户发的帖子被评论",0),
    WITHDRAW_NOTICE("帖子被撤通知",1),
    DELETE_NOTICE("评论被删通知",2),
    COMMENTED_ON("评论被评论",3);


    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    BussinessNoticeType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (BussinessNoticeType type: BussinessNoticeType.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public BussinessNoticeType getTypeByValue(int value) {
        for (BussinessNoticeType type : BussinessNoticeType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }



}
