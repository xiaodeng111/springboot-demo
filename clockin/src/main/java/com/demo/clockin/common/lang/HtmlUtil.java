package com.demo.clockin.common.lang;

import com.demo.clockin.common.constant.Property;
import com.demo.clockin.common.constant.Property;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Administrator on 2018/2/27.
 */
public class HtmlUtil {

    /**
     * 去掉img和video标签里的域名
     * @param htmlContent
     * @return
     */
    public static String replaceImgVideoHtml(String htmlContent){
        Document doc = Jsoup.parse(htmlContent);
        Elements imgElements = doc.getElementsByTag("img");

        delDomain(imgElements);
        Elements videoElements = doc.getElementsByTag("video");
        delDomain(videoElements);

        Elements body = doc.getElementsByTag("body");
        return body.html();
    }

    private static void delDomain(Elements imgElements) {
        if(imgElements != null && imgElements.size() > 0) {
            for(Element image : imgElements) {
                String src = image.attr("src");
                if(StringUtil.isEmpty(src)) {
                    continue;
                }

                String relationUrl =src.replace(Property.FILE_UPLOAD_ROOTURL,"");
                image.attr("src", relationUrl);
            }
        }
    }

    private static void addDomain(Elements imgElements) {
        if(imgElements != null && imgElements.size() > 0) {
            for(Element image : imgElements) {
                String src = image.attr("src");
                if(StringUtil.isEmpty(src)) {
                    continue;
                }

                String absUrl =Property.FILE_UPLOAD_ROOTURL + src;
                image.attr("src", absUrl);
            }
        }
    }

    /**
     * img和video标签里的添加域名
     * @param htmlContent
     * @return
     */
    public static String addImgVideoDomain(String htmlContent){
        Document doc = Jsoup.parse(htmlContent);
        Elements imgElements = doc.getElementsByTag("img");

        addDomain(imgElements);
        Elements videoElements = doc.getElementsByTag("video");
        addDomain(videoElements);
        Elements body = doc.getElementsByTag("body");
        return body.html();
    }

}
