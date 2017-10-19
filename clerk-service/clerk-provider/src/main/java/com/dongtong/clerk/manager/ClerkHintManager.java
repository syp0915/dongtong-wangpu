package com.dongtong.clerk.manager;

import com.alibaba.fastjson.JSONObject;
import com.dongtong.basic.dto.req.WorkNoticeReqDTO;
import com.dongtong.basic.enums.WorkServiceType;
import com.dongtong.basic.service.BaseShopNumberService;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.clerk.bo.ClerkHintBO;
import com.dongtong.clerk.constant.*;
import com.dongtong.clerk.dao.*;
import com.dongtong.clerk.domain.*;
import com.dongtong.clerk.dto.*;
import com.dongtong.clerk.enums.ClerkRoleType;
import com.dongtong.clerk.enums.HintFrom;
import com.dongtong.clerk.query.ClerkHintList4MapQuery;
import com.dongtong.clerk.query.ClerkHintQuery;
import com.dongtong.clerk.service.HintOverTimeService;
import com.dongtong.clerk.util.HttpUtil;
import com.dongtong.customer.enums.ServiceType;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.date.DateUtils;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLDataException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author sunyaping
 * @Package com.dongtong.clerk.manage
 * @Description :TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-08 13:21
 * version V1.0.0
 **/
@Service
public class ClerkHintManager {

    @Autowired
    private ClerkHintMapper clerkHintMapper;

    @Autowired
    private ClerkMapper clerkMapper;

    @Autowired
    private ClerkHintFlowLogMapper clerkHintFlowLogMapper;

    @Autowired
    private ClerkHintOperatorLogMapper clerkHintOperatorLogMapper;

    @Autowired
    private ClerkProviderEnvProperties clerkProviderEnvProperties;

    @Autowired
    private ClerkHintImgMapper clerkHintImgMapper;

    @Autowired
    private BaseShopNumberService baseShopNumberService;

    @Autowired
    private HintOverTimeService hintOverTimeService;

    @Autowired
    private NoticePushService noticePushService;

    /**
     * 发布金铺寻租线索
     * @param clerkHintDTO
     * @return
     */
    @Transactional
    public Long issueClue(ClerkHintDTO clerkHintDTO) throws Exception{
        ClerkHint clerkHint=new ClerkHint();
        BeanUtils.copyProperties(clerkHintDTO,clerkHint);
        clerkHint.setSubscribeTime(DateUtils.string2Date(clerkHintDTO.getSubscribeTime(),"yyyy-MM-dd HH:mm:ss"));
        clerkHint.setLatitude(ValidateHelper.isEmpty(clerkHintDTO.getLatitude())?null:clerkHintDTO.getLatitude());
        clerkHint.setLongitude((ValidateHelper.isEmpty(clerkHintDTO.getLongitude())?null:clerkHintDTO.getLongitude()));
        clerkHint.setHintFrom(HintFrom.CUSTOMER.getValue());
        clerkHint.setCreater(clerkHintDTO.getIssuerId());
        clerkHint.setIsShow(0);
        clerkHint.setAddrIsShow(0);
//        clerkHint.setCreateTime(new Date());
//        clerkHintMapper.insertSelective(clerkHint);
        clerkHintMapper.insert(clerkHint);
        Long clerkHintId=clerkHint.getId();
        return clerkHintId;
    }

    /**
     * 业务员发布金铺寻租线索
     * @param clerkHintDTO
     * @return
     */
    @Transactional
    public ResultDO publishHintByClerk(ClerkHintDTO clerkHintDTO) throws Exception{
        ResultDO resultDO = new ResultDO();

        boolean isShopCodeUsed = false;
        //如果有不张贴海报，则需要生成二维码信息
        if(clerkHintDTO.getHasPoster().intValue()== ClerkHintHasPosterEnum.NO.getValue()){
            ResultDO<String> shopCodeDO = baseShopNumberService.createShopNumber("021");
            if(shopCodeDO.isSuccess()){
                clerkHintDTO.setShopCode(shopCodeDO.getData());
                isShopCodeUsed = true;
            }else{
                resultDO.setSuccess(false);
                resultDO.setErrCode(shopCodeDO.getErrCode());
                resultDO.setErrMsg(shopCodeDO.getErrMsg());
                return resultDO;
            }
        }
        ClerkHint clerkHint=new ClerkHint();
        BeanUtils.copyProperties(clerkHintDTO,clerkHint);
        Clerk clerk = clerkMapper.selectByPrimaryKey(clerkHintDTO.getIssuerId());
        if(ValidateHelper.isEmpty(clerk)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CLERK_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.CLERK_NOT_EXIST.getMsg());
            return resultDO;
        }

