package com.dongtong.clerk.dao;

import com.dongtong.clerk.domain.ClerkHint;
import com.dongtong.clerk.domain.HintOverTime;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.clerk.dao.HintOverTimeMapper.java
 * @Description: 线索超时表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author zhoumin
 * @date 2017/08/12 10:42
 * version v1.0.0
 */
@Repository
public interface HintOverTimeMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zhoumin
     * @Date 2017/08/12 10:42
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zhoumin
     * @Date 2017/08/12 10:42
     * @param record
     * @return int
     * @throws []
     */
    int insert(HintOverTime record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zhoumin
     * @Date 2017/08/12 10:42
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(HintOverTime record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zhoumin
     * @Date 2017/08/12 10:42
     * @param id
     * @return com.dongtong.clerk.domain.HintOverTime
     * @throws []
     */
    HintOverTime selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zhoumin
     * @Date 2017/08/12 10:42
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(HintOverTime record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zhoumin
     * @Date 2017/08/12 10:42
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(HintOverTime record);

    /**
     * 更新已经超时的线索
     * @Author zhoumin
     * @return
     */
    int overTimeUpdate(@Param("id") Long id);

    /**
     * 查询未超时线索
     * @return
     */
    List<HintOverTime> queryHintOverTime();

    List<HintOverTime> queryPushHintOverTime();
}