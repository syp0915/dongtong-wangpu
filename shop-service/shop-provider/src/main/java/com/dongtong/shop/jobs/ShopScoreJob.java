package com.dongtong.shop.jobs;

import com.dongtong.shop.dao.ShopMapper;
import com.dongtong.shop.domain.Shop;
import com.dongtong.shop.domain.ShopShelf;
import com.dongtong.shop.dto.ShopDetailDTO;
import com.dongtong.shop.enums.ShelfStatus;
import com.dongtong.shop.env.ShopEnv;
import com.dongtong.shop.manager.ShopShelfManager;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Package com.dongtong.shop.jobs.ShopScoreJob
 * @Description: 分值计算
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/14 12:43
 * version V1.0.0
 */
@Component
public class ShopScoreJob {
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ShopEnv shopEnv;
    @Autowired
    private ShopShelfManager shopShelfManager;

    @Async
    public void executeShopShelf(List<ShopShelf> list){
        if(list != null && list.size() > 0){
            for(ShopShelf shelf: list){
                asyncCalculateShopScoreJob(shelf.getShopId(), shelf);
            }
        }
    }

    private void asyncCalculateShopScoreJob(Long shopId, ShopShelf shelf){
        try{
            ShopDetailDTO dto = shopMapper.queryShopDetail(shopId);
            if(dto != null &&
                    dto.getShelfStatus().intValue() == ShelfStatus.PUT.getValue()) {
                float totalScore = 0.00f;
                float imgWeight = 0.00f;
                float nearWeight = 0.00f;
                float baseWeight = 0.00f;
                float operateWeight = 0.00f;
                float supportWeight = 0.00f;
                // 图片权重
                if(!ValidateHelper.isEmptyCollection(dto.getImageList())){
                    float size = shopEnv.getImgMaxScore()/shopEnv.getImgScore();
                    if(dto.getImageList().size() < size){
                        imgWeight = dto.getImageList().size() * shopEnv.getImgScore();
                    }else{
                        imgWeight = shopEnv.getImgMaxScore();
                    }
                }
                // 邻铺权重
                if(!ValidateHelper.isEmptyCollection(dto.getNearInfoList())){
                    float size = shopEnv.getNearMaxScore()/shopEnv.getNearScore();
                    if(dto.getNearInfoList().size() < size){
                        nearWeight = dto.getNearInfoList().size() * shopEnv.getNearScore();
                    }else{
                        nearWeight = shopEnv.getNearMaxScore();
                    }
                }
                // 门面权重-楼层、面宽、进深、层高
                if(baseWeight < shopEnv.getBaseMaxScore()
                        && dto.getFloor() != null) baseWeight += shopEnv.getBaseScore();
                if(baseWeight < shopEnv.getBaseMaxScore()
                        && dto.getWidth() != null) baseWeight += shopEnv.getBaseScore();
                if(baseWeight < shopEnv.getBaseMaxScore()
                        && dto.getDepth() != null) baseWeight += shopEnv.getBaseScore();
                if(baseWeight < shopEnv.getBaseMaxScore()
                        && dto.getHeight() != null) baseWeight += shopEnv.getBaseScore();

                // 营运权重 - 押金、电费、水费、燃气费、物业费
                if(operateWeight < shopEnv.getOperateMaxScore()
                        && dto.getDeposit() != null) operateWeight += shopEnv.getOperateScore();
                if(operateWeight < shopEnv.getOperateMaxScore()
                        && dto.getElectricRate() != null) operateWeight += shopEnv.getOperateScore();
                if(operateWeight < shopEnv.getOperateMaxScore()
                        && dto.getWaterRate() != null) operateWeight += shopEnv.getOperateScore();
                if(operateWeight < shopEnv.getOperateMaxScore()
                        && dto.getGasRate() != null) operateWeight += shopEnv.getOperateScore();
                if(operateWeight < shopEnv.getOperateMaxScore()
                        && dto.getPropertyRate() != null) operateWeight += shopEnv.getOperateScore();

                // 配套权重
                if(!ValidateHelper.isEmptyCollection(dto.getSupportList())){
                    float size = shopEnv.getSupportMaxScore()/shopEnv.getSupportScore();
                    if(dto.getSupportList().size() < size){
                        supportWeight = dto.getSupportList().size() * shopEnv.getSupportScore();
                    }else{
                        supportWeight = shopEnv.getNearMaxScore();
                    }
                }
                totalScore = imgWeight + nearWeight + baseWeight + operateWeight + supportWeight;
                Shop shop = shopMapper.selectByPrimaryKey(shopId);
                ShopShelf shopShelf = new ShopShelf();
                shopShelf.setId(shelf.getId());
                shopShelf.setVersion(shelf.getVersion());
                shopShelf.setModifier(-1L);
                shopShelf.setRunSign(1);
                // 判断分值与是否相等  不相等则更新  相等则不需要改变
                Float weightValue = shop.getWeightValue();
                if(weightValue == null || weightValue != totalScore){
                    Shop domain = new Shop();
                    domain.setId(shop.getId());
                    domain.setVersion(shop.getVersion());
                    domain.setModifier(-1L);
                    domain.setWeightValue(totalScore);
                    shopShelfManager.modifyShelfAndShopScore(domain,shopShelf);
                }else {
                    shopShelfManager.updateByPrimaryKeySelective(shopShelf);
                }
            }
        }catch (Exception e){
            Logger.error(ShopScoreJob.class, "商铺基础分值计算异常" + e.getMessage(), e);
        }
    }
}
