package com.dongtong.customer.service;

import com.dongtong.customer.domain.CustomerCollectedShop;
import com.dongtong.customer.domain.CustomerLiaison;
import com.dongtong.customer.domain.CustomerShopCorrect;
import com.dongtong.customer.dto.req.VisitInfoReqDTO;
import com.dongtong.customer.dto.resp.ShopInfoDTO;
import com.shfc.common.result.ResultDO;

/**
 * @Package com.dongtong.customer.service
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhoumin
 * @date 17/5/7 下午2:43
 * version V1.0.0
 */
public interface CustomerShopService {
    /**
     * 客户端 商铺纠错接口
     * @Author zhoumin
     * @param customerShopCorrect
     * @return
     */
    ResultDO<Boolean> createCorrect(CustomerShopCorrect customerShopCorrect);

    /**
     * 客户端 踩盘预约接口---接口重复
     * @Author zhoumin
     * @param visitInfoReqDTO
     * @return
     */
    ResultDO<Long> createVisit(VisitInfoReqDTO visitInfoReqDTO);

    /**
     * 客户端 店铺收藏接口
     * @Author zhoumin
     * @param customerCollectedShop
     * @return
     */
    ResultDO<Long> createCollected(CustomerCollectedShop customerCollectedShop);

    /**
     * 客户端 直拨电话记录接口
     * @Author zhoumin
     * @param customerLiaison
     * @return
     */
    ResultDO<Boolean> createLiaisonRecord(CustomerLiaison customerLiaison);

    /**
     * 商铺是否被收藏、约看
     * @Author zhoumin
     * @param shopId
     * @param customerId
     * @return
     */
    ResultDO<ShopInfoDTO> queryShopInfo(Long shopId , Long customerId);

    /**
     * 商铺浏览
     * @Author zhoumin
     * @param shopId
     * @param customerId
     * @return
     */
    ResultDO<Boolean> createBrowseShop(Long shopId , Long customerId);
}
