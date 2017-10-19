package com.dongtong.basic.service;

import com.dongtong.basic.domain.BaseShopNumber;
import com.shfc.common.result.ResultDO;

import java.util.List;
import java.util.Set;

/**
 * @Package com.dongtong.basic.service.BaseShopNumberService
 * @Description: 商铺二维码
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/6/13 11:34
 * version V1.0.0
 */
public interface BaseShopNumberService {
    /**
     * 修改二维码状态为已使用
     * add by xiehb
     * @param shopNum
     * @return
     */
    ResultDO<String> updateShopNumber(String shopNum);

    /**
     * 根据二维码编号查询
     * @param shopNum
     * @return
     */
    List<BaseShopNumber> shopNumberList(String shopNum);

    /**
     * 批量插入商铺二维码
     * @param shopNumber
     */
    int bathInsertShopNumber(Set<String> shopNumber);

    /**
     * 单条插入商铺二维码
     * @param shopNumber
     * @return
     */
    int insertSingleShopNumber(String shopNumber);

    /**
     * 生成一个未使用的二维码
     * @param cityCode 城市编号
     * @return
     */
    ResultDO<String> createShopNumber(String cityCode);
}
