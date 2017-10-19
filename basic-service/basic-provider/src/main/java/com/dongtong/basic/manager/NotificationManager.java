package com.dongtong.basic.manager;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.dongtong.basic.dao.BaseConfigureMapper;
import com.dongtong.basic.dao.BaseNotificationMapper;
import com.dongtong.basic.domain.BaseConfigure;
import com.dongtong.basic.domain.BaseNotification;
import com.dongtong.basic.dto.req.BaseNoticeReqDTO;
import com.dongtong.basic.dto.resp.*;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.query.NoticeQuery;
import com.dongtong.basic.util.BasicProperties;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.manager
 * @Description :消息通知管理
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-09 13:10
 * version V1.0.0
 **/
@Service
public class NotificationManager extends BaseManage{

    @Autowired
    private BaseNotificationMapper baseNotificationMapper;

    @Autowired
    private BaseConfigureMapper baseConfigureMapper;

    public ResultDO<Boolean> checkNotification(BaseNoticeReqDTO baseNoticeDTO){
        ResultDO<Boolean> resultDO = new ResultDO<Boolean>();
        int count = baseNotificationMapper.selectUnReadByReceiver(baseNoticeDTO);
        if(count>0){
            resultDO.setData(true);
        }else{
            resultDO.setData(false);
        }
        resultDO.setSuccess(true);
        return resultDO;
    }

    /**
     * 根据类型更新消息状态
     * @param record
     * @return
     */
    public ResultDO<Integer> updateNoticeStatus(BaseNotification record){
        ResultDO<Integer> resultDO = new ResultDO<Integer>();
        int result=baseNotificationMapper.updateStatusByType(record);
        resultDO.setSuccess(true);
        resultDO.setData(result);
        return  resultDO;
    }

    /**
     * 根据Id更新消息状态
     * @param msgId
     * @return
     */
    public ResultDO<Integer> updateById(Long msgId){
        ResultDO<Integer> resultDO = new ResultDO<Integer>();
        int result=baseNotificationMapper.updateById(msgId);
        resultDO.setSuccess(true);
        resultDO.setData(result);
        return  resultDO;
    }



    /**
     * 通知列表
     * @param baseNoticeReqDTO
     * @return
     */
    public List<NoticeRespDTO> getNoticeList(BaseNoticeReqDTO baseNoticeReqDTO){
        List<NoticeRespDTO> baseNotificationList=baseNotificationMapper.selectNoticeList(baseNoticeReqDTO);
        return  baseNotificationList;
    }

    /**
     * 根据通知类型返回不同列表
     * @param noticeQuery
     * @return
     */
    public Page<NoticeQuery> selectNoticeListByType(NoticeQuery noticeQuery) {
        Page<NoticeQuery> page = new Page<>(noticeQuery.getPageNumber(),noticeQuery.getPageSize());
        page.setQuery(noticeQuery);
        baseNotificationMapper.selectNoticeListByType(page);
        return  page;
    }

