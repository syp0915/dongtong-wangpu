package com.dongtong.basic.enums;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.enums
 * @Description：服务通知状态 （0-时间变动 1-服务撤销 2-服务完成 3-日程提醒）
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-08 17:21
 * version V1.0.0
 **/
public enum ServiceStatus {

    TIME_VARIATION("服务日程时间变动", 0),
    SERVICE_REVOCATION("服务日程被撤销", 1),
    SERVICE_COMPLETION("旺铺出租服务完成", 2),
    SCHEDULE_REMIND("日程提醒",3),
    SCHEDULE_CREAT("日程时间创建",4);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    ServiceStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (ServiceStatus status : ServiceStatus.values()) {
                if (status.value == value) {
                    return status.name;
                }
            }
        }
        return "";
    }

    public ServiceStatus getTypeByValue(int value) {
        for (ServiceStatus status : ServiceStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }


}
