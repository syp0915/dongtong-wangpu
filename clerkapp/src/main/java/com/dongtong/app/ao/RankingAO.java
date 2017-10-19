package com.dongtong.app.ao;

import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.basic.dto.AllRankingDTO;
import com.dongtong.basic.dto.MyRankingDTO;
import com.dongtong.basic.dto.RankingDTO;
import com.dongtong.basic.query.AllRankingQuery;
import com.dongtong.basic.query.MyRankingQuery;
import com.dongtong.basic.query.RankingQuery;
import com.dongtong.basic.service.RankingService;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package com.dongtong.app.ao.RankingAO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/15 17:21
 * version V1.0.0
 */
@Service
public class RankingAO {

    @Autowired
    private RankingService rankingService;

    public ResultDO<List<AllRankingDTO>> selectAllRankingByType(AllRankingQuery query){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        query.setUserId(userId);
        if(ValidateHelper.isEmpty(query.getType())){
            ResultDO<List<AllRankingDTO>> resultDO = new ResultDO<List<AllRankingDTO>>();
            resultDO.setErrMsg("请求参数不能为空");
            resultDO.setErrCode(-1);
            return resultDO;
        }
        return rankingService.selectAllRankingByType(query);
    }

    public ResultDO<Page<RankingDTO>> selectRanking(RankingQuery query){
        if(ValidateHelper.isEmpty(query.getType())||ValidateHelper.isEmpty(query.getClassify())||ValidateHelper.isEmpty(query.getPageNumber())){
            ResultDO<Page<RankingDTO>> resultDO = new ResultDO<Page<RankingDTO>>();
            resultDO.setErrMsg("请求参数不能为空");
            resultDO.setErrCode(-1);
            return resultDO;
        }
        return rankingService.selectRanking(query);
    }

    public ResultDO<MyRankingDTO> myRanking(MyRankingQuery query){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        query.setUserId(userId);
        if(ValidateHelper.isEmpty(query.getType())||ValidateHelper.isEmpty(query.getClassify())){
            ResultDO<MyRankingDTO> resultDO = new ResultDO<MyRankingDTO>();
            resultDO.setErrMsg("请求参数不能为空");
            resultDO.setErrCode(-1);
            return resultDO;
        }
        return rankingService.myRanking(query);
    }
}
