package com.dongtong.clerk.service;

import com.dongtong.clerk.domain.HintOverTime;
import com.shfc.common.result.ResultDO;

import java.util.List;

/**
 * @Package com.dongtong.clerk.service.HintOverTimeService
 * @Description: 线索超时
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhoumin
 * @date 2017/8/12 10:08
 * version V1.0.0
 */
public interface HintOverTimeService {
    /**
     * 添加线索超时跟进
     * @Author zhoumin
     * @return
     */
    ResultDO addHintOverTime (Long hintId,String date);

    /**
     *更新已经超时的线索
     * @Author zhoumin
     * @return
     */
    void overTimeUpdate();

    /**
     * 线索跟进即将超时
     * @return
     */
    ResultDO<List<HintOverTime>> queryPushHintOverTime();

    /**
     * 更新push字段
     * @param hintOverTime
     * @Author zhoumin
     * @return
     */
    ResultDO<Boolean> updateIsPush(HintOverTime hintOverTime);
}
