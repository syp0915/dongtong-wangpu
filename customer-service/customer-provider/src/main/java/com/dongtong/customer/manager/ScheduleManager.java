package com.dongtong.customer.manager;

import com.dongtong.customer.dao.CustomerScheduleMapper;
import com.dongtong.customer.dao.CustomerSignMapper;
import com.dongtong.customer.dao.CustomerVisitShopMapper;
import com.dongtong.customer.domain.CustomerSchedule;
import com.dongtong.customer.domain.CustomerSign;
import com.dongtong.customer.domain.CustomerVisitShop;
import com.shfc.common.exception.AppException;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/9 上午11:24.
 */
@Service
public class ScheduleManager {

    @Autowired(required = false)
    private CustomerScheduleMapper customerScheduleMapper;

    @Autowired(required = false)
    private CustomerSignMapper customerSignMapper;

    @Autowired(required = false)
    private CustomerVisitShopMapper customerVisitShopMapper;

    /**
     * 获取日程列表
     * @param type
     * @param pageNumber
     * @param pageSize
     */
    public Page<CustomerSchedule> getScheduleList(Integer type, Long userId, Integer pageNumber, Integer pageSize){
        Page<CustomerSchedule> query = new Page<CustomerSchedule>(pageNumber, pageSize);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", type);
        params.put("userId", userId);
        query.setQuery(params);
        customerScheduleMapper.getScheduleList(query);
        return query;
    }

    public List<CustomerSchedule> getScheduleListByCondition(String startDate, String endDate, Long customerId){
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("customerId", customerId);
        return customerScheduleMapper.getScheduleListByCondition(params);
    }

    public CustomerSchedule getScheduleInfoById(Long scheduleId, Long customerId){
        return customerScheduleMapper.getScheduleInfoById(scheduleId, customerId);
    }

    public CustomerSchedule selectScheduleByPrimaryKey(Long scheduleId){
        return customerScheduleMapper.selectByPrimaryKey(scheduleId);
    }

    public CustomerSign getCustomerSignInfoById(Long customerSignId){
        return customerSignMapper.selectByPrimaryKey(customerSignId);
    }

    public CustomerVisitShop getCustomerVisitShop(Long customerVisitShopId){
        return customerVisitShopMapper.selectByPrimaryKey(customerVisitShopId);
    }

    public Integer getUndoScheduleCount(Long customerId){
        Integer count = customerScheduleMapper.getUndoScheduleCount(customerId);
        if (count == null){
            count = 0;
        }
        return count;
    }

    public Long addSchedule(CustomerSchedule customerSchedule) {
        customerScheduleMapper.insert(customerSchedule);
        return customerSchedule.getId();
    }

    public CustomerSchedule queryScheduleByBizId(Long bizId,long type){
        return customerScheduleMapper.queryScheduleByBizId(bizId,type);

    }

    @Transactional(rollbackFor = AppException.class)
    public Boolean updatScheduleStatus(CustomerSchedule customerSchedule){
         return 1==customerScheduleMapper.updateByPrimaryKey(customerSchedule);
    }

    public List<HashMap<String, Object>> getNewestSchedule(){
        return  customerScheduleMapper.getNewestSchedule();
    }

    public boolean updateByPrimaryKeySelective(CustomerSchedule customerSchedule) throws AppException{
        int result = customerScheduleMapper.updateByPrimaryKeySelective(customerSchedule);
        if(result == 1){
            return true;
        }
        throw new AppException("更新日程信息失败");
    }

    public Integer queryScheduleByCustomerId(long customerId){
        return customerScheduleMapper.queryScheduleByCustomerId(customerId);
    }

    public Integer expireCustomerSchedule() {
        return customerScheduleMapper.expireCustomerSchedule();
    }

    public List<CustomerSchedule> getArrivalTimeSchedule(){
        return  customerScheduleMapper.getArrivalTimeSchedule();
    }

    public Long addScheduleByType(CustomerSchedule schedule1){
        customerScheduleMapper.insert(schedule1);
        return schedule1.getId();
    }

    public boolean updateScheduleByType(CustomerSchedule schedule1){
        int count1  = customerScheduleMapper.updateByPrimaryKeySelective(schedule1);
        return (count1==1);
    }

    public CustomerSchedule getScheduleInfo(Long bizId,Integer type){
        return customerScheduleMapper.getScheduleInfo(bizId,type);
    }
}
