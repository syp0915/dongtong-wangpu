package com.dongtong.customer.manager;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.enums.WorkServiceType;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.service.ClerkService;
import com.dongtong.customer.constant.ErrorConstant;
import com.dongtong.customer.dao.*;
import com.dongtong.customer.domain.*;
import com.dongtong.customer.dto.*;
import com.dongtong.customer.dto.req.AttentionPlateReqDTO;
import com.dongtong.customer.dto.req.AttentionReqDTO;
import com.dongtong.customer.dto.req.AttentionVocationReqDTO;
import com.dongtong.customer.dto.resp.IndexStatisticsRespDTO;
import com.dongtong.customer.dto.resp.VisitedStatisticDTO;
import com.dongtong.customer.enums.*;
import com.dongtong.customer.query.CustomerServiceQuery;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.date.DateUtils;
import com.shfc.common.exception.AppException;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/9 上午9:48.
 */
@Service
public class CustomerManager {

    @Autowired(required = false)
    private CustomerMapper customerMapper;

    @Autowired(required = false)
    private CustomerAttentionMapper customerAttentionMapper;

    @Autowired(required = false)
    private CustomerAttentionAreaMapper customerAttentionAreaMapper;

    @Autowired(required = false)
    private CustomerScheduleMapper customerScheduleMapper;

    @Autowired(required = false)
    private CustomerSignMapper customerSignMapper;

    @Autowired(required = false)
    private CustomerVisitShopMapper customerVisitShopMapper;

    @Autowired
    private CustomerAttentionVocationMapper customerAttentionVocationMapper;      //v1.2
    @Autowired
    private CustomerAttentionPlateMapper customerAttentionPlateMapper;        //v1.2

    @Autowired
    private NoticePushService noticePushService;

    @Autowired
    private ClerkService clerkService;

    public Customer findCustomerInfoByUserPhone(String userPhone){
        return customerMapper.findCustomerInfoByUserPhone(userPhone);
    }

    public boolean registerCustomer(Customer customer){
        return customerMapper.insert(customer) > 0;
    }

    public Customer findCustomerInfoByCustomerId(Long customerId){
        return customerMapper.selectByCustomerId(customerId);
    }

    /**
     * 修改头像
     * @return
     */
    @Deprecated
    @Transactional(rollbackFor = AppException.class)
    public void updateHeadPortrait(Long customerId,String headPortrait){
        Customer customer=findCustomerInfoByCustomerId(customerId);
        customer.setHeadPortrait(headPortrait);
        customerMapper.updateByPrimaryKey(customer);

    }

    /**
     * @description
     * @package com.dongtong.customer.manager
     * @author chenxs
     * @date 2017/8/8 0008 17:30
     * @param customerId
     * @return CustomerInfoDTO
     *
     * v1.2
     *   --新增查询关注内容逻辑
     *   --新增查询我的服务逻辑
     *   --手机号码去掉脱敏
     */
    public CustomerInfoDTO queryInfoByCustomerId(Long customerId){
        CustomerInfoDTO customerInfoDTO = customerMapper.queryInfoByCustomerId(customerId);
        return customerInfoDTO;

    }

    public Customer getCustomerInfoByUserPhone(String userPhone) {
        return customerMapper.getCustomerInfoByUserPhone(userPhone);
    }


    /**
     * 根据用户id查询用户信息
     * @return
     */
    public CustomerDTO queryCustomerInfoByCustomerId(Long customerId){
        return customerMapper.queryCustomerInfo(customerId);
    }

