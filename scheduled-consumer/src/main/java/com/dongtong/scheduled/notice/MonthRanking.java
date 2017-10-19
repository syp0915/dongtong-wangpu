package com.dongtong.scheduled.notice;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dongtong.basic.dto.RankingDTO;
import com.dongtong.basic.dto.req.WorkNoticeReqDTO;
import com.dongtong.basic.enums.*;
import com.dongtong.basic.query.RankingQuery;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.basic.service.RankingService;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.domain.ClerkHistoryMonthRanking;
import com.dongtong.clerk.service.ClerkHistoryService;
import com.dongtong.clerk.service.ClerkService;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.date.DateUtils;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author sunyaping
 * @Package com.dongtong.scheduled.notice
 * @Description 月排名推送
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-25 17:08
 * version V1.0.0
 **/
@Component
public class MonthRanking extends BaseCheck implements SimpleJob {

    @Autowired
    private NoticePushService noticePushService;

    @Autowired
    private ClerkService clerkService;

    @Autowired
    private ClerkHistoryService clerkHistoryService;

    @Autowired
    private RankingService rankingService;

    private static HashMap<String,Object> hashMap=new HashMap<>();

    /**
     * 月榜排名推送 每月最后一天 18点
     */
    public void monthRanking(){
        WorkNoticeReqDTO workNoticeReqDTO=new WorkNoticeReqDTO();
        getCloseStoreName(RankType.MONTH);
        getSignName(RankType.MONTH);
        getLookName(RankType.MONTH);
        getRegisterName(RankType.MONTH);
        getHintName(RankType.MONTH);
        workNoticeReqDTO.setReceiveType(ReceiveType.CLERK.getValue());
        workNoticeReqDTO.setWorkNoticeType(WorkServiceType.MONTH.getValue());
        workNoticeReqDTO.setServiceType(WorkServiceType.MONTH.getValue());
        workNoticeReqDTO.setCloseStoreName(checkStringMapObject(hashMap.get("closeStore")));
        workNoticeReqDTO.setSignName(checkStringMapObject(hashMap.get("sign")));
        workNoticeReqDTO.setLookName(checkStringMapObject(hashMap.get("look")));
        workNoticeReqDTO.setRegisterName(checkStringMapObject(hashMap.get("register")));
        workNoticeReqDTO.setHintName(checkStringMapObject(hashMap.get("hint")));
        //所有业务员列表
        ResultDO<List<Clerk>> clerkListResult = clerkService.getAllClerkInfo();
        if (!clerkListResult.isSuccess()){
            return;
        }
        List<Clerk> clerkList = clerkListResult.getData();
        if (clerkList == null || clerkList.size() < 0){
            return;
        }
        for (Clerk clerk : clerkList) {
            workNoticeReqDTO.setReceiveId(clerk.getId());
            ResultDO<Boolean> pushResult = noticePushService.pushWeekMonthNotice(workNoticeReqDTO,clerk.getDeviceId(),clerk.getOsType());
            if (!pushResult.isSuccess()) {
                com.shfc.common.base.Logger.warn(MonthRanking.class, "月榜排名推送：" + JSON.toJSONString(clerk) + "失败，失败原因：" + pushResult.getErrCode() + "-" + pushResult.getErrMsg());
            }
            Logger.info(MonthRanking.class,"月榜排名推送result---------->" + JSON.toJSONString(pushResult));
        }
    }

