package com.aguang.jinjuback.controllers.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    private String jsonObj = "{\n" +
            "    \"button\": [\n" +
            "        {\n" +
            "            \"name\": \"Test\", \n" +
            "            \"sub_button\": [\n" +
            "                {\n" +
            "                    \"type\": \"click\", \n" +
            "                    \"name\": \"Test\", \n" +
            "                    \"key\": \"super_rebate\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"click\", \n" +
            "                    \"name\": \"Test\", \n" +
            "                    \"key\": \"wei_shop\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }, \n" +
            "        {\n" +
            "            \"name\": \"Test\", \n" +
            "            \"sub_button\": [\n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"Test\", \n" +
            "                    \"url\": \"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2287dbde2e640908&redirect_uri=http://bt18088883.iok.la/page/quan_tmallchaoshi&response_type=code&scope=snsapi_userinfo&state=12580&connect_redirect=1#wechat_redirect\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"click\", \n" +
            "                    \"name\": \"Test\", \n" +
            "                    \"key\": \"didi_discount_coupon\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"click\", \n" +
            "                    \"name\": \"Test\", \n" +
            "                    \"key\": \"jd_discount_coupon\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"type\": \"view\", \n" +
            "            \"name\": \"我的\", \n" +
            "            \"url\": \"http://47.100.177.183/\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Autowired
    RestTemplate restTemplate;

    public String hiService() {
        return restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx2287dbde2e640908&secret=95f8e3156184945420033ed8889ca37c",String.class);
    }



    public String createMenu() {

        String appid = "6_1JQewpalkTlZi2mQV5V5VdpWA33P4YmumG8JPA9mi9CjCzjJ2FI5YB05WQ1N5Ab2-u5jzc6yDGO-IwpgrdaT6D5TJLu7EEYMSP-E-9uhZKKkUtELNUgW-7VsUxRJlrW25QcGykF_iw0edAZIXEEaAGATAA";

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

//        JSONObject jsonObj = JSONObject.fromObject(params);

//        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);

        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj, headers);

        return restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+appid, formEntity, String.class);
    }

}