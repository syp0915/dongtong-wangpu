package com.dongtong.basic.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.constant.ErrorConstant;
import com.dongtong.basic.dto.req.BussinessNoticeReqDTO;
import com.dongtong.basic.dto.req.ServiceNoticeReqDTO;
import com.dongtong.basic.dto.req.ShopNoticeReqDTO;
import com.dongtong.basic.dto.req.WorkNoticeReqDTO;
import com.dongtong.basic.enums.*;
import com.dongtong.basic.manager.NoticePushManager;
import com.dongtong.basic.util.BasicProperties;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.date.DateFormatUtils;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.service
 * @Description ：消息推送服务
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-11 17:36
 * version V1.0.0
 **/
@Service
public class NoticePushServiceImpl implements NoticePushService{

    @Autowired
    private NoticePushManager noticePushManager;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private BasicProperties basicProperties;



    /**
     * 帖子评论通知 catalogType=2时：0-发帖人评论通知
     * @param bussinessNoticeReqDTO  添加所需参数
     * @param deviceId  设备号
     * @param osType  设备类型
     * @return
     */
    @Override
    public  ResultDO<Boolean> pushPostCommentNotify(BussinessNoticeReqDTO bussinessNoticeReqDTO,
                                                    String deviceId,Integer osType){
        ResultDO resultDO=new ResultDO();
        if(ValidateHelper.isEmpty(bussinessNoticeReqDTO) || ValidateHelper.isEmptyString(deviceId) || ValidateHelper.isEmpty(osType)) {
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        try {
//            bussinessNoticeReqDTO.setBussinessType(BussinessNoticeType.COMMENT_NOTICE.getValue());
            //存放本地数据库
            ResultDO<Long> resultAdd= notificationService.addBussinessNotice(bussinessNoticeReqDTO);
            if (!resultAdd.isSuccess()){
                resultDO.setSuccess(false);
                resultDO.setErrCode(resultAdd.getErrCode());
                resultDO.setErrMsg(resultAdd.getErrMsg());
                return resultDO;
            }
            //获取消息通知Id
            Long msgId = resultAdd.getData();
            final HashMap<String, Object> hashMap = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            hashMap.put("sendDateTime", sdf.format(new Date()));
            hashMap.put("catalogType", NoticeType.BUSSINESS.getValue());
            hashMap.put("bizId", bussinessNoticeReqDTO.getBussinessId());
            hashMap.put("bizType", bussinessNoticeReqDTO.getBussinessType());//BussinessNoticeType.COMMENT_NOTICE.getValue()
            hashMap.put("messageId", msgId);
            final HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("deviceId", deviceId);
            paramMap.put("osType", osType);
            resultDO.setSuccess(true);
            new Thread(new Runnable(){
                @Override
                public void run() {
                    ResultDO remoteResult =noticePushManager.pushPostCommentNotify(paramMap, hashMap);
                    Logger.info(NoticePushServiceImpl.class, "帖子评论通知返回结果:"+ JSON.toJSONString(remoteResult));
                }
            }).start();
            resultDO.setSuccess(true);
            resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
            resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
            resultDO.setData(true);
            return resultDO;
        }catch (Exception e){
            Logger.warn(NoticePushServiceImpl.class,"帖子评论通知 catalogType=2时：0-发帖人评论通知,错误信息:"+e.getMessage());
            e.printStackTrace();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.RUNTIME_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.RUNTIME_EXCEPTION.getMsg());
            return resultDO;
        }
    }

    /**
     * 帖子或评论被删除通知 catalogType=2时 通知类型 1：帖子被删 2：评论被删 3:评论被评论
     * @param bussinessNoticeReqDTO   添加所需参数
     * @param deviceId 设备号
     * @param osType 设备类型
     * @return
     */
    @Override
    public  ResultDO<Boolean> pushBusDelNotify(BussinessNoticeReqDTO bussinessNoticeReqDTO,
                                                    String deviceId,Integer osType){
        ResultDO resultDO=new ResultDO();
        if(ValidateHelper.isEmpty(bussinessNoticeReqDTO) ||ValidateHelper.isEmptyString(deviceId) || ValidateHelper.isEmpty(osType)) {
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        //存放本地数据库
        try{
            ResultDO<Long> resultAdd= notificationService.addBussinessNotice(bussinessNoticeReqDTO);
            //获取消息通知Id
            Long msgId = resultAdd.getData();
            final HashMap<String ,Object> hashMap=new HashMap();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            hashMap.put("sendDateTime",sdf.format(new Date()));
            hashMap.put("catalogType", NoticeType.BUSSINESS.getValue());
            hashMap.put("bizType", bussinessNoticeReqDTO.getBussinessType());
            hashMap.put("bizId",  bussinessNoticeReqDTO.getBussinessId());
            hashMap.put("messageId", msgId);
            final HashMap<String,Object> paramMap=new HashMap<>();
            paramMap.put("deviceId",deviceId);
            paramMap.put("osType",osType);
            new Thread(new Runnable(){
                @Override
                public void run() {
                    ResultDO remoteResult =noticePushManager.pushBusDelNotify(paramMap,hashMap);
                    Logger.info(NoticePushServiceImpl.class, "帖子或评论被删除通知返回结果:"+ JSON.toJSONString(remoteResult));
                }
            }).start();
            resultDO.setSuccess(true);
            resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
            resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
            resultDO.setData(true);
            return resultDO;
        }
        catch (Exception e){
            Logger.warn(NoticePushServiceImpl.class,"帖子或评论被删除通知 catalogType=2时 通知类型 1：帖子被删 2：评论被删 3:评论被评论,错误信息:"+e.getMessage());
            e.printStackTrace();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.RUNTIME_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.RUNTIME_EXCEPTION.getMsg());
            return resultDO;
        }
    }

