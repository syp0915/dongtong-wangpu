package com.dongtong.basic.remote;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.enums.*;
import com.dongtong.basic.util.BasicProperties;
import net.sf.json.JSONObject;
import okhttp3.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/8 下午1:22.
 */
@Service
public class MessageCenterNetRequest{

    private Logger logger = Logger.getLogger(MessageCenterNetRequest.class);

    @Autowired
    private BasicProperties basicProperties;

    /**
     * @description
     * @package com.dongtong.basic.remote
     * @author liaozm
     * @date 2017/8/16 18:18
     * @params appid 应用ID businessId:业务模板ID  osType:设备类型 0:ios 1:android deviceId：设备ID
     * 模板应用参数contentParamList bussParam基础参数
     * @return
     */
    public String sendNotice(String appid, String businessId, int osType, String deviceId, List<String> contentParamList, String bussParam){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appId", appid);
        params.put("businessId", businessId);
        params.put("deviceType", DeviceType.getNameByValue(osType));  //设备类型
        if(osType==0){  //ios
            params.put("type",2);
        }
        if(osType==1){  //android
            params.put("type",0);
        }
        params.put("notifyChannel","jpush");  //消息发送通道
        params.put("deviceId",deviceId);       //设备号
        Map<String, Object> contentParam = new HashMap<String, Object>();
        if(contentParamList==null){
            contentParam.put("notify","notify");
        }else{
            contentParam.put("notify", StringUtils.join(contentParamList, '|'));
        }
        params.put("paramter", contentParam);
        params.put("bussParam",bussParam);
        params.put("environment",basicProperties.getMcShopEnvironment());
        com.shfc.common.base.Logger.info(MessageCenterNetRequest.class,"URL:"+basicProperties.getMcShopBaseUrl()+"---参数:"+JSON.toJSONString(params));//增加日志功能
        return this.requestMessageCenter(basicProperties.getMcShopBaseUrl(), params);
    }


    /**
     * 发送公用类短信验证码
     * @param userPhone
     * @param contentParamList 短信内容变量参数，按照模板顺序
     * @return
     */
    public String sendCommonSmsVerifyCode(String userPhone, List<String> contentParamList){
        return sendSmsMessage(userPhone, basicProperties.getSmsCommonBusinessId(), contentParamList);
    }

    /**************************************************************************************************/

    /******************************************消息通知推送****************************************************/


    /**
     * 帖子评论通知
     * @param paramMap
     * @param hashMap
     * @return
     */
    public String pushPostCommentNotify(HashMap<String,Object> paramMap,HashMap<String,Object> hashMap ){
        int bizType = Integer.parseInt(hashMap.get("bizType")+"");
        if(bizType == BussinessNoticeType.COMMENTED_ON.getValue()){
            paramMap.put("businessId",basicProperties.getCommentReplayBussnessId());
        }else{
            paramMap.put("businessId",basicProperties.getCommentBusinessId());
        }
        paramMap.put("appId",basicProperties.getCustomerAppId());
        return pushNoticeMessage(paramMap,null,hashMap);
    }

    /**
     * 帖子或评论被删除通知
     * @param hashMap
     * @return
     */
    public String pushBusDelNotify(HashMap<String,Object> paramMap,HashMap<String,Object> hashMap ){
        Integer type= Integer.valueOf(hashMap.get("bizType").toString());
        //帖子被撤
        if(type== BussinessNoticeType.WITHDRAW_NOTICE.getValue()){
            paramMap.put("businessId",basicProperties.getBusPostDelBusinessId());
        }
        //评论被删
        if(type== BussinessNoticeType.DELETE_NOTICE.getValue()){
            paramMap.put("businessId",basicProperties.getBusCommentDelBusinessId());
        }
        paramMap.put("appId",basicProperties.getCustomerAppId());
        return pushNoticeMessage(paramMap,null,hashMap);
    }

    /**
     * 日程提醒
     * @param hashMap
     * @return
     */
    public String pushScheduleNotify(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,HashMap<String,Object> hashMap ){
        paramMap.put("businessId",basicProperties.getScheduleBusinessId());
        paramMap.put("appId",basicProperties.getCustomerAppId());
        return pushNoticeMessage(paramMap,contentParamList,hashMap);
    }

