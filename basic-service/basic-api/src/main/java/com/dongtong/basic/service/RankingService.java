package com.dongtong.basic.service;

import com.dongtong.basic.dto.AllRankingDTO;
import com.dongtong.basic.dto.MyRankingDTO;
import com.dongtong.basic.dto.RankingDTO;
import com.dongtong.basic.query.AllRankingQuery;
import com.dongtong.basic.query.MyRankingQuery;
import com.dongtong.basic.query.RankingQuery;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;

import java.util.List;

/**
 * @Package com.dongtong.basic.service.RankingService
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/12 17:34
 * version V1.0.0
 */
public interface RankingService {

    /**
     * 本周或月个人带看排行榜-（报表排行）
     * @return
     */
    ResultDO<List<AllRankingDTO>> selectAllRankingByType(AllRankingQuery query);


    /**
     * 本周或月带看排行榜-（排行列表-所有人）
     * @return
     */
    ResultDO<Page<RankingDTO>> selectRanking(RankingQuery query);

    /**
     * 本周或月带看排行榜-（排行列表-所有人）
     * @return
     */
    ResultDO<MyRankingDTO> myRanking(MyRankingQuery query);


}
