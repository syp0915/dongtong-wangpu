package com.dongtong.customer.manager;

import com.dongtong.basic.dto.req.ServiceNoticeReqDTO;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.enums.ServiceNoticeType;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.service.ClerkService;
import com.dongtong.customer.constant.ErrorConstant;
import com.dongtong.customer.dao.CustomerVisitShopMapper;
import com.dongtong.customer.domain.Customer;
import com.dongtong.customer.domain.CustomerSchedule;
import com.dongtong.customer.domain.CustomerVisitShop;
import com.dongtong.customer.dto.TenantAndLandlordDTO;
import com.dongtong.customer.dto.VisitDTO;
import com.dongtong.customer.dto.req.VisitShopReqDTO;
import com.dongtong.customer.dto.resp.VisitShopRespDTO;
import com.dongtong.customer.service.CustomerService;
import com.dongtong.customer.service.ScheduleService;
import com.shfc.common.base.Logger;
import com.shfc.common.date.DateUtils;
import com.shfc.common.exception.AppException;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/10 11:08
 * @since 1.0
 */
@Service
public class CustomerVisitShopManager {
    @Resource
    private CustomerVisitShopMapper customerVisitShopMapper;

    @Autowired(required = false)
    private ScheduleService scheduleService;

    @Autowired
    private ClerkService clerkService;

    @Autowired
    private NoticePushService noticePushService;

    @Autowired
    private CustomerService customerService;

    public Page<VisitShopRespDTO> getVisitList(VisitShopReqDTO visitShopReqDTO, Page page) {
        customerVisitShopMapper.selectByPage(visitShopReqDTO,page);
        return page;
    }

    public VisitShopRespDTO getVisitInfo(VisitShopReqDTO visitShopReqDTO) {
        Long id=visitShopReqDTO.getId();
        return  customerVisitShopMapper.selectById(id);

    }