    /**
     * 日程提醒通知 atalogType=0时  3-日程提醒
     * @param serviceNoticeReqDTO 用户Id
     * @param deviceId 设备号
     * @param osType 设备类型
     * @return
     */
    @Override
    public  ResultDO<Boolean> pushScheduleNotify(ServiceNoticeReqDTO serviceNoticeReqDTO,
                                                 String deviceId,Integer osType){
        ResultDO resultDO=new ResultDO();
        if(ValidateHelper.isEmpty(serviceNoticeReqDTO) ||ValidateHelper.isEmptyString(deviceId) || ValidateHelper.isEmpty(osType)) {
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        try {
            serviceNoticeReqDTO.setServiceStatus(ServiceStatus.SCHEDULE_REMIND.getValue());
            //存放本地数据库
            ResultDO<Long> resultAdd= notificationService.addServiceNotice(serviceNoticeReqDTO);
            //获取消息通知Id
            Long msgId = resultAdd.getData();
            final HashMap<String, Object> hashMap = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            hashMap.put("sendDateTime", sdf.format(new Date()));
            hashMap.put("catalogType", NoticeType.SERVICE.getValue());
            hashMap.put("bizType", ServiceStatus.SCHEDULE_REMIND.getValue());
            hashMap.put("messageId", msgId);
            final ArrayList<String> contentParamList = new ArrayList<>();
            contentParamList.add(serviceNoticeReqDTO.getScheduleCount().toString());
            final HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("deviceId", deviceId);
            paramMap.put("osType", osType);
            new Thread(new Runnable(){
                @Override
                public void run() {
                    ResultDO remoteResult = noticePushManager.pushScheduleNotify(paramMap, contentParamList, hashMap);
                    Logger.info(NoticePushServiceImpl.class, "日程提醒通知返回结果:"+ JSON.toJSONString(remoteResult));
                }
            }).start();
            resultDO.setSuccess(true);
            resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
            resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
            resultDO.setData(true);
            return resultDO;
        }catch (Exception e){
            Logger.warn(NoticePushServiceImpl.class,"日程提醒通知 atalogType=0时  3-日程提醒,错误信息:"+e.getMessage());
            e.printStackTrace();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.RUNTIME_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.RUNTIME_EXCEPTION.getMsg());
            return resultDO;
        }
    }

