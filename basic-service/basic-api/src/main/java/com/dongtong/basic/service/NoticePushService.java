package com.dongtong.basic.service;

import com.dongtong.basic.dto.req.BussinessNoticeReqDTO;
import com.dongtong.basic.dto.req.ServiceNoticeReqDTO;
import com.dongtong.basic.dto.req.ShopNoticeReqDTO;
import com.dongtong.basic.dto.req.WorkNoticeReqDTO;
import com.shfc.common.result.ResultDO;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.service
 * @Description：消息推送服务
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-11 17:37
 * version V1.0.0
 **/
public interface NoticePushService {

    /**
     * 帖子评论通知  catalogType=2时：0-发帖人评论通知
     * @param bussinessNoticeReqDTO
     * @param deviceId
     * @param osType
     * @return
     */
      ResultDO<Boolean> pushPostCommentNotify(BussinessNoticeReqDTO bussinessNoticeReqDTO,
                                              String deviceId,Integer osType);

    /**
     * 帖子或评论被删除通知 catalogType=2时 通知类型 1：帖子被删 2：评论被删 3:评论被评论
     * @param bussinessNoticeReqDTO
     * @param deviceId
     * @param osType
     * @return
     */
      ResultDO<Boolean> pushBusDelNotify(BussinessNoticeReqDTO bussinessNoticeReqDTO,
                                         String deviceId,Integer osType);

    /**
     * 日程提醒通知 atalogType=0时  3-日程提醒
     * @param serviceNoticeReqDTO
     * @param deviceId
     * @param osType
     * @return
     */
      ResultDO<Boolean> pushScheduleNotify(ServiceNoticeReqDTO serviceNoticeReqDTO,
                                           String deviceId,Integer osType);

    /**
     * 服务日程变动提醒 catalogType=0时 0-预约看铺 1-旺铺寻租 2-签约租铺 3-日程提醒 4-服务完成 5-租客看铺 6-租客签约
     * @param serviceNoticeReqDTO
     * @param deviceId
     * @param osType
     * @return
     */
      ResultDO<Boolean> pushScheduleChangeNotify(ServiceNoticeReqDTO serviceNoticeReqDTO,String userPhone,
                                                 String deviceId,Integer osType);

    /**
     * 服务撤销提醒 catalogType=0时 0-预约看铺 1-旺铺寻租 2-签约租铺 3-日程提醒 4-服务完成 5-租客看铺 6-租客签约
     * @param serviceNoticeReqDTO
     * @param deviceId
     * @param osType
     * @return
     */
      ResultDO<Boolean> pushServiceRevokeNotify(ServiceNoticeReqDTO serviceNoticeReqDTO,String userPhone,
                                                String deviceId,Integer osType);

    /**
     * 服务发布成功短信提醒
     * @param serviceNoticeReqDTO
     * @param userPhone
     * @return
     */
      ResultDO<Boolean> pushServiceSucNotify(ServiceNoticeReqDTO serviceNoticeReqDTO,String userPhone);


    /**
     * 小喇叭消息推送 （不用存入本地库） 5-小喇叭  0-实堪 1-约看 2-签约 9-线索
     * @return
     */
    ResultDO<Boolean> pushTrumpetNotice(String deviceId,Integer osType,Integer type,String name,String roadName);

    /**
     * 商铺被下架通知 catalogType=3时：0-商铺下架
     * @param shopNoticeReqDTO
     * @param deviceId
     * @param osType
     * @return
     */
      ResultDO pushShopNotice(ShopNoticeReqDTO shopNoticeReqDTO,String deviceId,Integer osType);

    /**
     * 业务端商铺类通知  catalogType=3时： 1-店铺转到业务员名下2-线索被发布店铺 3-店铺跟进即将超时
     * @param reqDTO
     * @param deviceId
     * @param osType
     * @return
     */
    ResultDO<Boolean> clerkShopNotice(ShopNoticeReqDTO reqDTO,String deviceId,Integer osType);

    /**
     * 接到商户需求通知
     * @param workNoticeReqDTO
     * @param deviceId
     * @param osType
     * @return
     */
      ResultDO<Boolean>  pushWorkNotice(WorkNoticeReqDTO workNoticeReqDTO,String deviceId,Integer osType);

    /**
     * 任务到时提醒 catalogType=4时(工作)  0：实堪 1：约看 2：签约 6-线索转到业务员名下 7-线索被废弃
     * @param workNoticeReqDTO
     * @param deviceId
     * @param osType
     * @return
     */
      ResultDO<Boolean>  pushArrivalTimeNotice(WorkNoticeReqDTO workNoticeReqDTO,String deviceId,Integer osType);

    /**
     * 周/月榜提醒 catalogType=4时 3-周榜排名 4-月榜排名 3：周榜 4：月榜
     * @param workNoticeReqDTO
     * @param deviceId
     * @param osType
     * @return
     */
      ResultDO<Boolean>  pushWeekMonthNotice(WorkNoticeReqDTO workNoticeReqDTO,String deviceId,Integer osType);


    /**
     * 业务端帖子评论通知 catalogType=2时：0-发帖人评论通知
     * @param bussinessNoticeReqDTO
     * @param deviceId
     * @param osType
     * @return
     */
      ResultDO<Boolean> pushPostCommentBusNotify(BussinessNoticeReqDTO bussinessNoticeReqDTO,String deviceId,Integer osType);

    /**
     * @description 服务日程新增提醒 0-预约看铺 1-旺铺寻租 2-签约租铺 3-日程提醒 4-服务完成 5-租客看铺 6-租客签约
     * @package com.dongtong.basic.service
     * @author liaozm
     * @date 2017/8/11 13:57
     * @params
     * @return
     */
    ResultDO<Boolean> pushScheduleAddNotify(ServiceNoticeReqDTO serviceNoticeReqDTO,String userPhone,
                                            String deviceId,Integer osType);
}
