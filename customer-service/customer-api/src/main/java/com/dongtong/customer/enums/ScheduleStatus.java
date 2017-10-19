package com.dongtong.customer.enums;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *日程类型
 * @author wky
 * @version V1.0
 * @create 2017-05-10 10:11
 **/
public enum ScheduleStatus {
    ACCEPTING("服务受理中", 0),
    FINISHED("已完成", 1),
    CANCEL("已撤销",2),
    OVERDUE("已过期",3);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    ScheduleStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (ScheduleStatus status : ScheduleStatus.values()) {
                if (status.value == value) {
                    return status.name;
                }
            }
        }
        return "";
    }

    public ScheduleStatus getTypeByValue(int value) {
        for (ScheduleStatus status : ScheduleStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }
}

