package com.dongtong.scheduled.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dongtong.customer.service.ScheduleService;
import com.shfc.common.result.ResultDO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/31 上午10:19.
 */
@Service
public class CustomerScheduleTask implements SimpleJob {

    @Autowired(required = false)
    private ScheduleService scheduleService;

    protected static Logger logger = Logger.getLogger(CustomerScheduleTask.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        try {
            this.expireCustomerSchedule();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("过期用户日程定时任务--------------->失败，失败原因：" + e.getMessage());
        }

    }

    public void expireCustomerSchedule() {
        logger.info("过期用户日程定时任务--------------->开始");
        ResultDO<Integer> resultDO = scheduleService.expireCustomerSchedule();
        if (!resultDO.isSuccess()){
            logger.error("过期用户日程定时任务--------------->失败，失败原因code:：" + resultDO.getErrCode() + " msg:" + resultDO.getErrMsg());
            return;
        }
        logger.info("过期用户日程定时任务--------------->结束，更新数据：" + resultDO.getData() + "条");
    }
}
