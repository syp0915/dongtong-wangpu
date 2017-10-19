package com.dongtong.customer.dao;

import com.dongtong.customer.domain.CustomerFeedback;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.dongtong.customer.dao.CustomerFeedbackMapper.java
 * @Description: 建议反馈表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:42
 * version v1.0.0
 */
@Repository
public interface CustomerFeedbackMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:42
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 14:42
     * @param record
     * @return int
     * @throws []
     */
    int insert(CustomerFeedback record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 14:42
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(CustomerFeedback record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:42
     * @param id
     * @return com.dongtong.customer.domain.CustomerFeedback
     * @throws []
     */
    CustomerFeedback selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 14:42
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(CustomerFeedback record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:42
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(CustomerFeedback record);
}