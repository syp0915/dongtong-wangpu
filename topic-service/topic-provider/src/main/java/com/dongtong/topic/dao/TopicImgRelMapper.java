package com.dongtong.topic.dao;

import com.dongtong.topic.domain.TopicImgRel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.topic.dao.TopicImgRelMapper.java
 * @Description: 生意圈图片关系
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/04 16:16
 * version v1.0.0
 */
@Repository
public interface TopicImgRelMapper {
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
    int insert(TopicImgRel record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/04 16:16
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(TopicImgRel record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:16
     * @param id
     * @return com.dongtong.topic.domain.TopicImgRel
     * @throws []
     */
    TopicImgRel selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/04 16:16
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(TopicImgRel record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:16
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(TopicImgRel record);

    List<TopicImgRel> selectImageListByTopicId(Long topicId);
}