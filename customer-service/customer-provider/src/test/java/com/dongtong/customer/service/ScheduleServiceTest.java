package com.dongtong.customer.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.customer.JunitBaseTest;
import com.dongtong.customer.dto.CustomerScheduleDTO;
import com.dongtong.customer.dto.ScheduleTypeDTO;
import com.dongtong.customer.dto.resp.CalendarScheduleRespDTO;
import com.dongtong.customer.dto.resp.ScheduleDetailRespDTO;
import com.dongtong.customer.dto.resp.ScheduleListRespDTO;
import com.dongtong.customer.enums.ScheduleType;
import com.shfc.common.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/11 下午5:57.
 */
public class ScheduleServiceTest extends JunitBaseTest {
    @Autowired(required = false)
    private ScheduleService scheduleService;

    /**
     *  根据用户id获取日程列表
     * @return
     */
    @Test
    public void getScheduleListByUserId(){
        ResultDO<ScheduleListRespDTO> resultDO = scheduleService.getScheduleListByUserId(1L, 0, 1, 10);
        System.out.println("result-------------->" + JSON.toJSONString(resultDO));
    }


    /**
     * 根据日程id和用户id获取日程详情
     * @return
     */
    @Test
    public void getScheduleInfoById(){
        ResultDO<ScheduleDetailRespDTO> resultDO = scheduleService.getScheduleInfoById(3L, 3L);
        System.out.println("result-------------->" + JSON.toJSONString(resultDO));
    }

    /**
     * 获取指定用户八周日程列表
     * @return
     */
    @Test
    public void getCalendarScheduleList(){
        ResultDO<CalendarScheduleRespDTO> resultDO = scheduleService.getCalendarScheduleList(1L);
        System.out.println("result-------------->" + JSON.toJSONString(resultDO));
    }

    /**
     * 撤销日程
     * @return
     */
    @Test
    public void revokeSchedule(){
        ResultDO resultDO = scheduleService.revokeSchedule(2L, 2L);
        System.out.println("result-------------->" + JSON.toJSONString(resultDO));
    }

    /**
     * 撤销日程
     * @return
     */
    @Test
    public void ensureSchedule(){
        ResultDO resultDO = scheduleService.ensureSchedule(2L, 2L);
        System.out.println("result-------------->" + JSON.toJSONString(resultDO));
    }

    /**
     * 新增日程
     * @return
     */
    @Test
    public void addSchedule(){
        CustomerScheduleDTO customerScheduleDTO = new CustomerScheduleDTO();
        customerScheduleDTO.setClerkId(2L);
        customerScheduleDTO.setStatus(0);
        customerScheduleDTO.setBizId(1L);
        customerScheduleDTO.setProvince("上海");
        customerScheduleDTO.setCity("上海市");
        customerScheduleDTO.setDistrict("杨浦区");
        customerScheduleDTO.setAddress("武东路198号");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 2);
        customerScheduleDTO.setMeetTime(calendar.getTime());
        customerScheduleDTO.setApplicationTime(new Date());
        customerScheduleDTO.setCustomerId(2L);
        customerScheduleDTO.setType(0);
        ResultDO<Long> resultDO = scheduleService.addSchedule(customerScheduleDTO);
        System.out.println("result-------------->" + JSON.toJSONString(resultDO));
    }


    /**
     * 返回用户当天最新日程信息
     */
    @Test
    public void getNewestSchedule(){
        ResultDO<List<HashMap<String, Object>>> resultDO= scheduleService.getNewestSchedule();
        System.out.println("result-------------->" + JSON.toJSONString(resultDO));
    }

    @Test
    public void testAddScheduleByType(){
        ScheduleTypeDTO scheduleTypeDTO = new ScheduleTypeDTO();
        scheduleTypeDTO.setClerkId(106L);
        scheduleTypeDTO.setBizId(122L);
        scheduleTypeDTO.setMeetTime("2017-08-17 12:30:00");
        scheduleTypeDTO.setType(3);
//        scheduleTypeDTO.setCustomerId(1L);
//        scheduleTypeDTO.setLandlordId(1L);
        ResultDO resultDO = scheduleService.addScheduleByType(scheduleTypeDTO);
        System.out.println(resultDO.toString());
    }
}
