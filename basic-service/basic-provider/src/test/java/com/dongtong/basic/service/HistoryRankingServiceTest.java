package com.dongtong.basic.service;

import com.dongtong.basic.JunitBaseTest;
import com.dongtong.basic.dto.resp.HistoryPersonalRankingRespDTO;
import com.dongtong.basic.dto.resp.HistoryRankingListRespDTO;
import com.dongtong.basic.dto.resp.HistoryRankingRespDTO;
import com.dongtong.basic.query.HistoryPersonalRankingQuery;
import com.dongtong.basic.query.HistoryRankingQuery;
import com.shfc.common.base.Logger;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @version V1.0.0
 * @Package com.dongtong.basic.service
 * @Description:
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/12 14:05
 */
public class HistoryRankingServiceTest extends JunitBaseTest {

    @Autowired
    private HistoryRankingService historyRankingService;

    @Test
    public void testGetHistoryRankingList(){
        HistoryRankingQuery historyRankingQuery = new HistoryRankingQuery();
        historyRankingQuery.setNumber(13);
        historyRankingQuery.setPageNumber(1);
        historyRankingQuery.setPageSize(20);
        historyRankingQuery.setType(0);
        historyRankingQuery.setClassify(1);
        ResultDO<Page<HistoryRankingListRespDTO>> result
                = historyRankingService.getHistoryRankingList(historyRankingQuery);
        Logger.info(this.getClass(),"接口返回的报文："+result.getData());
    }

    @Test
    public void testGetPersonalRanking(){
        HistoryPersonalRankingQuery query = new HistoryPersonalRankingQuery();
        query.setOperator(2L);
        query.setType(1);
        query.setClassify(2);
        query.setNumber(5);
        ResultDO<HistoryPersonalRankingRespDTO> result
                = historyRankingService.getHistoryPersonalRanking(query);
        Logger.info(this.getClass(),"接口返回的报文："+result);
    }

    @Test
    public void testGetHistoryRanking(){
        HistoryRankingQuery query = new HistoryRankingQuery();
        query.setType(1);
        query.setNumber(1);
        query.setOperator(2L);
        ResultDO<List<HistoryRankingRespDTO>> result
                = historyRankingService.getHistoryRanking(query);
        Logger.info(this.getClass(),"接口返回的报文："+result);
    }
}
