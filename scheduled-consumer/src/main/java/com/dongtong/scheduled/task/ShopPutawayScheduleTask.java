package com.dongtong.scheduled.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dongtong.customer.service.ScheduleService;
import com.dongtong.shop.service.ShopService;
import com.shfc.common.date.DateUtils;
import com.shfc.common.result.ResultDO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopPutawayScheduleTask implements SimpleJob {

    @Autowired
    private ShopService shopService;
    private static Logger logger = Logger.getLogger(ShopPutawayScheduleTask.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        try {
            shopService.calculateShopScore();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("过期用户日程定时任务--------------->失败，失败原因：" + e.getMessage());
        }

    }
}
