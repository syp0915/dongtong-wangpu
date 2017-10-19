package com.dongtong.basic.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.JunitBaseTest;
import com.dongtong.basic.dto.AllRankingDTO;
import com.dongtong.basic.dto.MyRankingDTO;
import com.dongtong.basic.dto.RankingDTO;
import com.dongtong.basic.enums.RankServiceType;
import com.dongtong.basic.enums.RankType;
import com.dongtong.basic.query.AllRankingQuery;
import com.dongtong.basic.query.MyRankingQuery;
import com.dongtong.basic.query.RankingQuery;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Package com.dongtong.basic.service.RankingServiceTest
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/15 15:36
 * version V1.0.0
 */
public class RankingServiceTest extends JunitBaseTest {
    @Autowired
    private RankingService rankingService;

    /**
     * 本周或月个人带看排行榜-（报表排行）
     */
    @Test
    public void testSelectAllRankingByType(){
        AllRankingQuery query = new AllRankingQuery();
        query.setType(1);
        query.setUserId(1L);
        ResultDO<List<AllRankingDTO>> result = rankingService.selectAllRankingByType(query);
        System.out.println(result.isSuccess()+"-----------------"+result.getErrCode()+"++++++++"+result.getData().size());
    }

    /**
     * 本周或月个人带看排行榜-（报表排行）
     */
    @Test
    public void testSelectAllRankingByType1(){
        AllRankingQuery query = new AllRankingQuery();
        query.setType(2);
        query.setUserId(1L);
        ResultDO<List<AllRankingDTO>> result = rankingService.selectAllRankingByType(query);
        System.out.println(result.isSuccess()+"-----------------"+result.getErrCode()+"++++++++"+result.getErrMsg());
    }

    /**
     * 本周或月个人带看排行榜-（报表排行）
     */
    @Test
    public void testSelectAllRankingByType2(){
        AllRankingQuery query = new AllRankingQuery();
        query.setType(0);
        query.setUserId(1L);
        ResultDO<List<AllRankingDTO>> result = rankingService.selectAllRankingByType(query);
        System.out.println(result.isSuccess()+"-----------------"+result.getErrCode()+"++++++++"+result.getErrMsg());
    }

    /**
     * 本周或月个人带看排行榜-（报表排行）
     */
    @Test
    public void testSelectRanking(){
        RankingQuery rankingQuery=new RankingQuery();
        rankingQuery.setType(RankType.WEEK.getValue());
        rankingQuery.setPageNumber(1);
        rankingQuery.setPageSize(1);
        rankingQuery.setClassify(RankServiceType.DEVELOPMENT.getValue()); //核准榜
        ResultDO<Page<RankingDTO>> pageResultDO=rankingService.selectRanking(rankingQuery);
        Page<RankingDTO> data=pageResultDO.getData();
        System.out.println(JSON.toJSONString(data));
        if(data.getData().size()>0){
            RankingDTO rankingDTO= data.getData().get(0);
            System.out.println(JSON.toJSONString(rankingDTO));
        }
//        RankingQuery query = new RankingQuery();
//        query.setType(1);
//        query.setClassify(1);
//        query.setPageNumber(1);
//        query.setPageSize(20);
//        ResultDO<Page<RankingDTO>> result = rankingService.selectRanking(query);
//        System.out.println(result.isSuccess()+"-----------------"+result.getErrCode()+"++++++++"+result.getData().getTotalSize());
    }

    /**
     * 本周或月个人带看排行榜-（报表排行）
     */
    @Test
    public void testSelectRanking1(){
        RankingQuery query = new RankingQuery();
        query.setType(0);
        query.setClassify(1);
        query.setPageNumber(1);
        query.setPageSize(20);
        ResultDO<Page<RankingDTO>> result = rankingService.selectRanking(query);
        System.out.println(result.isSuccess()+"-----------------"+result.getErrCode()+"++++++++"+result.getData().getTotalSize());
    }

    /**
     * 本周或月个人带看排行榜-（报表排行）
     */
    @Test
    public void testSelectRanking2(){
        RankingQuery query = new RankingQuery();
        query.setType(2);
        query.setClassify(1);
        query.setPageNumber(1);
        query.setPageSize(20);
        ResultDO<Page<RankingDTO>> result = rankingService.selectRanking(query);
        System.out.println(result.isSuccess()+"-----------------"+result.getErrCode()+"++++++++"+result.getErrMsg());
    }

    /**
     * 本周或月个人带看排行榜-（报表排行）
     */
    @Test
    public void testSelectRanking3(){
        RankingQuery query = new RankingQuery();
        query.setClassify(1);
        query.setPageNumber(1);
        query.setPageSize(20);
        ResultDO<Page<RankingDTO>> result = rankingService.selectRanking(query);
        System.out.println(result.isSuccess()+"-----------------"+result.getErrCode()+"++++++++"+result.getErrMsg());
    }

    /**
     * 本周或月个人带看排行榜-（报表排行）
     */
    @Test
    public void testSelectRanking4(){
        RankingQuery query = new RankingQuery();
        query.setType(0);
        query.setPageNumber(1);
        query.setPageSize(20);
        ResultDO<Page<RankingDTO>> result = rankingService.selectRanking(query);
        System.out.println(result.isSuccess()+"-----------------"+result.getErrCode()+"++++++++"+result.getData());
    }
    @Test
    public void testMyRanking(){
        MyRankingQuery query = new MyRankingQuery();
        query.setClassify(1);
        query.setUserId(1L);
        query.setType(1);
        ResultDO<MyRankingDTO> result = rankingService.myRanking(query);
        System.out.println(result.isSuccess()+"-----------------"+result.getErrCode()+result.getData().getBestRanking());
    }
    @Test
    public void testMyRanking1(){
        MyRankingQuery query = new MyRankingQuery();
        query.setUserId(1L);
        query.setType(1);
        ResultDO<MyRankingDTO> result = rankingService.myRanking(query);
        System.out.println(result.isSuccess()+"-----------------"+result.getErrCode()+"DDDDDDDDDDD"+result.getErrMsg());
    }
    @Test
    public void testMyRanking2(){
        MyRankingQuery query = new MyRankingQuery();
        query.setClassify(11);
        query.setUserId(1L);
        query.setType(1);
        ResultDO<MyRankingDTO> result = rankingService.myRanking(query);
        System.out.println(result.isSuccess()+"-----------------"+result.getErrCode()+"DDDDDDDDDDD"+result.getErrMsg());
    }
    @Test
    public void testMyRanking3(){
        MyRankingQuery query = new MyRankingQuery();
        query.setClassify(1);
        query.setUserId(1L);
        ResultDO<MyRankingDTO> result = rankingService.myRanking(query);
        System.out.println(result.isSuccess()+"-----------------"+result.getErrCode()+"DDDDDDDDDDD"+result.getErrMsg());
    }
    @Test
    public void testMyRanking4(){
        MyRankingQuery query = new MyRankingQuery();
        query.setClassify(1);
        query.setUserId(1L);
        query.setType(133);
        ResultDO<MyRankingDTO> result = rankingService.myRanking(query);
        System.out.println(result.isSuccess()+"-----------------"+result.getErrCode());
    }
}
