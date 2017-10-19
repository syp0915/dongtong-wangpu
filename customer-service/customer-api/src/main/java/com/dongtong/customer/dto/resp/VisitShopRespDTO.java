package com.dongtong.customer.dto.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * 类注释
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/10 10:54
 * @since 1.0
 */
@Data
public class VisitShopRespDTO implements Serializable {
    private static final long serialVersionUID = 2697366265836971550L;
    private Long id;
    private Long clerkId;
    private String shopAddress;
    private String visitTime;
    private int status;
    private String linkmanName;
    private String linkmanPhone;
    private String contacter;
    private String contacterTel;
    private String blockName;
    private String districtName;
    private Long customerId;
    private Integer tagId;
    private String cancelCause;
    private String coverImgUrl;
}
