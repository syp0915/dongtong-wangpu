package com.dongtong.customer.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.domain.BaseNotification;
import com.dongtong.basic.dto.req.ServiceNoticeReqDTO;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.enums.ServiceNoticeType;
import com.dongtong.basic.enums.ServiceStatus;
import com.dongtong.basic.enums.WorkServiceType;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.basic.service.NotificationService;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.domain.ClerkHint;
import com.dongtong.clerk.dto.ClerkHintDTO;
import com.dongtong.clerk.service.ClerkHintService;
import com.dongtong.clerk.service.ClerkService;
import com.dongtong.customer.constant.ErrorConstant;
import com.dongtong.customer.domain.Customer;
import com.dongtong.customer.domain.CustomerSchedule;
import com.dongtong.customer.domain.CustomerSign;
import com.dongtong.customer.domain.CustomerVisitShop;
import com.dongtong.customer.dto.CustomerScheduleDTO;
import com.dongtong.customer.dto.ScheduleTypeDTO;
import com.dongtong.customer.dto.TenantAndLandlordDTO;
import com.dongtong.customer.dto.req.SignReqDTO;
import com.dongtong.customer.dto.req.VisitShopReqDTO;
import com.dongtong.customer.dto.resp.*;
import com.dongtong.customer.enums.*;
import com.dongtong.customer.manager.CustomerSignManager;
import com.dongtong.customer.manager.CustomerVisitShopManager;
import com.dongtong.customer.manager.ScheduleManager;
import com.dongtong.shop.domain.Shop;
import com.dongtong.shop.service.ShopService;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.date.DateUtils;
import com.shfc.common.exception.AppException;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/9 上午10:55.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired(required = false)
    private ScheduleManager scheduleManager;

    @Autowired(required = false)
    private ClerkService clerkService;

    @Autowired(required = false)
    private ClerkHintService clerkHintService;

    @Autowired(required = false)
    private CustomerSignManager customerSignManager;

    @Autowired(required = false)
    private CustomerVisitShopManager customerVisitShopManager;

    @Autowired(required = false)
    private CustomerVisitShopService customerVisitShopService;

    @Autowired(required = false)
    private CustomerSignSerivce customerSignSerivce;

    @Autowired(required = false)
    private NoticePushService noticePushService;

    @Autowired(required = false)
    private ShopService shopService;

    @Autowired
    private DataSourceTransactionManager txManager;

    @Autowired(required = false)
    private CustomerService customerService;

    @Autowired(required = false)
    private NotificationService notificationService;

    /**
     * 根据用户id获取日程列表
     *
     * @param userId
     * @param pageNumber
     * @param pageSize   @return
     */
    @Override
    public ResultDO<ScheduleListRespDTO> getScheduleListByUserId(Long userId, Integer type, Integer pageNumber, Integer pageSize) {
        ResultDO<ScheduleListRespDTO> resultDO = new ResultDO<ScheduleListRespDTO>();
        if (ValidateHelper.isEmpty(userId) || ValidateHelper.isEmpty(type) || ValidateHelper.isEmpty(pageNumber) || ValidateHelper.isEmpty(pageSize)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Page<CustomerSchedule> query = scheduleManager.getScheduleList(type, userId, pageNumber, pageSize);
        List<CustomerSchedule> list = query.getData();
        ScheduleListRespDTO scheduleListRespDTO = new ScheduleListRespDTO();
        List<ScheduleInfoBean> scheduleList = new ArrayList<ScheduleInfoBean>();
        if (list != null && list.size() > 0){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (CustomerSchedule customerSchedule : list) {
                ScheduleInfoBean scheduleInfoBean = new ScheduleInfoBean();
                scheduleInfoBean.setScheduleId(customerSchedule.getId());
                scheduleInfoBean.setServiceType(customerSchedule.getType());
                scheduleInfoBean.setProvince(customerSchedule.getProvince());
                scheduleInfoBean.setCity(customerSchedule.getCity());
                scheduleInfoBean.setDistrict(customerSchedule.getDistrict());
                scheduleInfoBean.setAddress(customerSchedule.getAddress());
                if (customerSchedule.getMeetTime() != null){
                    scheduleInfoBean.setOrderTime(sdf.format(customerSchedule.getMeetTime()));
                }
                scheduleInfoBean.setStatus(customerSchedule.getStatus());
                scheduleList.add(scheduleInfoBean);
            }
            scheduleListRespDTO.setTotalCount(query.getTotalSize());
        }else {
            scheduleListRespDTO.setTotalCount(0L);
        }
        scheduleListRespDTO.setScheduleList(scheduleList);
        scheduleListRespDTO.setPageNumber(pageNumber);
        scheduleListRespDTO.setPageSize(pageSize);

        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(scheduleListRespDTO);
        return resultDO;
    }

    /**
     * 根据日程id和用户id获取日程详情
     *
     * @param scheduleId
     * @return
     */
    @Override
    public ResultDO<ScheduleDetailRespDTO> getScheduleInfoById(Long scheduleId, Long customerId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ResultDO<ScheduleDetailRespDTO> resultDO = new ResultDO<ScheduleDetailRespDTO>();
        if (ValidateHelper.isEmpty(scheduleId) || ValidateHelper.isEmpty(customerId)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        CustomerSchedule customerSchedule = scheduleManager.getScheduleInfoById(scheduleId, customerId);
        if (customerSchedule == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        Long bizId = customerSchedule.getBizId();
        if (bizId == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.DB_DATA_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.DB_DATA_EXCEPTION.getMsg());
            return resultDO;
        }
        ScheduleDetailRespDTO scheduleDetailRespDTO = new ScheduleDetailRespDTO();

        Long clerkId = customerSchedule.getClerkId();
        if (clerkId != null){
            ResultDO<Clerk> clerkResult = clerkService.getClerkInfoById(clerkId);
            if (clerkResult == null){
                resultDO.setSuccess(false);
                resultDO.setErrCode(ErrorConstant.REMOTE_SERVER_RESP_NULL.getCode());
                resultDO.setErrMsg(ErrorConstant.REMOTE_SERVER_RESP_NULL.getMsg());
                return resultDO;
            }
            if (!clerkResult.isSuccess()){
                resultDO.setSuccess(false);
                resultDO.setErrCode(clerkResult.getErrCode());
                resultDO.setErrMsg(clerkResult.getErrMsg());
                return resultDO;
            }
            Clerk clerk = clerkResult.getData();
            scheduleDetailRespDTO.setPicUrl(clerk.getHeadPortrait());
            scheduleDetailRespDTO.setClerkName(clerk.getRealName());
            scheduleDetailRespDTO.setClerkPhone(clerk.getPhone());
        }
        if (customerSchedule.getApplicationTime() != null){
            scheduleDetailRespDTO.setApplyTime(sdf.format(customerSchedule.getApplicationTime()));
        }
        scheduleDetailRespDTO.setScheduleId(customerSchedule.getId() + "");
        Integer serviceType = customerSchedule.getType();
        scheduleDetailRespDTO.setServiceType(serviceType);
        scheduleDetailRespDTO.setProvince(customerSchedule.getProvince());
        scheduleDetailRespDTO.setCity((customerSchedule.getCity()));
        scheduleDetailRespDTO.setDistrict(customerSchedule.getDistrict());
        scheduleDetailRespDTO.setAddress(customerSchedule.getAddress());
        scheduleDetailRespDTO.setBizId(customerSchedule.getBizId());
        if (customerSchedule.getMeetTime() != null){
            scheduleDetailRespDTO.setOrderTime(sdf.format(customerSchedule.getMeetTime()));
        }
        if (customerSchedule.getCompleteTime() != null){
            scheduleDetailRespDTO.setCompleteTime(sdf.format(customerSchedule.getCompleteTime()));
        }
        BaseNotification baseNotification = new BaseNotification();
        baseNotification.setBizId(customerSchedule.getId());
        baseNotification.setNotifyType(0);
        baseNotification.setReceiverId(customerSchedule.getCustomerId());
        baseNotification.setReceiverType(ReceiveType.CUSTOMER.getValue());
        scheduleDetailRespDTO.setStatus(customerSchedule.getStatus());
        //类型：1-旺铺寻租(线索)2-旺铺寻租(实勘)3-预约看铺(租客) 4-租客看铺(房东) 5-签约租铺(租客) 6-租客签约(房东)
        if (serviceType == 1 || serviceType == 2){//寻租  1-旺铺寻租(线索)2-旺铺寻租(实勘)
            ResultDO<ClerkHint> hintResult = clerkHintService.getClerkHintInfoById(bizId);
            if (hintResult != null && hintResult.isSuccess()){
                ResultDO<Shop> shopResultDO = shopService.getShopByHitId(hintResult.getData().getId());
                if(shopResultDO.isSuccess()){
                    if(!ValidateHelper.isEmpty(shopResultDO.getData())){
                        scheduleDetailRespDTO.setShopId(shopResultDO.getData().getId());
                    }
                }
                ClerkHint clerkHint = hintResult.getData();
                scheduleDetailRespDTO.setContactName(clerkHint.getLinkmanName());
                scheduleDetailRespDTO.setContactPhone(clerkHint.getLinkmanPhone());
            }
            baseNotification.setServiceType(1);
        }else if (serviceType == 3 || serviceType == 4){//预约
            CustomerVisitShop customerVisitShop = scheduleManager.getCustomerVisitShop(bizId);
            if (customerVisitShop != null){
                scheduleDetailRespDTO.setContactName(customerVisitShop.getLinkmanName());
                scheduleDetailRespDTO.setContactPhone(customerVisitShop.getLinkmanPhone());
                scheduleDetailRespDTO.setShopId(customerVisitShop.getShopId());
            }
            if(serviceType == 3){
                baseNotification.setServiceType(0);
            }
            if(serviceType == 4){
                baseNotification.setServiceType(5);
            }
        }else if (serviceType == 5 || serviceType == 6){//签约
            CustomerSign customerSign = scheduleManager.getCustomerSignInfoById(bizId);
            if (customerSign != null){
                scheduleDetailRespDTO.setContactName(customerSign.getContacter());
                scheduleDetailRespDTO.setContactPhone(customerSign.getContactMobile());
                scheduleDetailRespDTO.setShopId(customerSign.getShopId());
            }
            if(serviceType == 5){
                baseNotification.setServiceType(2);
            }
            if(serviceType == 6){
                baseNotification.setServiceType(6);
            }
        }
        notificationService.updateByBusId(baseNotification);

        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(scheduleDetailRespDTO);
        return resultDO;
    }

    /**
     * 获取指定用户八周日程列表
     *
     * @param customerId
     * @return
     */
    @Override
    public ResultDO<CalendarScheduleRespDTO> getCalendarScheduleList(Long customerId) {
        ResultDO<CalendarScheduleRespDTO> resultDO = new ResultDO<CalendarScheduleRespDTO>();
        SimpleDateFormat sdf8 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf14 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (ValidateHelper.isEmpty(customerId)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }

        CalendarScheduleRespDTO calendarScheduleRespDTO = new CalendarScheduleRespDTO();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, -dayOfWeek + 1);//星期一为一周的起始，需要做参数调整
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DATE, 56);
        Date endDate = calendar.getTime();
        List<DateScheduleBean> dateList = new ArrayList<DateScheduleBean>();
        List<CustomerSchedule> list = scheduleManager.getScheduleListByCondition(sdf8.format(startDate), sdf8.format(endDate), customerId);

        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, -1);
        for (int i = 0; i < 56; i++) {
            DateScheduleBean dateScheduleBean = new DateScheduleBean();
            calendar.add(Calendar.DATE, 1);
            String dateString = sdf8.format(calendar.getTime());
            dateScheduleBean.setDateString(dateString);

            List<ScheduleInfoBean> scheduleList = new ArrayList<ScheduleInfoBean>();
            if (list != null && list.size() > 0){
                for (CustomerSchedule customerSchedule : list) {
                    if (customerSchedule.getMeetTime() != null && dateString.equals(sdf8.format(customerSchedule.getMeetTime()))){
                        ScheduleInfoBean scheduleInfoBean = new ScheduleInfoBean();
                        scheduleInfoBean.setScheduleId(customerSchedule.getId());
                        scheduleInfoBean.setServiceType(customerSchedule.getType());
                        scheduleInfoBean.setProvince(customerSchedule.getProvince());
                        scheduleInfoBean.setCity(customerSchedule.getCity());
                        scheduleInfoBean.setDistrict(customerSchedule.getDistrict());
                        scheduleInfoBean.setAddress(customerSchedule.getAddress());
                        scheduleInfoBean.setStatus(customerSchedule.getStatus());
                        scheduleInfoBean.setOrderTime(sdf14.format(customerSchedule.getMeetTime()));
                        scheduleList.add(scheduleInfoBean);
                    }
                }
            }
            dateScheduleBean.setScheduleList(scheduleList);
            dateList.add(dateScheduleBean);
        }
        calendarScheduleRespDTO.setUndoScheduleCount(scheduleManager.getUndoScheduleCount(customerId));
        calendarScheduleRespDTO.setDateList(dateList);
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(calendarScheduleRespDTO);
        return resultDO;
    }

    /**
     * 新增日程
     *
     * @param customerScheduleDTO
     * @return
     */
    @Override
    public ResultDO<Long> addSchedule(CustomerScheduleDTO customerScheduleDTO) {
        ResultDO<Long> resultDO = new ResultDO<Long>();
        if (customerScheduleDTO == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Long id = scheduleManager.addSchedule(customerScheduleDTO);
        if (id == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.DB_OPR_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.DB_OPR_EXCEPTION.getMsg());
            return resultDO;
        }
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(id);
        return resultDO;
    }

    /**
     * 取消日程
     *
     * @param scheduleId
     * @return
     */
    @Override
    public ResultDO revokeSchedule(Long scheduleId, Long customerId) {
        ResultDO resultDO = new ResultDO();
        if (scheduleId == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        CustomerSchedule customerSchedule = scheduleManager.selectScheduleByPrimaryKey(scheduleId);
        if (customerSchedule == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        if (customerSchedule.getCustomerId() != customerId){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NO_RIGHT_OPERATE.getCode());
            resultDO.setErrMsg(ErrorConstant.NO_RIGHT_OPERATE.getMsg());
            return resultDO;
        }
        customerSchedule.setStatus(2);

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);// 事物隔离级别，开启新事务
        TransactionStatus status = txManager.getTransaction(def); // 获得事务状态
        try{
            scheduleManager.updateByPrimaryKeySelective(customerSchedule);
        }catch(AppException e){
            Logger.error(this,e.getMessage(),e);
            txManager.rollback(status);
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.UPDATE_SCHEDULE_ERROR.getCode());
            resultDO.setErrMsg(ErrorConstant.UPDATE_VITIST_TIME_ERROR.getMsg());
            return resultDO;
        }
        if (customerSchedule.getType() == 0){//旺铺寻租
            ClerkHintDTO clerkHintDTO = new ClerkHintDTO();
            clerkHintDTO.setId(customerSchedule.getBizId());
            clerkHintDTO.setStatus(3);//置为已废弃
            ResultDO clerkHindDO = clerkHintService.updateClerkHintStatus(clerkHintDTO);
            if(!clerkHindDO.isSuccess()){
                txManager.rollback(status);
            }else{
                txManager.commit(status);
            }
            return clerkHindDO;
        }else if (customerSchedule.getType() == 1){//约看
            VisitShopReqDTO visitShopReqDTO = new VisitShopReqDTO();
            visitShopReqDTO.setId(customerSchedule.getBizId());
            visitShopReqDTO.setStatus(2);
            ResultDO customerDO = customerVisitShopManager.updateMeetStatus(visitShopReqDTO);
            if(!customerDO.isSuccess()){
                txManager.rollback(status);
            }else{
                txManager.commit(status);
            }
            return customerDO;
        }else if (customerSchedule.getType() == 2){//撤销签约
            SignReqDTO signReqDTO = new SignReqDTO();
            signReqDTO.setId(customerSchedule.getBizId());
            signReqDTO.setStatus(9);//置为已取消
            ResultDO customerSignDO = customerSignManager.updateSignedStatus(signReqDTO);
            if(!customerSignDO.isSuccess()){
                txManager.rollback(status);
            }else{
                txManager.commit(status);
            }
            return customerSignDO;
        }else {
            txManager.rollback(status);
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.SCHEDULE_STATUS_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.SCHEDULE_STATUS_EXCEPTION.getMsg());
            return resultDO;
        }
    }

    /**
     * 确认日程服务
     *
     * @param scheduleId
     * @return
     */
    @Override
    public ResultDO ensureSchedule(Long scheduleId, Long customerId) {
        ResultDO resultDO = new ResultDO();
        if (scheduleId == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        CustomerSchedule customerSchedule = scheduleManager.selectScheduleByPrimaryKey(scheduleId);
        if (customerSchedule == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        if (customerSchedule.getCustomerId().longValue() != customerId.longValue()){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NO_RIGHT_OPERATE.getCode());
            resultDO.setErrMsg(ErrorConstant.NO_RIGHT_OPERATE.getMsg());
            return resultDO;
        }
        customerSchedule.setStatus(1);
        customerSchedule.setCompleteTime(new Date());

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);// 事物隔离级别，开启新事务
        TransactionStatus status = txManager.getTransaction(def); // 获得事务状态

        try{
            scheduleManager.updateByPrimaryKeySelective(customerSchedule);
        }catch (AppException e){
            Logger.error(this,e.getMessage(),e);
            txManager.rollback(status);
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.UPDATE_SCHEDULE_ERROR.getCode());
            resultDO.setErrMsg(ErrorConstant.UPDATE_VITIST_TIME_ERROR.getMsg());
            return resultDO;
        }
        ResultDO<Clerk> clerkResultDO=clerkService.getClerkInfoById(customerSchedule.getClerkId());
        //当前业务员信息
        Clerk clerk=clerkResultDO.getData();
        if (customerSchedule.getType() == 0){//旺铺寻租
            ClerkHintDTO clerkHintDTO = new ClerkHintDTO();
            clerkHintDTO.setId(customerSchedule.getBizId());
            clerkHintDTO.setStatus(2);//置为已收铺
            resultDO = clerkHintService.updateClerkHintStatus(clerkHintDTO);
            if(resultDO.isSuccess()){
                txManager.commit(status);
            }else{
                txManager.rollback(status);
            }
            return resultDO;
        }else if (customerSchedule.getType() == 1){//约看
            VisitShopReqDTO visitShopReqDTO = new VisitShopReqDTO();
            visitShopReqDTO.setId(customerSchedule.getBizId());
            visitShopReqDTO.setStatus(VisitStatus.VISITED.getValue());//置为已踩盘
            resultDO=customerVisitShopManager.updateMeetStatus(visitShopReqDTO);
            if(resultDO.isSuccess()){
                this.pushTrumpetMessageToAllClerk(WorkServiceType.ORDER_SEE.getValue(), clerk.getRealName());
                txManager.commit(status);
            }else{
                txManager.rollback(status);
            }
            return resultDO;
        }else if (customerSchedule.getType() == 2){//签约完成
            SignReqDTO signReqDTO = new SignReqDTO();
            signReqDTO.setId(customerSchedule.getBizId());
            signReqDTO.setStatus(1);//置为一已签约
            resultDO=customerSignManager.updateSignedStatus(signReqDTO);
            if(resultDO.isSuccess()){
                this.pushTrumpetMessageToAllClerk(WorkServiceType.SIGN_CONTRACT.getValue(), clerk.getRealName());
                txManager.commit(status);
            }else{
                txManager.rollback(status);
            }
            return  resultDO;
        }else {
            txManager.rollback(status);
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.SCHEDULE_STATUS_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.SCHEDULE_STATUS_EXCEPTION.getMsg());
            return resultDO;
        }
    }

    /**
     * 异步推送小喇叭消息给所有业务员，推送失败不能影响主线业务
     * @param messageType
     * @param clerkName
     */
    @Async
    private void pushTrumpetMessageToAllClerk(final Integer messageType, final String clerkName){
        try {
            //所有业务员列表
            ResultDO<List<Clerk>> clerkListResult = clerkService.getAllClerkInfo();
            if (!clerkListResult.isSuccess()){
                return;
            }
            List<Clerk> clerkList = clerkListResult.getData();
            if (clerkList == null || clerkList.size() < 0){
                return;
            }
            for (final Clerk clerk : clerkList) {
                new Runnable(){

                    /**
                     * When an object implementing interface <code>Runnable</code> is used
                     * to create a thread, starting the thread causes the object's
                     * <code>run</code> method to be called in that separately executing
                     * thread.
                     * <p>
                     * The general contract of the method <code>run</code> is that it may
                     * take any action whatsoever.
                     *
                     * @see Thread#run()
                     */
                    @Override
                    public void run() {
                        ResultDO<Boolean> pushResult = noticePushService.pushTrumpetNotice(clerk.getDeviceId(), clerk.getOsType(), messageType, clerkName, "");
                        if (!pushResult.isSuccess()) {
                            Logger.error(ScheduleServiceImpl.class, "推送小喇叭消息给业务员：" + JSON.toJSONString(clerk) + "失败，失败原因：" + pushResult.getErrCode() + "-" + pushResult.getErrMsg());
                        }
                    }
                }.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }


    /**
     * 返回用户当天最新日程信息
     * @return
     */
    @Override
    public ResultDO<List<HashMap<String, Object>>> getNewestSchedule() {
        ResultDO<List<HashMap<String, Object>>> resultDO=new ResultDO<>();
        List<HashMap<String, Object>> hashMap=scheduleManager.getNewestSchedule();
        resultDO.setSuccess(true);
        resultDO.setData(hashMap);
        return resultDO;
    }

    /**
     * 将已经过期的用户日程状态置为已过期
     *
     * @return
     */
    @Override
    public ResultDO<Integer> expireCustomerSchedule() {
        ResultDO<Integer> resultDO = new ResultDO<Integer>();
        try {
            resultDO.setSuccess(true);
            resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
            resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
            resultDO.setData(scheduleManager.expireCustomerSchedule());
        }catch (Exception e){
            e.printStackTrace();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.DB_OPR_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.DB_DATA_EXCEPTION.getMsg());
            return resultDO;
        }
        return resultDO;
    }

    @Override
    public ResultDO<CustomerSchedule> queryScheduleByBizId(Long bizId, long type) {
        ResultDO<CustomerSchedule> res=new ResultDO();
        CustomerSchedule customerSchedule=   scheduleManager.queryScheduleByBizId(bizId,type);
        res.setData(customerSchedule);
        res.setSuccess(true);
        return res;
    }

    @Override
    public ResultDO updatScheduleStatus(CustomerSchedule customerSchedule) {
        ResultDO resultDO=new ResultDO();
           if (scheduleManager.updatScheduleStatus(customerSchedule)){
               resultDO.setSuccess(true);
           }
        return resultDO;
    }

    @Override
    public ResultDO<List<CustomerSchedule>> getArrivalTimeSchedule() {
        ResultDO<List<CustomerSchedule>> res=new ResultDO();
        List<CustomerSchedule> customerSchedules=scheduleManager.getArrivalTimeSchedule();
        res.setData(customerSchedules);
        return res;
    }

    /**
     * 设置日程
     * @param typeDTO
     * @return
     */
    @Override
    public ResultDO<String> addScheduleByType(ScheduleTypeDTO typeDTO) {
        ResultDO<String> resultDO = new ResultDO<>();
        String check  = checkScheduleTypeDTO(typeDTO);
        if(check != null){
            resultDO.setErrMsg(check);
            return resultDO;
        }
        try {
            //时间转化成Date类型
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date meetTime = sdf.parse(typeDTO.getMeetTime());
            //根据业务员id查询业务员信息
            ResultDO<Clerk> clerkResultDO =  clerkService.getClerkInfoById(typeDTO.getClerkId());
            if (!clerkResultDO.isSuccess()){
                resultDO.setErrMsg("业务员不存在");
                return resultDO;
            }
            Clerk clerk = clerkResultDO.getData();

            //根据type查找对应信息
            Integer type = typeDTO.getType();
            if (type == 1 || type == 2){//线索id
                ResultDO<ClerkHint> clerkHintResultDO = clerkHintService.getClerkHintInfoById(typeDTO.getBizId());
                final ClerkHint clerkHint = clerkHintResultDO.getData();
                if (null == clerkHint){
                    resultDO.setErrMsg("线索不存在！");
                    return resultDO;
                }
                if (clerkHint.getStatus()==ClerkHintStatus.SHOP_TRANSFORM.getValue()
                        || clerkHint.getStatus()==ClerkHintStatus.ABANDON.getValue()
                        || clerkHint.getStatus()==ClerkHintStatus.CANCEL.getValue()){
                    resultDO.setErrMsg("该线索状态不支持设置日程");
                    return resultDO;
                }
                CustomerSchedule customerSchedule = new CustomerSchedule();//日程

                //判断线索状态
                if (ClerkHintStatus.EXPAND_CLERK_SURE.getValue() != clerkHint.getStatus() && ClerkHintStatus.AWAIT_EXAMINE.getValue() != clerkHint.getStatus()){
                    resultDO.setErrMsg("线索状态不一致！");
                    return resultDO;
                }
                if (ClerkHintStatus.EXPAND_CLERK_SURE.getValue() == clerkHint.getStatus()){//拓铺员待核准
                    customerSchedule.setType(ScheduleType.SHOP_FOR_CHECK.getValue());
                }
                if (ClerkHintStatus.AWAIT_EXAMINE.getValue() == clerkHint.getStatus()){//交易员待实勘
                    customerSchedule.setType(ScheduleType.SHOP_FOR_SURVEY.getValue());
                }
                customerSchedule.setMeetTime(meetTime);

                //判断日程是否存在
                CustomerSchedule scheduleInfo = scheduleManager.getScheduleInfo(typeDTO.getBizId(),customerSchedule.getType());
                if (null != scheduleInfo){
                    //更新
                    customerSchedule.setId(scheduleInfo.getId());
                    customerSchedule.setModifier(typeDTO.getClerkId());//更新人
                    boolean resultSuccess = scheduleManager.updateScheduleByType(customerSchedule);
                    resultDO.setSuccess(resultSuccess);

                    //修改日程push+短信提醒
                    if (clerkHint.getIssuerType() == IssuerType.CUSTOMER.getValue()){
                        ResultDO<Customer> customerResultDO = customerService.getCustomerInfoByUserPhone(clerkHint.getIssuerPhone());
                        if (customerResultDO.isSuccess() && customerResultDO.getData() !=null){
                            final Customer customer = customerResultDO.getData();
                            final ServiceNoticeReqDTO serviceNoticeReqDTO = new ServiceNoticeReqDTO();
                            serviceNoticeReqDTO.setServiceType(ServiceNoticeType.SHOP_RENT_SEEKING.getValue());
                            serviceNoticeReqDTO.setServiceStatus(ServiceStatus.TIME_VARIATION.getValue());
                            serviceNoticeReqDTO.setServiceName(clerk.getRealName());
                            serviceNoticeReqDTO.setServiceTel(clerk.getPhone());
                            serviceNoticeReqDTO.setShopAddress(clerkHint.getShopAddress());
                            serviceNoticeReqDTO.setReceiveId(customer.getId());
                            serviceNoticeReqDTO.setOldTime(DateUtils.date2String(scheduleInfo.getMeetTime(),"yyyy-MM-dd HH:mm:ss"));
                            serviceNoticeReqDTO.setCurrentTime(DateUtils.date2String(meetTime,"yyyy-MM-dd HH:mm:ss"));
                            serviceNoticeReqDTO.setBussinessId(scheduleInfo.getId());
                            try {
                                new Thread(new Runnable(){
                                    @Override
                                    public void run() {
                                        noticePushService.pushScheduleChangeNotify(serviceNoticeReqDTO,customer.getPhone(),
                                                customer.getDeviceId(),customer.getOsType());
                                    }
                                }).start();

                            }catch (Exception e){
                                Logger.error(e, "服务日程变动提醒异常");
                            }
                        }
                    }

                }else {
                    //新增
                    customerSchedule.setClerkId(typeDTO.getClerkId());//业务员id
                    customerSchedule.setBizId(typeDTO.getBizId());//业务id
                    customerSchedule.setApplicationTime(meetTime);
                    if (clerkHint.getHintFrom()== HintFrom.CUSTOMER.getValue()){
                        customerSchedule.setCustomerId(clerkHint.getIssuerId());
                    }
                    customerSchedule.setCreater(typeDTO.getClerkId());//创建人
                    customerSchedule.setAddress(clerkHint.getShopAddress());
                    customerSchedule.setStatus(ScheduleStatus.ACCEPTING.getValue());
                    Long id = scheduleManager.addScheduleByType(customerSchedule);
                    resultDO.setSuccess(true);

                    //新增日程push+短信提醒
                    if (clerkHint.getIssuerType() == IssuerType.CUSTOMER.getValue()){
                        ResultDO<Customer> customerResultDO = customerService.getCustomerInfoByUserPhone(clerkHint.getIssuerPhone());
                        if (customerResultDO.isSuccess() && customerResultDO.getData() !=null){
                            final Customer customer = customerResultDO.getData();
                            final ServiceNoticeReqDTO serviceNoticeReqDTO = new ServiceNoticeReqDTO();
                            serviceNoticeReqDTO.setServiceType(ServiceNoticeType.SHOP_RENT_SEEKING.getValue());
                            serviceNoticeReqDTO.setServiceStatus(ServiceStatus.SCHEDULE_CREAT.getValue());
                            serviceNoticeReqDTO.setServiceName(clerk.getRealName());
                            serviceNoticeReqDTO.setServiceTel(clerk.getPhone());
                            serviceNoticeReqDTO.setReceiveId(customer.getId());
                            serviceNoticeReqDTO.setShopAddress(clerkHint.getShopAddress());
                            serviceNoticeReqDTO.setCurrentTime(DateUtils.date2String(meetTime,"yyyy-MM-dd HH:mm:ss"));
                            serviceNoticeReqDTO.setBussinessId(id);
                            try {
                                new Thread(new Runnable(){
                                    @Override
                                    public void run() {
                                        noticePushService.pushScheduleAddNotify(serviceNoticeReqDTO,customer.getPhone(),
                                                customer.getDeviceId(),customer.getOsType());
                                    }
                                }).start();

                            }catch (Exception e){
                                Logger.error(e, "服务日程创建提醒异常");
                            }
                        }
                    }

                }

            }else if (type == 3){//约看
                ResultDO<CustomerVisitShop> visitShopResultDO = customerVisitShopService.getCustomerVisitInfoById(typeDTO.getBizId());
                CustomerVisitShop customerVisitShop = visitShopResultDO.getData();
                if (null == customerVisitShop){
                    resultDO.setErrMsg("约看不存在");
                    return resultDO;
                }
                if (customerVisitShop.getStatus()!=VisitStatus.VISITING.getValue()){
                    resultDO.setErrMsg("该约看状态不支持设置日程");
                    return resultDO;
                }
                ResultDO<Shop> shopResultDO = shopService.getShopById(customerVisitShop.getShopId());
                Shop shop = shopResultDO.getData();
                if (null == shop){
                    resultDO.setErrMsg("商铺不存在");
                    return resultDO;
                }
                //查询房东和租客id
                final TenantAndLandlordDTO tenantAndLandlordDTO = customerVisitShopManager.getTenantAndLandlord(typeDTO.getBizId());
                if (null == tenantAndLandlordDTO){
                    resultDO.setErrMsg("无对应的房东租客信息");
                    return resultDO;
                }

                CustomerSchedule scheduleTenant = new CustomerSchedule();//租客
                CustomerSchedule scheduleLandlord = new CustomerSchedule();//房东

                //判断日程是否存在
                boolean resultSuccess1 = false;
                boolean resultSuccess2 = false;
                CustomerSchedule scheduleInfoTenant = scheduleManager.getScheduleInfo(typeDTO.getBizId(),ScheduleType.VISIT_TENANT.getValue());
                CustomerSchedule scheduleInfoLandlord = scheduleManager.getScheduleInfo(typeDTO.getBizId(),ScheduleType.VISIT_LANDLORD.getValue());

                if (null != scheduleInfoTenant){
                    //更新
                    scheduleTenant.setId(scheduleInfoTenant.getId());
                    scheduleTenant.setMeetTime(meetTime);//约见时间
                    scheduleTenant.setModifier(typeDTO.getClerkId());//更新人
                    resultSuccess1 = scheduleManager.updateScheduleByType(scheduleTenant);
                    //修改日程push+短信提醒
                    if(null != tenantAndLandlordDTO.getTenantDTO()){
                        final ServiceNoticeReqDTO serviceNoticeReqDTO = new ServiceNoticeReqDTO();
                        serviceNoticeReqDTO.setServiceType(ServiceNoticeType.APPOINT_MENTSHOP.getValue());
                        serviceNoticeReqDTO.setServiceStatus(ServiceStatus.TIME_VARIATION.getValue());
                        serviceNoticeReqDTO.setServiceName(clerk.getRealName());
                        serviceNoticeReqDTO.setServiceTel(clerk.getPhone());
                        serviceNoticeReqDTO.setReceiveId(tenantAndLandlordDTO.getTenantId());
                        serviceNoticeReqDTO.setShopAddress(shop.getAddress());
                        serviceNoticeReqDTO.setOldTime(DateUtils.date2String(scheduleInfoTenant.getMeetTime(),"yyyy-MM-dd HH:mm:ss"));
                        serviceNoticeReqDTO.setCurrentTime(DateUtils.date2String(meetTime,"yyyy-MM-dd HH:mm:ss"));
                        serviceNoticeReqDTO.setBussinessId(scheduleInfoTenant.getId());

                        try {
                            new Thread(new Runnable(){
                                @Override
                                public void run() {
                                    noticePushService.pushScheduleChangeNotify(serviceNoticeReqDTO,tenantAndLandlordDTO.getTenantDTO().getTenantPhone(),
                                            tenantAndLandlordDTO.getTenantDTO().getTenantDeviceId(),tenantAndLandlordDTO.getTenantDTO().getTenantOsType());
                                }
                            }).start();

                        }catch (Exception e){
                            Logger.error(e, "服务日程变动提醒异常");
                        }
                    }

                }else {
                    //新增
                    scheduleTenant.setClerkId(typeDTO.getClerkId());//业务员id
                    scheduleTenant.setBizId(typeDTO.getBizId());
                    scheduleTenant.setCreater(typeDTO.getClerkId());//创建人
                    scheduleTenant.setType(ScheduleType.VISIT_TENANT.getValue());//预约看铺(租客)
                    scheduleTenant.setCustomerId(tenantAndLandlordDTO.getTenantId());//客户id
                    scheduleTenant.setApplicationTime(meetTime);//申请时间
                    scheduleTenant.setMeetTime(meetTime);//约见时间
                    scheduleTenant.setAddress(shop.getAddress());//地址
                    scheduleTenant.setStatus(ScheduleStatus.ACCEPTING.getValue());//状态
                    Long id = scheduleManager.addScheduleByType(scheduleTenant);
                    resultSuccess1 = true;
                    //新增日程push+短信提醒
                    if(null != tenantAndLandlordDTO.getTenantDTO()){
                        final ServiceNoticeReqDTO serviceNoticeReqDTO = new ServiceNoticeReqDTO();
                        serviceNoticeReqDTO.setServiceType(ServiceNoticeType.APPOINT_MENTSHOP.getValue());
                        serviceNoticeReqDTO.setServiceStatus(ServiceStatus.SCHEDULE_CREAT.getValue());
                        serviceNoticeReqDTO.setServiceName(clerk.getRealName());
                        serviceNoticeReqDTO.setServiceTel(clerk.getPhone());
                        serviceNoticeReqDTO.setReceiveId(tenantAndLandlordDTO.getTenantId());
                        serviceNoticeReqDTO.setShopAddress(shop.getAddress());
                        serviceNoticeReqDTO.setCurrentTime(DateUtils.date2String(meetTime,"yyyy-MM-dd HH:mm:ss"));
                        serviceNoticeReqDTO.setBussinessId(id);
                        try {
                            new Thread(new Runnable(){
                                @Override
                                public void run() {
                                    noticePushService.pushScheduleAddNotify(serviceNoticeReqDTO,tenantAndLandlordDTO.getTenantDTO().getTenantPhone(),
                                            tenantAndLandlordDTO.getTenantDTO().getTenantDeviceId(),tenantAndLandlordDTO.getTenantDTO().getTenantOsType());
                                }
                            }).start();

                        }catch (Exception e){
                            Logger.error(e, "服务日程新建提醒异常");
                        }
                    }

                }
                if (null != scheduleInfoLandlord){
                    //更新
                    scheduleLandlord.setId(scheduleInfoLandlord.getId());
                    scheduleLandlord.setMeetTime(meetTime);
                    scheduleLandlord.setModifier(typeDTO.getClerkId());//更新人
                    resultSuccess2 = scheduleManager.updateScheduleByType(scheduleLandlord);
                    //修改日程push+短信提醒
                    if(null != tenantAndLandlordDTO.getLandlordDTO()){
                        final ServiceNoticeReqDTO serviceNoticeReqDTO = new ServiceNoticeReqDTO();
                        serviceNoticeReqDTO.setServiceType(ServiceNoticeType.RENTER_MENTSHOP.getValue());
                        serviceNoticeReqDTO.setServiceStatus(ServiceStatus.TIME_VARIATION.getValue());
                        serviceNoticeReqDTO.setServiceName(clerk.getRealName());
                        serviceNoticeReqDTO.setServiceTel(clerk.getPhone());
                        serviceNoticeReqDTO.setReceiveId(tenantAndLandlordDTO.getLandlordId());
                        serviceNoticeReqDTO.setShopAddress(shop.getAddress());
                        serviceNoticeReqDTO.setOldTime(DateUtils.date2String(scheduleInfoLandlord.getMeetTime(),"yyyy-MM-dd HH:mm:ss"));
                        serviceNoticeReqDTO.setCurrentTime(DateUtils.date2String(meetTime,"yyyy-MM-dd HH:mm:ss"));
                        serviceNoticeReqDTO.setBussinessId(scheduleInfoLandlord.getId());
                        try {
                            new Thread(new Runnable(){
                                @Override
                                public void run() {
                                    noticePushService.pushScheduleChangeNotify(serviceNoticeReqDTO,tenantAndLandlordDTO.getLandlordDTO().getLandlordPhone(),
                                            tenantAndLandlordDTO.getLandlordDTO().getLandlordDeviceId(),tenantAndLandlordDTO.getLandlordDTO().getLandlordOsType());
                                }
                            }).start();

                        }catch (Exception e){
                            Logger.error(e, "服务日程变动提醒异常");
                        }
                    }

                }else {
                    //新增
                    scheduleLandlord.setClerkId(typeDTO.getClerkId());
                    scheduleLandlord.setBizId(typeDTO.getBizId());
                    scheduleLandlord.setCreater(typeDTO.getClerkId());
                    scheduleLandlord.setType(ScheduleType.VISIT_LANDLORD.getValue());//租客看铺(房东)
                    scheduleLandlord.setCustomerId(tenantAndLandlordDTO.getLandlordId());//房东id
                    scheduleLandlord.setApplicationTime(meetTime);
                    scheduleLandlord.setMeetTime(meetTime);
                    scheduleLandlord.setAddress(shop.getAddress());
                    scheduleLandlord.setStatus(ScheduleStatus.ACCEPTING.getValue());
                    Long id = scheduleManager.addScheduleByType(scheduleLandlord);
                    resultSuccess2 = true;

                    //新增日程push+短信提醒
                    if(null != tenantAndLandlordDTO.getLandlordDTO()){
                        final ServiceNoticeReqDTO serviceNoticeReqDTO = new ServiceNoticeReqDTO();
                        serviceNoticeReqDTO.setServiceType(ServiceNoticeType.RENTER_MENTSHOP.getValue());
                        serviceNoticeReqDTO.setServiceStatus(ServiceStatus.SCHEDULE_CREAT.getValue());
                        serviceNoticeReqDTO.setServiceName(clerk.getRealName());
                        serviceNoticeReqDTO.setServiceTel(clerk.getPhone());
                        serviceNoticeReqDTO.setReceiveId(tenantAndLandlordDTO.getLandlordId());
                        serviceNoticeReqDTO.setShopAddress(shop.getAddress());
                        serviceNoticeReqDTO.setCurrentTime(DateUtils.date2String(meetTime,"yyyy-MM-dd HH:mm:ss"));
                        serviceNoticeReqDTO.setBussinessId(id);
                        try {
                            new Thread(new Runnable(){
                                @Override
                                public void run() {
                                    noticePushService.pushScheduleAddNotify(serviceNoticeReqDTO,tenantAndLandlordDTO.getLandlordDTO().getLandlordPhone(),
                                            tenantAndLandlordDTO.getLandlordDTO().getLandlordDeviceId(),tenantAndLandlordDTO.getLandlordDTO().getLandlordOsType());
                                }
                            }).start();

                        }catch (Exception e){
                            Logger.error(e, "服务日程新增提醒异常");
                        }
                    }
                }

                resultDO.setSuccess(resultSuccess1 && resultSuccess2);

            }else if (type == 4){//签约
                ResultDO<CustomerSign> signResultDO = customerSignSerivce.getCustomerSignInfoById(typeDTO.getBizId());
                CustomerSign customerSign = signResultDO.getData();
                if (null == customerSign){
                    resultDO.setErrMsg("签约不存在");
                    return resultDO;
                }
                if (customerSign.getStatus()==SignStatus.SIGN_UPLOAD.getValue()
                        ||customerSign.getStatus()==SignStatus.CANCEL.getValue()){
                    resultDO.setErrMsg("该签约状态不支持设置日程");
                    return resultDO;
                }
                ResultDO<Shop> shopResultDO = shopService.getShopById(customerSign.getShopId());
                Shop shop = shopResultDO.getData();
                if (null == shop){
                    resultDO.setErrMsg("商铺不存在");
                    return resultDO;
                }
                //查询房东和租客id
                final TenantAndLandlordDTO tenantAndLandlordDTO = customerSignManager.getTenantAndLandlord(typeDTO.getBizId());
                if (null == tenantAndLandlordDTO){
                    resultDO.setErrMsg("无对应的房东租客信息");
                    return resultDO;
                }

                //设置日程
                CustomerSchedule scheduleTenant = new CustomerSchedule();//租客
                CustomerSchedule scheduleLandlord = new CustomerSchedule();//房东

                //判断日程是否存在
                boolean resultSuccess1 = false;
                boolean resultSuccess2 = false;
                CustomerSchedule scheduleInfoTenant = scheduleManager.getScheduleInfo(typeDTO.getBizId(),ScheduleType.SIGN_TENANT.getValue());
                CustomerSchedule scheduleInfoLandlord = scheduleManager.getScheduleInfo(typeDTO.getBizId(),ScheduleType.SIGN_LANDLORD.getValue());

                if (null != scheduleInfoTenant){
                    //更新
                    scheduleTenant.setId(scheduleInfoTenant.getId());
                    scheduleTenant.setMeetTime(meetTime);//约见时间
                    scheduleTenant.setModifier(typeDTO.getClerkId());//更新人
                    resultSuccess1 = scheduleManager.updateScheduleByType(scheduleTenant);
                    //修改日程push+短信提醒
                    if(null != tenantAndLandlordDTO.getTenantDTO()){
                        final ServiceNoticeReqDTO serviceNoticeReqDTO = new ServiceNoticeReqDTO();
                        serviceNoticeReqDTO.setServiceType(ServiceNoticeType.CONTRACT_LEASING.getValue());
                        serviceNoticeReqDTO.setServiceStatus(ServiceStatus.TIME_VARIATION.getValue());
                        serviceNoticeReqDTO.setServiceName(clerk.getRealName());
                        serviceNoticeReqDTO.setServiceTel(clerk.getPhone());
                        serviceNoticeReqDTO.setReceiveId(tenantAndLandlordDTO.getTenantId());
                        serviceNoticeReqDTO.setShopAddress(shop.getAddress());
                        serviceNoticeReqDTO.setOldTime(DateUtils.date2String(scheduleInfoTenant.getMeetTime(),"yyyy-MM-dd HH:mm:ss"));
                        serviceNoticeReqDTO.setCurrentTime(DateUtils.date2String(scheduleInfoTenant.getMeetTime(),"yyyy-MM-dd HH:mm:ss"));
                        serviceNoticeReqDTO.setBussinessId(scheduleInfoTenant.getId());
                        try {
                            new Thread(new Runnable(){
                                @Override
                                public void run() {
                                    noticePushService.pushScheduleChangeNotify(serviceNoticeReqDTO,tenantAndLandlordDTO.getTenantDTO().getTenantPhone(),
                                            tenantAndLandlordDTO.getTenantDTO().getTenantDeviceId(),tenantAndLandlordDTO.getTenantDTO().getTenantOsType());
                                }
                            }).start();

                        }catch (Exception e){
                            Logger.error(e, "服务日程变动提醒异常");
                        }
                    }


                }else {
                    //新增
                    scheduleTenant.setClerkId(typeDTO.getClerkId());//业务员id
                    scheduleTenant.setBizId(typeDTO.getBizId());
                    scheduleTenant.setCreater(typeDTO.getClerkId());//创建人
                    scheduleTenant.setType(ScheduleType.SIGN_TENANT.getValue());//签约租铺(租客)
                    scheduleTenant.setCustomerId(tenantAndLandlordDTO.getTenantId());//客户id
                    scheduleTenant.setApplicationTime(meetTime);//申请时间
                    scheduleTenant.setMeetTime(meetTime);//约见时间
                    scheduleTenant.setAddress(customerSign.getShopAddress());//地址
                    scheduleTenant.setStatus(ScheduleStatus.ACCEPTING.getValue());//状态
                    Long id = scheduleManager.addScheduleByType(scheduleTenant);
                    resultSuccess1 = true;
                    //新增日程push+短信提醒
                    if(null != tenantAndLandlordDTO.getTenantDTO()){
                        final ServiceNoticeReqDTO serviceNoticeReqDTO = new ServiceNoticeReqDTO();
                        serviceNoticeReqDTO.setServiceType(ServiceNoticeType.CONTRACT_LEASING.getValue());
                        serviceNoticeReqDTO.setServiceStatus(ServiceStatus.SCHEDULE_CREAT.getValue());
                        serviceNoticeReqDTO.setServiceName(clerk.getRealName());
                        serviceNoticeReqDTO.setServiceTel(clerk.getPhone());
                        serviceNoticeReqDTO.setReceiveId(tenantAndLandlordDTO.getTenantId());
                        serviceNoticeReqDTO.setShopAddress(shop.getAddress());
                        serviceNoticeReqDTO.setCurrentTime(DateUtils.date2String(meetTime,"yyyy-MM-dd HH:mm:ss"));
                        serviceNoticeReqDTO.setBussinessId(id);
                        try {
                            new Thread(new Runnable(){
                                @Override
                                public void run() {
                                    noticePushService.pushScheduleAddNotify(serviceNoticeReqDTO,tenantAndLandlordDTO.getTenantDTO().getTenantPhone(),
                                            tenantAndLandlordDTO.getTenantDTO().getTenantDeviceId(),tenantAndLandlordDTO.getTenantDTO().getTenantOsType());
                                }
                            }).start();

                        }catch (Exception e){
                            Logger.error(e, "服务日程新建提醒异常");
                        }
                    }


                }
                if (null != scheduleInfoLandlord){
                    //更新
                    scheduleLandlord.setId(scheduleInfoLandlord.getId());
                    scheduleLandlord.setMeetTime(meetTime);
                    scheduleLandlord.setModifier(typeDTO.getClerkId());//更新人
                    resultSuccess2 = scheduleManager.updateScheduleByType(scheduleLandlord);
                    //修改日程push+短信提醒
                    if(null != tenantAndLandlordDTO.getLandlordDTO()){
                        final ServiceNoticeReqDTO serviceNoticeReqDTO = new ServiceNoticeReqDTO();
                        serviceNoticeReqDTO.setServiceType(ServiceNoticeType.RENTER_SIGN.getValue());
                        serviceNoticeReqDTO.setServiceStatus(ServiceStatus.TIME_VARIATION.getValue());
                        serviceNoticeReqDTO.setServiceName(clerk.getRealName());
                        serviceNoticeReqDTO.setServiceTel(clerk.getPhone());
                        serviceNoticeReqDTO.setReceiveId(tenantAndLandlordDTO.getLandlordId());
                        serviceNoticeReqDTO.setShopAddress(shop.getAddress());
                        serviceNoticeReqDTO.setOldTime(DateUtils.date2String(scheduleInfoLandlord.getMeetTime(),"yyyy-MM-dd HH:mm:ss"));
                        serviceNoticeReqDTO.setCurrentTime(DateUtils.date2String(meetTime,"yyyy-MM-dd HH:mm:ss"));
                        serviceNoticeReqDTO.setBussinessId(scheduleInfoLandlord.getId());
                        try {
                            new Thread(new Runnable(){
                                @Override
                                public void run() {
                                    noticePushService.pushScheduleChangeNotify(serviceNoticeReqDTO,tenantAndLandlordDTO.getLandlordDTO().getLandlordPhone(),
                                            tenantAndLandlordDTO.getLandlordDTO().getLandlordDeviceId(),tenantAndLandlordDTO.getLandlordDTO().getLandlordOsType());
                                }
                            }).start();

                        }catch (Exception e){
                            Logger.error(e, "服务日程变动提醒异常");
                        }
                    }


                }else {
                    //新增
                    scheduleLandlord.setClerkId(typeDTO.getClerkId());
                    scheduleLandlord.setBizId(typeDTO.getBizId());
                    scheduleLandlord.setCreater(typeDTO.getClerkId());
                    scheduleLandlord.setType(ScheduleType.SIGN_LANDLORD.getValue());//租客签约(房东)
                    scheduleLandlord.setCustomerId(tenantAndLandlordDTO.getLandlordId());//房东id
                    scheduleLandlord.setApplicationTime(meetTime);
                    scheduleLandlord.setMeetTime(meetTime);
                    scheduleLandlord.setAddress(customerSign.getShopAddress());
                    scheduleLandlord.setStatus(ScheduleStatus.ACCEPTING.getValue());
                    Long id = scheduleManager.addScheduleByType(scheduleLandlord);
                    resultSuccess2 = true;
                    //新增日程push+短信提醒
                    if(null != tenantAndLandlordDTO.getLandlordDTO()){
                        final ServiceNoticeReqDTO serviceNoticeReqDTO = new ServiceNoticeReqDTO();
                        serviceNoticeReqDTO.setServiceType(ServiceNoticeType.RENTER_SIGN.getValue());
                        serviceNoticeReqDTO.setServiceStatus(ServiceStatus.SCHEDULE_CREAT.getValue());
                        serviceNoticeReqDTO.setServiceName(clerk.getRealName());
                        serviceNoticeReqDTO.setServiceTel(clerk.getPhone());
                        serviceNoticeReqDTO.setReceiveId(tenantAndLandlordDTO.getLandlordId());
                        serviceNoticeReqDTO.setShopAddress(shop.getAddress());
                        serviceNoticeReqDTO.setCurrentTime(DateUtils.date2String(meetTime,"yyyy-MM-dd HH:mm:ss"));
                        serviceNoticeReqDTO.setBussinessId(id);
                        try {
                            new Thread(new Runnable(){
                                @Override
                                public void run() {
                                    noticePushService.pushScheduleAddNotify(serviceNoticeReqDTO,tenantAndLandlordDTO.getLandlordDTO().getLandlordPhone(),
                                            tenantAndLandlordDTO.getLandlordDTO().getLandlordDeviceId(),tenantAndLandlordDTO.getLandlordDTO().getLandlordOsType());
                                }
                            }).start();

                        }catch (Exception e){
                            Logger.error(e, "服务日程新建提醒异常");
                        }
                    }
                }

                resultDO.setSuccess(resultSuccess1 && resultSuccess2);

            }else {
                resultDO.setErrMsg("无此类型！");
                return resultDO;
            }
        }catch (Exception e){
            Logger.error(e,"设置日程异常");
            resultDO.setErrMsg("设置日程异常："+e.getMessage());
        }
        return resultDO;
    }

    /**
     * check请求参数
     * @param typeDTO
     * @return
     */
    private String checkScheduleTypeDTO(ScheduleTypeDTO typeDTO){
        if (null == typeDTO){
            return "请求参数不能为空！";
        }
        if(null == typeDTO.getClerkId()){
            return "请登录！";
        }
        if (null == typeDTO.getBizId()){
            return "业务id不能为空！";
        }
        if (null == typeDTO.getType()){
            return "类型不能为空！";
        }
        if (ValidateHelper.isEmpty(typeDTO.getMeetTime())){
            return "约见时间不能为空！";
        }
        return null;
    }
}
