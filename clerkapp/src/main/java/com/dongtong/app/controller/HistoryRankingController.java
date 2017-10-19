package com.dongtong.app.controller;

import com.dongtong.app.ao.HistoryRankingAO;
import com.dongtong.basic.dto.resp.HistoryPersonalRankingRespDTO;
import com.dongtong.basic.dto.resp.HistoryRankingListRespDTO;
import com.dongtong.basic.dto.resp.HistoryRankingRespDTO;
import com.dongtong.basic.query.HistoryPersonalRankingQuery;
import com.dongtong.basic.query.HistoryRankingQuery;
import com.shfc.common.base.Logger;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description 历史榜单
 * @package com.dongtong.app.controller
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/12 10:11
 * @version v1.0.0
 */
@Api(tags = "HistoryRankingController", description = "历史榜单接口")
@RestController
@RequestMapping(value = "/api/history",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HistoryRankingController {

    @Autowired
    private HistoryRankingAO historyRankingAO;

    /**
     * @description 获取历史榜单详细列表
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/15 10:10
     * @params query , version
     * @return ResultDO<Page<HistoryRankingListRespDTO>>
     */
    @ApiOperation(value = "获取历史榜单详细列表", notes = "获取历史榜单详细列表")
    @RequestMapping(value = "/historyRankingList/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Page<HistoryRankingListRespDTO>> getHistoryRankingList(@ApiParam(name="query",value="请求参数", required=true)@RequestBody HistoryRankingQuery query,
                                                                           @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        Logger.info(this.getClass(),"前端请求报文："+query);
        return historyRankingAO.getHistoryRankingList(query);
    }

    /**
     * @description 获取历史榜单
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/15 10:10
     * @params query , version
     * @return ResultDO<List<HistoryRankingRespDTO>>
     */
    @ApiOperation(value = "获取历史榜单", notes = "获取历史榜单")
    @RequestMapping(value = "/historyRanking/{version:.+}", method = RequestMethod.POST)
    public ResultDO<List<HistoryRankingRespDTO>> getHistoryRanking(@ApiParam(name="query",value="请求参数", required=true)@RequestBody HistoryRankingQuery query,
                                                                   @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        Logger.info(this.getClass(),"前端请求报文："+query);
        return historyRankingAO.getHistoryRanking(query);
    }

    /**
     * @description 获取历史个人排行
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/15 10:11
     * @params query , version
     * @return ResultDO<HistoryPersonalRankingRespDTO>
     */
    @ApiOperation(value = "获取历史个人排行", notes = "获取历史个人排行")
    @RequestMapping(value = "/historyPersonalRanking/{version:.+}", method = RequestMethod.POST)
    public ResultDO<HistoryPersonalRankingRespDTO> getHistoryPersonalRanking(@ApiParam(name="query",value="请求参数", required=true)@RequestBody HistoryPersonalRankingQuery query,
                                                                       @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        Logger.info(this.getClass(),"前端请求报文："+query);
        return historyRankingAO.getHistoryPersonalRanking(query);
    }
}
