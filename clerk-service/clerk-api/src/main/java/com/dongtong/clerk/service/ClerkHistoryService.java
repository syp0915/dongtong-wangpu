package com.dongtong.clerk.service;

import com.dongtong.clerk.domain.ClerkHistoryMonthRanking;
import com.dongtong.clerk.domain.ClerkHistoryWeekRanking;
import com.shfc.common.result.ResultDO;

/**
 * @Package com.dongtong.clerk.service.ClerkHistoryService
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2017
 * Author wliao
 * @date 2017/5/27 14:01
 * version V1.0.0
 */
public interface ClerkHistoryService {

    /**
     * 增加历史数据月份
     * @param clerkHistoryMonthRanking
     * @return
     */
    ResultDO<Boolean> addClerkHistoryMonth(ClerkHistoryMonthRanking clerkHistoryMonthRanking);

    /**
     * 增加历史排名数据周
     * @param clerkHistoryWeekRanking
     * @return
     */
    ResultDO<Boolean> addClerkHistoryWeek(ClerkHistoryWeekRanking clerkHistoryWeekRanking);
}
