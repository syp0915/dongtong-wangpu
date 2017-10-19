package com.dongtong.basic.dao;

import com.dongtong.basic.domain.BaseConfigure;
import com.dongtong.basic.domain.BaseNotification;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.dongtong.basic.dao.BaseConfigureMapper.java
 * @Description: 配置表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author sunyaping
 * @date 2017/06/22 16:10
 * version v1.0.0
 */
@Repository
public interface BaseConfigureMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author sunyaping
     * @Date 2017/06/22 16:10
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author sunyaping
     * @Date 2017/06/22 16:10
     * @param record
     * @return int
     * @throws []
     */
    int insert(BaseConfigure record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author sunyaping
     * @Date 2017/06/22 16:10
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(BaseConfigure record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author sunyaping
     * @Date 2017/06/22 16:10
     * @param id
     * @return com.dongtong.basic.domain.BaseConfigure
     * @throws []
     */
    BaseConfigure selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author sunyaping
     * @Date 2017/06/22 16:10
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(BaseConfigure record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author sunyaping
     * @Date 2017/06/22 16:10
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(BaseConfigure record);

    /**
     * 根据code查询配置参数值
     * @param code
     * @return
     */
    BaseConfigure queryCustomerServiceTel(Long code);
}