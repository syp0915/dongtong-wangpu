package com.dongtong.app.ao;

import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.shop.dto.ShopFollowDTO;
import com.dongtong.shop.dto.ShopFollowInfoDTO;
import com.dongtong.shop.service.ShopFollowService;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package com.dongtong.app.ao.ShopFollowAO
 * @Description: ShopFollowAO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/10 15:34
 * version V1.0.0
 */
@Service
public class ShopFollowAO {
    @Autowired
    private ShopFollowService shopFollowService;

    public ResultDO<Long> saveFollow(ShopFollowInfoDTO dto){
        ResultDO<Long> resultDO = new ResultDO<>();
        if(dto == null){
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }

        dto.setClerkId(HttpSessionUtils.getCurrentAppUserId());

        return shopFollowService.saveFollow(dto);
    }

    public ResultDO<List<ShopFollowDTO>> getFollowListByShopId(Long shopId){
        return shopFollowService.getFollowListByShopId(shopId);
    }

    public ResultDO<Boolean> deleteFollowById(Long id){
        return shopFollowService.deleteFollowById(id, HttpSessionUtils.getCurrentAppUserId());
    }
}
