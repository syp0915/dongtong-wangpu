package com.dongtong.basic.manager;

import com.dongtong.basic.dao.BaseNotificationMapper;
import com.dongtong.basic.domain.BaseNotification;
import com.dongtong.basic.dto.req.WorkNoticeReqDTO;
import com.dongtong.basic.enums.BussinessEndNoticeType;
import com.dongtong.basic.enums.NoticeType;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.enums.WorkServiceType;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.date.DateFormatUtils;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.manager
 * @Description :工作通知管理
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-10 10:15
 * version V1.0.0
 **/
@Service
public class WorkNoticeManager {

    @Autowired
    private BaseNotificationMapper baseNotificationMapper;

    /**
     * 工作通知添加
     * @param workNoticeDTO
     * @return
     */
    public ResultDO<Long> addWorkNotice(WorkNoticeReqDTO workNoticeDTO){
        ResultDO resultDO=new ResultDO();
        HashMap<String,String> hashMap=getInfoByNoticeType(workNoticeDTO);
        BaseNotification baseNotification=new BaseNotification();
        baseNotification.setBizId(workNoticeDTO.getBussinessId());     //所对应业务Id
        baseNotification.setNotifyType(NoticeType.WORK.getValue());   //服务通知类型:工作
        baseNotification.setReceiverId(workNoticeDTO.getReceiveId());  //接收人Id
        baseNotification.setReceiverType(ReceiveType.CLERK.getValue()); //服务为业务端通知即接收类型为1：业务员
        baseNotification.setServiceType(workNoticeDTO.getServiceType()); //0-实堪 1-约看 2-签约 3-周榜单 4-月榜单
        baseNotification.setStatus(1); //消息查看状态 默认未查看
        if(hashMap.size()>0){
            baseNotification.setDigest(hashMap.get("digest"));  //消息通知简要
            baseNotification.setContent(hashMap.get("content"));//消息通知内容
        }
        baseNotification.setCreateTime(new Date());  //通知时间
        int result=baseNotificationMapper.insert(baseNotification);
        if(result>0){
            resultDO.setSuccess(true);
            resultDO.setData(baseNotification.getId());
        }else{
            resultDO.setErrMsg("添加记录失败");
        }
        return  resultDO;
    }



    /**
     * 根据通知类型获取简要和内容
     * @param workNoticeDTO
     * @return
     */
    public HashMap<String,String> getInfoByNoticeType(WorkNoticeReqDTO workNoticeDTO){
        HashMap<String,String> hashMap=new HashMap<String,String>();
        Integer workNoticeType=workNoticeDTO.getWorkNoticeType();
        Integer serviceType=workNoticeDTO.getServiceType();
        String shopAddress=workNoticeDTO.getShopAddress();
        String planTime= workNoticeDTO.getPlanTime();
        String digest="";          //简要信息
        String content="";         //消息内容
        StringBuilder json = new StringBuilder();
        json.append("{");
        //通知状态为：接到商户需求
        if((!ValidateHelper.isEmpty(workNoticeType)) && workNoticeType==BussinessEndNoticeType.RECEIVED_DEMAND.getValue()){
            digest="收到了新的服务需求";
            StringBuffer sbf=new StringBuffer();
            sbf.append("有商户向你发起了");//$实堪/约看/签约$ （徐虹北路11号101室）的服务需求。"
            HashMap<Integer,String> map=getMap();
            sbf.append(map.get(serviceType));
            json.append("\"serviceType\":" + "\"" + serviceType + "\",");
            sbf.append("("+shopAddress+")的服务需求。");
            content=sbf.toString();
            json.append("\"content\":" + "\"" + content + "\"");
        }
        //通知状态为：任务到时提醒
        if((!ValidateHelper.isEmpty(workNoticeType)) && workNoticeType==BussinessEndNoticeType.TASK_REMIND.getValue()){
            StringBuffer sbf=new StringBuffer();  //内容
            StringBuffer dt=new StringBuffer();  //简要
            HashMap<Integer,String> map=getMap();
            sbf.append(map.get(serviceType));
            dt.append(map.get(serviceType));
            json.append("\"serviceType\":" + "\"" + serviceType + "\",");
            dt.append("计划任务提醒");
            sbf.append(shopAddress+",计划时间"+ DateFormatUtils.formatDate(planTime,"yyyy-MM-dd HH:mm")+"分");
            digest=dt.toString();
            content=sbf.toString();
            json.append("\"content\":" + "\"" + content + "\"");
        }
        //通知状态为：周榜排名提醒
        if(serviceType==WorkServiceType.WEEK.getValue()){
            digest="周榜最新排名";
            content="截至目前的各榜擂主，线索榜 "+workNoticeDTO.getHintName()
                    +"、核准榜 "+workNoticeDTO.getCloseStoreName()+"、签约榜 "+workNoticeDTO.getSignName()+"、约看榜 "+workNoticeDTO.getLookName()+"、注册榜 "+workNoticeDTO.getRegisterName()+"。";
            json.append("\"serviceType\":" + "\"" + serviceType + "\",");
            json.append("\"content\":" + "\"" + content + "\"");

        }
        //通知状态为：月榜排名提醒
        if(serviceType==WorkServiceType.MONTH.getValue()){
            digest="月榜最新排名";
            content="截至目前的各榜擂主，线索榜"+workNoticeDTO.getHintName()+"、核准榜 "+workNoticeDTO.getCloseStoreName()
                    +"、签约榜 "+workNoticeDTO.getSignName()+"、约看榜 "+workNoticeDTO.getLookName()+"、注册榜 "+workNoticeDTO.getRegisterName()+"。";
            json.append("\"serviceType\":" + "\"" + serviceType + "\",");
            json.append("\"content\":" + "\"" + content + "\"");
        }
        if(serviceType == WorkServiceType.SCRAP_CLUE.getValue()){//线索废弃
            digest="你的线索被废弃";
            content="你发布的线索被废弃";
            json.append("\"serviceType\":" + "\"" + serviceType + "\",");
            json.append("\"content\":" + "\"" + content + "\"");
        }
        if(serviceType == WorkServiceType.TIMEOUT_CLUE.getValue()){//线索废弃
            digest="线索跟进提醒";
            content="你认领的线索即将超时，请尽快处理";
            json.append("\"serviceType\":" + "\"" + serviceType + "\",");
            json.append("\"content\":" + "\"" + content + "\"");
        }

        json.append("}");
        hashMap.put("digest",digest);
        hashMap.put("content",json.toString());
        return hashMap;
    }

    public HashMap<Integer,String> getMap(){
        HashMap<Integer,String> hash=new HashMap();
        hash.put(WorkServiceType.FIELD_TRIP.getValue(),"实堪");
        hash.put(WorkServiceType.ORDER_SEE.getValue(),"约看");
        hash.put(WorkServiceType.SIGN_CONTRACT.getValue(),"签约");
        return hash;
    }


}
