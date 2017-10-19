package com.dongtong.basic.enums;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/6/2 上午10:38.
 */
public enum SmsUseSceneType {

    LOGIN("登录", 0),
    APPLY_LOAD("贷款申请", 1),
    SHOP_SIGN("租铺签约" ,2),
    APPLY_FIND_RECENT("寻租申请" ,3),
    SEE_SHOP("带我踩盘" ,4),
    CORRECT_SHOP("商铺纠错" ,5),
    ORDER_SEE_SHOP("预约看铺" ,6),
    UPDATE_PHONE("修改手机号" ,7);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    SmsUseSceneType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (SmsUseSceneType type : SmsUseSceneType.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public static SmsUseSceneType getTypeByValue(int value) {
        for (SmsUseSceneType type : SmsUseSceneType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
