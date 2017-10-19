package com.dongtong.clerk.dao;

import com.dongtong.clerk.domain.ClerkHistoryWeekRanking;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.dongtong.clerk.dao.ClerkHistoryWeekRankingMapper.java
 * @Description: 历史周排名
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:20
 * version v1.0.0
 */
@Repository
public interface ClerkHistoryWeekRankingMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:20
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 14:20
     * @param record
     * @return int
     * @throws []
     */
    int insert(ClerkHistoryWeekRanking record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 14:20
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(ClerkHistoryWeekRanking record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:20
     * @param id
     * @return com.dongtong.clerk.domain.ClerkHistoryWeekRanking
     * @throws []
     */
    ClerkHistoryWeekRanking selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 14:20
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(ClerkHistoryWeekRanking record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:20
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(ClerkHistoryWeekRanking record);
}