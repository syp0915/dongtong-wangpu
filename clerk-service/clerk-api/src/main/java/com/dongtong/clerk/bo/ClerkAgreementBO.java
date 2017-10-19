package com.dongtong.clerk.bo;

import com.dongtong.clerk.domain.ClerkAgreementImg;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 合同详情
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/12 14:38
 * @since 1.0
 */
@Data
public class ClerkAgreementBO implements Serializable {
    private static final long serialVersionUID = 3002010778441761817L;
    private String shopAddress;
    private String startTime;
    private String endTime;
    private BigDecimal rent;
    private BigDecimal transferFee;
    private String rentName;
    private String rentMobile;
    private String lesseeName;
    private String lesseeMobile;
    private int rentWay;
    private Long districtId;
    private String districtName;
    private Long blockId;
    private String blockName;
    private List<ClerkAgreementImg> agreementImg;
}
