package com.dongtong.basic.enums;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.enums
 * @Description：业务端通知状态 （ 0-商铺被下架 1-接到商户需求 2-任务到时提醒 3-周榜排名提醒 4-月榜排名提醒 5-帖子被评价 ）
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-09 10:30
 * version V1.0.0
 **/
public enum BussinessEndNoticeType {
    RECEIVED_DEMAND("接到用户需求",1),
    TASK_REMIND("任务到时提醒",2),
    HITE_CANCEL_REMIND("线索废弃提醒",3);
    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    BussinessEndNoticeType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (BussinessEndNoticeType type : BussinessEndNoticeType.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public BussinessEndNoticeType getTypeByValue(int value) {
        for (BussinessEndNoticeType type : BussinessEndNoticeType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }



}
