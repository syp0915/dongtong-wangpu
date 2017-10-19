package com.dongtong.app.ao;

import com.dongtong.app.constant.ErrorConstant;
import com.dongtong.customer.dto.resp.ShopNameIsGoodRespDTO;
import com.dongtong.customer.query.ShopNameIsGoodReqQuery;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.encrypt.MD5Utils;
import com.shfc.common.result.ResultDO;
import org.springframework.stereotype.Service;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/11 上午10:20.
 */
@Service
public class FortuneAO {

    /**
     * 店名md5加密为32位字符串，转全大写，分解为32长度字符数组，取出每个字符对应的ACSII码，取和，结果对10取模，得到对应索引数组结果
     * @param query
     * @return
     */
    public ResultDO<ShopNameIsGoodRespDTO> shopNameIsGood(ShopNameIsGoodReqQuery query){
        ResultDO<ShopNameIsGoodRespDTO> resultDO = new ResultDO<ShopNameIsGoodRespDTO>();
        String shopName = query.getShopName();
        if (ValidateHelper.isEmpty(shopName)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }

        Integer[] moneyLuckArray = {9, 10, 9, 8, 10, 8, 9, 8, 10, 9};
        Integer[] guestLuckArray = {8, 8, 9, 8, 9, 7, 7, 9, 8, 9};
        Integer[] futureArray = {9 ,8 ,9 ,9 ,9, 8, 8, 8, 9 ,10};
        String[] descriptionString = new String[10];
        descriptionString[0] = "草木逢春正适时，财源广进福自来，万事无障碍而达目的。乘天助之幸运，实现志望，生意兴隆，富贵东来的好运数。";
        descriptionString[1] = "伟大昌隆之运，威势冲天之象，赫赫首领之数，盟主四方。虽然自主经营有如微贱出身，但能砥志奋斗，克服万难，最终成就大志大业，功名荣达，五湖商友来朝，四海客户来拜。";
        descriptionString[2] = "自受天荫事无不成之数，富贵长寿之贵运也，财帛天长地久，生意事事成就，财运隆昌，福寿绵长，安享盛名。";
        descriptionString[3] = "昭日升天之象，形成确定之意。店铺人才多且足智多谋，做事刚毅果断有如旭日东升，旺盛隆昌至极，属名闻天下的吉祥运。";
        descriptionString[4] = "位尊望重，建立基业，雅量厚重，足智多谋，善于协调，所谋如意，公司繁荣，生意兴隆，福禄寿俱全，谋大事大业皆可成，富贵发达的好暗示，属温和之首领运数，四方商客来朝拜，威镇五湖，财达三江。";
        descriptionString[5] = "智虑周密，志向坚定，独立经营，勤勉力行，发展奋进之象，托祖荫有回天之力，愿望达成，名利双收。";
        descriptionString[6] = "天德地祥俱备，天地人和。财禄丰盈，富裕安稳，业务盛大，万宝朝宗之运，此数理为天赋之美，安稳吉庆有余，但须谨慎处事切勿傲慢种下苦果。";
        descriptionString[7] = "本有生成之吉兆，怎奈花生石上，虽有天荫，自应受福，无奈内心劳苦甚多，若能坚定意志，贯彻力行，吉从天降，始可业有大成。";
        descriptionString[8] = "寒莺逢春，生机之象，店主资性刚毅，胆智过人，事业成功，享天赋之富贵幸福。但略有小难，若能跨过，后得享吉祥繁荣，万事如意，生意兴隆。";
        descriptionString[9] = "万物承惠雨露之恩，有发育壮大之象也，事事随心所欲，逢凶化吉，得天赐之福，不费心神，万事如意，生意兴隆，惠及商朋，荣传子孙，誉享万年之最大吉数。但必专心一意，坚韧向上，以心换心，臣服天下。";

        String md5String = MD5Utils.encrypt(shopName, MD5Utils.MD5_KEY).toUpperCase();
        char[] charArray = md5String.toCharArray();
        Integer sum = 0;
        for (int i = 0; i < charArray.length; i++) {
            sum += charArray[i];
        }
        ShopNameIsGoodRespDTO shopNameIsGoodRespDTO = new ShopNameIsGoodRespDTO();
        Integer index = sum % 10;
        shopNameIsGoodRespDTO.setMoneyLuck(moneyLuckArray[index]);
        shopNameIsGoodRespDTO.setGuestLuck(guestLuckArray[index]);
        shopNameIsGoodRespDTO.setFuture(futureArray[index]);
        shopNameIsGoodRespDTO.setDescription(descriptionString[index]);

        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(shopNameIsGoodRespDTO);
        return resultDO;
    }
}
