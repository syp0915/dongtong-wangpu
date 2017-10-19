package com.dongtong.shop.manager;

import com.dongtong.clerk.constant.ClerkHintStatusEnum;
import com.dongtong.clerk.dto.ClerkHintDTO;
import com.dongtong.clerk.service.ClerkHintService;
import com.dongtong.shop.dao.*;
import com.dongtong.shop.domain.Shop;
import com.dongtong.shop.domain.ShopLog;
import com.dongtong.shop.domain.ShopShelf;
import com.dongtong.shop.domain.ShopStatusFlow;
import com.dongtong.shop.dto.*;
import com.dongtong.shop.enums.ShelfStatus;
import com.dongtong.shop.jobs.ShopStationRelJob;
import com.dongtong.shop.query.ShopRecomendQuery;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.exception.AppException;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Package com.dongtong.shop.manager.ShopManager
 * @Description: ShopManager
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/4 16:15
 * version V1.0.0
 */
@Service
public class ShopManager {
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ShopImgMapper shopImgMapper;
    @Autowired
    private ShopSupportTagRelMapper shopSupportTagRelMapper;
    @Autowired
    private ShopOperateRelMapper shopOperateRelMapper;
    @Autowired
    private ShopFeatureTagRelMapper shopFeatureTagRelMapper;
    @Autowired
    private ShopStatusFlowMapper shopStatusFlowMapper;
    @Autowired
    private ShopStationRelJob shopStationRelJob;
    @Autowired
    private ClerkHintService clerkHintService;
    @Autowired
    private ShopNearManager shopNearManager;
    @Autowired
    private ShopShelfMapper shopShelfMapper;
    @Autowired
    private ShopLogMapper shopLogMapper;

    public Shop getShopById(Long shopId){
        return shopMapper.selectByPrimaryKey(shopId);
    }

    public Shop getShopByHitId(Long hintId){
        return shopMapper.selectByPrimaryKey(hintId);
    }

    @Transactional
    public Boolean updateShopByPrimaryKeySelective(Shop shop){
        int count = shopMapper.updateByPrimaryKeySelective(shop);
        updateShopBuildStationRel(shop);
        if(count == 1)
            return true;

        return false;
    }

    @Transactional
    public Boolean updateByPrimaryKey(Shop shop){

        int count = shopMapper.updateByPrimaryKey(shop);
        updateShopBuildStationRel(shop);
        if(count == 1)
            return true;

        return false;
    }

    /**
     * 修改商铺时变更经纬度则需从新简历地铁站关系
     * @param shop
     */
    private void  updateShopBuildStationRel(Shop shop){
        if(!ValidateHelper.isEmptyString(shop.getLatitude())
                && !ValidateHelper.isEmptyString(shop.getLongitude())){
            Shop domain = shopMapper.selectByPrimaryKey(shop.getId());
            if(!shop.getLatitude().equals(domain.getLatitude()) ||
                    !shop.getLongitude().equals(domain.getLongitude())){
                shopStationRelJob.asyncShopStationRelJob(shop.getId());
            }

        }
    }

    public Page<ShopCustomerDTO> queryShopByCustomer(Page<ShopCustomerDTO> page){

        shopMapper.queryShopByCustomer(page);

        return page;
    }

    public Page<ShopClerkDTO> queryShopByClerk(Page<ShopClerkDTO> page){

        shopMapper.queryShopByClerk(page);

        return page;
    }

    public LatestShopDTO getLatestShop(){
        return shopMapper.getLatestShop();
    }

    public LatestShopDTO getLatestShopByPhone(String phone){
        return shopMapper.getLatestShopByPhone(phone);
    }

