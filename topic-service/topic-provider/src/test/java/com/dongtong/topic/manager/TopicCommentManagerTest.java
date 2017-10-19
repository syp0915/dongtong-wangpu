package com.dongtong.topic.manager;

import com.dongtong.topic.JunitBaseTest;
import com.dongtong.topic.dto.TopicCommentDTO;
import com.dongtong.topic.dto.TopicCommentDetailDTO;
import com.dongtong.topic.enums.UserType;
import com.shfc.common.base.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description
 * @package com.dongtong.topic.manager
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/15 13:52
 * @version v1.0.0
 */
public class TopicCommentManagerTest extends JunitBaseTest {

    @Autowired
    private TopicCommentManager topicCommentManager;

    @Test
    public void testPublishComment(){
        TopicCommentDTO topicCommentDTO = new TopicCommentDTO();
        topicCommentDTO.setUserType(UserType.CLERK.getValue());
        topicCommentDTO.setTopicId(1L);
        topicCommentDTO.setOperator(1L);
        topicCommentDTO.setContent("66666");
        TopicCommentDetailDTO count = topicCommentManager.publishComment(topicCommentDTO);
        Logger.info(this.getClass(),"返回的报文："+count);
    }

    @Test
    public void testDeleteComment(){
        TopicCommentDTO topicCommentDTO = new TopicCommentDTO();
        topicCommentDTO.setCommentId(2222L);
        topicCommentDTO.setOperator(23232L);
        Integer count = topicCommentManager.deleteComment(topicCommentDTO);
        Logger.info(this.getClass(),"返回的报文："+count);
    }
}
