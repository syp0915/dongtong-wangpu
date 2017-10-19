package com.dongtong.topic.manager;

import com.dongtong.topic.dao.TopicCommentMapper;
import com.dongtong.topic.domain.TopicComment;
import com.dongtong.topic.dto.TopicCommentDTO;
import com.dongtong.topic.dto.TopicCommentDetailDTO;
import com.dongtong.topic.query.TopicCommentDetailQuery;
import com.shfc.common.base.Logger;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 2017/5/9
 * @package com.dongtong.topic.manager
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/15 10:49
 * @version v1.0.0
 */
@Service
public class TopicCommentManager {

    @Autowired
    private TopicCommentMapper topicCommentMapper;
    @Autowired
    private UserInfoManager userInfoManager;

    /**
     * @description 发布评论
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/5/15 10:48
     * @params topicCommentDTO
     * @return Integer
     */
    public TopicCommentDetailDTO publishComment(TopicCommentDTO topicCommentDTO){
        //组装数据库参数
        TopicComment topicComment = new TopicComment();
        topicComment.setBusinessId(topicCommentDTO.getTopicId());
        topicComment.setCommentatorId(topicCommentDTO.getOperator());
        topicComment.setCommentatorType(topicCommentDTO.getUserType());
        topicComment.setContent(topicCommentDTO.getContent());
        topicComment.setCreater(topicCommentDTO.getOperator());
        topicComment.setReply(topicCommentDTO.getIsReply());
        topicComment.setParentId(topicCommentDTO.getParentId());
        try {
            Integer count = topicCommentMapper.insert(topicComment);
            Logger.info(this.getClass(),"返回的ID:" + topicComment.getId());
        }catch ( Exception e ){
            Logger.error(this.getClass(), "publishComment数据库插入异常", e);
        }

        TopicCommentDetailQuery topicCommentDetailQuery  = new TopicCommentDetailQuery();
        topicCommentDetailQuery.setCommentId(topicComment.getId());
        topicCommentDetailQuery.setUserId(topicComment.getCommentatorId());
        topicCommentDetailQuery.setUserType(topicComment.getCommentatorType());
        TopicCommentDetailDTO topicCommentDetailDTO = selectCommentDetailById(topicCommentDetailQuery);

        return topicCommentDetailDTO;
    }

    /**
     * @description 删除评论
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/5/15 10:49
     * @params commentId
     * @return Integer
     */
    public Integer deleteComment(TopicCommentDTO topicCommentDTO){
        try {
            Integer count = topicCommentMapper.deleteById(topicCommentDTO);
            return count;
        }catch ( Exception e ){
            Logger.error(this.getClass(), "deleteComment数据库删除异常", e);
            return 0;
        }
    }

    /**
     * 分页根据topicid获取评论集合
     * @param topicCommentDetailQuery
     * @return
     */
    public Page<TopicCommentDetailDTO> getCommentByBusinessId(TopicCommentDetailQuery topicCommentDetailQuery){
        Page<TopicCommentDetailDTO> params = new Page<>();
        params.setPageSize(topicCommentDetailQuery.getPageSize());
        params.setPageNumber(topicCommentDetailQuery.getPageNumber());
        params.setQuery(topicCommentDetailQuery);
        Logger.info(this.getClass(),"PageSize："+topicCommentDetailQuery.getPageSize());
        topicCommentMapper.selectByBusinessId(params);
        Logger.info(this.getClass(),"帖子详情评论列表，本页评论条数："+params.getPageSize());
        return params;
    }

    /**
     * @description
     * @package com.dongtong.topic.manager
     * @company dongtong
     * @copyright Copyright (c) 2016
     * @author chenxs
     * @date 2017/5/22 20:09
     * @version v1.0.0
     */
    public Integer getCommentByBusinessId(Long businessId){
        return topicCommentMapper.selectCountByBusinessId(businessId);
    }


    public TopicComment getTopicCommentById(Long commentId){
        try {
            TopicComment topicComment = topicCommentMapper.selectByPrimaryKey(commentId);
            return topicComment;
        }catch ( Exception e ){
            Logger.error(this.getClass(), "getTopicCommentById数据库删除异常", e);
            return null;
        }
    }


    /**
     * @description
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/8/21 0021 11:29
     * @param
     * @return
     *
     * v1.2
     */
    public TopicCommentDetailDTO selectCommentDetailById(TopicCommentDetailQuery topicCommentDetailQuery){
        try {
            TopicCommentDetailDTO topicCommentDetailDTO = topicCommentMapper.selectCommentDetailById(topicCommentDetailQuery);
            return topicCommentDetailDTO;
        }catch ( Exception e ){
            Logger.error(this.getClass(), "获取当前评论异常", e);
            return null;
        }
    }

    /**
     * @description
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/8/22 0022 14:45
     * @param
     * @return
     */
    public TopicComment selectCommentById(Long id){
        try {
            TopicComment topicComment = topicCommentMapper.selectByPrimaryKey(id);
            return topicComment;
        }catch ( Exception e ){
            Logger.error(this.getClass(), "获取当前评论异常", e);
            return null;
        }
    }


}
