package com.dongtong.basic.service;

import com.dongtong.basic.constant.ErrorConstant;
import com.dongtong.basic.dto.AllRankingDTO;
import com.dongtong.basic.dto.MyRankingDTO;
import com.dongtong.basic.dto.RankingDTO;
import com.dongtong.basic.manager.RankingManager;
import com.dongtong.basic.query.AllRankingQuery;
import com.dongtong.basic.query.MyRankingQuery;
import com.dongtong.basic.query.RankingQuery;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package com.dongtong.basic.service.RankingServiceImpl
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/12 17:34
 * version V1.0.0
 */
@Service
public class RankingServiceImpl implements RankingService{

    @Autowired(required = false)
    private RankingManager rankingManager;

    @Override
    public ResultDO<List<AllRankingDTO>> selectAllRankingByType(AllRankingQuery query) {
        ResultDO<List<AllRankingDTO>> resultDO = new ResultDO<List<AllRankingDTO>>();
        if(ValidateHelper.isEmpty(query.getType())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        return rankingManager.selectAllRankingByType(query);
    }

    @Override
    public ResultDO<Page<RankingDTO>> selectRanking(RankingQuery query) {
        ResultDO<Page<RankingDTO>> resultDO  = new ResultDO<Page<RankingDTO>>();
        if(ValidateHelper.isEmpty(query.getType()) || ValidateHelper.isEmpty(query.getPageNumber()) || ValidateHelper.isEmpty(query.getClassify())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        if(ValidateHelper.isEmpty(query.getPageSize())){
            query.setPageSize(20);
        }
        return rankingManager.selectRanking(query);
    }

    /**
     * 我的 排名情况报表
     * @param query
     * @return
     */
    @Override
    public ResultDO<MyRankingDTO> myRanking(MyRankingQuery query) {
        ResultDO<MyRankingDTO> resultDO  = new ResultDO<MyRankingDTO>();
        if(ValidateHelper.isEmpty(query.getType()) || ValidateHelper.isEmpty(query.getUserId()) || ValidateHelper.isEmpty(query.getClassify())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        return rankingManager.myRanking(query);
    }
}
