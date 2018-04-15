package com.aguang.jinjuback.services.spider;

import com.aguang.jinjuback.dao.admin.AdJinjuDao;
import com.aguang.jinjuback.model.constants.AdJinjuSourceConstant;
import com.aguang.jinjuback.services.AreaInfoService;
import com.aguang.jinjuback.utils.DateUtils;
import com.aguang.jinjuback.utils.HtmlUtils;
import com.aguang.jinjuback.utils.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SpiderService {

    public final static Logger LOG = LoggerFactory.getLogger(AreaInfoService.class);

    public static final String NEIHAN_URL = "http://www.neihanshequ.com/";


    public static final String[] QIUSHIBAIKE_URL = new String[]
            {"https://www.qiushibaike.com/hot/", "https://www.qiushibaike.com/text/",
                    "https://www.qiushibaike.com/history/", "https://www.qiushibaike.com/textnew/"};
    public static final String BUDEJIE_URL = "http://www.budejie.com/text/";

    public static final String PENGFU_URL = "https://www.pengfu.com/xiaohua_%s.html";

    @Autowired
    private AdJinjuDao adJinjuDao;

    @Resource
    private TaskExecutor taskExecutor;

    /**
     * 内涵段子
     */
    public void doNeihan() {

        // 抓取网页
        Document document = HtmlUtils.getHtmlTextByUrl(NEIHAN_URL, "UTF-8");

        // 解析标签
        Elements elements = document.select("h1[class='title']");
        //Elements elements = document.select("div[class='upload-txt  no-mb']");

        if(elements == null)
            return;

        LOG.info(String.format("内涵段子[%s]", elements.size()));

        // 遍历标签元素
        for (Element element : elements) {
            Elements children = element.children();
            String text = children.first().ownText();

            createAdJinju(text, AdJinjuSourceConstant.NEIHAN);
        }
    }

    /**
     * 百思不得姐
     */
    public void doBudejie() {

        taskExecutor.execute(() -> {
            for (int i = 1; i <= 10; i++) {

                LOG.info("To GO: " + BUDEJIE_URL + i);

                // 抓取网页
                Document document = HtmlUtils.getHtmlTextByUrl(BUDEJIE_URL + i, "UTF-8");

                // 解析标签
                Elements elements = document.select("div[class='j-r-list-c-desc']");

                if(elements == null)
                    return;

                LOG.info(String.format("百思不得姐[%s]", elements.size()));

                // 遍历标签元素
                for (Element element : elements) {
                    Elements children = element.children();
                    String text = children.first().ownText();

                    createAdJinju(text, AdJinjuSourceConstant.BUDEJIE);
                }
            }
        });


    }

    /**
     * 糗事百科
     */
    public void doQiushibaike() {

        for (int i = 0; i < QIUSHIBAIKE_URL.length; i++) {

            LOG.info("To GO: " + QIUSHIBAIKE_URL[i]);

            // 抓取网页
            Document document = HtmlUtils.getHtmlTextByUrl(QIUSHIBAIKE_URL[i], "UTF-8");

            // 解析标签
            Elements elements = document.select("article");

            if(elements == null)
                return;

            LOG.info(String.format("糗事百科[%s]", elements.size()));

            // 遍历标签元素
            for (Element element : elements) {
                Elements children = element.children();

                Elements select = children.select("a[class='text']");

                if(select == null)
                    continue;

                Element first = select.first();

                if(first == null)
                    continue;

                String text = first.ownText();

                createAdJinju(text, AdJinjuSourceConstant.QIUSHIBAIKE);
            }
        }
    }

    /**
     * 捧腹
     */
    public void doPengfu() {

        for (int i = 1; i <= 10; i++) {

            LOG.info("To GO: " + String.format(PENGFU_URL, i));

            // 抓取网页
            Document document = HtmlUtils.getHtmlTextByUrl(String.format(PENGFU_URL, i), "UTF-8");

            // 解析标签
            Elements elements = document.select("div[class='content-img clearfix pt10 relative']");

            if (elements == null)
                return;

            LOG.info(String.format("捧腹[%s]", elements.size()));

            // 遍历标签元素
            for (Element element : elements) {
                String text = element.ownText();

                createAdJinju(text, AdJinjuSourceConstant.PENGFU);
            }
        }
    }

    /**
     * 创建后台金句
     * @param content
     */
    public void createAdJinju(String content, Integer source) {
        if(StringUtils.isBlank(content))
            return;

        try {
            Integer count = adJinjuDao.getAdJinjuByContent(content);

            // 如果该数据已存在，则不入库
            if(count!=null && count==0) {
                adJinjuDao.createAdJinju(content, source, DateUtils.getCurrentTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