    /**
     * 服务通知返回列表
     * @param noticeQueryList
     * @return
     */
    public List<ServiceNoticeRespDTO> getServiceNoticeList(List<NoticeQuery> noticeQueryList){
        List<ServiceNoticeRespDTO> serviceNoticeRespDTOList=new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String str="yyyy-MM-dd HH:mm";
        for(int i=0;i<noticeQueryList.size();i++){
            ServiceNoticeRespDTO serviceNoticeRespDTO=new ServiceNoticeRespDTO();
            BaseNotification baseNotification=noticeQueryList.get(i);
            serviceNoticeRespDTO.setBussinessId(baseNotification.getBizId());
            serviceNoticeRespDTO.setDigest(baseNotification.getDigest());
            serviceNoticeRespDTO.setServiceType(baseNotification.getServiceType());
            Date createTime=baseNotification.getCreateTime();
            if(createTime==null){
                serviceNoticeRespDTO.setNotifyTime("");
            }else{
                serviceNoticeRespDTO.setNotifyTime(sdf.format(createTime));
            }
            serviceNoticeRespDTO.setReceiveId(baseNotification.getReceiverId());
            serviceNoticeRespDTO.setStatus(baseNotification.getStatus());
            String content=baseNotification.getContent();
            try {
                JSONObject object = JSONObject.parseObject(content);
                serviceNoticeRespDTO.setCurrentSchedule(parseTime(checkStringMapObject(object.get("currentTime")),str));
                serviceNoticeRespDTO.setServiceStatus(Integer.parseInt(checkIntMapObject(object.get("serviceStatus"))));
                serviceNoticeRespDTO.setOldSchedule(parseTime(checkStringMapObject(object.get("oldTime")),str));
                String reason = checkStringMapObject(object.get("reason"));
                if(reason.length()>0){
                    String reasonLastStr = reason.substring(reason.length()-1,reason.length());
                    if(",".equals(reasonLastStr)){
                        reason = reason.substring(0,reason.length()-1);
                    }
                }
                serviceNoticeRespDTO.setReason(reason);
                serviceNoticeRespDTO.setShopAddress(checkStringMapObject(object.get("shopAddress")));
                serviceNoticeRespDTO.setServiceName(checkStringMapObject(object.get("serviceName")));
                serviceNoticeRespDTO.setServiceTel(checkStringMapObject(object.get("serviceTel")));
                serviceNoticeRespDTO.setScheduleCount(Integer.parseInt(checkIntMapObject(object.get("scheduleCount"))));
                //serviceNoticeRespDTO.setCustomerServiceTel(getTelCodeValue());
            }catch (JSONException e){
                e.printStackTrace();
            }
            serviceNoticeRespDTOList.add(serviceNoticeRespDTO);
        }
        return serviceNoticeRespDTOList;
    }

