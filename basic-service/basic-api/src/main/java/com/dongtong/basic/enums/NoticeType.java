package com.dongtong.basic.enums;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.enums
 * @Description：通知类型 （0-服务 1-系统 2-生意圈 3-商铺 4-工作 5-小喇叭）
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-09 17:00
 * version V1.0.0
 **/
public enum NoticeType {
    SERVICE("服务",0),
    SYSTEM("系统",1),
    BUSSINESS("生意圈",2),
    SHOP("商铺",3),
    WORK("工作",4),
    TRUMPET("小喇叭",5);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    NoticeType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (NoticeType type : NoticeType.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public NoticeType getTypeByValue(int value) {
        for (NoticeType type : NoticeType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }

}