    /**
     * @description
     * @package com.dongtong.customer.manager
     * @author chenxs
     * @date 2017/8/9 0009 14:18
     * @paramdto
     *
     * v1.2
     *    --修改入参
     *    --关注板块和关注行业可以多选
     */
    @Transactional(rollbackFor = AppException.class)
    public void updateFollow(AttentionReqDTO dto){
        List<AttentionVocationReqDTO> vocationList = dto.getVocationList();
        List<AttentionPlateReqDTO> plateList = dto.getPlateList();
        List<Integer> areaList = dto.getAreaList();

        List<CustomerAttentionVocation> attenVocation = customerAttentionVocationMapper.selectVocationListByCustomer(dto.getCustomerId());
        //如果数据库中关注行业不为空，按用户全部删除
        if(attenVocation != null && attenVocation.size() > 0){
            customerAttentionVocationMapper.deleteByCustomerId(dto.getCustomerId());
        }

        if(vocationList != null && vocationList.size() > 0){
            //消除相同对象
            HashSet<AttentionVocationReqDTO> hsVocation = new HashSet<>(vocationList);
            List<AttentionVocationReqDTO> arVocation = new ArrayList<>(hsVocation);
            //如果传入参数中关注行业不为空，执行批量插入
            if(!ValidateHelper.isEmpty(arVocation) && arVocation.size() > 0){
                customerAttentionVocationMapper.batchInsertVocation(arVocation , dto.getCustomerId() );
            }
        }

        List<CustomerAttentionPlate> attenPlate = customerAttentionPlateMapper.selectPlateListByCustomer(dto.getCustomerId());
        //如果数据库中关注板块不为空，按用户执行全部删除
        if(attenPlate != null && attenPlate.size() > 0){
            customerAttentionPlateMapper.deleteByCustomerId(dto.getCustomerId());
        }
        if(plateList != null && plateList.size() > 0){
            //消除相同对象
            HashSet<AttentionPlateReqDTO> hsPlate = new HashSet<>(plateList);
            List<AttentionPlateReqDTO> arPlate = new ArrayList<>(hsPlate);
            //如果传入参数中关注板块不为空，执行批量插入
            if(!ValidateHelper.isEmpty(arPlate) && arPlate.size() > 0){
                customerAttentionPlateMapper.batchInsertPlate(arPlate , dto.getCustomerId() );
            }
        }

        List<Integer> attentionArea = customerAttentionAreaMapper.selectAreaByCustomerId(dto.getCustomerId());
        //如果数据库中关注面积参数不为空，按用户执行全部删除
        if(attentionArea != null && attentionArea.size() > 0){
            customerAttentionAreaMapper.deleteAttentionByCustomerId(dto.getCustomerId());
        }
        //如果传入参数关注面积不为空，执行批量插入
        if(areaList != null && areaList.size() > 0){
            customerAttentionAreaMapper.insertAttentionArea(areaList , dto.getCustomerId());
        }
    }

    /**
     * 添加签约申请
     * @return
     */
    @Transactional(rollbackFor = AppException.class)
    public Long addSign(CustomerSignDTO dto,Long clerkId,String district,String shopAddress){
       // CustomerSign customerSign=customerSignMapper.querySign(dto.getCustomerId(),dto.getShopId());
      //  if(customerSign==null) {
            CustomerSign customerSign=new CustomerSign();
            customerSign.setCustomerId(dto.getCustomerId());
            customerSign.setShopId(dto.getShopId());
            customerSign.setClerkId(clerkId);
            customerSign.setShopAddress(shopAddress);
            customerSign.setContacter(dto.getContactName());
            customerSign.setContactMobile(dto.getContactMobile());
            customerSign.setAppointTime(DateUtils.string2Date(dto.getSubscribeTime()));
            customerSign.setStatus(SignStatus.PENDING.getValue());

            customerSignMapper.insert(customerSign);
//        }else {
//            customerSign.setClerkId(clerkId);
//            customerSign.setShopAddress(shopAddress);
//            customerSign.setContacter(dto.getContactName());
//            customerSign.setContactMobile(dto.getContactMobile());
//            customerSign.setAppointTime(DateUtils.string2Date(dto.getSubscribeTime()));
//            customerSign.setStatus(SignStatus.PENDING.getValue());
//            customerSignMapper.updateByPrimaryKey(customerSign);
//        }
        return customerSign.getId();
    }
    /**
     * 添加约看
     * @return
     */
    @Transactional(rollbackFor = AppException.class)
    public Long addAppointment(CustomerSignDTO dto,Long clerkId,String district,String shopAddress){
//        CustomerVisitShop customerVisitShop=customerVisitShopMapper.queryVisitByParams(dto.getCustomerId(),dto.getShopId());
//        if(customerVisitShop==null) {
            CustomerVisitShop customerVisitShop=new CustomerVisitShop();
            customerVisitShop.setCustomerId(dto.getCustomerId());
            customerVisitShop.setShopId(dto.getShopId());
            customerVisitShop.setClerkId(clerkId);
            customerVisitShop.setVisitTime(DateUtils.string2Date(dto.getSubscribeTime()));
            customerVisitShop.setLinkmanName(dto.getContactName());
            customerVisitShop.setLinkmanPhone(dto.getContactMobile());
            customerVisitShop.setStatus(VisitStatus.VISITING.getValue());
            customerVisitShop.setIsDelete(YesNo.NO.getValue());
            customerVisitShopMapper.insert(customerVisitShop);
//        }else {
//            customerVisitShop.setClerkId(clerkId);
//            customerVisitShop.setVisitTime(DateUtils.string2Date(dto.getSubscribeTime()));
//            customerVisitShop.setLinkmanName(dto.getContactName());
//            customerVisitShop.setLinkmanPhone(dto.getContactMobile());
//            customerVisitShop.setStatus(VisitStatus.VISITING.getValue());
//            customerVisitShop.setIsDelete(YesNo.NO.getValue());
//            customerVisitShopMapper.updateByPrimaryKey(customerVisitShop);
//
//        }
        return customerVisitShop.getId();

    }

