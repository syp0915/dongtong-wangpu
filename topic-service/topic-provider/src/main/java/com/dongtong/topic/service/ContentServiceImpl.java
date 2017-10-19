package com.dongtong.topic.service;

import com.dongtong.topic.dto.req.ContentDetailReqDTO;
import com.dongtong.topic.dto.req.ContentGuideDetailReqDTO;
import com.dongtong.topic.dto.req.ContentReqDTO;
import com.dongtong.topic.dto.req.HotTagsReqDTO;
import com.dongtong.topic.dto.resp.*;
import com.dongtong.topic.enums.TopicErrorCode;
import com.dongtong.topic.manager.ContentManager;
import com.dongtong.topic.query.ContentGuideListQuery;
import com.dongtong.topic.query.ContentListQuery;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentManager contentManager;
    /**
     * @description 获取内容列表
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/4 16:11
     * @param contentListQuery
     * @return ResultDO<List<ContentListRespDTO>>
     */
    public ResultDO<Page<ContentListRespDTO>> getContentList(ContentListQuery contentListQuery) {
        ResultDO<Page<ContentListRespDTO>> resultDO = new ResultDO<>();

        Integer contentType = contentListQuery.getContentType();
        if(ValidateHelper.isEmpty(contentType)){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        resultDO = contentManager.getContentList(contentListQuery);
        return resultDO;
    }

    /**
     * @description 获取内容详情
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/4 16:12
     * @param contentDetailReqDTO
     * @return ResultDO<ContentDetailRespDTO>
     */
    public ResultDO<ContentDetailRespDTO> getContentDetail(ContentDetailReqDTO contentDetailReqDTO) {
        ResultDO<ContentDetailRespDTO> resultDO = new ResultDO<>();

        Long contentId = contentDetailReqDTO.getContentId();
        if(ValidateHelper.isEmpty(contentId)){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        resultDO = contentManager.getContentDetail(contentDetailReqDTO);
        return resultDO;
    }

    /**
     * @description 获取热门标签
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/4 16:12
     * @param hotTagsReqDTO
     * @return ResultDO<List<String>>
     */
    public ResultDO<List<HotTagsRespDTO>> getTagsList(HotTagsReqDTO hotTagsReqDTO) {
        ResultDO<List<HotTagsRespDTO>> resultDO = new ResultDO<>();

        Integer contentType = hotTagsReqDTO.getContentType();
        if(ValidateHelper.isEmpty(contentType)){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        resultDO = contentManager.getTagsList(hotTagsReqDTO);
        return resultDO;
    }

    /**
     * @description 根据热门标签查询内容列表
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/7 11:09
     * @param contentListQuery
     * @return ResultDO<Page<ContentListRespDTO>>
     */
    public ResultDO<Page<ContentListRespDTO>> getContentListByTags(ContentListQuery contentListQuery) {
        ResultDO<Page<ContentListRespDTO>> resultDO = new ResultDO<>();

        Integer contentType = contentListQuery.getContentType();
        Long tagId = contentListQuery.getTagId();
        if(ValidateHelper.isEmpty(contentType) ||
                ValidateHelper.isEmpty(tagId)){
            resultDO.setSuccess(false);
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        resultDO = contentManager.getContentListByTags(contentListQuery);
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/7 0007 15:05
     * @param content
     * @return ResultDO
     */
    public ResultDO contentGood(ContentReqDTO content) {
        ResultDO resultDO = new ResultDO();

        Long contentId = content.getContentId();
        if(ValidateHelper.isEmpty(contentId)){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        resultDO = contentManager.contentGood(contentId);
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/7 0007 15:05
     * @param content
     * @return ResultDO
     */
    public ResultDO contentBad(ContentReqDTO content) {
        ResultDO resultDO = new ResultDO();

        Long contentId = content.getContentId();
        if(ValidateHelper.isEmpty(contentId)){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        resultDO = contentManager.contentBad(contentId);
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/8 0008 11:32
     * @param contentGuideListQuery
     * @return ResultDO<List<HomePageGuideListRespDTO>>
     */
    public ResultDO<List<HomePageGuideListRespDTO>> getHomePageGuideData(ContentGuideListQuery contentGuideListQuery) {
        ResultDO<List<HomePageGuideListRespDTO>> resultDO = new ResultDO<>();

        Integer contentType = contentGuideListQuery.getContentType();
        if(ValidateHelper.isEmpty(contentType)){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        resultDO = contentManager.getHomePageGuideData(contentGuideListQuery);
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/8 0008 11:32
     * @param contentGuideListQuery
     * @return ResultDO<Page<ContentGuideListRespDTO>>
     */
    public ResultDO<Page<ContentGuideListRespDTO>> getContentGuideList(ContentGuideListQuery contentGuideListQuery) {
        ResultDO<Page<ContentGuideListRespDTO>> resultDO = new ResultDO<>();

        Integer contentType = contentGuideListQuery.getContentType();
        if(ValidateHelper.isEmpty(contentType)){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        resultDO = contentManager.getContentGuideList(contentGuideListQuery);
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/8 0008 11:32
     * @param contentGuideDetailReqDTO
     * @return ResultDO<ContentGuideDetailRespDTO>
     */
    public ResultDO<ContentGuideDetailRespDTO> getContentGuideDetail(ContentGuideDetailReqDTO contentGuideDetailReqDTO) {
        ResultDO<ContentGuideDetailRespDTO> resultDO = new ResultDO<>();

        Long contentId = contentGuideDetailReqDTO.getContentId();
        if(ValidateHelper.isEmpty(contentId)){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        resultDO = contentManager.getContentGuideDetail(contentGuideDetailReqDTO);
        return resultDO;
    }
}
