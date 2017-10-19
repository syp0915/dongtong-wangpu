package com.dongtong.scheduled.notice;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dongtong.basic.dto.req.WorkNoticeReqDTO;
import com.dongtong.basic.enums.WorkServiceType;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.domain.ClerkHint;
import com.dongtong.clerk.domain.HintOverTime;
import com.dongtong.clerk.service.ClerkHintService;
import com.dongtong.clerk.service.ClerkService;
import com.dongtong.clerk.service.HintOverTimeService;
import com.shfc.common.result.ResultDO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhoumin
 * @Package com.dongtong.scheduled.notice
 * @Description 线索超时提醒推送
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-08-21 17:09
 * version V1.0.0
 **/
@Component
public class PushHint implements SimpleJob {

    @Autowired
    private NoticePushService noticePushService;

    @Autowired
    private ClerkService clerkService;

    @Autowired
    private HintOverTimeService hintOverTimeService;

    @Autowired
    private ClerkHintService clerkHintService;

    private static Logger logger = Logger.getLogger(PushHint.class);



    /**
     * 线索即将超时提醒
     */
    public void hintOverTime(){
        ResultDO<List<HintOverTime>> resultDO = hintOverTimeService.queryPushHintOverTime();
        List<HintOverTime> hintOverTimeList = resultDO.getData();
        if (hintOverTimeList!=null && hintOverTimeList.size()>0){
            for (HintOverTime hintOverTime : hintOverTimeList) {
                WorkNoticeReqDTO workNoticeReqDTO = new WorkNoticeReqDTO();
                workNoticeReqDTO.setServiceType(WorkServiceType.TIMEOUT_CLUE.getValue());

                //查找业务信息
                ResultDO<ClerkHint> clerkHintResultDO = clerkHintService.getClerkHintInfoById(hintOverTime.getHintId());
                if (clerkHintResultDO.isSuccess() && clerkHintResultDO.getData()!=null){
                    ResultDO<Clerk> clerkResultDO = clerkService.getClerkInfoById(clerkHintResultDO.getData().getOwnerId());
                    Clerk clerk = clerkResultDO.getData();
                    workNoticeReqDTO.setReceiveId(clerk.getId());//设置接收人ID
                    workNoticeReqDTO.setBussinessId(hintOverTime.getHintId());
                    ResultDO<Boolean> rest = noticePushService.pushArrivalTimeNotice(workNoticeReqDTO, clerk.getDeviceId(), clerk.getOsType());
                    if (rest.isSuccess()){//更新超时表push字段
                         hintOverTimeService.updateIsPush(hintOverTime);
                    }
                    logger.info("线索即将超时提醒result---------->" + JSON.toJSONString(rest)+"hintId:"+hintOverTime.getHintId());
                }

            }
        }
    }

    @Override
    public void execute(ShardingContext shardingContext) {
        try {
            hintOverTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
