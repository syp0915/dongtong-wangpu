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
import com.dongtong.customer.dto.req.VisitShopReqDTO;
import com.dongtong.customer.dto.resp.VisitShopRespDTO;
import com.dongtong.customer.service.CustomerService;
import com.dongtong.customer.service.CustomerVisitShopService;
import com.dongtong.customer.service.ScheduleService;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 类注释
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/17 9:47
 * @since 1.0
 */
@Service
@Slf4j
public class CustomerVisitAO {
    @Resource
    private CustomerVisitShopService customerVisitShopService;
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

    public ResultDO<Page<VisitShopRespDTO>> getVisitList(VisitShopReqDTO visitShopReqDTO) {
        ResultDO res=new ResultDO();
        Page page = new Page();
        if(visitShopReqDTO.getPageSize()==0){
            res.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            res.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            return res;
        }
        if(visitShopReqDTO.getPageNumber()==0){
            res.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            res.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            return res;
        }
        page.setPageSize(visitShopReqDTO.getPageSize());
        page.setPageNumber(visitShopReqDTO.getPageNumber());
        visitShopReqDTO.setClerkId((Long) HttpSessionUtils.getObject(AuthSessionUtils.APP_CURRENT_USER_ID));
        return customerVisitShopService.getVisitList(visitShopReqDTO, page);
    }

    public ResultDO<VisitShopRespDTO> getVisitInfo(VisitShopReqDTO visitShopReqDTO) {
        ResultDO res = new ResultDO();
        if (visitShopReqDTO.getId() == null) {
            res.setErrMsg(ErrorConstant.NULL_VISIT_ID.getMsg());
            res.setErrCode(ErrorConstant.NULL_VISIT_ID.getCode());
            return res;
        }

        return customerVisitShopService.getVisitInfo(visitShopReqDTO);
    }

    public ResultDO updateVisitTime(VisitShopReqDTO visitShopReqDTO) throws ParseException {
        ResultDO rest = new ResultDO();
        if (visitShopReqDTO.getId() == null) {
            rest.setErrMsg(ErrorConstant.NULL_VISIT_ID.getMsg());
            rest.setErrCode(ErrorConstant.NULL_VISIT_ID.getCode());
            return rest;
        }
        if (ValidateHelper.isEmptyString(visitShopReqDTO.getVisitTime())) {
            rest.setErrMsg(ErrorConstant.NULL_VISIT_TIME.getMsg());
            rest.setErrCode(ErrorConstant.NULL_VISIT_TIME.getCode());
            return rest;
        }
        String visitTime = visitShopReqDTO.getVisitTime();
        if(!DateValidate.isValidDate(visitTime,"yyyy-MM-dd HH:mm")){
            rest.setErrCode(ErrorConstant.ERROR_DATE_FORMAT.getCode());
            rest.setErrMsg(ErrorConstant.ERROR_DATE_FORMAT.getMsg());
            return rest;
        }

        return customerVisitShopService.updateVisitTime(visitShopReqDTO);
    }

    public ResultDO updateVisitStatus(VisitShopReqDTO visitShopReqDTO) {
        ResultDO rest = new ResultDO();
        if (visitShopReqDTO.getId() == null) {
            rest.setErrMsg(ErrorConstant.NULL_VISIT_ID.getMsg());
            rest.setErrCode(ErrorConstant.NULL_VISIT_ID.getCode());
            return rest;
        }


        rest = customerVisitShopService.updateVisitStatus(visitShopReqDTO);

        return rest;
    }



}