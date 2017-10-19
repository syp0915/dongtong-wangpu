package com.dongtong.app.ao;

import com.dongtong.app.utils.CustomerappProperties;
import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.topic.dto.*;
import com.dongtong.topic.dto.req.ContentDetailReqDTO;
import com.dongtong.topic.dto.req.ContentGuideDetailReqDTO;
import com.dongtong.topic.dto.req.ContentReqDTO;
import com.dongtong.topic.dto.req.HotTagsReqDTO;
import com.dongtong.topic.dto.resp.*;
import com.dongtong.topic.enums.ContentTypeEnums;
import com.dongtong.topic.enums.PubishType;
import com.dongtong.topic.enums.TopicErrorCode;
import com.dongtong.topic.enums.UserType;
import com.dongtong.topic.query.*;
import com.dongtong.topic.service.ContentService;
import com.dongtong.topic.service.TopicCommentService;
import com.dongtong.topic.service.TopicLikeService;
import com.dongtong.topic.service.TopicService;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package com.dongtong.app.ao.TopicAO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/12 14:13
 * version V1.0.0
 */
@Service
public class TopicAO {

    @Autowired
    private TopicService topicService;
    @Autowired
    private TopicCommentService topicCommentService;
    @Autowired
    private TopicLikeService topicLikeService;
    @Autowired
    private ContentService contentService;

//    @Value("${topic.open}")
//    private Integer topicOpen;
    @Autowired
    private CustomerappProperties customerappProperties;

    /**
     * 生意圈列表
     * @param query
     * @return
     */
    public ResultDO<CustomerTopicListDTO> getTopicList(TopicListQuery query){
        ResultDO<Page<TopicListDTO>> result = new ResultDO<Page<TopicListDTO>>();
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        query.setUserId(userId);
        query.setUserType(UserType.MERCHANT.getValue());
        result = topicService.getTopicList(query);
        ResultDO<CustomerTopicListDTO> resultDO = new ResultDO<CustomerTopicListDTO>();
        if(result.isSuccess()){
            CustomerTopicListDTO customerTopicListDTO = new CustomerTopicListDTO();
            customerTopicListDTO.setPage(result.getData());
            customerTopicListDTO.setCreateTopicOpen(CustomerappProperties.getTopicPublishOpenFlag());
            resultDO.setData(customerTopicListDTO);
            resultDO.setSuccess(true);
        }else{
            CustomerTopicListDTO customerTopicListDTO = new CustomerTopicListDTO();
            customerTopicListDTO.setCreateTopicOpen(CustomerappProperties.getTopicPublishOpenFlag());
            resultDO.setSuccess(false);
            resultDO.setErrCode(result.getErrCode());
            resultDO.setErrMsg(result.getErrMsg());
        }
        return resultDO;
    }

    /**
     * 帖子详情
     * @param topicId
     * @return
     */
    public ResultDO<TopicDetailDTO> getTopicDetail(Long topicId){
        ResultDO<TopicDetailDTO> resultDO = new ResultDO<TopicDetailDTO>();
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        TopicDetailQuery topicDetailQuery = new TopicDetailQuery();
        topicDetailQuery.setUserId(userId);
        topicDetailQuery.setUserType(UserType.MERCHANT.getValue());
        topicDetailQuery.setTopicId(topicId);
        resultDO = topicService.getTopicDetailByID(topicDetailQuery);
        return resultDO;
    }

    public ResultDO<TopicDetailDTO> getTopicDetailByUserId(Long topicId){
        ResultDO<TopicDetailDTO> resultDO = new ResultDO<TopicDetailDTO>();
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        TopicDetailQuery topicDetailQuery = new TopicDetailQuery();
        topicDetailQuery.setUserId(userId);
        topicDetailQuery.setUserType(UserType.MERCHANT.getValue());
        topicDetailQuery.setTopicId(topicId);
        resultDO = topicService.getTopicDetailByUserId(topicDetailQuery);
        return resultDO;
    }

