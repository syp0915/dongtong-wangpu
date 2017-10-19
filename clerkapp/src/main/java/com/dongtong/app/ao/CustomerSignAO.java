package com.dongtong.app.ao;

import com.dongtong.app.constant.ErrorConstant;
import com.dongtong.app.utils.AuthSessionUtils;
import com.dongtong.app.utils.DateValidate;
import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.basic.dto.req.ServiceNoticeReqDTO;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.enums.ServiceNoticeType;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.basic.service.SmsService;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.service.ClerkService;
import com.dongtong.customer.domain.Customer;
import com.dongtong.customer.domain.CustomerSchedule;
import com.dongtong.customer.dto.req.SignReqDTO;
import com.dongtong.customer.dto.resp.SignRespDTO;
import com.dongtong.customer.service.CustomerService;
import com.dongtong.customer.service.CustomerSignSerivce;
import com.dongtong.customer.service.ScheduleService;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 签约
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/17 9:55
 * @since 1.0
 */
@Service
@Slf4j
public class CustomerSignAO {
    @Resource
    private CustomerSignSerivce customerSignSerivce;
    @Autowired
    private NoticePushService noticePushService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private ClerkService clerkService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ScheduleService scheduleService;

    public ResultDO<Page<SignRespDTO>> getSignList(SignReqDTO signReqDTO) {
        Page page=new Page();
        ResultDO res=new ResultDO();
        if(signReqDTO.getPageSize()==0){
            res.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            res.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            return res;
        }
        if(signReqDTO.getPageNumber()==0){
            res.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            res.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            return res;
        }
        page.setPageSize(signReqDTO.getPageSize());
        page.setPageNumber(signReqDTO.getPageNumber());
        signReqDTO.setClerkId((Long) HttpSessionUtils.getObject(AuthSessionUtils.APP_CURRENT_USER_ID));
        return customerSignSerivce.getSignList(signReqDTO,page);
    }

    public ResultDO<SignRespDTO> getSignInfo(SignReqDTO signReqDTO) {
        ResultDO res=new ResultDO();
        if(signReqDTO.getId()==null){
            res.setErrMsg(ErrorConstant.NULL_SIGNID.getMsg());
            res.setErrCode(ErrorConstant.NULL_SIGNID.getCode());
            return res;
        }
        return customerSignSerivce.getSignInfo(signReqDTO);
    }

