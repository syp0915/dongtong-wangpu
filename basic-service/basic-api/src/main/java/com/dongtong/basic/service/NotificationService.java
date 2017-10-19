package com.dongtong.basic.service;

import com.dongtong.basic.domain.BaseNotification;
import com.dongtong.basic.dto.req.*;
import com.dongtong.basic.dto.resp.NotifyListRespDTO;
import com.dongtong.basic.dto.resp.NotifyTypeListRespDTO;
import com.dongtong.basic.query.NoticeQuery;
import com.shfc.common.result.ResultDO;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.service
 * @Description：消息通知
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-08 17:11
 * version V1.0.0
 **/
public interface NotificationService {

    /**
     * 是否有未读通知（用于商铺首页 个人中心详情）
     * @return
     */
    ResultDO<Boolean> checkNotification(String userId,Integer receiveType);

    /**
     * 根据消息类型，接受类型更新消息状态
     * @return
     */
    ResultDO<Integer> updateNoticeStatus(BaseNotification baseNotification);

    /**
     * 获取通知列表
     * @param baseNoticeDTO
     * @return
     */
    ResultDO<NotifyTypeListRespDTO> getNoticeList(BaseNoticeReqDTO baseNoticeDTO);

    /**
     * 根据通知类型获取通知列表
     * @param noticeQuery
     * @return
     */
    ResultDO<NotifyListRespDTO> selectNoticeListByType(NoticeQuery noticeQuery);


    /**
     * 服务类型通知添加
     * @param serviceNoticeDTO
     * @return
     */
    ResultDO<Long> addServiceNotice(ServiceNoticeReqDTO serviceNoticeDTO);


    /**
     * 生意圈类型通知添加
     * @param bussinessNoticeDTO
     * @return
     */
    ResultDO<Long> addBussinessNotice(BussinessNoticeReqDTO bussinessNoticeDTO);

    /**
     * 商铺类型通知添加
     * @param shopNoticeDTO
     * @return
     */
    ResultDO<Long> addShopNotice(ShopNoticeReqDTO shopNoticeDTO);

    /**
     * 工作类型通知添加
     * @param workNoticeDTO
     * @return
     */
    ResultDO<Long> addWorkNotice(WorkNoticeReqDTO workNoticeDTO);

    /**
     * 评论帖子人数
     * @param baseNoticeReqDTO
     * @return
     */
    ResultDO<Integer> selectBusNoticeCount(BaseNoticeReqDTO baseNoticeReqDTO);

    /**
     * 根据Id更更新
     * @param msgId
     * @return
     */
    ResultDO<Boolean> updateById(Long msgId);

    /**
     * @description
     * @package com.dongtong.basic.service
     * @author chenxs
     * @date 2017/8/22 0022 16:10
     * @param
     * @return
     */
    ResultDO<Integer> updateByBusId(BaseNotification baseNotification);

}
