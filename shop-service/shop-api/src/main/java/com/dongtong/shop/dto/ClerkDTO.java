package com.dongtong.shop.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.customer.dto.resp
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhoumin
 * @date 17/5/11 下午8:03
 * version V1.0.0
 */
public class ClerkDTO implements Serializable {
    private static final long serialVersionUID = 5494794648637250895L;
    /**
     * 业务员id
     */
    private Long clerkId;

    /**
     * 业务员姓名
     */
    private String clerkName;

    public Long getClerkId() {
        return clerkId;
    }

    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }
}