    /**
     * 获取历史月排名功能
     * auther:liaozm
     */
    public void monthHistoryRanking(){
        RankingQuery query = new RankingQuery();
        query.setType(1);//月榜
        query.setClassify(RankServiceType.DEVELOPMENT.getValue());//收铺
        query.setPageNumber(1);
        query.setPageSize(Integer.MAX_VALUE);
        ResultDO<Page<RankingDTO>> resultDO = rankingService.selectRanking(query);
        if(resultDO.isSuccess()){
            Date currentDate = new Date();
            List<RankingDTO> list = resultDO.getData().getData();
            if((!ValidateHelper.isEmpty(list))&&list.size()>0){
                for (RankingDTO rankingDTO : list) {
                    ClerkHistoryMonthRanking clerkHistoryMonthRanking = new ClerkHistoryMonthRanking();
                    clerkHistoryMonthRanking.setClassify(RankServiceType.DEVELOPMENT.getValue());
                    clerkHistoryMonthRanking.setCustomerId(Long.parseLong(rankingDTO.getId()));
                    clerkHistoryMonthRanking.setHeadPortrait(rankingDTO.getHeadPicture());
                    clerkHistoryMonthRanking.setName(rankingDTO.getName());
                    clerkHistoryMonthRanking.setQuantity(rankingDTO.getQuantity());
                    clerkHistoryMonthRanking.setRanking(rankingDTO.getRanking());
                    clerkHistoryMonthRanking.setYear(DateUtils.getCurrentYear(currentDate)+"");
                    clerkHistoryMonthRanking.setPeriod(DateUtils.getCurrentMonth()+"");//当前月份
                    clerkHistoryMonthRanking.setPeriodBeginTime(DateUtils.string2Date((DateUtils.getCurrentTime("yyyy-MM")+"-01 00:00:01"),"yyyy-MM-dd HH:mm:ss"));
                    clerkHistoryMonthRanking.setPeriodEndTime(DateUtils.string2Date((DateUtils.getCurrentTime("yyyy-MM-dd")+" 23:59:59"),"yyyy-MM-dd HH:mm:ss"));
                    ResultDO<Boolean> result = clerkHistoryService.addClerkHistoryMonth(clerkHistoryMonthRanking);
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
                    ClerkHistoryMonthRanking clerkHistoryMonthRanking = new ClerkHistoryMonthRanking();
                    clerkHistoryMonthRanking.setClassify(RankServiceType.ORDER_SEE.getValue());
                    clerkHistoryMonthRanking.setCustomerId(Long.parseLong(rankingDTO.getId()));
                    clerkHistoryMonthRanking.setHeadPortrait(rankingDTO.getHeadPicture());
                    clerkHistoryMonthRanking.setName(rankingDTO.getName());
                    clerkHistoryMonthRanking.setQuantity(rankingDTO.getQuantity());
                    clerkHistoryMonthRanking.setRanking(rankingDTO.getRanking());
                    clerkHistoryMonthRanking.setYear(DateUtils.getCurrentYear(currentDate)+"");
                    clerkHistoryMonthRanking.setPeriod(DateUtils.getCurrentMonth()+"");//当前月份
                    clerkHistoryMonthRanking.setPeriodBeginTime(DateUtils.string2Date((DateUtils.getCurrentTime("yyyy-MM")+"-01 00:00:01"),"yyyy-MM-dd HH:mm:ss"));
                    clerkHistoryMonthRanking.setPeriodEndTime(DateUtils.string2Date((DateUtils.getCurrentTime("yyyy-MM-dd")+" 23:59:59"),"yyyy-MM-dd HH:mm:ss"));
                    ResultDO<Boolean> result = clerkHistoryService.addClerkHistoryMonth(clerkHistoryMonthRanking);
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
                    ClerkHistoryMonthRanking clerkHistoryMonthRanking = new ClerkHistoryMonthRanking();
                    clerkHistoryMonthRanking.setClassify(RankServiceType.SIGN_CONTRACT.getValue());
                    clerkHistoryMonthRanking.setCustomerId(Long.parseLong(rankingDTO.getId()));
                    clerkHistoryMonthRanking.setHeadPortrait(rankingDTO.getHeadPicture());
                    clerkHistoryMonthRanking.setName(rankingDTO.getName());
                    clerkHistoryMonthRanking.setQuantity(rankingDTO.getQuantity());
                    clerkHistoryMonthRanking.setRanking(rankingDTO.getRanking());
                    clerkHistoryMonthRanking.setYear(DateUtils.getCurrentYear(currentDate)+"");
                    clerkHistoryMonthRanking.setPeriod(DateUtils.getCurrentMonth()+"");//当前月份
                    clerkHistoryMonthRanking.setPeriodBeginTime(DateUtils.string2Date((DateUtils.getCurrentTime("yyyy-MM")+"-01 00:00:01"),"yyyy-MM-dd HH:mm:ss"));
                    clerkHistoryMonthRanking.setPeriodEndTime(DateUtils.string2Date((DateUtils.getCurrentTime("yyyy-MM-dd")+" 23:59:59"),"yyyy-MM-dd HH:mm:ss"));
                    ResultDO<Boolean> result = clerkHistoryService.addClerkHistoryMonth(clerkHistoryMonthRanking);
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
                    ClerkHistoryMonthRanking clerkHistoryMonthRanking = new ClerkHistoryMonthRanking();
                    clerkHistoryMonthRanking.setClassify(RankServiceType.REGISTERED.getValue());
                    clerkHistoryMonthRanking.setCustomerId(Long.parseLong(rankingDTO.getId()));
                    clerkHistoryMonthRanking.setHeadPortrait(rankingDTO.getHeadPicture());
                    clerkHistoryMonthRanking.setName(rankingDTO.getName());
                    clerkHistoryMonthRanking.setQuantity(rankingDTO.getQuantity());
                    clerkHistoryMonthRanking.setRanking(rankingDTO.getRanking());
                    clerkHistoryMonthRanking.setYear(DateUtils.getCurrentYear(currentDate)+"");
                    clerkHistoryMonthRanking.setPeriod(DateUtils.getCurrentMonth()+"");//当前月份
                    clerkHistoryMonthRanking.setPeriodBeginTime(DateUtils.string2Date((DateUtils.getCurrentTime("yyyy-MM")+"-01 00:00:01"),"yyyy-MM-dd HH:mm:ss"));
                    clerkHistoryMonthRanking.setPeriodEndTime(DateUtils.string2Date((DateUtils.getCurrentTime("yyyy-MM-dd")+" 23:59:59"),"yyyy-MM-dd HH:mm:ss"));
                    ResultDO<Boolean> result = clerkHistoryService.addClerkHistoryMonth(clerkHistoryMonthRanking);
                    Logger.info(MonthRanking.class,result.isSuccess()+"------");
                }
            }
        }


        query.setClassify(RankServiceType.HINT.getValue());//线索
        resultDO = rankingService.selectRanking(query);
        if(resultDO.isSuccess()){
            Date currentDate = new Date();
            List<RankingDTO> list = resultDO.getData().getData();
            if((!ValidateHelper.isEmpty(list))&&list.size()>0){
                for (RankingDTO rankingDTO : list) {
                    ClerkHistoryMonthRanking clerkHistoryMonthRanking = new ClerkHistoryMonthRanking();
                    clerkHistoryMonthRanking.setClassify(RankServiceType.REGISTERED.getValue());
                    clerkHistoryMonthRanking.setCustomerId(Long.parseLong(rankingDTO.getId()));
                    clerkHistoryMonthRanking.setHeadPortrait(rankingDTO.getHeadPicture());
                    clerkHistoryMonthRanking.setName(rankingDTO.getName());
                    clerkHistoryMonthRanking.setQuantity(rankingDTO.getQuantity());
                    clerkHistoryMonthRanking.setRanking(rankingDTO.getRanking());
                    clerkHistoryMonthRanking.setYear(DateUtils.getCurrentYear(currentDate)+"");
                    clerkHistoryMonthRanking.setPeriod(DateUtils.getCurrentMonth()+"");//当前月份
                    clerkHistoryMonthRanking.setPeriodBeginTime(DateUtils.string2Date((DateUtils.getCurrentTime("yyyy-MM")+"-01 00:00:01"),"yyyy-MM-dd HH:mm:ss"));
                    clerkHistoryMonthRanking.setPeriodEndTime(DateUtils.string2Date((DateUtils.getCurrentTime("yyyy-MM-dd")+" 23:59:59"),"yyyy-MM-dd HH:mm:ss"));
                    ResultDO<Boolean> result = clerkHistoryService.addClerkHistoryMonth(clerkHistoryMonthRanking);
                    Logger.info(MonthRanking.class,result.isSuccess()+"------");
                }
            }
        }
    }

