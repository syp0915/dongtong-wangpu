package com.dongtong.shop.enums;

/**
 * @Package com.dongtong.shop.enums.ErrorConstant
 * @Description: 商铺业务端错误代码
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/6/27 15:43
 * version V1.0.0
 */
public enum ErrorConstant {
    SHOP_CODE_NOT_USE(410, "该二维码编号不能使用！"),
    SHOP_CODE_NOT_ALREAD_USE(411, "该二维码编号已经绑定商铺！"),
    NO_PERMISSION_NOT_SELF(210, "不能修改非本人发布的商铺！"),
    NO_PERMISSION_ALREAD_CLAIM(410, "已经认领的商铺，非交易业务员不能修改！"),
    NO_PERMISSION_ALREAD_CLAIM_NOT_SELF(410, "不能修改非本人认领的商铺！");



    private final int code;
    private final String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ErrorConstant(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (ErrorConstant constant : ErrorConstant.values()) {
                if (constant.code == value) {
                    return constant.msg;
                }
            }
        }
        return "";
    }

    public ErrorConstant getTypeByValue(int value) {
        for (ErrorConstant constant : ErrorConstant.values()) {
            if (constant.code == value) {
                return constant;
            }
        }
        return null;
    }
}
