package com.dongtong.customer.dto;

import java.io.Serializable;

/**
 * @Author:zhoumin
 * @Description:查询房东和租客
 * @Date:Created in 20:45 2017/8/16.
 */
public class TenantAndLandlordDTO implements Serializable {
    private static final long serialVersionUID = -3867830871691080291L;

    /**
     * 租客id
     */
    private Long tenantId;

    /**
     * 房东id
     */
    private Long landlordId;

    /**
     * 租客信息
     */
    private TenantDTO tenantDTO;

    /**
     * 房东信息
     */
    private LandlordDTO landlordDTO;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(Long landlordId) {
        this.landlordId = landlordId;
    }

    public TenantDTO getTenantDTO() {
        return tenantDTO;
    }

    public void setTenantDTO(TenantDTO tenantDTO) {
        this.tenantDTO = tenantDTO;
    }

    public LandlordDTO getLandlordDTO() {
        return landlordDTO;
    }

    public void setLandlordDTO(LandlordDTO landlordDTO) {
        this.landlordDTO = landlordDTO;
    }
}
