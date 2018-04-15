package com.aguang.jinjuback.controllers.dingtalk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DingTalkRestClient {

    private String WEB_HOOK = "https://oapi.dingtalk.com/robot/send?access_token=ab7e015fd985ed8eeec9ccd0cff288b240248f48102b9c9d1216e1cfa60364c3";

    private String jsonObj = "{\n" +
            "    \"msgtype\": \"text\", \n" +
            "    \"text\": {\n" +
            "        \"content\": \"我就是我,是不一样的烟火\"\n" +
            "    }, \n" +
            "    \"at\": {\n" +
            "        \"atMobiles\": [\n" +
            "            \"156xxxx8827\", \n" +
            "            \"189xxxx8325\"\n" +
            "        ], \n" +
            "        \"isAtAll\": false\n" +
            "    }\n" +
            "}";

    @Autowired
    RestTemplate restTemplate;

    /**
     * 发送钉钉消息
     * @return
     */
    public DingTalkResult sendDingTalk() {

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

//        JSONObject jsonObj = JSONObject.fromObject(params);
//        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);

        HttpEntity<String> formEntity = new HttpEntity<>(jsonObj, headers);

        DingTalkResult result = restTemplate.postForObject(WEB_HOOK, formEntity, DingTalkResult.class);
        return result;
    }

}