    /**
     * 发布商铺数据库流程
     * 1.商铺基本信息
     * 2.商铺照片
     * 3.商铺配套
     * 4.经营范围(可选)
     * 5.特色
     * 6.添加商铺状态流转记录
     * 7.临铺信息(可选)
     * 8.地铁站关系信息
     * 9.记录上下架
     * @param shopDTO
     * @return
     */
    @Transactional(rollbackFor = AppException.class)
    public Long issueShop(IssueShopDTO shopDTO) throws AppException{
        Shop shop = new Shop();
        BeanUtils.copyProperties(shopDTO,shop);
        //1.商铺基础信息保存
        shopMapper.insert(shop);
        Long shopId = shop.getId();
        List<ShopImgDTO> shopImgList = shopDTO.getShopImgList();
        //2.保存商铺图片
        shopImgMapper.insertList(shopImgList,shopId);
        List<Long> support = shopDTO.getSupport();//配套
        if(support!=null && !support.isEmpty()){
            //3.保存商铺配套
            shopSupportTagRelMapper.insertList(support,shopId);
        }
        List<Long> operateRel = shopDTO.getOperateRel();//适合经营
        if(operateRel!=null && operateRel.size()>0){
            //4.保存适合经营
            shopOperateRelMapper.insertList(operateRel,shopId);
        }
        List<Long> feature = shopDTO.getFeature();//特色
        if(feature!=null && !feature.isEmpty()){
            //5.保存商铺特色
            shopFeatureTagRelMapper.insertList(feature,shopId);
        }
        ShopStatusFlow record = new ShopStatusFlow();
        record.setShopId(shopId);
        record.setOperatorId(shopDTO.getClerkId());
        record.setOperatorType(0);
        record.setStatus(1);
        //6.添加商铺状态流转记录
        shopStatusFlowMapper.insert(record);
        //7.保存临铺信息
        shopNearManager.saveNearShop(shopId,shopDTO);
        //8.地铁站关系信息
        shopStationRelJob.asyncShopStationRelJob(shopId);
        //9.商铺上下架记录表
        ShopShelf shopShelf = new ShopShelf();
        shopShelf.setShopId(shopId);
        shopShelf.setStatus(ShelfStatus.PUT.getValue());
        shopShelf.setRunSign(0);
        shopShelf.setCreater(shopDTO.getClerkId());
        shopShelfMapper.insert(shopShelf);
        //10.来自线索的商铺 更新线索状态为已转化
        if(shopDTO.getHintId()!=null){
            ClerkHintDTO hintDTO = new ClerkHintDTO();
            hintDTO.setId(shopDTO.getHintId());
            hintDTO.setStatus(ClerkHintStatusEnum.EXAMINE_SPOT_FINISHED.getValue());//线索状态-已转化
            ResultDO<Boolean> hintResult  = clerkHintService.updateStatus(hintDTO);
            if(!hintResult.isSuccess()){
                throw new AppException("发布商铺线索状态改成已转化异常！");
            }
        }
        return shopId;
    }


    /**
     *修改经营状况
     * @param shopDTO
     * @return
     * @throws AppException
     */
    @Transactional(rollbackFor = AppException.class)
    public int updateShopOperateState(IssueShopDTO shopDTO) throws AppException{
        Long shopId = shopDTO.getShopId();
        Shop shop = getShopById(shopId);
        if(shop!=null){
            shop.setShopName(shopDTO.getShopName());
            shop.setOperateStatus(shopDTO.getOperateStatus());
            shop.setIndustryId(shopDTO.getIndustryId());
            int count = shopMapper.updateByPrimaryKeySelective(shop);
            //特色(非必填)，可经营范围，图片 ;
            List<Long> feature = shopDTO.getFeature();//特色
            if(feature!=null && !feature.isEmpty()){
                shopFeatureTagRelMapper.deleteByShopId(shopId);
                shopFeatureTagRelMapper.insertList(feature,shopId);//保存商铺特色
            }else {
                shopFeatureTagRelMapper.deleteByShopId(shopId);
            }
            List<Long> operateRel = shopDTO.getOperateRel();//适合经营
            if(operateRel!=null && !operateRel.isEmpty()){
                shopOperateRelMapper.deleteByShopId(shopId);
                shopOperateRelMapper.insertList(operateRel,shopId);//保存适合经营
            }else {
                shopOperateRelMapper.deleteByShopId(shopId);
            }
            shopImgMapper.deleteByShopId(shopId);
            List<ShopImgDTO> shopImgList = shopDTO.getShopImgList();
            shopImgMapper.insertList(shopImgList,shopId);//保存商铺图片
            return count;
        }else {
            return -1;
        }
    }
    /**
     * 修改配套设施
     * @param shopDTO
     * @return
     * @throws AppException
     */
    @Transactional(rollbackFor = AppException.class)
    public void updateShopSupport(IssueShopDTO shopDTO) throws AppException{
        Long shopId = shopDTO.getShopId();
        List<Long> support = shopDTO.getSupport();//配套
        if(support!=null && !support.isEmpty()){
            shopSupportTagRelMapper.deleteByShopId(shopId);
            shopSupportTagRelMapper.insertList(support,shopId);// 保存商铺配套
        }else {
            shopSupportTagRelMapper.deleteByShopId(shopId);
        }
    }

    public ShopScanCountDTO queryMyShopScanCount(String phone){
        ShopScanCountDTO dto  = new ShopScanCountDTO();
        dto.setWeekCount(shopMapper.queryThisWeekScanCount(phone));

        List<String> list = shopMapper.queryThisWeekScanRate();
        if(!ValidateHelper.isEmpty(list) && list.contains(phone)){
            int index = list.indexOf(phone) + 1;
            int size  = list.size();
            float rate = (float)index/size;
            float subtraction = 1 - rate;
            int ra = Math.round(subtraction * 100);
            dto.setRate(ra + "%");
        }else{
            dto.setRate("0%");
        }

        return dto;
    }

