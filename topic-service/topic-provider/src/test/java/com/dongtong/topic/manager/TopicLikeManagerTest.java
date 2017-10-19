package com.dongtong.topic.manager;

import com.dongtong.topic.JunitBaseTest;
import com.dongtong.topic.dto.TopicLikeDTO;
import com.dongtong.topic.enums.UserType;
import com.shfc.common.base.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description 帖子点赞相关Manager的Test
 * @package com.dongtong.topic.manager
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/15 13:51
 * @version v1.0.0
 */
public class TopicLikeManagerTest extends JunitBaseTest {

    @Autowired
    private TopicLikeManager topicLikeManager;

    /**
     * @description 帖子点赞
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/5/15 14:55
     */
    @Test
    public void testLikeTopic(){
        TopicLikeDTO likeDTO = new TopicLikeDTO();
        likeDTO.setOperator(1L);
        likeDTO.setLikeType(UserType.CLERK.getValue());
        likeDTO.setTopicId(0L);
        Integer count = topicLikeManager.likeTopic(likeDTO);
        Logger.info(this.getClass(),"返回的报文："+count);
    }

    @Test
    public void testUnlikeTopic(){
        TopicLikeDTO likeDTO = new TopicLikeDTO();
        likeDTO.setOperator(1L);
        likeDTO.setLikeType(UserType.CLERK.getValue());
        likeDTO.setTopicId(0L);
        Integer count = topicLikeManager.unlikeTopic(likeDTO);
        Logger.info(this.getClass(),"返回的报文："+count);
    }
}
