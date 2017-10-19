package com.dongtong.customer.dao;

import com.dongtong.customer.domain.CustomerAttentionPlate;
import com.dongtong.customer.dto.req.AttentionPlateReqDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.shop.dao.CustomerAttentionPlateMapper.java
 * @Description: 用户关注板块表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author xiehb
 * @date 2017/08/03 20:05
 * version v1.2.0
 */
@Repository
public interface CustomerAttentionPlateMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author xiehb
     * @Date 2017/08/03 20:05
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author xiehb
     * @Date 2017/08/03 20:05
     * @param record
     * @return int
     * @throws []
     */
    int insert(CustomerAttentionPlate record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author xiehb
     * @Date 2017/08/03 20:05
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(CustomerAttentionPlate record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author xiehb
     * @Date 2017/08/03 20:05
     * @param id
     * @return com.dongtong.shop.domain.CustomerAttentionPlate
     * @throws []
     */
    CustomerAttentionPlate selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author xiehb
     * @Date 2017/08/03 20:05
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(CustomerAttentionPlate record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author xiehb
     * @Date 2017/08/03 20:05
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(CustomerAttentionPlate record);

    int batchInsert(java.util.List<CustomerAttentionPlate> list);

    int deleteByCustomerId(Long customerId);

    int batchInsertPlate(@Param("list") List<AttentionPlateReqDTO> list, @Param("customerId") Long customerId);

    List<CustomerAttentionPlate> selectPlateListByCustomer(Long customerId);
}