    /**
     * 生意圈通知返回列表
     * @param noticeQueryList
     * @return
     */
    public List<BussinessNoticeRespDTO> getBussinessNoticeList(List<NoticeQuery> noticeQueryList){
        List<BussinessNoticeRespDTO> bussinessNoticeRespDTOList=new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String str="yyyy-MM-dd HH:mm:ss";
        for(int i=0;i<noticeQueryList.size();i++){
            BussinessNoticeRespDTO bussinessNoticeRespDTO=new BussinessNoticeRespDTO();
            BaseNotification baseNotification=noticeQueryList.get(i);
            bussinessNoticeRespDTO.setBussinessId(baseNotification.getBizId());
            bussinessNoticeRespDTO.setDigest(baseNotification.getDigest());
            Date createTime=baseNotification.getCreateTime();
            if(createTime==null){
                bussinessNoticeRespDTO.setNotifyTime("");
            }else{
                bussinessNoticeRespDTO.setNotifyTime(sdf.format(createTime));
            }
            bussinessNoticeRespDTO.setReceiveId(baseNotification.getReceiverId());
            bussinessNoticeRespDTO.setStatus(baseNotification.getStatus());
            bussinessNoticeRespDTO.setBussinessType(baseNotification.getServiceType());
           // bussinessNoticeRespDTO.setCustomerServiceTel(getTelCodeValue());
            String content=baseNotification.getContent();
            if(baseNotification.getReceiverType()== ReceiveType.CUSTOMER.getValue()){
                try{
                    JSONObject object=JSONObject.parseObject(content);
                    bussinessNoticeRespDTO.setPostName(checkStringMapObject(object.get("postName")));
                    bussinessNoticeRespDTO.setCommentOrOprationTime(parseBusTime(checkStringMapObject(object.get("commentOrOprationTime")),str));
                    bussinessNoticeRespDTO.setContentOrReason(checkStringMapObject(object.get("contentOrReason")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(baseNotification.getReceiverType()== ReceiveType.CLERK.getValue()){
                bussinessNoticeRespDTO.setContent(content);
            }
            bussinessNoticeRespDTOList.add(bussinessNoticeRespDTO);
        }
        return  bussinessNoticeRespDTOList;

    }

    /**
     * 商铺通知返回列表
     * @param noticeQueryList
     * @return
     */
    public List<BaseNoticeRespDTO> getShopNoticeList(List<NoticeQuery> noticeQueryList){
        List<BaseNoticeRespDTO> baseNoticeRespDTOList=new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<noticeQueryList.size();i++){
            BaseNoticeRespDTO baseNoticeRespDTO=new BaseNoticeRespDTO();
            BaseNotification baseNotification=noticeQueryList.get(i);
            baseNoticeRespDTO.setBussinessId(baseNotification.getBizId());
            baseNoticeRespDTO.setDigest(baseNotification.getDigest());
            Date createTime=baseNotification.getCreateTime();
            if(createTime==null){
                baseNoticeRespDTO.setNotifyTime("");
            }else{
                baseNoticeRespDTO.setNotifyTime(sdf.format(createTime));
            }
            baseNoticeRespDTO.setReceiveId(baseNotification.getReceiverId());
            baseNoticeRespDTO.setStatus(baseNotification.getStatus());
            String content=baseNotification.getContent();
            baseNoticeRespDTO.setContent(content);
            baseNoticeRespDTOList.add(baseNoticeRespDTO);
        }
        return baseNoticeRespDTOList;

    }

    /**
     * 工作通知列表
     * @param noticeQueryList
     * @return
     */
    public List<WorkNoticeRespDTO> getWorkNoticeList(List<NoticeQuery> noticeQueryList){
        List<WorkNoticeRespDTO> workNoticeRespDTOList=new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<noticeQueryList.size();i++) {
            WorkNoticeRespDTO workNoticeRespDTO=new WorkNoticeRespDTO();
            BaseNotification baseNotification = noticeQueryList.get(i);
            workNoticeRespDTO.setBussinessId(baseNotification.getBizId());
            workNoticeRespDTO.setDigest(baseNotification.getDigest());
            workNoticeRespDTO.setServiceType(baseNotification.getServiceType());
            Date createTime = baseNotification.getCreateTime();
            if(createTime==null){
                workNoticeRespDTO.setNotifyTime("");
            }else{
                workNoticeRespDTO.setNotifyTime(sdf.format(createTime));
            }
            workNoticeRespDTO.setReceiveId(baseNotification.getReceiverId());
            workNoticeRespDTO.setStatus(baseNotification.getStatus());
            String content = baseNotification.getContent();
            try {
                JSONObject object = JSONObject.parseObject(content);
                workNoticeRespDTO.setContent(checkIntMapObject(object.get("content")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            workNoticeRespDTOList.add(workNoticeRespDTO);
        }
        return  workNoticeRespDTOList;
    }

    private String parseTime(String dateStr,String format) {
        String formattedTime="";
        if(!ValidateHelper.isEmptyString(dateStr)){
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = simpleDateFormat.parse(dateStr);
                simpleDateFormat = new SimpleDateFormat(format);
                formattedTime = simpleDateFormat.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return formattedTime;
    }

    private String parseBusTime(String dateStr,String format) {
        String formattedTime="";
        if(!ValidateHelper.isEmptyString(dateStr)){
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = simpleDateFormat.parse(dateStr);
                simpleDateFormat = new SimpleDateFormat(format);
                formattedTime = simpleDateFormat.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return formattedTime;
    }


    public ResultDO<Integer> selectBusNoticeCount(BaseNoticeReqDTO baseNoticeDTO){
        ResultDO<Integer> resultDO = new ResultDO<Integer>();
        int count = baseNotificationMapper.selectBusNoticeCount(baseNoticeDTO);
        resultDO.setData(count);
        resultDO.setSuccess(true);
        return resultDO;
    }


    /**
     * 获取客服电话
     * @return
     */
    public String getTelCodeValue(){
        BaseConfigure baseConfigure=baseConfigureMapper.queryCustomerServiceTel(0L);
        return baseConfigure.getValue();
    }
    
    /**
     * @description 
     * @package com.dongtong.basic.manager
     * @author chenxs
     * @date 2017/8/22 0022 16:10
     * @param 
     * @return 
     */
    public ResultDO<Integer> updateByBusId(BaseNotification baseNotification){
        ResultDO<Integer> resultDO = new ResultDO<Integer>();
        int count = baseNotificationMapper.updateByBusId(baseNotification);
        resultDO.setData(count);
        resultDO.setSuccess(true);
        return resultDO;
    }
}
