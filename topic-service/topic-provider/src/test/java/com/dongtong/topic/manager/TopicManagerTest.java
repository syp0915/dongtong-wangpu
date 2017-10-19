package com.dongtong.topic.manager;

import com.dongtong.topic.JunitBaseTest;
import com.dongtong.topic.domain.Topic;
import com.dongtong.topic.dto.TopicDTO;
import com.dongtong.topic.enums.PubishType;
import com.dongtong.topic.enums.YesNo;
import com.shfc.common.base.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description 帖子相关Manager
 * @package com.dongtong.topic.manager
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/15 13:50
 * @version v1.0.0
 */
public class TopicManagerTest extends JunitBaseTest {

    @Autowired
    private TopicManager topicManager;

    /**
     * @description 发布帖子
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/5/15 15:20
     */
    @Test
    public void testPublishTopic(){
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setOperator(1L);   //TO-DO从token获取发布者
        topicDTO.setContent("666666");
        topicDTO.setHyperlinkUrl("");
        topicDTO.setPublishType(PubishType.NEWS.getValue());
        topicDTO.setTitle("66");
        Long id = topicManager.publishTopic(topicDTO);
        Logger.info(this.getClass(),"返回的报文："+id);
    }

    /**
     * @description 根据ID查询帖子
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/5/15 15:20
     */
    @Test
    public void testGetTopicById(){
        Long topicId = 1L;
        Topic topic = topicManager.getTopicById(topicId);
        Logger.info(this.getClass(),"返回的报文："+topic);
    }

    /**
     * @description 帖子置顶
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/5/15 15:21
     */
    @Test
    public void testStickTopic(){
        Topic topic = new Topic();
        topic.setId(13L);
        topic.setKind(YesNo.YES.getValue());
        topic.setModifier(1000L);
        Integer count = topicManager.stickTopic(topic);
        Logger.info(this.getClass(),"返回的报文："+count);
    }

    /**
     * @description 取消帖子置顶
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/5/15 15:21
     */
    @Test
    public void testUnStickTopic(){
        Topic topic = new Topic();
        topic.setId(13L);
        topic.setKind(YesNo.NO.getValue());
        topic.setModifier(1000L);
        Integer count = topicManager.unStickTopic(topic);
        Logger.info(this.getClass(),"返回的报文："+count);
    }

    /**
     * @description 根据ID删除帖子
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/5/15 15:21
     */
    @Test
    public void testDeleteTopicById(){
        Topic topic = new Topic();
        topic.setModifier(1L);
        topic.setId(1L);
        topic.setIsDel(YesNo.YES.getValue());
        Integer count = topicManager.deleteTopicById(topic);
        Logger.info(this.getClass(),"返回的报文："+count);
    }
}