    public Page<ShopCustomerPublishDTO> queryMyPublishShop(Page<ShopCustomerPublishDTO> page){

        shopMapper.queryMyPublishShop(page);

        return page;
    }

    @Transactional(rollbackFor = AppException.class)
    public Boolean operatorShopStatus(Shop shop, ShopStatusFlow statusFlow,ShopShelf shopShelf,ShopLog shopLog) throws AppException{
        int shopCount  = shopMapper.updateByPrimaryKeySelective(shop);
        int shopFlowCount  = shopStatusFlowMapper.insert(statusFlow);
        int shelfCount = shopShelfMapper.insert(shopShelf);
        int logCount = 0;
        if (null != shopLog){
            logCount = shopLogMapper.insert(shopLog);
        }
        if (null != shopLog){
            return (shopCount>0) && (shopFlowCount==1) && shelfCount==1 && logCount==1;
        }else {
            return (shopCount>0) && (shopFlowCount==1) && shelfCount==1;
        }
    }

    /**
     * 客户端统计数目
     * @Author zhoumin
     * @return
     */
    public ShopCountDTO countShopFromClient() {
        return shopMapper.countShopFromClient();
    }

    /**
     * 业务端商铺统计数目
     * @Author zhoumin
     * @param clerkId
     * @return
     */
    public ShopCountDTO countShopFromService(Long clerkId) {
        return shopMapper.countShopFromService(clerkId);
    }

    /**
     * 铺位照片查询
     * @Author zhoumin
     * @param shopId
     * @return
     */
    public List<ShopImgDTO> queryShopImg(Long shopId) {
        return shopImgMapper.queryShopImg(shopId);
    }

    /**
     * 查询街铺详情
     * @Author zhoumin
     * @param shopId
     * @return
     */
    public ShopDetailDTO queryShopDetail(Long shopId) {
        return shopMapper.queryShopDetail(shopId);
    }

    /**
     * 查询我收藏的商铺列表
     * @param page
     * @return
     */
    public Page<ShopCollectedCustomerDTO> shopCollectedList(Page page){
        shopMapper.shopCollectedList(page);
        page.setQuery(null);
        return page;
    }

    /**
     * 查询我浏览的商铺列表
     * @param page
     * @return
     */
    public Page<ShopBrowseCustomerDTO> shopBrowseList(Page page){
        shopMapper.shopBrowseList(page);
        page.setQuery(null);
        return page;
    }

    /**
     * 查找业务员信息
     * @param shopId
     * @return
     */
    public ClerkDTO queryClerkByShopId(Long shopId) {
        return shopMapper.queryClerkByShopId(shopId);
    }

    public ClerkAndShopInfoDTO queryClerkAndShopInfoByShopId(Long shopId){
        return shopMapper.queryClerkAndShopInfoByShopId(shopId);
    }
    /**
     * 查询我约看的商铺列表
     * @param page
     * @return
     */
    public Page<ShopVisitCustomerDTO> shopVisitedList(Page page){
        shopMapper.shopVisitedList(page);
        return page;
    }

    public Integer countShops(){
        return shopMapper.countShop();
    }

    /**
     * 查询已经被使用的二维码编号
     * @param shopNumber
     * @return
     */
    public Integer queryShopNumber(String shopNumber){
        return shopMapper.queryShopNumber(shopNumber);
    }

    /**
     * 根据二维码查询商铺
     * @param shopNumber
     * @return
     */
    public Integer queryShopByShopNum(String shopNumber){
        return shopMapper.queryShopByShopNum(shopNumber);
    }

    /**
     * 根据二维码编号查询对应的商铺id
     * @param shopCode
     * @return
     */
    public Long getShopId (String shopCode){
        return shopMapper.getShopId(shopCode);
    }

    /**
     * 更新商铺认领信息
     * @Author：zhoumin
     * @return
     */
    public String updateClaimInfo(Shop shop,ShopStatusFlow statusFlow){
        int count1 = shopMapper.updateByPrimaryKeySelective(shop);
        int count2 = shopStatusFlowMapper.insert(statusFlow);
        String claimTime = null;
        if (count1 >0 && count2 > 0) {
            Date claimTimeDate = shop.getClaimTime();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
            claimTime = sdf.format(claimTimeDate);
        }
        return claimTime;
    }


    /**
     * @description
     * @package com.dongtong.shop.manager
     * @author chenxs
     * @date 2017/8/11 0011 16:25
     * @param shopRecomendQuery
     * @return ShopRecomendListDTO
     */
    public ShopRecomendListDTO getRecomendShop(ShopRecomendQuery shopRecomendQuery) {

        return shopMapper.selectRecomendShopById(shopRecomendQuery);
    }
}
