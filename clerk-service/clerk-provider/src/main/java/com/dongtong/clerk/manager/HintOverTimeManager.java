package com.dongtong.clerk.manager;

import com.dongtong.clerk.dao.ClerkHintMapper;
import com.dongtong.clerk.dao.HintOverTimeMapper;
import com.dongtong.clerk.domain.ClerkHint;
import com.dongtong.clerk.domain.HintOverTime;
import com.dongtong.clerk.enums.ClerkHintStatus;
import com.dongtong.clerk.enums.OverTimeHintStatus;
import com.dongtong.clerk.enums.OverTimeStatus;
import com.shfc.common.base.Logger;
import com.shfc.common.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Package com.dongtong.clerk.manager.HintOverTimeManager
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhoumin
 * @Date:Created in 11:09 2017/8/12.
 * version V1.0.0
 */
@Service
public class HintOverTimeManager {

    @Autowired
    private HintOverTimeMapper hintOverTimeMapper;

    @Autowired
    private ClerkHintManager clerkHintManager;

    @Autowired
    private ClerkHintMapper clerkHintMapper;

    public Long addHintOverTime(HintOverTime hintOverTime){
        hintOverTimeMapper.insert(hintOverTime);
        return hintOverTime.getId();
    }

    @Async
    public void overTimeUpdate(){
        List<HintOverTime> list = hintOverTimeMapper.queryHintOverTime();
        if(list != null && list.size() > 0){
            for(HintOverTime hintOverTime: list){
                asyncOverTimeUpdate(hintOverTime.getHintId(), hintOverTime);
            }
        }
    }

    private void asyncOverTimeUpdate(Long hintId,HintOverTime hintOverTime){
        try {
            ClerkHint clerkHint = clerkHintManager.getClerkHintById(hintId);
            int left= hintOverTime.getOverTime().compareTo(DateUtils.getCurrentDate());
            if (null != clerkHint){
                if (hintOverTime.getHintStatus() !=null && OverTimeHintStatus.EXPAND_CLERK_SURE.getValue()==hintOverTime.getHintStatus()){
                    if (ClerkHintStatus.EXPAND_CLERK_SURE.getValue() != clerkHint.getStatus()
                            || (clerkHint.getExpandClerkId()!=null && clerkHint.getExpandClerkId().longValue() != hintOverTime.getClerkId().longValue())){//更新
                        hintOverTime.setStatus(OverTimeStatus.PROCESS.getValue());
                        hintOverTimeMapper.updateByPrimaryKeySelective(hintOverTime);
                    }else if (left <= 0){//超时更新
                        hintOverTime.setExpiredClerkId(clerkHint.getExpandClerkId());
                        clerkHint.setStatus(ClerkHintStatus.EXPAND_CLERK_SHELTERS.getValue());
                        clerkHint.setClaimTime(null);
                        clerkHint.setOwnerId(null);
                        clerkHint.setExpandClerkId(null);
                        clerkHint.setExpandClerkName(null);

                        hintOverTime.setStatus(OverTimeStatus.OVER_TIME.getValue());
                        modifyHintAndOverTime(clerkHint,hintOverTime);
                    }

                } else if (hintOverTime.getHintStatus() !=null && OverTimeHintStatus.AWAIT_EXAMINE.getValue()== hintOverTime.getHintStatus()){//更新
                    if (ClerkHintStatus.AWAIT_EXAMINE.getValue() != clerkHint.getStatus()
                            || (clerkHint.getExpandClerkId()!=null && clerkHint.getTradeClerkId().longValue() != hintOverTime.getClerkId().longValue())){//更新
                        hintOverTime.setStatus(OverTimeStatus.PROCESS.getValue());
                        hintOverTimeMapper.updateByPrimaryKeySelective(hintOverTime);
                    }else if (left <= 0){//超时更新
                        hintOverTime.setExpiredClerkId(clerkHint.getTradeClerkId());
                        clerkHint.setStatus(ClerkHintStatus.TRADE_CLERK_SHELTERS.getValue());
                        clerkHint.setClaimTime(null);
                        clerkHint.setOwnerId(null);
                        clerkHint.setTradeClerkId(null);
                        clerkHint.setTradeClerkName(null);

                        hintOverTime.setStatus(OverTimeStatus.OVER_TIME.getValue());
                        modifyHintAndOverTime(clerkHint,hintOverTime);
                    }
                }
            }
        }catch (Exception e){
            Logger.error(HintOverTimeManager.class, "线索超时更新异常" + e.getMessage(), e);
        }
    }

    @Transactional
    public void modifyHintAndOverTime(ClerkHint clerkHint, HintOverTime hintOverTime){
        hintOverTimeMapper.updateByPrimaryKeySelective(hintOverTime);
        clerkHintMapper.updateByPrimaryKey(clerkHint);
    }

    public List<HintOverTime> queryPushHintOverTime(){
        return hintOverTimeMapper.queryPushHintOverTime();
    }

    /**
     * 更新超时表
     * @param hintOverTime
     * @return
     */
    public Boolean updateIsPush(HintOverTime hintOverTime){
        int count = hintOverTimeMapper.updateByPrimaryKeySelective(hintOverTime);
        return count==1;
    }
}
