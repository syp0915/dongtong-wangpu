package com.dongtong.basic.manager;

import com.dongtong.basic.constant.ErrorConstant;
import com.dongtong.basic.dao.RankingMapper;
import com.dongtong.basic.dto.*;
import com.dongtong.basic.enums.RankServiceType;
import com.dongtong.basic.enums.RankType;
import com.dongtong.basic.query.AllRankingQuery;
import com.dongtong.basic.query.MyRankingQuery;
import com.dongtong.basic.query.RankingPageQuery;
import com.dongtong.basic.query.RankingQuery;
import com.shfc.common.base.StringUtils;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.date.DateFormatUtils;
import com.shfc.common.date.DateUtils;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Package com.dongtong.basic.manager.RankingManager
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/12 17:52
 * version V1.0.0
 */
@Service
public class RankingManager {

    @Autowired
    private RankingMapper rankingMapper;

    public ResultDO<MyRankingDTO> myRanking(MyRankingQuery query) {
        ResultDO<MyRankingDTO> resultDO = new ResultDO<MyRankingDTO>();

        if(ValidateHelper.isEmpty(query.getUserId())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.PARAMETER_ERROR.getCode());
            resultDO.setErrMsg(ErrorConstant.PARAMETER_ERROR.getMsg());
            return resultDO;
        }

