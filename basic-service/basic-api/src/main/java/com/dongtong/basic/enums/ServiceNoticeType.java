package com.dongtong.basic.enums;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.enums
 * @Description：服务通知类型 （0-预约看铺 1-旺铺寻租 2-签约租铺 ）
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-08 17:21
 * version V1.0.0
 **/
public enum ServiceNoticeType {
    APPOINT_MENTSHOP("预约看铺",0),
    SHOP_RENT_SEEKING("旺铺寻租",1),
    CONTRACT_LEASING("签约租铺",2),
    SERVICE_COMPLETION("服务完成",4),
    RENTER_MENTSHOP("租客看铺",5),
    RENTER_SIGN("租客签约",6);


    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    ServiceNoticeType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (ServiceNoticeType type : ServiceNoticeType.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public ServiceNoticeType getTypeByValue(int value) {
        for (ServiceNoticeType type : ServiceNoticeType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