    /**
     * @description 生意圈帖子评论列表
     * @package com.dongtong.app.ao
     * @company dongtong
     * @copyright Copyright (c) 2016
     * @author chenxs
     * @date 2017/5/22 19:31
     * @version v1.0.0
     */
    public ResultDO<Page<TopicCommentDetailDTO>> getCommentList(TopicCommentDetailQuery topicCommentDetailQuery){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        topicCommentDetailQuery.setUserId(userId);
        topicCommentDetailQuery.setUserType(UserType.MERCHANT.getValue());
        return topicCommentService.getCommentList(topicCommentDetailQuery);
    }

    /**
     * 我的帖子列表
     * @param query
     * @return
     */
    public ResultDO<Page<TopicListDTO>> getTopicListByUserId(MyTopicListQuery query){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        query.setUserId(userId);
        query.setUserType(UserType.MERCHANT.getValue());
        ResultDO<Page<TopicListDTO>> result = new ResultDO<Page<TopicListDTO>>();
        result = topicService.getTopicListByUserId(query);
        return result;
    }

    /**
     * @description 发布帖子
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/5/15 10:04
     * @params topicDTO
     * @return ResultDO
     */
    public ResultDO publishTopic(TopicDTO topicDTO){
        ResultDO resultDO = new ResultDO();
        String result = checkTopicParams(topicDTO);
        if(result != null){
            resultDO.setErrCode(TopicErrorCode.PARAMS_ILLEGAL_ERROR.getCode());
            resultDO.setErrMsg(result);
            return resultDO;
        }
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        topicDTO.setOperator(userId);
        topicDTO.setUserType(UserType.MERCHANT.getValue());
//        topicDTO.setUserType(topicDTO.getPublisherType());
        return topicService.publishTopic(topicDTO);
    }

    /**
     * @description 删除帖子
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/5/15 10:05
     * @params topicId
     * @return ResultDO
     */
    public ResultDO deleteTopic(TopicDTO topicDTO){
        TopicDeleteDTO topicDeleteDTO = new TopicDeleteDTO();
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        topicDeleteDTO.setOperator(userId);
        topicDeleteDTO.setTopicId(topicDTO.getTopicId());
        topicDeleteDTO.setUserType(UserType.MERCHANT.getValue());
        return topicService.deleteTopic(topicDeleteDTO);
    }

    /**
     * @description 发布评论
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/5/15 10:06
     * @params topicCommentDTO
     * @return ResultDO
     */
    public ResultDO publishComment(TopicCommentDTO topicCommentDTO){
//        ResultDO resultDO = new ResultDO();
//        String comment = topicCommentDTO.getContent();
//        if(ValidateHelper.isEmptyString(comment) || comment.length() > 20){
//            resultDO.setErrCode(TopicErrorCode.PARAMS_ILLEGAL_ERROR.getCode());
//            resultDO.setErrMsg("评论字数只能0到100字");
//            return resultDO;
//        }
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        topicCommentDTO.setOperator(userId);
        topicCommentDTO.setUserType(UserType.MERCHANT.getValue());
        ResultDO resultDO = topicCommentService.publishComment(topicCommentDTO);
        return resultDO;
    }

    /**
     * @description 删除评论
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/5/15 10:06
     * @params commentId
     * @return ResultDO
     */
    public ResultDO deleteComment(Long commentId){
        TopicCommentDTO topicCommentDTO = new TopicCommentDTO();
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        topicCommentDTO.setOperator(userId);
        topicCommentDTO.setUserType(UserType.MERCHANT.getValue());
        topicCommentDTO.setCommentId(commentId);
        return topicCommentService.deleteComment(topicCommentDTO);
    }

    /**
     * @description 置顶帖子
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/5/15 10:06
     * @params topicId
     * @return ResultDO
     */
    public ResultDO stickTopic(TopicDTO topicDTO){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        topicDTO.setOperator(userId);
        topicDTO.setUserType(UserType.MERCHANT.getValue());
        return topicService.stickTopic(topicDTO);
    }

