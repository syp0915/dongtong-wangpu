package com.dongtong.scheduled.notice;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dongtong.basic.dto.req.ServiceNoticeReqDTO;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.enums.ServiceStatus;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.customer.domain.Customer;
import com.dongtong.customer.service.CustomerService;
import com.dongtong.customer.service.ScheduleService;
import com.shfc.common.result.ResultDO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @author sunyaping
 * @Package com.dongtong.scheduledconsumer
 * @Description ：日程推送
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-17 11:04
 * version V1.0.0
 **/
@Component
public class PushSchedule extends BaseCheck implements SimpleJob {

    private static Logger logger = Logger.getLogger(PushSchedule.class);

    @Autowired
    private NoticePushService noticePushService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private CustomerService customerService;



    /**
     * 每天9点推送当天有日程的用户通知
     */
    public void pushSchedule(){
        ResultDO<List<HashMap<String,Object>>> resultDO=scheduleService.getNewestSchedule();
        ServiceNoticeReqDTO serviceNoticeReqDTO=new ServiceNoticeReqDTO();
        List<HashMap<String,Object>> data=resultDO.getData();
        if(data.size()>0){
            for(int i=0;i<data.size();i++){
                HashMap<String,Object> hashMap=data.get(i);
                Long bizId=Long.parseLong(checkStringMapObject(hashMap.get("biz_id")));
                Integer type=Integer.parseInt(checkIntMapObject(hashMap.get("type")));
                String meetTime=checkStringMapObject(hashMap.get("meet_time"));
                Long customerId=Long.parseLong(checkStringMapObject(hashMap.get("customer_id")));
                String province=checkStringMapObject(hashMap.get("province"));
                String city=checkStringMapObject(hashMap.get("city"));
                String district=checkStringMapObject(hashMap.get("district"));
                String address=checkStringMapObject(hashMap.get("address"));
                Integer scheduleCount=Integer.parseInt(checkIntMapObject(hashMap.get("scheduleCount")));
                serviceNoticeReqDTO.setScheduleCount(scheduleCount);
                serviceNoticeReqDTO.setBussinessId(bizId);
                serviceNoticeReqDTO.setServiceType(type);
                serviceNoticeReqDTO.setServiceStatus(ServiceStatus.SCHEDULE_REMIND.getValue());
                serviceNoticeReqDTO.setCurrentTime(meetTime);
                serviceNoticeReqDTO.setReceiveId(customerId);
                serviceNoticeReqDTO.setReceiveType(ReceiveType.CUSTOMER.getValue());
                serviceNoticeReqDTO.setShopAddress(province+city+district+address);
                ResultDO<Customer> customerResultDO=customerService.findCustomerInfoByCustomerId(customerId);
                Customer customer=customerResultDO.getData();
                ResultDO<Boolean> result=noticePushService.pushScheduleNotify(serviceNoticeReqDTO,customer.getDeviceId(),customer.getOsType());
                logger.info("日程提醒result---------->" + JSON.toJSONString(result)+"bizId:"+bizId);
            }
        }
    }


    @Override
    public void execute(ShardingContext shardingContext) {
        try {
            pushSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
