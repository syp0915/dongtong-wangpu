package com.dongtong.shop.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dongtong.basic.dto.req.ServiceNoticeReqDTO;
import com.dongtong.basic.dto.req.ShopNoticeReqDTO;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.enums.ServiceNoticeType;
import com.dongtong.basic.enums.ShopServiceType;
import com.dongtong.basic.service.BaseShopNumberService;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.domain.ClerkHint;
import com.dongtong.clerk.enums.ClerkRoleType;
import com.dongtong.clerk.service.ClerkHintService;
import com.dongtong.clerk.service.ClerkService;
import com.dongtong.clerk.util.HttpUtil;
import com.dongtong.customer.domain.CustomerSchedule;
import com.dongtong.customer.dto.CustomerDTO;
import com.dongtong.customer.dto.CustomerInfoDTO;
import com.dongtong.customer.dto.resp.AttentionPlateRespDTO;
import com.dongtong.customer.dto.resp.AttentionVocationRespDTO;
import com.dongtong.customer.service.CustomerInfoService;
import com.dongtong.customer.service.ScheduleService;
import com.dongtong.shop.domain.*;
import com.dongtong.shop.dto.*;
import com.dongtong.shop.enums.*;
import com.dongtong.shop.env.ShopEnv;
import com.dongtong.shop.jobs.ShopScoreJob;
import com.dongtong.shop.manager.ShopManager;
import com.dongtong.shop.manager.ShopNearManager;
import com.dongtong.shop.manager.ShopShelfManager;
import com.dongtong.shop.query.*;
import com.shfc.common.base.Logger;
import com.shfc.common.base.RegexUtils;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.exception.AppException;
import com.shfc.common.math.RandomUtils;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Package com.dongtong.shop.service.ShopServiceImpl
 * @Description: ShopService 实现类
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/4 15:15
 * version V1.0.0
 */
