package com.dongtong.scheduled.notice;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dongtong.basic.dto.req.ShopNoticeReqDTO;
import com.dongtong.basic.enums.ShopServiceType;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.service.ClerkService;
import com.dongtong.shop.domain.ShopFollow;
import com.dongtong.shop.service.ShopFollowService;
import com.shfc.common.base.Logger;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Package com.dongtong.scheduled.notice.ShopFollowTimeOut
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/8/21 19:19
 * version V1.0.0
 */
@Component
public class ShopFollowTimeOut implements SimpleJob {
    @Autowired
    private ShopFollowService shopFollowService;
    @Autowired
    private ClerkService clerkService;
    @Autowired
    private NoticePushService noticePushService;

    @Override
    public void execute(ShardingContext shardingContext) {
        List<ShopFollow> followList = shopFollowService.getFollowByTimeOut();
        if(followList!=null && !followList.isEmpty()){
            for(ShopFollow follow : followList){
                Logger.info(this,"店铺跟进7天超时数据：folloList.size="+followList.size()+"follow.toString()="+follow.toString());
                Long clerkId = follow.getClerkId();
                Long shopId = follow.getShopId();
                ResultDO<Clerk> clerkResultDO = clerkService.getClerkInfoById(clerkId);
                if(clerkResultDO.isSuccess()){
                    Clerk clerk = clerkResultDO.getData();
                    ShopNoticeReqDTO shopNoticeReqDTO = new ShopNoticeReqDTO();
                    shopNoticeReqDTO.setBussinessId(shopId);
                    shopNoticeReqDTO.setReceiveId(clerkId);
                    shopNoticeReqDTO.setShopNoticeType(ShopServiceType.SHOP_TIMEOUT.getValue());
                    ResultDO<Boolean> noticeReslut = noticePushService.clerkShopNotice(shopNoticeReqDTO,clerk.getDeviceId(),clerk.getOsType());
                    if(!noticeReslut.isSuccess()){
                        Logger.info(this,"店铺跟进7天即将超时异常："+noticeReslut.toString());
                    }
                }
            }
        }

    }
}