    /**
     * 服务日程变动提醒 catalogType=0时 0-预约看铺 1-旺铺寻租 2-签约租铺 3-日程提醒 4-服务完成 5-租客看铺 6-租客签约
     * @param serviceNoticeReqDTO  添加所需参数
     * @param deviceId 设备号
     * @param osType 设备类型
     * @return
     */
    @Override
    public  ResultDO<Boolean> pushScheduleChangeNotify(ServiceNoticeReqDTO serviceNoticeReqDTO,String userPhone,
                                                       String deviceId,Integer osType){
        ResultDO resultDO=new ResultDO();
        if(ValidateHelper.isEmpty(serviceNoticeReqDTO) || ValidateHelper.isEmptyString(deviceId) || ValidateHelper.isEmpty(osType)) {
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        try {
            Logger.info(NoticePushServiceImpl.class, JSON.toJSONString(serviceNoticeReqDTO));
//            serviceNoticeReqDTO.setServiceStatus(ServiceStatus.TIME_VARIATION.getValue());
            //存放本地数据库
            ResultDO<Long> resultAdd= notificationService.addServiceNotice(serviceNoticeReqDTO);
            Logger.info(NoticePushServiceImpl.class,"调用增加数据方法，结果返回值是:"+JSON.toJSONString(resultAdd));
            //获取消息通知Id
            Long msgId = resultAdd.getData();
            final HashMap<String, Object> hashMap = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            hashMap.put("sendDateTime", sdf.format(new Date()));
            hashMap.put("catalogType", NoticeType.SERVICE.getValue());
            hashMap.put("bizType", serviceNoticeReqDTO.getServiceType());
            hashMap.put("bizId", serviceNoticeReqDTO.getBussinessId());
            hashMap.put("messageId", msgId);
            final ArrayList<String> contentParamList = new ArrayList<>();
            final ArrayList<String> smsContentParamList=new ArrayList<>(); //短信参数
            smsContentParamList.add(serviceNoticeReqDTO.getCurrentTime());
            smsContentParamList.add(serviceNoticeReqDTO.getShopAddress());
            smsContentParamList.add(serviceNoticeReqDTO.getServiceName());
            smsContentParamList.add(serviceNoticeReqDTO.getServiceTel());
            final HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("deviceId", deviceId);
            paramMap.put("osType", osType);
            paramMap.put("userPhone", userPhone);
            new Thread(new Runnable(){
                @Override
                public void run() {
                    ResultDO remoteResult = noticePushManager.pushScheduleChangeNotify(paramMap, contentParamList,smsContentParamList,hashMap);
                    Logger.info(NoticePushServiceImpl.class, "服务日程变动提醒通知返回结果:"+ JSON.toJSONString(remoteResult));
                }
            }).start();
            resultDO.setSuccess(true);
            resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
            resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
            resultDO.setData(true);
            return resultDO;
        }catch (Exception e){
            Logger.warn(NoticePushServiceImpl.class,"服务日程变动提醒 catalogType=0时 0-预约看铺 1-旺铺寻租 2-签约租铺 3-日程提醒 4-服务完成 5-租客看铺 6-租客签约,错误信息:"+e.getMessage());
            e.printStackTrace();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.RUNTIME_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.RUNTIME_EXCEPTION.getMsg());
            return resultDO;
        }
    }

    /**
     * 服务撤销提醒 catalogType=0时 0-预约看铺 1-旺铺寻租 2-签约租铺 3-日程提醒 4-服务完成 5-租客看铺 6-租客签约
     * @param serviceNoticeReqDTO  添加所需参数
     * @param deviceId 设备号
     * @param osType 设备类型
     * @return
     */
    @Override
    public  ResultDO<Boolean> pushServiceRevokeNotify(ServiceNoticeReqDTO serviceNoticeReqDTO,String userPhone,
                                                      String deviceId,Integer osType){
        ResultDO resultDO=new ResultDO();
        if( ValidateHelper.isEmpty(serviceNoticeReqDTO) || ValidateHelper.isEmptyString(deviceId) || ValidateHelper.isEmpty(osType)) {
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        try {
            Logger.info(NoticePushServiceImpl.class, JSON.toJSONString(serviceNoticeReqDTO));
            serviceNoticeReqDTO.setServiceStatus(ServiceStatus.SERVICE_REVOCATION.getValue());
            //存放本地数据库
            ResultDO<Long> resultAdd= notificationService.addServiceNotice(serviceNoticeReqDTO);
            Logger.info(NoticePushServiceImpl.class,"调用增加数据方法，结果返回值是:"+JSON.toJSONString(resultAdd));
            //获取消息通知Id
            Long msgId = resultAdd.getData();
            final HashMap<String, Object> hashMap = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            hashMap.put("sendDateTime", sdf.format(new Date()));
            hashMap.put("catalogType", NoticeType.SERVICE.getValue());
            hashMap.put("bizType", serviceNoticeReqDTO.getServiceType());
            hashMap.put("bizId", serviceNoticeReqDTO.getBussinessId());
            hashMap.put("messageId", msgId);
            final ArrayList<String> contentParamList = new ArrayList<>();
            final ArrayList<String> smsContentParamList=new ArrayList<>(); //短信参数
            smsContentParamList.add(serviceNoticeReqDTO.getReason());
            smsContentParamList.add(serviceNoticeReqDTO.getCurrentTime());
            smsContentParamList.add(serviceNoticeReqDTO.getShopAddress());
            smsContentParamList.add(serviceNoticeReqDTO.getServiceName());
            smsContentParamList.add(serviceNoticeReqDTO.getServiceTel());
            final HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("deviceId", deviceId);
            paramMap.put("osType", osType);
            paramMap.put("userPhone", userPhone);
            new Thread(new Runnable(){
                @Override
                public void run() {
                    ResultDO remoteResult = noticePushManager.pushServiceRevokeNotify(paramMap, contentParamList,smsContentParamList,hashMap);
                    Logger.info(NoticePushServiceImpl.class, "服务撤销提醒通知返回结果:"+ JSON.toJSONString(remoteResult));
                }
            }).start();
            resultDO.setSuccess(true);
            resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
            resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
            resultDO.setData(true);
            return resultDO;
        }catch (Exception e){
            Logger.warn(NoticePushServiceImpl.class,"服务撤销提醒 catalogType=0时 0-预约看铺 1-旺铺寻租 2-签约租铺 3-日程提醒 4-服务完成 5-租客看铺 6-租客签约,错误信息:"+e.getMessage());
            e.printStackTrace();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.RUNTIME_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.RUNTIME_EXCEPTION.getMsg());
            return resultDO;
        }
    }

