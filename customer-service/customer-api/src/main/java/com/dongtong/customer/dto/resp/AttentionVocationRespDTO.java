package com.dongtong.customer.dto.resp;

import java.io.Serializable;

/**
 * @description 关注业态
 * @package com.dongtong.customer.dto.resp
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/8/10 0010 16:40
 * @version v1.0.0
 */
public class AttentionVocationRespDTO implements Serializable {

    private Long vocationId;

    private String vocationName;

    public Long getVocationId() {
        return vocationId;
    }

    public void setVocationId(Long vocationId) {
        this.vocationId = vocationId;
    }

    public String getVocationName() {
        return vocationName;
    }

    public void setVocationName(String vocationName) {
        this.vocationName = vocationName;
    }
}
