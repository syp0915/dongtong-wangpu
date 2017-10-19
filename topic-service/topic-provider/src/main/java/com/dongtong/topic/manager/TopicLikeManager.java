package com.dongtong.topic.manager;

import com.dongtong.topic.dao.TopicLikeMapper;
import com.dongtong.topic.domain.TopicLike;
import com.dongtong.topic.dto.TopicLikeDTO;
import com.shfc.common.base.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description
 * @package com.dongtong.topic.manager
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/9 10:46
 * @version v1.0.0
 */
@Service
public class TopicLikeManager {

    @Autowired
    private TopicLikeMapper topicLikeMapper;

    /**
     * @description 帖子点赞接口
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/5/15 10:46
     * @params likeDTO
     * @return Integer
     */
    public Integer likeTopic(TopicLikeDTO likeDTO){
        TopicLike topicLike = new TopicLike();
        topicLike.setBusinessId(likeDTO.getTopicId());
        topicLike.setLikeId(likeDTO.getOperator());
        topicLike.setLikeType(likeDTO.getUserType());
        topicLike.setCreater(likeDTO.getOperator());
        try {
            Integer count = topicLikeMapper.insert(topicLike);
            return count;
        }catch ( Exception e ){
            Logger.error(this.getClass(), "likeTopic数据库点赞记录插入异常", e);
            return 0;
        }
    }

    public TopicLike getLikeById(TopicLikeDTO topicLikeDTO){
        TopicLike topicLike = new TopicLike();
        topicLike.setBusinessId(topicLikeDTO.getTopicId());
        topicLike.setLikeId(topicLikeDTO.getOperator());
        topicLike.setLikeType(topicLikeDTO.getUserType());
        try {
            TopicLike likes = topicLikeMapper.selectLikeByTopicIdAndUser(topicLike);
            return likes;
        }catch ( Exception e ){
            Logger.error(this.getClass(), "getLikeById数据库点赞记录查询异常", e);
            return null;
        }
    }

    public Integer getLikeByTopicIdAndUser(TopicLikeDTO topicLikeDTO){
        TopicLike params = new TopicLike();
        params.setLikeId(topicLikeDTO.getOperator());
        params.setBusinessId(topicLikeDTO.getTopicId());
        params.setLikeType(topicLikeDTO.getUserType());
        try {
            Integer count = topicLikeMapper.selectByTopicIdAndUser(params);
            return count;
        }catch ( Exception e ){
            Logger.error(this.getClass(), "getLikeById数据库点赞记录查询异常", e);
            return null;
        }
    }


    /**
     * @description 取消点赞
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/5/15 10:47
     * @params likeId
     * @return Integer
     */
    public Integer unlikeTopic(TopicLikeDTO topicLikeDTO){
        TopicLike topicLike = new TopicLike();
        topicLike.setLikeId(topicLikeDTO.getOperator());
        topicLike.setBusinessId(topicLikeDTO.getTopicId());
        topicLike.setLikeType(topicLikeDTO.getUserType());
        try {
            Integer count = topicLikeMapper.deleteLikeByTopicIdAndUser(topicLike);
            return count;
        }catch ( Exception e ){
            Logger.error(this.getClass(), "unlikeTopic数据库点赞记录删除异常", e);
            return null;
        }
    }

    /**
     * 获取帖子点赞接口
     * @param topicId
     * @return
     */
    public Integer getLikeTopicNumber(Long topicId){
        try {
            Integer count = topicLikeMapper.selectLikeByTopicId(topicId);
            return count;
        }catch ( Exception e ){
            Logger.error(this.getClass(), "likeTopic数据库点赞记录插入异常", e);
            return 0;
        }
    }
}