    /**
     * 服务发布成功短信提醒
     * @param serviceNoticeReqDTO
     * @param userPhone
     * @return
     */
    @Override
    public  ResultDO<Boolean> pushServiceSucNotify(ServiceNoticeReqDTO serviceNoticeReqDTO,final String userPhone){
        ResultDO resultDO=new ResultDO();
        if(ValidateHelper.isEmpty(serviceNoticeReqDTO) || ValidateHelper.isEmptyString(userPhone)) {
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        try {
            final ArrayList<String> smsContentParamList=new ArrayList<>(); //短信参数
            smsContentParamList.add(serviceNoticeReqDTO.getShopAddress());
            smsContentParamList.add(serviceNoticeReqDTO.getServiceName());
            smsContentParamList.add(serviceNoticeReqDTO.getServiceTel());
            new Thread(new Runnable(){
                @Override
                public void run() {
                    ResultDO remoteResult = noticePushManager.pushServiceSucNotify(userPhone,smsContentParamList);
                    Logger.info(NoticePushServiceImpl.class, "服务发布成功短信提醒返回结果:"+ JSON.toJSONString(remoteResult));
                }
            }).start();
            resultDO.setSuccess(true);
            resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
            resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
            resultDO.setData(true);
            return resultDO;
        }catch (Exception e){
            Logger.warn(NoticePushServiceImpl.class,"服务发布成功短信提醒,错误信息:"+e.getMessage());
            e.printStackTrace();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.RUNTIME_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.RUNTIME_EXCEPTION.getMsg());
            return resultDO;
        }
    }


