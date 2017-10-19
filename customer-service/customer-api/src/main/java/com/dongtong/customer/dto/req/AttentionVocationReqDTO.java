package com.dongtong.customer.dto.req;

import java.io.Serializable;

/**
 * @description 关注业态
 * @package com.dongtong.customer.dto.req
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/8/10 0010 16:40
 * @version v1.0.0
 */
public class AttentionVocationReqDTO implements Serializable {

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

    @Override
    public String toString() {
        return "AttentionVocationReqDTO{" +
                "vocationId=" + vocationId +
                ", vocationName='" + vocationName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttentionVocationReqDTO that = (AttentionVocationReqDTO) o;

        if (vocationId != null ? !vocationId.equals(that.vocationId) : that.vocationId != null) return false;
        return vocationName != null ? vocationName.equals(that.vocationName) : that.vocationName == null;
    }

    @Override
    public int hashCode() {
        int result = vocationId != null ? vocationId.hashCode() : 0;
        result = 31 * result + (vocationName != null ? vocationName.hashCode() : 0);
        return result;
    }
}
