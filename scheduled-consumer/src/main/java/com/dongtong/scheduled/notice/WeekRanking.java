package com.dongtong.scheduled.notice;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dongtong.basic.dto.RankingDTO;
import com.dongtong.basic.dto.req.WorkNoticeReqDTO;
import com.dongtong.basic.enums.RankServiceType;
import com.dongtong.basic.enums.RankType;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.enums.WorkServiceType;
import com.dongtong.basic.query.RankingQuery;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.basic.service.RankingService;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.service.ClerkService;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @author sunyaping
 * @Package com.dongtong.scheduled.notice
 * @Description 周排名推送
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-25 17:08
 * version V1.0.0
 **/
@Component
public class WeekRanking extends BaseCheck implements SimpleJob {

    private static Logger logger = Logger.getLogger(WeekRanking.class);

    @Autowired
    private NoticePushService noticePushService;

    @Autowired
    private ClerkService clerkService;

    @Autowired
    private RankingService rankingService;

    private static HashMap<String,Object> hashMap=new HashMap<>();

    /**
     * 周榜排名推送 每天18点
     */
    public void weekRanking(){
        WorkNoticeReqDTO workNoticeReqDTO=new WorkNoticeReqDTO();
        getCloseStoreName(RankType.WEEK);
        getSignName(RankType.WEEK);
        getLookName(RankType.WEEK);
        getRegisterName(RankType.WEEK);
        getHintName(RankType.WEEK);
        workNoticeReqDTO.setReceiveType(ReceiveType.CLERK.getValue());
        workNoticeReqDTO.setWorkNoticeType(WorkServiceType.WEEK.getValue());
        workNoticeReqDTO.setServiceType(WorkServiceType.WEEK.getValue());
        workNoticeReqDTO.setCloseStoreName(checkStringMapObject(hashMap.get("closeStore")));
        workNoticeReqDTO.setSignName(checkStringMapObject(hashMap.get("sign")));
        workNoticeReqDTO.setLookName(checkStringMapObject(hashMap.get("look")));
        workNoticeReqDTO.setRegisterName(checkStringMapObject(hashMap.get("register")));
        workNoticeReqDTO.setHintName(checkStringMapObject(hashMap.get("hint")));
        com.shfc.common.base.Logger.info(WeekRanking.class,"推送内容是:"+JSON.toJSONString(workNoticeReqDTO));
        //所有业务员列表
        ResultDO<List<Clerk>> clerkListResult = clerkService.getAllClerkInfo();
        if (!clerkListResult.isSuccess()){
            return;
        }
        List<Clerk> clerkList = clerkListResult.getData();
        if (clerkList == null || clerkList.size() < 0){
            return;
        }
        for ( Clerk clerk : clerkList) {
            workNoticeReqDTO.setReceiveId(clerk.getId());
            ResultDO<Boolean> pushResult = noticePushService.pushWeekMonthNotice(workNoticeReqDTO,clerk.getDeviceId(),clerk.getOsType());
            if (!pushResult.isSuccess()) {
                com.shfc.common.base.Logger.warn(WeekRanking.class, "周榜排名推送：" + JSON.toJSONString(clerk) + "失败，失败原因：" + pushResult.getErrCode() + "-" + pushResult.getErrMsg());
            }
            com.shfc.common.base.Logger.info(WeekRanking.class,"周榜排名推送result---------->" + JSON.toJSONString(pushResult));
        }

    }

    @Override
    public void execute(ShardingContext shardingContext) {
        try {
            weekRanking();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取核准榜主
     * @param rankType
     */
    public void getCloseStoreName(RankType rankType){
        RankingQuery rankingQuery=new RankingQuery();
        rankingQuery.setType(rankType.getValue());
        rankingQuery.setPageNumber(1);
        rankingQuery.setPageSize(1);
        rankingQuery.setClassify(RankServiceType.DEVELOPMENT.getValue()); //核准榜
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
     * 获取线索榜主
     * @param rankType
     */
    public void getHintName(RankType rankType){
        RankingQuery rankingQuery=new RankingQuery();
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
