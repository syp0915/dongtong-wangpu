package com.dongtong.basic.enums;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.enums
 * @Description:商铺通知类型
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-08-11 10:26
 * version V1.0.0
 **/
public enum ShopServiceType {
    SOLD_OUT("商铺下架",0),
    RECEIVE_SHOP("店铺转到业务员名下",1),
    PUBLISH_SHOP("线索被发布店铺",2),
    SHOP_TIMEOUT("店铺跟进即将超时",3);


    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    ShopServiceType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (ShopServiceType type : ShopServiceType.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public ShopServiceType getTypeByValue(int value) {
        for (ShopServiceType type : ShopServiceType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
