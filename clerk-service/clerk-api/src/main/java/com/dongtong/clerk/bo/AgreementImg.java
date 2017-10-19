package com.dongtong.clerk.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 类注释
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/18 14:28
 * @since 1.0
 */
@Data
public class AgreementImg implements Serializable {
    private static final long serialVersionUID = -8340621229017355696L;
    private String imgUrl;
    private int imgIndex;
}
