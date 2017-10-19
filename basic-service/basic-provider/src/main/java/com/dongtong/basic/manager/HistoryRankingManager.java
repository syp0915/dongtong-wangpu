package com.dongtong.basic.manager;

import com.dongtong.basic.dao.HistoryMonthRankingMapper;
import com.dongtong.basic.dao.HistoryWeekRankingMapper;
import com.dongtong.basic.domain.HistoryMonthRanking;
import com.dongtong.basic.domain.HistoryWeekRanking;
import com.dongtong.basic.dto.resp.HistoryPersonalRankingRespDTO;
import com.dongtong.basic.dto.resp.HistoryRankingListRespDTO;
import com.dongtong.basic.dto.resp.HistoryRankingRespDTO;
import com.dongtong.basic.enums.RankType;
import com.dongtong.basic.query.HistoryPersonalRankingQuery;
import com.dongtong.basic.query.HistoryRankingQuery;
import com.dongtong.basic.util.BasicProperties;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.date.DateUtils;
import com.shfc.common.exception.AppException;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @description 历史榜单
 * @package com.dongtong.basic.manager
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/12 10:16
 * @version v1.0.0
 */
@Service
public class HistoryRankingManager {

    @Autowired
    private HistoryMonthRankingMapper historyMonthRankingMapper;

    @Autowired
    private HistoryWeekRankingMapper historyWeekRankingMapper;

    @Autowired
    private BasicProperties basicProperties;

    /**
     * @description 分页获取榜单--榜单列表
     * @package com.dongtong.basic.manager
     * @author chenxs
     * @date 2017/5/15 10:16
     * @params historyRankingQuery
     * @return Page<HistoryRankingListRespDTO>
     */
    @Transactional(rollbackFor = AppException.class)
    public Page<HistoryRankingListRespDTO> getHistoryMonthRankingList(HistoryRankingQuery historyRankingQuery){
        //获取当前系统时间
        Date date = DateUtils.getCurrentDate();
        Integer year = DateUtils.getCurrentYear(date);      //获取当年年份
        historyRankingQuery.setYear(year);
        Page<HistoryRankingListRespDTO> page = new Page<>();
        page.setPageNumber(historyRankingQuery.getPageNumber());
        page.setPageSize(historyRankingQuery.getPageSize());
        page.setQuery(historyRankingQuery);
        if(historyRankingQuery.getType() == RankType.WEEK.getValue()){
            historyWeekRankingMapper.selectPageByTypeAndNO(page);
        } else if(historyRankingQuery.getType() == RankType.MONTH.getValue()){
            historyMonthRankingMapper.selectPageByTypeAndNO(page);
        }
        return page;
    }