@Service
public class ShopServiceImpl implements ShopService{
    @Autowired
    private ShopManager shopManager;
    @Autowired
    private ShopNearManager shopNearManager;
    @Autowired
    private CustomerInfoService customerInfoService;
    @Autowired
    private NoticePushService noticePushService;
    @Autowired
    private ClerkService clerkService;
    @Autowired
    private BaseShopNumberService baseShopNumberService;
    @Autowired
    private ClerkHintService clerkHintService;

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ShopShelfManager shopShelfManager;
    @Autowired
    private ShopScoreJob shopScoreJob;
    @Autowired
    private ShopEnv shopEnv;

    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(15);
    @Override
    public ResultDO<Page<ShopCustomerDTO>> queryShopByCustomer(ShopCustomerQuery query, Long customerId) {

        ResultDO<Page<ShopCustomerDTO>> resultDO = new ResultDO<>();
        String error = checkCustomerQueryParams(query);
        if(error != null){
            resultDO.setErrMsg(error);
            return resultDO;
        }
       /* if(query.getQueryType() == 0 && customerId == null){
            resultDO.setErrMsg("根据关注类型用户id必传");
            return resultDO;
        }*/

        if(customerId != null){
            // 用户登录查询 用户关注的商铺
            ResultDO<CustomerInfoDTO> customerDTOResultDO = customerInfoService.queryCustomerInfo(customerId);
            if(!customerDTOResultDO.isSuccess()){
                resultDO.setErrMsg(customerDTOResultDO.getErrMsg());
                resultDO.setErrCode(customerDTOResultDO.getErrCode());
                return resultDO;
            }
            CustomerInfoDTO dto = customerDTOResultDO.getData();
            //获取用户关注的 业态、板块、面积
            List<Long> followVocationList = new ArrayList<>();//用户关注的业态
            List<AttentionVocationRespDTO> vocationList = dto.fetchVocationList();
            if(vocationList!=null && !vocationList.isEmpty()){ //解析用户关注的业态，只需要业态名称
                for(AttentionVocationRespDTO vocation : vocationList){
                    if(vocation!=null && vocation.getVocationId()!=null){
                        followVocationList.add(vocation.getVocationId());
                        query.setFollowVocationList(followVocationList);
                    }
                }
            }
            List<String> followPlateList = new ArrayList<>();//用户关注的板块
            List<AttentionPlateRespDTO> plateList = dto.fetchPlateList();
            if(plateList!=null && !plateList.isEmpty()){ //解析用户关注的板块，只需要板块名称
                for(AttentionPlateRespDTO plate : plateList){
                    if(plate!=null && !ValidateHelper.isEmptyString(plate.getPlateName())) {
                        followPlateList.add(plate.getPlateName());
                        query.setFollowPlateList(followPlateList);
                    }
                }
            }
            if(!ValidateHelper.isEmpty(dto.fetchAreaList())){
                // 设置关注面积
                query.setFollowAreaList(dto.fetchAreaList());
            }
        }
        Page<ShopCustomerDTO> page = new Page<>(query.getPageNumber(), query.getPageSize());
        try {
            page.setQuery(query);
            resultDO.setData(shopManager.queryShopByCustomer(page));
            resultDO.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e, "客户端查询旺铺列表异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }

        return resultDO;
    }

    @Override
    public ResultDO<Page<ShopClerkDTO>> queryShopByClerk(ShopClerkQuery query) {
        ResultDO<Page<ShopClerkDTO>> resultDO = new ResultDO<>();

        String error = checkClerkQueryParams(query);
        if(error != null){
            resultDO.setErrMsg(error);
            return resultDO;
        }
        Page<ShopClerkDTO> page = new Page<>(query.getPageNumber(), query.getPageSize());
        try {
            page.setQuery(query);
            resultDO.setData(shopManager.queryShopByClerk(page));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "业务端查询旺铺列表异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }

        return resultDO;
    }

    @Override
    public ResultDO<LatestShopDTO> getLatestShop() {

        ResultDO<LatestShopDTO> resultDO = new ResultDO<>();
        try {
            resultDO.setData(shopManager.getLatestShop());
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "客户端查询最近正在转让的一个旺铺异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }

        return resultDO;
    }

    @Override
    public ResultDO<LatestShopDTO> getLatestShopByPhone(String phone) {
        ResultDO<LatestShopDTO> resultDO = new ResultDO<>();
        try {
            resultDO.setData(shopManager.getLatestShopByPhone(phone));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "客户端查询最近正在转让的一个旺铺异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }

        return resultDO;
    }

    @Override
    public ResultDO<ShopScanCountDTO> queryMyShopScanCount(String phone) {

        ResultDO<ShopScanCountDTO> resultDO = new ResultDO<>();
        try {
            resultDO.setData(shopManager.queryMyShopScanCount(phone));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "客户端查询最近正在转让的一个旺铺异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    /**
     * v1.2新需求 店铺必须由线索转换（判断线索状态 ：待实堪）
     * @param shopDTO
     * @return
     */
    @Override
    public ResultDO<Long> issueShop(final IssueShopDTO shopDTO){
        ResultDO<Long> resultDO = new ResultDO<>();
        Long hindId = shopDTO.getHintId();
        if(hindId==null){
            resultDO.setErrMsg("线索ID不能为空！");
            return resultDO;
        }
        ResultDO<ClerkHint> hintResultDO = clerkHintService.getClerkHintInfoById(hindId);
        if(hintResultDO.isSuccess()){
            ClerkHint hint = hintResultDO.getData();
            if(hint==null){
                resultDO.setErrMsg("线索不存在！");
                return resultDO;
            }else if(hint.getTradeClerkId()==null || hint.getTradeClerkId()!=shopDTO.getCreater()){
                resultDO.setErrMsg("该线索非当前业务员认领，不能发布商铺！");
                return resultDO;
            }else if(hint.getStatus()!=3){
                resultDO.setErrMsg("交易员待实勘的线索才能发布商铺！");
                return resultDO;
            }
            shopDTO.setShopCode(hint.getShopCode());//线索_二维码
            shopDTO.setIsPoster(hint.getHasPoster());//线索_是否贴海报
            shopDTO.setClerkId(hint.getTradeClerkId());//业务员ID来源线索
            shopDTO.setExpandClerkId(hint.getExpandClerkId());//拓铺员ID
            //线索地址没有改变，拷贝线索区域板块到商铺
            if(hint.getShopAddress().equals(shopDTO.getAddress())){
                shopDTO.setBlockId(hint.getBlockId());
                shopDTO.setBlockName(hint.getBlockName());
                shopDTO.setDistrictId(hint.getDistrictId());
                shopDTO.setDistrictName(hint.getDistrictName());
            }else {
                shopDTO.setHintAddress(hint.getShopAddress());
            }
        }else {
            resultDO.setErrMsg(hintResultDO.getErrMsg());
            return resultDO;
        }

        String checkResult  = checkIssueShop(shopDTO);
        if(!ValidateHelper.isEmptyString(checkResult)){
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }

        try {
            shopDTO.setRentStatus(RentStatus.RENT.getValue());
            shopDTO.setShelfStatus(ShelfStatus.PUT.getValue());//发布商铺默认状态-已出租、已上架
            final Long shopId = shopManager.issueShop(shopDTO);
            final Long expandClerkId  = shopDTO.getExpandClerkId();
            if (shopId!=null){
                fixedThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        ShopNoticeReqDTO shopNoticeReqDTO = new ShopNoticeReqDTO();
                        shopNoticeReqDTO.setBussinessId(shopId);
                        shopNoticeReqDTO.setReceiveId(expandClerkId);
                        shopNoticeReqDTO.setShopNoticeType(ShopServiceType.PUBLISH_SHOP.getValue());
                        ResultDO<Clerk> clerkResult = clerkService.getClerkInfoById(expandClerkId);
                        if(clerkResult.isSuccess()){
                            Clerk clerk = clerkResult.getData();
                            try {
                                ResultDO<Boolean> booleanResultDO = noticePushService.clerkShopNotice(shopNoticeReqDTO, clerk.getDeviceId(), clerk.getOsType());
                                Logger.info(this,"发布旺铺推送拓铺员，推送结果=="+booleanResultDO.toString());
                            }catch (Exception e){
                                Logger.error(e, "发布旺铺推送拓铺员异常");
                            }
                        }

                    }
                });
                //判断线索地址是否变更，更新商铺区域板块信息
                if(shopDTO.getHintAddress()!=null && !shopDTO.getHintAddress().equals(shopDTO.getAddress())){
                    fixedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            //解析商铺地址
                            String url = shopEnv.getGlobalappUrl()+"/point/calculate/v1.0.0?lat="+shopDTO.getLatitude()+"&lng="+shopDTO.getLongitude();
                            String urlJson = null;
                            try {
                                urlJson = HttpUtil.get(url);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            JSONObject object = JSON.parseObject(urlJson);
                            JSONObject dataObj = (JSONObject) object.get("data");
                            Shop shop  = shopManager.getShopById(shopId);
                            if(shop!=null){
                                shop.setDistrictId(dataObj.getLong("districtId"));
                                shop.setDistrictName(dataObj.getString("districtName"));
                                shop.setBlockId(dataObj.getLong("blockId"));
                                shop.setBlockName(dataObj.getString("blockName"));
                                shopManager.updateShopByPrimaryKeySelective(shop);
                            }
                        }
                    });
                }

                resultDO.setSuccess(true);
                resultDO.setData(shopId);
                return resultDO;
            }
        } catch (Exception e) {
            Logger.error(ShopServiceImpl.class,"发布商铺数据库保存异常");
            resultDO.setErrMsg("发布商铺数据库保存异常");
            return resultDO;
        }
        Logger.error(ShopServiceImpl.class,"发布商铺数据库保存异常");
        resultDO.setErrMsg("发布商铺数据库保存异常");
        return resultDO;
    }

    public Shop selectById(Long shopId){
        return shopManager.getShopById(shopId);
    }

    /**
     * 判断业务员的权限
     * @param clerkId
     * @param shop
     * @return
     */
    private ResultDO<Boolean> checkClerkLimit(Long clerkId,Shop shop){
        ResultDO<Boolean> resultDO = new ResultDO<>();
        ResultDO<Clerk> clerkResult = clerkService.getClerkInfoById(clerkId);
        if(clerkResult.isSuccess()){
            Clerk clerk = clerkResult.getData();
            Integer roleType = clerk.getRoleType();
            if(roleType!=null){
                int rentStatus = shop.getRentStatus().intValue();
                //商铺，需要判断权限
                if(roleType.intValue() != ClerkRoleType.DEAL_CLERK.getValue()){
                    resultDO.setErrCode(212);
                    resultDO.setErrMsg("非交易业务员不能修改商铺！");
                    return resultDO;
                }else if(shop.getClerkId().longValue()!=clerkId){
                    resultDO.setErrCode(213);
                    resultDO.setErrMsg("不能修改非本人发布的商铺！");
                    return resultDO;
                }
                resultDO.setSuccess(true);
                return resultDO;
            }else {
                resultDO.setErrMsg("业务员角色不正确！");
                return resultDO;
            }
        }else {
            resultDO.setErrCode(clerkResult.getErrCode());
            resultDO.setErrMsg(clerkResult.getErrMsg());
            return resultDO;
        }
    }
    /**
     * 修改商铺-租售信息
     * @param shopDTO
     * @return
     */
    @Override
    public ResultDO<Boolean> updateShopRent(IssueShopDTO shopDTO) {
        Long clerkId = shopDTO.getModifier();
        ResultDO<Boolean> resultDO = new ResultDO<>();
        Long shopId = shopDTO.getShopId();//商铺ID
        if(shopId==null){
            resultDO.setErrMsg("商铺ID不能为空！");
            return resultDO;
        }
        String checkResult = checkShopRent(shopDTO);
        if(checkResult!=null){
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }
        Shop shop = shopManager.getShopById(shopId);
        if(shop==null){
            resultDO.setErrMsg("商铺信息不存在！");
            return resultDO;
        }
        //权限判断
        ResultDO<Boolean> limitResult = checkClerkLimit(clerkId,shop);
        if(!limitResult.isSuccess()){
            resultDO.setErrCode(limitResult.getErrCode());
            resultDO.setErrMsg(limitResult.getErrMsg());
            return resultDO;
        }
        shop.setRentType(shopDTO.getRentType());
        Boolean result  = shopManager.updateShopByPrimaryKeySelective(shop);
        if(result){
            resultDO.setSuccess(true);
            resultDO.setData(true);
            return resultDO;
        }
        resultDO.setErrMsg("更新商铺租售信息失败！");
        return resultDO;
    }

    /**
     * 修改商铺-客户信息
     * @param shopDTO
     * @return
     */
    @Override
    public ResultDO<Boolean> updateShopContacter(IssueShopDTO shopDTO) {
        Long clerkId = shopDTO.getModifier();
        ResultDO<Boolean> resultDO = new ResultDO<>();
        Long shopId = shopDTO.getShopId();//商铺ID
        if(shopId==null){
            resultDO.setErrMsg("商铺ID不能为空！");
            return resultDO;
        }
        String checkResult  = checkShopContacter(shopDTO);
        if(checkResult!=null){
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }
        Shop shop = shopManager.getShopById(shopId);
        if(shop==null){
            resultDO.setErrMsg("商铺信息不存在！");
            return resultDO;
        }
        //权限判断
        ResultDO<Boolean> limitResult = checkClerkLimit(clerkId,shop);
        if(!limitResult.isSuccess()){
            resultDO.setErrCode(limitResult.getErrCode());
            resultDO.setErrMsg(limitResult.getErrMsg());
            return resultDO;
        }
        shop.setContacter(shopDTO.getContacter());
        shop.setContactTel(shopDTO.getContactTel());
        shop.setIsShow(shopDTO.getIsShow());
        Boolean result  = shopManager.updateShopByPrimaryKeySelective(shop);
        if(result){
            resultDO.setSuccess(true);
            resultDO.setData(true);
            return resultDO;
        }
        resultDO.setErrMsg("更新商铺客户信息失败！");
        return resultDO;
    }

    /**
     * 修改经营状况
     * @param shopDTO
     * @return
     */
    @Override
    public ResultDO<Boolean> updateShopOperateState(IssueShopDTO shopDTO) {
        ResultDO<Boolean> resultDO = new ResultDO<>();
        Long shopId = shopDTO.getShopId();//商铺ID
        if(shopId==null){
            resultDO.setErrMsg("商铺ID不能为空！");
            return resultDO;
        }
        String checkResult  = checkShopOperateState(shopDTO);
        if(checkResult!=null){
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }
        Shop shop = shopManager.getShopById(shopId);
        if(shop==null){
            resultDO.setErrMsg("商铺信息不存在！");
            return resultDO;
        }
        Long clerkId = shopDTO.getModifier();
        //权限判断
        ResultDO<Boolean> limitResult = checkClerkLimit(clerkId,shop);
        if(!limitResult.isSuccess()){
            resultDO.setErrCode(limitResult.getErrCode());
            resultDO.setErrMsg(limitResult.getErrMsg());
            return resultDO;
        }
        try {
            int  resultCount = shopManager.updateShopOperateState(shopDTO);
            if(resultCount== -1){
                resultDO.setErrMsg("商铺信息不存在！");
                return resultDO;
            }
            if(resultCount>0){
                resultDO.setSuccess(true);
                resultDO.setData(true);
                return resultDO;
            }
        } catch (AppException e) {
           Logger.error(e,"更新商铺经营状况信息失败！");
        }
        resultDO.setErrMsg("更新商铺经营状况信息失败！");
        return resultDO;
    }

    /**
     * 修改商铺-租赁相关的信息
     * @param shopDTO
     * @return
     */
    @Override
    public ResultDO<Boolean> updateShopRentParam(IssueShopDTO shopDTO) {
        ResultDO<Boolean> resultDO = new ResultDO<>();
        Long shopId = shopDTO.getShopId();//商铺ID
        if(shopId==null){
            resultDO.setErrMsg("商铺ID不能为空！");
            return resultDO;
        }
        String checkResult  = checkShopRentParam(shopDTO);
        if(checkResult!=null){
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }
        Shop shop = shopManager.getShopById(shopId);
        if(shop==null){
            resultDO.setErrMsg("商铺信息不存在！");
            return resultDO;
        }
        Long clerkId = shopDTO.getModifier();
        //权限判断
        ResultDO<Boolean> limitResult = checkClerkLimit(clerkId,shop);
        if(!limitResult.isSuccess()){
            resultDO.setErrCode(limitResult.getErrCode());
            resultDO.setErrMsg(limitResult.getErrMsg());
            return resultDO;
        }
        shop.setRent(shopDTO.getRent());
        shop.setTransferFee(shopDTO.getTransferFee());
        shop.setIsFace(shopDTO.getIsFace());
        shop.setCompactResidue(shopDTO.getCompactResidue());
        shop.setDeposit(shopDTO.getDeposit());

        Boolean result  = shopManager.updateByPrimaryKey(shop);
        if(result){
            resultDO.setSuccess(true);
            resultDO.setData(true);
            return resultDO;
        }
        resultDO.setErrMsg("更新商铺租赁相关的信息失败！");
        return resultDO;
    }
    /**
     * 修改商铺-位置相关的信息
     * @param shopDTO
     * @return
     */
    @Override
    public ResultDO<DistrictBlockDTO> updateShopSite(IssueShopDTO shopDTO) {
        ResultDO<DistrictBlockDTO> resultDO = new ResultDO<>();
        Long shopId = shopDTO.getShopId();//商铺ID
        if(shopId==null){
            resultDO.setErrMsg("商铺ID不能为空！");
            return resultDO;
        }
        String checkResult  = checkShopSiteParam(shopDTO);
        if(checkResult!=null){
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }
        Shop shop = shopManager.getShopById(shopId);
        if(shop==null){
            resultDO.setErrMsg("商铺信息不存在！");
            return resultDO;
        }
        Long clerkId = shopDTO.getModifier();
        //权限判断
        ResultDO<Boolean> limitResult = checkClerkLimit(clerkId,shop);
        if(!limitResult.isSuccess()){
            resultDO.setErrCode(limitResult.getErrCode());
            resultDO.setErrMsg(limitResult.getErrMsg());
            return resultDO;
        }
        if(!shopDTO.getAddress().equals(shop.getAddress())){
            String url = shopEnv.getGlobalappUrl()+"/point/calculate/v1.0.0?lat="+shopDTO.getLatitude()+"&lng="+shopDTO.getLongitude();
            String urlJson = null;
            try {
                urlJson = HttpUtil.get(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject object = JSON.parseObject(urlJson);
            JSONObject dataObj = (JSONObject) object.get("data");
            shop.setDistrictId(dataObj.getLong("districtId"));
            shop.setDistrictName(dataObj.getString("districtName"));
            shop.setBlockId(dataObj.getLong("blockId"));
            shop.setBlockName(dataObj.getString("blockName"));
        }
        shop.setAddress(shopDTO.getAddress());
        shop.setLongitude(shopDTO.getLongitude());
        shop.setLatitude(shopDTO.getLatitude());
        shop.setAddrIsShow(shopDTO.getAddrIsShow());
        Boolean result  = shopManager.updateShopByPrimaryKeySelective(shop);
        if(result){
            resultDO.setSuccess(true);
            DistrictBlockDTO dto = new DistrictBlockDTO();
            dto.setDistrictId(shop.getDistrictId());
            dto.setDistrictName(shop.getDistrictName());
            dto.setBlockId(shop.getBlockId());
            dto.setBlockName(shop.getBlockName());
            resultDO.setData(dto);
            return resultDO;
        }
        resultDO.setErrMsg("更新商铺位置相关的信息失败！");
        return resultDO;
    }

    /**
     * 修改商铺-建筑相关的信息
     * @param shopDTO
     * @return
     */
    @Override
    public ResultDO<Boolean> updateShopBuilding(IssueShopDTO shopDTO) {
        ResultDO<Boolean> resultDO = new ResultDO<>();
        Long shopId = shopDTO.getShopId();//商铺ID
        if(shopId==null){
            resultDO.setErrMsg("商铺ID不能为空！");
            return resultDO;
        }
        String checkResult  = checkShopBuildingParam(shopDTO);
        if(checkResult!=null){
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }
        Shop shop = shopManager.getShopById(shopId);
        if(shop==null){
            resultDO.setErrMsg("商铺信息不存在！");
            return resultDO;
        }
        Float depth  = shopDTO.getDepth();
        if(depth==null){
            if(shop.getDepth()!=null){
                shop.setDepth(null);
            }
        }else {
            shop.setDepth(shopDTO.getDepth());
        }
        Float height  = shopDTO.getHeight();//层高
        if(height==null){
            if(shop.getHeight()!=null){
                shop.setHeight(null);
            }
        }else {
            shop.setHeight(shopDTO.getHeight());
        }
        Long clerkId = shopDTO.getModifier();
        //权限判断
        ResultDO<Boolean> limitResult = checkClerkLimit(clerkId,shop);
        if(!limitResult.isSuccess()){
            resultDO.setErrCode(limitResult.getErrCode());
            resultDO.setErrMsg(limitResult.getErrMsg());
            return resultDO;
        }
        shop.setArea(shopDTO.getArea());
        shop.setFloor(shopDTO.getFloor());
        shop.setTotalFloor(shopDTO.getTotalFloor());
        shop.setWidth(shopDTO.getWidth());
        Boolean result  = shopManager.updateByPrimaryKey(shop);
        if(result){
            resultDO.setSuccess(true);
            resultDO.setData(true);
            return resultDO;
        }
        resultDO.setErrMsg("更新商铺建筑相关的信息失败！");
        return resultDO;
    }

    /**
     * 修改商铺-营运相关的信息
     * add by xiehb
     * @param shopDTO
     * @return
     */
    public ResultDO<Boolean> updateShopService (IssueShopDTO shopDTO){
        ResultDO<Boolean> resultDO = new ResultDO<>();
        Long shopId = shopDTO.getShopId();//商铺ID
        if(shopId==null){
            resultDO.setErrMsg("商铺ID不能为空！");
            return resultDO;
        }

        String checkResult  = checkShopServiceParam(shopDTO);
        if(checkResult!=null){
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }
        Shop shop = shopManager.getShopById(shopId);
        if(shop==null){
            resultDO.setErrMsg("商铺信息不存在！");
            return resultDO;
        }
        Long clerkId = shopDTO.getModifier();
        //权限判断
        ResultDO<Boolean> limitResult = checkClerkLimit(clerkId,shop);
        if(!limitResult.isSuccess()){
            resultDO.setErrCode(limitResult.getErrCode());
            resultDO.setErrMsg(limitResult.getErrMsg());
            return resultDO;
        }
        //水费、电费、燃气费、物业费 新增时不为空，需要校验数字类型
        Float waterRate = shopDTO.getWaterRate();//水费
        if(waterRate == null){
            if(shop.getWaterRate()!=null){
                shop.setWaterRate(null);
            }
        }else {
            shop.setWaterRate(waterRate);
        }
        Float electricRate = shopDTO.getElectricRate();//电费
        if(electricRate == null){
            if(shop.getElectricRate()!=null){
                shop.setElectricRate(null);
            }
        }else {
            shop.setElectricRate(shopDTO.getElectricRate());
        }
        Float gasRate = shopDTO.getGasRate();//燃气费
        if(gasRate == null){
            if(shop.getGasRate()!=null){
                shop.setGasRate(null);
            }
        }else {
            shop.setGasRate(shopDTO.getGasRate());
        }
        Float propertyRate =  shopDTO.getPropertyRate();//物业费
        if(propertyRate == null){
            if(shop.getPropertyRate()!=null){
                shop.setPropertyRate(null);
            }
        }else {
            shop.setPropertyRate(shopDTO.getPropertyRate());
        }
        Boolean result  = shopManager.updateByPrimaryKey(shop);
        if(result){
            resultDO.setSuccess(true);
            resultDO.setData(true);
            return resultDO;
        }
        resultDO.setErrMsg("更新商铺营运相关的信息失败！");
        return resultDO;
    }
    /**
     * 修改商铺-配套设施相关的信息
     * add by xiehb
     * @param shopDTO
     * @return
     */
    public ResultDO<Boolean> updateShopSupport (IssueShopDTO shopDTO){
        ResultDO<Boolean> resultDO = new ResultDO<>();
        Long shopId = shopDTO.getShopId();//商铺ID
        if(shopId==null){
            resultDO.setErrMsg("商铺ID不能为空！");
            return resultDO;
        }
        Shop shop = shopManager.getShopById(shopId);
        if(shop==null){
            resultDO.setErrMsg("商铺信息不存在！");
            return resultDO;
        }
        Long clerkId = shopDTO.getModifier();
        //权限判断
        ResultDO<Boolean> limitResult = checkClerkLimit(clerkId,shop);
        if(!limitResult.isSuccess()){
            resultDO.setErrCode(limitResult.getErrCode());
            resultDO.setErrMsg(limitResult.getErrMsg());
            return resultDO;
        }
        try {
            shopManager.updateShopSupport(shopDTO);
            resultDO.setSuccess(true);
            resultDO.setData(true);
            return resultDO;
        } catch (Exception e) {
            Logger.error(ShopServiceImpl.class,e.getMessage());
            resultDO.setErrMsg("更新商铺配套设施相关的信息失败！");
            return resultDO;
        }
    }

    /**
     * 修改商铺-临铺信息
     * @param shopDTO
     * @return
     */
    @Override
    public ResultDO<List<ShopNearDetailDTO>> updateShopNear(IssueShopDTO shopDTO) {
        ResultDO<List<ShopNearDetailDTO>> resultDO = new ResultDO<>();
        Long shopId = shopDTO.getShopId();//商铺ID
        if(shopId==null){
            resultDO.setErrMsg("商铺ID不能为空！");
            return resultDO;
        }
        Shop shop = shopManager.getShopById(shopId);
        if(shop==null){
            resultDO.setErrMsg("商铺信息不存在！");
            return resultDO;
        }
        Long clerkId = shopDTO.getModifier();
        //权限判断
        ResultDO<Boolean> limitResult = checkClerkLimit(clerkId,shop);
        if(!limitResult.isSuccess()){
            resultDO.setErrCode(limitResult.getErrCode());
            resultDO.setErrMsg(limitResult.getErrMsg());
            return resultDO;
        }
        shopDTO.setShopId(shopId);
        String checkResult = checkShopNear(shopDTO);
        if(!ValidateHelper.isEmptyString(checkResult)){
            resultDO.setErrMsg(checkResult);
            return resultDO;
        }
        try {
            shopNearManager.updateShopNear(shopDTO);
            List<ShopNearDetailDTO> nearInfoList = shopNearManager.selectNearShop(shopId);
            for (ShopNearDetailDTO list: nearInfoList) {
                if (null != list.getNearId() ) {
                    List<ShopImgDTO> nearImgList = shopNearManager.selectNearImg(list.getNearId());
                    if (null != nearImgList) {
                        list.setNearImg(nearImgList);
                    }
                }
            }
            resultDO.setSuccess(true);
            resultDO.setData(nearInfoList);
            return resultDO;
        } catch (AppException e) {
            Logger.error(e,"更新商铺临铺信息失败！");
        }
        resultDO.setErrMsg("更新商铺临铺信息失败！");
        return resultDO;
    }

    @Override
    public ResultDO<Boolean> delShopNear(Long nearId,Long currentId) {
        ResultDO<Boolean> resultDO = new ResultDO<>();
        if (nearId==null){
            resultDO.setErrMsg("临铺ID不能为空！");
            return resultDO;
        }
        ShopNear shopNear  = shopNearManager.selectById(nearId);
        if(shopNear==null){
            resultDO.setErrMsg("临铺信息不存在！");
            return resultDO;
        }else {
            Shop shop = shopManager.getShopById(shopNear.getShopId());
            //权限判断
            ResultDO<Boolean> limitResult = checkClerkLimit(currentId,shop);
            if(!limitResult.isSuccess()){
                resultDO.setErrCode(limitResult.getErrCode());
                resultDO.setErrMsg(limitResult.getErrMsg());
                return resultDO;
            }
        }
        try {
            shopNearManager.delShopNear(nearId);
            resultDO.setData(true);
            resultDO.setSuccess(true);
            return resultDO;
        } catch (AppException e) {
            e.printStackTrace();
            Logger.error(e,"删除临铺信息失败!");
        }
        resultDO.setErrMsg("删除临铺信息失败！");
        return resultDO;
    }

    @Override
    public ResultDO<List<ShopImgDTO>> shopNearImg(Long nearId) {
        ResultDO<List<ShopImgDTO>> resultDO = new ResultDO<>();
        if(nearId==null){
            resultDO.setErrMsg("临铺ID不能为空！");
            return resultDO;
        }
        ShopNear shopNear = shopNearManager.selectById(nearId);
        if(shopNear==null){
            resultDO.setErrMsg("临铺信息不存在！");
            return resultDO;
        }
        List<ShopImgDTO> list = shopNearManager.selectNearImg(nearId);
        resultDO.setData(list);
        resultDO.setSuccess(true);
        return resultDO;
    }

    @Override
    public ResultDO<Page<ShopCollectedCustomerDTO>> shopCollectedList(ShopCollectedQuery query) {
        ResultDO<Page<ShopCollectedCustomerDTO>> resultDO = new ResultDO<>();
        if(query == null){
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }
        if(query.getCustomerId()==null){
            resultDO.setErrMsg("客户ID不能为空");
            return resultDO;
        }
        Page<ShopCollectedCustomerDTO> page = new Page<>(query.getPageNumber(), query.getPageSize());
        try {
            page.setQuery(query);
            resultDO.setData(shopManager.shopCollectedList(page));
            resultDO.setSuccess(true);
            return resultDO;
        }catch (Exception e){
            Logger.error(e, "客户端查询收藏旺铺列表异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
            return resultDO;
        }
    }

    @Override
    public ResultDO<Page<ShopBrowseCustomerDTO>> shopBrowseList(ShopCollectedQuery query) {
        ResultDO<Page<ShopBrowseCustomerDTO>> resultDO = new ResultDO<>();
        if(query == null){
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }
        if(query.getCustomerId()==null){
            resultDO.setErrMsg("客户ID不能为空");
            return resultDO;
        }
        Page<ShopCollectedCustomerDTO> page = new Page<>(query.getPageNumber(), query.getPageSize());
        try {
            page.setQuery(query);
            resultDO.setData(shopManager.shopBrowseList(page));
            resultDO.setSuccess(true);
            return resultDO;
        }catch (Exception e){
            Logger.error(e, "客户端查询收藏旺铺列表异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
            return resultDO;
        }
    }


    @Override
    public ResultDO<Page<ShopCustomerPublishDTO>> queryMyPublishShop(BaseQuery query, String phone) {
        ResultDO<Page<ShopCustomerPublishDTO>> resultDO = new ResultDO<>();
        try {
            Page<ShopCustomerPublishDTO> page = new Page<>(query.getPageNumber(), query.getPageSize());
            page.setQuery(phone);
            resultDO.setData(shopManager.queryMyPublishShop(page));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "客户端查询我发布的金铺列表异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }

        return resultDO;
    }

    @Override
    public ResultDO<Boolean> unDoShop(ShopUndoDTO shopUndoDTO) {
        ResultDO<Boolean> resultDO = new ResultDO<>();
        try {
            if(shopUndoDTO.getShopId() == null){
                resultDO.setErrMsg("商铺id必填" );
                return resultDO;
            }
            if(shopUndoDTO.getType() == null){
                resultDO.setErrMsg("撤下类型不能为空");
                return resultDO;
            }else {
                shopUndoDTO.setRentStatus(shopUndoDTO.getType());
            }
            final Shop shop = shopManager.getShopById(shopUndoDTO.getShopId());
            if(shop == null){
                resultDO.setErrMsg("商铺不存在");
                return resultDO;
            }
            if(shopUndoDTO.getUndoType().intValue() == UndoType.CLERK.getValue()){
                // 业务员下架时
                if(shopUndoDTO.getUndoId().longValue() != shop.getClerkId().longValue()){
                    resultDO.setErrMsg("商铺不属于该业务员");
                    return resultDO;
                }
            }
            if(shopUndoDTO.getUndoType().intValue() == UndoType.CUSTOMER.getValue()){
                // 用戶下架时 判断该商铺是否属于该用户
                ResultDO<CustomerDTO> customerDTOResultDO = customerInfoService.customerInfo(shopUndoDTO.getUndoId());
                if(!customerDTOResultDO.isSuccess()){
                    resultDO.setErrMsg(customerDTOResultDO.getErrMsg());
                    resultDO.setErrCode(customerDTOResultDO.getErrCode());
                    return resultDO;
                }
                String phone = customerDTOResultDO.getData().getPhone();
                if(!shop.getContactTel().equals(phone)){
                    resultDO.setErrMsg("商铺不属于该用户");
                    return resultDO;
                }
            }

            if(null != shop.getShelfStatus() && shop.getShelfStatus().intValue() == ShelfStatus.OUT.getValue()){
                resultDO.setErrMsg("商铺已下架请勿重复操作");
                return resultDO;
            }
            Shop modifyShop = new Shop();
            modifyShop.setId(shop.getId());
            modifyShop.setVersion(shop.getVersion());
            modifyShop.setModifier(shopUndoDTO.getUndoId());
            modifyShop.setRentStatus(shopUndoDTO.getRentStatus());//出租状态
            modifyShop.setShelfStatus(ShelfStatus.OUT.getValue());//已下架
            modifyShop.setUndoType(shopUndoDTO.getUndoType());
            modifyShop.setUndoId(shopUndoDTO.getUndoId());
            modifyShop.setUndoTagId(shopUndoDTO.getTagId());
            modifyShop.setUndoContent(shopUndoDTO.getUndoContent());
            modifyShop.setUndoTime(new Date());

            ShopStatusFlow statusFlow = new ShopStatusFlow();
            statusFlow.setShopId(shopUndoDTO.getShopId());
            statusFlow.setStatus(shopUndoDTO.getRentStatus());
            statusFlow.setDescription("下架");
            statusFlow.setOperatorType(shopUndoDTO.getUndoType());
            statusFlow.setOperatorId(shopUndoDTO.getUndoId());
            statusFlow.setCreater(shopUndoDTO.getUndoId());
            ShopShelf shopShelf = new ShopShelf();//商铺上下架记录表
            shopShelf.setShopId(shop.getId());
            shopShelf.setStatus(ShelfStatus.OUT.getValue());
            shopShelf.setModifier(shopUndoDTO.getUndoId());

            ShopLog shopLog = null;//商铺操作日志表
            //业务员下架记录到日志表中
            if(shopUndoDTO.getUndoType().intValue() == UndoType.CLERK.getValue()){
                shopLog = new ShopLog();
                shopLog.setShopId(shop.getId());
                shopLog.setClerkId(shopUndoDTO.getUndoId());
                shopLog.setType(LogType.OUT.getValue());
                shopLog.setRemark("下架");
                shopLog.setCreater(shopUndoDTO.getUndoId());
            }
            Boolean resBool = shopManager.operatorShopStatus(modifyShop, statusFlow,shopShelf,shopLog);
            if(resBool){
                resultDO.setData(resBool);
                resultDO.setSuccess(true);
            }else {
                Logger.error(this,"商铺下架数据库修改异常");
                resultDO.setErrMsg("系统异常，请联系管理员");
            }
            if(shopUndoDTO.getUndoType().intValue() == UndoType.CUSTOMER.getValue()){
                // 用户端下架时-推送消息
                fixedThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        ResultDO<Clerk> clerkResultDO = clerkService.getClerkInfoById(shop.getClerkId());
                        if(clerkResultDO.isSuccess() && clerkResultDO.getData() !=null){
                            ShopNoticeReqDTO shopNoticeReqDTO = new ShopNoticeReqDTO();
                            shopNoticeReqDTO.setShopAddress(shop.getAddress());
                            shopNoticeReqDTO.setShopNoticeType(ShopServiceType.SOLD_OUT.getValue());
                            shopNoticeReqDTO.setBussinessId(shop.getId());
                            shopNoticeReqDTO.setReceiveId(shop.getClerkId());
                            shopNoticeReqDTO.setReceiveType(ReceiveType.CLERK.getValue());
                            try {
                                noticePushService.pushShopNotice(shopNoticeReqDTO, clerkResultDO.getData().getDeviceId(), clerkResultDO.getData().getOsType());
                            }catch (Exception e){
                                e.printStackTrace();
                                Logger.error(e, "推送下架商铺异常");
                            }
                        }
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(e, "下架商铺异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    @Override
    public ResultDO<Boolean> putAwayShop(Long clerkId, Long shopId) {
        ResultDO<Boolean> resultDO = new ResultDO<>();

        try {
            Shop shop = shopManager.getShopById(shopId);
            if(shop == null){
                resultDO.setErrMsg("商铺不存在");
                return resultDO;
            }
            // 业务员上架时
            if(clerkId.longValue() != shop.getClerkId().longValue()){
                resultDO.setErrMsg("商铺不属于该业务员");
                return resultDO;
            }
            if(shop.getShelfStatus() == ShelfStatus.PUT.getValue()){
                resultDO.setErrMsg("商铺已上架请勿重复操作");
                return resultDO;
            }
            Shop modifyShop = new Shop();
            modifyShop.setId(shopId);
            modifyShop.setVersion(shop.getVersion());
            modifyShop.setModifier(clerkId);
            modifyShop.setRentStatus(RentStatus.RENT.getValue());
            modifyShop.setShelfStatus(ShelfStatus.PUT.getValue());
            ShopStatusFlow statusFlow = new ShopStatusFlow();//店铺状态流转记录
            statusFlow.setShopId(shopId);
            statusFlow.setStatus(RentStatus.RENT.getValue());
            statusFlow.setDescription(ShelfStatus.PUT.getName());
            statusFlow.setOperatorType(OperatorType.CLERK.getValue());
            statusFlow.setOperatorId(clerkId);
            statusFlow.setCreater(clerkId);
            ShopShelf shopShelf = new ShopShelf();//商铺上下架记录表
            shopShelf.setShopId(shopId);
            shopShelf.setStatus(ShelfStatus.PUT.getValue());
            shopShelf.setRunSign(0);
            shopShelf.setCreater(clerkId);

            ShopLog shopLog = new ShopLog();//商铺操作日志表
            shopLog.setShopId(shop.getId());
            shopLog.setClerkId(clerkId);
            shopLog.setType(LogType.PUT.getValue());
            shopLog.setRemark("上架");
            shopLog.setCreater(clerkId);
            resultDO.setData(shopManager.operatorShopStatus(modifyShop, statusFlow,shopShelf,shopLog));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "上架商铺异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }

        return resultDO;
    }

    @Override
    public ResultDO<ShopCountDTO> countShopFromClient() {
        ResultDO<ShopCountDTO> resultDO = new ResultDO<>();
        try {
            resultDO.setData(shopManager.countShopFromClient());
            resultDO.setSuccess(true);
        }catch (Exception e) {
            Logger.error(e, "客户端：查询旺铺总数统计接口异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    @Override
    public ResultDO<ShopCountDTO> countShopFromService(Long clerkId) {
        ResultDO<ShopCountDTO> resultDO = new ResultDO<>();
        if (null == clerkId) {
            resultDO.setErrMsg("业务员id不能为空");
            return resultDO;
        }
        try {
            resultDO.setData(shopManager.countShopFromService(clerkId));
            resultDO.setSuccess(true);
        }catch (Exception e) {
            Logger.error(e, "业务端：查询全部商铺以及本月发布商铺数目接口异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    @Override
    public ResultDO<List<ShopImgDTO>> queryShopImg(Long shopId) {
        ResultDO<List<ShopImgDTO>> resultDO = new ResultDO<>();
        try {
            resultDO.setData(shopManager.queryShopImg(shopId));
            resultDO.setSuccess(true);
        }catch (Exception e) {
            Logger.error(e, "铺位照片查询接口异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    @Override
    public ResultDO<ShopDetailDTO> queryShopDetail(Long shopId) {
        ResultDO<ShopDetailDTO> resultDO = new ResultDO<>();
        try {
            //查询具体商铺信息
            ShopDetailDTO dto = shopManager.queryShopDetail(shopId);
            if (null == dto) {
                resultDO.setErrMsg("该商铺不存在或商铺已经出租");
                return resultDO;
            }
            //添加邻铺照片信息
            List<ShopNearDetailDTO> nearInfoList = dto.getNearInfoList();
            if (null != nearInfoList && nearInfoList.size() > 0) {
                List<ShopImgDTO> nearImgList = null;
                for (ShopNearDetailDTO list: nearInfoList) {
                    if (null != list && null != list.getNearId() ) {
                        nearImgList = shopNearManager.selectNearImg(list.getNearId());
                        if (null != nearImgList) {
                            list.setNearImg(nearImgList);
                        }
                    }
                }
            }
            resultDO.setData(dto);
            resultDO.setSuccess(true);
        }catch (Exception e) {
            Logger.error(e, "店铺详情接口异常："+e.getMessage());
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    @Override
    public ClerkDTO queryClerkByShopId(Long shopId) {
        return shopManager.queryClerkByShopId(shopId);
    }

    @Override
    public ClerkAndShopInfoDTO queryClerkAndShopInfoByShopId(Long shopId) {
        return shopManager.queryClerkAndShopInfoByShopId(shopId);
    }

    @Override
    public ResultDO<Page<ShopVisitCustomerDTO>> shopVisitedList(ShopVisitQuery query) {
        ResultDO<Page<ShopVisitCustomerDTO>> resultDO=new ResultDO<>();
        if(query.getCustomerId()==null){
            resultDO.setErrMsg("用户Id不能为空");
            return resultDO;
        }

//        if(ValidateHelper.isEmpty(query.getLongitude())||ValidateHelper.isEmpty(query.getLatitude())){
//            resultDO.setErrMsg("经纬度不能为空");
//            return resultDO;
//        }
        Page<ShopVisitCustomerDTO> page=new Page<>(query.getPageNumber(),query.getPageSize());
        try{
            page.setQuery(query);
            resultDO.setData(shopManager.shopVisitedList(page));
            resultDO.setSuccess(true);
            return resultDO;
        }catch (Exception e){
            Logger.error(e, "客户端查询约看过的旺铺列表异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
            return resultDO;
        }

    }

    @Override
    public Integer countShops() {
        return shopManager.countShops();
    }

    @Override
    public ResultDO<Long> getShopId(String shopCode) {
        ResultDO<Long> resultDO = new ResultDO<>();
        try {
            Long shopId = shopManager.getShopId(shopCode);
            if (null == shopId) {
                resultDO.setErrMsg("商铺不存在");
                return resultDO;
            }
            resultDO.setData(shopId);
            resultDO.setSuccess(true);
        }catch (Exception e) {
            Logger.error(e, "根据二维码编号查找商铺id异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    @Override
    public ResultDO<String> updateClaimInfo(Long shopId, Long claimId) {
        ResultDO<String> resultDO = new ResultDO<>();
        if (shopId == null) {
            resultDO.setErrMsg("商铺id不能为空");
            return resultDO;
        }
        if (claimId == null) {
            resultDO.setErrMsg("认领人id不能为空");
            return resultDO;
        }
        try {
            Shop shop = shopManager.getShopById(shopId);
            if(shop == null){
                resultDO.setErrMsg("商铺不存在");
                return resultDO;
            }

           /* if((shop.getRentStatus().intValue() != RentStatus.UN_CLAIM.getValue())
                    || shop.getClerkId() != null){
                resultDO.setErrMsg("该商铺已经被认领！");
                return resultDO;
            }*/

            Shop modifyShop = new Shop();
            modifyShop.setId(shopId);
            modifyShop.setClerkId(claimId);
            modifyShop.setRentStatus(RentStatus.STAY_RENT.getValue());//待出租状态
            modifyShop.setClaimTime(new Date());
            modifyShop.setVersion(shop.getVersion());
            modifyShop.setModifier(claimId);

            ShopStatusFlow statusFlow = new ShopStatusFlow();
            statusFlow.setShopId(shopId);
            statusFlow.setStatus(RentStatus.STAY_RENT.getValue());//待出租状态
            statusFlow.setDescription("认领");
            statusFlow.setOperatorType(OperatorType.CLERK.getValue());
            statusFlow.setOperatorId(claimId);
            statusFlow.setCreater(claimId);

            resultDO.setData(shopManager.updateClaimInfo(modifyShop, statusFlow));
            resultDO.setSuccess(true);

            if (shop.getHintId() != null) {
                //查询发布该线索是否是客户
                ResultDO<ClerkHint> clerkHintResultDO = clerkHintService.getClerkHintInfoById(shop.getHintId());
                if (clerkHintResultDO.isSuccess() && clerkHintResultDO.getData() != null) {
                    if (clerkHintResultDO.getData().getIssuerType() == ReceiveType.CUSTOMER.getValue()) {
                        //查询日程并修改状态
                        ResultDO<CustomerSchedule> customerSchedule =  scheduleService.queryScheduleByBizId(shop.getHintId(),0);
                        if(customerSchedule.isSuccess() && customerSchedule.getData() != null){
                            ResultDO ensureScheduleResult = scheduleService.ensureSchedule(customerSchedule.getData().getId(),clerkHintResultDO.getData().getIssuerId());
                            if(!ensureScheduleResult.isSuccess()){
                                Logger.error(ShopServiceImpl.class,resultDO.getErrMsg());
                            }
                        }

                        ResultDO<Clerk> clerkResultDO = clerkService.getClerkInfoById(claimId);
                        if(clerkResultDO.isSuccess() && clerkResultDO.getData() !=null){
                            ServiceNoticeReqDTO reqDTO = new ServiceNoticeReqDTO();
                            reqDTO.setServiceType(ServiceNoticeType.SHOP_RENT_SEEKING.getValue());//旺铺寻租
                            reqDTO.setServiceName(clerkResultDO.getData().getRealName());
                            reqDTO.setServiceTel(clerkResultDO.getData().getPhone());
                            reqDTO.setShopAddress(shop.getAddress());
                            reqDTO.setReceiveType(ReceiveType.CUSTOMER.getValue());
                            try {
                                noticePushService.pushServiceSucNotify(reqDTO,shop.getContactTel());
                            }catch (Exception e){
                                Logger.error(e, "认领成功发送短信异常");
                            }
                        }
                    }
                }
            }

        }catch (Exception e) {
            Logger.error(e, "更新商铺信息异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    @Override
    public ResultDO<String> shopCodeGenerate(ShopCodeDTO dto) {
        ResultDO<String> resultDO = new ResultDO<>();
        Integer count = dto.getCount();
        if(count>500){
            resultDO.setErrMsg("生成个数不能大于500个！");
            return resultDO;
        }
        int randomLength = 4;
        String cityCode = dto.getCityCode();
        if(ValidateHelper.isEmptyString(cityCode)){
            resultDO.setErrMsg("城市编号不能为空！");
            return resultDO;
        }else if(cityCode.length()>4){
            resultDO.setErrMsg("城市编号长度不能大于4位！");
            return resultDO;
        }else if(cityCode.length()==3){
            randomLength=randomLength+1;
        }
        HashSet<String> set = new HashSet<>();
        for (int i=0;i<count;i++){
            StringBuilder sbf = new StringBuilder();
            sbf.append(cityCode).append(RandomUtils.generateLowerString(randomLength)).append(RandomUtils.generateNumberString(4));
            set.add(sbf.toString());
        }
        try {
            for(String str:set){
                try {
                    baseShopNumberService.insertSingleShopNumber(str);
                }catch (Exception e){
                    Logger.info(this,"二维码 "+str+" 插入异常，可能存在重复数据");
                }
            }
            resultDO.setSuccess(true);
            return resultDO;
        }catch (Exception e){
            resultDO.setErrMsg("二维码插入异常，可能存在重复数据");
            return resultDO;
        }
    }

    @Override
    public ResultDO<Shop> getShopById(Long id) {
        ResultDO<Shop> resultDO = new ResultDO<>();
        if (null == id){
            resultDO.setErrMsg("商铺id不能为空！");
            return resultDO;
        }
        try {
            resultDO.setData(shopManager.getShopById(id));
            resultDO.setSuccess(true);
        }catch (Exception e){
            resultDO.setErrMsg("查找商铺信息失败："+e.getMessage());
        }
        return resultDO;
    }

    @Override
    public ResultDO<Shop> getShopByHitId(Long hintId) {
        ResultDO<Shop> resultDO = new ResultDO<>();
        if (null == hintId){
            resultDO.setErrMsg("商铺线索id不能为空！");
            return resultDO;
        }
        try {
            resultDO.setData(shopManager.getShopByHitId(hintId));
            resultDO.setSuccess(true);
        }catch (Exception e){
            resultDO.setErrMsg("查找商铺信息失败："+e.getMessage());
        }
        return resultDO;
    }

    @Override
    public void calculateShopScore() {
        try {
            int index = 1;
            Page page = shopShelfManager.queryShopShelf(index, 500);
            int totalPage = page.getTotalPage();
            while (index <= totalPage){
                shopScoreJob.executeShopShelf(page.getData());
                index ++;
                page = shopShelfManager.queryShopShelf(index, 500);
            }
        }catch (Exception e){
            Logger.error(ShopServiceImpl.class, "商铺分值权重跑批" + e.getMessage(), e);
        }
    }

    /**
     * 校验公共请求参数
     * @param query
     * @return
     */
    private String checkQueryParams(BaseShopQuery query){
        if(query == null)
            return "请求参数不能为空";

        if(ValidateHelper.isEmpty(query.getLongitude()) &&
                !ValidateHelper.isEmpty(query.getLatitude()))
            return "经度不能为空";
        if(!ValidateHelper.isEmpty(query.getLongitude()) &&
                ValidateHelper.isEmpty(query.getLatitude()))
            return "纬度不能为空";

        if(!ValidateHelper.isEmpty(query.getRentList())){
            for(Integer rent: query.getRentList()){
                if(rent == null || rent < 1 || rent > 6)
                    return "租金参数错误";
            }
        }
        if(!ValidateHelper.isEmpty(query.getTransferList())){
            for(Integer transfer: query.getTransferList()){
                if(transfer == null || transfer < 1 || transfer > 6)
                    return "转让费参数错误";
            }
        }
        if(!ValidateHelper.isEmpty(query.getAreaList())){
            for(Integer area: query.getAreaList()){
                if(area == null || area < 1 || area > 7)
                    return "面积参数错误";
            }
        }

        return null;
    }

    /**
     * 客户端请求参数
     * @param query
     * @return
     */
    private String checkCustomerQueryParams(ShopCustomerQuery query){
        String error  = checkQueryParams(query);
        if(error != null) return error;

       /* if(query.getQueryType() == null ||
                query.getQueryType() < 0 || query.getQueryType() > 1)
            return "筛选类型错误";
*/
        if(query.getShopType() != null && (query.getShopType() < 0 || query.getShopType() > 3))
            return "街铺主题类型错误";

        if(query.getSortType() != null && (query.getSortType() < 1 || query.getSortType() > 4))
            return "排序方式错误";

        if(query.getSortType() != null && query.getSortType() == 2
                && (ValidateHelper.isEmpty(query.getLongitude()) || ValidateHelper.isEmpty(query.getLatitude())))
            return "按距离排序经纬度必传";

        return null;
    }

    /**
     * 客户端请求参数
     * @param query
     * @return
     */
    private String checkClerkQueryParams(ShopClerkQuery query){
        String error = checkQueryParams(query);
        if(error != null) return error;

        /*if(ValidateHelper.isEmpty(query.getLongitude()) || ValidateHelper.isEmpty(query.getLatitude())){
            return "经纬度不能为空";
        }*/

        if(query.getShopType() == null)
            return "查询街铺类型不能为空";

        // 0-我的店铺 1-全部店铺
        if(query.getShopType() != null && (query.getShopType() < 0 || query.getShopType() > 1))
            return "查询街铺类型错误";

        if(query.getShopType() == 0 && query.getClerkId() == null)
            return  "业务员id不能为空";

        //1-出租中 2-暂不出租 3-已出租
        if(query.getRentStatus() != null && query.getRentStatus().size() > 3)
            return "出租状态错误";

        if(query.getRentStatus() != null && query.getRentStatus().size() > 0){
            for(Integer rentStatus: query.getRentStatus()){
                if(rentStatus == null || rentStatus < 1 || rentStatus > 3)
                    return "出租状态参数错误";
            }
        }
        if(query.getShelfStatus()!=null && query.getShelfStatus().size()> 2){
            return "发布状态错误";
        }else if(query.getShelfStatus()!=null && !query.getShelfStatus().isEmpty()){
            for(int shelfStatus : query.getShelfStatus()){
                if(shelfStatus<0 || shelfStatus>1){
                    return "发布状态参数错误";
                }
            }
        }
        //1-按距离（升序） 2-按热度（倒序） 3-更新时间（倒序） v1.2 新排序规则 1:发布时间 2:更新时间
        if(query.getSortType() != null && (query.getSortType() < 1 || query.getSortType() > 2))
            return "排序方式错误";

        return null;
    }

    /**
     * check租售信息
     * @param shopDTO
     * @return
     */
    private String checkShopRent(IssueShopDTO shopDTO){
        Integer rentType = shopDTO.getRentType();
        if(rentType==null){
            return "出租类型不能为空！";
        }else if(rentType.intValue()<0 || rentType.intValue()>1){
            return "出租类型输入不正确！";
        }
        return null;
    }

    /**
     * check 商铺联系人信息
     * @param shopDTO
     * @return
     */
    private String checkShopContacter(IssueShopDTO shopDTO){
        String contacter = shopDTO.getContacter();
        if(ValidateHelper.isEmptyString(contacter)){
            return "联系人不能为空！";
        }else if(contacter.length()>20){
            return "联系人长度最多20字！";
        }
        String contactTel = shopDTO.getContactTel();
        if(ValidateHelper.isEmptyString(contactTel)){
            return "联系人电话不能为空！";
        }else if(!RegexUtils.isInteger(contactTel)){
            return "联系人电话不正确！";
        }else if(contactTel.length()!=11){
            return "联系人电话必须11位！";
        }
        Integer isShow = shopDTO.getIsShow();//是否在前台显示 0-否 1-是
        if(isShow==null){
           // shopDTO.setIsShow(0);//未勾选 默认否
            return "是否在前台显示接口参数必填！";
        }else if(isShow.intValue()<0 || isShow.intValue()>1){
            return "是否在前台显示参数错误！";
        }
        return null;
    }

    /**
     * check 商铺图片
     * @param shopImgList
     * @return
     */
    private String checkShopImg(List<ShopImgDTO> shopImgList){
        if(shopImgList==null || shopImgList.size()==0){
            return "商铺图片不能为空！";
        }
        if(shopImgList.size()>10){
            return "商铺图片不能大于10张！";
        }
        List<String> tempList = new ArrayList<>();//临时变量 保存是否设置封面
        Iterator<ShopImgDTO> iterator = shopImgList.iterator();
        while (iterator.hasNext()){
            ShopImgDTO dto = iterator.next();
            String imgUrl = dto.getImgUrl();
            if(ValidateHelper.isEmptyString(imgUrl)){
                return "图片地址不能为空！";
            }
            Integer isCover = dto.getIsCover();//是否为封面(0-否 1-是)
            if(isCover==null){
                return "商铺图片是否为封面必填！";
            }
            tempList.add(String.valueOf(isCover));
        }
        if(tempList.remove("1")){
            if(tempList.contains("1")){
                return "商铺图片只能设置一张封面！";
            }
        }else {
            return "商铺图片是否为封面必填！";
        }
        return null;
    }
    /**
     * check 临铺图片
     * @param shopImgList
     * @return
     */
    private String checkShopNearImg(List<ShopImgDTO> shopImgList){
        if(shopImgList==null || shopImgList.size()==0){
            return "临铺图片不能为空！";
        }
        if(shopImgList.size()>10){
            return "临铺图片不能大于10张！";
        }
        List<String> tempList = new ArrayList<>();//临时变量 保存是否设置封面
        Iterator<ShopImgDTO> iterator = shopImgList.iterator();
        while (iterator.hasNext()){
            ShopImgDTO dto = iterator.next();
            String imgUrl = dto.getImgUrl();
            if(ValidateHelper.isEmptyString(imgUrl)){
                return "临铺图片地址不能为空！";
            }
            Integer isCover = dto.getIsCover();//是否为封面(0-否 1-是)
            if(isCover==null){
                return "临铺图片是否为封面必填！";
            }
            tempList.add(String.valueOf(isCover));
        }
        if(tempList.remove("1")){
            if(tempList.contains("1")){
                return "临铺图片只能设置一张封面！";
            }
        }else {
            return "临铺图片是否为封面必填！";
        }
        return null;
    }
    /**
     * check 商铺经营状况
     * @param shopDTO
     * @return
     */
    private String checkShopOperateState(IssueShopDTO shopDTO){
        Integer operateStatus = shopDTO.getOperateStatus();
        if(operateStatus==null){
            return "经营状态不能为空！";
        }else if(operateStatus.intValue()<0 || operateStatus.intValue()>1){
            return "经营状态输入不合法！";
        }
        String shopName = shopDTO.getShopName();
        if(ValidateHelper.isEmptyString(shopName)){
            return "店铺名称不能为空！";
        }else if(shopName.length()>20){
            return "店铺名称最多20字！";
        }
        Long industryId = shopDTO.getIndustryId();
        if(industryId==null){
            return "所属业态不能为空！";
        }
        String checkShopImg = checkShopImg(shopDTO.getShopImgList());
        if(checkShopImg!=null){
            return checkShopImg;
        }
        return null;
    }
    private String checkShopRentParam(IssueShopDTO shopDTO){
        BigDecimal rent =  shopDTO.getRent();
        if(rent==null){
            return "租金不能为空！";
        }else if(!RegexUtils.isInteger(rent.toString())){
            return "租金只能是整数!";
        }else if(rent.toString().length()>8){
            return "租金输入不合法!";
        }

        BigDecimal deposit = shopDTO.getDeposit();
        if(deposit!=null){
            if(deposit.toString().length()>8){
                return "押金输入不合法!";
            }else if(!RegexUtils.isInteger(deposit.toString())){
                return "押金只能是整数!";
            }
        }

        BigDecimal transferFee =shopDTO.getTransferFee();
        if(transferFee!=null){
            if(!RegexUtils.isInteger(transferFee.toString())){
                return "转让费只能是整数!";
            }else if(transferFee.toString().length()>8){
                return "转让费输入不合法!";
            }
        }
        Integer isFace = shopDTO.getIsFace();
        if(isFace==null){
            //shopDTO.setIsFace(0);//是否面议 0-否 1-是
            return "是否面议不能为空！";
        }else if(isFace.intValue()<0|| isFace.intValue()>1){
            return "是否面议输入不正确！";
        }
        Integer compactResidue = shopDTO.getCompactResidue();
        if(compactResidue!=null){
            if(compactResidue<0 || compactResidue>2){
                return "合同剩余输入不正确！";
            }
        }
        return null;
    }

    /**
     * 位置信息校验
     * @param shopDTO
     * @return
     */
    private String checkShopSiteParam(final IssueShopDTO shopDTO){
        String address = shopDTO.getAddress();
        if(ValidateHelper.isEmptyString(address)){
            return "街铺地址不能为空！";
        }else if(address.length()>120){
            return "街铺地址超长！";
        }
        final String longitude = shopDTO.getLongitude();
        if(ValidateHelper.isEmptyString(longitude)){
            return "经度不能为空！";
        }
        final String latitude = shopDTO.getLatitude();
        if(ValidateHelper.isEmptyString(latitude)){
            return "纬度不能为空！";
        }
        Integer addrIsShow = shopDTO.getAddrIsShow();//地址是否在前台显示 0-否 1-是
        if(addrIsShow==null){
            return "是否在前台显示地址,参数必填！";
        }else if(addrIsShow.intValue()<0 || addrIsShow.intValue()>1){
            return "是否在前台显示地址,参数错误！";
        }
        return null;
    }
    /**
     * 位置信息校验
     * @param shopDTO
     * @return
     */
    private String checkShopBuildingParam(IssueShopDTO shopDTO){
        String floor = shopDTO.getFloor();
        if(ValidateHelper.isEmptyString(floor)){
            return "所在楼层不能为空！";
        }
        String[] floorArray = floor.split(",");
        int maxFloor = 0;//最大楼层值
        for (String fl : floorArray){
            if(RegexUtils.isInteger(fl)){
                int floorNum  = Integer.parseInt(fl);
                if(floorNum<-4 || floorNum>6){
                    return "所在楼层输入不正确！";
                }
                if(floorNum>maxFloor){
                    maxFloor =  floorNum;
                }
            }else {
                return "所在楼层必须是数字！";
            }
        }
        String totalFloor = shopDTO.getTotalFloor();
        if(ValidateHelper.isEmptyString(totalFloor)){
            return "总楼层不能为空！";
        }
        if(RegexUtils.isInteger(totalFloor)){
            int totalFloorNum  = Integer.parseInt(totalFloor);
            if(totalFloorNum<1 || totalFloorNum>6){
                return "总楼层输入不正确！";
            }else if (totalFloorNum<maxFloor){
                return "总楼层必须大于所在楼层！";
            }
        }else {
            return "总楼层必须是数字";
        }
        Float area = shopDTO.getArea();
        if(area==null || area.isNaN()){
            return "面积不能为空！";
        }else {
            if(!RegexUtils.find(area.toString(),"^(([1-9]{1,5})|([0-9]{1,5}\\.[0-9]{1,2}))$")){
                return "面积输入不合法！";
            }
        }
        Float width = shopDTO.getWidth();
        if(width==null || width.isNaN()){
            return "面宽不能为空！";
        }else {
            if(!RegexUtils.find(width.toString(),"^(([1-9]{1,5})|([0-9]{1,5}\\.[0-9]{1,2}))$")){
                return "面宽输入不合法！";
            }
        }
        Float depth  = shopDTO.getDepth();
        if(depth!=null){
            if(!RegexUtils.find(depth.toString(),"^(([1-9]{1,5})|([0-9]{1,5}\\.[0-9]{1,2}))$")){
                return "进深输入不合法！";
            }
        }
        Float height  = shopDTO.getHeight();//层高
        if(height!=null){
            if(!RegexUtils.find(height.toString(),"^(([1-9]{1,5})|([0-9]{1,5}\\.[0-9]{1,2}))$")){
                return "层高输入不合法！";
            }
        }
        return null;
    }

    private String checkShopServiceParam(IssueShopDTO shopDTO){
        //水费、电费、燃气费、物业费 新增时不为空，需要校验数字类型
        Float waterRate = shopDTO.getWaterRate();//水费
        if(waterRate!=null){
            if(!RegexUtils.find(waterRate.toString(),"^(([1-9]{1,5})|([0-9]{1,5}\\.[0-9]{1,2}))$")){
                return "水费输入不合法！";
            }
        }
        Float electricRate = shopDTO.getElectricRate();//电费
        if(electricRate!=null){
            if(!RegexUtils.find(electricRate.toString(),"^(([1-9]{1,5})|([0-9]{1,5}\\.[0-9]{1,2}))$")){
                return "电费输入不合法！";
            }
        }
        Float gasRate = shopDTO.getGasRate();//燃气费
        if(gasRate!=null){
            if(!RegexUtils.find(gasRate.toString(),"^(([1-9]{1,5})|([0-9]{1,5}\\.[0-9]{1,2}))$")){
                return "燃气费输入不合法！";
            }
        }
        Float propertyRate =  shopDTO.getPropertyRate();//物业费
        if(propertyRate!=null){
            if(!RegexUtils.find(propertyRate.toString(),"^(([1-9]{1,5})|([0-9]{1,5}\\.[0-9]{1,2}))$")){
                return "物业费输入不合法！";
            }
        }
        return null;
    }

    private String checkShopSupportParam(IssueShopDTO shopDTO){
        List<Long> support = shopDTO.getSupport();
        if(support==null || support.isEmpty()){
            return "配套不能为空！";
        }
        return null;
    }

    private String checkIssueShop(IssueShopDTO shopDTO){
        if(shopDTO==null){
            return "请求参数不能为空！";
        }
        String checkShopImg = checkShopImg(shopDTO.getShopImgList());
        if(checkShopImg!=null){
            return checkShopImg;
        }
        String checkShopRent = checkShopRent(shopDTO);
        if(checkShopRent!=null){
            return checkShopRent;
        }
        String checkShopContacter = checkShopContacter(shopDTO);
        if(checkShopContacter!=null){
            return checkShopContacter;
        }
        String checkShopSite = checkShopSiteParam(shopDTO);
        if(checkShopSite!=null){
            return checkShopSite;
        }
        String checkShopBuilding = checkShopBuildingParam(shopDTO);
        if(checkShopBuilding!=null){
            return checkShopBuilding;
        }
        Integer isPoster = shopDTO.getIsPoster();//是否贴海报
        if(isPoster==null){
            return "是否贴海报不能为空！";
        }else if(isPoster.intValue()<0 || isPoster.intValue()>1){
            return "是否贴海报,参数错误！";
        }
        String shopCode = shopDTO.getShopCode();
        if(ValidateHelper.isEmptyString(shopCode)){
            return "二维码编号不能为空！";
        }else if(shopCode.length()<11 || shopCode.length()>12) {
            return "二维码长度不正确！";
        }

        String checkShopOperateState = checkShopOperateState(shopDTO);
        if(checkShopOperateState!=null){
            return checkShopOperateState;
        }
        Integer rentWay = shopDTO.getRentWay();
        if(rentWay==null){
            return "付款方式不能为空！";
        }else if(rentWay.intValue()<0 || rentWay.intValue()>4){
            //付款方式(0- 按月收取 1-季付 2-半年付 3-按年收取 4-两月付)
            return "付款方式输入不正确！";
        }
        String checkRentParam  = checkShopRentParam(shopDTO);
        if(checkRentParam!=null){
            return checkRentParam;
        }
        String checkServiceParam = checkShopServiceParam(shopDTO);
        if(checkServiceParam!=null){
            return checkServiceParam;
        }
        String checkNearShop = checkShopNear(shopDTO);
        if(checkNearShop != null){
            return checkNearShop;
        }
        return null;
    }

    /**
     * 发布商铺时校验临铺信息
     * @param shopDTO
     * @return
     */
    private String checkShopNear(IssueShopDTO shopDTO){
        //临铺信息左1
        ShopNearDTO nearLeftOne = shopDTO.getNearLeftOne();
        // 临铺信息左2
        ShopNearDTO nearLeftTwo = shopDTO.getNearLeftTwo();
        // 临铺信息右1
        ShopNearDTO nearRightOne = shopDTO.getNearRightOne();
        // 临铺信息右2
        ShopNearDTO nearRightTwo = shopDTO.getNearRightTwo();
        Long shopId = shopDTO.getShopId();//新增时shopId为空，更新时根据shopId和nearId一起查询临铺校验
        if(nearLeftOne!=null){
            String checkNearLeftOne = checkShopNearDTO(nearLeftOne,shopId);
            if(checkNearLeftOne!=null){
                return checkNearLeftOne;
            }
        }
        if(nearLeftTwo!=null){
            String checkNearLeftTwo = checkShopNearDTO(nearLeftTwo,shopId);
            if(checkNearLeftTwo!=null){
                return checkNearLeftTwo;
            }
        }
        if(nearRightOne!=null){
            String checkNearRightOne = checkShopNearDTO(nearRightOne,shopId);
            if(checkNearRightOne!=null){
                return checkNearRightOne;
            }
        }
        if(nearRightTwo!=null){
            String checkNearRightTwo = checkShopNearDTO(nearRightTwo,shopId);
            if(checkNearRightTwo!=null){
                return checkNearRightTwo;
            }
        }
        return null;
    }
    private String checkShopNearDTO(ShopNearDTO nearDTO,Long shopId){
        Long nearId = nearDTO.getNearId();//临铺ID
        //更新临铺时nearId+shopId一起校验
        if(nearId!=null){
            ShopNear shopNear = shopNearManager.selectNearByIdAndShopId(nearId,shopId);
            if(shopNear==null){
                return "临铺信息匹配不成功！";
            }
        }
        List<ShopImgDTO> nearShopImgList  = nearDTO.getNearImg();
        String checkShopNearImg = checkShopNearImg(nearShopImgList);
        if(checkShopNearImg!=null){
            return checkShopNearImg;
        }
        String name = nearDTO.getName();
        if(ValidateHelper.isEmptyString(name)){
            return "临铺名称不能为空！";
        }
        Long industryId = nearDTO.getIndustryId();
        if(industryId == null){
            return "临铺所属业态不能为空！";
        }
        return null;
    }

    /**
     * @description
     * @package com.dongtong.shop.service
     * @author chenxs
     * @date 2017/8/11 0011 16:29
     * @param shopRecomendQuery
     * @return ResultDO<ShopRecomendListDTO>
     */
    public ResultDO<ShopRecomendListDTO> getRecomendShop(ShopRecomendQuery shopRecomendQuery){
        ResultDO<ShopRecomendListDTO> resultDO = new ResultDO<>();
        if(ValidateHelper.isEmpty(shopRecomendQuery.getShopId())){
            resultDO.setSuccess(false);
            resultDO.setErrMsg("shopId为空");
        }

        ShopRecomendListDTO shopRecomendListDTO = shopManager.getRecomendShop(shopRecomendQuery);
        resultDO.setData(shopRecomendListDTO);
        resultDO.setSuccess(true);
        return resultDO;
    }
}
