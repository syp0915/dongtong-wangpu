package com.dongtong.topic.service;

import com.dongtong.topic.domain.Topic;
import com.dongtong.topic.domain.TopicLike;
import com.dongtong.topic.dto.TopicLikeDTO;
import com.dongtong.topic.enums.TopicErrorCode;
import com.dongtong.topic.enums.YesNo;
import com.dongtong.topic.manager.TopicLikeManager;
import com.dongtong.topic.manager.TopicManager;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version V1.0.0
 * @Package com.dongtong.topic.service
 * @Description:
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/9 18:41
 */
@Service
public class TopicLikeServiceImpl implements TopicLikeService {

    @Autowired
    private TopicLikeManager topicLikeManager;
    @Autowired
    private TopicManager topicManager;

    /**
     * @description 帖子点赞接口
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 10:52
     * @params topicLikeDTO
     * @return ResultDO
     */
    public ResultDO likeTopic(TopicLikeDTO topicLikeDTO){
        Logger.info(this.getClass(),"传入的参数："+topicLikeDTO);
        ResultDO resultDO = new ResultDO();

        Long topicId = topicLikeDTO.getTopicId();
        Integer likeType = topicLikeDTO.getUserType();
        Long operator = topicLikeDTO.getOperator();

        //判断输入参数
        if(ValidateHelper.isEmpty(topicId)){
            resultDO.setErrMsg("帖子ID不能为空");
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }
        if(ValidateHelper.isEmpty(likeType) || ValidateHelper.isEmpty(operator)){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        //判断帖子是否存在
        Topic topic = topicManager.getTopicById(topicId);
        if(topic == null || topic.getIsDel() == YesNo.YES.getValue()){
            resultDO.setErrMsg(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getCode());
            return resultDO;
        }

        //判断当前用户是否点赞
        Integer likeCount = topicLikeManager.getLikeByTopicIdAndUser(topicLikeDTO);
        Integer count = 0;
        if(likeCount != null && likeCount == 0){
            count = topicLikeManager.likeTopic(topicLikeDTO);
        } else {
            count = likeCount;
        }

        resultDO.setSuccess(true);
        return resultDO;
    }

    /**
     * @description 帖子取消点赞接口
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 10:52
     * @params likeId
     * @return ResultDO
     */
    public ResultDO unLikeTopic(TopicLikeDTO topicLikeDTO){
        ResultDO resultDO = new ResultDO();
        Long operator = topicLikeDTO.getOperator();
        Long topicId = topicLikeDTO.getTopicId();
        //判断输入参数
        if(ValidateHelper.isEmpty(operator)){
            resultDO.setErrMsg(TopicErrorCode.PARAMETER_NOT_NULL.getMsg());
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        if(ValidateHelper.isEmpty(topicId)){
            resultDO.setErrMsg("帖子ID不能为空");
            resultDO.setErrCode(TopicErrorCode.PARAMETER_NOT_NULL.getCode());
            return resultDO;
        }

        Logger.info(this.getClass(),"取消点赞传入的参数："+topicLikeDTO);

        //判断是否存在
        TopicLike like = topicLikeManager.getLikeById(topicLikeDTO);
        if(like == null){
            resultDO.setErrMsg("不存在的点赞");
            resultDO.setErrCode(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getCode());
            return resultDO;
        }

        Long likeUser = like.getLikeId();
        //商户用户，需要判断点赞人和操作人是否为同一人，否则为无权限
        if(operator.longValue() != likeUser.longValue()){
            resultDO.setErrMsg(TopicErrorCode.NO_AUTH_OPEATE.getMsg());
            resultDO.setErrCode(TopicErrorCode.NO_AUTH_OPEATE.getCode());
            return resultDO;
        }
        Integer count = topicLikeManager.unlikeTopic(topicLikeDTO);
        resultDO.setSuccess(true);
        return resultDO;
    }
}