    @Override
    public void execute(ShardingContext shardingContext) {
        try {
            monthRanking();
            monthHistoryRanking();//跑批排名
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取收铺榜主
     * @param rankType
     */
    public void getCloseStoreName(RankType rankType){
        RankingQuery rankingQuery=new RankingQuery();
        rankingQuery.setWhetherPush(true);
        rankingQuery.setType(rankType.getValue());
        rankingQuery.setPageNumber(1);
        rankingQuery.setPageSize(1);
        rankingQuery.setClassify(RankServiceType.DEVELOPMENT.getValue()); //收铺榜
        ResultDO<Page<RankingDTO>> pageResultDO=rankingService.selectRanking(rankingQuery);
        Page<RankingDTO> data=pageResultDO.getData();
        if(data.getData().size()>0){
            RankingDTO rankingDTO= data.getData().get(0);
            hashMap.put("closeStore",rankingDTO.getName());
        }
    }
    /**
     * 获取签约榜主
     * @param rankType
     */
    public void getSignName(RankType rankType){
        RankingQuery rankingQuery=new RankingQuery();
        rankingQuery.setWhetherPush(true);
        rankingQuery.setType(rankType.getValue());
        rankingQuery.setPageNumber(1);
        rankingQuery.setPageSize(1);
        rankingQuery.setClassify(RankServiceType.SIGN_CONTRACT.getValue()); //签约榜
        ResultDO<Page<RankingDTO>> pageResultDO=rankingService.selectRanking(rankingQuery);
        Page<RankingDTO> data=pageResultDO.getData();
        if(data.getData().size()>0) {
            RankingDTO rankingDTO = data.getData().get(0);
            hashMap.put("sign", rankingDTO.getName());
        }
    }

    /**
     * 获取约看榜主
     * @param rankType
     */
    public void getLookName(RankType rankType){
        RankingQuery rankingQuery=new RankingQuery();
        rankingQuery.setWhetherPush(true);
        rankingQuery.setType(rankType.getValue());
        rankingQuery.setPageNumber(1);
        rankingQuery.setPageSize(1);
        rankingQuery.setClassify(RankServiceType.ORDER_SEE.getValue()); //约看榜
        ResultDO<Page<RankingDTO>> pageResultDO=rankingService.selectRanking(rankingQuery);
        Page<RankingDTO> data=pageResultDO.getData();
        if(data.getData().size()>0) {
            RankingDTO rankingDTO = data.getData().get(0);
            hashMap.put("look", rankingDTO.getName());
        }
    }

    /**
     * 获取注册榜主
     * @param rankType
     */
    public void getRegisterName(RankType rankType){
        RankingQuery rankingQuery=new RankingQuery();
        rankingQuery.setWhetherPush(true);
        rankingQuery.setType(rankType.getValue());
        rankingQuery.setPageNumber(1);
        rankingQuery.setPageSize(1);
        rankingQuery.setClassify(RankServiceType.REGISTERED.getValue()); //注册榜
        ResultDO<Page<RankingDTO>> pageResultDO=rankingService.selectRanking(rankingQuery);
        Page<RankingDTO> data=pageResultDO.getData();
        if(data.getData().size()>0) {
            RankingDTO rankingDTO = data.getData().get(0);
            hashMap.put("register", rankingDTO.getName());
        }
    }

    /**
     * 获取注册榜主
     * @param rankType
     */
    public void getHintName(RankType rankType){
        RankingQuery rankingQuery=new RankingQuery();
        rankingQuery.setWhetherPush(true);
        rankingQuery.setType(rankType.getValue());
        rankingQuery.setPageNumber(1);
        rankingQuery.setPageSize(1);
        rankingQuery.setClassify(RankServiceType.HINT.getValue()); //注册榜
        ResultDO<Page<RankingDTO>> pageResultDO=rankingService.selectRanking(rankingQuery);
        Page<RankingDTO> data=pageResultDO.getData();
        if(data.getData().size()>0) {
            RankingDTO rankingDTO = data.getData().get(0);
            hashMap.put("hint", rankingDTO.getName());
        }
    }



}
