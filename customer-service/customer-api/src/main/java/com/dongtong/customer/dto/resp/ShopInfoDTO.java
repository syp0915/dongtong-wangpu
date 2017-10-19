package com.dongtong.customer.dto.resp;

import java.io.Serializable;

/**
 * @Package com.dongtong.customer.dto.resp
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhoumin
 * @date 17/5/12 下午2:47
 * version V1.0.0
 */
public class ShopInfoDTO implements Serializable {
    private static final long serialVersionUID = 5517970845226521945L;

    /**
     * 约看id
     */
    private Long visitId;

    /**
     * 收藏id
     */
    private Long collectedId;

    public Long getVisitId() {
        return visitId;
    }

    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }

    public Long getCollectedId() {
        return collectedId;
    }

    public void setCollectedId(Long collectedId) {
        this.collectedId = collectedId;
    }
}
