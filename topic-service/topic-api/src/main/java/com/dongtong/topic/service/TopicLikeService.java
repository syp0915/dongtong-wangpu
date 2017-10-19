package com.dongtong.topic.service;

import com.dongtong.topic.dto.TopicLikeDTO;
import com.shfc.common.result.ResultDO;
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
public interface TopicLikeService {

    /**
     * 帖子点赞接口
     * @param topicLikeDTO
     * @return
     */
    ResultDO likeTopic(TopicLikeDTO topicLikeDTO);

    /**
     * 帖子取消点赞接口
     * @param topicLikeDTO
     * @return
     */
    ResultDO unLikeTopic(TopicLikeDTO topicLikeDTO);
}
