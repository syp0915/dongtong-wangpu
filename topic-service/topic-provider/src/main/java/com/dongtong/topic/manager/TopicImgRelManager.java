package com.dongtong.topic.manager;

import com.dongtong.topic.dao.TopicImgRelMapper;
import com.dongtong.topic.domain.TopicImgRel;
import com.shfc.common.base.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version V1.0.0
 * @Package com.dongtong.topic.manager
 * @Description:
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/9 18:47
 */
@Service
public class TopicImgRelManager {

    @Autowired
    private TopicImgRelMapper topicImgRelMapper;

    /**
     * @description 插入图片
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/5/15 10:48
     * @params topicImgRel
     * @return Integer
     */
    public Integer insertImages(TopicImgRel topicImgRel){
        try {
            Integer count = topicImgRelMapper.insert(topicImgRel);
            return count;
        }catch ( Exception e ){
            Logger.error(this.getClass(), "insertImages数据库删除异常", e);
            return 0;
        }
    }
}
