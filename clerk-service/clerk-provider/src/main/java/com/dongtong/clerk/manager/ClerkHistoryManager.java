package com.dongtong.clerk.manager;

import com.dongtong.clerk.dao.ClerkHistoryMonthRankingMapper;
import com.dongtong.clerk.dao.ClerkHistoryWeekRankingMapper;
import com.dongtong.clerk.domain.ClerkHistoryMonthRanking;
import com.dongtong.clerk.domain.ClerkHistoryWeekRanking;
import com.shfc.common.base.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package com.dongtong.clerk.manager.ClerkHistoryManager
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2017
 * Author wliao
 * @date 2017/5/27 14:03
 * version V1.0.0
 */
@Service
public class ClerkHistoryManager {

    @Autowired
    private ClerkHistoryMonthRankingMapper clerkHistoryMonthRankingMapper;

    @Autowired
    private ClerkHistoryWeekRankingMapper clerkHistoryWeekRankingMapper;

    public Boolean addClerkHistoryMonthRanking(ClerkHistoryMonthRanking clerkHistoryMonthRanking){
        try {
            clerkHistoryMonthRankingMapper.insert(clerkHistoryMonthRanking);
        }catch (Exception e){
            Logger.error(e, "插入月度历史排名错误!:"+e.getMessage());
            return false;
        }
        return true;
    }

    public Boolean addClerkHistoryWeekRanking(ClerkHistoryWeekRanking clerkHistoryWeekRanking){
        try {
            clerkHistoryWeekRankingMapper.insert(clerkHistoryWeekRanking);
        }catch (Exception e){
            Logger.error(e, "插入月度历史排名错误!:"+e.getMessage());
            return false;
        }
        return true;
    }
}
