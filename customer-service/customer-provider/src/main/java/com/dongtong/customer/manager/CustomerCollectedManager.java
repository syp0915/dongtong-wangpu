package com.dongtong.customer.manager;

import com.dongtong.customer.dao.CustomerCollectedShopMapper;
import com.dongtong.customer.domain.CustomerCollectedShop;
import com.dongtong.customer.dto.resp.CollectStatisticDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package com.dongtong.customer.manager.CustomerCollectedManager
 * @Description: 用户收藏管理
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/10 17:08
 * version V1.0.0
 */
@Service
public class CustomerCollectedManager {
    @Autowired
    private CustomerCollectedShopMapper customerCollectedShopMapper;

    /**
     * 用户收藏总数的统计
     * @return
     */
    public List<CollectStatisticDTO> myCollectStatistic(){
        return customerCollectedShopMapper.myCollectStatistic();
    }

    /**
     * 查询收藏
     * @param collectedId
\     * @return
     */
    public CustomerCollectedShop selectById(Long collectedId){
        return customerCollectedShopMapper.selectByPrimaryKey(collectedId);
    }

    /**
     * 取消收藏
     * @param shopId
     * @param customerId
     * @return
     */
    public int deleteByShopId(Long shopId,Long customerId){
        return customerCollectedShopMapper.deleteByShopId(shopId,customerId);
    }
}
