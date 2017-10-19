package com.dongtong.basic.dao;

import com.dongtong.basic.domain.HistoryMonthRanking;
import com.dongtong.basic.dto.resp.HistoryPersonalRankingRespDTO;
import com.dongtong.basic.dto.resp.HistoryRankingListRespDTO;
import com.dongtong.basic.dto.resp.HistoryRankingRespDTO;
import com.dongtong.basic.query.HistoryRankingQuery;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.shfc.sms.dao.HistoryMonthRankingMapper.java
 * @Description: 历史月排名
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author chen xiushen
 * @date 2017/05/12 13:06
 * version v1.0.0
 */
@Repository
public interface HistoryMonthRankingMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author chen xiushen
     * @Date 2017/05/12 13:06
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author chen xiushen
     * @Date 2017/05/12 13:06
     * @param record
     * @return int
     * @throws []
     */
    int insert(HistoryMonthRanking record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author chen xiushen
     * @Date 2017/05/12 13:06
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(HistoryMonthRanking record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author chen xiushen
     * @Date 2017/05/12 13:06
     * @param id
     * @return com.shfc.sms.domain.HistoryMonthRanking
     * @throws []
     */
    HistoryMonthRanking selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author chen xiushen
     * @Date 2017/05/12 13:06
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(HistoryMonthRanking record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author chen xiushen
     * @Date 2017/05/12 13:06
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(HistoryMonthRanking record);

    /**
     * @Description: 分页获取全部数据库记录
     * @Title selectByPage
     * @Author chen xiushen
     * @Date 2017/05/12 13:06
     * @return null
     * @throws []
     */
    List<HistoryMonthRanking> selectByPage(Page<HistoryMonthRanking> page);

    /**
     * 获取榜单详细记录
     * @param page
     * @return
     */
    List<HistoryRankingListRespDTO> selectPageByTypeAndNO(Page<HistoryRankingListRespDTO> page);

    /**
     * @Description: 获取个人周排行
     * @Title selectPageByTypeAndNO
     * @Author chen xiushen
     * @Date 2017/05/12 13:06
     * @return null
     * @throws []
     */
    HistoryPersonalRankingRespDTO selectByCustomerIdAndPeriod(HistoryMonthRanking historyMonthRanking);

    /**
     * @Description: 根据周期获取榜单记录
     * @Title selectPageByTypeAndNO
     * @Author chen xiushen
     * @Date 2017/05/12 13:06
     * @return null
     * @throws []
     */
    List<HistoryRankingRespDTO> selectByPeriod(HistoryMonthRanking historyMonthRanking);
}