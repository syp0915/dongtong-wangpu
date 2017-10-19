package com.dongtong.customer.enums;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *签约状态
 * @author wky
 * @version V1.0
 * @create 2017-05-10 10:11
 **/
public enum SignStatus {
    PENDING("待签约", 0),
    SIGN_NOT_UPLOAD("已签约未上传合同（业主确认日程)", 1),
    SIGN_UPLOAD("已签约上传合同",2),
    CANCEL("业务员取消",9);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    SignStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (SignStatus status : SignStatus.values()) {
                if (status.value == value) {
                    return status.name;
                }
            }
        }
        return "";
    }

    public SignStatus getTypeByValue(int value) {
        for (SignStatus status : SignStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }
}

