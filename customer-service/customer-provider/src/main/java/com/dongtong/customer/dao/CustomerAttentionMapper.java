package com.dongtong.customer.dao;

import com.dongtong.customer.domain.CustomerAttention;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.dongtong.customer.dao.CustomerAttentionMapper.java
 * @Description: 用户关注表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:40
 * version v1.0.0
 */
@Repository
public interface CustomerAttentionMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:40
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 14:40
     * @param record
     * @return int
     * @throws []
     */
    int insert(CustomerAttention record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 14:40
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(CustomerAttention record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:40
     * @param id
     * @return com.dongtong.customer.domain.CustomerAttention
     * @throws []
     */
    CustomerAttention selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 14:40
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(CustomerAttention record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:40
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(CustomerAttention record);

    /**
     * @Description: 根据用户id获取关注信息
     * @Title queryFollow
     * @Author 吴开阳
     * @Date 2017/05/02 14:40
     * @param customerId
     * @return int
     * @throws []
     */
    CustomerAttention queryFollow(Long customerId);
}