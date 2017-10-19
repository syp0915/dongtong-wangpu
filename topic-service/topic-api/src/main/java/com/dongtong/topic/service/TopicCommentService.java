package com.dongtong.topic.service;

import com.dongtong.topic.dto.TopicCommentDTO;
import com.dongtong.topic.dto.TopicCommentDetailDTO;
import com.dongtong.topic.query.TopicCommentDetailQuery;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Service;

/**
 * @version V1.0.0
 * @Package com.dongtong.topic.service
 * @Description:
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/9 15:46
 */
@Service
public interface TopicCommentService {

    /**
     * 评论帖子接口
     * @param topicCommentDTO
     * @return
     */
    ResultDO<TopicCommentDetailDTO> publishComment(TopicCommentDTO topicCommentDTO);

    /**
     * 删除评论接口
     * @param topicCommentDTO
     * @return
     */
    ResultDO deleteComment(TopicCommentDTO topicCommentDTO);

    ResultDO<Page<TopicCommentDetailDTO>> getCommentList(TopicCommentDetailQuery topicCommentDetailQuery);
}
