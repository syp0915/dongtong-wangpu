package com.dongtong.clerk.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 店铺线索
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/9 14:05
 * @since 1.0
 */
@Data
public class ClerkHintBO implements Serializable{
    private static final long serialVersionUID = 3066724842712018803L;
    private long id;
    private Long ownerId;
    private String shopAddress;
    private String subscribeTime;
    private Integer status;
    private String linkmanName;
    private String linkmanPhone;
    private Long issuerId;
    private Integer tagId;
    private String  discardCause;
    private Integer issuerType;
    private Long shopId;


}