    /**
     * 服务日程新增通知
     * @param hashMap
     * @return
     */
    public String pushScheduleAddNotify(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,ArrayList<String> smsContentParamList,HashMap<String,Object> hashMap ){
        Integer type= Integer.valueOf(hashMap.get("bizType").toString());
        //预约看铺
        if(type== ServiceNoticeType.APPOINT_MENTSHOP.getValue()){
            paramMap.put("businessId",basicProperties.getScheduleSeeAddBusinessId());
        }
        //旺铺寻租
        if(type== ServiceNoticeType.SHOP_RENT_SEEKING.getValue()){
            paramMap.put("businessId",basicProperties.getScheduleRentAddBusinessId());
        }
        //签约租铺
        if(type== ServiceNoticeType.CONTRACT_LEASING.getValue()){
            paramMap.put("businessId",basicProperties.getScheduleSignAddBusinessId());
        }
//        //服务完成
//        if(type== ServiceNoticeType.APPOINT_MENTSHOP.getValue()){
//            paramMap.put("businessId",basicProperties.getScheduleSeeChangeBusinessId());
//        }
        //租客看铺
        if(type== ServiceNoticeType.RENTER_MENTSHOP.getValue()){
            paramMap.put("businessId",basicProperties.getScheduleRentShopAddBusinessId());
        }
        //租客签约
        if(type== ServiceNoticeType.RENTER_SIGN.getValue()){
            paramMap.put("businessId",basicProperties.getScheduleSignShopAddBusinessId());
        }
        paramMap.put("appId",basicProperties.getCustomerAppId());
        return pushSendNoticeMessage(paramMap,contentParamList,smsContentParamList,hashMap);
    }

    /**
     * 服务日程变动通知
     * @param hashMap
     * @return
     */
    public String pushScheduleChangeNotify(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,ArrayList<String> smsContentParamList,HashMap<String,Object> hashMap ){
        Integer type= Integer.valueOf(hashMap.get("bizType").toString());
        //预约看铺
        if(type== ServiceNoticeType.APPOINT_MENTSHOP.getValue()){
            paramMap.put("businessId",basicProperties.getScheduleSeeChangeBusinessId());
        }
        //旺铺寻租
        if(type== ServiceNoticeType.SHOP_RENT_SEEKING.getValue()){
            paramMap.put("businessId",basicProperties.getScheduleRentChangeBusinessId());
        }
        //签约租铺
        if(type== ServiceNoticeType.CONTRACT_LEASING.getValue()){
            paramMap.put("businessId",basicProperties.getScheduleSignChangeBusinessId());
        }
        //服务完成
        if(type== ServiceNoticeType.SERVICE_COMPLETION.getValue()){
            paramMap.put("businessId",basicProperties.getScheduleSeeChangeBusinessId());
        }
        //租客看铺
        if(type== ServiceNoticeType.RENTER_MENTSHOP.getValue()){
            paramMap.put("businessId",basicProperties.getScheduleTrentSeeChangeBusinessId());
        }
        //租客签约
        if(type== ServiceNoticeType.RENTER_SIGN.getValue()){
            paramMap.put("businessId",basicProperties.getScheduleTrentSignChangeBusinessId());
        }

        paramMap.put("appId",basicProperties.getCustomerAppId());
        return pushSendNoticeMessage(paramMap,contentParamList,smsContentParamList,hashMap);
    }


    /**
     * 服务撤销通知
     * @param hashMap
     * @return
     */
    public String pushServiceRevokeNotify(HashMap<String,Object> paramMap,ArrayList<String> contentParamList, ArrayList<String> smsContentParamList,HashMap<String,Object> hashMap ){
        Integer type= Integer.valueOf(hashMap.get("bizType").toString());
        //预约看铺
        if(type== ServiceNoticeType.APPOINT_MENTSHOP.getValue()){
            paramMap.put("businessId",basicProperties.getServiceSeeRevokeBusinessId());
        }
        //旺铺寻租
        if(type== ServiceNoticeType.SHOP_RENT_SEEKING.getValue()){
            paramMap.put("businessId",basicProperties.getServiceRentRevokeBusinessId());
        }
        //签约租铺
        if(type== ServiceNoticeType.CONTRACT_LEASING.getValue()){
            paramMap.put("businessId",basicProperties.getServiceSignRevokeBusinessId());
        }
        paramMap.put("appId",basicProperties.getCustomerAppId());
        return pushSendNoticeMessage(paramMap,contentParamList,smsContentParamList,hashMap);
    }
    /**
     * 服务完成通知
     * @param
     * @return
     */
    public String pushServiceSucNotify(String userPhone,ArrayList<String> smsContentParamList){
        return sendSmsMessage(userPhone,basicProperties.getServiceSucBusinessId(),smsContentParamList);
    }

