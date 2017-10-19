package com.dongtong.clerk.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.enums.WorkServiceType;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.clerk.bo.ClerkHintBO;
import com.dongtong.clerk.constant.ClerkHintHasPosterEnum;
import com.dongtong.clerk.constant.ClerkHintStatusEnum;
import com.dongtong.clerk.constant.ErrorConstant;
import com.dongtong.clerk.constant.IssuerTypeEnum;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.domain.ClerkHint;
import com.dongtong.clerk.dto.*;
import com.dongtong.clerk.dto.resp.ReleaseTotalRespDTO;
import com.dongtong.clerk.enums.ClerkHintStatus;
import com.dongtong.clerk.enums.ClerkRoleType;
import com.dongtong.clerk.enums.ClerkStatus;
import com.dongtong.clerk.enums.IssuerType;
import com.dongtong.clerk.manager.ClerkHintManager;
import com.dongtong.clerk.manager.ClerkManager;
import com.dongtong.clerk.query.*;
import com.fc.common.redis.RedisUtil;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.date.DateUtils;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * @author sunyaping
 * @Package com.dongtong.clerk.service
 * @Description :TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-08 13:30
 * version V1.0.0
 **/
@Service
@Slf4j
public class ClerkHintServiceImpl implements  ClerkHintService {

    @Autowired
    private ClerkHintManager clerkHintManager;

    @Autowired
    private ClerkManager clerkManager;

    @Autowired
    private NoticePushService noticePushService;

    private static int releaseTotal=12;            //系统计数，默认12 发布寻租老板人数

    public ResultDO<Long> issueClue(ClerkHintDTO clerkHintDTO){
        ResultDO<Long> resultDO = new ResultDO<>();
        String checkResult  = checkIssueClue(clerkHintDTO);
        if(!ValidateHelper.isEmptyString(checkResult)){
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }
        Long clerkHintId = null;
        clerkHintDTO.setIssuerType(IssuerType.CUSTOMER.getValue());   //寻租为用户发布
        clerkHintDTO.setStatus(ClerkStatus.SHELTERS.getValue());       //发起申请时线索状态为待认领

        try {
            clerkHintId = clerkHintManager.issueClue(clerkHintDTO);
        } catch (Exception e) {
            Logger.error(this.getClass(),"发布寻租失败，"+e.getMessage(),e);
            resultDO.setSuccess(false);
            resultDO.setErrMsg(e.getMessage());
            return resultDO;
        }
        if(clerkHintId==null){
            resultDO.setSuccess(false);
            resultDO.setErrMsg("插入失败");
            return  resultDO;
        }
        if(RedisUtil.get("releaseTotal")!=null){
            releaseTotal=(int)RedisUtil.get("releaseTotal");
        }
        //每当有一人发布旺铺寻租，则该基数增加1/2/3（随机）
        RedisUtil.set("releaseTotal",releaseTotal+1);
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(clerkHintId);
        return  resultDO;
    }

