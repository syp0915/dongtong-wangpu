package com.dongtong.clerk.dao;

import com.dongtong.clerk.domain.ClerkRemark;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.clerk.dao.ClerkRemarkMapper.java
 * @Description: 备注信息表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author liaozm
 * @date 2017/08/08 09:46
 * version v1.0.0
 */
@Repository
public interface ClerkRemarkMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author liaozm
     * @Date 2017/08/08 09:46
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author liaozm
     * @Date 2017/08/08 09:46
     * @param record
     * @return int
     * @throws []
     */
    int insert(ClerkRemark record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author liaozm
     * @Date 2017/08/08 09:46
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(ClerkRemark record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author liaozm
     * @Date 2017/08/08 09:46
     * @param id
     * @return com.dongtong.clerk.domain.ClerkRemark
     * @throws []
     */
    ClerkRemark selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author liaozm
     * @Date 2017/08/08 09:46
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(ClerkRemark record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author liaozm
     * @Date 2017/08/08 09:46
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(ClerkRemark record);

    /**
     * @Description: 分页获取全部数据库记录
     * @Title selectByPage
     * @Author liaozm
     * @Date 2017/08/08 09:46
     * @return null
     * @throws []
     */
    List<ClerkRemark> selectByPage(Page<ClerkRemark> page);
}