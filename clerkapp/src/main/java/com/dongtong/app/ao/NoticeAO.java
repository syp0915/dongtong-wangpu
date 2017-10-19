package com.dongtong.app.ao;

import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.basic.dto.req.BaseNoticeReqDTO;
import com.dongtong.basic.dto.resp.NotifyListRespDTO;
import com.dongtong.basic.dto.resp.NotifyTypeListRespDTO;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.query.NoticeQuery;
import com.dongtong.basic.service.NotificationService;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunyaping
 * @Package com.dongtong.app.ao
 * @Description :消息通知
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-15 9:55
 * version V1.0.0
 **/
@Service
public class NoticeAO {

    @Autowired(required = false)
    private NotificationService notificationService;

    /**
     * 是否有未读消息
     * @param receiveType
     * @return
     */
    public ResultDO<Boolean> IsUnRead(Integer receiveType){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        return  notificationService.checkNotification(userId.toString(),receiveType);
    }

    /**
     * 获取通知列表
     * @return
     */
    public ResultDO<NotifyTypeListRespDTO> getNoticeList(){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        BaseNoticeReqDTO baseNoticeReqDTO=new BaseNoticeReqDTO();
        baseNoticeReqDTO.setReceiveId(userId);
        baseNoticeReqDTO.setReceiveType(ReceiveType.CLERK.getValue());
        return notificationService.getNoticeList(baseNoticeReqDTO);
    }

    /**
     * 通过通知类型获取相应类型通知列表
     * @param noticeQuery
     * @return
     */
    public ResultDO<NotifyListRespDTO> getNoticeByTypeList(NoticeQuery noticeQuery){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        noticeQuery.setReceiverId(userId);
        noticeQuery.setReceiverType(ReceiveType.CLERK.getValue());
        return notificationService.selectNoticeListByType(noticeQuery);
    }
}