    @Transactional(rollbackFor = AppException.class)
    public ResultDO updateVisitTime(VisitShopReqDTO visitShopReqDTO) throws ParseException,AppException {
        ResultDO resultDO=new ResultDO();

        ResultDO rest = new ResultDO();
        VisitShopRespDTO res = getVisitInfo(visitShopReqDTO);
        if(res==null){
            rest.setErrMsg(ErrorConstant.NO_EXSIT_VISIT_ID.getMsg());
            rest.setErrCode(ErrorConstant.NO_EXSIT_VISIT_ID.getCode());
            return rest;
        }
        if(res.getStatus()!=0){
            rest.setErrMsg(ErrorConstant.ERROR_VISIT_STATUS.getMsg());
            rest.setErrCode(ErrorConstant.ERROR_VISIT_STATUS.getCode());
            return rest;
        }

        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		/*根据bizId获取日程*/
        ResultDO<CustomerSchedule> schedule = scheduleService.queryScheduleByBizId(res.getId(), 1);
        if(schedule.getData()==null){
            resultDO.setErrMsg(ErrorConstant.NULL_SCHEDULE.getMsg());
            resultDO.setErrCode(ErrorConstant.NULL_SCHEDULE.getCode());
            return resultDO;
        }
        /*修改日程表*/
        CustomerSchedule customerSchedule = new CustomerSchedule();
        customerSchedule.setId(schedule.getData().getId());
        customerSchedule.setMeetTime(format1.parse(visitShopReqDTO.getVisitTime()));
        customerSchedule.setOldMeetTime(format1.parse(res.getVisitTime()));
        try {
            ResultDO scheduleDO = scheduleService.updatScheduleStatus(customerSchedule);
            if(scheduleDO.isSuccess()){
                CustomerVisitShop cusomerVisitShop=new CustomerVisitShop();
                cusomerVisitShop.setId(visitShopReqDTO.getId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                cusomerVisitShop.setVisitTime(sdf.parse(visitShopReqDTO.getVisitTime()));
                if(1==customerVisitShopMapper.updateByPrimaryKey(cusomerVisitShop)){
                    resultDO.setSuccess(true);
                }else{
                    Logger.error(this,"更新约看时间失败，visitId="+visitShopReqDTO.getId());
                    throw new AppException("更新约看时间失败");
                }

            }else{
                Logger.error(this,"更新约看时间失败，visitId="+visitShopReqDTO.getId());
                throw new AppException("更新约看时间失败");
            }
            sendSmsNotice(visitShopReqDTO,res,schedule,1);
            resultDO.setSuccess(true);
        } catch (Exception e) {
            Logger.error(this,"更新约看时间处理失败",e);
            throw new AppException("更新约看时间处理失败");
        }

        return resultDO;
    }

    @Async
    public void sendSmsNotice(VisitShopReqDTO visitShopReqDTO, VisitShopRespDTO res, ResultDO<CustomerSchedule> schedule, Integer tagId){
        /**短信**/
        ResultDO<Clerk> clerk = clerkService.getClerkInfoById(res.getClerkId());
        String clerkName = clerk.getData().getRealName();
        String userPhone = res.getLinkmanPhone();
        ServiceNoticeReqDTO serviceNoticeDTO = new ServiceNoticeReqDTO();
        serviceNoticeDTO.setShopAddress(res.getShopAddress());
        serviceNoticeDTO.setServiceTel(clerk.getData().getPhone());
        serviceNoticeDTO.setServiceType(ServiceNoticeType.APPOINT_MENTSHOP.getValue());
        serviceNoticeDTO.setBussinessId(schedule.getData().getId());
        serviceNoticeDTO.setReceiveId(res.getCustomerId());
        serviceNoticeDTO.setReceiveType(ReceiveType.CUSTOMER.getValue());
        serviceNoticeDTO.setServiceName(clerkName);
        ResultDO<Customer> customer = customerService.findCustomerInfoByCustomerId(res.getCustomerId());
        String deviceId = customer.getData().getDeviceId();
        Integer osType = customer.getData().getOsType();
        if(tagId==1){//1:客户修改约看时间
            serviceNoticeDTO.setOldTime(res.getVisitTime());
            serviceNoticeDTO.setCurrentTime(visitShopReqDTO.getVisitTime());
            try {
			/*通知*/
                noticePushService.pushScheduleChangeNotify(serviceNoticeDTO,userPhone, deviceId, osType);
            } catch (Exception e) {
                Logger.error(this,"发送通知处理异常", e);
                e.printStackTrace();
            }
        }else{//业务员修改约看时间
            if(visitShopReqDTO.getTagId()==0){
                serviceNoticeDTO.setReason("客户取消,");
            }else if(visitShopReqDTO.getTagId()==1){
                serviceNoticeDTO.setReason("房东取消");
            }else if(visitShopReqDTO.getTagId()==2){
                serviceNoticeDTO.setReason("时间冲突");
            }else{
                serviceNoticeDTO.setReason("其他");
            }
            serviceNoticeDTO.setCurrentTime(res.getVisitTime());
            try {
			/*通知*/
                noticePushService.pushServiceRevokeNotify(serviceNoticeDTO,userPhone, deviceId, osType);
            } catch (Exception e) {
                Logger.error(this,"发送通知处理异常", e);
            }
        }

    }

    @Transactional(rollbackFor = AppException.class)
    public ResultDO updateVisitStatus(VisitShopReqDTO visitShopReqDTO) throws AppException{
        ResultDO resultDO = new ResultDO();
        VisitShopRespDTO res = getVisitInfo(visitShopReqDTO);
        if(res==null){
            resultDO.setErrMsg(ErrorConstant.NO_EXSIT_VISIT_ID.getMsg());
            resultDO.setErrCode(ErrorConstant.NO_EXSIT_VISIT_ID.getCode());
            return resultDO;
        }
        if(res.getStatus()!=0){
            resultDO.setErrMsg(ErrorConstant.DISCARD_VISIT.getMsg());
            resultDO.setErrCode(ErrorConstant.DISCARD_VISIT.getCode());
            return resultDO;
        }
        /*根据bizId获取日程*/
        ResultDO<CustomerSchedule> schedule = scheduleService.queryScheduleByBizId(res.getId(), 3);
        if (schedule.getData() == null) {
            resultDO.setErrMsg(ErrorConstant.NULL_SCHEDULE.getMsg());
            resultDO.setErrCode(ErrorConstant.NULL_SCHEDULE.getCode());
            return resultDO;
        }
        //sendSmsNotice(visitShopReqDTO, res, schedule, 2);
        try {
            /*修改日程表*/
            if(schedule.getData().getMeetTime().getTime()> DateUtils.getCurrentDate().getTime()){
                CustomerSchedule customerSchedule = new CustomerSchedule();
                customerSchedule.setId(schedule.getData().getId());
                customerSchedule.setStatus(2);
                ResultDO scheduleDO = scheduleService.updatScheduleStatus(customerSchedule);

                if (!scheduleDO.isSuccess()) {
                    Logger.error(this, "修改约看状态失败，");
                    throw new AppException("修改约看状态失败");
                }
            }

            CustomerVisitShop cusomerVisitShop = new CustomerVisitShop();
            cusomerVisitShop.setId(visitShopReqDTO.getId());
            cusomerVisitShop.setTagId(visitShopReqDTO.getTagId());
            cusomerVisitShop.setCancelCause(visitShopReqDTO.getCancelCause());
            cusomerVisitShop.setCancelTime(new Date());
            cusomerVisitShop.setStatus(2);
            if (1 == customerVisitShopMapper.updateByPrimaryKey(cusomerVisitShop)) {
                resultDO.setSuccess(true);
            }
            resultDO.setSuccess(true);
        } catch (Exception e) {
            Logger.error(this, "线索废弃失败,e:{}", e);
            throw e;
        }

        return resultDO;
    }

    public ResultDO<Integer> getDeadTimeNum(VisitShopReqDTO visitShopReqDTO) {
        ResultDO res=new ResultDO();
        int count= customerVisitShopMapper.getDeadTimeNum(visitShopReqDTO);
        res.setData(count);
        return res;
    }

    public Page<VisitShopRespDTO> selectNeedByPage(VisitShopReqDTO visitShopReqDTO, Page page) {
        customerVisitShopMapper.selectNeedByPage(visitShopReqDTO,page);
        return page;
    }

    /**
     * 获取所有待办列表
     * @return
     */
    public List<VisitShopRespDTO> pendingList(){
       return customerVisitShopMapper.pendingList();
    }

    public ResultDO updateMeetStatus(VisitShopReqDTO visitShopReqDTO) {
        ResultDO res=new ResultDO();
        CustomerVisitShop cusomerVisitShop=new CustomerVisitShop();
        cusomerVisitShop.setId(visitShopReqDTO.getId());
        cusomerVisitShop.setStatus(visitShopReqDTO.getStatus());
        if(visitShopReqDTO.getStatus()==2){
            cusomerVisitShop.setRevocationTime(new Date());
        }else if(visitShopReqDTO.getStatus()==1){
            cusomerVisitShop.setSuccessTime(new Date());
        }

        if(1==customerVisitShopMapper.updateByPrimaryKey(cusomerVisitShop)){
            res.setSuccess(true);
        }
        return res;
    }

    /**
     * 根据约看id查询约看信息
     * @param visitId
     * @Author zhoumin
     * @return
     */
    public CustomerVisitShop getCustomerVisitInfoById(Long visitId) {
        return customerVisitShopMapper.selectByPrimaryKey(visitId);
    }

    /**
     * 查看用户最近一次约看
     * @Author zhoumin
     * @param customerId
     * @return
     */
    public VisitDTO queryLastVisit(Long customerId){
        return customerVisitShopMapper.queryLastVisit(customerId);
    }

    /**
     * 根据约看id，查询对应的房东和租客id
     * @param id
     * @return
     */
    public TenantAndLandlordDTO getTenantAndLandlord(Long id){
        return customerVisitShopMapper.getTenantAndLandlord(id);
    }
}
