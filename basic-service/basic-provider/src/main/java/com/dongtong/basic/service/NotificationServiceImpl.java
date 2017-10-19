package com.dongtong.basic.service;

import com.dongtong.basic.constant.ErrorConstant;
import com.dongtong.basic.domain.BaseNotification;
import com.dongtong.basic.dto.req.*;
import com.dongtong.basic.dto.resp.NoticeRespDTO;
import com.dongtong.basic.dto.resp.NotifyListRespDTO;
import com.dongtong.basic.dto.resp.NotifyTypeListRespDTO;
import com.dongtong.basic.enums.*;
import com.dongtong.basic.manager.*;
import com.dongtong.basic.query.NoticeQuery;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.service
 * @Description :消息通知
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-09 14:50
 * version V1.0.0
 **/
@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationManager notificationManager;

    @Autowired
    private ServiceNoticeManager serviceNoticeManager;

    @Autowired
    private BussinessNoticeManager bussinessNoticeManager;

    @Autowired
    private ShopNoticeManager shopNoticeManager;

    @Autowired
    private WorkNoticeManager workNoticeManager;

    /**
     * 是否有未读消息
     * @param userId
     * @param receiveType
     * @return
     */
    @Override
    public ResultDO<Boolean> checkNotification(String userId,Integer receiveType) {
        ResultDO<Boolean> resultDO = new ResultDO<Boolean>();
        if(ValidateHelper.isEmpty(userId) || ValidateHelper.isEmpty(receiveType)){
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            resultDO.setSuccess(false);
            return resultDO;
        }
        BaseNoticeReqDTO baseNoticeDTO = new BaseNoticeReqDTO();
        baseNoticeDTO.setReceiveId(Long.parseLong(userId));
        baseNoticeDTO.setReceiveType(receiveType);
        resultDO = notificationManager.checkNotification(baseNoticeDTO);
        return resultDO;
    }

    /**
     * 根据消息Id更新消息状态
     * @param msgId
     * @return
     */
    @Override
    public ResultDO<Boolean> updateById(Long msgId){
        ResultDO<Boolean> resultDO = new ResultDO<Boolean>();
        if(msgId==null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        ResultDO<Integer> resultDO1=notificationManager.updateById(msgId);
        int result=resultDO1.getData();
        if(result>0){
            resultDO.setSuccess(true);
            resultDO.setData(true);
        }
        else{
            resultDO.setData(false);
            resultDO.setErrMsg("消息更新失败");
            Logger.error(NotificationServiceImpl.class,"消息更新失败");
        }
        return  resultDO;
    }

    /**
     * 根据消息类型，接受类型更新消息状态
     * @param baseNotification
     * @return
     */
    @Override
    public ResultDO<Integer> updateNoticeStatus(BaseNotification baseNotification){
        ResultDO<Integer> resultDO = new ResultDO<Integer>();
        if(baseNotification==null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        resultDO=notificationManager.updateNoticeStatus(baseNotification);
        return  resultDO;
    }


    /**
     * 获取通知列表
     * @param baseNoticeDTO
     * @return
     */
    @Override
    public ResultDO<NotifyTypeListRespDTO> getNoticeList(BaseNoticeReqDTO baseNoticeDTO) {
        ResultDO<NotifyTypeListRespDTO> resultDO=new ResultDO<>();
        Long receiveId=baseNoticeDTO.getReceiveId();
        Integer receiveType=baseNoticeDTO.getReceiveType();
        if(receiveId==null){
            resultDO.setSuccess(false);
            resultDO.setErrMsg("接收人Id不能为空");
            return resultDO;
        }
        if(receiveType==null){
            resultDO.setSuccess(false);
            resultDO.setErrMsg("\"接收人类型不能为空\"");
            return resultDO;
        }
        NotifyTypeListRespDTO notifyListDTO=new NotifyTypeListRespDTO();
        List<NoticeRespDTO> noticeList=notificationManager.getNoticeList(baseNoticeDTO);
        notifyListDTO.setBaseNoticeDTOList(noticeList);
        resultDO.setSuccess(true);
        resultDO.setData(notifyListDTO);
        return resultDO;
    }
    /**
     * 根据通知类型查询相应通知列表
     * @param query
     * @return
     */
    @Override
    public  ResultDO<NotifyListRespDTO> selectNoticeListByType(NoticeQuery query) {
        NotifyListRespDTO notifyListRespDTO=new NotifyListRespDTO();
        ResultDO<NotifyListRespDTO> resultDO=new ResultDO<>();
        if (query==null){
            resultDO.setSuccess(false);
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }
        Integer noticeType=query.getNotifyType();
        if(noticeType==null){
            resultDO.setSuccess(false);
            resultDO.setErrMsg("通知类型不能为空");
            return resultDO;
        }
        Long receiveId=query.getReceiverId();
        if(receiveId==null){
            resultDO.setSuccess(false);
            resultDO.setErrMsg("接收人Id不能为空");
            return resultDO;
        }
        Integer receiverType=query.getReceiverType();
        if(receiverType==null){
            resultDO.setSuccess(false);
            resultDO.setErrMsg("接收人类型不能为空");
            return resultDO;
        }
        Page<NoticeQuery> noticeQueryPage=notificationManager.selectNoticeListByType(query);
        List<NoticeQuery> queryList=noticeQueryPage.getData();
        //查看分类列表时更新所有通知状态为已读
        try {
            BaseNotification record = new BaseNotification();
            record.setReceiverId(query.getReceiverId());
            record.setReceiverType(query.getReceiverType());
            record.setNotifyType(query.getNotifyType());
            ResultDO<Integer> result = notificationManager.updateNoticeStatus(record);
            if (result.getData() < 0) {
                Logger.error(NotificationServiceImpl.class, "消息状态更新失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            Logger.error(NotificationServiceImpl.class, "消息状态更新失败");
        }
        if (queryList.size()>0){
            if(noticeType== NoticeType.SERVICE.getValue()){  //服务通知
                notifyListRespDTO.setServiceNoticeRespDTOList(notificationManager.getServiceNoticeList(queryList));
            }
            if(query.getReceiverType().intValue() != ReceiveType.CLERK.getValue()){
                if(noticeType== NoticeType.BUSSINESS.getValue()){ //生意圈通知
                    notifyListRespDTO.setBussinessNoticeRespDTOList(notificationManager.getBussinessNoticeList(queryList));
                }
            }
            if(noticeType== NoticeType.SHOP.getValue()){  //商铺通知
                notifyListRespDTO.setShopNoticeRespDTOList(notificationManager.getShopNoticeList(queryList));
            }
            if(noticeType== NoticeType.WORK.getValue()){  //工作通知
                notifyListRespDTO.setWorkNoticeRespDTOList(notificationManager.getWorkNoticeList(queryList));
            }
        }
        notifyListRespDTO.setPageNumber(noticeQueryPage.getPageNumber());
        notifyListRespDTO.setPageSize(noticeQueryPage.getPageSize());
        notifyListRespDTO.setTotalCount(noticeQueryPage.getTotalSize());
        notifyListRespDTO.setTotalPage(noticeQueryPage.getTotalPage());
        resultDO.setSuccess(true);
        resultDO.setData(notifyListRespDTO);
        return resultDO;
    }


    /**
     * 服务通知类型添加
     * @param serviceNoticeDTO
     * @return
     */
    @Override
    public ResultDO<Long> addServiceNotice(ServiceNoticeReqDTO serviceNoticeDTO) {
        ResultDO resultDO=new ResultDO();
        String msg=checkServiceParam(serviceNoticeDTO);
        if(!ValidateHelper.isEmptyString(msg)){
            resultDO.setSuccess(false);
            resultDO.setErrMsg(msg);
            return  resultDO;
        }
        //serviceType : 0-预约看铺 1-旺铺寻租 2-签约租铺 3-日程提醒
        Integer serviceStatus=serviceNoticeDTO.getServiceStatus();
        //除却日程提醒外，服务状态、小二姓名与电话都不能为空
        if(serviceStatus!= ServiceStatus.SCHEDULE_REMIND.getValue()){
            String errMsg= checkParamByType(serviceNoticeDTO);
            if(!ValidateHelper.isEmptyString(errMsg)){
                resultDO.setSuccess(false);
                resultDO.setErrMsg(errMsg);
                return  resultDO;
            }
        }
        String checkMsg=checkParamByStatus(serviceNoticeDTO);
        if(!ValidateHelper.isEmptyString(checkMsg)){
            resultDO.setSuccess(false);
            resultDO.setErrMsg(checkMsg);
            return  resultDO;
        }
        resultDO=serviceNoticeManager.addServiceNotice(serviceNoticeDTO);
        return resultDO;
    }

    /**
     * 生意圈通知添加
     * @param bussinessNoticeDTO
     * @return
     */
    @Override
    public ResultDO<Long> addBussinessNotice(BussinessNoticeReqDTO bussinessNoticeDTO) {
        ResultDO resultDO=new ResultDO();
        String msg=checkMustParam(bussinessNoticeDTO);
        if(!ValidateHelper.isEmptyString(msg)){
            resultDO.setSuccess(false);
            resultDO.setErrMsg(msg);
            Logger.info(BussinessNoticeManager.class,msg);
            return  resultDO;
        }
        String errMsg= checkParamByReceiveType(bussinessNoticeDTO);
        if(!ValidateHelper.isEmptyString(errMsg)){
            resultDO.setSuccess(false);
            resultDO.setErrMsg(errMsg);
            Logger.info(BussinessNoticeManager.class,errMsg);
            return  resultDO;
        }
        resultDO=bussinessNoticeManager.addBussinessNotice(bussinessNoticeDTO);
        return resultDO;
    }

    /**
     * 商铺通知添加
     * @param shopNoticeDTO
     * @return
     */
    @Override
    public ResultDO<Long> addShopNotice(ShopNoticeReqDTO shopNoticeDTO) {
        ResultDO resultDO=new ResultDO();
        String result=checkShopParam(shopNoticeDTO);
        if(!ValidateHelper.isEmptyString(result)){
            resultDO.setErrMsg(result);
            return  resultDO;
        }
        resultDO=shopNoticeManager.addShopNotice(shopNoticeDTO);
        return resultDO;
    }

    /**
     * 工作通知添加
     * @param workNoticeDTO
     * @return
     */
    @Override
    public ResultDO<Long> addWorkNotice(WorkNoticeReqDTO workNoticeDTO) {
        ResultDO resultDO=new ResultDO();
        String checkResult=checkWorkParam(workNoticeDTO);
        if(!ValidateHelper.isEmptyString(checkResult)){
            resultDO.setSuccess(false);
            resultDO.setErrMsg(checkResult);
            return  resultDO;
        }
        Integer shopNoticeType=workNoticeDTO.getWorkNoticeType();
        if(shopNoticeType!=null){
            String result=checkWorkParamByType(workNoticeDTO);
            if(!ValidateHelper.isEmptyString(result)){
                resultDO.setSuccess(false);
                resultDO.setErrMsg(result);
                return  resultDO;
            }
        }
        return workNoticeManager.addWorkNotice(workNoticeDTO);
    }

    @Override
    public ResultDO<Integer> selectBusNoticeCount(BaseNoticeReqDTO baseNoticeReqDTO) {
        ResultDO<Integer> resultDO = new ResultDO<Integer>();
        if(ValidateHelper.isEmpty(baseNoticeReqDTO)){
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            resultDO.setSuccess(false);
            return resultDO;
        }
        resultDO = notificationManager.selectBusNoticeCount(baseNoticeReqDTO);
        return resultDO;
    }

    /**
     * 服务通知类型必传参数校验
     * @param serviceNoticeDTO
     * @return
     */
    public String checkServiceParam(ServiceNoticeReqDTO serviceNoticeDTO){
        if(serviceNoticeDTO==null){
            return "请求参数不能为空";
        }
        Integer serviceType=serviceNoticeDTO.getServiceType();
        if(serviceType==null){
            return "服务通知类型不能为空";
        }
        Integer serviceStatus=serviceNoticeDTO.getServiceStatus();
        if(serviceStatus==null){
            return "服务提醒类型不能为空";
        }
        Long receiveId=serviceNoticeDTO.getReceiveId();
        if(receiveId==null){
            return "接收人Id不能为空";
        }

        if(ValidateHelper.isEmptyString(serviceNoticeDTO.getShopAddress())){
            return "商铺地址不能为空";
        }
        if(serviceStatus!= ServiceStatus.SERVICE_COMPLETION.getValue()){
            if(ValidateHelper.isEmptyString(serviceNoticeDTO.getCurrentTime())){
                return "当前日程不能为空";
            }
        }
        return null;
    }


    /**
     * 根据服务状态判定必需参数
     * @param serviceNoticeDTO
     * @return
     */
    public String checkParamByStatus(ServiceNoticeReqDTO serviceNoticeDTO){
        // serviceStatus : 0-时间变动 1-服务撤销 2-服务完成
        Integer serviceStatus=serviceNoticeDTO.getServiceStatus();
        //0：时间变动，必传旧日程
        if(serviceStatus== ServiceStatus.TIME_VARIATION.getValue()){
            if(ValidateHelper.isEmptyString(serviceNoticeDTO.getOldTime())){
                return "旧日程不能为空";
            }
        }
        //0：服务撤销，必传撤销原因或废弃原因
        if(serviceStatus==ServiceStatus.SERVICE_REVOCATION.getValue()){
            if(ValidateHelper.isEmpty(serviceNoticeDTO.getReason())){
                return "撤销原因或废弃原因不能为空";
            }
        }
        return null;
    }

    /**
     * 根据服务类型判定必需参数
     * @param serviceNoticeDTO
     * @return
     */
    public String checkParamByType(ServiceNoticeReqDTO serviceNoticeDTO){
        if(ValidateHelper.isEmpty(serviceNoticeDTO.getServiceName())){
            return "小二姓名不能为空";
        }
        if(ValidateHelper.isEmpty(serviceNoticeDTO.getServiceTel())){
            return "小二电话不能为空";
        }
        Integer serviceStatus=serviceNoticeDTO.getServiceStatus();
        if(serviceStatus==null){
            return "服务状态不能为空";
        }
        return null;
    }

    /**
     * 生意圈
     * @param bussinessNoticeDTO
     * @return
     */
    public String checkMustParam(BussinessNoticeReqDTO bussinessNoticeDTO){
        if(bussinessNoticeDTO==null){
            return "请求参数不能为空";
        }
        Integer bussinessType=bussinessNoticeDTO.getBussinessType();
        if(bussinessType==null){
            return "生意圈通知类型不能为空";
        }
        //生意圈通知类型接收人可为客户和业务员，所以接收类型必须传入
        Integer receiveType=bussinessNoticeDTO.getReceiveType();
        if(receiveType==null){
            return "接收人类型不能为空";
        }
        Long receiveId=bussinessNoticeDTO.getReceiveId();
        if(receiveId==null){
            return "接收人Id不能为空";
        }

        return null;
    }

    /**
     * 根据接收类型判断必传参数
     * @param bussinessNoticeDTO
     * @return
     */
    public String checkParamByReceiveType(BussinessNoticeReqDTO bussinessNoticeDTO){
        //如果接收人为业务员即业务端生意通知必传参数判断
        if(bussinessNoticeDTO.getReceiveType()== ReceiveType.CLERK.getValue()){
            if(ValidateHelper.isEmpty(bussinessNoticeDTO.getCommentTel())){
                return "评论人手机号不能为空";
            }
            if(ValidateHelper.isEmpty(bussinessNoticeDTO.getContentOrReason())){
                return "评论内容不能为空";
            }
        }
        if(bussinessNoticeDTO.getReceiveType()== ReceiveType.CUSTOMER.getValue()){
            if(ValidateHelper.isEmpty(bussinessNoticeDTO.getPostName())){
                return "帖子名称不能为空";
            }
            //BussinessType： 0-评论通知 1-帖子被撤通知 2-评论被删通知
            if(bussinessNoticeDTO.getBussinessType()!= BussinessNoticeType.COMMENT_NOTICE.getValue()){
                /*if(ValidateHelper.isEmptyString(bussinessNoticeDTO.getContentOrReason())){
                    return "删除原因不能为空";
                }*/
                if(ValidateHelper.isEmpty(bussinessNoticeDTO.getCommentOrOprationTime())){
                    return "操作时间不能为空";
                }
            }else{
                if(ValidateHelper.isEmptyString(bussinessNoticeDTO.getContentOrReason())){
                    return "评论内容不能为空";
                }
                if(ValidateHelper.isEmpty(bussinessNoticeDTO.getCommentOrOprationTime())){
                    return "评论时间不能为空";
                }
            }
        }
        return null;
    }


    /**
     * 检查必传参数
     * @param shopNoticeDTO
     * @return
     */
    public String checkShopParam(ShopNoticeReqDTO shopNoticeDTO){
        if(shopNoticeDTO==null){
            return "请求参数不能为空";
        }
        Integer shopNoticeType=shopNoticeDTO.getShopNoticeType();
        if(shopNoticeType==null){
            return "通知状态不能为空";
        }
        Long bussinessId=shopNoticeDTO.getBussinessId();
        if(bussinessId==null){
            return "业务Id不能为空";
        }
        Long receiveId=shopNoticeDTO.getReceiveId();
        if(receiveId==null){
            return "接收人Id不能为空";
        }
        if(shopNoticeType.intValue()==ShopServiceType.SOLD_OUT.getValue()){
            if(ValidateHelper.isEmptyString(shopNoticeDTO.getShopAddress())){
                return "商铺地址不能为空";
            }
        }
        return null;
    }

    /**
     * 检查必传参数
     * @param workNoticeDTO
     * @return
     */
    public String checkWorkParam(WorkNoticeReqDTO workNoticeDTO){
        if(workNoticeDTO==null){
            return "请求参数不能为空";
        }

        Long receiveId=workNoticeDTO.getReceiveId();
        if(receiveId==null){
            return "接收人Id不能为空";
        }
        return null;
    }

    /**
     * 根据通知状态判定参数是否必传
     * @param workNoticeDTO
     * @return
     */
    public  String checkWorkParamByType(WorkNoticeReqDTO workNoticeDTO){
        Integer workNoticeType=workNoticeDTO.getWorkNoticeType();
        if(workNoticeType== BussinessEndNoticeType.RECEIVED_DEMAND.getValue()||
                workNoticeType== BussinessEndNoticeType.TASK_REMIND.getValue()  ){
            //0-实堪 1-约看 2-签约
            Integer serviceType =workNoticeDTO.getServiceType();
            if(serviceType==null){
                return "服务或任务类型不能为空";
            }
            if(ValidateHelper.isEmptyString(workNoticeDTO.getShopAddress())){
                return "商铺地址不能为空";
            }
        }
        if(workNoticeType== BussinessEndNoticeType.TASK_REMIND.getValue()){
            if(ValidateHelper.isEmpty(workNoticeDTO.getPlanTime())){
                return "计划时间不能为空";
            }
        }
//        if(workNoticeType== WorkServiceType.WEEK.getValue() ||
//                workNoticeType== WorkServiceType.MONTH.getValue() ){
//            if(ValidateHelper.isEmpty(workNoticeDTO.getCloseStoreName())){
//                return "收铺榜擂主姓名不能为空";
//            }
//            if(ValidateHelper.isEmpty(workNoticeDTO.getSignName())){
//                return "签约榜擂主姓名不能为空";
//            }
//            if(ValidateHelper.isEmpty(workNoticeDTO.getLookName())){
//                return "约看榜擂主姓名不能为空";
//            }
//            if(ValidateHelper.isEmpty(workNoticeDTO.getRegisterName())){
//                return "注册榜擂主姓名不能为空";
//            }
//        }

        return null;
    }

    /**
     * @description
     * @package com.dongtong.basic.service
     * @author chenxs
     * @date 2017/8/22 0022 16:10
     * @param
     * @return
     */
    public ResultDO<Integer> updateByBusId(BaseNotification baseNotification){
        ResultDO<Integer> resultDO = notificationManager.updateByBusId(baseNotification);
        return resultDO;
    }

}
