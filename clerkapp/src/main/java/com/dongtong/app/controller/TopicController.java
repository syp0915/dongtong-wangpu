package com.dongtong.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.dongtong.app.annotation.UnLoginLimit;
import com.dongtong.app.ao.TopicAO;
import com.dongtong.app.constant.ErrorConstant;
import com.dongtong.topic.dto.*;
import com.dongtong.topic.query.TopicCommentDetailQuery;
import com.dongtong.topic.query.TopicListQuery;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @description 生意圈
 * @package com.dongtong.app.controller
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/11 10:09
 * @version v1.0.0
 */
@Api(tags = "TopicController", description = "生意圈接口")
@RestController
@RequestMapping(value = "/api/topic",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TopicController {

    @Autowired
    private TopicAO topicAO;

    /**
     * @description 发布帖子
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/15 10:01
     * @params dto , version
     * @return ResultDO
     */
    @ApiOperation(value = "生意圈发布帖子", notes = "生意圈发布帖子")
    @RequestMapping(value = "/publishTopic/{version:.+}", method = RequestMethod.POST)
    public ResultDO publishTopic(@ApiParam(name="dto",value="请求参数", required=true)@RequestBody TopicDTO dto,
                                 @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        Logger.info(this.getClass(),"前端传过来的参数："+dto);
        return topicAO.publishTopic(dto);
    }

    /**
     * @description 删除帖子
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/15 10:02
     * @params dto , version
     * @return ResultDO
     */
    @ApiOperation(value = "生意圈删除帖子", notes = "生意圈删除帖子")
    @RequestMapping(value = "/deleteTopic/{version:.+}", method = RequestMethod.POST)
    public ResultDO deleteTopic(@ApiParam(name="dto",value="请求参数", required=true)@RequestBody TopicDTO dto,
                                @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        Logger.info(this.getClass(),"前端传过来的参数："+dto);
        return topicAO.deleteTopic(dto);
    }

    /**
     * @description 发布评论
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/15 10:03
     * @params dto , version
     * @return ResultDO
     */
    @ApiOperation(value = "生意圈发布评论", notes = "生意圈发布评论")
    @RequestMapping(value = "/publishComment/{version:.+}", method = RequestMethod.POST)
    public ResultDO publishComment(@ApiParam(name="dto",value="请求参数", required=true)@RequestBody TopicCommentDTO dto,
                                   @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        Logger.info(this.getClass(),"前端传过来的参数："+dto);
        return topicAO.publishComment(dto);
    }

    /**
     * @description 删除评论
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/15 10:03
     * @params dto , version
     * @return ResultDO
     */
    @ApiOperation(value = "生意圈删除评论", notes = "生意圈删除评论")
    @RequestMapping(value = "/deleteComment/{version:.+}", method = RequestMethod.POST)
    public ResultDO deleteComment(@ApiParam(name="dto",value="请求参数", required=false)@RequestBody TopicCommentDTO dto,
                                  @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        Logger.info(this.getClass(),"前端传过来的参数："+dto);
        return topicAO.deleteComment(dto.getCommentId());
    }

    /**
     * @description 置顶帖子
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/15 10:03
     * @params dto , version
     * @return ResultDO
     */
    @ApiOperation(value = "生意圈帖子置顶", notes = "生意圈帖子置顶")
    @RequestMapping(value = "/stickTopic/{version:.+}", method = RequestMethod.POST)
    public ResultDO stickTopic(@ApiParam(name="dto",value="请求参数", required=true)@RequestBody TopicDTO dto,
                               @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        Logger.info(this.getClass(),"前端传过来的参数："+dto);
        return topicAO.stickTopic(dto);
    }

    /**
     * @description 取消置顶帖子
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/15 10:03
     * @params dto , version
     * @return ResultDO
     */
    @ApiOperation(value = "生意圈取消帖子置顶", notes = "生意圈取消帖子置顶")
    @RequestMapping(value = "/unStickTopic/{version:.+}", method = RequestMethod.POST)
    public ResultDO unStickTopic(@ApiParam(name="dto",value="请求参数", required=true)@RequestBody TopicDTO dto,
                                 @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        Logger.info(this.getClass(),"前端传过来的参数："+dto);
        return topicAO.unStickTopic(dto);
    }
    /**
     * @description 帖子点赞
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/15 10:03
     * @params dto , version
     * @return ResultDO
     */
    @ApiOperation(value = "生意圈帖子点赞", notes = "生意圈帖子点赞")
    @RequestMapping(value = "/likeTopic/{version:.+}", method = RequestMethod.POST)
    public ResultDO likeTopic(@ApiParam(name="dto",value="请求参数", required=true)@RequestBody TopicLikeDTO dto,
                              @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        Logger.info(this.getClass(),"前端传过来的参数："+dto);
        return topicAO.likeTopic(dto);
    }

    /**
     * @description 取消帖子点赞
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/15 10:03
     * @params dto , version
     * @return ResultDO
     */
    @ApiOperation(value = "生意圈取消帖子点赞", notes = "生意圈取消帖子点赞")
    @RequestMapping(value = "/unLikeTopic/{version:.+}", method = RequestMethod.POST)
    public ResultDO unLikeTopic(@ApiParam(name="dto",value="请求参数", required=true)@RequestBody TopicLikeDTO dto,
                                @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        Logger.info(this.getClass(),"前端传过来的参数："+dto);
        return topicAO.unLikeTopic(dto);
    }

    /**
     * @Description: 生意圈列表接口
     * @Title estimate
     * @Author  wliao
     * @Date 2017/1/4 14:16
     * @param  reqJson, version
     * @return String
     * @throws
     */
    @UnLoginLimit
    @ApiOperation(value = "生意圈列表接口",notes = "生意圈列表接口")
    @RequestMapping(value = "/topicList/{version:.+}",method = RequestMethod.POST)
    public ResultDO<Page<TopicListDTO>> getTopicList(@ApiParam(name = "reqJson",value = "请求参数",required = true) @RequestBody TopicListQuery reqJson,
                                                     @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return topicAO.getTopicList(reqJson);
    }

    /**
     * @Description: 生意圈帖子详情接口
     * @Title estimate
     * @Author  wliao
     * @Date 2017/1/4 14:16
     * @param  jsonObject, version
     * @return String
     * @throws
     */
    @UnLoginLimit
    @ApiOperation(value = "生意圈帖子详情接口",notes = "生意圈帖子详情接口")
    @RequestMapping(value = "/topicDetail/{version:.+}",method = RequestMethod.POST)
    public ResultDO<TopicDetailDTO> getTopicDetail(
            @ApiParam(name="topicId",value="帖子id", required=true)@RequestBody JSONObject jsonObject,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        ResultDO<TopicDetailDTO> resultDO = new ResultDO<>();
        Logger.info(this.getClass(),"前端传来的参数:"+jsonObject);
        String topicId = String.valueOf(jsonObject.get("topicId"));
        Logger.info(this.getClass(),topicId);
        if(ValidateHelper.isEmpty(topicId)){
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg("帖子ID不能为空");
            return resultDO;
        }
        try{
            Long topicIdLong = Long.valueOf(topicId);
            return topicAO.getTopicDetail(topicIdLong);
        }catch(NumberFormatException e){
            Logger.error(this.getClass(),e.getMessage());
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg("帖子ID不合法");
            return resultDO;
        }
    }

    /**
     * @description
     * @package com.dongtong.app.controller
     * @company dongtong
     * @copyright Copyright (c) 2016
     * @author chenxs
     * @date 2017/5/22 19:31
     * @version v1.0.0
     */
    @UnLoginLimit
    @ApiOperation(value = "生意圈帖子评论列表接口",notes = "生意圈帖子评论列表接口")
    @RequestMapping(value = "/topicCommentList/{version:.+}",method = RequestMethod.POST)
    public ResultDO<Page<TopicCommentDetailDTO>> getCommentLlist(
            @ApiParam(name="topicId",value="帖子id", required=true)@RequestBody TopicCommentDetailQuery topicCommentDetailQuery,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return topicAO.getCommentLlist(topicCommentDetailQuery);
    }

}
