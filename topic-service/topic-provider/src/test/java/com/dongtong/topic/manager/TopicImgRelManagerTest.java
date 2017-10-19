package com.dongtong.topic.manager;

import com.dongtong.topic.JunitBaseTest;
import com.dongtong.topic.domain.TopicImgRel;
import com.shfc.common.base.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description 帖子图片
 * @package com.dongtong.topic.manager
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/15 14:47
 * @version v1.0.0
 */
public class TopicImgRelManagerTest extends JunitBaseTest {

    @Autowired
    private TopicImgRelManager topicImgRelManager;

    @Test
    public void testInsertImage(){
        TopicImgRel topicImgRel = new TopicImgRel();
        topicImgRel.setTopicId(0L);
        topicImgRel.setImgUrl("http://www.baidu.com");
        topicImgRel.setImgIndex(0);
        Integer count = topicImgRelManager.insertImages(topicImgRel);
        Logger.info(this.getClass(),"返回的报文："+count);
    }
}
