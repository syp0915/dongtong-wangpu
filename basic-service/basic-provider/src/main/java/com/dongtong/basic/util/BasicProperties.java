package com.dongtong.basic.util;

import com.shfc.common.base.Logger;
import com.shfc.common.disconf.BaseProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-18 16:03
 **/
@Service
public class BasicProperties extends BaseProperties {
    //是否开启拦截
    private String requestInterceptorOpenLogo;
    //东方云请求统一基础地址
    private String requestBaseUrl;
    //东方云分配的商户id
    private String merchantId;
    //东方云分配的商户accesskey值
    private String merchantAccessKey;
    //东方云分配的商户encryptkey值
    private String merchantEncryptKey;
    //东方云分配的频道号
    private String merchantChannelNo;
    //消息中心请求统一基础地址
    private String mcShopBaseUrl;
    //消息中心请求环境
    private String mcShopEnvironment;
    //消息中心appId
    private String mcShopAppId;
    //公用短信发送业务id
    private String smsCommonBusinessId;

    //用户端appId
    private String customerAppId;

    //预约看铺重新安排通知短信发送业务id
    private String smsSeeShopReorderBusinessId;
    //旺铺寻租重新安排通知短信发送业务id
    private String smsRentReorderBusinessId;
    //签约租铺重新安排通知短信发送业务id
    private String smsSignReorderBusinessId;

    //预约看铺撤销通知短信发送业务id
    private String smsSeeShopRevokeBusinessId;
    //旺铺寻租撤销通知短信发送业务id
    private String smsRentRevokeBusinessId;
    //签约租铺撤销通知短信发送业务id
    private String smsSignRevokeBusinessId;

    //发布成功通知
    private String smsPublishBusinessId;

    //用户端评论通知业务id
    private String commentBusinessId;

    //用户端帖子删除通知业务id
    private String busPostDelBusinessId;
    //用户端评论删除通知业务id
    private String busCommentDelBusinessId;


    //用户端日程通知业务id
    private String scheduleBusinessId;

    //用户端预约看铺服务日程变动通知业务id
    private String scheduleSeeChangeBusinessId;
    //用户端旺铺寻租服务日程变动通知业务id
    private String scheduleRentChangeBusinessId;
    //用户端签约租铺服务日程变动通知业务id
    private String scheduleSignChangeBusinessId;



    //用户端预约看铺服务撤销通知业务id
    private String serviceSeeRevokeBusinessId;
    //用户端旺铺寻租服务撤销通知业务id
    private String serviceRentRevokeBusinessId;
    //用户端签约租铺服务撤销通知业务id
    private String serviceSignRevokeBusinessId;



    //用户端服务发布成功通知业务id
    private String serviceSucBusinessId;

    //业务端appId
    private String mcShopClerkAppId;

    //业务端小喇叭实堪通知业务id
    private String trumpetProspectBusinessId;
    //业务端小喇叭约看通知业务id
    private String trumpetSeeBusinessId;
    //业务端小喇叭签约通知业务id
    private String trumpetSignBusinessId;



    //业务端商铺通知业务id
    private String shopBusinessId;

    //业务端接到实堪任务通知业务id
    private String workProspectBusinessId;
    //业务端接到约看任务通知业务id
    private String workSeeBusinessId;
    //业务端接到签约任务通知业务id
    private String workSignBusinessId;



    //业务端实堪任务到时通知业务id
    private String arrivalTimeProspectBusinessId;
    //业务端约看任务到时通知业务id
    private String arrivalTimeSeeBusinessId;
    //业务端签约任务到时通知业务id
    private String arrivalTimeSignBusinessId;



    //业务端周榜通知业务id
    private String weekBusinessId;
    //业务端月榜通知业务id
    private String monthBusinessId;


    //业务端评论通知通知业务id
    private String postCommentbusBusinessId;

    private String historyRankingStartDate;

    //万能验证码开关
    private String universalSmsCodeSwitch;

    public String getHistoryRankingStartDate() {
        try {
            return this.getProperty("history.ranking.start.date");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数history.ranking.start.date异常",e);
            return "";
        }
    }

    public String getRequestInterceptorOpenLogo() {
        try {
            return this.getProperty("request.interceptor.openLogo");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数request.interceptor.openLogo异常",e);
            return "";
        }
    }



    public String getRequestBaseUrl() {
        try {
            return this.getProperty("dfr.request.base.url");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数dfr.request.base.url异常",e);
            return "";
        }
    }

    public String getMerchantId() {
        try {
            return this.getProperty("dfr.merchant.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数dfr.merchant.id异常",e);
            return "";
        }
    }

    public String getMerchantAccessKey() {
        try {
            return this.getProperty("dfr.merchant.access.key");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数dfr.merchant.access.key异常",e);
            return "";
        }
    }


