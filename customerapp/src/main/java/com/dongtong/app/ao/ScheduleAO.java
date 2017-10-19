package com.dongtong.app.ao;

import com.dongtong.app.constant.ErrorConstant;
import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.customer.dto.resp.CalendarScheduleRespDTO;
import com.dongtong.customer.dto.resp.ScheduleDetailRespDTO;
import com.dongtong.customer.dto.resp.ScheduleListRespDTO;
import com.dongtong.customer.query.*;
import com.dongtong.customer.service.ScheduleService;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/10 下午6:08.
 */
@Service
public class ScheduleAO {

    @Autowired(required = false)
    private ScheduleService scheduleService;


    public ResultDO<ScheduleListRespDTO> getScheduleListByUserId(ScheduleListReqQuery query){
        ResultDO<ScheduleListRespDTO> resultDO = new ResultDO<ScheduleListRespDTO>();
        Integer type = query.getType();
        Integer pageNumber = query.getPageNumber();
        Integer pageSize = query.getPageSize();
        Long customerId = HttpSessionUtils.getCurrentAppUserId();
        if (customerId == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        if (ValidateHelper.isEmpty(type)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        return scheduleService.getScheduleListByUserId(customerId, type, pageNumber, pageSize);
    }


    public ResultDO<ScheduleDetailRespDTO> getScheduleInfoById(ScheduleDetailReqQuery query){
        ResultDO<ScheduleDetailRespDTO> resultDO = new ResultDO<ScheduleDetailRespDTO>();
        Long scheduleId = query.getScheduleId();
        if (ValidateHelper.isEmpty(scheduleId)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Long customerId = HttpSessionUtils.getCurrentAppUserId();
        if (customerId == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        return scheduleService.getScheduleInfoById(scheduleId, customerId);
    }


    public ResultDO<CalendarScheduleRespDTO> getCalendarScheduleList(CalendarScheduleReqQuery query){
        ResultDO<CalendarScheduleRespDTO> resultDO = new ResultDO<CalendarScheduleRespDTO>();
        Long customerId = HttpSessionUtils.getCurrentAppUserId();
        if (customerId == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        return scheduleService.getCalendarScheduleList(customerId);
    }

    public ResultDO revokeSchedule(ScheduleRevokeQuery query) {
        ResultDO resultDO = new ResultDO();
        Long scheduleId = query.getScheduleId();
        if (scheduleId == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Long customerId = HttpSessionUtils.getCurrentAppUserId();
        if (customerId == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        return scheduleService.revokeSchedule(scheduleId, customerId);
    }

    public ResultDO ensureSchedule(ScheduleEnsureQuery query) {
        ResultDO resultDO = new ResultDO();
        Long scheduleId = query.getScheduleId();
        if (scheduleId == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Long customerId = HttpSessionUtils.getCurrentAppUserId();
        if (customerId == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        return scheduleService.ensureSchedule(scheduleId, customerId);
    }
}