    /**
     * @description 取消置顶帖子
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/5/15 10:06
     * @params topicId
     * @return ResultDO
     */
    public ResultDO unStickTopic(TopicDTO topicDTO){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        topicDTO.setOperator(userId);
        topicDTO.setUserType(UserType.MERCHANT.getValue());
        return topicService.unStickTopic(topicDTO);
    }

    /**
     * @description 帖子点赞
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/5/15 10:06
     * @params topicLikeDTO
     * @return ResultDO
     */
    public ResultDO likeTopic(TopicLikeDTO topicLikeDTO){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        topicLikeDTO.setOperator(userId);
        topicLikeDTO.setUserType(UserType.MERCHANT.getValue());
        Logger.info(this.getClass(),"参数："+topicLikeDTO);
        return topicLikeService.likeTopic(topicLikeDTO);
    }

    /**
     * @description 取消帖子点赞
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/5/15 10:06
     * @params topicLikeDTO
     * @return ResultDO
     */
    public ResultDO unLikeTopic(TopicLikeDTO topicLikeDTO){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        topicLikeDTO.setOperator(userId);
        topicLikeDTO.setUserType(UserType.MERCHANT.getValue());
        Logger.info(this.getClass(),"取消点赞参数："+topicLikeDTO);
        return topicLikeService.unLikeTopic(topicLikeDTO);
    }

    private String checkTopicParams(TopicDTO topicDTO){
        String title = topicDTO.getTitle();
        String content = topicDTO.getContent();
        List<String> image = topicDTO.getImageList();
        Integer publishType = topicDTO.getPublishType();
        if(ValidateHelper.isEmptyString(title)){
            return "帖子标题不能为空";
        }

        if(ValidateHelper.isEmpty(publishType)){
            return "帖子类型不能为空";
        }

        if(ValidateHelper.isEmpty(content)){
            if(ValidateHelper.isEmpty(image) || image.size() == 0){
                return "帖子内容和图片必填其中之一";
            }
        }else{
            if(publishType == PubishType.TOPIC.getValue()) {
                if (ValidateHelper.isEmptyString(content) || content.length() > 200) {
                    return "帖子内容只能0到200字";
                }
            }

            if(publishType == PubishType.NEWS.getValue()){
                if(ValidateHelper.isEmptyString(content) || content.length() > 200){
                    return "资讯内容只能0到200字";
                }
            }
        }

        if(!ValidateHelper.isEmpty(image) && image.size() >  9){
            return "最多能发9张图片";
        }

        if(title.length() > 20){
            return "帖子标题只能0到20字";
        }

        return null;
    }


/********************************** v1.2 生意圈内容  ***************************************/

