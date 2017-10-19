package com.dongtong.app.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 类注释
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/17 18:41
 * @since 1.0
 */
@Data
public class NeedDealList implements Serializable {
    private static final long serialVersionUID = -8543027000692471046L;
    private Object shopRentData;
    private Object meetData;
    private Object signData;
    private Long clerkHintTotalSize;
    private Long visitShopTotalSize;
    private Long signTotalSize;
    private int hintDeadTimeSize;
    private int visitDeadTimeSize;
    private int signDeadTimeSize;

}