    /**
     * 业务员发布线索
     * @param clerkHintDTO
     * @return
     */
    public ResultDO<Long> publishHintByClerk(ClerkHintDTO clerkHintDTO){
        ResultDO<Long> resultDO = new ResultDO<>();
        String checkResult  = checkpublishHintByClerk(clerkHintDTO);
        if(!ValidateHelper.isEmptyString(checkResult)){
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }

        Long clerkHintId = null;
        clerkHintDTO.setIssuerType(IssuerTypeEnum.CLERK.getValue());   //寻租为业务员发布
        clerkHintDTO.setStatus(ClerkHintStatusEnum.WAITING_TRADE_CLAIM.getValue());       //发业务员起申请时线索状态为交易员待认领
        clerkHintDTO.setOwnerId(clerkHintDTO.getIssuerId());
        clerkHintDTO.setExpandClerkId(clerkHintDTO.getIssuerId());
        try {
            ResultDO hindDO = clerkHintManager.publishHintByClerk(clerkHintDTO);
            if(RedisUtil.get("releaseTotal")!=null){
                releaseTotal=(int)RedisUtil.get("releaseTotal");
            }
            if(hindDO.isSuccess()){
                Long expandClerkId = clerkHintDTO.getExpandClerkId();
                if(!ValidateHelper.isEmpty(expandClerkId)){
                    Clerk clerk = clerkManager.getClerkInfoById(expandClerkId);
                    if(!ValidateHelper.isEmpty(clerk)){
                        this.pushTrumpetMessageToAllClerk(WorkServiceType.FIELD_TRIP.getValue(),clerk.getRealName(),clerkHintDTO.getShopAddress());
                    }
                }
            }
            //每当有一人发布旺铺寻租，则该技术增加1/2/3（随机）
            RedisUtil.set("releaseTotal",releaseTotal+(int)(Math.random()*3+1));
            return hindDO;
        } catch (Exception e) {
            Logger.error(this.getClass(),"发布线索失败，"+e.getMessage(),e);
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.HINT_PUBLISH_FAILER.getCode());
            resultDO.setErrMsg(ErrorConstant.HINT_PUBLISH_FAILER.getMsg()+","+e.getMessage());
            return resultDO;
        }
    }

    /**
     * 异步推送小喇叭消息给所有业务员，推送失败不能影响主线业务
     * @param messageType
     * @param clerkName
     */
    @Async
    private void pushTrumpetMessageToAllClerk(final Integer messageType, final String clerkName,final String roadName){
        try {
            //所有业务员列表
            List<Clerk> clerkList = clerkManager.getAllClerkInfo();
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
                        ResultDO<Boolean> pushResult = noticePushService.pushTrumpetNotice(clerk.getDeviceId(), clerk.getOsType(), messageType, clerkName, roadName);
                        if (!pushResult.isSuccess()) {
                            Logger.error(ClerkHintServiceImpl.class, "推送小喇叭消息给业务员：" + JSON.toJSONString(clerk) + "失败，失败原因：" + pushResult.getErrCode() + "-" + pushResult.getErrMsg());
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
     * 根据id查找线索详情
     *
     * @param clerkHintId
     * @return
     */
    @Override
    public ResultDO<ClerkHint> getClerkHintInfoById(Long clerkHintId) {
        ResultDO<ClerkHint> resultDO = new ResultDO<ClerkHint>();
        if (ValidateHelper.isEmpty(clerkHintId)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        ClerkHint clerkHint = clerkHintManager.getClerkHintById(clerkHintId);
        if (clerkHint == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(clerkHint);
        return resultDO;
    }

    /**
     * 旺铺寻租使用人数统计
     * @return
     */
    public ResultDO<ReleaseTotalRespDTO> releaseTotal(){
        ResultDO<ReleaseTotalRespDTO> resultDO = new ResultDO<>();
        ReleaseTotalRespDTO releaseTotalRespDTO=new ReleaseTotalRespDTO();
        int total=0;
        if(RedisUtil.exists("releaseTotal")){
            total=(int) RedisUtil.get("releaseTotal");
        }
        releaseTotalRespDTO.setReleaseTotal(total);
        releaseTotalRespDTO.setAvgPeople(30);
        resultDO.setSuccess(true);
        resultDO.setData(releaseTotalRespDTO);
        return resultDO;
    }

    @Override
    public ResultDO <Page<ClerkHintBO>> getShopClueList(ClerkHintDTO clerkHintDTO, Page page) {
        ResultDO<Page<ClerkHintBO>> result=new ResultDO();
        Page<ClerkHintBO> listPage=clerkHintManager.getShopClueList(clerkHintDTO,page);
        result.setData(listPage);
        result.setSuccess(true);
        return result;
    }

    @Override
    public ResultDO<ClerkHintDTO> getShopClueInfo(ClerkHintDTO clerkHintDTO) {
        ResultDO<ClerkHintDTO> result=new ResultDO<ClerkHintDTO>();
        ClerkHintDTO clerkHint= clerkHintManager.getShopClueInfo(clerkHintDTO);
        result.setData(clerkHint);
        result.setSuccess(true);
        return result;
    }

    @Override
    public ResultDO shopClaim(ClerkHintDTO clerkHintDTO) {
        Logger.debug(this.getClass(),clerkHintDTO.getOwnerId()+"");
        return clerkHintManager.shopClaim(clerkHintDTO);
    }

    @Override
    public ResultDO updateSubscribeTime(ClerkHintDTO clerkHintDTO) throws ParseException {
        return clerkHintManager.updateSubscribeTime(clerkHintDTO);
    }

    @Override
    public ResultDO updateClueStatus(ClerkHintDTO clerkHintDTO) {
        return clerkHintManager.updateClueStatus(clerkHintDTO);
    }

    @Override
    public ResultDO<ClerkHintDTO> queryClerkHintDetail(ClerkHintQuery clerkHintQuery) {
        if(ValidateHelper.isEmpty(clerkHintQuery.getClerkId())){
            ResultDO resultDO = new ResultDO();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_CLERT_HINT_ID.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_CLERT_HINT_ID.getMsg());
            return resultDO;
        }

        ResultDO<ClerkHintDTO> resultDO = clerkHintManager.queryClerkHintDetail(clerkHintQuery);
        return resultDO;
    }

    @Override
    public ResultDO<Page<ClerkHintDTO>> queryClerkHintList(ClerkHintListQuery clerkHintListQuery) {
        Page<ClerkHintDTO> page = new Page<>(clerkHintListQuery.getPageNumber(), clerkHintListQuery.getPageSize());
//        ResultDO checkDO = checkQueryClertHintList(clerkHintListQuery);
//        if(!checkDO.isSuccess()){
//            return checkDO;
//        }
        if(!ValidateHelper.isEmpty(clerkHintListQuery.getOwnerId())){
            Clerk clerk = clerkManager.getClerkInfoById(clerkHintListQuery.getOwnerId());
            if(!ValidateHelper.isEmpty(clerk)){
                if(clerk.getRoleType().intValue()== ClerkRoleType.DEAL_CLERK.getValue()){
                    clerkHintListQuery.setTradeClerkId(clerk.getId());
                    clerkHintListQuery.setOwnerId(null);
                }else if(clerk.getRoleType().intValue()== ClerkRoleType.EXPAND_CLERK.getValue()){
                    clerkHintListQuery.setExpandClerkId(clerk.getId());
                    clerkHintListQuery.setOwnerId(null);
                }
            }
        }

        ResultDO resultDO = new ResultDO();
        try {
            if(ValidateHelper.isEmpty(clerkHintListQuery.getHintOwnType())){
                clerkHintListQuery.setHintOwnType(1);//线索归属类型参数为空则默认全部
            }
            //排序字段设置
            if(ValidateHelper.isEmpty(clerkHintListQuery.getOrderType())){
                clerkHintListQuery.setOrderColumn("ch.create_time desc");
            }else if(clerkHintListQuery.getOrderType()==1){
                clerkHintListQuery.setOrderColumn("ch.create_time desc");
            }else if(clerkHintListQuery.getOrderType()==2){
                clerkHintListQuery.setOrderColumn("ch.modify_time desc");
            }else{
                clerkHintListQuery.setOrderColumn("ch.create_time desc");
            }
            page.setQuery(clerkHintListQuery);
            resultDO.setData(clerkHintManager.queryClerkHintList(page));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "查询线索列表失败",e);
            resultDO.setErrCode(ErrorConstant.SYSTEM_ERROR.getCode());
            resultDO.setErrMsg(ErrorConstant.SYSTEM_ERROR.getMsg());
        }
        return resultDO;
    }

    @Override
    public ResultDO<List<ClerkHint4MapDTO>> queryClerkHintListForMap(ClerkHintList4MapQuery clerkHintList4MapQuery) {
//        Page<ClerkHint4MapDTO> page = new Page<>(clerkHintList4MapQuery.getPageNumber(), clerkHintList4MapQuery.getPageSize());
        ResultDO checkDO = checkClerkHintList4Map(clerkHintList4MapQuery);
        if(!checkDO.isSuccess()){
            return checkDO;
        }

        ResultDO resultDO = new ResultDO();
        try {
            if(ValidateHelper.isEmpty(clerkHintList4MapQuery.getOwnType())){
                clerkHintList4MapQuery.setOwnType(1);//线索归属类型参数为空则默认全部
            }
//            page.setQuery(clerkHintList4MapQuery);
            resultDO.setData(clerkHintManager.queryClerkHint4MapList(clerkHintList4MapQuery));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "查询线索列表失败",e);
            resultDO.setErrCode(ErrorConstant.SYSTEM_ERROR.getCode());
            resultDO.setErrMsg(ErrorConstant.SYSTEM_ERROR.getMsg());
        }
        return resultDO;
    }

    @Override
    public ResultDO<Integer> getDeadTimeCount(ClerkHintDTO clerkHintDTO) {
        return clerkHintManager.getDeadTimeCount(clerkHintDTO);
    }

    @Override
    public ResultDO<Page<ClerkHintBO>> selectNeedByPage(ClerkHintDTO clerkHintDTO, Page page) {
        ResultDO<Page<ClerkHintBO>> result=new ResultDO();
        Page<ClerkHintBO> listPage=clerkHintManager.selectNeedByPage(clerkHintDTO,page);
        result.setData(listPage);
        result.setSuccess(true);
        return result;
    }

    /**
     * 修改线索状态
     * @param clerkHintDTO
     * @return
     */
    @Override
    public ResultDO updateClerkHintStatus(ClerkHintDTO clerkHintDTO) {
        return clerkHintManager.updateClerkHintStatus(clerkHintDTO);
    }

    @Override
    public ResultDO updateShopImgInfo(ClerkHintImgsDTO clerkHintImgsDTO) {
        ResultDO resultDO = null;
        if(ValidateHelper.isEmpty(clerkHintImgsDTO)
                && ValidateHelper.isEmpty(clerkHintImgsDTO.getModifier())
                && ValidateHelper.isEmpty(clerkHintImgsDTO.getId())
                && (clerkHintImgsDTO.getShopImgList()==null || clerkHintImgsDTO.getShopImgList().size()==0)){
            resultDO = new ResultDO();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        return clerkHintManager.updateShopImgInfo(clerkHintImgsDTO);
    }

    @Override
    public ResultDO updatePositionInfo(ClerkHintDTO clerkHintDTO) {
        ResultDO resultDO = null;
        if(ValidateHelper.isEmpty(clerkHintDTO)
                && ValidateHelper.isEmpty(clerkHintDTO.getModifier())
                && ValidateHelper.isEmpty(clerkHintDTO.getId())){
            resultDO = new ResultDO();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }

        resultDO = clerkHintManager.updatePositionInfo(clerkHintDTO);
        return resultDO;
    }


    @Override
    public ResultDO updateCustomerInfo(ClerkHintDTO clerkHintDTO) {
        ResultDO resultDO = null;
        if(ValidateHelper.isEmpty(clerkHintDTO)
                && ValidateHelper.isEmpty(clerkHintDTO.getOwnerId())
                && ValidateHelper.isEmpty(clerkHintDTO.getId())){
            resultDO = new ResultDO();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }

        resultDO = clerkHintManager.updateCustomerInfo(clerkHintDTO);
        return resultDO;
    }

    /**
     * 待确认、待核准线索列表
     * @param query
     * @Author zhoumin
     * @return
     */
    @Override
    public ResultDO<Page<ClerkHintList>> queryHintList(ClerkHintTypeQuery query) {
        ResultDO<Page<ClerkHintList>> resultDO = new ResultDO();
        if (null == query.getId()){
            resultDO.setErrMsg(ErrorConstant.NULL_CLERK_ID.getMsg());
            resultDO.setErrCode(ErrorConstant.NULL_CLERK_ID.getCode());
            return resultDO;
        }
        if (null == query.getType()){
            resultDO.setErrMsg(ErrorConstant.NULL_TYPE.getMsg());
            resultDO.setErrCode(ErrorConstant.NULL_TYPE.getCode());
            return resultDO;
        }
        Page<ClerkHintList> page = new Page<>(query.getPageNumber(),query.getPageSize());
        try {
            page.setQuery(query);
            resultDO.setData(clerkHintManager.queryHintList(page));
            resultDO.setSuccess(true);
        }catch (Exception e) {
            Logger.error(e, "查询待核准或待确认线索列表失败",e);
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    @Override
    public ResultDO updateBuildingInfo(ClerkHintDTO clerkHintDTO) {
        ResultDO resultDO = null;
        if(ValidateHelper.isEmpty(clerkHintDTO)
                && ValidateHelper.isEmpty(clerkHintDTO.getModifier())
                && ValidateHelper.isEmpty(clerkHintDTO.getId())){
            resultDO = new ResultDO();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }

        resultDO = clerkHintManager.updateBuildingInfo(clerkHintDTO);
        return resultDO;
    }

    @Override
    public ResultDO<ClerkHintStatisticsDTO> hintStatistics(Long userId) {
        return clerkHintManager.hintStatistics(userId);
    }

    @Override
    public ResultDO confirmHint(ClerkHintComfirmDTO clerkHintComfirmDTO) {
        ResultDO resultDO = null;
        if(ValidateHelper.isEmpty(clerkHintComfirmDTO)
                && ValidateHelper.isEmpty(clerkHintComfirmDTO.getClerkId())
                && ValidateHelper.isEmpty(clerkHintComfirmDTO.getHintId())){
            resultDO = new ResultDO();
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }

        resultDO = clerkHintManager.confirmHint(clerkHintComfirmDTO);
        return resultDO;
    }

    public ResultDO<ClerkHintDetailDTO> getHintDetailInfo(ClerkHintDetailReqDTO reqDTO) {
        ResultDO<ClerkHintDetailDTO> resultDO = new ResultDO<>();
        String check = checkParms(reqDTO);
        if (null != check){
            resultDO.setErrMsg(check);
            return resultDO;
        }
        //根据线索id查找线索信息
        try {
            ClerkHint clerkHint = clerkHintManager.getClerkHintById(reqDTO.getId());
            if (null == clerkHint){
                resultDO.setErrMsg(ErrorConstant.CLERT_HINT_NOT_EXIST.getMsg());
                resultDO.setErrCode(ErrorConstant.CLERT_HINT_NOT_EXIST.getCode());
                return resultDO;
            }
            /*if (clerkHint.getOwnerId() != reqDTO.getClerkId()){
                resultDO.setErrMsg(ErrorConstant.HINT_NOT_BELONG_CLERK.getMsg());
                resultDO.setErrCode(ErrorConstant.HINT_NOT_BELONG_CLERK.getCode());
                return resultDO;
            }*/

            if (reqDTO.getType() == 1 && clerkHint.getStatus() != ClerkHintStatus.EXPAND_CLERK_SURE.getValue()){//1:线索待确认
                resultDO.setErrMsg(ErrorConstant.ERROR_HINT_STATUS.getMsg());
                resultDO.setErrCode(ErrorConstant.ERROR_HINT_STATUS.getCode());
                return resultDO;
            }
            if (reqDTO.getType() == 2 && clerkHint.getStatus() != ClerkHintStatus.AWAIT_EXAMINE.getValue()){//2:线索待实勘
                resultDO.setErrMsg(ErrorConstant.ERROR_HINT_STATUS.getMsg());
                resultDO.setErrCode(ErrorConstant.ERROR_HINT_STATUS.getCode());
                return resultDO;
            }

            //返回查询详情
            ClerkHintDetailDTO clerkHintDetailDTO = clerkHintManager.getHintDetailInfo(reqDTO.getId());
            //处理超时文案
            if (!ValidateHelper.isEmpty(clerkHintDetailDTO.getOverTime()) && !ValidateHelper.isEmpty(clerkHintDetailDTO.getOverTimeRemind())){
                int overTime = DateUtils.string2Date(clerkHintDetailDTO.getOverTime()).compareTo(DateUtils.getCurrentDate());//超时时间
                int overTimeRemind = DateUtils.string2Date(clerkHintDetailDTO.getOverTimeRemind()).compareTo(DateUtils.getCurrentDate());//开始提醒时间

                if (overTimeRemind > 0 && overTime > 0){
                    clerkHintDetailDTO.setOverTimeRemindStr("请于"+clerkHintDetailDTO.getOverTimeStr()+"前完成线索确认");
                }
                if (overTimeRemind < 0 && overTime > 0){
                    clerkHintDetailDTO.setOverTimeRemindStr("即将超时，请尽快处理。");
                }
            }
            resultDO.setData(clerkHintDetailDTO);
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "查找待确认、待实勘线索详情失败！");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    /**
     * check参数
     * @param reqDTO
     * @return
     */
    private String checkParms(ClerkHintDetailReqDTO reqDTO){
        if (null == reqDTO){
            return "请求参数不能为空！";
        }
        if (null == reqDTO.getClerkId()){
            return "业务员id不能为空！";
        }
        if (null == reqDTO.getType()){
            return "查询类型不能为空！";
        }
        if (null == reqDTO.getId()){
            return "线索id不能为空！";
        }
        return null;
    }

    @Override
    public ResultDO tradeClerkClaim(ClerkHintComfirmDTO clerkHintComfirmDTO) {
        ResultDO resultDO = new ResultDO();
        if(ValidateHelper.isEmpty(clerkHintComfirmDTO)
                && ValidateHelper.isEmpty(clerkHintComfirmDTO.getClerkId())
                && ValidateHelper.isEmpty(clerkHintComfirmDTO.getHintId())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        try {
            resultDO = clerkHintManager.tradeClerkClaim(clerkHintComfirmDTO);
        }catch (Exception e){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.TRADE_CLAIM_ERROR.getCode());
            resultDO.setErrMsg(ErrorConstant.TRADE_CLAIM_ERROR.getMsg());
        }
        return resultDO;
    }

    /**
     * @description 业务员签约/约看列表v1.2
     * @package com.dongtong.clerk.service
     * @author liaozm
     * @date 2017/8/7 15:38
     * @params
     * @return
     */
    @Override
    public ResultDO<Page<ClerkScheduleListDTO>> querySignOrViewList(ClerkSignTypeQuery query) {
        ResultDO<Page<ClerkScheduleListDTO>> resultDO = new ResultDO<Page<ClerkScheduleListDTO>>();
        if (ValidateHelper.isEmpty(query.getId()) || ValidateHelper.isEmpty(query.getType())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Page<ClerkScheduleListDTO> page = new Page<>(query.getPageNumber(),query.getPageSize());
        try {
            page.setQuery(query);
            if(query.getType()==1){
                resultDO.setData(clerkHintManager.queryViewList(page));
                resultDO.setSuccess(true);
            }else if(query.getType()==2){
                resultDO.setData(clerkHintManager.querySignList(page));
                resultDO.setSuccess(true);
            }else{
                resultDO.setSuccess(false);
                resultDO.setErrCode(ErrorConstant.PARAMETER_TYPE_ERROR.getCode());
                resultDO.setErrMsg(ErrorConstant.PARAMETER_TYPE_ERROR.getMsg());
            }
        }catch (Exception e) {
            Logger.error(e, "业务员签约/约看列表v1.2失败",e);
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    /**
     * @description 约看/签约详情查询v1.2
     * @package com.dongtong.clerk.service
     * @author liaozm
     * @date 2017/8/8 10:52
     * @params
     * @return
     */
    @Override
    public ResultDO<ClerkSignOrViewDetailDTO> querySignOrViewDetail(SignOrViewQuery query) {
        ResultDO<ClerkSignOrViewDetailDTO> resultDO = new ResultDO<ClerkSignOrViewDetailDTO>();
        if (ValidateHelper.isEmpty(query.getId()) || ValidateHelper.isEmpty(query.getType())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }

        try {
            if(query.getType()==1){//约看
                resultDO.setData(clerkHintManager.queryViewDetail(query.getId()));
                resultDO.setSuccess(true);
            }else if(query.getType()==2){//签约
                resultDO.setData(clerkHintManager.querySignDetail(query.getId()));
                resultDO.setSuccess(true);
            }else{
                resultDO.setSuccess(false);
                resultDO.setErrCode(ErrorConstant.PARAMETER_TYPE_ERROR.getCode());
                resultDO.setErrMsg(ErrorConstant.PARAMETER_TYPE_ERROR.getMsg());
            }
        }catch (Exception e) {
            Logger.error(e, "查询约看/签约详情v1.2失败",e);
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    private ResultDO checkClerkHintList4Map(ClerkHintList4MapQuery clerkHintList4MapQuery) {
        ResultDO checkDO = new ResultDO();
        if(clerkHintList4MapQuery==null){
            checkDO.setSuccess(false);
            checkDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            checkDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return checkDO;
        }

        if(ValidateHelper.isEmpty(clerkHintList4MapQuery.getBlockId())
                && (ValidateHelper.isEmpty(clerkHintList4MapQuery.getMaxLat())
                || ValidateHelper.isEmpty(clerkHintList4MapQuery.getMinLat())
                || ValidateHelper.isEmpty(clerkHintList4MapQuery.getMaxLon())
                || ValidateHelper.isEmpty(clerkHintList4MapQuery.getMinLon()))){
            checkDO.setSuccess(false);
            checkDO.setErrCode(ErrorConstant.NULL_BLOCKID_OR_LON_LAT.getCode());
            checkDO.setErrMsg(ErrorConstant.NULL_BLOCKID_OR_LON_LAT.getMsg());
            return checkDO;
        }
        checkDO.setSuccess(true);
        return checkDO;
    }


//    private ResultDO checkQueryClertHintList(ClerkHintListQuery clerkHintListQuery) {
//        ResultDO checkDO = new ResultDO();
//        if(clerkHintListQuery==null){
//            checkDO.setSuccess(false);
//            checkDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
//            checkDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
//            return checkDO;
//        }
//
//        if(ValidateHelper.isEmpty(clerkHintListQuery.getLatitude())
//                && ValidateHelper.isEmpty(clerkHintListQuery.getLongitude())){
//            checkDO.setSuccess(false);
//            checkDO.setErrCode(ErrorConstant.NULL_LONGITUDE_LATITUDE.getCode());
//            checkDO.setErrMsg(ErrorConstant.NULL_LONGITUDE_LATITUDE.getMsg());
//            return checkDO;
//        }
//        checkDO.setSuccess(true);
//        return checkDO;
//    }



    /**
     * 请求参数校验
     * @param clerkHintDTO
     * @return
     */
    public String checkpublishHintByClerk(ClerkHintDTO clerkHintDTO){
        if(clerkHintDTO==null){
            return "请求参数不能为空！";
        }
        String address=clerkHintDTO.getShopAddress();
        if(ValidateHelper.isEmptyString(address)){
            return "商铺地址不能为空！";
        }

        if(ValidateHelper.isEmpty(clerkHintDTO.getHintFrom())){
            return "线索来源不能为空！";
        }

        if(ValidateHelper.isEmpty(clerkHintDTO.getTotalFloor())){
            return "总楼层不能为空！";
        }

        if(ValidateHelper.isEmpty(clerkHintDTO.getFloor())){
            return "所在楼层不能为空！";
        }

        if(ValidateHelper.isEmpty(clerkHintDTO.getHasPoster())){
            return "是否张贴海报不能为空！";
        }

        if(clerkHintDTO.getHasPoster().intValue()== ClerkHintHasPosterEnum.YES.getValue()
                && ValidateHelper.isEmpty(clerkHintDTO.getShopCode())){
            return "二维码不能为空！";
        }

        return null;
    }

    /**
     * 请求参数校验
     * @param clerkHintDTO
     * @return
     */
    public String checkIssueClue(ClerkHintDTO clerkHintDTO){
        if(clerkHintDTO==null){
            return "请求参数不能为空！";
        }
        String address=clerkHintDTO.getShopAddress();
        if(ValidateHelper.isEmptyString(address)){
            return "商铺地址不能为空！";
        }
        Long districtId=clerkHintDTO.getDistrictId();
        if(districtId==null){
            return "区域ID不能为空！";
        }
        Long blockId=clerkHintDTO.getBlockId();
        if(blockId==null){
            return "板块ID不能为空！";
        }
        if(ValidateHelper.isEmptyString(clerkHintDTO.getDistrictName())){
            return "区域名称不能为空！";
        }
        if(ValidateHelper.isEmptyString(clerkHintDTO.getBlockName())){
            return "板块名称不能为空！";
        }
//        String name=clerkHintDTO.getLinkmanName();
//        if(ValidateHelper.isEmptyString(name)){
//            return "联系人姓名不能为空！";
//        }
//        String linkPhone=clerkHintDTO.getLinkmanPhone();
//        if(ValidateHelper.isEmptyString(linkPhone)){
//            return "联系电话不能为空！";
//        }
        /*if(ValidateHelper.isEmptyString(clerkHintDTO.getSubscribeTime())){
            return "约见时间不能为空！";
        }*/
        //预约时间不能再当前时间之前
        /*int left= DateUtils.string2Date(clerkHintDTO.getSubscribeTime()).compareTo(DateUtils.getCurrentDate());
        if (left<0){
            return "约见时间不能小于当前时间";
        }*/
        return null;
    }

    @Override
    public ResultDO<List<ClerkHintBO>> selectSchedule() {
        ResultDO<List<ClerkHintBO>> listResultDO=new ResultDO<>();
        List<ClerkHintBO> list=clerkHintManager.selectSchedule();
        listResultDO.setSuccess(true);
        listResultDO.setData(list);
        return listResultDO;
    }

    /**
     * 修改线索状态
     * update xiehb
     * @param clerkHintDTO
     * @return
     */
    @Override
    public ResultDO<Boolean> updateStatus(ClerkHintDTO clerkHintDTO) {
        ResultDO<Boolean> resultDO=new ResultDO<>();
        Long hintId=clerkHintDTO.getId();
        Integer status=clerkHintDTO.getStatus();
        if(hintId==null){
            resultDO.setErrMsg(ErrorConstant.NULL_CLERT_HINT_ID.getMsg());
            resultDO.setErrCode(ErrorConstant.NULL_CLERT_HINT_ID.getCode());
            return resultDO;
        }
        if(status==null){
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            return resultDO;
        }
        ClerkHint clerkHint = clerkHintManager.getClerkHintById(hintId);
        clerkHint.setStatus(status);
        if(status.intValue()==ClerkHintStatusEnum.EXAMINE_SPOT_FINISHED.getValue()){
            clerkHint.setCheckTime(DateUtils.getCurrentDate());
        }
        Boolean bool = clerkHintManager.updateStatus(clerkHint);
        if(bool){
            resultDO.setData(true);
            resultDO.setSuccess(true);
            return resultDO;
        }
        return resultDO;
    }
}
