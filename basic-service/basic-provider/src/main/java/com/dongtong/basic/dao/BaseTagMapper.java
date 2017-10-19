package com.dongtong.basic.dao;

import com.dongtong.basic.domain.BaseTag;
import com.dongtong.basic.dto.TagInfoDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.basic.dao.BaseTagMapper.java
 * @Description: 标签表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:09
 * version v1.0.0
 */
@Repository
public interface BaseTagMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:09
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 14:09
     * @param record
     * @return int
     * @throws []
     */
    int insert(BaseTag record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 14:09
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(BaseTag record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:09
     * @param id
     * @return com.dongtong.basic.domain.BaseTag
     * @throws []
     */
    BaseTag selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 14:09
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(BaseTag record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:09
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(BaseTag record);
    /**
     * @Description: 根据类型获取标签
     * @Title queryTagByType
     * @Author wuky
     * @Date 2017/05/05 14:09
     * @param type
     * @return int
     * @throws []
     */
    List<TagInfoDTO> queryTagByType(@Param("type") Long type);

    /**
     * @Description: 根据标签Id获取标签
     * @Title queryTagByType
     * @Author wuky
     * @Date 2017/05/05 14:09
     * @param tagIds
     * @return int
     * @throws []
     */
    List<String> queryTagById(@Param("tagIds")List<Long> tagIds);
}