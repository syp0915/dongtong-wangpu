package com.dongtong.shop.jobs;

import com.dongtong.shop.dao.ShopMapper;
import com.dongtong.shop.dao.ShopStationRelMapper;
import com.dongtong.shop.domain.Shop;
import com.dongtong.shop.domain.ShopStationRel;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package com.dongtong.shop.jobs.ShopStationRelJob
 * @Description: 商铺地铁站关系
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/8 16:02
 * version V1.0.0
 */
@Service
public class ShopStationRelJob {
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ShopStationRelMapper shopStationRelMapper;

    @Async
    public void asyncShopStationRelJob(Long shopId){
        try{
            Shop shop = shopMapper.selectByPrimaryKey(shopId);
            if(shop != null
                    && !ValidateHelper.isEmpty(shop.getLongitude())
                    && !ValidateHelper.isEmpty(shop.getLatitude())){

                // 如果存在则删除原有地铁站关系
                shopStationRelMapper.deleteByShopId(shopId);

                List<ShopStationRel> list = shopStationRelMapper.shopStationRelJob(shop.getLongitude(), shop.getLatitude());
                if(!ValidateHelper.isEmpty(list)){
                    List<ShopStationRel> relList = new ArrayList<>();
                    for(ShopStationRel rel: list){
                        ShopStationRel shopStationRel =  shopStationRelMapper.findRelByShopIdAndStationId(shopId, rel.getStationId());
                        if(shopStationRel == null){
                            // 判断库中是否有重复记录
                            rel.setShopId(shopId);
                            rel.setCreater(-1L);
                            relList.add(rel);
                        }
                    }

                    if(relList.size() > 0){
                        // 判断是有记录待插入
                        shopStationRelMapper.batchInsert(relList);
                    }
                }
            }

        }catch (Exception e){
            Logger.error(ShopStationRelJob.class, "地铁站商铺关系跑批异常" + e.getMessage(), e);
        }
    }
}