    /**
     * @description
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/8/7 0007 15:55
     * @param query
     * @return ResultDO<Page<ContentListRespDTO>>
     */
    public ResultDO<Page<ContentListRespDTO>> getContentList(ContentListQuery query){
        ResultDO<Page<ContentListRespDTO>> resultDO = new ResultDO<>();

        if(ValidateHelper.isEmpty(query.getTagId())){
            resultDO = contentService.getContentList(query);
        } else {
            resultDO = contentService.getContentListByTags(query);
        }

        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/8/7 0007 15:55
     * @param contentDetailReqDTO
     * @return ResultDO<ContentDetailRespDTO>
     */
    public ResultDO<ContentDetailRespDTO> getContentDetail(ContentDetailReqDTO contentDetailReqDTO) {
        ResultDO<ContentDetailRespDTO> resultDO = contentService.getContentDetail(contentDetailReqDTO);
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/8/7 0007 17:18
     * @param hotTagsReqDTO
     * @return ResultDO<List<HotTagsRespDTO>>
     */
    public ResultDO<List<HotTagsRespDTO>> getTagsList(HotTagsReqDTO hotTagsReqDTO){
        ResultDO<List<HotTagsRespDTO>> resultDO = contentService.getTagsList(hotTagsReqDTO);
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/8/7 0007 17:13
     * @return ResultDO<HomePageDataRespDTO>
     */
    public ResultDO<HomePageDataRespDTO> getHomePageData(){
        ResultDO<HomePageDataRespDTO> resultDO = new ResultDO<HomePageDataRespDTO>();
        HomePageDataRespDTO homePageDataRespDTO = new HomePageDataRespDTO();
        ContentListQuery query = new ContentListQuery();
        query.setPageSize(3);
        query.setPageNumber(1);
        query.setContentType(ContentTypeEnums.HEADLINE.getValue());
        ResultDO<Page<ContentListRespDTO>> headlinePageList = contentService.getContentList(query);
        homePageDataRespDTO.setHeadlineList(headlinePageList.getData().getData());

        query.setPageSize(2);
        query.setPageNumber(1);
        query.setContentType(ContentTypeEnums.BIBLE.getValue());
        ResultDO<Page<ContentListRespDTO>> biblePageList = contentService.getContentList(query);
        homePageDataRespDTO.setBibleList(biblePageList.getData().getData());

        Long userId = HttpSessionUtils.getCurrentAppUserId();
        TopicListQuery topicListQuery = new TopicListQuery();
        topicListQuery.setUserId(userId);
        topicListQuery.setUserType(UserType.MERCHANT.getValue());
        ResultDO<List<TopicListDTO>> topicPageList = topicService.getHomePageTopic(topicListQuery);
        homePageDataRespDTO.setTopicList(topicPageList.getData());

        resultDO.setData(homePageDataRespDTO);
        resultDO.setSuccess(true);

        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/8/7 0007 17:32
     * @param content
     * @return ResultDO
     */
    public ResultDO contentGood(ContentReqDTO content){
        ResultDO resultDO = contentService.contentGood(content);
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/8/7 0007 17:32
     * @param content
     * @return ResultDO
     */
    public ResultDO contentBad(ContentReqDTO content){
        ResultDO resultDO = contentService.contentBad(content);
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/8/8 0008 14:02
     * @return List<HomePageGuideListRespDTO>
     */
    public ResultDO<List<HomePageGuideListRespDTO>> getHomePageGuideData(){
        ResultDO<List<HomePageGuideListRespDTO>> resultDO = new ResultDO<>();

        ContentGuideListQuery contentGuideListQuery = new ContentGuideListQuery();
        contentGuideListQuery.setContentType(ContentTypeEnums.GUIDE.getValue());
        contentGuideListQuery.setPageNumber(1);
        contentGuideListQuery.setPageSize(3);
        resultDO = contentService.getHomePageGuideData(contentGuideListQuery);

        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/8/8 0008 14:04
     * @param contentGuideListQuery
     * @return ResultDO<Page<ContentGuideListRespDTO>>
     */
    public ResultDO<Page<ContentGuideListRespDTO>> getContentGuideList(ContentGuideListQuery contentGuideListQuery){
        ResultDO<Page<ContentGuideListRespDTO>> resultDO = new ResultDO<>();
        contentGuideListQuery.setContentType(ContentTypeEnums.GUIDE.getValue());
        resultDO = contentService.getContentGuideList(contentGuideListQuery);

        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.app.ao
     * @author chenxs
     * @date 2017/8/8 0008 14:05
     * @param contentGuideDetailReqDTO
     * @return ResultDO<ContentGuideDetailRespDTO>
     */
    public ResultDO<ContentGuideDetailRespDTO> getContentGuideDetail(ContentGuideDetailReqDTO contentGuideDetailReqDTO){
        ResultDO<ContentGuideDetailRespDTO> resultDO = new ResultDO<>();
        resultDO = contentService.getContentGuideDetail(contentGuideDetailReqDTO);

        return resultDO;
    }
}