    /**
     * 小喇叭通知推送 （不用存入本地库） 5-小喇叭  0-实堪 1-约看 2-签约 9-线索
     * @param type   0-实堪 1-约看 2-签约 9-线索
     * @param name  业务员姓名
     * @param roadName 实堪路名
     * @return
     */
    @Override
    public  ResultDO<Boolean> pushTrumpetNotice(String deviceId,Integer osType,Integer type,String name,String roadName){
        ResultDO resultDO=new ResultDO();
        if(ValidateHelper.isEmptyString(deviceId) || ValidateHelper.isEmpty(osType) || ValidateHelper.isEmpty(type)
                || ValidateHelper.isEmptyString(name)) {
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
       try {
           final HashMap<String, Object> hashMap = new HashMap();
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           hashMap.put("sendDateTime", sdf.format(new Date()));
           hashMap.put("catalogType", NoticeType.TRUMPET.getValue());
           hashMap.put("bizType", type);
           final ArrayList<String> contentParamList = new ArrayList<>();
           contentParamList.add(name);
           if (type == WorkServiceType.FIELD_TRIP.getValue()) {
               contentParamList.add(roadName);
           }
            final HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("deviceId",deviceId);
            paramMap.put("osType", osType);
           new Thread(new Runnable(){
               @Override
               public void run() {
                   ResultDO remoteResult = noticePushManager.pushTrumpetNotice(paramMap, contentParamList, hashMap);
                   Logger.info(NoticePushServiceImpl.class, "小喇叭通知推送返回结果:"+ JSON.toJSONString(remoteResult));
               }
            }).start();
            resultDO.setSuccess(true);
            resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
            resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
            resultDO.setData(true);
           return resultDO;
        }catch (Exception e){
           Logger.warn(NoticePushServiceImpl.class,"小喇叭通知推送 （不用存入本地库） 5-小喇叭  0-实堪 1-约看 2-签约 9-线索,错误信息:"+e.getMessage());
           e.printStackTrace();
           resultDO.setSuccess(false);
           resultDO.setErrCode(ErrorConstant.RUNTIME_EXCEPTION.getCode());
           resultDO.setErrMsg(ErrorConstant.RUNTIME_EXCEPTION.getMsg());
           return resultDO;
        }
    }

    /**
     * 商铺被下架通知 catalogType=3时：0-商铺下架
     * @param shopNoticeReqDTO
     * @param deviceId 设备号
     * @param osType 设备类型
     * @return
     */
    @Override
    public  ResultDO<Boolean> pushShopNotice(ShopNoticeReqDTO shopNoticeReqDTO,String deviceId,Integer osType){
        ResultDO resultDO=new ResultDO();
        if(ValidateHelper.isEmpty(shopNoticeReqDTO) || ValidateHelper.isEmptyString(deviceId) || ValidateHelper.isEmpty(osType)) {
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        try {
            shopNoticeReqDTO.setShopNoticeType(ShopServiceType.SOLD_OUT.getValue());
            //存放本地数据库
            ResultDO<Long> resultAdd= notificationService.addShopNotice(shopNoticeReqDTO);
            //获取消息通知Id
            Long msgId=(Long)resultAdd.getData();
            final HashMap<String, Object> hashMap = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            hashMap.put("sendDateTime", sdf.format(new Date()));
            hashMap.put("catalogType", NoticeType.SHOP.getValue());
            hashMap.put("bizId", shopNoticeReqDTO.getBussinessId());
            hashMap.put("messageId", msgId);
            final ArrayList<String> contentParamList = new ArrayList<>();
            contentParamList.add(shopNoticeReqDTO.getShopAddress());
            final HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("deviceId", deviceId);
            paramMap.put("osType", osType);
            paramMap.put("businessId",basicProperties.getShopBusinessId());
            hashMap.put("bizType",shopNoticeReqDTO.getShopNoticeType());
            Logger.info(NoticePushServiceImpl.class,"用户撤下日志，内容是:"+JSON.toJSONString(paramMap));
            new Thread(new Runnable(){
                @Override
                public void run() {
                    ResultDO remoteResult = noticePushManager.pushShopNotice(paramMap, contentParamList, hashMap);
                    Logger.info(NoticePushServiceImpl.class, "商铺被下架通知返回结果:"+ JSON.toJSONString(remoteResult));
                }
            }).start();
            resultDO.setSuccess(true);
            resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
            resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
            resultDO.setData(true);
            return resultDO;
        }catch (Exception e){
            Logger.warn(NoticePushServiceImpl.class,"商铺被下架通知 catalogType=3时：0-商铺下架,错误信息:"+e.getMessage());
            e.printStackTrace();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.RUNTIME_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.RUNTIME_EXCEPTION.getMsg());
            return resultDO;
        }

    }

    /**
     * @description catalogType=3时： 1-店铺转到业务员名下2-线索被发布店铺 3-店铺跟进即将超时
     * @package com.dongtong.basic.service
     * @author liaozm
     * @date 2017/8/18 13:31
     * @params
     * @return
     */
    @Override
    public ResultDO<Boolean> clerkShopNotice(ShopNoticeReqDTO reqDTO, String deviceId, Integer osType) {
        ResultDO<Boolean> resultDO=new ResultDO();
        if(ValidateHelper.isEmpty(reqDTO) || ValidateHelper.isEmptyString(deviceId) || ValidateHelper.isEmpty(osType)) {
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
//        reqDTO.setShopNoticeType(ShopServiceType.PUBLISH_SHOP.getValue());
        //消息存放本地数据库
        ResultDO<Long> resultAdd= notificationService.addShopNotice(reqDTO);
        //获取消息通知Id
        if(!resultAdd.isSuccess()){
            resultDO.setErrMsg(resultAdd.getErrMsg());
            return resultDO;
        }
        Long msgId=resultAdd.getData();
        HashMap<String, Object> hashMap = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        hashMap.put("sendDateTime", sdf.format(new Date()));
        hashMap.put("catalogType", NoticeType.SHOP.getValue());
        hashMap.put("bizId", reqDTO.getBussinessId());
        hashMap.put("bizType",reqDTO.getShopNoticeType());
        hashMap.put("messageId", msgId);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("deviceId", deviceId);
        paramMap.put("osType", osType);
        ResultDO remoteResult = noticePushManager.pushShopNotice(paramMap, null, hashMap);
        Logger.info(NoticePushServiceImpl.class, "商铺类通知返回结果:"+ JSON.toJSONString(remoteResult));
        if(!remoteResult.isSuccess()){
            resultDO.setErrMsg(remoteResult.getErrMsg());
            return resultDO;
        }
        resultDO.setSuccess(true);
        return resultDO;
    }

    /**
     * 接到商户需求通知 接到商户需求
     * @param workNoticeReqDTO
     * @param deviceId
     * @param osType
     * @return
     */
    @Override
    public  ResultDO<Boolean> pushWorkNotice(WorkNoticeReqDTO workNoticeReqDTO,String deviceId,Integer osType){
        ResultDO resultDO=new ResultDO();
        if(ValidateHelper.isEmpty(workNoticeReqDTO) || ValidateHelper.isEmptyString(deviceId) || ValidateHelper.isEmpty(osType)) {
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        try {
            workNoticeReqDTO.setWorkNoticeType(BussinessEndNoticeType.RECEIVED_DEMAND.getValue());
            //存放本地数据库
            ResultDO<Long>   resultAdd= notificationService.addWorkNotice(workNoticeReqDTO);
            //获取消息通知Id
            Long msgId = resultAdd.getData();
            final HashMap<String, Object> hashMap = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            hashMap.put("sendDateTime", sdf.format(new Date()));
            hashMap.put("catalogType", NoticeType.WORK.getValue());
            hashMap.put("bizId", workNoticeReqDTO.getBussinessId());
            hashMap.put("bizType", workNoticeReqDTO.getServiceType());
            hashMap.put("messageId",msgId);
            final ArrayList<String> contentParamList = new ArrayList<>();
            contentParamList.add(workNoticeReqDTO.getShopAddress());
            final HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("deviceId", deviceId);
            paramMap.put("osType", osType);
            new Thread(new Runnable(){
                @Override
                public void run() {
                    ResultDO remoteResult = noticePushManager.pushWorkNotice(paramMap, contentParamList, hashMap);
                    Logger.info(NoticePushServiceImpl.class, "接到商户需求通知返回结果:"+ JSON.toJSONString(remoteResult));
                }
            }).start();
            resultDO.setSuccess(true);
            resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
            resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
            resultDO.setData(true);
            return resultDO;
        }catch (Exception e){
            Logger.warn(NoticePushServiceImpl.class,"接到商户需求通知 接到商户需求,错误信息:"+e.getMessage());
            e.printStackTrace();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.RUNTIME_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.RUNTIME_EXCEPTION.getMsg());
            return resultDO;
        }

    }

    /**
     * 任务到时提醒  catalogType=4时(工作)  0：实堪 1：约看 2：签约 6-线索转到业务员名下 7-线索被废弃 8-线索跟进即将超时
     * @param workNoticeReqDTO
     * @param deviceId
     * @param osType
     * @return
     */
    @Override
    public  ResultDO<Boolean> pushArrivalTimeNotice(WorkNoticeReqDTO workNoticeReqDTO,String deviceId,Integer osType){
        ResultDO resultDO=new ResultDO();
        if(ValidateHelper.isEmpty(workNoticeReqDTO) || ValidateHelper.isEmptyString(deviceId) || ValidateHelper.isEmpty(osType)) {
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        try {
            Logger.info(NoticePushServiceImpl.class, JSON.toJSONString(workNoticeReqDTO));
            if(workNoticeReqDTO.getServiceType() == WorkServiceType.FIELD_TRIP.getValue()
                    || workNoticeReqDTO.getServiceType() == WorkServiceType.ORDER_SEE.getValue()
                    || workNoticeReqDTO.getServiceType() == WorkServiceType.SIGN_CONTRACT.getValue()){
                workNoticeReqDTO.setWorkNoticeType(BussinessEndNoticeType.TASK_REMIND.getValue());
            }
            //存放本地数据库
            ResultDO<Long> resultAdd= notificationService.addWorkNotice(workNoticeReqDTO);
            Logger.info(NoticePushServiceImpl.class,"存放本地数据库问题:"+ JSON.toJSONString(resultAdd));
            Long msgId = resultAdd.getData() ;
            final HashMap<String, Object> hashMap = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            hashMap.put("sendDateTime", sdf.format(new Date()));
            hashMap.put("catalogType", NoticeType.WORK.getValue());
            hashMap.put("bizId", workNoticeReqDTO.getBussinessId());
            hashMap.put("bizType", workNoticeReqDTO.getServiceType());
            hashMap.put("messageId", msgId);
            final ArrayList<String> contentParamList = new ArrayList<>();
            contentParamList.add(workNoticeReqDTO.getShopAddress());
            if (!ValidateHelper.isEmptyString(workNoticeReqDTO.getPlanTime())){
                contentParamList.add(DateFormatUtils.formatDate(workNoticeReqDTO.getPlanTime(),"yyyy-MM-dd HH:mm"));
            }else{
                contentParamList.add(workNoticeReqDTO.getPlanTime());
            }
            final HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("deviceId", deviceId);
            paramMap.put("osType", osType);
            new Thread(new Runnable(){
                @Override
                public void run() {
                    ResultDO remoteResult = noticePushManager.pushArrivalTimeNotice(paramMap, contentParamList, hashMap);
                    Logger.info(NoticePushServiceImpl.class, "任务到时提醒通知返回结果:"+ JSON.toJSONString(remoteResult));
                }
            }).start();
            resultDO.setSuccess(true);
            resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
            resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
            resultDO.setData(true);
            return resultDO;
        }catch (Exception e){
            Logger.warn(NoticePushServiceImpl.class,"任务到时提醒  catalogType=4时(工作)  0：实堪 1：约看 2：签约 6-线索转到业务员名下 7-线索被废弃 8-线索跟进即将超时,错误信息:"+e.getMessage(),e);
            e.printStackTrace();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.RUNTIME_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.RUNTIME_EXCEPTION.getMsg());
            return resultDO;
        }
    }

    /**
     * 周/月榜提醒  catalogType=4时 3-周榜排名 4-月榜排名 3：周榜 4：月榜
     * @param workNoticeReqDTO
     * @param deviceId
     * @param osType
     * @return
     */
    @Override
    public  ResultDO<Boolean> pushWeekMonthNotice(WorkNoticeReqDTO workNoticeReqDTO,String deviceId,Integer osType){
        ResultDO resultDO=new ResultDO();
        if(ValidateHelper.isEmpty(workNoticeReqDTO) || ValidateHelper.isEmptyString(deviceId) || ValidateHelper.isEmpty(osType)) {
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        try {
            //存放本地数据库
            ResultDO<Long> resultAdd= notificationService.addWorkNotice(workNoticeReqDTO);
            if (!resultAdd.isSuccess()){
                resultDO.setSuccess(false);
                resultDO.setErrCode(resultAdd.getErrCode());
                resultDO.setErrMsg(resultAdd.getErrMsg());
                return resultDO;
            }
            Long msgId = resultAdd.getData() ;
            final HashMap<String, Object> hashMap = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            hashMap.put("sendDateTime", sdf.format(new Date()));
            hashMap.put("catalogType", NoticeType.WORK.getValue());
            hashMap.put("bizType", workNoticeReqDTO.getServiceType());
            hashMap.put("messageId", msgId);
            final ArrayList<String> contentParamList = new ArrayList<>();
            contentParamList.add(workNoticeReqDTO.getHintName());
            contentParamList.add(workNoticeReqDTO.getCloseStoreName());
            contentParamList.add(workNoticeReqDTO.getSignName());
            contentParamList.add(workNoticeReqDTO.getLookName());
            contentParamList.add(workNoticeReqDTO.getRegisterName());
            final HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("deviceId", deviceId);
            paramMap.put("osType", osType);
            new Thread(new Runnable(){
                @Override
                public void run() {
                    ResultDO remoteResult = noticePushManager.pushWeekMonthNotice(paramMap, contentParamList, hashMap);
                    Logger.info(NoticePushServiceImpl.class, "周/月榜提醒通知返回结果:"+ JSON.toJSONString(remoteResult));
                }
            }).start();
            resultDO.setSuccess(true);
            resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
            resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
            resultDO.setData(true);
            return resultDO;
        }catch (Exception e){
            Logger.warn(NoticePushServiceImpl.class,"周/月榜提醒  catalogType=4时 3-周榜排名 4-月榜排名 3：周榜 4：月榜,错误信息:"+e.getMessage());
            e.printStackTrace();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.RUNTIME_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.RUNTIME_EXCEPTION.getMsg());
            return resultDO;
        }
    }

    /**
     * 业务端帖子评论通知 catalogType=2时：0-发帖人评论通知
     * @param bussinessNoticeReqDTO
     * @param deviceId
     * @param osType
     * @return
     */
    @Override
    public  ResultDO<Boolean> pushPostCommentBusNotify(BussinessNoticeReqDTO bussinessNoticeReqDTO,String deviceId,Integer osType){
        ResultDO resultDO=new ResultDO();
        if(ValidateHelper.isEmpty(bussinessNoticeReqDTO) || ValidateHelper.isEmptyString(deviceId) || ValidateHelper.isEmpty(osType)) {
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        try {
            bussinessNoticeReqDTO.setBussinessType(BussinessNoticeType.COMMENT_NOTICE.getValue());
            //存放本地数据库
            ResultDO<Long> resultAdd= notificationService.addBussinessNotice(bussinessNoticeReqDTO);
            Long msgId = resultAdd.getData();
            final HashMap<String, Object> hashMap = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            hashMap.put("sendDateTime", sdf.format(new Date()));
            hashMap.put("catalogType", NoticeType.BUSSINESS.getValue());
            hashMap.put("bizId", bussinessNoticeReqDTO.getBussinessId());
            hashMap.put("bizType", BussinessNoticeType.COMMENT_NOTICE.getValue());
            hashMap.put("messageId", msgId);
            final ArrayList<String> contentParamList = new ArrayList<>();
            String newTel="";
            if(!ValidateHelper.isEmptyString(bussinessNoticeReqDTO.getCommentTel())){
                String tel=bussinessNoticeReqDTO.getCommentTel();
//                newTel=tel.substring(0,3)+"****"+tel.substring(7);
                newTel = tel;
            }
            contentParamList.add(newTel);
            contentParamList.add(bussinessNoticeReqDTO.getContentOrReason());
            final HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("deviceId", deviceId);
            paramMap.put("osType", osType);
            new Thread(new Runnable(){
                @Override
                public void run() {
                    ResultDO remoteResult = noticePushManager.pushWeekMonthNotice(paramMap, contentParamList, hashMap);
                    Logger.info(NoticePushServiceImpl.class, "业务端帖子评论通知返回结果:"+ JSON.toJSONString(remoteResult));
                }
            }).start();
            ResultDO remoteResult = noticePushManager.pushPostCommentBusNotify(paramMap, contentParamList, hashMap);
            if (!remoteResult.isSuccess()) {
                resultDO.setSuccess(false);
                resultDO.setErrCode(remoteResult.getErrCode());
                resultDO.setErrMsg(remoteResult.getErrMsg());
                return resultDO;
            }
            resultDO.setSuccess(true);
            resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
            resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
            resultDO.setData(true);
            return resultDO;
        }catch (Exception e){
            Logger.warn(NoticePushServiceImpl.class,"业务端帖子评论通知 catalogType=2时：0-发帖人评论通知,错误信息:"+e.getMessage());
            e.printStackTrace();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.RUNTIME_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.RUNTIME_EXCEPTION.getMsg());
            return resultDO;
        }

    }

    /**
     * @description 服务日程新增提醒 服务 0-预约看铺 1-旺铺寻租 2-签约租铺 3-日程提醒 4-服务完成 5-租客看铺 6-租客签约
     * @package com.dongtong.basic.service
     * @author liaozm
     * @date 2017/8/11 13:55
     * @params
     * @return
     */
    @Override
    public  ResultDO<Boolean> pushScheduleAddNotify(ServiceNoticeReqDTO serviceNoticeReqDTO,String userPhone,
                                                       String deviceId,Integer osType){
        ResultDO resultDO=new ResultDO();
        if(ValidateHelper.isEmpty(serviceNoticeReqDTO) || ValidateHelper.isEmptyString(deviceId) || ValidateHelper.isEmpty(osType)) {
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        try {
            Logger.info(NoticePushServiceImpl.class, JSON.toJSONString(serviceNoticeReqDTO));
//            serviceNoticeReqDTO.setServiceStatus(ServiceStatus.TIME_VARIATION.getValue());
            //存放本地数据库
            ResultDO<Long> resultAdd= notificationService.addServiceNotice(serviceNoticeReqDTO);
            Logger.info(NoticePushServiceImpl.class,"调用服务日程新增提醒方法，结果返回值是:"+JSON.toJSONString(resultAdd));
            //获取消息通知Id
            Long msgId = resultAdd.getData();
            final HashMap<String, Object> hashMap = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            hashMap.put("sendDateTime", sdf.format(new Date()));
            hashMap.put("catalogType", NoticeType.SERVICE.getValue());
            hashMap.put("bizType", serviceNoticeReqDTO.getServiceType());
            hashMap.put("bizId", serviceNoticeReqDTO.getBussinessId());
            hashMap.put("messageId", msgId);
            final ArrayList<String> contentParamList = new ArrayList<>();
            final ArrayList<String> smsContentParamList=new ArrayList<>(); //短信参数
            smsContentParamList.add(serviceNoticeReqDTO.getCurrentTime());
            smsContentParamList.add(serviceNoticeReqDTO.getShopAddress());
            smsContentParamList.add(serviceNoticeReqDTO.getServiceName());
            smsContentParamList.add(serviceNoticeReqDTO.getServiceTel());
            final HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("deviceId", deviceId);
            paramMap.put("osType", osType);
            paramMap.put("userPhone", userPhone);
            new Thread(new Runnable(){
                @Override
                public void run() {
                    ResultDO remoteResult = noticePushManager.pushScheduleAddNotify(paramMap, contentParamList,smsContentParamList,hashMap);
                    Logger.info(NoticePushServiceImpl.class, "服务日程新增提醒通知返回结果:"+ JSON.toJSONString(remoteResult));
                }
            }).start();
            resultDO.setSuccess(true);
            resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
            resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
            resultDO.setData(true);
            return resultDO;
        }catch (Exception e){
            Logger.warn(NoticePushServiceImpl.class,"服务日程新增提醒 服务 0-预约看铺 1-旺铺寻租 2-签约租铺 3-日程提醒 4-服务完成 5-租客看铺 6-租客签约,错误信息:"+e.getMessage());
            e.printStackTrace();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.RUNTIME_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.RUNTIME_EXCEPTION.getMsg());
            return resultDO;
        }
    }


    /**
     * 服务类型
     * @return
     */
    public HashMap getHashMap(){
        HashMap<Integer,String> hashMap=new HashMap<>();
        hashMap.put(ServiceNoticeType.APPOINT_MENTSHOP.getValue(),"预约看铺");
        hashMap.put(ServiceNoticeType.SHOP_RENT_SEEKING.getValue(),"旺铺寻租");
        hashMap.put(ServiceNoticeType.CONTRACT_LEASING.getValue(),"签约租铺");
        return hashMap;
    }
    /**
     * 工作服务类型
     * @return
     */
    public HashMap getWorkHashMap(){
        HashMap<Integer,String> hashMap=new HashMap<>();
        hashMap.put(WorkServiceType.FIELD_TRIP.getValue(),"实堪");
        hashMap.put(WorkServiceType.ORDER_SEE.getValue(),"约看");
        hashMap.put(WorkServiceType.SIGN_CONTRACT.getValue(),"签约");
        return hashMap;
    }
    public ResultDO check(String deviceId,Integer osType){
        ResultDO resultDO=new ResultDO();
        if(ValidateHelper.isEmptyString(deviceId) || osType==null) {
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        resultDO.setSuccess(true);
        return resultDO;
    }


}
