package com.dongtong.basic.manager;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.constant.ErrorConstant;
import com.dongtong.basic.remote.MessageCenterNetRequest;
import com.shfc.common.base.Logger;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.manager
 * @Description
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-15 18:30
 * version V1.0.0
 **/
@Service
public class NoticePushManager {

    @Autowired(required = false)
    private MessageCenterNetRequest messageCenterNetRequest;

    /**
     * 推送帖子评论通知短信
     * @param hashMap
     * @return
     */
    public ResultDO pushPostCommentNotify(HashMap<String,Object> paramMap,HashMap hashMap){
        ResultDO resultDO = new ResultDO();
        String result = messageCenterNetRequest.pushPostCommentNotify(paramMap,hashMap);
        if (result == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.REMOTE_SERVER_RESP_NULL.getCode());
            resultDO.setErrMsg(ErrorConstant.REMOTE_SERVER_RESP_NULL.getMsg());
            return resultDO;
        }
        resultDO = JSON.parseObject(result, ResultDO.class);
        return resultDO;
    }

    public ResultDO pushBusDelNotify(HashMap<String,Object> paramMap,HashMap hashMap){
        ResultDO resultDO = new ResultDO();
        String result = messageCenterNetRequest.pushBusDelNotify(paramMap,hashMap);
        if (result == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.REMOTE_SERVER_RESP_NULL.getCode());
            resultDO.setErrMsg(ErrorConstant.REMOTE_SERVER_RESP_NULL.getMsg());
            return resultDO;
        }
        resultDO = JSON.parseObject(result, ResultDO.class);
        return resultDO;
    }

    public ResultDO pushScheduleNotify(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,HashMap hashMap){
        ResultDO resultDO = new ResultDO();
        String result = messageCenterNetRequest.pushScheduleNotify(paramMap,contentParamList,hashMap);
        if (result == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.REMOTE_SERVER_RESP_NULL.getCode());
            resultDO.setErrMsg(ErrorConstant.REMOTE_SERVER_RESP_NULL.getMsg());
            return resultDO;
        }
        resultDO = JSON.parseObject(result, ResultDO.class);
        return resultDO;
    }

    public ResultDO pushScheduleAddNotify(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,
                                             ArrayList<String> smsContentParamList, HashMap hashMap){
        ResultDO resultDO = new ResultDO();
        String result = messageCenterNetRequest.pushScheduleAddNotify(paramMap,contentParamList,smsContentParamList,hashMap);
        if (result == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.REMOTE_SERVER_RESP_NULL.getCode());
            resultDO.setErrMsg(ErrorConstant.REMOTE_SERVER_RESP_NULL.getMsg());
            return resultDO;
        }
        resultDO = JSON.parseObject(result, ResultDO.class);
        return resultDO;
    }

    public ResultDO pushScheduleChangeNotify(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,
                                             ArrayList<String> smsContentParamList, HashMap hashMap){
        ResultDO resultDO = new ResultDO();
        String result = messageCenterNetRequest.pushScheduleChangeNotify(paramMap,contentParamList,smsContentParamList,hashMap);
        if (result == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.REMOTE_SERVER_RESP_NULL.getCode());
            resultDO.setErrMsg(ErrorConstant.REMOTE_SERVER_RESP_NULL.getMsg());
            return resultDO;
        }
        resultDO = JSON.parseObject(result, ResultDO.class);
        return resultDO;
    }

    public ResultDO pushServiceRevokeNotify(HashMap<String,Object> paramMap,ArrayList<String> contentParamList, ArrayList<String> smsContentParamList,HashMap hashMap){
        ResultDO resultDO = new ResultDO();
        String result = messageCenterNetRequest.pushServiceRevokeNotify(paramMap,contentParamList,smsContentParamList,hashMap);
        if (result == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.REMOTE_SERVER_RESP_NULL.getCode());
            resultDO.setErrMsg(ErrorConstant.REMOTE_SERVER_RESP_NULL.getMsg());
            return resultDO;
        }
        resultDO = JSON.parseObject(result, ResultDO.class);
        return resultDO;
    }

    public ResultDO pushServiceSucNotify(String userPhone,ArrayList<String> smsContentParamList){
        ResultDO resultDO = new ResultDO();
        String result = messageCenterNetRequest.pushServiceSucNotify(userPhone,smsContentParamList);
        if (result == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.REMOTE_SERVER_RESP_NULL.getCode());
            resultDO.setErrMsg(ErrorConstant.REMOTE_SERVER_RESP_NULL.getMsg());
            return resultDO;
        }
        resultDO = JSON.parseObject(result, ResultDO.class);
        return resultDO;
    }

    public ResultDO pushTrumpetNotice(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,HashMap hashMap){
        ResultDO resultDO = new ResultDO();
        String result = messageCenterNetRequest.pushTrumpetNotice(paramMap,contentParamList,hashMap);
        if (result == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.REMOTE_SERVER_RESP_NULL.getCode());
            resultDO.setErrMsg(ErrorConstant.REMOTE_SERVER_RESP_NULL.getMsg());
            return resultDO;
        }
        resultDO = JSON.parseObject(result, ResultDO.class);
        return resultDO;
    }

    public ResultDO pushShopNotice(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,HashMap hashMap){
        ResultDO resultDO = new ResultDO();
        String result = messageCenterNetRequest.pushShopNotice(paramMap,contentParamList,hashMap);
        Logger.info(NoticePushManager.class,"返回结果:"+result);
        if (result == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.REMOTE_SERVER_RESP_NULL.getCode());
            resultDO.setErrMsg(ErrorConstant.REMOTE_SERVER_RESP_NULL.getMsg());
            return resultDO;
        }
        resultDO = JSON.parseObject(result, ResultDO.class);
        return resultDO;
    }
    public ResultDO pushWorkNotice(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,HashMap hashMap){
        ResultDO resultDO = new ResultDO();
        String result = messageCenterNetRequest.pushWorkNotice(paramMap,contentParamList,hashMap);
        if (result == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.REMOTE_SERVER_RESP_NULL.getCode());
            resultDO.setErrMsg(ErrorConstant.REMOTE_SERVER_RESP_NULL.getMsg());
            return resultDO;
        }
        resultDO = JSON.parseObject(result, ResultDO.class);
        return resultDO;
    }
    public ResultDO pushArrivalTimeNotice(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,HashMap hashMap){
        ResultDO resultDO = new ResultDO();
        String result = messageCenterNetRequest.pushArrivalTimeNotice(paramMap,contentParamList,hashMap);
        if (result == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.REMOTE_SERVER_RESP_NULL.getCode());
            resultDO.setErrMsg(ErrorConstant.REMOTE_SERVER_RESP_NULL.getMsg());
            return resultDO;
        }
        resultDO = JSON.parseObject(result, ResultDO.class);
        return resultDO;
    }
    public ResultDO pushWeekMonthNotice(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,HashMap hashMap){
        ResultDO resultDO = new ResultDO();
        String result = messageCenterNetRequest.pushWeekMonthNotice(paramMap,contentParamList,hashMap);
        if (result == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.REMOTE_SERVER_RESP_NULL.getCode());
            resultDO.setErrMsg(ErrorConstant.REMOTE_SERVER_RESP_NULL.getMsg());
            return resultDO;
        }
        resultDO = JSON.parseObject(result, ResultDO.class);
        return resultDO;
    }
    public ResultDO pushPostCommentBusNotify(HashMap<String,Object> paramMap,ArrayList<String> contentParamList,HashMap hashMap){
        ResultDO resultDO = new ResultDO();
        String result = messageCenterNetRequest.pushPostCommentBusNotify(paramMap,contentParamList,hashMap);
        if (result == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.REMOTE_SERVER_RESP_NULL.getCode());
            resultDO.setErrMsg(ErrorConstant.REMOTE_SERVER_RESP_NULL.getMsg());
            return resultDO;
        }
        resultDO = JSON.parseObject(result, ResultDO.class);
        return resultDO;
    }


}
