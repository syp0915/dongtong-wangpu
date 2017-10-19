package com.dongtong.customer.dao;

import com.dongtong.customer.domain.CustomerAttentionArea;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.customer.dao.CustomerAttentionAreaMapper.java
 * @Description: 用户关注面积表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:41
 * version v1.0.0
 */
@Repository
public interface CustomerAttentionAreaMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:41
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 14:41
     * @param record
     * @return int
     * @throws []
     */
    int insert(CustomerAttentionArea record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 14:41
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(CustomerAttentionArea record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:41
     * @param id
     * @return com.dongtong.customer.domain.CustomerAttentionArea
     * @throws []
     */
    CustomerAttentionArea selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 14:41
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(CustomerAttentionArea record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:41
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(CustomerAttentionArea record);

    /**
     * 批量插入关注面积
     * @param list
     * @param customerId;
     */
    int insertAttentionArea(@Param("list") List<Integer> list, @Param("customerId") Long customerId);
    /**
     * 删除关注面积
     * @param customerId;
     */
    int deleteAttentionByCustomerId(Long customerId);

    List<Integer> selectAreaByCustomerId(Long customerId);
}