package com.dongtong.shop.service;

import com.dongtong.shop.domain.ShopFollow;
import com.dongtong.shop.dto.ShopFollowDTO;
import com.dongtong.shop.dto.ShopFollowInfoDTO;
import com.shfc.common.result.ResultDO;

import java.util.List;

/**
 * @Package com.dongtong.shop.service.ShopFollowService
 * @Description: 跟进
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/8 15:23
 * version V1.0.0
 */
public interface ShopFollowService {

    /**
     * 添加跟进
     * @param dto
     * @return
     */
    ResultDO<Long> saveFollow(ShopFollowInfoDTO dto);

    /**
     * 根据商铺id获取跟进列表
     * @param shopId
     * @return
     */
    ResultDO<List<ShopFollowDTO>> getFollowListByShopId(Long shopId);

    /**
     * 删除根据信息
     * @param id
     * @param clerkId
     * @return
     */
    ResultDO<Boolean> deleteFollowById(Long id, Long clerkId);
    /**
     * 查询7天未跟进的所有商铺
     * add by xiehb
     */
    List<ShopFollow> getFollowByTimeOut();
}
