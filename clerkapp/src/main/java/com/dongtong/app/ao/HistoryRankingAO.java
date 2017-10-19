package com.dongtong.app.ao;

import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.basic.dto.resp.HistoryPersonalRankingRespDTO;
import com.dongtong.basic.dto.resp.HistoryRankingListRespDTO;
import com.dongtong.basic.dto.resp.HistoryRankingRespDTO;
import com.dongtong.basic.query.HistoryPersonalRankingQuery;
import com.dongtong.basic.query.HistoryRankingQuery;
import com.dongtong.basic.service.HistoryRankingService;
import com.shfc.common.base.Logger;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version V1.0.0
 * @Package com.dongtong.app.ao
 * @Description:
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/12 14:20
 */
@Service
public class HistoryRankingAO {

    @Autowired
    private HistoryRankingService historyRankingService;

    /**
     * @description 查询历史榜单列表
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/5/15 10:12
     * @params query
     * @return ResultDO<Page<HistoryRankingListRespDTO>>
     */
    public ResultDO<Page<HistoryRankingListRespDTO>> getHistoryRankingList(HistoryRankingQuery query){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        query.setOperator(userId);
        ResultDO<Page<HistoryRankingListRespDTO>> resultDO = historyRankingService.getHistoryRankingList(query);
        Logger.info(this.getClass(),"查询的结果："+resultDO.getData().getData());
        return resultDO;
    }

    /**
     * @description 查询历史个人排行
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/5/15 10:12
     * @params query
     * @return ResultDO<HistoryPersonalRankingRespDTO>
     */
    public ResultDO<HistoryPersonalRankingRespDTO> getHistoryPersonalRanking(HistoryPersonalRankingQuery query){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        query.setOperator(userId);
        Logger.info(this.getClass(),"传入的用户ID:"+userId);
        ResultDO<HistoryPersonalRankingRespDTO> resultDO = historyRankingService.getHistoryPersonalRanking(query);
        Logger.info(this.getClass(),"查询的结果："+resultDO.getData());
        return resultDO;
    }

    /**
     * @description 查询历史榜单
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/5/15 10:11
     * @params query
     * @return ResultDO<List<HistoryRankingRespDTO>>
     */
    public ResultDO<List<HistoryRankingRespDTO>> getHistoryRanking(HistoryRankingQuery query){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        query.setOperator(userId);
        ResultDO<List<HistoryRankingRespDTO>> resultDO = historyRankingService.getHistoryRanking(query);
        Logger.info(this.getClass(),"查询的结果："+resultDO.getData());
        return resultDO;
    }

}
