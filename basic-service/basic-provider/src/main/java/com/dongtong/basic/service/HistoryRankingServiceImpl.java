package com.dongtong.basic.service;

import com.dongtong.basic.constant.ErrorConstant;
import com.dongtong.basic.dto.resp.HistoryPersonalRankingRespDTO;
import com.dongtong.basic.dto.resp.HistoryRankingListRespDTO;
import com.dongtong.basic.dto.resp.HistoryRankingRespDTO;
import com.dongtong.basic.enums.RankType;
import com.dongtong.basic.manager.HistoryRankingManager;
import com.dongtong.basic.query.HistoryPersonalRankingQuery;
import com.dongtong.basic.query.HistoryRankingQuery;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @description 历史榜单
 * @package com.dongtong.basic.service
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/12 10:15
 * @version v1.0.0
 */
@Service
public class HistoryRankingServiceImpl implements HistoryRankingService{

    @Autowired
    private HistoryRankingManager historyRankingManager;

    /**
     * @description 查询（周/月）详细榜单
     * @package com.dongtong.basic.service
     * @author chenxs
     * @date 2017/5/15 10:15
     * @params historyRankingQuery
     * @return ResultDO<Page<HistoryRankingListRespDTO>>
     */
    public ResultDO<Page<HistoryRankingListRespDTO>> getHistoryRankingList(HistoryRankingQuery historyRankingQuery){
        ResultDO<Page<HistoryRankingListRespDTO>> resultDO = new ResultDO<Page<HistoryRankingListRespDTO>>();
        Integer type = historyRankingQuery.getType();
        Integer number = historyRankingQuery.getNumber();
        Integer pageNumber = historyRankingQuery.getPageNumber();
        Integer classify = historyRankingQuery.getClassify();
        if(ValidateHelper.isEmpty(type)){
            resultDO.setErrMsg("榜单类型不能为空");
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            return resultDO;
        }

        if(ValidateHelper.isEmpty(number)){
            resultDO.setErrMsg("周/月数不能为空");
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            return resultDO;
        }

        if(ValidateHelper.isEmpty(pageNumber)){
            resultDO.setErrMsg("页数不能为空");
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            return resultDO;
        }

        if(ValidateHelper.isEmpty(classify)){
            resultDO.setErrMsg("业务类型不能为空");
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            return resultDO;
        }

        String checkMsg = checkParams(type,number);
        if(checkMsg != null){
            resultDO.setErrMsg(checkMsg);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            return resultDO;
        }
        Page<HistoryRankingListRespDTO> page = historyRankingManager.getHistoryMonthRankingList(historyRankingQuery);
        resultDO.setData(page);
        resultDO.setSuccess(true);
        Logger.info(this.getClass(),"查询的结果："+page.getData());
        return resultDO;
    }

    /**
     * @description 查询个人历史排行
     * @package com.dongtong.basic.service
     * @author chenxs
     * @date 2017/5/15 10:15
     * @params historyPersonalRankingQuery
     * @return ResultDO<HistoryPersonalRankingRespDTO>
     */
    public ResultDO<HistoryPersonalRankingRespDTO> getHistoryPersonalRanking(HistoryPersonalRankingQuery historyPersonalRankingQuery){
        ResultDO<HistoryPersonalRankingRespDTO> resultDO = new ResultDO<HistoryPersonalRankingRespDTO>();
        Integer type = historyPersonalRankingQuery.getType();
        Integer number = historyPersonalRankingQuery.getNumber();
        Integer classify = historyPersonalRankingQuery.getClassify();
        if(ValidateHelper.isEmpty(type)){
            resultDO.setErrMsg("榜单类型不能为空");
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            return resultDO;
        }

        if(ValidateHelper.isEmpty(number)){
            resultDO.setErrMsg("周/月数不能为空");
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            return resultDO;
        }

        if(ValidateHelper.isEmpty(classify)){
            resultDO.setErrMsg("业务类型不能为空");
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            return resultDO;
        }

        String checkMsg = checkParams(type,number);
        if(checkMsg != null){
            resultDO.setErrMsg(checkMsg);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            return resultDO;
        }
        HistoryPersonalRankingRespDTO historyPersonalRankingRespDTO = historyRankingManager.getHistoryPersonalRanking(historyPersonalRankingQuery);
        resultDO.setSuccess(true);
        resultDO.setData(historyPersonalRankingRespDTO);
        Logger.info(this.getClass(),"查询的结果："+resultDO);
        return resultDO;
    }

    /**
     * @description 查询（周/月）总榜单
     * @package com.dongtong.basic.service
     * @author chenxs
     * @date 2017/5/15 10:15
     * @params type
     * @return ResultDO<List<HistoryRankingRespDTO>>
     */
    public ResultDO<List<HistoryRankingRespDTO>> getHistoryRanking(HistoryRankingQuery query){
        ResultDO<List<HistoryRankingRespDTO>> resultDO = new ResultDO<List<HistoryRankingRespDTO>>();
        Integer type = query.getType();
        Integer classify = query.getClassify();
        if(ValidateHelper.isEmpty(type)){
            resultDO.setErrMsg("榜单类型不能为空");
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            return resultDO;
        }

        if(ValidateHelper.isEmpty(classify)){
            resultDO.setErrMsg("业务类型不能为空");
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            return resultDO;
        }
        List<HistoryRankingRespDTO> historyRankingRespDTO = historyRankingManager.getHistoryRanking(query);
        resultDO.setSuccess(true);
        resultDO.setData(historyRankingRespDTO);
        Logger.info(this.getClass(),"查询的结果："+resultDO);
        return resultDO;
    }

    private String checkParams(int type, int number){
        if(type == RankType.MONTH.getValue()){
            if(number > 12 || number < 0){
                return "月榜单月份为正数不能大于12";
            }
        }
        if(type == RankType.WEEK.getValue()){
            if(number > 52 || number < 0){
                return "周榜单周数为正数且不能大于52";
            }
        }
        return null;
    }
}
