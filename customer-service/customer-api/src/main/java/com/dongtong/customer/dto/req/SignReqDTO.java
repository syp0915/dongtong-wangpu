package com.dongtong.customer.dto.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 类注释
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/10 16:02
 * @since 1.0
 */
@Data
public class SignReqDTO implements Serializable{
    private static final long serialVersionUID = 3551844310138249384L;
    private Long id;
    private Long clerkId;
    private String signTime;
    private Long tagId;
    private String cancelCause;
    private int pageSize;
    private int pageNumber;
    private Integer status;
    private Long agreementId;
    private String uploadTime;

}