        //操作权限校验
        if(clerk.getRoleType().intValue()!=ClerkRoleType.EXPAND_CLERK.getValue()){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NOT_AUTH_OPERATE.getCode());
            resultDO.setErrMsg(ErrorConstant.NOT_AUTH_OPERATE.getMsg());
            return resultDO;
        }

        String url = clerkProviderEnvProperties.getGlobalappUrl()+"/point/calculate/v1.0.0";
        url += "?lat="+clerkHintDTO.getLatitude() +"&lng="+clerkHintDTO.getLongitude();
        try {
            String resp = HttpUtil.get(url);

            if(!ValidateHelper.isEmpty(resp)){
                Logger.info(this.getClass(),"获取到区域板块信息："+resp);
                JSONObject respObj = com.alibaba.fastjson.JSON.parseObject(resp);
                if(respObj.getBoolean("success") && !ValidateHelper.isEmpty(respObj.get("data"))){
                    JSONObject obj = (JSONObject)respObj.get("data");

                    clerkHint.setDistrictId(obj.getLong("districtId"));
                    clerkHint.setDistrictName(obj.getString("districtName"));
                    clerkHint.setBlockId(obj.getLong("blockId"));
                    clerkHint.setBlockName(obj.getString("blockName"));
                }
            }else{
                Logger.warn(this.getClass(),"获取区域板块信息失败,lat="
                        +clerkHintDTO.getLatitude()+",lon="+clerkHintDTO.getLongitude());
            }

        } catch (IOException e) {
            Logger.error(this.getClass(),"获取区域板块信息失败",e);
            e.printStackTrace();
        }

        //更新二维码使用状态
        if(isShopCodeUsed){
            ResultDO<String> shopNumberDO = baseShopNumberService.updateShopNumber(clerkHintDTO.getShopCode());
            if(!shopNumberDO.isSuccess()){
                resultDO.setSuccess(false);
                resultDO.setErrCode(ErrorConstant.SHOP_CODE_UPDATE_FAIL.getCode());
                resultDO.setErrMsg(ErrorConstant.SHOP_CODE_UPDATE_FAIL.getMsg());
                return resultDO;
            }
        }

        clerkHint.setExpandClerkId(clerkHintDTO.getIssuerId());
        clerkHint.setExpandClerkName(clerk.getRealName());
        clerkHint.setClaimTime(new Date());
        clerkHint.setConfirmTime(new Date());
        clerkHint.setLatitude(ValidateHelper.isEmpty(clerkHintDTO.getLatitude())?null:clerkHintDTO.getLatitude());
        clerkHint.setLongitude((ValidateHelper.isEmpty(clerkHintDTO.getLongitude())?null:clerkHintDTO.getLongitude()));
        clerkHint.setCreateTime(new Date());
        clerkHint.setCreater(clerkHintDTO.getIssuerId());
        clerkHint.setVersion(0L);
        int result = clerkHintMapper.insertSelective(clerkHint);
        if(result==1){
            if(!ValidateHelper.isEmpty(clerkHintDTO.getShopImgList()) && clerkHintDTO.getShopImgList().size()>0){
                for(ShopImgDTO shopImgDTO:clerkHintDTO.getShopImgList()){
                    ClerkHintImg clerkHintImg = new ClerkHintImg();
                    clerkHintImg.setImgIndex(shopImgDTO.getImgIndex());
                    clerkHintImg.setIsCover(shopImgDTO.getIsCover());
                    clerkHintImg.setImgUrl(shopImgDTO.getImgUrl());
                    clerkHintImg.setCreater(clerkHintDTO.getIssuerId());
                    clerkHintImg.setHintId(clerkHint.getId());
                    clerkHintImg.setCreateTime(new Date());

                    clerkHintImgMapper.insertSelective(clerkHintImg);
                }
            }

            Long clerkHintId=clerkHint.getId();
            resultDO.setSuccess(true);
            resultDO.setData(clerkHintId);
        }else{
            Logger.error(this.getClass(),"发布线索失败，param="+ com.alibaba.fastjson.JSON.toJSON(clerkHintDTO).toString());
            throw new SQLDataException("发布线索失败");
        }
        return resultDO;
    }


    public Page<ClerkHintBO> getShopClueList(ClerkHintDTO clerkHintDTO, Page page) {
        clerkHintMapper.selectByPage(clerkHintDTO,page);
        return page;

    }

    public ClerkHintDTO getShopClueInfo(ClerkHintDTO clerkHintDTO) {
        return clerkHintMapper.selectById(clerkHintDTO);
    }

    /**
     * 拓铺员认领
     * @param clerkHintDTO
     * @return
     */
    public ResultDO shopClaim(ClerkHintDTO clerkHintDTO) {
        ResultDO resultDO=new ResultDO();

        Clerk clerk = clerkMapper.selectByPrimaryKey(clerkHintDTO.getOwnerId());
        if(ValidateHelper.isEmpty(clerk)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CLERK_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.CLERK_NOT_EXIST.getMsg());
            return resultDO;
        }

        ClerkHint clerkHint = clerkHintMapper.selectByPrimaryKey(clerkHintDTO.getId());

        if(ValidateHelper.isEmpty(clerkHint)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CLERT_HINT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.CLERT_HINT_NOT_EXIST.getMsg());
            return resultDO;
        }

        //校验业务员是否曾经认领过
        ClerkHintFlowLog  flowLogParam = new ClerkHintFlowLog();
        flowLogParam.setClerkId(clerkHintDTO.getOwnerId());
        flowLogParam.setHintId(clerkHint.getId());
        flowLogParam.setType(ClerkHintStatusEnum.WAITING_CONFIRM.getValue());
        List<ClerkHintFlowLog> flowLogList = clerkHintFlowLogMapper.selectClaimLog(flowLogParam);
        if(flowLogList!=null && flowLogList.size()>0){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.HAS_CLAIMED_HINT.getCode());
            resultDO.setErrMsg(ErrorConstant.HAS_CLAIMED_HINT.getMsg());
            return resultDO;
        }

        //拓铺员角色校验(拓铺员)
        if((clerk.getRoleType().intValue() != ClerkRoleType.EXPAND_CLERK.getValue())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NOT_AUTH_OPERATE.getCode());
            resultDO.setErrMsg(ErrorConstant.NOT_AUTH_OPERATE.getMsg());
            return resultDO;
        }
        //线索状态校验（交易员待认领）
        if(clerkHint.getStatus().intValue() != ClerkHintStatusEnum.WAITTING_CLAIM.getValue()){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.HINT_STATUS_NOT_ACCORD.getCode());
            resultDO.setErrMsg(ErrorConstant.HINT_STATUS_NOT_ACCORD.getMsg());
            return resultDO;
        }

        clerkHint.setStatus(ClerkHintStatusEnum.WAITING_CONFIRM.getValue());
        clerkHint.setClaimTime(new Date());
        clerkHint.setOwnerId(clerkHintDTO.getOwnerId());
        clerkHint.setExpandClerkId(clerkHintDTO.getOwnerId());
        clerkHint.setExpandClerkName(clerk.getRealName());
        if(1==clerkHintMapper.updateByPrimaryKey(clerkHint)){
            String date = DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");
            ResultDO hintOverDO = hintOverTimeService.addHintOverTime(clerkHint.getId(),date);
            if(!hintOverDO.isSuccess()){
                Logger.warn(this.getClass(),"添加线索超时记录失败，hintId="+clerkHint.getId());
            }

            //添加线索状态流转记录
            ClerkHintFlowLog clerkHintFlowLog = new ClerkHintFlowLog();
            clerkHintFlowLog.setHintId(clerkHintDTO.getId());
            clerkHintFlowLog.setClerkId(clerkHintDTO.getOwnerId());
            clerkHintFlowLog.setCreateTime(new Date());
            clerkHintFlowLog.setType(ClerkHintStatusEnum.WAITING_CONFIRM.getValue());
            String remark = "拓铺员"+clerk.getRealName()+"认领线索";
            clerkHintFlowLog.setRemark(remark);
            clerkHintFlowLogMapper.insertSelective(clerkHintFlowLog);

            //添加线索操作日志
            ClerkHintOperatorLog clerkHintOperatorLog = new ClerkHintOperatorLog();
            clerkHintOperatorLog.setClerkId(clerkHintDTO.getOwnerId());
            clerkHintOperatorLog.setHintId(clerkHintDTO.getId());
            clerkHintOperatorLog.setType(ClerkHintOperatorTypeEnum.EXPAND_CLERK_CLAIM.getValue());
            clerkHintOperatorLog.setCreateTime(new Date());
            clerkHintOperatorLog.setCreater(clerkHintDTO.getOwnerId());
            clerkHintOperatorLog.setRemark(remark);
            clerkHintOperatorLogMapper.insertSelective(clerkHintOperatorLog);

            resultDO.setSuccess(true);
        }else{
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.UPDATE_DATA_FAIL.getCode());
            resultDO.setErrMsg(ErrorConstant.UPDATE_DATA_FAIL.getMsg());
        }
        return resultDO;
    }

    /**
     * 线索参数校验
     * @param clerkHint
     * @return
     */
    private String checkHintData(ClerkHint clerkHint) {
        StringBuffer checkMsg = new StringBuffer();
        if(ValidateHelper.isEmpty(clerkHint.getShopAddress())){
            checkMsg.append("线索地址、");
        }

        if(ValidateHelper.isEmpty(clerkHint.getLinkmanName())){
            checkMsg.append("联系人、");
        }

        if(ValidateHelper.isEmpty(clerkHint.getLinkmanPhone())){
            checkMsg.append("联系电话、");
        }

        if(ValidateHelper.isEmpty(clerkHint.getLongitude()) && ValidateHelper.isEmpty(clerkHint.getLatitude())){
            checkMsg.append("经纬度、");
        }

        if(ValidateHelper.isEmpty(clerkHint.getHintFrom())){
            checkMsg.append("线索来源、");
        }

        if(ValidateHelper.isEmpty(clerkHint.getTotalFloor()) && ValidateHelper.isEmpty(clerkHint.getFloor())){
            checkMsg.append("楼层信息、");
        }

        if(ValidateHelper.isEmpty(clerkHint.getHasPoster())){
            checkMsg.append("是否张贴海报、");
        }

        if(ValidateHelper.isEmpty(clerkHint.getShopCode())){
            checkMsg.append("二维码、");
        }

        if(ValidateHelper.isEmpty(clerkHint.getIsShow())){
            checkMsg.append("前台是否展示电话、");
        }

        if(ValidateHelper.isEmpty(clerkHint.getAddrIsShow())){
            checkMsg.append("前台是否展示地址、");
        }

        List<ClerkHintImg> hintImgList = clerkHintImgMapper.selectByHintId(clerkHint.getId());
        if(hintImgList==null || hintImgList.size()==0){
            checkMsg.append("线索图片、");
        }
        String chkMsg = checkMsg.toString();
        if(!ValidateHelper.isEmpty(chkMsg)){
            chkMsg = chkMsg.substring(0,chkMsg.length()-1);
            chkMsg += "信息不能为空";
        }

        return chkMsg;
    }

    public ResultDO updateSubscribeTime(ClerkHintDTO clerkHintDTO) throws ParseException {
        ResultDO res=new ResultDO();
        ClerkHint clerkHint=new ClerkHint();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        clerkHint.setId(clerkHintDTO.getId());
        clerkHint.setSubscribeTime(sdf.parse(clerkHintDTO.getSubscribeTime()));
        if(1==clerkHintMapper.updateByPrimaryKey(clerkHint)){
            res.setSuccess(true);
        }
        return res;
    }

    /**
     * 线索废弃
     * @param clerkHintDTO
     * @return
     */
    public ResultDO updateClueStatus(ClerkHintDTO clerkHintDTO) {
        ResultDO resultDO=new ResultDO();

        Clerk clerk = clerkMapper.selectByPrimaryKey(clerkHintDTO.getModifier());
        if(ValidateHelper.isEmpty(clerk)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CLERK_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.CLERK_NOT_EXIST.getMsg());
            return resultDO;
        }

        ClerkHint clerkHint = clerkHintMapper.selectByPrimaryKey(clerkHintDTO.getId());

        if(ValidateHelper.isEmpty(clerkHint)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CLERT_HINT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.CLERT_HINT_NOT_EXIST.getMsg());
            return resultDO;
        }

        //线索状态校验（交易员待实勘）
        if(!(clerkHint.getStatus().intValue() == ClerkHintStatusEnum.WAITING_CONFIRM.getValue()
            ||clerkHint.getStatus().intValue() == ClerkHintStatusEnum.WAITTING_EXAMINE_SPOT.getValue())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.HINT_STATUS_NOT_ACCORD.getCode());
            resultDO.setErrMsg(ErrorConstant.HINT_STATUS_NOT_ACCORD.getMsg());
            return resultDO;
        }

        //交易员或拓铺员角色校验（交易员 ,且线索为该交易员认领 或者拓铺员，且线索拥有者为拓铺员）
        if(ValidateHelper.isEmpty(clerk.getRoleType())||ValidateHelper.isEmpty(clerkHint.getOwnerId())
                ||!((clerk.getRoleType().intValue()== ClerkRoleType.DEAL_CLERK.getValue()
                ||clerk.getRoleType().intValue() == ClerkRoleType.EXPAND_CLERK.getValue())
                && clerk.getId().longValue()==clerkHint.getOwnerId().longValue())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NOT_AUTH_OPERATE.getCode());
            resultDO.setErrMsg(ErrorConstant.NOT_AUTH_OPERATE.getMsg());
            return resultDO;
        }

        clerkHint.setDiscardCause(clerkHintDTO.getDiscardCause());
        clerkHint.setTagId(clerkHintDTO.getTagId());
        clerkHint.setStatus(ClerkHintStatusEnum.CANCELED.getValue());
        clerkHint.setDiscardTime(new Date());
        if(1==clerkHintMapper.updateByPrimaryKeySelective(clerkHint)){
            //添加线索状态流转记录
            ClerkHintFlowLog clerkHintFlowLog = new ClerkHintFlowLog();
            clerkHintFlowLog.setHintId(clerkHint.getId());
            clerkHintFlowLog.setClerkId(clerkHint.getOwnerId());
            clerkHintFlowLog.setCreateTime(new Date());
            clerkHintFlowLog.setType(ClerkHintStatusEnum.CANCELED.getValue());

            //添加线索操作日志
            ClerkHintOperatorLog clerkHintOperatorLog = new ClerkHintOperatorLog();
            clerkHintOperatorLog.setClerkId(clerkHint.getOwnerId());
            clerkHintOperatorLog.setHintId(clerkHint.getId());
            String remark = "";
            if(clerk.getRoleType().intValue()== ClerkRoleType.DEAL_CLERK.getValue()){
                remark = "交易员";
                clerkHintOperatorLog.setType(ClerkHintOperatorTypeEnum.EXPAND_CLERK_CONFIRM_INVALID.getValue());
            }else{
                remark = "拓铺员";
                clerkHintOperatorLog.setType(ClerkHintOperatorTypeEnum.TRADE_CLERK_CONFIRM_INVALID.getValue());
            }

            remark+=clerk.getRealName()+"将该线索("+clerkHintDTO.getDiscardCause()+")";
            clerkHintFlowLog.setRemark(remark);
            clerkHintFlowLogMapper.insertSelective(clerkHintFlowLog);

            clerkHintOperatorLog.setCreateTime(new Date());
            clerkHintOperatorLog.setCreater(clerkHintDTO.getModifier());
            clerkHintOperatorLog.setRemark(remark);
            clerkHintOperatorLogMapper.insertSelective(clerkHintOperatorLog);

            //如果是交易员废弃，则需要通知业务员
            if(clerk.getRoleType().intValue()==ClerkRoleType.DEAL_CLERK.getValue()){
                pushHintCancelMsg(clerkHint);
            }
            resultDO.setSuccess(true);
        }else{
            resultDO.setErrCode(ErrorConstant.UPDATE_DATA_FAIL.getCode());
            resultDO.setErrMsg(ErrorConstant.UPDATE_DATA_FAIL.getMsg());
        }
        return resultDO;
    }

    @Async
    private void pushHintCancelMsg(ClerkHint clerkHint) {
        Logger.debug(this.getClass(),"线索废弃推送消息开始");
        WorkNoticeReqDTO workNoticeReqDTO = new WorkNoticeReqDTO();

        workNoticeReqDTO.setReceiveType(WorkServiceType.SCRAP_CLUE.getValue());
        workNoticeReqDTO.setBussinessId(clerkHint.getId());
        workNoticeReqDTO.setReceiveId(clerkHint.getExpandClerkId());
        workNoticeReqDTO.setServiceType(WorkServiceType.SCRAP_CLUE.getValue());
        Clerk expandClerk = clerkMapper.selectByPrimaryKey(clerkHint.getExpandClerkId());
        if(ValidateHelper.isEmpty(expandClerk) || ValidateHelper.isEmpty(expandClerk.getDeviceId())){
            Logger.warn(this.getClass(),"用户deviceId为空，无法推送消息，clerkId="+(expandClerk==null?"":expandClerk.getId()));
            return;
        }
        ResultDO<Boolean> pushDO = noticePushService.pushArrivalTimeNotice(workNoticeReqDTO,expandClerk.getDeviceId(), expandClerk.getOsType());
        Logger.debug(this.getClass(),"线索废弃推送消息完成，推送结果"+pushDO.getData()+",errMsg="+pushDO.getErrMsg());
    }

    public ClerkHint getClerkHintById(Long clerkHintId) {
        return clerkHintMapper.selectByPrimaryKey(clerkHintId);
    }

    public ResultDO<ClerkHintDTO> queryClerkHintDetail(ClerkHintQuery clerkHintQuery) {
        ResultDO res=new ResultDO();
        ClerkHintDTO hint = clerkHintMapper.selectClerkHintDetail(clerkHintQuery);
        if(hint==null){
            res.setSuccess(false);
            res.setErrCode(ErrorConstant.CLERT_HINT_NOT_EXIST.getCode());
            res.setErrMsg(ErrorConstant.CLERT_HINT_NOT_EXIST.getMsg());
            return res;
        }
        res.setSuccess(true);
        res.setData(hint);
        return res;
    }

    public Page<ClerkHintDTO> queryClerkHintList(Page<ClerkHintDTO> page) {
        clerkHintMapper.selectClerkHintList(page);
        page.setQuery(null);
        return page;
    }

    public List<ClerkHint4MapDTO> queryClerkHint4MapList(ClerkHintList4MapQuery clerkHintList4MapQuery) {
        List<ClerkHint4MapDTO> clerkHintList = clerkHintMapper.selectClerkHint4MapList(clerkHintList4MapQuery);
        return clerkHintList;
    }

    public ResultDO<Integer> getDeadTimeCount(ClerkHintDTO clerkHintDTO) {
        ResultDO resultDO=new ResultDO();
        int count=  clerkHintMapper.getDeadTimeCount(clerkHintDTO);
        resultDO.setData(count);
        return resultDO;
    }

    public Page<ClerkHintBO> selectNeedByPage(ClerkHintDTO clerkHintDTO, Page page) {
        clerkHintMapper.selectNeedByPage(clerkHintDTO,page);
        return page;
    }

    public  List<ClerkHintBO> selectSchedule(){
        return clerkHintMapper.selectSchedule();
    }

    public Boolean updateStatus(ClerkHint clerkHint) {
        Integer result=clerkHintMapper.updateByPrimaryKey(clerkHint);
        if(1==result){
           return true;
        }
        return false;
    }
    public ResultDO updateClerkHintStatus(ClerkHintDTO clerkHintDTO) {
        ResultDO res=new ResultDO();
        ClerkHint clerkHint=new ClerkHint();
        clerkHint.setId(clerkHintDTO.getId());
        clerkHint.setStatus(clerkHintDTO.getStatus());
        if(clerkHintDTO.getStatus()==3){
            clerkHint.setDiscardTime(new Date());
            clerkHint.setTagId(1);
        }else if(clerkHintDTO.getStatus()==2){
            clerkHint.setIssueShopTime(new Date());
        }
        if(1==clerkHintMapper.updateByPrimaryKey(clerkHint)){
            res.setSuccess(true);
        }
        return res;
    }

    /**
     * 交易员认领线索
     * @param clerkHintComfirmDTO
     * @return
     */
    @Transactional
    public ResultDO tradeClerkClaim(ClerkHintComfirmDTO clerkHintComfirmDTO) throws SQLDataException {
        ResultDO resultDO = new ResultDO();
        Clerk clerk = clerkMapper.selectByPrimaryKey(clerkHintComfirmDTO.getClerkId());
        if(ValidateHelper.isEmpty(clerk)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CLERK_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.CLERK_NOT_EXIST.getMsg());
            return resultDO;
        }

        ClerkHint clerkHint = clerkHintMapper.selectByPrimaryKey(clerkHintComfirmDTO.getHintId());

        if(ValidateHelper.isEmpty(clerkHint)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CLERT_HINT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.CLERT_HINT_NOT_EXIST.getMsg());
            return resultDO;
        }

        //校验业务员是否曾经认领过
        ClerkHintFlowLog  flowLogParam = new ClerkHintFlowLog();
        flowLogParam.setClerkId(clerkHintComfirmDTO.getClerkId());
        flowLogParam.setHintId(clerkHint.getId());
        flowLogParam.setType(ClerkHintStatusEnum.WAITTING_EXAMINE_SPOT.getValue());
        List<ClerkHintFlowLog> flowLogList = clerkHintFlowLogMapper.selectClaimLog(flowLogParam);
        if(flowLogList!=null && flowLogList.size()>0){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.HAS_CLAIMED_HINT.getCode());
            resultDO.setErrMsg(ErrorConstant.HAS_CLAIMED_HINT.getMsg());
            return resultDO;
        }

        //线索状态校验（交易员待认领）
        if(clerkHint.getStatus().intValue() != ClerkHintStatusEnum.WAITING_TRADE_CLAIM.getValue()){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.HINT_STATUS_NOT_ACCORD.getCode());
            resultDO.setErrMsg(ErrorConstant.HINT_STATUS_NOT_ACCORD.getMsg());
            return resultDO;
        }
        //交易员角色校验(交易员)
        if((clerk.getRoleType().intValue() != ClerkRoleType.DEAL_CLERK.getValue())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NOT_AUTH_OPERATE.getCode());
            resultDO.setErrMsg(ErrorConstant.NOT_AUTH_OPERATE.getMsg());
            return resultDO;
        }

        //更新线索状态为待实勘
        clerkHint.setOwnerId(clerkHintComfirmDTO.getClerkId());
        clerkHint.setStatus(ClerkHintStatusEnum.WAITTING_EXAMINE_SPOT.getValue());
        clerkHint.setModifier(clerk.getId());
        clerkHint.setModifyTime(new Date());
        clerkHint.setTradeClerkId(clerk.getId());
        clerkHint.setTradeClerkName(clerk.getRealName());
        int result = clerkHintMapper.updateByPrimaryKeySelective(clerkHint);
        if(result!=1){
            Logger.error(this.getClass(),"交易员认领失败，"+com.alibaba.fastjson.JSON.toJSON(clerkHintComfirmDTO).toString());
            throw new SQLDataException("交易员认领失败");
        }

        //添加线索认领超时记录
        String date = DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");
        ResultDO hintOverDO = hintOverTimeService.addHintOverTime(clerkHint.getId(),date);
        if(!hintOverDO.isSuccess()){
            Logger.warn(this.getClass(),"添加线索超时记录失败，hintId="+clerkHint.getId());
        }

        //添加线索状态流转记录
        ClerkHintFlowLog clerkHintFlowLog = new ClerkHintFlowLog();
        clerkHintFlowLog.setHintId(clerkHintComfirmDTO.getHintId());
        clerkHintFlowLog.setClerkId(clerkHintComfirmDTO.getClerkId());
        clerkHintFlowLog.setCreateTime(new Date());
        clerkHintFlowLog.setType(ClerkHintStatusEnum.WAITTING_EXAMINE_SPOT.getValue());
        String remark = "交易员"+clerk.getRealName()+"认领店铺线索";
        clerkHintFlowLog.setRemark(remark);
        clerkHintFlowLogMapper.insertSelective(clerkHintFlowLog);

        //添加线索操作日志
        ClerkHintOperatorLog clerkHintOperatorLog = new ClerkHintOperatorLog();
        clerkHintOperatorLog.setClerkId(clerkHintComfirmDTO.getClerkId());
        clerkHintOperatorLog.setHintId(clerkHintComfirmDTO.getHintId());
        clerkHintOperatorLog.setType(ClerkHintOperatorTypeEnum.TRADE_CLAIM.getValue());
        clerkHintOperatorLog.setCreateTime(new Date());
        clerkHintOperatorLog.setCreater(clerkHintComfirmDTO.getClerkId());
        clerkHintOperatorLog.setRemark(remark);
        clerkHintOperatorLogMapper.insertSelective(clerkHintOperatorLog);

        resultDO.setSuccess(true);
        resultDO.setErrMsg("交易员认领线索成功");
        return resultDO;
    }

    /**
     * 拓铺员确认线索有效
     * @param clerkHintComfirmDTO
     * @return
     */
    public ResultDO confirmHint(ClerkHintComfirmDTO clerkHintComfirmDTO) {
        ResultDO resultDO = new ResultDO();
        Clerk clerk = clerkMapper.selectByPrimaryKey(clerkHintComfirmDTO.getClerkId());
        if(ValidateHelper.isEmpty(clerk)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CLERK_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.CLERK_NOT_EXIST.getMsg());
            return resultDO;
        }

        ClerkHint clerkHint = clerkHintMapper.selectByPrimaryKey(clerkHintComfirmDTO.getHintId());

        if(ValidateHelper.isEmpty(clerkHint)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CLERT_HINT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.CLERT_HINT_NOT_EXIST.getMsg());
            return resultDO;
        }

        //线索状态校验（拓铺员待确认）
        if(clerkHint.getStatus().intValue() != ClerkHintStatusEnum.WAITING_CONFIRM.getValue()){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.HINT_STATUS_NOT_ACCORD.getCode());
            resultDO.setErrMsg(ErrorConstant.HINT_STATUS_NOT_ACCORD.getMsg());
            return resultDO;
        }
        //拓铺员角色校验（拓铺员 ,且线索为该拓铺员认领）
        if(clerk.getRoleType().intValue()!= ClerkRoleType.EXPAND_CLERK.getValue()
                || clerk.getId().longValue()!=clerkHint.getOwnerId().longValue()){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NOT_AUTH_OPERATE.getCode());
            resultDO.setErrMsg(ErrorConstant.NOT_AUTH_OPERATE.getMsg());
            return resultDO;
        }

        //校验线索必填字段
        String checkMsg = checkHintData(clerkHint);
        if(!ValidateHelper.isEmpty(checkMsg)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(checkMsg);
            return resultDO;
        }

        //更新线索状态为交易员待认领
        clerkHint.setOwnerId(clerkHintComfirmDTO.getClerkId());
        clerkHint.setStatus(ClerkHintStatusEnum.WAITING_TRADE_CLAIM.getValue());
        clerkHint.setModifyTime(new Date());
        clerkHintMapper.updateByPrimaryKeySelective(clerkHint);

        //添加线索状态流转记录
        ClerkHintFlowLog clerkHintFlowLog = new ClerkHintFlowLog();
        clerkHintFlowLog.setHintId(clerkHintComfirmDTO.getHintId());
        clerkHintFlowLog.setClerkId(clerkHintComfirmDTO.getClerkId());
        clerkHintFlowLog.setCreateTime(new Date());
        clerkHintFlowLog.setType(ClerkHintStatusEnum.WAITING_TRADE_CLAIM.getValue());
        clerkHintFlowLogMapper.insertSelective(clerkHintFlowLog);

        //添加线索操作日志
        ClerkHintOperatorLog clerkHintOperatorLog = new ClerkHintOperatorLog();
        clerkHintOperatorLog.setClerkId(clerkHintComfirmDTO.getClerkId());
        clerkHintOperatorLog.setHintId(clerkHintComfirmDTO.getHintId());
        clerkHintOperatorLog.setType(ClerkHintOperatorTypeEnum.EXPAND_CLERK_CONFIRM.getValue());
        String remark = "拓铺员"+clerk.getRealName()+"确认线索有效";
        clerkHintOperatorLog.setRemark(remark);
        clerkHintOperatorLog.setCreateTime(new Date());
        clerkHintOperatorLog.setCreater(clerkHintComfirmDTO.getClerkId());
        clerkHintOperatorLogMapper.insertSelective(clerkHintOperatorLog);

        resultDO.setSuccess(true);
        resultDO.setErrMsg("拓铺员确认线索有效成功");
        return resultDO;
    }

    /**
     * 更新线索客户信息
     * @param clerkHintDTO
     * @return
     */
    public ResultDO updateCustomerInfo(ClerkHintDTO clerkHintDTO) {
        ResultDO resultDO = new ResultDO();
        Clerk clerk = clerkMapper.selectByPrimaryKey(clerkHintDTO.getModifier());
        if(ValidateHelper.isEmpty(clerk)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CLERK_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.CLERK_NOT_EXIST.getMsg());
            return resultDO;
        }

        ClerkHint clerkHint = clerkHintMapper.selectByPrimaryKey(clerkHintDTO.getId());

        if(ValidateHelper.isEmpty(clerkHint)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CLERT_HINT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.CLERT_HINT_NOT_EXIST.getMsg());
            return resultDO;
        }
        //线索状态校验（交易员待核准 或者拓铺员带确认）
        if(!(clerkHint.getStatus().intValue() == ClerkHintStatusEnum.WAITTING_EXAMINE_SPOT.getValue()
                || clerkHint.getStatus().intValue() == ClerkHintStatusEnum.WAITING_CONFIRM.getValue())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.HINT_STATUS_NOT_ACCORD.getCode());
            resultDO.setErrMsg(ErrorConstant.HINT_STATUS_NOT_ACCORD.getMsg());
            return resultDO;
        }

        //交易员或拓铺员角色校验（交易员 ,且线索为该交易员认领 或者拓铺员，且线索拥有者为拓铺员）
        if(ValidateHelper.isEmpty(clerk.getRoleType())||ValidateHelper.isEmpty(clerkHint.getOwnerId())
                ||!((clerk.getRoleType().intValue()== ClerkRoleType.DEAL_CLERK.getValue()
                ||clerk.getRoleType().intValue() == ClerkRoleType.EXPAND_CLERK.getValue())
                && clerk.getId().longValue()==clerkHint.getOwnerId().longValue())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NOT_AUTH_OPERATE.getCode());
            resultDO.setErrMsg(ErrorConstant.NOT_AUTH_OPERATE.getMsg());
            return resultDO;
        }

        //线索客户信息修改
        clerkHint.setLinkmanName(clerkHintDTO.getLinkmanName());
        clerkHint.setLinkmanPhone(clerkHintDTO.getLinkmanPhone());
        clerkHint.setModifier(clerkHintDTO.getModifier());
        clerkHint.setIsShow(clerkHintDTO.getIsShow());
        clerkHint.setModifyTime(new Date());
        clerkHintMapper.updateByPrimaryKeySelective(clerkHint);
        resultDO.setSuccess(true);

        return resultDO;
    }

    /**
     * 线索建筑信息修改
     * @param clerkHintDTO
     * @return
     */
    public ResultDO updateBuildingInfo(ClerkHintDTO clerkHintDTO) {
        ResultDO resultDO = new ResultDO();
        Logger.debug(this.getClass(),"clerkId="+clerkHintDTO.getModifier());
        Clerk clerk = clerkMapper.selectByPrimaryKey(clerkHintDTO.getModifier());
        if(ValidateHelper.isEmpty(clerk)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CLERK_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.CLERK_NOT_EXIST.getMsg());
            return resultDO;
        }

        ClerkHint clerkHint = clerkHintMapper.selectByPrimaryKey(clerkHintDTO.getId());

        if(ValidateHelper.isEmpty(clerkHint)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CLERT_HINT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.CLERT_HINT_NOT_EXIST.getMsg());
            return resultDO;
        }

        //线索状态校验（交易员待核准 或者拓铺员带确认）
        if(!(clerkHint.getStatus().intValue() == ClerkHintStatusEnum.WAITTING_EXAMINE_SPOT.getValue()
                || clerkHint.getStatus().intValue() == ClerkHintStatusEnum.WAITING_CONFIRM.getValue())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.HINT_STATUS_NOT_ACCORD.getCode());
            resultDO.setErrMsg(ErrorConstant.HINT_STATUS_NOT_ACCORD.getMsg());
            return resultDO;
        }
        //交易员或拓铺员角色校验（交易员 ,且线索为该交易员认领 或者拓铺员，且线索拥有者为拓铺员）
        if(ValidateHelper.isEmpty(clerk.getRoleType())||ValidateHelper.isEmpty(clerkHint.getOwnerId())
               ||!((clerk.getRoleType().intValue()== ClerkRoleType.DEAL_CLERK.getValue()
                ||clerk.getRoleType().intValue() == ClerkRoleType.EXPAND_CLERK.getValue())
                && clerk.getId().longValue()==clerkHint.getOwnerId().longValue())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NOT_AUTH_OPERATE.getCode());
            resultDO.setErrMsg(ErrorConstant.NOT_AUTH_OPERATE.getMsg());
            return resultDO;
        }

        //线索建筑信息修改
        clerkHint.setTotalFloor(clerkHintDTO.getTotalFloor());
        clerkHint.setFloor(clerkHintDTO.getFloor());
        clerkHint.setModifier(clerkHintDTO.getModifier());
        clerkHint.setModifyTime(new Date());
        clerkHintMapper.updateByPrimaryKeySelective(clerkHint);
        resultDO.setSuccess(true);

        return resultDO;
    }

    /**
     * 线索位置信息修改
     * @param clerkHintDTO
     * @return
     */
    public ResultDO updatePositionInfo(ClerkHintDTO clerkHintDTO) {
        ResultDO resultDO = new ResultDO();
        Logger.debug(this.getClass(),"clerkId="+clerkHintDTO.getModifier());
        Clerk clerk = clerkMapper.selectByPrimaryKey(clerkHintDTO.getModifier());
        if(ValidateHelper.isEmpty(clerk)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CLERK_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.CLERK_NOT_EXIST.getMsg());
            return resultDO;
        }

        ClerkHint clerkHint = clerkHintMapper.selectByPrimaryKey(clerkHintDTO.getId());

        if(ValidateHelper.isEmpty(clerkHint)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CLERT_HINT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.CLERT_HINT_NOT_EXIST.getMsg());
            return resultDO;
        }

        //线索状态校验（交易员待核准 或者拓铺员带确认）
        if(!(clerkHint.getStatus().intValue() == ClerkHintStatusEnum.WAITTING_EXAMINE_SPOT.getValue()
                || clerkHint.getStatus().intValue() == ClerkHintStatusEnum.WAITING_CONFIRM.getValue())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.HINT_STATUS_NOT_ACCORD.getCode());
            resultDO.setErrMsg(ErrorConstant.HINT_STATUS_NOT_ACCORD.getMsg());
            return resultDO;
        }
        //交易员或拓铺员角色校验（交易员 ,且线索为该交易员认领 或者拓铺员，且线索拥有者为拓铺员）
        if(ValidateHelper.isEmpty(clerk.getRoleType())||ValidateHelper.isEmpty(clerkHint.getOwnerId())
                ||!((clerk.getRoleType().intValue()== ClerkRoleType.DEAL_CLERK.getValue()
                ||clerk.getRoleType().intValue() == ClerkRoleType.EXPAND_CLERK.getValue())
                && clerk.getId().longValue()==clerkHint.getOwnerId().longValue())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NOT_AUTH_OPERATE.getCode());
            resultDO.setErrMsg(ErrorConstant.NOT_AUTH_OPERATE.getMsg());
            return resultDO;
        }

        //根据坐标获取区域板块信息
        if(!ValidateHelper.isEmpty(clerkHintDTO.getLongitude())
                &&!ValidateHelper.isEmpty(clerkHintDTO.getLatitude()) ){

            String url = clerkProviderEnvProperties.getGlobalappUrl()+"/point/calculate/v1.0.0";
            url += "?lat="+clerkHintDTO.getLatitude() +"&lng="+clerkHintDTO.getLongitude();
            try {
                String resp = HttpUtil.get(url);

                if(!ValidateHelper.isEmpty(resp)){
                    Logger.info(this.getClass(),"获取到区域板块信息："+resp);
                    JSONObject respObj = com.alibaba.fastjson.JSON.parseObject(resp);
                    if(respObj.getBoolean("success") && !ValidateHelper.isEmpty(respObj.get("data"))){
                        JSONObject obj = (JSONObject)respObj.get("data");

                        clerkHint.setDistrictId(obj.getLong("districtId"));
                        clerkHint.setDistrictName(obj.getString("districtName"));
                        clerkHint.setBlockId(obj.getLong("blockId"));
                        clerkHint.setBlockName(obj.getString("blockName"));
                    }
                }else{
                    Logger.warn(this.getClass(),"获取区域板块信息失败,lat="
                            +clerkHintDTO.getLatitude()+",lon="+clerkHintDTO.getLongitude());
                }

            } catch (IOException e) {
                Logger.error(this.getClass(),"获取区域板块信息失败",e);
                e.printStackTrace();
            }
        }

        //是否使用过新的二维码
        boolean isShopCodeUsed = false;
        //如果有不张贴海报，则需要生成二维码信息
        if(clerkHintDTO.getHasPoster().intValue()== ClerkHintHasPosterEnum.NO.getValue()
                && ValidateHelper.isEmpty(clerkHint.getShopCode())){
            ResultDO<String> shopCodeDO = baseShopNumberService.createShopNumber("021");
            if(shopCodeDO.isSuccess()){
                clerkHintDTO.setShopCode(shopCodeDO.getData());
                clerkHint.setShopCode(clerkHintDTO.getShopCode());
                isShopCodeUsed = true;
            }else{
                resultDO.setSuccess(false);
                resultDO.setErrCode(shopCodeDO.getErrCode());
                resultDO.setErrMsg(shopCodeDO.getErrMsg());
                return resultDO;
            }
        }else if(clerkHintDTO.getHasPoster().intValue()== ClerkHintHasPosterEnum.YES.getValue()
                && !ValidateHelper.isEmpty(clerkHintDTO.getShopCode())){
            clerkHint.setShopCode(clerkHintDTO.getShopCode());
        }

        //更新二维码使用状态
        if(isShopCodeUsed){
            ResultDO<String> shopNumberDO = baseShopNumberService.updateShopNumber(clerkHintDTO.getShopCode());
            if(!shopNumberDO.isSuccess()){
                resultDO.setSuccess(false);
                resultDO.setErrCode(ErrorConstant.SHOP_CODE_UPDATE_FAIL.getCode());
                resultDO.setErrMsg(ErrorConstant.SHOP_CODE_UPDATE_FAIL.getMsg());
                return resultDO;
            }
        }

        //线索建筑信息修改
        clerkHint.setAddrIsShow(clerkHintDTO.getAddrIsShow());
        clerkHint.setLongitude(clerkHintDTO.getLongitude());
        clerkHint.setLatitude(clerkHintDTO.getLatitude());
        clerkHint.setShopAddress(clerkHintDTO.getShopAddress());
        clerkHint.setHasPoster(clerkHintDTO.getHasPoster());
        clerkHint.setModifier(clerkHintDTO.getModifier());
        clerkHint.setModifyTime(new Date());
        int result = clerkHintMapper.updateByPrimaryKeySelective(clerkHint);
        if(result==1){
            resultDO.setSuccess(true);
            resultDO.setErrMsg("更新线索位置信息成功");
            ClerkHintAddrDTO clerkHintAddrDTO = new ClerkHintAddrDTO();
            clerkHintAddrDTO.setBlockId(clerkHint.getBlockId());
            clerkHintAddrDTO.setBlockName(clerkHint.getBlockName());
            clerkHintAddrDTO.setDistrictId(clerkHint.getDistrictId());
            clerkHintAddrDTO.setDistrictName(clerkHint.getDistrictName());
            clerkHintAddrDTO.setShopCode(clerkHint.getShopCode());
            resultDO.setData(clerkHintAddrDTO);
        }else{
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.UPDATE_DATA_FAIL.getCode());
            resultDO.setErrMsg(ErrorConstant.UPDATE_DATA_FAIL.getMsg());
        }

        return resultDO;
    }

    /**
     * 线索数量统计
     * @param userId
     * @return
     */
    public ResultDO<ClerkHintStatisticsDTO> hintStatistics(Long userId) {
        ResultDO resultDO = new ResultDO();

        HashMap paramMap = new HashMap();
        paramMap.put("userId",userId);
        ClerkHintStatisticsDTO clerkHintStatisticsDTO = clerkHintMapper.statisticsHintNum(paramMap);
        resultDO.setSuccess(true);
        resultDO.setData(clerkHintStatisticsDTO);

        return resultDO;
    }
    /*
     * 查询待确认、带核准线索列表
     * @param page
     * @Author zhoumin
     * @return
     */
    public Page<ClerkHintList> queryHintList(Page<ClerkHintList> page){
        clerkHintMapper.queryHintListByPage(page);
        page.setQuery(null);
        return page;
    }

    /**
     * 待确认、待实勘线索详情
     * @param id
     * @Author zhoumin
     * @return
     */
    public ClerkHintDetailDTO getHintDetailInfo(Long id){
        return clerkHintMapper.getHintDetailInfo(id);
    }

    /**
     * 业务员约看列表v1.2
     * @param page
     * @Author zhoumin
     * @return
     */
    public Page<ClerkScheduleListDTO> queryViewList(Page<ClerkScheduleListDTO> page){
        clerkHintMapper.queryViewListByPage(page);
        page.setQuery(null);
        return page;
    }

    /**
     * 业务员签约列表v1.2
     * @param page
     * @Author zhoumin
     * @return
     */
    public Page<ClerkScheduleListDTO> querySignList(Page<ClerkScheduleListDTO> page){
        clerkHintMapper.querySignListByPage(page);
        page.setQuery(null);
        return page;
    }

    /**
     * @description 约看详情查询v1.2
     * @package com.dongtong.clerk.manager
     * @author liaozm
     * @date 2017/8/8 11:14
     * @params
     * @return
     */
    public ClerkSignOrViewDetailDTO queryViewDetail(Long id){
        return clerkHintMapper.queryViewDetail(id);
    }

    /**
     * @description 签约详情查询v1.2
     * @package com.dongtong.clerk.manager
     * @author liaozm
     * @date 2017/8/8 11:14
     * @params
     * @return
     */
    public ClerkSignOrViewDetailDTO querySignDetail(Long id){
        return clerkHintMapper.querySignDetail(id);
    }

    /**
     * 添加线索图片
     * @param clerkHintImgsDTO
     * @return
     */
    @Transactional
    public ResultDO updateShopImgInfo(ClerkHintImgsDTO clerkHintImgsDTO) {
        ResultDO resultDO = new ResultDO();
        Clerk clerk = clerkMapper.selectByPrimaryKey(clerkHintImgsDTO.getModifier());
        if(ValidateHelper.isEmpty(clerk)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CLERK_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.CLERK_NOT_EXIST.getMsg());
            return resultDO;
        }

        ClerkHint clerkHint = clerkHintMapper.selectByPrimaryKey(clerkHintImgsDTO.getId());

        if(ValidateHelper.isEmpty(clerkHint)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.CLERT_HINT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.CLERT_HINT_NOT_EXIST.getMsg());
            return resultDO;
        }

        //线索状态校验（交易员待核准 或者拓铺员带确认）
        if(!(clerkHint.getStatus().intValue() == ClerkHintStatusEnum.WAITTING_EXAMINE_SPOT.getValue()
                || clerkHint.getStatus().intValue() == ClerkHintStatusEnum.WAITING_CONFIRM.getValue())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.HINT_STATUS_NOT_ACCORD.getCode());
            resultDO.setErrMsg(ErrorConstant.HINT_STATUS_NOT_ACCORD.getMsg());
            return resultDO;
        }
        //交易员或拓铺员角色校验（交易员 ,且线索为该交易员认领 或者拓铺员，且线索拥有者为拓铺员）
        if(ValidateHelper.isEmpty(clerk.getRoleType())||ValidateHelper.isEmpty(clerkHint.getOwnerId())
                ||!((clerk.getRoleType().intValue()== ClerkRoleType.DEAL_CLERK.getValue()
                ||clerk.getRoleType().intValue() == ClerkRoleType.EXPAND_CLERK.getValue())
                && clerk.getId().longValue()==clerkHint.getOwnerId().longValue())){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NOT_AUTH_OPERATE.getCode());
            resultDO.setErrMsg(ErrorConstant.NOT_AUTH_OPERATE.getMsg());
            return resultDO;
        }

        if(clerkHintImgsDTO.getShopImgList()!=null && clerkHintImgsDTO.getShopImgList().size()>0){
            int delResult = clerkHintImgMapper.deleteByHintId(clerkHintImgsDTO.getId());
            for(int i=0;i<clerkHintImgsDTO.getShopImgList().size();i++){
                ShopImgDTO shopImgDTO = clerkHintImgsDTO.getShopImgList().get(i);
                ClerkHintImg clerkHintImg = new ClerkHintImg();
                clerkHintImg.setHintId(clerkHintImgsDTO.getId());
                clerkHintImg.setImgUrl(shopImgDTO.getImgUrl());
                clerkHintImg.setIsCover(shopImgDTO.getIsCover());
                clerkHintImg.setImgIndex(shopImgDTO.getImgIndex());
                clerkHintImg.setCreater(clerkHintImgsDTO.getModifier());
                clerkHintImg.setCreateTime(new Date());
                clerkHintImgMapper.insertSelective(clerkHintImg);
            }
        }

        resultDO.setSuccess(true);
        return resultDO;
    }
}
