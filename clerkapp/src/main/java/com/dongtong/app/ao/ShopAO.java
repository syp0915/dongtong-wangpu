package com.dongtong.app.ao;

import com.alibaba.fastjson.JSON;
import com.dongtong.app.exception.ErrorConstant;
import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.basic.dto.req.ServiceNoticeReqDTO;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.enums.ServiceNoticeType;
import com.dongtong.basic.service.BaseShopNumberService;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.domain.ClerkHint;
import com.dongtong.clerk.enums.ClerkRoleType;
import com.dongtong.clerk.service.ClerkHintService;
import com.dongtong.clerk.service.ClerkService;
import com.dongtong.customer.domain.Customer;
import com.dongtong.customer.domain.CustomerSchedule;
import com.dongtong.customer.service.CustomerService;
import com.dongtong.customer.service.ScheduleService;
import com.dongtong.shop.dto.*;
import com.dongtong.shop.enums.UndoType;
import com.dongtong.shop.query.ShopClerkQuery;
import com.dongtong.shop.service.ShopService;
import com.shfc.common.base.Logger;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package com.dongtong.app.ao.ShopAO
 * @Description: ShopAO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/4 17:02
 * version V1.0.0
 */
@Service
public class ShopAO {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ClerkHintService clerkHintService;
    @Autowired
    private NoticePushService noticePushService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ClerkService clerkService;
    @Autowired
    private BaseShopNumberService baseShopNumberService;
    /**
     * 业务端查询旺铺列表
     * @Author lv bin
     * @param query
     * @return
     */
    public ResultDO<Page<ShopClerkDTO>> queryShopByClerk(ShopClerkQuery query){

        ResultDO<Page<ShopClerkDTO>> resultDO = new ResultDO<>();
        if(query == null){
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }

        query.setClerkId(HttpSessionUtils.getCurrentAppUserId());

        return shopService.queryShopByClerk(query);
    }
    /**
     * 发布商铺
     * @param shopDTO
     * @return
     */
    public ResultDO<Long> issueShop(IssueShopDTO shopDTO){
        ResultDO<Long> resultDO = new ResultDO<>();
        Long currentId = HttpSessionUtils.getCurrentAppUserId();
        if(shopDTO==null){
            resultDO.setErrMsg("请求参数不能为空！");
            return resultDO;
        }
        ResultDO<Clerk> clerkResult = clerkService.getClerkInfoById(currentId);
        if(clerkResult.isSuccess()){
            Clerk clerk = clerkResult.getData();
            Integer roleType = clerk.getRoleType();
            if(roleType!=null && roleType.intValue()!= ClerkRoleType.DEAL_CLERK.getValue()){
                resultDO.setErrCode(ErrorConstant.NO_PERMISSION_TO_ACCESS.getCode());
                resultDO.setErrMsg(ErrorConstant.NO_PERMISSION_TO_ACCESS.getMsg());
                return resultDO;
            }
        }
        shopDTO.setCreater(currentId);
        resultDO =  shopService.issueShop(shopDTO);
        return resultDO;
    }

