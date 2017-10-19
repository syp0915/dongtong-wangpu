package com.dongtong.clerk.dao;

import com.dongtong.clerk.bo.ClerkHintBO;
import com.dongtong.clerk.domain.ClerkHint;
import com.dongtong.clerk.dto.ClerkHint4MapDTO;
import com.dongtong.clerk.dto.ClerkHintDTO;
import com.dongtong.clerk.dto.ClerkHintStatisticsDTO;
import com.dongtong.clerk.dto.*;
import com.dongtong.clerk.query.ClerkHintList4MapQuery;
import com.dongtong.clerk.query.ClerkHintQuery;
import com.shfc.mybatis.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @Package: com.dongtong.clerk.dao.ClerkHintMapper.java
 * @Description: 线索表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/05 11:19
 * version v1.0.0
 */
@Repository
public interface ClerkHintMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/05 11:19
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/05 11:19
     * @param record
     * @return int
     * @throws []
     */
    int insert(ClerkHint record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/05 11:19
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(ClerkHint record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/05 11:19
     * @param id
     * @return com.dongtong.clerk.domain.ClerkHint
     * @throws []
     */
    ClerkHint selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/05 11:19
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(ClerkHint record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/05 11:19
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(ClerkHint record);

    /**
     * @Description: 分页获取全部数据库记录
     * @Title selectByPage
     * @Author zhanghz
     * @Date 2017/05/09 16:09
     * @return null
     * @throws []
     */
   // List<ClerkHint> selectByPage(Page<ClerkHint> page);
    /**
     * @Description: 分页获取全部数据库记录
     * @Title selectByPage
     * @Author lv bin
     * @Date 2017/05/09 15:33
     * @return null
     * @throws []
     * @param page
     */
    List<ClerkHintBO> selectByPage(@Param("clerkHint") ClerkHintDTO clerkHintDTO, Page page);

    /**
     * @param clerkHintDTO
     * @return
     */
    ClerkHintDTO selectById(ClerkHintDTO clerkHintDTO);

    /**
     * 查询线索详情
     * @param clerkHintQuery
     * @return
     */
    ClerkHintDTO selectClerkHintDetail(ClerkHintQuery clerkHintQuery);

    /**
     * 商铺线索列表筛选
     * @param page
     * @return
     */
    List<ClerkHintDTO> selectClerkHintList(Page<ClerkHintDTO> page);

    /**
     * 商铺地图线索列表查询
     * @param clerkHintList4MapQuery
     * @return
     */
    List<ClerkHint4MapDTO> selectClerkHint4MapList(ClerkHintList4MapQuery clerkHintList4MapQuery);

    /**
     * 待实堪列表
     * @return
     */
    List<ClerkHintBO> selectSchedule();


    /**
     * 获取过期数量
     * @param clerkHintDTO
     * @return
     */
     int getDeadTimeCount(ClerkHintDTO clerkHintDTO);

    /**
     * 获取待办列表
     * @param clerkHintDTO
     * @param page
     * @return
     */
    List<ClerkHintBO> selectNeedByPage(@Param("clerkHint") ClerkHintDTO clerkHintDTO, Page page);

    /**
     * 查找待确认、待核准线索列表
     * @param page
     * @Author zhoumin
     * @return
     */
    List<ClerkHintList> queryHintListByPage(@Param("page")Page<ClerkHintList> page);

    /**
     * 查找待确认、待核准线索详情
     * @param id
     * @Author zhoumin
     * @return
     */
    ClerkHintDetailDTO getHintDetailInfo(@Param("id")Long id);

    /**
     * 业务员约看列表v1.2
     * @param page
     * @Author zhoumin
     * @return
     */
    List<ClerkScheduleListDTO> queryViewListByPage(Page<ClerkScheduleListDTO> page);

    ClerkHintStatisticsDTO statisticsHintNum(HashMap paramMap);
    /**
     * 业务员签约列表v1.2
     * @param page
     * @Author zhoumin
     * @return
     */
    List<ClerkScheduleListDTO> querySignListByPage(Page<ClerkScheduleListDTO> page);

    /**
     * @description 约看详情查询v1.2
     * @package com.dongtong.clerk.dao
     * @author liaozm
     * @date 2017/8/8 11:23
     * @params
     * @return
     */
    ClerkSignOrViewDetailDTO queryViewDetail(Long id);

    /**
     * @description 签约详情查询v1.2
     * @package com.dongtong.clerk.dao
     * @author liaozm
     * @date 2017/8/8 11:23
     * @params
     * @return
     */
    ClerkSignOrViewDetailDTO querySignDetail(Long id);
}