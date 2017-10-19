package com.dongtong.topic.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlUtils {

    /**
     * @description 替换图片css样式
     * @package com.dongtong.topic.utils
     * @author chenxs
     * @date 2017/8/29 0029 15:16
     * @param content
     * @return String
     */
    public static String replaceImageStyle(String content){
        Document doc = Jsoup.parse(content);
        Elements eles = doc.getAllElements();
        for(Element ele : eles){
            String tagName = ele.tagName();
            if("img".equalsIgnoreCase(tagName)){
                String oldStyle = ele.attr("style");
                StringBuilder newStyle = new StringBuilder("width:100%;");
                newStyle.append(oldStyle);
                ele.attr("style",newStyle.toString());
            }
        }
        String newsBody = doc.body().html();
        return newsBody;
    }

    /**
     * 删除所有的HTML标签
     *
     * @param source 需要进行除HTML的文本
     * @return
     */
    public static String deleteAllHTMLTag(String source) {
        if(source == null) {
            return "";
        }

        String s = source;
        /** 删除普通标签  */
        s = s.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "");
        /** 删除转义字符 */
        s = s.replaceAll("&.{2,6}?;", "");
        return s;
    }

    public static void main(String[] args) {

    }
}
