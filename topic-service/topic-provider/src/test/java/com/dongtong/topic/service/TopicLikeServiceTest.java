package com.dongtong.topic.service;

import com.dongtong.topic.JunitBaseTest;
import com.dongtong.topic.dto.TopicLikeDTO;
import com.dongtong.topic.enums.UserType;
import com.shfc.common.base.Logger;
import com.shfc.common.result.ResultDO;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version V1.0.0
 * @Package com.dongtong.topic.service
 * @Description:
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/11 10:12
 */
public class TopicLikeServiceTest extends JunitBaseTest {

    @Autowired
    private TopicLikeService topicLikeService;

    /**
     * @description 运营点赞
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:31
     */
    @Test
    public void testLikeTopic0(){
        TopicLikeDTO topicLikeDTO = new TopicLikeDTO();
        topicLikeDTO.setUserType(UserType.CLERK.getValue());
        topicLikeDTO.setTopicId(10L);
        topicLikeDTO.setOperator(2L);
        ResultDO resultDO = topicLikeService.likeTopic(topicLikeDTO);
        Logger.info(this.getClass(),"接口返回的报文："+resultDO);
    }

    /**
     * @description 商户点赞帖子
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:32
     */
    @Test
    public void testLikeTopic1(){
        TopicLikeDTO topicLikeDTO = new TopicLikeDTO();
        topicLikeDTO.setTopicId(290L);
        topicLikeDTO.setOperator(3L);
        topicLikeDTO.setUserType(UserType.CLERK.getValue());
        ResultDO resultDO = topicLikeService.likeTopic(topicLikeDTO);
        Logger.info(this.getClass(),"接口返回的报文："+resultDO);
    }

    /**
     * @description 商户取消点赞帖子
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/5/15 14:32
     */
    @Test
    public void testUnLikeTopic0(){
        TopicLikeDTO topicLikeDTO = new TopicLikeDTO();
        topicLikeDTO.setTopicId(290L);
        topicLikeDTO.setOperator(3L);
        topicLikeDTO.setUserType(UserType.CLERK.getValue());
        ResultDO resultDO = topicLikeService.unLikeTopic(topicLikeDTO);
        Logger.info(this.getClass(),"接口返回的报文："+resultDO);
    }
}
