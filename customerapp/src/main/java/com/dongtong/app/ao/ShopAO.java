package com.dongtong.app.ao;

import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.basic.enums.SmsUseSceneType;
import com.dongtong.basic.service.SmsService;
import com.dongtong.customer.domain.CustomerCollectedShop;
import com.dongtong.customer.domain.CustomerLiaison;
import com.dongtong.customer.dto.CustomerCorrectDTO;
import com.dongtong.customer.dto.CustomerDTO;
import com.dongtong.customer.dto.resp.ShopInfoDTO;
import com.dongtong.customer.dto.resp.StatisticDTO;
import com.dongtong.customer.service.CustomerInfoService;
import com.dongtong.customer.service.CustomerShopService;
import com.dongtong.shop.dto.*;
import com.dongtong.shop.enums.UndoType;
import com.dongtong.shop.query.BaseQuery;
import com.dongtong.shop.query.ShopCollectedQuery;
import com.dongtong.shop.query.ShopCustomerQuery;
import com.dongtong.shop.service.ShopService;
import com.fc.common.redis.RedisUtil;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private CustomerInfoService customerInfoService;
    @Autowired
    private CustomerShopService customerShopService;
    @Autowired
    private SmsService smsService;

    public ResultDO<Page<ShopCustomerDTO>> queryShopByCustomer(ShopCustomerQuery query){
        ResultDO<Page<ShopCustomerDTO>> resultDO = new ResultDO<>();
        String cacheKey = query.cacheKey()+"_v1.2";
        Long currentAppUserId = HttpSessionUtils.getCurrentAppUserId();
        if( currentAppUserId != null){
            cacheKey = cacheKey + "_" + currentAppUserId;
        }
        /**
         * 添加缓存控制
         */
        Object obj = RedisUtil.get(cacheKey);
        if(obj != null){
            ResultDO<Page<ShopCustomerDTO>> resultDO1 = (ResultDO<Page<ShopCustomerDTO>>)obj;

            return resultDO1;
        }
        ResultDO<Page<ShopCustomerDTO>> resultDO1 = shopService.queryShopByCustomer(query, HttpSessionUtils.getCurrentAppUserId());
        /**
         * 缓存半小时
         */
        if(resultDO1.isSuccess()){
            RedisUtil.set(cacheKey, resultDO1, 1800L);
        }
        return resultDO1;
    }

    public ResultDO<ShopScanCountDTO> queryMyShopScanCount(){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        ResultDO<ShopScanCountDTO> resultDO = new ResultDO<>();
        ResultDO<CustomerDTO> resultDO1 = customerInfoService.customerInfo(userId);

        if(!resultDO1.isSuccess()){
            resultDO.setErrMsg(resultDO1.getErrMsg());
            resultDO.setErrCode(resultDO1.getErrCode());
            return resultDO;
        }

        // 根据用户id查询用户手机号
        String phone = resultDO1.getData().getPhone();
        return shopService.queryMyShopScanCount(phone);
    }

    public ResultDO<Page<ShopCustomerPublishDTO>> queryMyPublishShop(BaseQuery query){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        ResultDO<Page<ShopCustomerPublishDTO>> resultDO = new ResultDO<>();
        ResultDO<CustomerDTO> resultDO1 = customerInfoService.customerInfo(userId);

        if(!resultDO1.isSuccess()){
            resultDO.setErrMsg(resultDO1.getErrMsg());
            resultDO.setErrCode(resultDO1.getErrCode());
            return resultDO;
        }
        // 根据用户id查询用户手机号
        String phone = resultDO1.getData().getPhone();
        if(query == null) query = new BaseQuery();

        return shopService.queryMyPublishShop(query, phone);
    }

    public ResultDO<Boolean> unDoShop(ShopUndoDTO shopUndoDTO){

        ResultDO<Boolean> resultDO = new ResultDO<>();
        if(shopUndoDTO == null){
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }
        Integer type = shopUndoDTO.getType();
        if(type==null){
            resultDO.setErrMsg("原因类型不能为空");
            return resultDO;
        }else if (type.intValue()<1||type.intValue()>3){
            resultDO.setErrMsg("原因类型输入不正确！");
            return resultDO;
        }
        shopUndoDTO.setUndoType(UndoType.CUSTOMER.getValue());
        shopUndoDTO.setUndoId(HttpSessionUtils.getCurrentAppUserId());
        return shopService.unDoShop(shopUndoDTO);
    }

    /**
     * 我收藏的商铺-排名统计
     * add by xiehb
     * @return
     */
    public ResultDO<StatisticDTO> myCollectStatistic(){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        return customerInfoService.myCollectStatistic(userId);
    }

    /**
     * 我收藏的商铺-商铺列表
     * add by xiehb
     * @param query
     * @return
     */
    public ResultDO<Page<ShopCollectedCustomerDTO>> shopCollectedList(ShopCollectedQuery query){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        if(query==null){
            query = new ShopCollectedQuery();
        }
        query.setCustomerId(userId);
        return shopService.shopCollectedList(query);
    }
    /**
     * 我收藏的商铺-取消收藏
     * add by xiehb
     * @return
     */
    public ResultDO<Boolean> cancelShopCollected(Long shopId){
        ResultDO<Boolean>  resultDO = new ResultDO<>();
        Long customerId = HttpSessionUtils.getCurrentAppUserId();
        if(shopId==null){
            resultDO.setErrMsg("商铺ID不能为空！");
            return resultDO;
        }
        return customerInfoService.cancelShopCollected(shopId,customerId);
    }

    /**
     * 我浏览的商铺-排名统计
     * add by xiehb
     * @return
     */
    public ResultDO<StatisticDTO> myBrowseStatistic(){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        return customerInfoService.myBrowseStatistic(userId);
    }

    /**
     * 我浏览的商铺-商铺列表
     * add by xiehb
     * @param query
     * @return
     */
    public ResultDO<Page<ShopBrowseCustomerDTO>> shopBrowseList(ShopCollectedQuery query){
        Long userId = HttpSessionUtils.getCurrentAppUserId();
        if(query==null){
            query = new ShopCollectedQuery();
        }
        query.setCustomerId(userId);
        return shopService.shopBrowseList(query);
    }
    /**
     * 我浏览的商铺-删除浏览
     * add by xiehb
     * @return
     */
    public ResultDO<Boolean> deleteShopBrowse(Long shopId){
        ResultDO<Boolean>  resultDO = new ResultDO<>();
        Long customerId = HttpSessionUtils.getCurrentAppUserId();
        if(shopId==null){
            resultDO.setErrMsg("商铺ID不能为空！");
            return resultDO;
        }
        return customerInfoService.deleteShopBrowse(shopId,customerId);
    }

    /**
     * 商铺数目统计
     * @Author zhoumin
     * @return
     */
    public ResultDO<ShopCountDTO> countShopFromClient() {
        return shopService.countShopFromClient();
    }

    /**
     * 查询商铺详情
     * @Author zhoumin
     * @param shopId
     * @return
     */
    public ResultDO<ShopDetailDTO> queryShopDetail (Long shopId) {
        ResultDO<ShopDetailDTO> resultDO = new ResultDO<>();
        if (shopId == null) {
            resultDO.setErrMsg("商铺id不能为空");
            return resultDO;
        }
        Long customerId = HttpSessionUtils.getCurrentAppUserId();
        //商铺浏览记录
        customerShopService.createBrowseShop(shopId,customerId);
        //商铺详情
        resultDO = shopService.queryShopDetail(shopId);
        if (!resultDO.isSuccess()) {
            return resultDO;
        }
        ShopDetailDTO shopDetailDTO = resultDO.getData();
        //联系人信息脱敏显示，统一显示为X老板，X取第一个字；
        if (!ValidateHelper.isEmptyString(shopDetailDTO.getContacter())) {
            shopDetailDTO.setContacter(shopDetailDTO.getContacter().substring(0,1)+"老板");
        }
        if (customerId == null) {
            return resultDO;
        }
        //判断该商铺是否被收藏、约看
        ResultDO<ShopInfoDTO> shopInfoDTOResultDO = customerShopService.queryShopInfo(shopId,customerId);

        if (resultDO.isSuccess() && shopInfoDTOResultDO.isSuccess()) {
            if (shopInfoDTOResultDO.getData().getVisitId() != null) {
                shopDetailDTO.setIsVisit(true);
            }else {
                shopDetailDTO.setIsVisit(false);
            }
            if (shopInfoDTOResultDO.getData().getCollectedId() != null) {
                shopDetailDTO.setIsCollected(true);
            }else {
                shopDetailDTO.setIsCollected(false);
            }
        }
        return resultDO;
    }

    /**
     * 商铺纠错
     * @Author zhoumin
     * @param dto
     * @return
     */
    public ResultDO<Boolean> createCorrect(CustomerCorrectDTO dto) {
        ResultDO<Boolean> resultDO = new ResultDO<>();
        if (null == dto) {
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }
        //手机号码正则校验
        String phone = dto.getContactMobile();
        if (!ValidateHelper.isEmptyString(phone)) {
            Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
            Matcher m = p.matcher(phone);
            if (!m.matches()) {
                resultDO.setErrMsg("手机格式错误");
                return resultDO;
            }
        }else {
            resultDO.setErrMsg("手机号不能为空");
            return resultDO;
        }
        Long customerId = HttpSessionUtils.getCurrentAppUserId();
        dto.setCustomerId(customerId);

        //查询当前用户信息
        ResultDO<CustomerDTO> customerDTOResultDO = customerInfoService.customerInfo(customerId);
        if(!customerDTOResultDO.isSuccess()){
            resultDO.setErrMsg(customerDTOResultDO.getErrMsg());
            return resultDO;
        }

        //修改手机号
        if (! dto.getContactMobile().equals(customerDTOResultDO.getData().getPhone())) {
            //验证短信验证码
            if (ValidateHelper.isEmpty(dto.getSmsVerifyCode()) || null == dto.getMessageId()) {
                resultDO.setErrMsg("短信验证码不能为空");
                return resultDO;
            }
            ResultDO<Boolean> smsResult=smsService.verifySmsVerifyCode(dto.getMessageId(),dto.getSmsVerifyCode(), dto.getContactMobile(), SmsUseSceneType.CORRECT_SHOP.getValue());
            if(!smsResult.isSuccess()){
                resultDO.setErrMsg(smsResult.getErrMsg());
                return resultDO;
            }
            //验证图形验证码
            if(!ValidateHelper.isEmpty(dto.getPicVerifyCode()) && null != dto.getPicVerifyId()){
                ResultDO<Boolean> picResult = smsService.verifyPicVerify(dto.getPicVerifyId(),dto.getPicVerifyCode());
                if(!picResult.isSuccess()){
                    resultDO.setErrMsg(smsResult.getErrMsg());
                    return resultDO;
                }
            }
        }
        return customerShopService.createCorrect(dto);
    }

    /**
     * 商铺约看
     * @Author zhoumin
     * @param visitInfoReqDTO
     * @return
     */
    /*public ResultDO<Long> createVisit(VisitInfoReqDTO visitInfoReqDTO) {
        ResultDO<Long> resultDO = new ResultDO<>();
        Long shopId = visitInfoReqDTO.getShopId();
        if (null == shopId) {
            resultDO.setErrMsg("商铺ID不能为空");
            return resultDO;
        }
        //查询业务员信息
        ClerkDTO clerkDTO = shopService.queryClerkByShopId(shopId);
        visitInfoReqDTO.setClerkId(clerkDTO.getAllClerkIds());
        visitInfoReqDTO.setClerkName(clerkDTO.getClerkName());
        Long customerId = HttpSessionUtils.getCurrentAppUserId();
        visitInfoReqDTO.setCustomerId(customerId);
        resultDO = customerShopService.createVisit(visitInfoReqDTO);
        if (resultDO.isSuccess()) {
            //添加日程
            CustomerScheduleDTO scheduleDTO = new CustomerScheduleDTO();
            BeanUtils.copyProperties(visitInfoReqDTO , scheduleDTO);
            scheduleDTO.setCustomerId(customerId);
            scheduleDTO.setBizId(resultDO.getData());
            scheduleDTO.setType(1);//0-旺铺寻租 1--带我踩盘 2-签约租铺
            scheduleDTO.setApplicationTime(new Date());
            scheduleDTO.setMeetTime(visitInfoReqDTO.getVisitTime());
            scheduleDTO.setStatus(0);//0-服务受理中 1-已完成 2-已撤销
            scheduleService.addSchedule(scheduleDTO);
        }
        return resultDO;
    }*/

    /**
     * 商铺收藏
     * @Author zhoumin
     * @param collectedShop
     * @return
     */
    public ResultDO<Long> createCollected(CustomerCollectedShop collectedShop){
        ResultDO<Long> resultDO = new ResultDO<>();
        if (null == collectedShop) {
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }
        Long customerId = HttpSessionUtils.getCurrentAppUserId();
        collectedShop.setCustomerId(customerId);
        return customerShopService.createCollected(collectedShop);
    }

    /**
     * 商铺电话记录
     * @Author zhoumin
     * @param customerLiaison
     * @return
     */
    public ResultDO<Boolean> createLiaisonRecord(CustomerLiaison customerLiaison) {
        ResultDO<Boolean> resultDO = new ResultDO<>();
        if (null == customerLiaison) {
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }
        //手机号码正则校验
        String phone = customerLiaison.getPhone();
        if (!ValidateHelper.isEmptyString(phone)) {
            Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
            Matcher m = p.matcher(phone);
            if (!m.matches()) {
                resultDO.setErrMsg("手机格式错误");
                return resultDO;
            }
        }
        Long customerId = HttpSessionUtils.getCurrentAppUserId();
        customerLiaison.setCustomerId(customerId);
        return customerShopService.createLiaisonRecord(customerLiaison);
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

    public ResultDO<Long> getShopId(String shopCode) {
        ResultDO<Long> resultDO = new ResultDO<>();
        if (null == shopCode) {
            resultDO.setErrMsg("二维码编号不能为空");
            return resultDO;
        }
        return shopService.getShopId(shopCode);
    }

}
