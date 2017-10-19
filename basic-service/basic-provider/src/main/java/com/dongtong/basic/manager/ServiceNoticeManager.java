package com.dongtong.basic.manager;

import com.dongtong.basic.dao.BaseNotificationMapper;
import com.dongtong.basic.domain.BaseNotification;
import com.dongtong.basic.dto.req.ServiceNoticeReqDTO;
import com.dongtong.basic.enums.NoticeType;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.enums.ServiceNoticeType;
import com.dongtong.basic.enums.ServiceStatus;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.manager
 * @Description :服务通知管理
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-09 13:27
 * version V1.0.0
 **/
@Service
public class ServiceNoticeManager {

    @Autowired
    private BaseNotificationMapper baseNotificationMapper;

    /**
     * 服务通知插入消息通知表
     * @param serviceNoticeDTO
     * @return
     */
    public ResultDO<Long> addServiceNotice(ServiceNoticeReqDTO serviceNoticeDTO){
        ResultDO resultDO = new ResultDO();
        Integer serviceType=serviceNoticeDTO.getServiceType();
        // serviceStatus : 0-时间变动 1-服务撤销 2-服务完成
        Integer serviceStatus=serviceNoticeDTO.getServiceStatus();
        BaseNotification baseNotification=new BaseNotification();
        baseNotification.setBizId(serviceNoticeDTO.getBussinessId());      //所对应业务Id
        baseNotification.setNotifyType(NoticeType.SERVICE.getValue());   //通知类型：服务
        baseNotification.setReceiverId(serviceNoticeDTO.getReceiveId());  //接收人Id
        baseNotification.setReceiverType(ReceiveType.CUSTOMER.getValue()); //服务为用户端通知即接收类型为0：客户
        baseNotification.setServiceType(serviceNoticeDTO.getServiceType()); //服务通知类型  0-预约看铺 1-旺铺寻租 2-签约租铺
        baseNotification.setStatus(1); // 消息状态 默认未查看
        //生成消息通知简要
        String digest=appendDigest(serviceStatus,serviceType);
        baseNotification.setDigest(digest);
        //生成消息通知内容
        String createContent=createContent(serviceNoticeDTO);
        baseNotification.setContent(createContent);
        baseNotification.setCreateTime(new Date());  //消息通知时间
        int result= baseNotificationMapper.insert(baseNotification);
        if(result>0){
            resultDO.setSuccess(true);
            resultDO.setData(baseNotification.getId());
        }else{
            resultDO.setErrMsg("添加记录失败");
        }
        return  resultDO;
    }

    /**
     * 生成通知简要
     * @return
     */
    public String appendDigest(Integer serviceStatus,Integer serviceType){
        StringBuffer digest=new StringBuffer();
        if(serviceStatus== ServiceStatus.TIME_VARIATION.getValue()){
            digest.append("小二重新安排了您");
            if(serviceType== ServiceNoticeType.APPOINT_MENTSHOP.getValue()){
                digest.append("预约看铺");
            }
            if(serviceType== ServiceNoticeType.SHOP_RENT_SEEKING.getValue()){
                digest.append("转铺");
            }
            if(serviceType== ServiceNoticeType.CONTRACT_LEASING.getValue()){
                digest.append("签约租铺");
            }
            if(serviceType== ServiceNoticeType.RENTER_MENTSHOP.getValue()){
                digest.append("租客看铺");
            }
            if(serviceType== ServiceNoticeType.RENTER_SIGN.getValue()){
                digest.append("租客签约");
            }
            digest.append("的日程");
        }
        if(serviceStatus== ServiceStatus.SERVICE_REVOCATION.getValue()){
            if(serviceType== ServiceNoticeType.APPOINT_MENTSHOP.getValue()){
                digest.append("小二撤销了您预约看铺的日程");
            }
            if(serviceType== ServiceNoticeType.SHOP_RENT_SEEKING.getValue()){
                digest.append("您的旺铺寻租服务已撤销");
            }
            if(serviceType== ServiceNoticeType.CONTRACT_LEASING.getValue()){
                digest.append("小二撤销了您签约租铺的日程");
            }
        }
        if(serviceStatus== ServiceStatus.SERVICE_COMPLETION.getValue()){
            if(serviceType== ServiceNoticeType.SHOP_RENT_SEEKING.getValue()){
                digest.append("您的旺铺已发布");
            }
        }
        if(serviceStatus==ServiceStatus.SCHEDULE_REMIND.getValue()){
            digest.append("您今天有日程安排，请注意安排时间");
        }
        if(serviceStatus== ServiceStatus.SCHEDULE_CREAT.getValue()){
            digest.append("小二安排了您");
            if(serviceType== ServiceNoticeType.APPOINT_MENTSHOP.getValue()){
                digest.append("预约看铺");
            }
            if(serviceType== ServiceNoticeType.SHOP_RENT_SEEKING.getValue()){
                digest.append("转铺");
            }
            if(serviceType== ServiceNoticeType.CONTRACT_LEASING.getValue()){
                digest.append("签约租铺");
            }
            if(serviceType== ServiceNoticeType.RENTER_MENTSHOP.getValue()){
                digest.append("租客看铺");
            }
            if(serviceType== ServiceNoticeType.RENTER_SIGN.getValue()){
                digest.append("租客签约");
            }
            digest.append("的日程");
        }
        return digest.toString();
    }

    /**
     * 生成通知类型（json字符串）
     * @return
     */
    public String createContent(ServiceNoticeReqDTO serviceNoticeDTO){
        StringBuilder json = new StringBuilder();
        // serviceStatus : 0-时间变动 1-服务撤销 2-服务完成
        Integer serviceStatus=serviceNoticeDTO.getServiceStatus();
        json.append("{");
        json.append("\"serviceStatus\":" + "\"" + serviceNoticeDTO.getServiceStatus() + "\",");
        json.append("\"shopAddress\":" + "\"" + serviceNoticeDTO.getShopAddress() + "\",");
        if(serviceStatus==ServiceStatus.TIME_VARIATION.getValue()){
            json.append("\"oldTime\":" + "\"" + serviceNoticeDTO.getOldTime() + "\",");
        }
        if(serviceStatus!=ServiceStatus.SCHEDULE_REMIND.getValue()){
            json.append("\"serviceName\":" + "\"" + serviceNoticeDTO.getServiceName() + "\",");
            json.append("\"serviceTel\":" + "\"" + serviceNoticeDTO.getServiceTel() + "\",");
        }else{
            json.append("\"scheduleCount\":" + "\"" + serviceNoticeDTO.getScheduleCount() + "\",");
        }
        if(serviceStatus==ServiceStatus.SERVICE_REVOCATION.getValue()) {
            json.append("\"reason\":" + "\"" + serviceNoticeDTO.getReason() + "\",");
        }
        json.append("\"currentTime\":" + "\"" + serviceNoticeDTO.getCurrentTime() + "\"");
        json.append("}");
        return json.toString();
    }
}
