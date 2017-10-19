package com.dongtong.scheduled.notice;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dongtong.basic.dto.RankingDTO;
import com.dongtong.basic.enums.RankServiceType;
import com.dongtong.basic.query.RankingQuery;
import com.dongtong.basic.service.RankingService;
import com.dongtong.clerk.service.ClerkHistoryService;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.date.DateUtils;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Package com.dongtong.scheduled.notice.ClerkHistoryWeekRanking
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2017
 * Author wliao
 * @date 2017/5/27 14:25
 * version V1.0.0
 */
public class ClerkHistoryWeekRanking extends BaseCheck implements SimpleJob {

    @Autowired
    private ClerkHistoryService clerkHistoryService;

    @Autowired
    private RankingService rankingService;

    @Override
    public void execute(ShardingContext shardingContext) {
        try {
            weekHistoryRanking();
        } catch (Exception e) {
            Logger.error(ClerkHistoryWeekRanking.class,"周排名跑批报错:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 获取历史周排名功能
     * auther:liaozm
     */
    public void weekHistoryRanking(){
        RankingQuery query = new RankingQuery();
        query.setType(0);//月榜
        query.setClassify(RankServiceType.DEVELOPMENT.getValue());//收铺
        query.setPageNumber(1);
        query.setPageSize(Integer.MAX_VALUE);

        String startDate = getCurrentMonday() + " 00:00:01";//开始时间 排序的开始和结束时间
        String endDate = getCurrentSunday() + " 23:59:59";//结束时间

        Calendar cal = Calendar.getInstance();
        //取当前日期的年份里面的周数
        int currentWeekOfYear = cal.get(Calendar.WEEK_OF_YEAR);

        ResultDO<Page<RankingDTO>> resultDO = rankingService.selectRanking(query);
        if(resultDO.isSuccess()){
            Date currentDate = new Date();
            List<RankingDTO> list = resultDO.getData().getData();
            if((!ValidateHelper.isEmpty(list))&&list.size()>0){
                for (RankingDTO rankingDTO : list) {
                    com.dongtong.clerk.domain.ClerkHistoryWeekRanking clerkHistoryWeekRanking = new com.dongtong.clerk.domain.ClerkHistoryWeekRanking();
                    clerkHistoryWeekRanking.setClassify(RankServiceType.DEVELOPMENT.getValue());
                    clerkHistoryWeekRanking.setCustomerId(Long.parseLong(rankingDTO.getId()));
                    clerkHistoryWeekRanking.setHeadPortrait(rankingDTO.getHeadPicture());
                    clerkHistoryWeekRanking.setName(rankingDTO.getName());
                    clerkHistoryWeekRanking.setQuantity(rankingDTO.getQuantity());
                    clerkHistoryWeekRanking.setRanking(rankingDTO.getRanking());
                    clerkHistoryWeekRanking.setYear(DateUtils.getCurrentYear(currentDate)+"");
                    clerkHistoryWeekRanking.setPeriod(currentWeekOfYear+"");//今年第几个礼拜
                    clerkHistoryWeekRanking.setPeriodBeginTime(DateUtils.string2Date((DateUtils.getCurrentTime("yyyy-MM")+"-01 00:00:01"),"yyyy-MM-dd HH:mm:ss"));
                    clerkHistoryWeekRanking.setPeriodEndTime(DateUtils.string2Date((DateUtils.getCurrentTime("yyyy-MM-dd")+" 23:59:59"),"yyyy-MM-dd HH:mm:ss"));
                    ResultDO<Boolean> result = clerkHistoryService.addClerkHistoryWeek(clerkHistoryWeekRanking);
                    Logger.info(MonthRanking.class,result.isSuccess()+"------");
                }
            }
        }
        query.setClassify(RankServiceType.ORDER_SEE.getValue());//约看
        resultDO = rankingService.selectRanking(query);
        if(resultDO.isSuccess()){
            Date currentDate = new Date();
            List<RankingDTO> list = resultDO.getData().getData();
            if((!ValidateHelper.isEmpty(list))&&list.size()>0){
                for (RankingDTO rankingDTO : list) {
                    com.dongtong.clerk.domain.ClerkHistoryWeekRanking clerkHistoryWeekRanking = new com.dongtong.clerk.domain.ClerkHistoryWeekRanking();
                    clerkHistoryWeekRanking.setClassify(RankServiceType.ORDER_SEE.getValue());
                    clerkHistoryWeekRanking.setCustomerId(Long.parseLong(rankingDTO.getId()));
                    clerkHistoryWeekRanking.setHeadPortrait(rankingDTO.getHeadPicture());
                    clerkHistoryWeekRanking.setName(rankingDTO.getName());
                    clerkHistoryWeekRanking.setQuantity(rankingDTO.getQuantity());
                    clerkHistoryWeekRanking.setRanking(rankingDTO.getRanking());
                    clerkHistoryWeekRanking.setYear(DateUtils.getCurrentYear(currentDate)+"");
                    clerkHistoryWeekRanking.setPeriod(currentWeekOfYear+"");//今年第几个礼拜
                    clerkHistoryWeekRanking.setPeriodBeginTime(DateUtils.string2Date(startDate,"yyyy-MM-dd HH:mm:ss"));
                    clerkHistoryWeekRanking.setPeriodEndTime(DateUtils.string2Date(endDate,"yyyy-MM-dd HH:mm:ss"));
                    ResultDO<Boolean> result = clerkHistoryService.addClerkHistoryWeek(clerkHistoryWeekRanking);
                    Logger.info(MonthRanking.class,result.isSuccess()+"------");
                }
            }
        }

        query.setClassify(RankServiceType.SIGN_CONTRACT.getValue());//签约
        resultDO = rankingService.selectRanking(query);
        if(resultDO.isSuccess()){
            Date currentDate = new Date();
            List<RankingDTO> list = resultDO.getData().getData();
            if((!ValidateHelper.isEmpty(list))&&list.size()>0){
                for (RankingDTO rankingDTO : list) {
                    com.dongtong.clerk.domain.ClerkHistoryWeekRanking clerkHistoryWeekRanking = new com.dongtong.clerk.domain.ClerkHistoryWeekRanking();
                    clerkHistoryWeekRanking.setClassify(RankServiceType.SIGN_CONTRACT.getValue());
                    clerkHistoryWeekRanking.setCustomerId(Long.parseLong(rankingDTO.getId()));
                    clerkHistoryWeekRanking.setHeadPortrait(rankingDTO.getHeadPicture());
                    clerkHistoryWeekRanking.setName(rankingDTO.getName());
                    clerkHistoryWeekRanking.setQuantity(rankingDTO.getQuantity());
                    clerkHistoryWeekRanking.setRanking(rankingDTO.getRanking());
                    clerkHistoryWeekRanking.setYear(DateUtils.getCurrentYear(currentDate)+"");
                    clerkHistoryWeekRanking.setPeriod(currentWeekOfYear+"");//今年第几个礼拜
                    clerkHistoryWeekRanking.setPeriodBeginTime(DateUtils.string2Date(startDate,"yyyy-MM-dd HH:mm:ss"));
                    clerkHistoryWeekRanking.setPeriodEndTime(DateUtils.string2Date(endDate,"yyyy-MM-dd HH:mm:ss"));
                    ResultDO<Boolean> result = clerkHistoryService.addClerkHistoryWeek(clerkHistoryWeekRanking);
                    Logger.info(MonthRanking.class,result.isSuccess()+"------");
                }
            }
        }

        query.setClassify(RankServiceType.REGISTERED.getValue());//注册
        resultDO = rankingService.selectRanking(query);
        if(resultDO.isSuccess()){
            Date currentDate = new Date();
            List<RankingDTO> list = resultDO.getData().getData();
            if((!ValidateHelper.isEmpty(list))&&list.size()>0){
                for (RankingDTO rankingDTO : list) {
                    com.dongtong.clerk.domain.ClerkHistoryWeekRanking clerkHistoryWeekRanking = new com.dongtong.clerk.domain.ClerkHistoryWeekRanking();
                    clerkHistoryWeekRanking.setClassify(RankServiceType.REGISTERED.getValue());
                    clerkHistoryWeekRanking.setCustomerId(Long.parseLong(rankingDTO.getId()));
                    clerkHistoryWeekRanking.setHeadPortrait(rankingDTO.getHeadPicture());
                    clerkHistoryWeekRanking.setName(rankingDTO.getName());
                    clerkHistoryWeekRanking.setQuantity(rankingDTO.getQuantity());
                    clerkHistoryWeekRanking.setRanking(rankingDTO.getRanking());
                    clerkHistoryWeekRanking.setYear(DateUtils.getCurrentYear(currentDate)+"");
                    clerkHistoryWeekRanking.setPeriod(currentWeekOfYear+"");//今年第几个礼拜
                    clerkHistoryWeekRanking.setPeriodBeginTime(DateUtils.string2Date(startDate,"yyyy-MM-dd HH:mm:ss"));
                    clerkHistoryWeekRanking.setPeriodEndTime(DateUtils.string2Date(endDate,"yyyy-MM-dd HH:mm:ss"));
                    ResultDO<Boolean> result = clerkHistoryService.addClerkHistoryWeek(clerkHistoryWeekRanking);
                    Logger.info(MonthRanking.class,result.isSuccess()+"------");
                }
            }
        }
    }

    /**
     * 当前周一的具体 时间
     * 格式 yyyy-MM-dd
     * @return
     */
    private static String getCurrentMonday(){
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);

        cal.setFirstDayOfWeek(Calendar.MONDAY);

        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天


        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }

        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);

        DateFormat datetimeDf = new SimpleDateFormat("yyyy-MM-dd");

        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        String imptimeBegin = datetimeDf.format(cal.getTime());
        Date mondayDate = cal.getTime();
        return datetimeDf.format(mondayDate);
    }

    /**
     * 当前周日的具体 时间
     * 格式 yyyy-MM-dd
     * @return
     */
    private static String getCurrentSunday(){
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.setFirstDayOfWeek(Calendar.MONDAY);

        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天


        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }

        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);

        DateFormat datetimeDf = new SimpleDateFormat("yyyy-MM-dd");

        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);

        cal.add(Calendar.DATE, 5);
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        String imptimeEnd = datetimeDf.format(cal.getTime());
        Date mondayDate = cal.getTime();
        return datetimeDf.format(mondayDate);
    }

}
