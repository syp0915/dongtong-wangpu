package com.dongtong.customer.service;

import com.dongtong.customer.domain.Customer;
import com.dongtong.customer.domain.CustomerBrowseShop;
import com.dongtong.customer.domain.CustomerCollectedShop;
import com.dongtong.customer.dto.CustomerDTO;
import com.dongtong.customer.dto.CustomerInfoDTO;
import com.dongtong.customer.dto.ServiceListDTO;
import com.dongtong.customer.dto.req.AttentionReqDTO;
import com.dongtong.customer.dto.resp.BrowseStatisticDTO;
import com.dongtong.customer.dto.resp.CollectStatisticDTO;
import com.dongtong.customer.dto.resp.StatisticDTO;
import com.dongtong.customer.dto.resp.VisitedStatisticDTO;
import com.dongtong.customer.manager.CustomerBrowseManager;
import com.dongtong.customer.manager.CustomerCollectedManager;
import com.dongtong.customer.manager.CustomerManager;
import com.dongtong.customer.query.ServiceListListQuery;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-10 10:42
 **/
@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {

    @Autowired
    private CustomerManager customerManager;
    @Autowired
    private CustomerCollectedManager customerCollectedManager;
    @Autowired
    private CustomerBrowseManager customerBrowseManager;

    @Autowired
    private CustomerService customerService;

    @Override
    public ResultDO<CustomerDTO> customerInfo(Long customerId) {
        ResultDO<CustomerDTO> resultDO=new ResultDO<>();
        if(customerId==null){
            resultDO.setErrMsg("用户Id不能为空");
            return resultDO;
        }

        CustomerDTO customerDTO=customerManager.queryCustomerInfoByCustomerId(customerId);
        if(customerDTO==null){
            resultDO.setErrMsg("用户Id对应得用户不存在");
            return resultDO;
        }

        resultDO.setData(customerDTO);
        resultDO.setSuccess(true);
        return resultDO;
    }

    @Deprecated
    @Override
    public ResultDO<String> updateHeadPortrait(Long customerId,String headPortrait) {
        ResultDO<String> resultDO=new ResultDO<>();
        if(customerId==null){
            resultDO.setErrMsg("用户Id不能为空");
            return resultDO;
        }
        if(ValidateHelper.isEmpty(headPortrait)){
            resultDO.setErrMsg("用户头像不能为空");
            return resultDO;
        }

        Customer customer=customerManager.findCustomerInfoByCustomerId(customerId);
        if(customer==null){
            resultDO.setErrMsg("用户不存在");
            return resultDO;
        }

        customerManager.updateHeadPortrait(customerId,headPortrait);
        resultDO.setSuccess(true);
        return resultDO;
    }

    @Override
    public ResultDO<CustomerInfoDTO> queryCustomerInfo(Long customerId) {
        ResultDO<CustomerInfoDTO> resultDO=new ResultDO<CustomerInfoDTO>();

        if(customerId==null){
            resultDO.setErrMsg("用户Id不能为空");
            return resultDO;
        }

        CustomerInfoDTO customerInfoDTO =customerManager.queryInfoByCustomerId(customerId);
        if(customerInfoDTO ==null){
            resultDO.setErrMsg("用户不存在");
            return resultDO;
        }
        //是否新用户
        customerInfoDTO.setIsNew(customerService.isNew(customerId));

        //获取未完成服务个数
        ServiceListListQuery serviceListListQuery = new ServiceListListQuery();
        serviceListListQuery.setUserId(customerId);
        serviceListListQuery.setType(1L);
        ResultDO<Page<ServiceListDTO>> mySerivce = customerService.myServiceList(serviceListListQuery);
        Long serviceCount = 0L;
        if(!ValidateHelper.isEmpty(mySerivce) && mySerivce.isSuccess()
                && !ValidateHelper.isEmpty(mySerivce.getData())){
            serviceCount = mySerivce.getData().getTotalSize();
        }
        customerInfoDTO.setMyService(serviceCount);
        Logger.info(this.getClass(),"查询到用户信息："+customerInfoDTO);
        resultDO.setData(customerInfoDTO);
        resultDO.setSuccess(true);
        return resultDO;
    }

    @Override
    public ResultDO<StatisticDTO> myCollectStatistic(Long customerId) {
        ResultDO<StatisticDTO> resultDO = new ResultDO<>();
        StatisticDTO statisticDTO = new StatisticDTO();
        CollectStatisticDTO dto =  new CollectStatisticDTO();
        List<CollectStatisticDTO> list =  customerCollectedManager.myCollectStatistic();
        Iterator<CollectStatisticDTO> iterator = list.iterator();
        while (iterator.hasNext()){
            dto  = iterator.next();
            if(dto.getCustomerId().longValue()==customerId.longValue()){
                int rowNo = dto.getRowNo();
                Integer totalCustomer  = dto.getTotalCustomer();
                float rate = (float)rowNo/totalCustomer;
                float subtraction = 1 - rate;
                int ra = Math.round(subtraction * 100);
                dto.setRate(ra + "%");
                break;
            }
        }
        if(ValidateHelper.isEmpty(dto.getRate())){
            dto.setRate("0%");
            dto.setTotalCollected(0);
        }
        statisticDTO.setTotal(dto.getTotalCollected());
        statisticDTO.setRate(dto.getRate());
        resultDO.setData(statisticDTO);
        resultDO.setSuccess(true);
        return resultDO;
    }
    /**
     * 取消收藏
     * @param shopId
     * @param customerId
     * @return
     */
    public ResultDO<Boolean> cancelShopCollected(Long shopId,Long customerId){
        ResultDO<Boolean> resultDO = new ResultDO<>();
       /* CustomerCollectedShop record =  customerCollectedManager.selectById(collectedId);
        if(record==null){
            resultDO.setErrMsg("收藏记录不存在！");
            return resultDO;
        }else if (record.getCustomerId()!=null && record.getCustomerId().longValue() != customerId.longValue()){
            resultDO.setErrMsg("只能删除自己收藏的商铺！");
            return resultDO;
        }*/
        customerCollectedManager.deleteByShopId(shopId,customerId);
        resultDO.setData(true);
        resultDO.setSuccess(true);
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.customer.service
     * @author chenxs
     * @date 2017/8/9 0009 15:33
     * @param dto
     * @return ResultDO<String>
     *
     * v1.2
     *    --修改入参
     *    --关注板块和关注行业可以多选
     */
    @Override
    public ResultDO<String> updateFollow(AttentionReqDTO dto) {
        ResultDO<String> resultDO=new ResultDO<String>();

        if(ValidateHelper.isEmpty(dto.getCustomerId())){
            resultDO.setErrMsg("用户Id不能为空");
            return resultDO;
        }
        //用户状态正常的才可以修改
        Customer customer=customerManager.findCustomerInfoByCustomerId(dto.getCustomerId());
        if(customer==null){
            resultDO.setErrMsg("用户信息不存在,不能设置关注信息");
            return resultDO;
        }

        //更新关注信息
        customerManager.updateFollow(dto);
        resultDO.setSuccess(true);
        return resultDO;
    }

    @Override
    public ResultDO<StatisticDTO> myBrowseStatistic(Long customerId) {
        ResultDO<StatisticDTO> resultDO = new ResultDO<>();
        StatisticDTO statisticDTO = new StatisticDTO();
        BrowseStatisticDTO dto =  new BrowseStatisticDTO();
        List<BrowseStatisticDTO> list =  customerBrowseManager.myBrowseStatistic();
        Iterator<BrowseStatisticDTO> iterator = list.iterator();
        while (iterator.hasNext()){
            dto  = iterator.next();
            if(dto.getCustomerId()!=null && dto.getCustomerId().longValue()==customerId.longValue()){
                int rowNo = dto.getRowNo();
                Integer totalCustomer  = dto.getTotalCustomer();
                float rate = (float)rowNo/totalCustomer;
                float subtraction = 1 - rate;
                int ra = Math.round(subtraction * 100);
                dto.setRate(ra + "%");
                break;
            }
        }
        if(ValidateHelper.isEmpty(dto.getRate())){
            dto.setRate("0%");
            dto.setTotalBrowse(0);
        }
        statisticDTO.setTotal(dto.getTotalBrowse());
        statisticDTO.setRate(dto.getRate());
        resultDO.setData(statisticDTO);
        resultDO.setSuccess(true);
        return resultDO;
    }

    @Override
    public ResultDO<Boolean> deleteShopBrowse(Long shopId, Long customerId) {
        ResultDO<Boolean> resultDO = new ResultDO<>();
       /* CustomerBrowseShop record =  customerBrowseManager.selectById(browseId);
        if(record==null){
            resultDO.setErrMsg("浏览记录不存在！");
            return resultDO;
        }else if (record.getCustomerId()!=null && record.getCustomerId().longValue() != customerId.longValue()){
            resultDO.setErrMsg("只能删除自己浏览的商铺！");
            return resultDO;
        }*/
        customerBrowseManager.deleteByShopId(shopId,customerId);
        resultDO.setData(true);
        resultDO.setSuccess(true);
        return resultDO;
    }

    @Override
    public ResultDO<StatisticDTO> myVisitStatistic(Long customerId) {
        ResultDO<StatisticDTO> resultDO = new ResultDO<>();
        StatisticDTO statisticDTO = new StatisticDTO();
        VisitedStatisticDTO dto =  new VisitedStatisticDTO();
        List<VisitedStatisticDTO> list =  customerManager.myVisitStatistic();
        Iterator<VisitedStatisticDTO> iterator = list.iterator();
        while (iterator.hasNext()){
            dto  = iterator.next();
            if(dto.getCustomerId().longValue()==customerId.longValue()){
                int rowNo = dto.getRowNo();
                Integer totalCustomer  = dto.getTotalCustomer();
                float rate = (float)rowNo/totalCustomer;
                float subtraction = 1 - rate;
                int ra = Math.round(subtraction * 100);
                dto.setRate(ra + "%");
                break;
            }
        }
        if(ValidateHelper.isEmpty(dto.getRate())){
            dto.setRate("0%");
            dto.setTotalCollected(0);
        }
        statisticDTO.setTotal(dto.getTotalCollected());
        statisticDTO.setRate(dto.getRate());
        resultDO.setData(statisticDTO);
        resultDO.setSuccess(true);
        return resultDO;
    }


    public String checkParams(CustomerInfoDTO dto){
        if(dto.getCustomerId()==null){
            return  "用户Id不能为空";
        }
//        if(ValidateHelper.isEmpty(dto.getCityId())&&ValidateHelper.isEmpty(dto.getCityName())){
//            return "关注城市不能为空";
//        }
//        if(ValidateHelper.isEmpty(dto.getDistrictId())&&ValidateHelper.isEmpty(dto.getDistrictName())){
//            return "关注区域不能为空";
//        }
//        if(ValidateHelper.isEmpty(dto.getBlockId())&&ValidateHelper.isEmpty(dto.getBlockName())){
//            return "关注板块不能为空";
//        }
//        if(ValidateHelper.isEmpty(dto.getIndustryId())&&ValidateHelper.isEmpty(dto.getIndustryName())){
//            return "关注行业不能为空";
//        }
//        if(dto.getList()==null||dto.getList().size()==0){
//            return "店铺面积不能为空不能为空";
//        }
//
//        if(dto.getList()!=null||dto.getList().size()>0){
//            for (Integer area:dto.getList()){
//                if(area==null||area<1||area>7){
//                    return "关注面积参数错误";
//                }
//            }
//        }
        return null;


    }


    /**
     * @description
     * @package com.dongtong.customer.service
     * @author chenxs
     * @date 2017/8/9 0009 15:50
     * @param dto
     * @return ResultDO
     *
     * v1.2新增
     *     --修改用户用户个人信息
     */
    @Override
    public ResultDO<String> updateCustomerInfo(CustomerInfoDTO dto) {
        ResultDO<String> resultDO = new ResultDO<>();

        if(ValidateHelper.isEmpty(dto.getCustomerId())){
            resultDO.setErrMsg("用户ID不能为空");
            resultDO.setData("用户ID不能为空");
            return resultDO;
        }
        resultDO = customerManager.updateCustomerInfo(dto);
        return resultDO;
    }

}