    /**
     * 小喇叭通知
     * @param contentParamList
     * @param hashMap
     * @return
     */
    public String pushTrumpetNotice(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,HashMap<String,Object> hashMap ){
        Integer type=Integer.valueOf(hashMap.get("bizType").toString());
        //实堪
        if(type== WorkServiceType.FIELD_TRIP.getValue()){
            paramMap.put("businessId",basicProperties.getTrumpetProspectBusinessId());
        }
        //约看
        if(type== WorkServiceType.ORDER_SEE.getValue()){
            paramMap.put("businessId",basicProperties.getTrumpetSeeBusinessId());
        }
        //签约
        if(type== WorkServiceType.SIGN_CONTRACT.getValue()){
            paramMap.put("businessId",basicProperties.getTrumpetSignBusinessId());
        }
        paramMap.put("appId",basicProperties.getMcShopClerkAppId());
        return pushNoticeMessage(paramMap,contentParamList,hashMap);
    }

    /**
     * 商铺通知
     * @param contentParamList
     * @param hashMap
     * @return
     */
    public String pushShopNotice(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,HashMap<String,Object> hashMap ){
        Integer type= Integer.valueOf(hashMap.get("bizType").toString());
        if(type.intValue()== ShopServiceType.PUBLISH_SHOP.getValue()){
            paramMap.put("businessId",basicProperties.getHintToShopBusinessId());
        }else if(type.intValue()== ShopServiceType.SHOP_TIMEOUT.getValue()){
            paramMap.put("businessId",basicProperties.getShopFollowBusinessId());
        }else if(type.intValue() == ShopServiceType.SOLD_OUT.getValue()){
            paramMap.put("businessId",basicProperties.getShopBusinessId());
        }
        paramMap.put("appId",basicProperties.getMcShopClerkAppId());
        return pushNoticeMessage(paramMap,contentParamList,hashMap);
    }

    /**
     * 接到任务提醒
     * @param contentParamList
     * @param hashMap
     * @return
     */
    public String pushWorkNotice(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,HashMap<String,Object> hashMap ){
        Integer type= Integer.valueOf(hashMap.get("bizType").toString());
        //实堪
        if(type== WorkServiceType.FIELD_TRIP.getValue()){
            paramMap.put("businessId",basicProperties.getWorkProspectBusinessId());
        }
        //约看
        if(type== WorkServiceType.ORDER_SEE.getValue()){
            paramMap.put("businessId",basicProperties.getWorkSeeBusinessId());
        }
        //签约
        if(type== WorkServiceType.SIGN_CONTRACT.getValue()){
            paramMap.put("businessId",basicProperties.getWorkSignBusinessId());
        }
        paramMap.put("appId",basicProperties.getMcShopClerkAppId());
        return pushNoticeMessage(paramMap,contentParamList,hashMap);
    }

    /**
     * 任务到时提醒
     * @param contentParamList
     * @param hashMap
     * @return
     */
    public String pushArrivalTimeNotice(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,HashMap<String,Object> hashMap ){
       Integer type= Integer.valueOf(hashMap.get("bizType").toString());
        //实堪
        if(type== WorkServiceType.FIELD_TRIP.getValue()){
            paramMap.put("businessId",basicProperties.getArrivalTimeProspectBusinessId());
        }
        //约看
        if(type== WorkServiceType.ORDER_SEE.getValue()){
            paramMap.put("businessId",basicProperties.getArrivalTimeSeeBusinessId());
        }
        //签约
        if(type== WorkServiceType.SIGN_CONTRACT.getValue()){
            paramMap.put("businessId",basicProperties.getArrivalTimeSignBusinessId());
        }
        //线索废弃
        if(type== WorkServiceType.SCRAP_CLUE.getValue()){
            paramMap.put("businessId",basicProperties.getArrivalTimeScapClubBusinessId());
        }
        //线索跟进提醒
        if(type== WorkServiceType.TIMEOUT_CLUE.getValue()){
            paramMap.put("businessId",basicProperties.getArrivalHintScapClubBusinessId());
        }
        paramMap.put("appId",basicProperties.getMcShopClerkAppId());
        return pushNoticeMessage(paramMap,contentParamList,hashMap);
    }

    /**
     * 周/月榜
     * @param contentParamList
     * @param hashMap
     * @return
     */
    public String pushWeekMonthNotice(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,HashMap<String,Object> hashMap ){
        Integer type= Integer.valueOf(hashMap.get("bizType").toString());
        //周榜
        if(type== WorkServiceType.WEEK.getValue()){
            paramMap.put("businessId",basicProperties.getWeekBusinessId());
        }
        //月榜
        if(type== WorkServiceType.MONTH.getValue()){
            paramMap.put("businessId",basicProperties.getMonthBusinessId());
        }
        paramMap.put("appId",basicProperties.getMcShopClerkAppId());
        return pushNoticeMessage(paramMap,contentParamList,hashMap);
    }

    /**
     * 业务端帖子评论
     * @param contentParamList
     * @param hashMap
     * @return
     */
    public String pushPostCommentBusNotify(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,HashMap<String,Object> hashMap ){
        paramMap.put("appId",basicProperties.getMcShopClerkAppId());
        paramMap.put("businessId",basicProperties.getPostCommentbusBusinessId());
        return pushNoticeMessage(paramMap,contentParamList,hashMap);
    }




