package com.dongtong.customer.manager;

import com.dongtong.customer.dao.*;
import com.dongtong.customer.domain.CustomerBrowseShop;
import com.dongtong.customer.domain.CustomerCollectedShop;
import com.dongtong.customer.domain.CustomerLiaison;
import com.dongtong.customer.domain.CustomerShopCorrect;
import com.dongtong.customer.dto.req.VisitInfoReqDTO;
import com.dongtong.customer.dto.resp.ShopInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package com.dongtong.customer.manager
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhoumin
 * @date 17/5/7 下午2:54
 * version V1.0.0
 */
@Service
public class ShopManager {
    @Autowired
    private CustomerShopCorrectMapper customerShopCorrectMapper;
    @Autowired
    private CustomerVisitShopMapper customerVisitShopMapper;
    @Autowired
    private CustomerCollectedShopMapper customerCollectedShopMapper;
    @Autowired
    private CustomerLiaisonMapper customerLiaisonMapper;
    @Autowired
    private CustomerBrowseShopMapper customerBrowseShopMapper;

    /**
     * 纠错
     * Author zhoumin
     * @param customerShopCorrect
     */
    public void createCorrect(CustomerShopCorrect customerShopCorrect){
        customerShopCorrect.setCreater(customerShopCorrect.getCustomerId());
        customerShopCorrectMapper.insert(customerShopCorrect);
    }

    /**
     * 约看
     * Author zhoumin
     * @param visitInfoReqDTO
     * @return
     */
    public Long createVisit(VisitInfoReqDTO visitInfoReqDTO){
        visitInfoReqDTO.setCreater(visitInfoReqDTO.getCustomerId());
        visitInfoReqDTO.setStatus(0);
        customerVisitShopMapper.insert(visitInfoReqDTO);
        return visitInfoReqDTO.getId();
    }

    /**
     * 收藏
     * Author zhoumin
     * @param customerCollectedShop
     * @return
     */
    public Long createCollected(CustomerCollectedShop customerCollectedShop){
        customerCollectedShop.setCreater(customerCollectedShop.getCustomerId());
        customerCollectedShopMapper.insert(customerCollectedShop);
        Long collectedId = customerCollectedShop.getId();
        return collectedId;
    }

    /**
     * 拨打电话
     * Author zhoumin
     * @param customerLiaison
     */
    public void createLiaisonRecord(CustomerLiaison customerLiaison){
        customerLiaison.setCreater(customerLiaison.getCustomerId());
        customerLiaisonMapper.insert(customerLiaison);
    }

    /**
     * 查询商铺是否约看、收藏
     * Author zhoumin
     * @param shopId
     * @param customerId
     * @return
     */
    public ShopInfoDTO queryShopInfo(Long shopId , Long customerId){
        Long visitId = customerVisitShopMapper.queryShopVisitInfo(shopId , customerId);
        Long collectedId = customerCollectedShopMapper.queryShopCollectedInfo(shopId , customerId);
        ShopInfoDTO dto = new ShopInfoDTO();
        dto.setVisitId(visitId);
        dto.setCollectedId(collectedId);
        return dto;
    }

    /**
     * 商铺浏览记录
     * Author zhoumin
     * @param shopId
     * @param customerId
     */
    public void createBrowseShop(Long shopId , Long customerId){
        CustomerBrowseShop browseShop = new CustomerBrowseShop();
        browseShop.setCustomerId(customerId);
        browseShop.setShopId(shopId);
        browseShop.setCreater(customerId);
        customerBrowseShopMapper.insert(browseShop);
    }

    /**
     * 商铺收藏
     * Author zhoumin
     * @param customerId
     * @param shopId
     * @return
     */
    public Long selectCollected(Long shopId , Long customerId) {
        return customerCollectedShopMapper.queryShopCollectedInfo(shopId , customerId);
    }
}
