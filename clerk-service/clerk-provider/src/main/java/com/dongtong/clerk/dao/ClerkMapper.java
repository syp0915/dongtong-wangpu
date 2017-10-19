package com.dongtong.clerk.dao;

import com.dongtong.clerk.domain.Clerk;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.clerk.dao.ClerkMapper.java
 * @Description: 业务员账户表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/04 16:01
 * version v1.0.0
 */
@Repository
public interface ClerkMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:01
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/04 16:01
     * @param record
     * @return int
     * @throws []
     */
    int insert(Clerk record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/04 16:01
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(Clerk record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:01
     * @param id
     * @return com.dongtong.clerk.domain.Clerk
     * @throws []
     */
    Clerk selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/04 16:01
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(Clerk record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/04 16:01
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(Clerk record);

    /**
     * 根据手机号查找业务员信息
     * @param userPhone
     * @return
     */
    Clerk getClerkInfoByUserPhone(String userPhone);

    /**
     * 查询所有业务员Id
     * @return
     */
    List<Long> getClerkId();

    /**
     * 获取所有业务员信息
     * @return
     */
    List<Clerk> getAllClerkInfo();
}