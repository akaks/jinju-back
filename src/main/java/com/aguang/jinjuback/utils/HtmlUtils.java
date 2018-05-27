package com.aguang.jinjuback.utils;

import com.aguang.jinjuback.services.AreaInfoService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HtmlUtils {

    public final static Logger logger = LoggerFactory.getLogger(AreaInfoService.class);

    /**
     * 根据url从网络获取网页文本
     * @param url
     * @param charsetName 字符集编码
     * @return
     */
    public static Document getHtmlTextByUrl(String url, String charsetName) {
        Document doc = null;
        try {
            //doc = Jsoup.connect(url).timeout(5000000).get();
            int i = (int)(Math.random() * 1000); //做一个随机延时，防止网站屏蔽
//            while (i != 0) {
//                i--;
//            }
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            doc = Jsoup.connect(url).data("query", "Java").userAgent("Mozilla").cookie("auth", "token").timeout(300000).post();

            doc = Jsoup.parse(new URL(url).openStream(), charsetName, url);


        } catch (Exception e) {
            logger.error("无该URL!");
//            try {
//                doc = Jsoup.connect(url).timeout(5000000).get();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
        }
        return doc;
    }

    public static Document getHtmlTextByFile(File file) {
        return getHtmlTextByFile(file, "UTF-8");
    }

    /**
     *
     */
    public static Document getHtmlTextByFile(File file, String charsetName) {
        Document doc = null;
        try {
            doc = Jsoup.parse(file, charsetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }


    /**
     * 根据元素属性获取某个元素内的elements列表
     * @param doc
     * @param className
     * @return
     */
    public static Elements getEleByClass(Document doc, String className) {
        //<tr class="provincetr">
        Elements elements = null;
        elements = doc.select(className); //     tr.provincetr
        return elements; //此处返回的就是所有的tr集合
    }
}
