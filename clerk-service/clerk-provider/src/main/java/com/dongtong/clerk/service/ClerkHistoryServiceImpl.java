package com.dongtong.clerk.service;

import com.dongtong.clerk.domain.ClerkHistoryMonthRanking;
import com.dongtong.clerk.domain.ClerkHistoryWeekRanking;
import com.dongtong.clerk.manager.ClerkHistoryManager;
import com.shfc.common.base.Logger;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package com.dongtong.clerk.service.ClerkHistoryServiceImpl
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2017
 * Author wliao
 * @date 2017/5/27 14:02
 * version V1.0.0
 */
@Service
public class ClerkHistoryServiceImpl implements ClerkHistoryService {

    @Autowired
    private ClerkHistoryManager clerkHistoryManager;


    @Override
    public ResultDO<Boolean> addClerkHistoryMonth(ClerkHistoryMonthRanking clerkHistoryMonthRanking) {
        ResultDO<Boolean> resultDO = new ResultDO<Boolean>();
        try {
            Boolean isok = clerkHistoryManager.addClerkHistoryMonthRanking(clerkHistoryMonthRanking);
            resultDO.setData(isok);
        }catch (Exception e){
            Logger.error(e, "商铺代办任务列表查询异常",e);
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        resultDO.setSuccess(true);
        return resultDO;
    }

    @Override
    public ResultDO<Boolean> addClerkHistoryWeek(ClerkHistoryWeekRanking clerkHistoryWeekRanking) {
        ResultDO<Boolean> resultDO = new ResultDO<Boolean>();
        try {
            Boolean isok = clerkHistoryManager.addClerkHistoryWeekRanking(clerkHistoryWeekRanking);
            resultDO.setData(isok);
        }catch (Exception e){
            Logger.error(e, "商铺代办任务列表查询异常",e);
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        resultDO.setSuccess(true);
        return resultDO;
    }
}
