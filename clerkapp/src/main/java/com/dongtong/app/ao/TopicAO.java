package com.dongtong.app.ao;

import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.topic.dto.*;
import com.dongtong.topic.enums.PubishType;
import com.dongtong.topic.enums.TopicErrorCode;
import com.dongtong.topic.enums.UserType;
import com.dongtong.topic.query.TopicCommentDetailQuery;
import com.dongtong.topic.query.TopicDetailQuery;
import com.dongtong.topic.query.TopicListQuery;
import com.dongtong.topic.service.TopicCommentService;
import com.dongtong.topic.service.TopicLikeService;
import com.dongtong.topic.service.TopicService;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 生意圈
 * @package com.dongtong.app.ao
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/11 10:19
 * @version v1.0.0
 */
@Service
public class TopicAO {

    @Autowired
    private TopicService topicService;
    @Autowired
    private TopicCommentService topicCommentService;
    @Autowired
    private TopicLikeService topicLikeService;

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
        topicDTO.setUserType(UserType.CLERK.getValue());
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
        topicDeleteDTO.setUserType(UserType.CLERK.getValue());
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
        topicCommentDTO.setUserType(UserType.CLERK.getValue());
        return topicCommentService.publishComment(topicCommentDTO);
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
        topicCommentDTO.setCommentId(commentId);
        topicCommentDTO.setUserType(UserType.CLERK.getValue());
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
        topicDTO.setUserType(UserType.CLERK.getValue());
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
        topicDTO.setUserType(UserType.CLERK.getValue());
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
        topicLikeDTO.setUserType(UserType.CLERK.getValue());
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
        topicLikeDTO.setUserType(UserType.CLERK.getValue());
        return topicLikeService.unLikeTopic(topicLikeDTO);
    }

    /**
     * 生意圈列表
     * @param query
     * @return
     */
    public ResultDO<Page<TopicListDTO>> getTopicList(TopicListQuery query){
        ResultDO<Page<TopicListDTO>> result = new ResultDO<Page<TopicListDTO>>();
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        query.setUserId(userId);
        query.setUserType(UserType.CLERK.getValue());
        result = topicService.getTopicList(query);
        return result;
    }

    /**
     * 我的帖子详情
     * @param topicId
     * @return
     */
    public ResultDO<TopicDetailDTO> getTopicDetail(Long topicId){
        ResultDO<TopicDetailDTO> resultDO = new ResultDO<TopicDetailDTO>();
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        TopicDetailQuery topicDetailQuery = new TopicDetailQuery();
        topicDetailQuery.setTopicId(topicId);
        topicDetailQuery.setUserId(userId);
        topicDetailQuery.setUserType(UserType.CLERK.getValue());
        resultDO = topicService.getTopicDetailByID(topicDetailQuery);
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
    public ResultDO<Page<TopicCommentDetailDTO>> getCommentLlist(TopicCommentDetailQuery topicCommentDetailQuery){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        topicCommentDetailQuery.setUserId(userId);
        topicCommentDetailQuery.setUserType(UserType.CLERK.getValue());
        return topicCommentService.getCommentList(topicCommentDetailQuery);
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

        if(!ValidateHelper.isEmpty(image) && image.size() > 9){
            return "最多能发9张图片";
        }

        if(title.length() > 20){
            return "帖子标题只能0到20字";
        }

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
        return null;
    }
}
