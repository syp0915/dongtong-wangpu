package com.dongtong.customer.dto.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 约看实体类
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/10 10:52
 * @since 1.0
 */
@Data
public class VisitShopReqDTO implements Serializable {
    private static final long serialVersionUID = -1491304875627488448L;
    private Long Id;
    private Long clerkId;
    private String visitTime;
    private Long tagId;
    private String cancelCause;
    private int pageSize;
    private int pageNumber;
    private Integer status;

}
