package com.dongtong.basic.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.dongtong.basic.constant.ErrorConstant;
import com.dongtong.basic.dto.resp.AliyunSTSRespDTO;
import com.shfc.common.result.ResultDO;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/25 下午4:39.
 */
@Service
public class AliyunServiceImpl implements AliyunService {

    // 目前只有"cn-hangzhou"这个region可用, 不要使用填写其他region的值
    public static final String REGION_CN_HANGZHOU = "cn-hangzhou";
    // 当前 STS API 版本
    public static final String STS_API_VERSION = "2015-04-01";

    // 只有 RAM用户（子账号）才能调用 AssumeRole 接口
    // 阿里云主账号的AccessKeys不能用于发起AssumeRole请求
    // 请首先在RAM控制台创建一个RAM用户，并为这个用户创建AccessKeys
    public static final String accessKeyId = "LTAINsh3eoYp7qy8";
    public static final String accessKeySecret = "Ot995pSTvkCUxLSsbSE0cJLUTtzRLa";
    // AssumeRole API 请求参数: RoleArn, RoleSessionName, Policy, and DurationSeconds
    // RoleArn 需要在 RAM 控制台上获取
    public static final String roleArn = "acs:ram::1764973912509561:role/wp-role";

    public static final String policy = "{\n" +
            "  \"Version\": \"1\",\n" +
            "  \"Statement\": [\n" +
            "    {\n" +
            "      \"Effect\": \"Allow\",\n" +
            "      \"Action\": [\n" +
            "        \"oss:PutObject\",\n" +
            "        \"oss:GetObject\"\n" +
            "      ],\n" +
            "      \"Resource\": [\n" +
            "        \"acs:oss:*:*:wp-oss-file/*\"\n" +
            "      ],\n" +
            "      \"Condition\": {}\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    /**
     * 获取阿里云oss以sts方式访问的token
     *
     * @return
     */
    @Override
    public ResultDO<AliyunSTSRespDTO> getTmpAccessInfo(String platRoleName, String osType, Long userId) {
        ResultDO<AliyunSTSRespDTO> resultDO = new ResultDO<AliyunSTSRespDTO>();
        AliyunSTSRespDTO aliyunSTSRespDTO = new AliyunSTSRespDTO();

        try {
            // RoleSessionName 是临时Token的会话名称，自己指定用于标识你的用户，主要用于审计，或者用于区分Token颁发给谁
            // 但是注意RoleSessionName的长度和规则，不要有空格，只能有'-' '_' 字母和数字等字符
            // 具体规则请参考API文档中的格式要求
            String roleSessionName = "wp-app-" + platRoleName + "-" + osType + "-" + userId;
            AssumeRoleRequest request = new AssumeRoleRequest();
            request.setVersion(STS_API_VERSION);
            request.setMethod(MethodType.POST);
            request.setProtocol(ProtocolType.HTTPS);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy);

            IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            AssumeRoleResponse response = client.getAcsResponse(request);
            if (response == null){
                resultDO.setSuccess(false);
                resultDO.setErrCode(ErrorConstant.REMOTE_SERVER_RESP_NULL.getCode());
                resultDO.setErrMsg(ErrorConstant.REMOTE_SERVER_RESP_NULL.getMsg());
                return resultDO;
            }
            aliyunSTSRespDTO.setAccessKeyId(response.getCredentials().getAccessKeyId());
            aliyunSTSRespDTO.setAccessKeySecret(response.getCredentials().getAccessKeySecret());
            String expiration = response.getCredentials().getExpiration().replaceAll("[A-Z]+"," ");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(sdf.parse(expiration));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.add(Calendar.HOUR_OF_DAY, 8);
            aliyunSTSRespDTO.setExpiration(sdf.format(calendar.getTime()).replace(" ","T") + "Z");
            aliyunSTSRespDTO.setSecurityToken(response.getCredentials().getSecurityToken());
            resultDO.setSuccess(true);
            resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
            resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
            resultDO.setData(aliyunSTSRespDTO);
        } catch (ClientException e) {
            e.printStackTrace();
            resultDO.setSuccess(false);
            resultDO.setErrCode(1);
            resultDO.setErrMsg(e.getErrMsg());
        }
        return resultDO;
    }
}
