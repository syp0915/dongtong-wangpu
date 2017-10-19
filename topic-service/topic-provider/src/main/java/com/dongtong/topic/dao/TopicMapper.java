package com.dongtong.topic.dao;

import com.dongtong.topic.domain.Topic;
import com.dongtong.topic.domain.TopicList;
import com.dongtong.topic.dto.TopicDetailDTO;
import com.dongtong.topic.dto.TopicListDTO;
import com.dongtong.topic.query.TopicDetailQuery;
import com.dongtong.topic.query.TopicListQuery;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.topic.dao.TopicMapper.java
 * @Description: 生意圈
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/04 16:16
 * version v1.0.0
 */
@Repository
public interface TopicMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:16
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/04 16:16
     * @param record
     * @return int
     * @throws []
     */
    int insert(Topic record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/04 16:16
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(Topic record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:16
     * @param id
     * @return com.dongtong.topic.domain.Topic
     * @throws []
     */
    Topic selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/04 16:16
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(Topic record);

    /**
     * @Title updateByPrimaryKeyWithBLOBs
     * @Author zm
     * @Date 2017/05/04 16:16
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeyWithBLOBs(Topic record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:16
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(Topic record);

    /**
     * @Description: 根据主键来更新记录的kind值
     * @Title updateTopicKindById
     * @Author zm
     * @Date 2017/05/10 09:16
     * @param topic
     * @return int
     * @throws []
     */
    int updateTopicKindById(Topic topic);

    /**
     * @Description: 根据主键来更新记录的is_del值(逻辑删帖)
     * @Title deleteTopicById
     * @Author zm
     * @Date 2017/05/10 09:16
     * @param topic
     * @return int
     * @throws []
     */
    int deleteTopicById(Topic topic);

    /**
     * 分页查询 根据用户ID 倒序排序
     * @param page
     * @return
     */
    List<TopicList> selectTopicListByUserId(Page<TopicListDTO> page);

    /**
     * 分页查询 根据用户ID 倒序排序
     * @param page
     * @return
     */
    List<TopicList> selectTopicList(Page<TopicListDTO> page);

    TopicDetailDTO selectTopicDetail(TopicDetailQuery topicDetailQuery);

    Topic selectByPrimaryKeyNoDel(Long id);

    /**
     * @description 
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/8/16 0016 10:29
     * @param 
     * @return 
     */
    List<TopicList> selectTopTopicList(TopicListQuery topicListQuery);

    /**
     * @description 查询7日内发布帖子，按最火排序
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/8/16 0016 10:29
     * @param 
     * @return 
     */
    List<TopicList> selectPopularTopicList(TopicListQuery topicListQuery);

    /**
     * @description 
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/8/16 0016 13:34
     * @param 
     * @return 
     */
    List<TopicList> selectNewTopicList(TopicListQuery topicListQuery);

    TopicDetailDTO selectTopicDetailByUserId(TopicDetailQuery topicDetailQuery);
}