    @Async
    public void sendNoteAndPush(ClerkHint record,Long shopId){
        Long issuerId = record.getIssuerId();//发布人ID
        Long currentId = HttpSessionUtils.getCurrentAppUserId(); //当前业务员Id
        Integer issuerType =  record.getIssuerType();//发布人类型(0用户1业务员)
        String linkmanPhone = record.getLinkmanPhone();//联系人手机
        //根据商铺ID查询
        ClerkAndShopInfoDTO clerkAndShopInfoDTO = shopService.queryClerkAndShopInfoByShopId(shopId);
        if(clerkAndShopInfoDTO==null){
            clerkAndShopInfoDTO = new ClerkAndShopInfoDTO();
        }
        if(issuerType!=null && issuerType.intValue()==0){
            ServiceNoticeReqDTO reqDTO = new ServiceNoticeReqDTO();
            reqDTO.setServiceTel(clerkAndShopInfoDTO.getPhone());
            reqDTO.setServiceName(clerkAndShopInfoDTO.getRealName());
            reqDTO.setShopAddress(clerkAndShopInfoDTO.getAddress());
            ResultDO<Customer> customerResult  = customerService.findCustomerInfoByCustomerId(issuerId);
            if(customerResult.isSuccess()){
                Customer customer = customerResult.getData();
                if(customer!=null){
                    reqDTO.setServiceType(ServiceNoticeType.SHOP_RENT_SEEKING.getValue());//旺铺寻租
                    reqDTO.setReceiveId(issuerId);
                    reqDTO.setReceiveType(ReceiveType.CUSTOMER.getValue());
//                    ResultDO<Boolean> pushResult = noticePushService.pushServiceSucNotify(reqDTO,linkmanPhone,customer.getDeviceId(),customer.getOsType());
//                    if(!pushResult.isSuccess()){
//                        Logger.error(ShopAO.class,pushResult.getErrMsg());
//                    }
                    //查询日程并修改状态
                    ResultDO<CustomerSchedule> customerSchedule =  scheduleService.queryScheduleByBizId(record.getId(),0);
                    CustomerSchedule schedule  = customerSchedule.getData();
                    if(schedule!=null){
                        ResultDO resultDO = scheduleService.ensureSchedule(schedule.getId(),customer.getId());
                        if(!resultDO.isSuccess()){
                            Logger.error(ShopAO.class,resultDO.getErrMsg());
                        }
                    }
                }
            }
        }

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
                            Logger.error(ShopAO.class, "推送小喇叭消息给业务员：" + JSON.toJSONString(clerk) + "失败，失败原因：" + pushResult.getErrCode() + "-" + pushResult.getErrMsg());
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
     * 修改商铺-租售信息
     * @param shopDTO
     * @return
     */
    public ResultDO<Boolean> updateShopRent(IssueShopDTO shopDTO){
        Long currentId = HttpSessionUtils.getCurrentAppUserId();
        shopDTO.setModifier(currentId);
        return shopService.updateShopRent(shopDTO);
    }
    /**
     * 修改商铺-客户信息
     * @param shopDTO
     * @return
     */
    public ResultDO<Boolean> updateShopContacter(IssueShopDTO shopDTO){
        Long currentId = HttpSessionUtils.getCurrentAppUserId();
        shopDTO.setModifier(currentId);
        return shopService.updateShopContacter(shopDTO);
    }
    /**
     * 修改商铺-经营状况信息
     * @param shopDTO
     * @return
     */
    public ResultDO<Boolean> updateShopOperateState (IssueShopDTO shopDTO){
        Long currentId = HttpSessionUtils.getCurrentAppUserId();
        shopDTO.setModifier(currentId);
        return shopService.updateShopOperateState(shopDTO);
    }
    /**
     * 修改商铺-租赁相关的信息
     * @param shopDTO
     * @return
     */
    public ResultDO<Boolean> updateShopRentParam (IssueShopDTO shopDTO){
        Long currentId = HttpSessionUtils.getCurrentAppUserId();
        shopDTO.setModifier(currentId);
        return shopService.updateShopRentParam(shopDTO);
    }
    /**
     * 修改商铺-位置相关的信息
     * @param shopDTO
     * @return
     */
    public ResultDO<DistrictBlockDTO> updateShopSite (IssueShopDTO shopDTO){
        Long currentId = HttpSessionUtils.getCurrentAppUserId();
        shopDTO.setModifier(currentId);
        return shopService.updateShopSite(shopDTO);
    }
    /**
     * 修改商铺-建筑相关的信息
     * @param shopDTO
     * @return
     */
    public ResultDO<Boolean> updateShopBuilding (IssueShopDTO shopDTO){
        Long currentId = HttpSessionUtils.getCurrentAppUserId();
        shopDTO.setModifier(currentId);
        return shopService.updateShopBuilding(shopDTO);
    }

    /**
     * 修改商铺-营运相关的信息
     * @param shopDTO
     * @return
     */
    public ResultDO<Boolean> updateShopService (IssueShopDTO shopDTO){
        Long currentId = HttpSessionUtils.getCurrentAppUserId();
        shopDTO.setModifier(currentId);
        return shopService.updateShopService(shopDTO);
    }
    /**
     * 修改商铺-配套设施相关的信息
     * @param shopDTO
     * @return
     */
    public ResultDO<Boolean> updateShopSupport (IssueShopDTO shopDTO){
        Long currentId = HttpSessionUtils.getCurrentAppUserId();
        shopDTO.setModifier(currentId);
        return shopService.updateShopSupport(shopDTO);
    }
    /**
     * 修改商铺-临铺信息
     * @param shopDTO
     * @return
     */
    public ResultDO<List<ShopNearDetailDTO>> updateShopNear (IssueShopDTO shopDTO){
        Long currentId = HttpSessionUtils.getCurrentAppUserId();
        shopDTO.setModifier(currentId);
        return shopService.updateShopNear(shopDTO);
    }

    /**
     * 删除临铺
     * @param nearId
     * @return
     */
    public ResultDO<Boolean> delShopNear(Long nearId){
        Long currentId = HttpSessionUtils.getCurrentAppUserId();
        return shopService.delShopNear(nearId,currentId);
    }

    /**
     * 临铺图片
     * @param nearId
     * @return
     */
    public ResultDO<List<ShopImgDTO>> shopNearImg(Long nearId){
        return shopService.shopNearImg(nearId);
    }

    /**
     * 商铺下架
     * @param shopUndoDTO
     * @return
     */
    public ResultDO<Boolean> unDoShop(ShopUndoDTO shopUndoDTO){
        ResultDO<Boolean> resultDO = new ResultDO<>();
        if(shopUndoDTO == null){
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }

        shopUndoDTO.setUndoType(UndoType.CLERK.getValue());
        shopUndoDTO.setUndoId(HttpSessionUtils.getCurrentAppUserId());

        return shopService.unDoShop(shopUndoDTO);
    }

    /**
     * 商铺上架
     * @param shopId
     * @return
     */
    public ResultDO<Boolean> putAwayShop(Long shopId){
        return shopService.putAwayShop(HttpSessionUtils.getCurrentAppUserId(), shopId);
    }

    /**
     * 商铺数目统计
     * @Author zhoumin
     * @return
     */
    public ResultDO<ShopCountDTO> countShopFromService() {
        return shopService.countShopFromService(HttpSessionUtils.getCurrentAppUserId());
    }

    /**
     * 查询商铺详情
     * @Author zhoumin
     * @param shopId
     * @return
     */
    public ResultDO<ShopDetailDTO> queryShopDetail(Long shopId) {
        ResultDO<ShopDetailDTO> resultDO = new ResultDO<>();
        if (shopId == null) {
            resultDO.setErrMsg("商铺id不能为空");
            return resultDO;
        }
        return shopService.queryShopDetail(shopId);
    }

    /**
     * 查询商铺照片
     * @Author zhoumin
     * @param shopId
     * @return
     */
    public ResultDO<List<ShopImgDTO>> queryShopImg(Long shopId){
        ResultDO<List<ShopImgDTO>> resultDO = new ResultDO<>();
        if (null == shopId) {
            resultDO.setErrMsg("商铺id不能为空");
            return resultDO;
        }
        return shopService.queryShopImg(shopId);
    }

    public ResultDO<String> claimShop(Long shopId) {
        ResultDO<String> resultDO = new ResultDO<>();
        Long clerkId = HttpSessionUtils.getCurrentAppUserId();
        if (clerkId == null) {
            resultDO.setErrMsg("请登录！");
            return resultDO;
        }
        //根据id  查询业务员信息
        ResultDO<Clerk> clerkResultDO = clerkService.getClerkInfoById(clerkId);
        if (!clerkResultDO.isSuccess()) {
            resultDO.setErrMsg(clerkResultDO.getErrMsg());
            resultDO.setErrCode(clerkResultDO.getErrCode());
            return resultDO;
        }
        Clerk clerk = clerkResultDO.getData();
        //判断是否是交易人员
        if (clerk.getRoleType()!=null && clerk.getRoleType() == 0) {
            //更新shop信息
            resultDO = shopService.updateClaimInfo(shopId,clerkId);
        }else {
            resultDO.setErrMsg("非交易人员不可认领该商铺！");
            return resultDO;
        }
        return resultDO;
    }

    /**
     * 生成商铺二维码
     * @param shopCodeDTO
     * @return
     */
    public ResultDO<String> shopCodeGenerate(ShopCodeDTO shopCodeDTO){
        ResultDO<String> resultDO = new ResultDO<>();
        if(shopCodeDTO==null){
            resultDO.setErrMsg("请求参数不能为空！");
            return resultDO;
        }
       /* ResultDO<Clerk> clerkResult = clerkService.getClerkInfoById(currentId);
        if(clerkResult.isSuccess()){
            Clerk clerk = clerkResult.getData();
            Integer roleType = clerk.getRoleType();
            if(roleType!=null && roleType.intValue()!= ClerkRoleType.CLERK.getValue()){
                resultDO.setErrCode(ErrorConstant.NO_PERMISSION_SHOP_CODE.getCode());
                resultDO.setErrMsg(ErrorConstant.NO_PERMISSION_SHOP_CODE.getMsg());
                return resultDO;
            }
        }*/
        return shopService.shopCodeGenerate(shopCodeDTO);
    }
    /**
     * 生成一个未使用的二维码
     * @param cityCode 城市编号
     * @return
     */
    public ResultDO<String>  createShopNumber(String cityCode){
        return baseShopNumberService.createShopNumber(cityCode);
    }
    /**
     * 单条插入商铺二维码
     * @param shopNumber
     * @return
     */
    public ResultDO<Boolean> insertSingleShopNumber(String shopNumber){
        ResultDO<Boolean> resultDO = new ResultDO<>();
        int count  = baseShopNumberService.insertSingleShopNumber(shopNumber);
        if(count>0){
            resultDO.setData(true);
            resultDO.setSuccess(true);
            return resultDO;
        }
        return resultDO;
    }
}
