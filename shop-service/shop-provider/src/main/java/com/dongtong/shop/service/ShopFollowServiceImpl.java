package com.dongtong.shop.service;

import com.dongtong.shop.domain.Shop;
import com.dongtong.shop.domain.ShopFollow;
import com.dongtong.shop.dto.ShopFollowDTO;
import com.dongtong.shop.dto.ShopFollowInfoDTO;
import com.dongtong.shop.manager.ShopFollowManager;
import com.dongtong.shop.manager.ShopManager;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package com.dongtong.shop.service.ShopFollowServiceImpl
 * @Description: 跟进
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/8 15:35
 * version V1.0.0
 */
@Service
public class ShopFollowServiceImpl implements ShopFollowService {

    @Autowired
    private ShopFollowManager shopFollowManager;
    @Autowired
    private ShopManager shopManager;

    @Override
    public ResultDO<Long> saveFollow(ShopFollowInfoDTO dto) {
        ResultDO<Long> resultDO = new ResultDO<>();
        if(dto == null || dto.getShopId() == null
                || ValidateHelper.isEmpty(dto.getContent())
                || dto.getClerkId() == null){
            resultDO.setErrMsg("请求参数不完整");
            return resultDO;
        }
        Shop shop = shopManager.getShopById(dto.getShopId());
        if(shop == null){
            resultDO.setErrMsg("商铺不存在");
            return resultDO;
        }
        /*if((shop.getRentStatus().intValue() == RentStatus.UN_CLAIM.getValue())
                || shop.getClerkId() == null){
            resultDO.setErrMsg("商铺未认领");
            return resultDO;
        }*/
        if(shop.getClerkId().longValue() != dto.getClerkId().longValue()){
            resultDO.setErrMsg("商铺非当前业务员所有");
            return resultDO;
        }

        try {
            ShopFollow follow = new ShopFollow();
            follow.setShopId(dto.getShopId());
            follow.setClerkId(dto.getClerkId());
            follow.setContent(dto.getContent());
            follow.setCreater(dto.getClerkId());
            resultDO.setData(shopFollowManager.saveFollow(follow));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "添加跟进异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }

        return resultDO;
    }

    @Override
    public ResultDO<List<ShopFollowDTO>> getFollowListByShopId(Long shopId) {

        ResultDO<List<ShopFollowDTO>> resultDO = new ResultDO<>();
        Shop shop = shopManager.getShopById(shopId);
        if(shop == null){
            resultDO.setErrMsg("商铺不存在");
            return resultDO;
        }
        try {
            resultDO.setData(shopFollowManager.getFollowListByShopId(shopId));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "添加跟进异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }

        return resultDO;
    }

    @Override
    public ResultDO<Boolean> deleteFollowById(Long id, Long clerkId) {
        ResultDO<Boolean> resultDO =  new ResultDO<>();

        ShopFollow shopFollow = shopFollowManager.getShopFollowById(id);
        if(shopFollow == null){
            resultDO.setErrMsg("跟进信息不存在");
            return resultDO;
        }
        if(shopFollow.getClerkId().longValue() != clerkId.longValue()){
            resultDO.setErrMsg("跟进信息非当前用户所有");
            return resultDO;
        }
        try {
            shopFollow.setDeleteFlag(1);
            shopFollow.setModifier(clerkId);
            resultDO.setData(shopFollowManager.updateShopFollow(shopFollow));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "添加跟进异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }

        return resultDO;
    }

    @Override
    public List<ShopFollow> getFollowByTimeOut() {
        return shopFollowManager.getFollowByTimeOut();
    }
}
