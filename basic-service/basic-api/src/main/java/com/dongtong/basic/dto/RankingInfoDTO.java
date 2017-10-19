package com.dongtong.basic.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.basic.dto.RankingInfoDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/15 10:31
 * version V1.0.0
 */
public class RankingInfoDTO implements Serializable {

    private static final long serialVersionUID = -3360937377471529830L;


    /**
     * 邀请码
     */
    private String invitationCode;
    /**
     * 单数
     */
    private String quantity;

    /**
     * 最大时间
     */
    private String maxtime;
    /**
     * 名字
     */
    private String name;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 排名
     */
    private String rowNumber;

    public String getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(String rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMaxtime() {
        return maxtime;
    }

    public void setMaxtime(String maxtime) {
        this.maxtime = maxtime;
    }
}
