package com.dongtong.clerk.dto;

import java.io.Serializable;

/**
 * @Author:zhoumin
 * @Description:
 * @Date:Created in 20:56 2017/8/8.
 */
public class ClerkHintDetailReqDTO implements Serializable {
    private static final long serialVersionUID = 2951179265314344238L;

    /**
     * 业务员id
     */
    private Long clerkId;

    /**
     * 1:线索待确认 2:线索待实勘
     */
    private Integer type;

    /**
     * 线索id
     */
    private Long id;

    public Long getClerkId() {
        return clerkId;
    }

    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