        RankType rankType =RankType.getTypeByValue(query.getType());
        RankServiceType rankServiceType = RankServiceType.getTypeByValue(query.getClassify());
        if(ValidateHelper.isEmpty(rankType) || ValidateHelper.isEmpty(rankServiceType)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.PARAMETER_ERROR.getCode());
            resultDO.setErrMsg(ErrorConstant.PARAMETER_ERROR.getMsg());
            return resultDO;
        }

        ClerkDTO clerkResultDO = rankingMapper.getClerkInfoById(query.getUserId());
        if(ValidateHelper.isEmpty(clerkResultDO) || ValidateHelper.isEmpty(clerkResultDO.getId())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.PARAMETER_ERROR.getCode());
            resultDO.setErrMsg(ErrorConstant.PARAMETER_ERROR.getMsg());
            return resultDO;
        }

        String startDate = "";//开始时间 排序的开始和结束时间
        String endDate = "";//结束时间

        if(rankType == RankType.WEEK){
            startDate = getCurrentMonday() + " 00:00:00";
            endDate = getCurrentSunday() + " 23:59:59";
        }

        if(rankType == RankType.MONTH){
            startDate = DateUtils.getCurrentTime("yyyy-MM")+"-01 00:00:00";
            endDate = DateUtils.getCurrentTime("yyyy-MM")+"-31 23:59:59";
        }

        MyRankingDTO myRankingDTO = new MyRankingDTO();
        ClerkDTO clerk = clerkResultDO;
        myRankingDTO.setName(clerk.getRealName());
        myRankingDTO.setHeadPicture(clerk.getHeadPortrait());


        if(rankServiceType == RankServiceType.DEVELOPMENT){//核准
            List<HashMap> list = rankingMapper.getClerkHitToShop(startDate,endDate);
            for (int i = 0 ;i<list.size();i++){
                Map map = list.get(i);
                if(StringUtils.toString(query.getUserId(),"").equals(StringUtils.toString(map.get("id"),""))){
                    myRankingDTO.setQuantity(StringUtils.toString(map.get("quantity"),""));//单数
                    myRankingDTO.setRanking(StringUtils.toString(map.get("rownumber"),""));//名次
                }
            }
        }else if(rankServiceType == RankServiceType.ORDER_SEE){//约看(customer_visit_shop)
            List<HashMap> list = rankingMapper.getClerkVisitShop(startDate,endDate);
            for (int i = 0 ;i<list.size();i++){
                Map map = list.get(i);
                if(StringUtils.toString(query.getUserId(),"").equals(StringUtils.toString(map.get("id"),""))){
                    myRankingDTO.setQuantity(StringUtils.toString(map.get("quantity"),""));//单数
                    myRankingDTO.setRanking(StringUtils.toString(map.get("rownumber"),""));//名次
                }
            }
        }else if(rankServiceType == RankServiceType.SIGN_CONTRACT){//签约(customer_sign)
            List<HashMap> list = rankingMapper.getClerkSign(startDate,endDate);
            for (int i = 0 ;i<list.size();i++){
                Map map = list.get(i);
                if(StringUtils.toString(query.getUserId(),"").equals(StringUtils.toString(map.get("id"),""))){
                    myRankingDTO.setQuantity(StringUtils.toString(map.get("quantity"),""));//单数
                    myRankingDTO.setRanking(StringUtils.toString(map.get("rownumber"),""));//名次
                }
            }
        }else if(rankServiceType == RankServiceType.REGISTERED){//注册(customer)
            String invitationCode = StringUtils.toString(clerk.getInvitationCode(),"");//邀请码
            List<RankingInfoDTO> list = rankingMapper.getCostomerByInviCode(startDate,endDate);
            for (int i = 0 ;i<list.size();i++){
                RankingInfoDTO rankingInfoDTO = list.get(i);
                if(invitationCode.equals(StringUtils.toString(rankingInfoDTO.getInvitationCode(),""))){
                    myRankingDTO.setQuantity(rankingInfoDTO.getQuantity());//单数
                    myRankingDTO.setRanking(rankingInfoDTO.getRowNumber());//名次
                }
            }
        }else if(rankServiceType == RankServiceType.HINT){//线索
            List<HashMap> list = rankingMapper.getClerkHit(startDate,endDate);
            for (int i = 0 ;i<list.size();i++){
                Map map = list.get(i);
                if(StringUtils.toString(query.getUserId(),"").equals(StringUtils.toString(map.get("id"),""))){
                    myRankingDTO.setQuantity(StringUtils.toString(map.get("quantity"),""));//单数
                    myRankingDTO.setRanking(StringUtils.toString(map.get("rownumber"),""));//名次
                }
            }
        }
        Integer bestRanking = 0;
        //查询历史最好名词
        if(rankType == RankType.WEEK){
            bestRanking = rankingMapper.getClerkHistoryBestRankingByWeek(query.getUserId()+"",rankServiceType.getValue());
        }else if(rankType == RankType.MONTH){
            bestRanking = rankingMapper.getClerkHistoryBestRankingByMonth(query.getUserId()+"",rankServiceType.getValue());
        }
        if(ValidateHelper.isEmpty(bestRanking)){//如果为空，未参与。就复制当前排名
            myRankingDTO.setBestRanking(new Float(myRankingDTO.getRanking()).intValue()+"");
        }else{
            myRankingDTO.setBestRanking(bestRanking.intValue()+"");
        }


        resultDO.setData(myRankingDTO);
        resultDO.setSuccess(true);
        resultDO.setErrCode(0);
        resultDO.setErrMsg("");
        return resultDO;
    }

    public ResultDO<List<AllRankingDTO>> selectAllRankingByType(AllRankingQuery query){
        ResultDO<List<AllRankingDTO>> resultDO = new ResultDO<List<AllRankingDTO>>();
        RankType rankType =RankType.getTypeByValue(query.getType());

        if(ValidateHelper.isEmpty(rankType)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.PARAMETER_ERROR.getCode());
            resultDO.setErrMsg(ErrorConstant.PARAMETER_ERROR.getMsg());
            return resultDO;
        }

        List<AllRankingDTO> list = new ArrayList<AllRankingDTO>();
        String startDate = "";//开始时间 排序的开始和结束时间
        String endDate = "";//结束时间

        if(rankType == RankType.WEEK){
            startDate = getCurrentMonday() + " 00:00:00";
            endDate = getCurrentSunday() + " 23:59:59";
        }

        if(rankType == RankType.MONTH){
            startDate = DateUtils.getCurrentTime("yyyy-MM")+"-01 00:00:00";
            endDate = DateUtils.getCurrentTime("yyyy-MM")+"-31 23:59:59";
        }

        AllRankingDTO allRankingDevelopment = new AllRankingDTO();
        allRankingDevelopment.setClassify(RankServiceType.DEVELOPMENT.getValue());
        List<HashMap> listDevelopment = rankingMapper.getClerkHitToShop(startDate,endDate);
        for (int i = 0 ;i<listDevelopment.size();i++){
            Map map = listDevelopment.get(i);
            if(StringUtils.toString(query.getUserId(),"").equals(StringUtils.toString(map.get("id"),""))){
                allRankingDevelopment.setQuantity(StringUtils.toString(map.get("quantity"),""));//单数
                allRankingDevelopment.setRanking(StringUtils.toString(map.get("rownumber"),""));//名次
            }
            map = listDevelopment.get(0);
            allRankingDevelopment.setOptimalName(StringUtils.toString(map.get("real_name"),""));//单数
            allRankingDevelopment.setOptimalNumber(StringUtils.toString(map.get("quantity"),""));
        }
        list.add(allRankingDevelopment);

        AllRankingDTO allRankingOrderSee = new AllRankingDTO();
        allRankingOrderSee.setClassify(RankServiceType.ORDER_SEE.getValue());
        List<HashMap> listOrderSee = rankingMapper.getClerkVisitShop(startDate,endDate);
        for (int i = 0 ;i<listOrderSee.size();i++){
            Map map = listOrderSee.get(i);
            if(StringUtils.toString(query.getUserId(),"").equals(StringUtils.toString(map.get("id"),""))){
                allRankingOrderSee.setQuantity(StringUtils.toString(map.get("quantity"),""));//单数
                allRankingOrderSee.setRanking(StringUtils.toString(map.get("rownumber"),""));//名次
            }
            map = listOrderSee.get(0);
            allRankingOrderSee.setOptimalName(StringUtils.toString(map.get("real_name"),""));//单数
            allRankingOrderSee.setOptimalNumber(StringUtils.toString(map.get("quantity"),""));
        }
        list.add(allRankingOrderSee);

        AllRankingDTO allRankingSign = new AllRankingDTO();
        allRankingSign.setClassify(RankServiceType.SIGN_CONTRACT.getValue());
        List<HashMap> listSign = rankingMapper.getClerkSign(startDate,endDate);
        for (int i = 0 ;i<listSign.size();i++){
            Map map = listSign.get(i);
            if(StringUtils.toString(query.getUserId(),"").equals(StringUtils.toString(map.get("id"),""))){
                allRankingSign.setQuantity(StringUtils.toString(map.get("quantity"),""));//单数
                allRankingSign.setRanking(StringUtils.toString(map.get("rownumber"),""));//名次
            }
            map = listSign.get(0);
            allRankingSign.setOptimalName(StringUtils.toString(map.get("real_name"),""));//单数
            allRankingSign.setOptimalNumber(StringUtils.toString(map.get("quantity"),""));
        }
        list.add(allRankingSign);

        AllRankingDTO allRankingRegist = new AllRankingDTO();
        allRankingRegist.setClassify(RankServiceType.REGISTERED.getValue());
        List<RankingInfoDTO> listRegist = rankingMapper.getCostomerByInviCode(startDate,endDate);
        for (int i = 0 ;i<listRegist.size();i++){
            RankingInfoDTO rankingInfoDTO = listRegist.get(i);
            if(StringUtils.toString(query.getUserId(),"").equals(rankingInfoDTO.getUserId())){
                allRankingRegist.setQuantity(rankingInfoDTO.getQuantity());//单数
                allRankingRegist.setRanking(rankingInfoDTO.getRowNumber());//名次
            }
            rankingInfoDTO = listRegist.get(0);
            allRankingRegist.setOptimalName(rankingInfoDTO.getName());
            allRankingRegist.setOptimalNumber(rankingInfoDTO.getQuantity());
        }
        list.add(allRankingRegist);

        AllRankingDTO allRankingHint = new AllRankingDTO();
        allRankingHint.setClassify(RankServiceType.HINT.getValue());
        List<HashMap> listHint = rankingMapper.getClerkHit(startDate,endDate);
        for (int i = 0 ;i<listHint.size();i++){
            Map map = listHint.get(i);
            if(StringUtils.toString(query.getUserId(),"").equals(StringUtils.toString(map.get("id"),""))){
                allRankingHint.setQuantity(StringUtils.toString(map.get("quantity"),""));//单数
                allRankingHint.setRanking(StringUtils.toString(map.get("rownumber"),""));//名次
            }
            map = listHint.get(0);
            allRankingHint.setOptimalName(StringUtils.toString(map.get("real_name"),""));//单数
            allRankingHint.setOptimalNumber(StringUtils.toString(map.get("quantity"),""));
        }
        list.add(allRankingHint);

        resultDO.setData(list);
        resultDO.setSuccess(true);
        resultDO.setErrCode(0);
        resultDO.setErrMsg("");
        return resultDO;
    }

    public ResultDO<Page<RankingDTO>> selectRanking(RankingQuery query){
        ResultDO<Page<RankingDTO>> resultDO = new ResultDO<Page<RankingDTO>>();
        //获取是否是推送
        Boolean whetherPush=query.getWhetherPush();
        RankType rankType =RankType.getTypeByValue(query.getType());
        RankServiceType rankServiceType = RankServiceType.getTypeByValue(query.getClassify());
        if(ValidateHelper.isEmpty(rankType) || ValidateHelper.isEmpty(rankServiceType)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.PARAMETER_ERROR.getCode());
            resultDO.setErrMsg(ErrorConstant.PARAMETER_ERROR.getMsg());
            return resultDO;
        }

        String startDate = "";//开始时间 排序的开始和结束时间
        String endDate = "";//结束时间

        if(rankType == RankType.WEEK){
            startDate = getCurrentMonday() + " 00:00:00";
            endDate = getCurrentSunday() + " 23:59:59";
        }

        if(rankType == RankType.MONTH){
            if(whetherPush==true){
                //获取上个月年月 getLastDate()
                String date=getLastDate();
                startDate = date+"-01 00:00:00";
                endDate = DateFormatUtils.formatDate(DateUtils.getMonthLastDate(date), "yyyy-MM-dd")+" 23:59:59";
            }else{
                startDate = DateUtils.getCurrentTime("yyyy-MM")+"-01 00:00:00";
                endDate = DateUtils.getCurrentTime("yyyy-MM")+"-31 23:59:59";
            }

        }

        Page<RankingDTO> page = new Page<RankingDTO>();
        page.setPageNumber(query.getPageNumber());
        page.setPageSize(query.getPageSize());
        RankingPageQuery pageQuery = new RankingPageQuery();
        pageQuery.setStartDate(startDate);
        pageQuery.setEndDate(endDate);
        page.setQuery(pageQuery);

        if(rankServiceType == RankServiceType.DEVELOPMENT){//核准
            List<RankingDTO> list = rankingMapper.getClerkHitToShopPage(page);
        }else if(rankServiceType == RankServiceType.ORDER_SEE){//约看(customer_visit_shop)
            List<RankingDTO> list = rankingMapper.getClerkVisitShopToPage(page);
        }else if(rankServiceType == RankServiceType.SIGN_CONTRACT){//签约(customer_sign)
            List<RankingDTO> list = rankingMapper.getClerkSignToPage(page);
        }else if(rankServiceType == RankServiceType.REGISTERED){//注册(customer)
            List<RankingDTO> list = rankingMapper.getClerkCustomerToPage(page);
        }else if(rankServiceType == RankServiceType.HINT){//线索
            List<RankingDTO> list = rankingMapper.getClerkHitPage(page);
        }
        resultDO.setData(page);
        resultDO.setSuccess(true);
        resultDO.setErrCode(0);
        resultDO.setErrMsg("");
        return resultDO;
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

    /**
     * 获取当前时间的上个月的年月
     * @return
     */
    private static String getLastDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        return sdf.format(cal.getTime());
    }

    public static void main(String[] args) {
        System.out.println(getCurrentMonday());
        System.out.println(getCurrentSunday());
        System.out.println(getLastDate());
    }

}