    public String getMerchantEncryptKey() {
        try {
            return this.getProperty("dfr.merchant.encrypt.key");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数dfr.merchant.encrypt.key异常",e);
            return "";
        }
    }

    public String getMerchantChannelNo() {
        try {
            return this.getProperty("dfr.merchant.channel.no");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数dfr.merchant.channel.no异常",e);
            return "";
        }
    }

    public String getMcShopBaseUrl() {
        try {
            return this.getProperty("mc.shop.base.url");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.base.url异常",e);
            return "";
        }
    }

    public String getMcShopEnvironment() {
        try {
            return this.getProperty("mc.shop.environment");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.environment异常",e);
            return "";
        }
    }

    public String getMcShopAppId() {
        try {
            return this.getProperty("mc.shop.customer.appId");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数dfr.request.base.url异常",e);
            return "";
        }
    }

    public String getSmsCommonBusinessId() {
        try {
            return this.getProperty("mc.shop.sms.common.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.sms.common.business.id异常",e);
            return "";
        }
    }


    public String getSmsSeeShopReorderBusinessId() {
        try {
            return this.getProperty("mc.shop.sms.see.reorder.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.sms.see.reorder.business.id异常",e);
            return "";
        }
    }

    public String getSmsRentReorderBusinessId() {
        try {
            return this.getProperty("mc.shop.sms.rent.reorder.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.sms.rent.reorder.business.id异常",e);
            return "";
        }
    }

    public String getSmsSignReorderBusinessId() {
        try {
            return this.getProperty("mc.shop.sms.sign.reorder.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.sms.sign.reorder.business.id异常",e);
            return "";
        }
    }

    public String getCustomerAppId(){
        try {
            return this.getProperty("mc.shop.customer.appId");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.customer.appId异常",e);
            return "";
        }
    }


    public String getSmsSeeShopRevokeBusinessId() {
        try {
            return this.getProperty("mc.shop.sms.see.revoke.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.sms.see.revoke.business.id异常",e);
            return "";
        }
    }

    public String getSmsRentRevokeBusinessId() {
        try {
            return this.getProperty("mc.shop.sms.rent.revoke.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.sms.rent.revoke.business.id异常",e);
            return "";
        }
    }

    public String getSmsSignRevokeBusinessId() {
        try {
            return this.getProperty("mc.shop.sms.sign.revoke.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.sms.sign.revoke.business.id异常",e);
            return "";
        }
    }

    public String getSmsPublishBusinessId() {
        try {
            return this.getProperty("mc.shop.sms.publish.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.sms.publish.business.id异常",e);
            return "";
        }
    }

