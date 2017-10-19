package com.dongtong.basic.manager;

import com.dongtong.basic.dao.BaseNotificationMapper;
import com.dongtong.basic.domain.BaseNotification;
import com.dongtong.basic.dto.req.ShopNoticeReqDTO;
import com.dongtong.basic.enums.NoticeType;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.enums.ShopServiceType;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.manager
 * @Description ：商铺通知管理
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-09 19:36
 * version V1.0.0
 **/
@Service
public class ShopNoticeManager {

    @Autowired
    private BaseNotificationMapper baseNotificationMapper;

    /**
     * 商铺通知添加
     * @param shopNoticeDTO
     * @return
     */
    public ResultDO<Long> addShopNotice(ShopNoticeReqDTO shopNoticeDTO){
        ResultDO resultDO=new ResultDO();
        BaseNotification baseNotification=new BaseNotification();
        baseNotification.setBizId(shopNoticeDTO.getBussinessId());     //所对应业务Id
        baseNotification.setNotifyType(NoticeType.SHOP.getValue());   //服务通知类型:商铺
        baseNotification.setReceiverId(shopNoticeDTO.getReceiveId());  //接收人Id
        baseNotification.setReceiverType(ReceiveType.CLERK.getValue()); //服务为业务端通知即接收类型为1：业务员
        baseNotification.setServiceType(shopNoticeDTO.getShopNoticeType());
        baseNotification.setStatus(1); //消息查看状态 默认未查看
        String title = "";
        String content = "";
        if(shopNoticeDTO.getShopNoticeType()== ShopServiceType.SOLD_OUT.getValue()){
            title = "房东撤销了店铺的出租";
            content = "你发布的商铺（"+shopNoticeDTO.getShopAddress()+"），已被房东撤销出租。";
        }else if(shopNoticeDTO.getShopNoticeType()== ShopServiceType.RECEIVE_SHOP.getValue()){
            title = "你收到了一个新的店铺";
            content = "你收到了一个新的店铺";
        }else if(shopNoticeDTO.getShopNoticeType()== ShopServiceType.PUBLISH_SHOP.getValue()){
            title = "你发布的线索已发布上线";
            content = "你发布的线索已发布上线";
        }else if(shopNoticeDTO.getShopNoticeType()== ShopServiceType.SHOP_TIMEOUT.getValue()){
            title = "店铺跟进提醒";
            content = "你的店铺即将超时，请尽快跟进";
        }
        baseNotification.setDigest(title);  //消息通知简要
        baseNotification.setContent(content);//消息通知内容
        baseNotification.setCreateTime(new Date());  //通知时间
        int result= baseNotificationMapper.insert(baseNotification);
        if(result>0){
            resultDO.setSuccess(true);
            resultDO.setData(baseNotification.getId());
        }else{
            resultDO.setErrMsg("添加记录失败");
        }
        return  resultDO;
    }



}
