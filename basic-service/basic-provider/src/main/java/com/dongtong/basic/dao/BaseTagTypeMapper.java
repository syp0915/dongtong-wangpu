package com.dongtong.basic.dao;

import com.dongtong.basic.domain.BaseTagType;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.dongtong.basic.dao.BaseTagTypeMapper.java
 * @Description: 标签类型
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:10
 * version v1.0.0
 */
@Repository
public interface BaseTagTypeMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:10
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 14:10
     * @param record
     * @return int
     * @throws []
     */
    int insert(BaseTagType record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 14:10
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(BaseTagType record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:10
     * @param id
     * @return com.dongtong.basic.domain.BaseTagType
     * @throws []
     */
    BaseTagType selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 14:10
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(BaseTagType record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:10
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(BaseTagType record);
}