    /**
     * 短信通知
     * @param userPhone
     * @param businessId
     * @param contentParamList
     * @return
     */
    private String sendSmsMessage(String userPhone, String businessId, List<String> contentParamList){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appId", basicProperties.getMcShopAppId());
        params.put("businessId", businessId);
        params.put("mobile", userPhone);
        Map<String, Object> contentParam = new HashMap<String, Object>();
        contentParam.put("sms", StringUtils.join(contentParamList, '|'));
        params.put("paramter", contentParam);
        params.put("environment",basicProperties.getMcShopEnvironment());
        return this.requestMessageCenter(basicProperties.getMcShopBaseUrl(), params);
    }

    /**
     * 消息推送
     * @param paramMap
     * @param contentParamList
     * @param hashMap
     * @return
     */
    private String pushNoticeMessage(HashMap<String,Object> paramMap, List<String> contentParamList,HashMap hashMap){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appId", paramMap.get("appId"));
        params.put("businessId", paramMap.get("businessId"));
        Integer osType=Integer.valueOf(paramMap.get("osType").toString());
        params.put("deviceType", DeviceType.getNameByValue(osType));  //设备类型
        if((int)(paramMap.get("osType"))==0){  //ios
            params.put("type",2);
        }
        if((int)(paramMap.get("osType"))==1){  //android
            params.put("type",0);
        }
        params.put("notifyChannel","jpush");  //消息发送通道
        params.put("deviceId",paramMap.get("deviceId"));       //设备号
        Map<String, Object> contentParam = new HashMap<String, Object>();
        if(contentParamList==null){
            contentParam.put("notify","notify");
        }else{
            contentParam.put("notify", StringUtils.join(contentParamList, '|'));
        }
        params.put("paramter", contentParam);
        JSONObject json= JSONObject.fromObject(hashMap);
        params.put("bussParam",json.toString());
        params.put("environment",basicProperties.getMcShopEnvironment());
        com.shfc.common.base.Logger.info(MessageCenterNetRequest.class,"URL:"+basicProperties.getMcShopBaseUrl()+"---参数:"+JSON.toJSONString(params));//增加日志功能
        return this.requestMessageCenter(basicProperties.getMcShopBaseUrl(), params);
    }


    /**
     * 短信发送和消息推送
     * @param paramMap
     * @param contentParamList
     * @param hashMap
     * @return
     */
    private String pushSendNoticeMessage(HashMap<String,Object> paramMap, List<String> contentParamList,List<String> smsContentParamList,HashMap hashMap){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appId", paramMap.get("appId"));
        params.put("businessId", paramMap.get("businessId"));
        Integer osType=Integer.valueOf(paramMap.get("osType").toString());
        params.put("deviceType", DeviceType.getNameByValue(osType));  //设备类型
        if((int)(paramMap.get("osType"))==0){  //ios
            params.put("type",2);
        }
        if((int)(paramMap.get("osType"))==1){  //android
            params.put("type",0);
        }
        params.put("mobile", paramMap.get("userPhone"));
        params.put("notifyChannel","jpush");  //消息发送通道
        params.put("deviceId",paramMap.get("deviceId"));       //设备号
        Map<String, Object> contentParam = new HashMap<String, Object>();
        if(contentParamList==null || contentParamList.size()<1){
            contentParam.put("notify","notify");
        }else{
            contentParam.put("notify", StringUtils.join(contentParamList, '|'));
        }
        if(smsContentParamList==null){
            contentParam.put("sms","");
        }else{
            contentParam.put("sms", StringUtils.join(smsContentParamList, '|'));
        }

        params.put("paramter", contentParam);
        JSONObject json= JSONObject.fromObject(hashMap);
        params.put("bussParam",json.toString());
        params.put("environment",basicProperties.getMcShopEnvironment());
        com.shfc.common.base.Logger.info(MessageCenterNetRequest.class,"URL:"+basicProperties.getMcShopBaseUrl()+"---参数:"+JSON.toJSONString(params));//增加日志功能
        return this.requestMessageCenter(basicProperties.getMcShopBaseUrl(), params);
    }


    /**
     * 统一封装参数，向消息中心发起请求
     * @param url
     * @param params
     * @return
     */
    public String requestMessageCenter(String url, Map<String, Object> params){
        logger.info("message center request params----------->" + JSON.toJSONString(params));
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .build();
        client.connectTimeoutMillis();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), JSON.toJSONString(params));
        Request request = new Request.Builder().url(url).post(requestBody).build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()){
                String result = response.body().string();
                logger.info("message center response string----------->" + result);
                return result;
            }else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