    public String getCommentBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.post.comment.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.post.comment.business.id异常",e);
            return "";
        }
    }

    public String getCommentReplayBussnessId(){
        try {
            return this.getProperty("mc.shop.notify.post.replay.comment.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.post.comment.business.id异常",e);
            return "";
        }
    }

    public String getBusPostDelBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.bus.post.del.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.bus.post.del.business.id异常",e);
            return "";
        }
    }

    public String getBusCommentDelBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.bus.comment.del.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.bus.comment.del.business.id异常",e);
            return "";
        }
    }

    public String getScheduleBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.schedule.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.schedule.business.id异常", e);
            return "";
        }
    }

    public String getScheduleSeeChangeBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.schedule.see.change.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.schedule.see.change.business.id异常",e);
            return "";
        }
    }

    public String getScheduleSignShopAddBusinessId(){
        try {
            return this.getProperty("mc.shop.sms.trent.sign.create.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.schedule.see.change.business.id异常",e);
            return "";
        }
    }

    /**
     * @description 租客看铺
     * @package com.dongtong.basic.util
     * @author liaozm
     * @date 2017/8/22 11:49
     * @params
     * @return
     */
    public String getScheduleRentShopAddBusinessId(){
        try {
            return this.getProperty("mc.shop.sms.see.shop.create.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.schedule.see.change.business.id异常",e);
            return "";
        }
    }

    /**
     * @description 签约租铺
     * @package com.dongtong.basic.util
     * @author liaozm
     * @date 2017/8/22 11:46
     * @params
     * @return
     */
    public String getScheduleSignAddBusinessId() {
        try {
            return this.getProperty("mc.shop.sms.sign.rent.create.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.schedule.see.change.business.id异常",e);
            return "";
        }
    }

    /**
     * @description 预约看铺
     * @package com.dongtong.basic.util
     * @author liaozm
     * @date 2017/8/22 11:46
     * @params
     * @return
     */
    public String getScheduleSeeAddBusinessId() {
        try {
            return this.getProperty("mc.shop.sms.see.rent.create.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.schedule.see.change.business.id异常",e);
            return "";
        }
    }
    /**
     * @description 旺铺寻租
     * @package com.dongtong.basic.util
     * @author liaozm
     * @date 2017/8/22 11:46
     * @params
     * @return
     */
    public String getScheduleRentAddBusinessId(){
        try {
            return this.getProperty("mc.shop.sms.rent.create.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.schedule.see.change.business.id异常",e);
            return "";
        }
    }
    /**
     * @description 租客看铺
     * @package com.dongtong.basic.util
     * @author liaozm
     * @date 2017/8/22 11:32
     * @params
     * @return
     */
    public String getScheduleTrentSeeChangeBusinessId(){
        try {
            return this.getProperty("mc.shop.notify.schedule.trent.see.change.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.schedule.see.change.business.id异常",e);
            return "";
        }
    }
    /**
     * @description 租客签约
     * @package com.dongtong.basic.util
     * @author liaozm
     * @date 2017/8/22 11:34
     * @params
     * @return
     */
    public String getScheduleTrentSignChangeBusinessId(){
        try {
            return this.getProperty("mc.shop.notify.schedule.trent.sign.change.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.schedule.see.change.business.id异常",e);
            return "";
        }
    }

    public String getScheduleRentChangeBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.schedule.rent.change.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.schedule.rent.change.business.id异常",e);
            return "";
        }
    }

    public String getScheduleSignChangeBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.schedule.sign.change.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.schedule.sign.change.business.id异常",e);
            return "";
        }
    }

    public String getServiceSeeRevokeBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.service.see.revoke.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.service.see.revoke.business.id异常",e);
            return "";
        }
    }

    public String getServiceRentRevokeBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.service.rent.revoke.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.service.rent.revoke.business.id异常",e);
            return "";
        }
    }

    public String getServiceSignRevokeBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.service.sign.revoke.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.service.sign.revoke.business.id异常",e);
            return "";
        }
    }

    public String getServiceSucBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.service.suc.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.service.suc.business.id异常",e);
            return "";
        }
    }

    public String getTrumpetProspectBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.trumpet.prospect.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.trumpet.prospect.business.id异常",e);
            return "";
        }
    }
    public String getTrumpetSeeBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.trumpet.see.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.trumpet.see.business.id异常",e);
            return "";
        }
    }
    public String getTrumpetSignBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.trumpet.sign.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.trumpet.sign.business.id异常",e);
            return "";
        }
    }

    public String getShopBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.shop.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.shop.business.id异常",e);
            return "";
        }
    }

    public String getWorkProspectBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.work.prospect.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.work.prospect.business.id异常",e);
            return "";
        }
    }

    public String getWorkSeeBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.work.see.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.work.see.business.id异常",e);
            return "";
        }
    }

    public String getWorkSignBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.work.sign.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.work.sign.business.id异常",e);
            return "";
        }
    }

    public String getArrivalTimeProspectBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.arrival.time.prospect.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.arrival.time.prospect.business.id异常",e);
            return "";
        }
    }
    public String getArrivalTimeSeeBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.arrival.time.see.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.arrival.time.see.business.id异常",e);
            return "";
        }
    }
    public String getArrivalTimeSignBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.arrival.time.sign.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.arrival.time.sign.business.id异常",e);
            return "";
        }
    }
    public String getArrivalTimeScapClubBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.arrival.scap.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.arrival.time.sign.business.id异常",e);
            return "";
        }
    }
    public String getArrivalHintScapClubBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.hint.scap.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.arrival.time.sign.business.id异常",e);
            return "";
        }
    }
    public String getWeekBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.week.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.week.business.id异常",e);
            return "";
        }
    }
    public String getMonthBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.month.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.month.business.id异常",e);
            return "";
        }
    }

    public String getPostCommentbusBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.post.commentbus.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.post.commentbus.business.id异常",e);
            return "";
        }
    }

    public String getMcShopClerkAppId(){
        try {
            return this.getProperty("mc.shop.clerk.appId");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.clerk.appId异常",e);
            return "";
        }
    }

    public String getUniversalSmsCodeSwitch(){
        try {
            return this.getProperty("universal.sms.code.switch");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数universal.sms.code.switc异常",e);
            return "0";
        }
    }

    public String getHintToShopBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.hint.to.shop.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.hint.to.shop.business.id异常",e);
            return "";
        }
    }
    public String getShopFollowBusinessId() {
        try {
            return this.getProperty("mc.shop.notify.shop.follow.business.id");
        } catch (IOException e) {
            Logger.error(BaseProperties.class, "获取参数mc.shop.notify.shop.follow.business.id异常",e);
            return "";
        }
    }

}
