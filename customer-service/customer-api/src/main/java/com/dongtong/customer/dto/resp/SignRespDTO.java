package com.dongtong.customer.dto.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * 商铺签约返回
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/10 16:02
 * @since 1.0
 */
@Data
public class SignRespDTO implements Serializable{
   private Long id;
    private String shopAddress;
    private String signTime;
    private String areaId;
    private String	blockId;
    private int   status;
    private String contacter;
    private String contactMobile;
    private String owner;
    private String ownerMobile;
    private Long clerkId;
    private Long customerId;
    private Long tagId;
    private String reason;
    private String coverImgUrl;

}
