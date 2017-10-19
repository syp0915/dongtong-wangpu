package com.dongtong.topic.dto.resp;

import com.dongtong.topic.dto.TopicListDTO;

import java.io.Serializable;
import java.util.List;

public class HomePageDataRespDTO implements Serializable{
    private List<ContentListRespDTO> headlineList;
    private List<ContentListRespDTO> bibleList;
    private List<TopicListDTO> topicList;

    public List<ContentListRespDTO> getHeadlineList() {
        return headlineList;
    }

    public void setHeadlineList(List<ContentListRespDTO> headlineList) {
        this.headlineList = headlineList;
    }

    public List<ContentListRespDTO> getBibleList() {
        return bibleList;
    }

    public void setBibleList(List<ContentListRespDTO> bibleList) {
        this.bibleList = bibleList;
    }

    public List<TopicListDTO> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<TopicListDTO> topicList) {
        this.topicList = topicList;
    }

    @Override
    public String toString() {
        return "HomePageDataRespDTO{" +
                "headlineList=" + headlineList +
                ", bibleList=" + bibleList +
                ", topicList=" + topicList +
                '}';
    }
}
