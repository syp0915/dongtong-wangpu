package com.dongtong.shop.manager;

import com.dongtong.shop.dao.ShopFollowMapper;
import com.dongtong.shop.domain.ShopFollow;
import com.dongtong.shop.dto.ShopFollowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package com.dongtong.shop.manager.ShopFollowManager
 * @Description: 商鋪跟進
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/8 15:35
 * version V1.0.0
 */
@Service
public class ShopFollowManager {
    @Autowired
    private ShopFollowMapper shopFollowMapper;

    public Long saveFollow(ShopFollow follow){
        shopFollowMapper.insert(follow);
        return follow.getId();
    }

    public List<ShopFollowDTO> getFollowListByShopId(Long shopId){
        return shopFollowMapper.getFollowListByShopId(shopId);
    }

    public ShopFollow getShopFollowById(Long id){
        return shopFollowMapper.selectByPrimaryKey(id);
    }

    public boolean deleteFollowById(Long id){
        int count = shopFollowMapper.deleteByPrimaryKey(id);
        if(count == 1) return true;
        return false;
    }

    public boolean updateShopFollow(ShopFollow follow){
        int count = shopFollowMapper.updateByPrimaryKeySelective(follow);
        if(count == 1) return true;
        return false;
    }

    /**
     * 查询7天未跟进的所有商铺
     * add by xiehb
     */
    public List<ShopFollow> getFollowByTimeOut(){
        return shopFollowMapper.getFollowByTimeOut();
    }
}
