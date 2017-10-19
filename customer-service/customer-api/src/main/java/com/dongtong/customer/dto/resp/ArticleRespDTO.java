package com.dongtong.customer.dto.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * 类注释
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/24 9:40
 * @since 1.0
 */
@Data
public class ArticleRespDTO implements Serializable {
    private Long id;
    private Long publisherId;
    private Integer publisherType;
    private String title;
    private Integer status;
    private String image;
    private String content;
}
