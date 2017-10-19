package com.dongtong.customer.service;

import com.dongtong.customer.domain.CustomerSchedule;
import com.dongtong.customer.dto.CustomerScheduleDTO;
import com.dongtong.customer.dto.ScheduleTypeDTO;
import com.dongtong.customer.dto.resp.CalendarScheduleRespDTO;
import com.dongtong.customer.dto.resp.ScheduleDetailRespDTO;
import com.dongtong.customer.dto.resp.ScheduleListRespDTO;
import com.shfc.common.result.ResultDO;

import java.util.HashMap;
import java.util.List;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/9 上午10:52.
 */
public interface ScheduleService {

    /**
     *  根据用户id获取日程列表
     * @param userId
     * @return
     */
    public ResultDO<ScheduleListRespDTO> getScheduleListByUserId(Long userId, Integer type, Integer pageNumber, Integer pageSize);


    /**
     * 根据日程id和用户id获取日程详情
     * @param scheduleId
     * @return
     */
    public ResultDO<ScheduleDetailRespDTO> getScheduleInfoById(Long scheduleId, Long customerId);

    /**
     * 获取指定用户八周日程列表
     * @param userId
     * @return
     */
    public ResultDO<CalendarScheduleRespDTO> getCalendarScheduleList(Long userId);

    /**
     * 新增日程
     * @param customerScheduleDTO
     * @return
     */
    public ResultDO<Long> addSchedule(CustomerScheduleDTO customerScheduleDTO);

    /**
     * 取消日程
     * @return
     */
    public ResultDO revokeSchedule(Long scheduleId, Long customerId);

    /**
     * 确认日程服务
     * @param scheduleId
     * @return
     */
    public ResultDO ensureSchedule(Long scheduleId, Long customerId);

    /**
     * 根据bizId和type查询日程
     */
    ResultDO<CustomerSchedule>queryScheduleByBizId(Long bizId,long type);

    /**
     * 根据日程id 修改日程
     *
     */
    ResultDO updatScheduleStatus(CustomerSchedule customerSchedule);

    /**
     * 查询当天最新的日程提醒
     * @return
     */
    ResultDO<List<HashMap<String, Object>>> getNewestSchedule();

    /**
     * 将已经过期的用户日程状态置为已过期
     * @return
     */
    ResultDO<Integer> expireCustomerSchedule();

    /**
     * 任务到时提醒（提前两小时）
     * @return
     */
    ResultDO<List<CustomerSchedule>> getArrivalTimeSchedule();

    /**
     * 根据类型设置日程
     * @param scheduleTypeDTO
     * @Author zhoumin
     * @return
     */
    ResultDO<String> addScheduleByType(ScheduleTypeDTO scheduleTypeDTO);
}
