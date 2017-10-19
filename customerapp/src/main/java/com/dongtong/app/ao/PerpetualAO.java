package com.dongtong.app.ao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dongtong.app.utils.CustomerappProperties;
import com.dongtong.app.wnl.api.DefaultApi;
import com.fc.common.redis.RedisUtil;
import com.shfc.common.base.StringUtils;
import com.shfc.common.date.DateUtils;
import com.shfc.common.json.JsonUtils;
import com.shfc.common.result.ResultDO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Package com.dongtong.app.ao.PerpetualAO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/10 14:58
 * version V1.0.0
 */
@Service
public class PerpetualAO {

    public Logger logger = Logger.getLogger(PerpetualAO.class);

    private final DefaultApi api = new DefaultApi();

//    @Value("${jdwx.appkey}")
//    private String appkey;
    @Autowired
    private CustomerappProperties customerappProperties;

    /**
     *
     * @return
     */
    public ResultDO<JSONObject> selectWnlJsonStr(){
        ResultDO<JSONObject> resultDO = new ResultDO<JSONObject>();
//        if(ValidateHelper.isEmpty(currentDate)){
//            resultDO.setErrMsg(ErrorConstant.DATE_ERROR.getMsg());
//            resultDO.setErrCode(ErrorConstant.DATE_ERROR.getCode());
//            return resultDO;
//        }
//        if(!ValidDateUtils.isValidDate(currentDate)){
//            resultDO.setErrMsg(ErrorConstant.DATE_ERROR.getMsg());
//            resultDO.setErrCode(ErrorConstant.DATE_ERROR.getCode());
//            return resultDO;
//        }
        String currentDate = DateUtils.formatDate(new Date(),"yyyy-MM-dd");
        String wnlStr = StringUtils.toString(RedisUtil.get("wnl_"+currentDate),"");
        if(wnlStr==null || wnlStr.length()==0){
            try {
                wnlStr = api.query2(currentDate,customerappProperties.getJdwxAppkey());
                JSONObject object = JsonUtils.parseJavaObject(wnlStr, JSONObject.class);

                if(object == null){
                    resultDO.setErrMsg("json格式错误,请检查请求参数");
                    return resultDO;
                }
                if("10000".equals(object.get("code").toString())){
                    String wnlResult = object.getJSONObject("result").get("result").toString();
                    JSONObject jsonobject = JSON.parseObject(wnlResult);
                    RedisUtil.set("wnl_"+currentDate,jsonobject);
                    resultDO.setData(jsonobject);
                }else{
                    resultDO.setErrMsg("京东万象接口调用错误，请检查参数");
                    String wnlResult = object.getJSONObject("result").toString();
                    logger.error("Exception:"+wnlResult);
                    return resultDO;
                }
            } catch (Exception e) {
                logger.error("Exception"+e.getMessage());
                resultDO.setErrMsg("调用京东万象报错,错误信息："+e.getMessage());
                return resultDO;
            }
        }else{
            JSONObject jsonobject = JSON.parseObject(wnlStr);
            resultDO.setData(jsonobject);
        }
        resultDO.setSuccess(true);
        return resultDO;
    }
}