    public ResultDO updateSignTime(SignReqDTO signReqDTO) throws ParseException {
        ResultDO rest=new ResultDO();
        if(signReqDTO.getId()==null){
            rest.setErrMsg(ErrorConstant.NULL_SIGNID.getMsg());
            rest.setErrCode(ErrorConstant.NULL_SIGNID.getCode());
            return rest;
        }
        if(ValidateHelper.isEmptyString(signReqDTO.getSignTime())){
            rest.setErrMsg(ErrorConstant.NULL_SIGN_TIME.getMsg());
            rest.setErrCode(ErrorConstant.NULL_SIGN_TIME.getCode());
            return rest;
        }
        String signTime=signReqDTO.getSignTime();
        if(!DateValidate.isValidDate(signTime,"yyyy-MM-dd HH:mm")){
            rest.setErrCode(ErrorConstant.ERROR_DATE_FORMAT.getCode());
            rest.setErrMsg(ErrorConstant.ERROR_DATE_FORMAT.getMsg());
            return rest;
        }
        ResultDO resultDO=new ResultDO();

        ResultDO<SignRespDTO> res=customerSignSerivce.getSignInfo(signReqDTO);
        if(res.getData()==null){
            rest.setErrMsg(ErrorConstant.NO_EXSIT_SIGN_ID.getMsg());
            rest.setErrCode(ErrorConstant.NO_EXSIT_SIGN_ID.getCode());
            return rest;
        }
        if(res.getData().getStatus()!=0){
            rest.setErrMsg(ErrorConstant.ERROR_SIGN_STATUS.getMsg());
            rest.setErrCode(ErrorConstant.ERROR_SIGN_STATUS.getCode());
            return rest;
        }
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if(res.getData()!=null){
		/*根据bizId获取日程*/
            ResultDO<CustomerSchedule>schedule=	scheduleService.queryScheduleByBizId(res.getData().getId(),2);
            if(schedule.getData()==null){
                rest.setErrMsg(ErrorConstant.NULL_SCHEDULE.getMsg());
                rest.setErrCode(ErrorConstant.NULL_SCHEDULE.getCode());
                return rest;
            }
            sendSmsNotice(signReqDTO,res,schedule,1);

		/*修改日程表*/
            CustomerSchedule customerSchedule=new CustomerSchedule();
            customerSchedule.setId(schedule.getData().getId());
            customerSchedule.setMeetTime(format1.parse(signTime));
            customerSchedule.setOldMeetTime(format1.parse(res.getData().getSignTime()));
            try{
                scheduleService.updatScheduleStatus(customerSchedule);
                customerSignSerivce.updateSignTime(signReqDTO);
                resultDO.setSuccess(true);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return resultDO;
    }

    public ResultDO updateSignStatus(SignReqDTO signReqDTO) {
        ResultDO rest=new ResultDO();
        if(signReqDTO.getId()==null){
            rest.setErrMsg(ErrorConstant.NULL_SIGNID.getMsg());
            rest.setErrCode(ErrorConstant.NULL_SIGNID.getCode());
            return rest;
        }
        ResultDO resultDO=new ResultDO();
        ResultDO<SignRespDTO> res=customerSignSerivce.getSignInfo(signReqDTO);
        if(res.getData()==null){
            rest.setErrMsg(ErrorConstant.NO_EXSIT_SIGN_ID.getMsg());
            rest.setErrCode(ErrorConstant.NO_EXSIT_SIGN_ID.getCode());
            return rest;
        }
        if(res.getData().getStatus()!=0){
            rest.setErrMsg(ErrorConstant.DISCARD_SIGN.getMsg());
            rest.setErrCode(ErrorConstant.DISCARD_SIGN.getCode());
            return rest;
        }
        if(res.getData()!=null){
//		/*根据bizId获取日程*/
//            ResultDO<CustomerSchedule>schedule=	scheduleService.queryScheduleByBizId(res.getData().getId(),2);
//            if(schedule.getData()==null){
//                resultDO.setErrMsg(ErrorConstant.NULL_SCHEDULE.getMsg());
//                resultDO.setErrCode(ErrorConstant.NULL_SCHEDULE.getCode());
//                return resultDO;
//            }
//            sendSmsNotice(signReqDTO,res,schedule,2);
//
//		/*修改日程表*/
//            CustomerSchedule customerSchedule=new CustomerSchedule();
//            customerSchedule.setId(schedule.getData().getId());
//            customerSchedule.setStatus(2);
            try{
//                scheduleService.updatScheduleStatus(customerSchedule);
                customerSignSerivce.updateSignStatus(signReqDTO);
                resultDO.setSuccess(true);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return resultDO;
    }



    @Async
    public void sendSmsNotice(SignReqDTO signReqDTO, ResultDO<SignRespDTO> res, ResultDO<CustomerSchedule> schedule, Integer tagId){
        /**短信**/
        ResultDO<Clerk> clerk=clerkService.getClerkInfoById(res.getData().getClerkId());
        String clerkName=clerk.getData().getRealName();
        String userPhone=res.getData().getContactMobile();
        ServiceNoticeReqDTO serviceNoticeDTO=new ServiceNoticeReqDTO();
        serviceNoticeDTO.setServiceType(ServiceNoticeType.CONTRACT_LEASING.getValue());
        serviceNoticeDTO.setShopAddress(res.getData().getShopAddress());
        serviceNoticeDTO.setServiceTel(clerk.getData().getPhone());
        serviceNoticeDTO.setBussinessId(schedule.getData().getId());
        serviceNoticeDTO.setReceiveId(res.getData().getCustomerId());
        serviceNoticeDTO.setReceiveType(ReceiveType.CUSTOMER.getValue());
        serviceNoticeDTO.setServiceName(clerkName);
        ResultDO<Customer> customer=customerService.findCustomerInfoByCustomerId(res.getData().getCustomerId());
        String deviceId=customer.getData().getDeviceId();
        Integer osType=customer.getData().getOsType();
        log.info("serviceNoticeDTO:{}",serviceNoticeDTO.toString());
        if(tagId==1){
            serviceNoticeDTO.setOldTime(res.getData().getSignTime());
            serviceNoticeDTO.setCurrentTime(signReqDTO.getSignTime());
            try{
			/*通知*/
                noticePushService.pushScheduleChangeNotify(serviceNoticeDTO,userPhone,deviceId, osType);
            }catch(Exception e){
                log.info("e",e);
                e.printStackTrace();
            }
        }else{
            if(signReqDTO.getTagId()==0){
                serviceNoticeDTO.setReason("客户取消,");
            }else if(signReqDTO.getTagId()==1){
                serviceNoticeDTO.setReason("房东取消");
            }else if(signReqDTO.getTagId()==2){
                serviceNoticeDTO.setReason("时间冲突");
            }else{
                serviceNoticeDTO.setReason("其他");
            }
            serviceNoticeDTO.setCurrentTime(res.getData().getSignTime());
            try{
			/*通知*/
                noticePushService.pushServiceRevokeNotify(serviceNoticeDTO,userPhone,deviceId, osType);
            }catch(Exception e){
                log.info("e",e);
                e.printStackTrace();
            }
        }
    }
}
