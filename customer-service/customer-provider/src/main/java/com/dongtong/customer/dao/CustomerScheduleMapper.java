package com.dongtong.customer.dao;

import com.dongtong.customer.domain.CustomerSchedule;
import com.shfc.mybatis.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @Package: com.dongtong.customer.dao.CustomerScheduleMapper.java
 * @Description: 日程表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author Jianguo Li
 * @date 2017/05/09 11:20
 * version v1.0.0
 */
@Repository
public interface CustomerScheduleMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author Jianguo Li
     * @Date 2017/05/09 11:20
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author Jianguo Li
     * @Date 2017/05/09 11:20
     * @param record
     * @return int
     * @throws []
     */
    int insert(CustomerSchedule record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author Jianguo Li
     * @Date 2017/05/09 11:20
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(CustomerSchedule record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author Jianguo Li
     * @Date 2017/05/09 11:20
     * @param id
     * @return com.dongtong.customer.domain.CustomerSchedule
     * @throws []
     */
    CustomerSchedule selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author Jianguo Li
     * @Date 2017/05/09 11:20
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(CustomerSchedule record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author Jianguo Li
     * @Date 2017/05/09 11:20
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(CustomerSchedule record);

    /**
     * 查询日程列表
     * @param query
     * @return
     */
    List<CustomerSchedule> getScheduleList(Page<CustomerSchedule> query);

    /**
     * 查询日历-日程
     * @param params
     * @return
     */
    List<CustomerSchedule> getScheduleListByCondition(HashMap<String, Object> params);

    /**
     * 获取指定用户未处理的有效日程数量
     * @param customerId
     * @return
     */
    Integer getUndoScheduleCount(Long customerId);


    CustomerSchedule queryScheduleByBizId(@Param("bizId") Long bizId, @Param("type") long type);



    /**
     * 查询当天最新的日程提醒
     * @return
     */
    List<HashMap<String, Object>> getNewestSchedule();

    /**
     * 是否有日程
     * @return
     */
    Integer queryScheduleByCustomerId(@Param("customerId") Long customerId);

    /**
     * 根据日程id和用户id查询日程
     * @param scheduleId
     * @param customerId
     * @return
     */
    CustomerSchedule getScheduleInfoById(@Param("scheduleId") Long scheduleId, @Param("customerId") Long customerId);

    /**
     * 过期已经过了约定时间的用户日程
     * @return
     */
    Integer expireCustomerSchedule();

    /**
     * 提前两小时日程提醒（误差10分钟）
     * @return
     */
    List<CustomerSchedule> getArrivalTimeSchedule();

    /**
     * @description 撤销相关日程
     * @package com.dongtong.customer.dao
     * @author liaozm
     * @date 2017/8/11 17:57
     * @params
     * @return
     */
    int revokeByBizId(@Param("bizId") Long bizId, @Param("type") Integer type);

    CustomerSchedule getScheduleInfo(@Param("bizId") Long hintId,@Param("type") Integer type);
}