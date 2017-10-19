package com.dongtong.clerk.service;

import com.dongtong.clerk.domain.ClerkHint;
import com.dongtong.clerk.domain.HintOverTime;
import com.dongtong.clerk.enums.ClerkHintStatus;
import com.dongtong.clerk.enums.OverTimeHintStatus;
import com.dongtong.clerk.enums.OverTimeStatus;
import com.dongtong.clerk.enums.YesNo;
import com.dongtong.clerk.manager.ClerkHintManager;
import com.dongtong.clerk.manager.HintOverTimeManager;
import com.shfc.common.base.Logger;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @Author:zhoumin
 * @Description:
 * @Date:Created in 10:53 2017/8/12.
 */
@Service
public class HintOverTimeServiceImpl implements HintOverTimeService {
    @Autowired(required=false)
    private ClerkHintManager clerkHintManager;
    @Autowired(required=false)
    private HintOverTimeManager hintOverTimeManager;

    @Value("${over_time_remaind}")
    private Integer overTimeRemaind;

    @Value("${over_time}")
    private Integer overTime;

    @Override
    public ResultDO<Long> addHintOverTime(Long hintId,String date) {
        ResultDO<Long> resultDO = new ResultDO();
        if (null == hintId){
            resultDO.setErrMsg("线索id不能为空");
            return resultDO;
        }
        try {
            //根据线索id查询该线索信息
            ClerkHint clerkHint = clerkHintManager.getClerkHintById(hintId);
            if (null == clerkHint){
                resultDO.setErrMsg("该线索不存在");
                return resultDO;
            }
            //带核准或待确认状态的线索
            if (clerkHint.getStatus() != ClerkHintStatus.EXPAND_CLERK_SURE.getValue()
                    && clerkHint.getStatus() != ClerkHintStatus.AWAIT_EXAMINE.getValue()){
                resultDO.setErrMsg("该线索已经完成确认或实勘");
                return resultDO;
            }

            HintOverTime hintOverTime = new HintOverTime();//线索超时表
            hintOverTime.setHintId(hintId);
            hintOverTime.setStatus(OverTimeStatus.NO_OVER_TIME.getValue());//未超时状态

            //计算超时时间和提醒超时时间
            Calendar calendarOverTime = new GregorianCalendar();
            Calendar calendarOverTimeRemind = new GregorianCalendar();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date oldDate = df.parse(date);
            calendarOverTime.setTime(oldDate);
            calendarOverTimeRemind.setTime(oldDate);
            calendarOverTime.set(Calendar.HOUR_OF_DAY, calendarOverTime.get(Calendar.HOUR_OF_DAY) + overTime);
            hintOverTime.setOverTime(df.parse(df.format(calendarOverTime.getTime())));

            calendarOverTimeRemind.set(Calendar.HOUR_OF_DAY, calendarOverTimeRemind.get(Calendar.HOUR_OF_DAY) + overTimeRemaind);
            hintOverTime.setOverTimeRemind(df.parse(df.format(calendarOverTimeRemind.getTime())));
            if (clerkHint.getStatus() == ClerkHintStatus.EXPAND_CLERK_SURE.getValue()){
                hintOverTime.setClerkId(clerkHint.getExpandClerkId());
            }
            if (clerkHint.getStatus() == ClerkHintStatus.AWAIT_EXAMINE.getValue()){
                hintOverTime.setClerkId(clerkHint.getTradeClerkId());
            }
            if (clerkHint.getStatus() == ClerkHintStatus.EXPAND_CLERK_SURE.getValue()){
                hintOverTime.setHintStatus(OverTimeHintStatus.EXPAND_CLERK_SURE.getValue());
            }
            if (clerkHint.getStatus() == ClerkHintStatus.AWAIT_EXAMINE.getValue()){
                hintOverTime.setHintStatus(OverTimeHintStatus.AWAIT_EXAMINE.getValue());
            }
            hintOverTime.setIsPush(YesNo.NO.getValue());
            hintOverTime.setCreater(clerkHint.getOwnerId());

            resultDO.setData(hintOverTimeManager.addHintOverTime(hintOverTime));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "保存到线索超时表中失败",e);
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    @Override
    public void overTimeUpdate() {
        try {
            hintOverTimeManager.overTimeUpdate();
        }catch (Exception e){
            Logger.error(HintOverTimeServiceImpl.class, "超时批量更新失败" + e.getMessage(), e);
        }
    }

    @Override
    public ResultDO<List<HintOverTime>> queryPushHintOverTime() {
        ResultDO<List<HintOverTime>> resultDO = new ResultDO<>();
        try {
            resultDO.setData(hintOverTimeManager.queryPushHintOverTime());
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "批量查找即将超时线索失败",e);
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    @Override
    public ResultDO<Boolean> updateIsPush(HintOverTime hintOverTime) {
        ResultDO<Boolean> resultDO = new ResultDO();
        if (null == hintOverTime){
            resultDO.setErrMsg("必传参数不能为空");
            return resultDO;
        }
        try {
            hintOverTime.setIsPush(YesNo.YES.getValue());
            resultDO.setData(hintOverTimeManager.updateIsPush(hintOverTime));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "更新超时表失败",e);
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }


}
