package com.dongtong.app.controller;

import com.dongtong.app.ao.RankingAO;
import com.dongtong.basic.dto.AllRankingDTO;
import com.dongtong.basic.dto.MyRankingDTO;
import com.dongtong.basic.dto.RankingDTO;
import com.dongtong.basic.query.AllRankingQuery;
import com.dongtong.basic.query.MyRankingQuery;
import com.dongtong.basic.query.RankingQuery;
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
 * @Package com.dongtong.app.controller.RankingController
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/15 17:20
 * version V1.0.0
 */
@Api(tags = "RankingController", description = "生意圈接口")
@RestController
@RequestMapping(value = "/api/ranking",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RankingController {
    @Autowired
    private RankingAO rankingAO;


    /**
     * 本周或月个人带看排行榜-（报表排行）
     * @return
     */
    @ApiOperation(value = "本周或月个人带看排行榜-（报表排行）", notes = "本周或月个人带看排行榜-（报表排行）")
    @RequestMapping(value = "/allRankByType/{version:.+}", method = RequestMethod.POST)
    public ResultDO<List<AllRankingDTO>> selectAllRankingByType(@ApiParam(name="dto",value="请求参数", required=true)@RequestBody AllRankingQuery query,
                                                                @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        Logger.info(this.getClass(),"前端传过来的参数："+query);
        return rankingAO.selectAllRankingByType(query);
    }

    /**
     * 本周或月带看排行榜-（排行列表-所有人）
     * @return
     */
    @ApiOperation(value = "本周或月带看排行榜-（排行列表-所有人）", notes = "本周或月带看排行榜-（排行列表-所有人）")
    @RequestMapping(value = "/queryRank/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Page<RankingDTO>> selectRanking(@ApiParam(name="dto",value="请求参数", required=true)@RequestBody RankingQuery query,
                                                    @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        Logger.info(this.getClass(),"前端传过来的参数："+query);
        return rankingAO.selectRanking(query);
    }

    /**
     * 本周或月带看排行榜-（排行列表-所有人）
     * @return
     */
    @ApiOperation(value = "本周或月带看排行榜-（排行列表-所有人）", notes = "本周或月带看排行榜-（排行列表-所有人）")
    @RequestMapping(value = "/myRanking/{version:.+}", method = RequestMethod.POST)
    public ResultDO<MyRankingDTO> myRanking(@ApiParam(name="dto",value="请求参数", required=true)@RequestBody MyRankingQuery query,
                                                         @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        Logger.info(this.getClass(),"前端传过来的参数："+query);
        return rankingAO.myRanking(query);
    }
}
