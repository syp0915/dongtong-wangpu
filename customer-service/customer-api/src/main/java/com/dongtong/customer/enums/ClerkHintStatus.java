package com.dongtong.customer.enums;

/**
 * @Author zhoumin
 * @Package com.dongtong.clerk.enums
 * @Description：线索状态(0拓铺员待认领—> 1拓铺员待确认 2：交易员待认领 3：待实堪 4:已转化 5:已废弃 6:已取消)
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-08-08 13:08
 **/
public enum ClerkHintStatus {

    EXPAND_CLERK_SHELTERS("拓铺员待认领", 0),
    EXPAND_CLERK_SURE("拓铺员待确认",1),
    TRADE_CLERK_SHELTERS("交易员待认领",2),
    AWAIT_EXAMINE("待实堪", 3),
    SHOP_TRANSFORM("已转化",4),
    ABANDON("已废弃",5),
    CANCEL("已取消",6);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    ClerkHintStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (ClerkHintStatus type : ClerkHintStatus.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public ClerkHintStatus getTypeByValue(int value) {
        for (ClerkHintStatus type : ClerkHintStatus.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }


}
