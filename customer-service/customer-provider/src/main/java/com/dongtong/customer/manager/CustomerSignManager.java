package com.dongtong.customer.manager;

import com.dongtong.customer.dao.CustomerSignMapper;
import com.dongtong.customer.domain.CustomerSchedule;
import com.dongtong.customer.domain.CustomerSign;
import com.dongtong.customer.dto.TenantAndLandlordDTO;
import com.dongtong.customer.dto.req.SignReqDTO;
import com.dongtong.customer.dto.resp.SignRespDTO;
import com.dongtong.customer.service.ScheduleService;
import com.shfc.common.base.Logger;
import com.shfc.common.date.DateUtils;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 类注释
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/10 15:54
 * @since 1.0
 */
@Service
public class CustomerSignManager {
    @Resource
    private CustomerSignMapper customerSignMapper;

    @Autowired(required = false)
    private ScheduleService scheduleService;

    public Page<SignRespDTO> getSignList(SignReqDTO signReqDTO, Page page) {
        customerSignMapper.selectByPage(signReqDTO,page);
        return page;
    }

    public SignRespDTO getSignInfo(SignReqDTO signReqDTO) {
        Long id=signReqDTO.getId();
        return customerSignMapper.selectById(id);
    }

    public ResultDO updateSignTime(SignReqDTO signReqDTO) throws ParseException {
        ResultDO res=new ResultDO();
        CustomerSign customerSign=new CustomerSign();
        customerSign.setId(signReqDTO.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        customerSign.setAppointTime(sdf.parse(signReqDTO.getSignTime()));
        if(1==customerSignMapper.updateByPrimaryKey(customerSign)){
            res.setSuccess(true);
        }
        return res;
    }

    public ResultDO updateSignStatus(SignReqDTO signReqDTO) {
        ResultDO res=new ResultDO();
        CustomerSign customerSign=new CustomerSign();
        customerSign.setId(signReqDTO.getId());
        customerSign.setTagId(signReqDTO.getTagId());
        customerSign.setReason(signReqDTO.getCancelCause());
        customerSign.setCancelTime(new Date());
        customerSign.setStatus(9);
        if(1==customerSignMapper.updateByPrimaryKey(customerSign)){
            res.setSuccess(true);
        }

        /*根据bizId获取日程*/
        ResultDO<CustomerSchedule> schedule = scheduleService.queryScheduleByBizId(signReqDTO.getId(), 5);
        if (schedule.getData() != null) {
            /*修改日程表*/
            if(schedule.getData().getMeetTime().getTime()> DateUtils.getCurrentDate().getTime()){
                CustomerSchedule customerSchedule = new CustomerSchedule();
                customerSchedule.setId(schedule.getData().getId());
                customerSchedule.setStatus(2);
                ResultDO scheduleDO = scheduleService.updatScheduleStatus(customerSchedule);
                if (!scheduleDO.isSuccess()) {
                    Logger.error(this, "修改约看状态失败，");
                }
            }

        }
        return res;
    }

    public ResultDO<Integer> getDeadTimeNum(SignReqDTO signReqDTO) {
        ResultDO res=new ResultDO();
        res.setData(customerSignMapper.getDeadTimeNum(signReqDTO));
        return res;
    }

    public Page<SignRespDTO> selectNeedByPage(SignReqDTO signReqDTO, Page page) {
        customerSignMapper.selectNeedByPage(signReqDTO,page);
        return page;
    }

    public List<CustomerSign> pendingList(){
        return customerSignMapper.pendingList();
    }

    public ResultDO updateSignedStatus(SignReqDTO signReqDTO) {
        ResultDO res=new ResultDO();
        CustomerSign customerSign=new CustomerSign();
        customerSign.setId(signReqDTO.getId());
        if(signReqDTO.getStatus()==9){
            customerSign.setCancelTime(new Date());
        }else if(signReqDTO.getStatus()==1){
            customerSign.setOwnerAffirmTime(new Date());
        }else if(signReqDTO.getStatus()==2){
            customerSign.setUploadTime(new Date());
            customerSign.setAgreementId(signReqDTO.getAgreementId());
        }
        customerSign.setStatus(signReqDTO.getStatus());
        if(1==customerSignMapper.updateByPrimaryKey(customerSign)){
            res.setSuccess(true);
        }
        return res;
    }

    /**
     * 根据签约id查询签约信息
     * @param signId
     * @Author zhoumin
     * @return
     */
    public CustomerSign getCustomerVisitInfoById(Long signId) {
        return customerSignMapper.selectByPrimaryKey(signId);
    }

    /**
     * 根据签约id，查询对应的房东和租客id
     * @param id
     * @Author zhoumin
     * @return
     */
    public TenantAndLandlordDTO getTenantAndLandlord(Long id){
        return customerSignMapper.getTenantAndLandlord(id);
    }
}
