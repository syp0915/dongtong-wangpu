package com.dongtong.customer.dto.resp;

import java.io.Serializable;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/11 上午10:12.
 */
public class ShopNameIsGoodRespDTO implements Serializable {

    private Integer moneyLuck;//财运
    private Integer guestLuck;//客运
    private Integer future;//前景
    private String description;//描述

    public Integer getMoneyLuck() {
        return moneyLuck;
    }

    public void setMoneyLuck(Integer moneyLuck) {
        this.moneyLuck = moneyLuck;
    }

    public Integer getGuestLuck() {
        return guestLuck;
    }

    public void setGuestLuck(Integer guestLuck) {
        this.guestLuck = guestLuck;
    }

    public Integer getFuture() {
        return future;
    }

    public void setFuture(Integer future) {
        this.future = future;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
