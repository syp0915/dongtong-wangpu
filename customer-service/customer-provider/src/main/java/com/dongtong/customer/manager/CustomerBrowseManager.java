package com.dongtong.customer.manager;

import com.dongtong.customer.dao.CustomerBrowseShopMapper;
import com.dongtong.customer.domain.CustomerBrowseShop;
import com.dongtong.customer.dto.resp.BrowseStatisticDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package com.dongtong.customer.manager.CustomerBrowseManager
 * @Description: 商户浏览商铺管理
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/11 17:44
 * version V1.0.0
 */
@Service
public class CustomerBrowseManager {
    @Autowired
    private CustomerBrowseShopMapper customerBrowseShopMapper;
    /**
     * 用户浏览总数的统计
     * @return
     */
    public List<BrowseStatisticDTO> myBrowseStatistic(){
        return customerBrowseShopMapper.myBrowseStatistic();
    }

    /**
     * 查询浏览商铺
     * @param browseId
     * @return
     */
    public CustomerBrowseShop selectById(Long browseId){
        return customerBrowseShopMapper.selectByPrimaryKey(browseId);
    }

    /**
     * 删除浏览记录
     * @param shopId
     * @param customerId
     * @return
     */
    public int deleteByShopId(Long shopId,Long customerId){
        return customerBrowseShopMapper.deleteByShopId(shopId,customerId);
    }



}
