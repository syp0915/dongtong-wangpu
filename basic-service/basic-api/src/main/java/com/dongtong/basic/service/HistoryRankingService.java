package com.dongtong.basic.service;

import com.dongtong.basic.dto.resp.HistoryPersonalRankingRespDTO;
import com.dongtong.basic.dto.resp.HistoryRankingListRespDTO;
import com.dongtong.basic.dto.resp.HistoryRankingRespDTO;
import com.dongtong.basic.query.HistoryPersonalRankingQuery;
import com.dongtong.basic.query.HistoryRankingQuery;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 历史榜单
 * @package com.dongtong.basic.service
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/12 10:13
 * @version v1.0.0
 */
@Service
public interface HistoryRankingService {

    /**
     * @description 历史详细榜单列表接口
     * @package com.dongtong.basic.service
     * @author chenxs
     * @date 2017/5/12 10:13
     * @params historyRankingQuery
     * @return ResultDO<Page<HistoryRankingListRespDTO>>
     */
    ResultDO<Page<HistoryRankingListRespDTO>> getHistoryRankingList(HistoryRankingQuery historyRankingQuery);

    /**
     * @description 历史个人排行接口
     * @package com.dongtong.basic.service
     * @author chenxs
     * @date 2017/5/12  10:14
     * @params type
     * @return ResultDO<HistoryPersonalRankingRespDTO>
     */
    ResultDO<HistoryPersonalRankingRespDTO> getHistoryPersonalRanking(HistoryPersonalRankingQuery historyPersonalRankingQuery);

    /**
     * @description 历史（周/月）榜单接口
     * @package com.dongtong.basic.service
     * @author chenxs
     * @date 2017/5/12  10:14
     * @params type
     * @return ResultDO<List<HistoryRankingRespDTO>>
     */
    ResultDO<List<HistoryRankingRespDTO>> getHistoryRanking(HistoryRankingQuery query);
}
