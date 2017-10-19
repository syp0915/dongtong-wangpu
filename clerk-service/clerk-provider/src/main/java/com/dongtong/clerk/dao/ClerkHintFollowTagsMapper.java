package com.dongtong.clerk.dao;

import com.dongtong.clerk.domain.ClerkHintFollowTags;
import java.util.List;

import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.dongtong.clerk.dao.ClerkHintFollowTagsMapper.java
 * @Description: 线索跟进标签表
 * @Company: ultimate leader
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author zhanghz
 * @date 2017/08/02 17:55
 * version v1.0.0
 */
@Repository
public interface ClerkHintFollowTagsMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zhanghz
     * @Date 2017/08/02 17:55
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zhanghz
     * @Date 2017/08/02 17:55
     * @param record
     * @return int
     * @throws []
     */
    int insert(ClerkHintFollowTags record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zhanghz
     * @Date 2017/08/02 17:55
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(ClerkHintFollowTags record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zhanghz
     * @Date 2017/08/02 17:55
     * @param id
     * @return com.dongtong.clerk.domain.ClerkHintFollowTags
     * @throws []
     */
    ClerkHintFollowTags selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zhanghz
     * @Date 2017/08/02 17:55
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(ClerkHintFollowTags record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zhanghz
     * @Date 2017/08/02 17:55
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(ClerkHintFollowTags record);

    /**
     * @Description: 分页获取全部数据库记录
     * @Title selectByPage
     * @Author zhanghz
     * @Date 2017/08/02 17:55
     * @return null
     * @throws []
     */
    List<ClerkHintFollowTags> selectByPage(Page<ClerkHintFollowTags> page);
}