package com.dongtong.customer.enums;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-10 10:11
 **/
public enum AuditStatus {
    PENDING_AUDIT("待审核", 0),
    AUDITED("审核通过", 1),
    AUDIT_NOT_APPROVED("审核不通过",2);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    AuditStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (AuditStatus status : AuditStatus.values()) {
                if (status.value == value) {
                    return status.name;
                }
            }
        }
        return "";
    }

    public AuditStatus getTypeByValue(int value) {
        for (AuditStatus status : AuditStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }
}