    /**
     * @description 个人排行信息
     * @package com.dongtong.basic.manager
     * @author chenxs
     * @date 2017/5/15 10:16
     * @params type
     * @return HistoryPersonalRankingRespDTO
     */
    public HistoryPersonalRankingRespDTO getHistoryPersonalRanking(HistoryPersonalRankingQuery historyPersonalRankingQuery){
        Integer type = historyPersonalRankingQuery.getType();
        Long operator = historyPersonalRankingQuery.getOperator();   //TO-DO获取token中的信息
        Integer classify = historyPersonalRankingQuery.getClassify();
        HistoryPersonalRankingRespDTO historyPersonalRankingRespDTO = new HistoryPersonalRankingRespDTO();
        Integer number = historyPersonalRankingQuery.getNumber();
        //获取当前系统时间
        Date date = DateUtils.getCurrentDate();
        Integer year = DateUtils.getCurrentYear(date);      //获取当年年份
        if(type == RankType.WEEK.getValue()){
            HistoryWeekRanking historyWeekRanking = new HistoryWeekRanking();
            historyWeekRanking.setPeriod(String.valueOf(number));
            historyWeekRanking.setYear(String.valueOf(year));
            historyWeekRanking.setCustomerId(operator);
            historyWeekRanking.setClassify(classify);
            historyPersonalRankingRespDTO = historyWeekRankingMapper.selectByCustomerIdAndPeriod(historyWeekRanking);
            Date startDate = getFirstDayOfWeek(year,number);
            historyPersonalRankingRespDTO.setDateTime(DateUtils.date2String(getFirstDayOfWeek(startDate),"yyyy.MM.dd")+" - "+DateUtils.date2String(getLastDayOfWeek(startDate),"yyyy.MM.dd"));
        } else if(type == RankType.MONTH.getValue()){
            HistoryMonthRanking historyMonthRanking = new HistoryMonthRanking();
            historyMonthRanking.setPeriod(String.valueOf(number));
            historyMonthRanking.setYear(String.valueOf(year));
            historyMonthRanking.setCustomerId(operator);
            historyMonthRanking.setClassify(classify);
            historyPersonalRankingRespDTO = historyMonthRankingMapper.selectByCustomerIdAndPeriod(historyMonthRanking);
            Date curDate = getFirstDayOfMonth(year, number);
            String dateStr = DateUtils.date2String(curDate,"yyyy-MM-dd");
            historyPersonalRankingRespDTO.setDateTime(DateUtils.date2String(DateUtils.string2Date(DateUtils.getMonthFirstDate(dateStr)),"yyyy.MM.dd")+" - "+DateUtils.date2String(DateUtils.string2Date(DateUtils.getMonthLastDate(dateStr)),"yyyy.MM.dd"));
        }
        return historyPersonalRankingRespDTO;
    }