    /**
     * 用户收藏总数的统计
     * @return
     */
    public List<VisitedStatisticDTO> myVisitStatistic(){
        return customerVisitShopMapper.myVisitStatistic();
    }

    /**
     * 根据用户id和约看id查看是否已经约看过该商铺
     * @return
     */
    public List<CustomerVisitShop> queryShopVisitById(Long shopId, Long customerId){
        return customerVisitShopMapper.queryShopVisitById(shopId,customerId);
    }

    /**
     * 删除约看
     * @return
     */
    @Transactional(rollbackFor = AppException.class)
    public int  cannelShopVisitById(Long shopId, Long customerId){
         return  customerVisitShopMapper.cannelShopVisitByShopId(shopId,customerId);
    }

    /**
     * 首页-我的发布、约看、收藏、浏览、未来日程 总数统计接口
     * @param customerId
     * @return
     */
    public IndexStatisticsRespDTO indexStatistics(Long customerId){
        return customerMapper.indexStatistics(customerId);
    }

    public boolean updateByPrimaryKeySelective(Customer customer){
        return customerMapper.updateByPrimaryKeySelective(customer) > 0;
    }


    /**
     * @description
     * @package com.dongtong.customer.manager
     * @author chenxs
     * @date 2017/8/9 0009 15:52
     * @param dto
     * @return ResultDO
     *
     * v1.2新增
     *     --修改用户个人信息功能
     */
    public ResultDO<String> updateCustomerInfo(CustomerInfoDTO dto){
        ResultDO<String> resultDO = new ResultDO<>();
        Customer customer = findCustomerInfoByCustomerId(dto.getCustomerId());

        String headPortrait = dto.getHeadPortrait();
        String nickName = dto.getNickName();
        String signature = dto.getSignature();
        String phone = dto.getPhone();

        if(!ValidateHelper.isEmpty(headPortrait)){
            customer.setHeadPortrait(headPortrait);
        }
        if(!ValidateHelper.isEmpty(nickName)){
            customer.setNickName(nickName);
        }
        if(!ValidateHelper.isEmpty(signature)){
            customer.setSignature(signature);
        }
        if(!ValidateHelper.isEmpty(phone)){
            customer.setPhone(phone);
        }
        Integer count = customerMapper.updateByPrimaryKey(customer);
        if(count == 0){
            resultDO.setErrMsg("更新用户信息出错！");
            resultDO.setData("更新数据库出错");
        }
        resultDO.setSuccess(true);
        resultDO.setData("用户信息修改成功！");
        return resultDO;
    }

    public Page<ServiceListDTO> selectServiceList(Page<ServiceListDTO> page){
        customerMapper.selectServiceList(page);
        page.setQuery(null);
        return page;
    }

