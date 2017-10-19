package com.dongtong.topic.manager;

import com.dongtong.shop.dto.ShopRecomendListDTO;
import com.dongtong.shop.query.ShopRecomendQuery;
import com.dongtong.shop.service.ShopService;
import com.dongtong.topic.dao.ContentMapper;
import com.dongtong.topic.dao.ContentTagsMapper;
import com.dongtong.topic.domain.Content;
import com.dongtong.topic.dto.req.ContentDetailReqDTO;
import com.dongtong.topic.dto.req.ContentGuideDetailReqDTO;
import com.dongtong.topic.dto.req.HotTagsReqDTO;
import com.dongtong.topic.dto.resp.*;
import com.dongtong.topic.enums.TopicErrorCode;
import com.dongtong.topic.query.ContentGuideListQuery;
import com.dongtong.topic.query.ContentListQuery;
import com.dongtong.topic.utils.HtmlUtils;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

;

@Service
public class ContentManager {

    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private ShopService shopService;

    @Autowired
    private ContentTagsMapper contentTagsMapper;

    /**
     * @description
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/8/7 13:58
     * @param contentListQuery
     * @return ResultDO<Page<ContentListRespDTO>>
     */
    public ResultDO<Page<ContentListRespDTO>> getContentList(ContentListQuery contentListQuery) {
        ResultDO<Page<ContentListRespDTO>> resultDO = new ResultDO<>();

        Page<ContentListRespDTO> page = new Page<>();
        page.setQuery(contentListQuery);
        page.setPageNumber(contentListQuery.getPageNumber());
        page.setPageSize(contentListQuery.getPageSize());
        contentMapper.selectContentList(page);

        //查询标签列表
        List<ContentListRespDTO> resultList = page.getData();
        List<ContentListRespDTO> newResultList = new ArrayList<>();
        for(ContentListRespDTO item : resultList){
            List<HotTagsRespDTO> tagList = contentTagsMapper.selectTagListByContentId(item.getContentId());
            item.setTagList(tagList);
            newResultList.add(item);
        }
        page.setData(newResultList);

        resultDO.setSuccess(true);
        resultDO.setData(page);
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/8/7 13:58
     * @param contentListQuery
     * @return ResultDO<Page<ContentListRespDTO>>
     */
    public ResultDO<Page<ContentListRespDTO>> getContentListByTags(ContentListQuery contentListQuery) {
        ResultDO<Page<ContentListRespDTO>> resultDO = new ResultDO<>();

        Page<ContentListRespDTO> page = new Page<>();
        page.setQuery(contentListQuery);
        page.setPageNumber(contentListQuery.getPageNumber());
        page.setPageSize(contentListQuery.getPageSize());
        contentMapper.selectContentListByTagId(page);

        //查询标签列表
        List<ContentListRespDTO> resultList = page.getData();
        List<ContentListRespDTO> newResultList = new ArrayList<>();
        for(ContentListRespDTO item : resultList){
            List<HotTagsRespDTO> tagList = contentTagsMapper.selectTagListByContentId(item.getContentId());
            item.setTagList(tagList);
            newResultList.add(item);
        }
        page.setData(newResultList);

        resultDO.setSuccess(true);
        resultDO.setData(page);
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/8/7 14:37
     * @param contentDetailReqDTO
     * @return ResultDO<ContentDetailRespDTO>
     */
    public ResultDO<ContentDetailRespDTO> getContentDetail(ContentDetailReqDTO contentDetailReqDTO) {
        ResultDO<ContentDetailRespDTO> resultDO = new ResultDO<>();

        ContentDetailRespDTO contentDetailRespDTO =  contentMapper.selectContentDetailById(contentDetailReqDTO);
        if(contentDetailRespDTO == null){
            resultDO.setErrMsg("内容详情不存在");
            resultDO.setErrCode(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getCode());
            return resultDO;
        }
        updateCount(contentDetailReqDTO.getContentId() , "view");    //更新查看次数

        if(contentDetailRespDTO.getContent() != null){
            String noHtmlTagsContent = HtmlUtils.deleteAllHTMLTag(contentDetailRespDTO.getContent());
            String imageStyleContent = HtmlUtils.replaceImageStyle(contentDetailRespDTO.getContent());
            contentDetailRespDTO.setContent(imageStyleContent);
            contentDetailRespDTO.setDesc(getDesc(noHtmlTagsContent));
        }

        List<HotTagsRespDTO> tagList = contentTagsMapper.selectTagListByContentId(contentDetailReqDTO.getContentId());
        if(tagList != null){
            contentDetailRespDTO.setTagList(tagList);
        }

        resultDO.setSuccess(true);
        resultDO.setData(contentDetailRespDTO);
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/8/7 13:58
     * @param hotTagsReqDTO
     * @return ResultDO<List<HotTagsRespDTO>>
     */
    public ResultDO<List<HotTagsRespDTO>> getTagsList(HotTagsReqDTO hotTagsReqDTO){
        ResultDO<List<HotTagsRespDTO>> resultDO = new ResultDO<>();

        List<HotTagsRespDTO> resultList = contentTagsMapper.selectTagListByType(hotTagsReqDTO);

        resultDO.setData(resultList);
        resultDO.setSuccess(true);
        return resultDO;
    }

    /**
     * @description 
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/8/7 0007 15:36
     * @param contentId
     * @return ResultDO
     */
    public ResultDO contentGood(Long contentId) {
        ResultDO resultDO = new ResultDO();

        Integer count = updateCount(contentId, "good");
        if(count < 0){
            resultDO.setSuccess(false);
            resultDO.setErrMsg("点赞失败");
            resultDO.setErrCode(TopicErrorCode.DB_OPERATE_ERROR.getCode());
        }

        resultDO.setSuccess(true);
        return resultDO;
    }

    /**
     * @description 
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/8/7 0007 15:36
     * @param contentId
     * @return ResultDO
     */
    public ResultDO contentBad(Long contentId) {
        ResultDO resultDO = new ResultDO();

        Integer count = updateCount(contentId, "bad");
        if(count < 0){
            resultDO.setSuccess(false);
            resultDO.setErrMsg("踩失败");
            resultDO.setErrCode(TopicErrorCode.DB_OPERATE_ERROR.getCode());
        }

        resultDO.setSuccess(true);
        return resultDO;
    }

    /**
     * @description 根据调用内容详情接口，统计查看次数
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/7 13:56
     * @param contentId
     * @param type    view--update view_count,good--update good_count,bad--update bad_count
     * @return Integer
     */
    private Integer updateCount(Long contentId,String type){
        Content content = contentMapper.selectByPrimaryKey(contentId);
        Integer count = 0;
        switch (type){
            case "view" :
                count = contentMapper.updateViewCount(content);
                break;
            case "good" :
                count = contentMapper.updateGoodCount(content);
                break;
            case "bad" :
                count = contentMapper.updateBadCount(content);
                break;
        }

        return count;
    }

    /**
     * @description
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/8/8 0008 11:25
     * @param contentGuideListQuery
     * @return ContentGuideListQuery
     */
    public ResultDO<List<HomePageGuideListRespDTO>> getHomePageGuideData(ContentGuideListQuery contentGuideListQuery) {
        ResultDO<List<HomePageGuideListRespDTO>> resultDO = new ResultDO<>();

        Page<HomePageGuideListRespDTO> page = new Page<>();
        page.setQuery(contentGuideListQuery);
        page.setPageSize(contentGuideListQuery.getPageSize());
        page.setPageNumber(contentGuideListQuery.getPageNumber());
        contentMapper.selectHomePageGuideList(page);

        resultDO.setSuccess(true);
        resultDO.setData(page.getData());
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/8/8 0008 11:25
     * @param contentGuideListQuery
     * @return ResultDO<Page<ContentGuideListRespDTO>>
     */
    public ResultDO<Page<ContentGuideListRespDTO>> getContentGuideList(ContentGuideListQuery contentGuideListQuery) {
        ResultDO<Page<ContentGuideListRespDTO>> resultDO = new ResultDO<>();

        Page<ContentGuideListRespDTO> page = new Page<>();
        page.setQuery(contentGuideListQuery);
        page.setPageSize(contentGuideListQuery.getPageSize());
        page.setPageNumber(contentGuideListQuery.getPageNumber());
        contentMapper.selectGuideList(page);

        resultDO.setData(page);
        resultDO.setSuccess(true);
        return resultDO;
    }

    /**
     * @description
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/8/8 0008 11:25
     * @param contentGuideDetailReqDTO
     * @return ResultDO<ContentGuideDetailRespDTO>
     */
    public ResultDO<ContentGuideDetailRespDTO> getContentGuideDetail(ContentGuideDetailReqDTO contentGuideDetailReqDTO) {
        ResultDO<ContentGuideDetailRespDTO> resultDO = new ResultDO<>();

        ContentGuideDetailRespDTO result = contentMapper.selectGuideDetail(contentGuideDetailReqDTO);
        if(result == null){
            resultDO.setErrMsg("内容详情不存在");
            resultDO.setErrCode(TopicErrorCode.TOPIC_ENTITY_IS_NULL.getCode());
            return resultDO;
        }

        if(result.getContent() != null ){
            String noHtmlTagsContent = HtmlUtils.deleteAllHTMLTag(result.getContent());
            String imageStyleContent = HtmlUtils.replaceImageStyle(result.getContent());
            result.setContent(imageStyleContent);
            result.setDesc(getDesc(noHtmlTagsContent));
        }


        //查询标签列表
        List<HotTagsRespDTO> tagList = contentTagsMapper.selectTagListByContentId(result.getContentId());
        result.setTagList(tagList);
        result.setShopList(getRecomendShopList(result.getContentId() , contentGuideDetailReqDTO));

        resultDO.setSuccess(true);
        resultDO.setData(result);
        return resultDO;

    }

    /**
     * @description 
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/8/11 0011 15:41
     * @param 
     * @return 
     */
    public List<ShopListRespDTO> getRecomendShopList(Long contentId , ContentGuideDetailReqDTO contentGuideDetailReqDTO){
        List<ShopListRespDTO> shopList = contentMapper.selectShopListByContent(contentId);
        List<ShopListRespDTO> shopResultList = new ArrayList<>();
        for(ShopListRespDTO item : shopList){
            ShopRecomendQuery shopRecomendQuery = new ShopRecomendQuery();
            shopRecomendQuery.setShopId(item.getShopId());
            if(!ValidateHelper.isEmptyString(contentGuideDetailReqDTO.getLatitude())
                    && !ValidateHelper.isEmptyString(contentGuideDetailReqDTO.getLongitude())){
                shopRecomendQuery.setLatitude(contentGuideDetailReqDTO.getLatitude());
                shopRecomendQuery.setLongitude(contentGuideDetailReqDTO.getLongitude());
            }
            ResultDO<ShopRecomendListDTO> shopResultDO = shopService.getRecomendShop(shopRecomendQuery);
            if(shopResultDO != null && shopResultDO.isSuccess()){
                ShopRecomendListDTO shopRecomendListDTO = shopResultDO.getData();
                if(shopRecomendListDTO != null){
                    BeanUtils.copyProperties(shopRecomendListDTO, item, "shopId");
                    shopResultList.add(item);
                }
            }
        }
        return shopList;
    }

    /**
     * @description 获取简介描述
     * @package com.dongtong.topic.service
     * @author chenxs
     * @date 2017/8/31 0031 16:29
     * @param content
     * @return String
     */
    private String getDesc(String content){
        StringBuilder description = new StringBuilder();
        if(content.length() > 25){
            description.append(content.trim().substring(0,25));
        } else {
            description.append(content.trim());
        }
        description.append("...");
        return description.toString();
    }
}
