package com.dongtong.scheduled.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dongtong.clerk.service.HintOverTimeService;
import com.shfc.common.result.ResultDO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 * @author zhoumin
 * @version V1.0
 * @date 2017/8/12 下午4:19.
 */
@Service
public class HintOverTimeTask implements SimpleJob {

    @Autowired(required = false)
    private HintOverTimeService hintOverTimeService;

    protected static Logger logger = Logger.getLogger(HintOverTimeTask.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        try {
            this.overTimeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("超时线索定时任务--------------->失败，失败原因：" + e.getMessage());
        }

    }

    public void overTimeUpdate() {
        logger.info("超时线索定时任务--------------->开始");
        try {
            hintOverTimeService.overTimeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("超时线索定时任务--------------->失败，失败原因：" + e.getMessage());
        }
    }
}
