package com.dongtong.topic.dao;

import com.dongtong.topic.domain.TopicLike;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.dongtong.topic.dao.TopicLikeMapper.java
 * @Description: 生意圈点赞
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/04 16:17
 * version v1.0.0
 */
@Repository
public interface TopicLikeMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:17
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/04 16:17
     * @param record
     * @return int
     * @throws []
     */
    int insert(TopicLike record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/04 16:17
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(TopicLike record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:17
     * @param id
     * @return com.dongtong.topic.domain.TopicLike
     * @throws []
     */
    TopicLike selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/04 16:17
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(TopicLike record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:17
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(TopicLike record);

    /**
     * 获取点赞数量
     * @param businessId
     * @return
     */
    int selectLikeByTopicId(Long businessId);

    /**
     * @description
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/5/15 17:04
     * @params topicLike
     * @return List<TopicLike>
     */
    Integer selectByTopicIdAndUser(TopicLike topicLike);

    /**
     * @description
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/5/15 17:04
     * @params topicLike
     * @return List<TopicLike>
     */
    Integer deleteLikeByTopicIdAndUser(TopicLike topicLike);

    TopicLike selectLikeByTopicIdAndUser(TopicLike topicLike);
}