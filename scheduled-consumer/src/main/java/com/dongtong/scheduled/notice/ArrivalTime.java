package com.dongtong.scheduled.notice;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dongtong.basic.dto.req.WorkNoticeReqDTO;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.service.ClerkService;
import com.dongtong.customer.domain.CustomerSchedule;
import com.dongtong.customer.service.ScheduleService;
import com.shfc.common.result.ResultDO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author sunyaping
 * @Package com.dongtong.scheduled.notice
 * @Description 任务到时提醒
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-25 17:09
 * version V1.0.0
 **/
@Component
public class ArrivalTime implements SimpleJob {

    @Autowired
    private NoticePushService noticePushService;

    @Autowired
    private ClerkService clerkService;

    @Autowired
    private ScheduleService scheduleService;

    private static Logger logger = Logger.getLogger(ArrivalTime.class);



    /**
     * 任务到时提醒
     */
    public void scheduleTime(){
        ResultDO<List<CustomerSchedule>> customerScheduleResultDO=scheduleService.getArrivalTimeSchedule();
        List<CustomerSchedule> customerSchedules=customerScheduleResultDO.getData();
        if(customerSchedules!=null && customerSchedules.size()>0) {
            for (CustomerSchedule customerSchedule : customerSchedules) {
                if(customerSchedule.getClerkId()!=null){
                    WorkNoticeReqDTO workNoticeReqDTO = new WorkNoticeReqDTO();
                    if(customerSchedule.getType()==3 || customerSchedule.getType()==4){//3-预约看铺(租客) 4-租客看铺(房东)
                        workNoticeReqDTO.setServiceType(1); //1:约看 2：签约
                    }else if(customerSchedule.getType()==5 || customerSchedule.getType()==6){//5-签约租铺(租客) 6-租客签约(房东)
                        workNoticeReqDTO.setServiceType(2); //1:约看 2：签约
                    }
                    workNoticeReqDTO.setShopAddress(customerSchedule.getAddress());
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    workNoticeReqDTO.setPlanTime(sdf.format(customerSchedule.getMeetTime()));
                    workNoticeReqDTO.setBussinessId(customerSchedule.getBizId());
                    workNoticeReqDTO.setReceiveType(ReceiveType.CLERK.getValue());
                    workNoticeReqDTO.setReceiveId(customerSchedule.getClerkId());
                    ResultDO<Clerk> clerkResultDO = clerkService.getClerkInfoById(customerSchedule.getClerkId());
                    Clerk clerk = clerkResultDO.getData();
                    ResultDO<Boolean> rest = noticePushService.pushArrivalTimeNotice(workNoticeReqDTO, clerk.getDeviceId(), clerk.getOsType());
                    logger.info("任务到时提醒result---------->" + JSON.toJSONString(rest)+"type:"+customerSchedule.getType()+"bizId:"+customerSchedule.getBizId());
                }
            }
        }
    }

    @Override
    public void execute(ShardingContext shardingContext) {
        try {
            scheduleTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
