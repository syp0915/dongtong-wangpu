package com.dongtong.customer.enums;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-10 10:11
 **/
public enum  LoanStatus {
    PENDING("待出租", 0),
    ACCEPTED("已受理", 1),
    REJECTED("已拒绝",2);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    LoanStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (LoanStatus status : LoanStatus.values()) {
                if (status.value == value) {
                    return status.name;
                }
            }
        }
        return "";
    }

    public LoanStatus getTypeByValue(int value) {
        for (LoanStatus status : LoanStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }
}