    public ResultDO<ServiceDetailDTO> queryServiceDetail(CustomerServiceQuery query){
        ResultDO<ServiceDetailDTO> resultDO = new ResultDO<ServiceDetailDTO>();
        ServiceDetailDTO serviceDetailDTO = new ServiceDetailDTO();
        if(query.getType()== ServiceType.SHOP_FOR_CHECK.getValue()){
            serviceDetailDTO = customerMapper.selectServiceHintDetail(query);
        }else if(query.getType()== ServiceType.VISIT_TENANT.getValue()){
            serviceDetailDTO = customerMapper.selectServiceVisitDetail(query);
        }else if(query.getType()== ServiceType.SIGN_TENANT.getValue()){
            serviceDetailDTO = customerMapper.selectServiceSignDetail(query);
        }else{
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.SERVICE_TYPE_ERROR.getCode());
            resultDO.setErrMsg(ErrorConstant.SERVICE_TYPE_ERROR.getMsg());
            return resultDO;
        }
        resultDO.setData(serviceDetailDTO);
        resultDO.setSuccess(true);
        return resultDO;
    }

    public ResultDO<Boolean> confirmService(CustomerServiceQuery query){
        ResultDO<Boolean> resultDO = new ResultDO<Boolean>();
        if(query.getType()== ServiceType.SHOP_FOR_CHECK.getValue()){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.SERVICE_TYPE_ERROR.getCode());
            resultDO.setErrMsg(ErrorConstant.SERVICE_TYPE_ERROR.getMsg());
            return resultDO;
        }else if(query.getType()== ServiceType.VISIT_TENANT.getValue()){
            CustomerVisitShop customerVisitShop = customerVisitShopMapper.selectByPrimaryKey(query.getId());
            if(!ValidateHelper.isEmpty(customerVisitShop)){
                Long clerkId = customerVisitShop.getClerkId();
                ResultDO<Clerk> clerkResult = clerkService.getClerkInfoById(clerkId);
                if(clerkResult.isSuccess()){
                    Clerk clerk = clerkResult.getData();
                    this.pushTrumpetMessageToAllClerk(WorkServiceType.ORDER_SEE.getValue(),clerk.getRealName(),"");
                }
            }
            customerMapper.confirmVisit(query.getId());
        }else if(query.getType()== ServiceType.SIGN_TENANT.getValue()){
            CustomerSign customerSign = customerSignMapper.selectByPrimaryKey(query.getId());
            if(!ValidateHelper.isEmpty(customerSign)){
                Long clerkId = customerSign.getClerkId();
                ResultDO<Clerk> clerkResult = clerkService.getClerkInfoById(clerkId);
                if(clerkResult.isSuccess()){
                    this.pushTrumpetMessageToAllClerk(WorkServiceType.SIGN_CONTRACT.getValue(),clerkResult.getData().getRealName(),"");
                }
            }
            customerMapper.confirmSign(query.getId());
        }else{
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.SERVICE_TYPE_ERROR.getCode());
            resultDO.setErrMsg(ErrorConstant.SERVICE_TYPE_ERROR.getMsg());
            return resultDO;
        }
        resultDO.setSuccess(true);
        return resultDO;
    }

    /**
     * 异步推送小喇叭消息给所有业务员，推送失败不能影响主线业务
     * @param messageType
     * @param clerkName
     */
    @Async
    private void pushTrumpetMessageToAllClerk(final Integer messageType, final String clerkName,final String shopAddress){
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
                        ResultDO<Boolean> pushResult = noticePushService.pushTrumpetNotice(clerk.getDeviceId(), clerk.getOsType(), messageType, clerkName, shopAddress);
                        if (!pushResult.isSuccess()) {
                            Logger.error(CustomerManager.class, "推送小喇叭消息给业务员：" + JSON.toJSONString(clerk) + "失败，失败原因：" + pushResult.getErrCode() + "-" + pushResult.getErrMsg());
                        }
                    }
                }.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    @Transactional
    public ResultDO<Boolean> revokedService(CustomerServiceQuery query){
        ResultDO<Boolean> resultDO = new ResultDO<Boolean>();
        if(query.getType()== ServiceType.SHOP_FOR_CHECK.getValue()){
            customerMapper.revokedHint(query.getId());
            customerScheduleMapper.revokeByBizId(query.getId(), ScheduleType.SHOP_FOR_CHECK.getValue());
            customerScheduleMapper.revokeByBizId(query.getId(), ScheduleType.SHOP_FOR_SURVEY.getValue());
        }else if(query.getType()== ServiceType.VISIT_TENANT.getValue()){
            customerMapper.revokedVisit(query.getId());
            customerScheduleMapper.revokeByBizId(query.getId(), ScheduleType.VISIT_TENANT.getValue());
            customerScheduleMapper.revokeByBizId(query.getId(), ScheduleType.VISIT_LANDLORD.getValue());
        }else if(query.getType()== ServiceType.SIGN_TENANT.getValue()){
            customerMapper.revokedSign(query.getId());
            customerScheduleMapper.revokeByBizId(query.getId(), ScheduleType.SIGN_TENANT.getValue());
            customerScheduleMapper.revokeByBizId(query.getId(), ScheduleType.SIGN_LANDLORD.getValue());
        }else{
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.SERVICE_TYPE_ERROR.getCode());
            resultDO.setErrMsg(ErrorConstant.SERVICE_TYPE_ERROR.getMsg());
            return resultDO;
        }
        resultDO.setSuccess(true);
        return resultDO;
    }
}
