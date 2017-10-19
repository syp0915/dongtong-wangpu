package com.dongtong.app.ao;

import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.customer.dto.ScheduleTypeDTO;
import com.dongtong.customer.service.ScheduleService;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:zhoumin
 * @Description:
 * @Date:Created in 10:11 2017/8/12.
 */
@Service
public class ScheduleAO {
    @Autowired
    private ScheduleService scheduleService;

    public ResultDO<String> addScheduleByType(ScheduleTypeDTO scheduleTypeDTO){
        ResultDO<String> resultDO = new ResultDO<>();
        if (null == scheduleTypeDTO){
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }
        Long user = HttpSessionUtils.getCurrentAppUserId();
        scheduleTypeDTO.setClerkId(user);
        resultDO = scheduleService.addScheduleByType(scheduleTypeDTO);
        return resultDO;
    }
}