    /**
     * @description 历史(周/月)个人总榜单接口
     * @package com.dongtong.basic.manager
     * @author chenxs
     * @date 2017/5/15 10:17
     * @params type
     * @return List<HistoryRankingRespDTO>
     */
    public List<HistoryRankingRespDTO> getHistoryRanking(HistoryRankingQuery query){
        Integer type = query.getType();
        Long operator = query.getOperator();
        Integer classify = query.getClassify();
        //获取当前系统时间
        Date date = DateUtils.getCurrentDate();
        Integer year = DateUtils.getCurrentYear(date);      //获取当年年份
        List<HistoryRankingRespDTO> historyRanking = new ArrayList<>();
        String startDateStr = basicProperties.getHistoryRankingStartDate();
        Date startDate = DateUtils.string2Date(startDateStr,"yyyy-MM-dd");
        if(type == RankType.WEEK.getValue()){   //周排行
            int startWeek = getWeekOfYear(startDate);
            HistoryWeekRanking historyWeekRanking = new HistoryWeekRanking();
            Integer week = getWeekOfYear(date);     //获取当前周是本年度第几周
            historyWeekRanking.setPeriod(String.valueOf(week));
            historyWeekRanking.setYear(String.valueOf(year));
            historyWeekRanking.setCustomerId(operator);
            historyWeekRanking.setClassify(classify);
            historyRanking = historyWeekRankingMapper.selectByPeriod(historyWeekRanking);     //获取当年周期数小于当前 周数的记录
            Map<String,HistoryRankingRespDTO> map = new HashMap<String,HistoryRankingRespDTO>();
            for (HistoryRankingRespDTO historyRankingRespDTO :historyRanking){
                map.put(historyRankingRespDTO.getNumber()+"",historyRankingRespDTO);
            }
            historyRanking = new ArrayList<>();
            week  = week -1;
            for(int i = week;i>= startWeek;i--){
                HistoryRankingRespDTO historyRankingRespDTO = map.get(i+"");
                if(ValidateHelper.isEmpty(historyRankingRespDTO)){
                    historyRankingRespDTO = new HistoryRankingRespDTO();
                    historyRankingRespDTO.setRanking(0+"");
                    historyRankingRespDTO.setQuantity(0+"");
                    historyRankingRespDTO.setNumber(i);
                }
                String currentDate = DateUtils.addDayToDate((i-startWeek)*7,startDate,"yyyy-MM-dd");
                historyRankingRespDTO.setDateTime(DateUtils.date2String(getFirstDayOfWeek(DateUtils.string2Date(currentDate,"yyyy-MM-dd")),"yyyy.MM.dd")+" - "+DateUtils.date2String(getLastDayOfWeek(DateUtils.string2Date(currentDate,"yyyy-MM-dd")),"yyyy.MM.dd"));
                historyRanking.add(historyRankingRespDTO);
            }
        } else if(type == RankType.MONTH.getValue()){   //月排行
            int startMonth = DateUtils.getCurrentMonth(startDate);
            HistoryMonthRanking historyMonthRanking = new HistoryMonthRanking();
            Integer month = DateUtils.getCurrentMonth();    //  获取当前月份
            historyMonthRanking.setPeriod(String.valueOf(month));
            historyMonthRanking.setYear(String.valueOf(year));
            historyMonthRanking.setCustomerId(operator);
            historyMonthRanking.setClassify(classify);
            historyRanking = historyMonthRankingMapper.selectByPeriod(historyMonthRanking);    //获取当年周期数小于当前月数的记录
            Map<String,HistoryRankingRespDTO> map = new HashMap<String,HistoryRankingRespDTO>();
            for (HistoryRankingRespDTO historyRankingRespDTO :historyRanking){
                map.put(historyRankingRespDTO.getNumber()+"",historyRankingRespDTO);
            }
            historyRanking = new ArrayList<>();
            month = month -1;
            for(int i = month;i >= startMonth;i--){
                HistoryRankingRespDTO historyRankingRespDTO = map.get(i+"");
                if(ValidateHelper.isEmpty(historyRankingRespDTO)){
                    historyRankingRespDTO = new HistoryRankingRespDTO();
                    historyRankingRespDTO.setRanking(0+"");
                    historyRankingRespDTO.setQuantity(0+"");
                    historyRankingRespDTO.setNumber(i);
                }
                String currentDate = DateUtils.addMothToDate(i-startMonth,startDate,"yyyy-MM-dd");
                historyRankingRespDTO.setDateTime(DateUtils.date2String(DateUtils.string2Date(DateUtils.getMonthFirstDate(currentDate)),"yyyy.MM.dd")+" - "+DateUtils.date2String(DateUtils.string2Date(DateUtils.getMonthLastDate(currentDate)),"yyyy.MM.dd"));
                historyRanking.add(historyRankingRespDTO);
            }
        }
        return historyRanking;
    }

//    /**
//     * @description 获取指定日期在当年中是第几周
//     * @package com.dongtong.basic.manager
//     * @author chenxs
//     * @date 2017/5/15 10:17
//     * @params date
//     * @return Integer
//     */
//    private Integer getWeekOfYear(Date date){
//        Calendar calendar = Calendar.getInstance();
//        calendar.setFirstDayOfWeek(Calendar.MONDAY);
//        calendar.setTime(date);
//        System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));
//        return calendar.get(Calendar.WEEK_OF_YEAR);
//    }


    /**
     *  获取当前时间所在年的周数
     * @param date
     * @author liaozm
     * @return
     */
    public Integer getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);

        return c.get(Calendar.WEEK_OF_YEAR);
    }

    // 获取某年的第几周的开始日期
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    // 获取某年的第几周的开始日期
    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DATE, 1);
        Calendar cal = (GregorianCalendar) c.clone();
        return cal.getTime();
    }

    // 获取当前时间所在周的结束日期
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }

    // 获取当前时间所在周的开始日期
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }


    public static void main(String[] args) {
        String time = "2017-05-01";
        Date date = DateUtils.string2Date(time,"yyyy-MM-dd");
        Integer weekNumber =  new HistoryRankingManager().getWeekOfYear(date);
//        Date result = getFirstDayOfWeek(2017,weekNumber);
        Date result = getLastDayOfWeek(date);
        Date startResult = getFirstDayOfWeek(date);
        System.out.println(DateUtils.date2String(startResult)+DateUtils.date2String(result));
    }
}
