package com.aguang.jinjuback.controllers.dingtalk;

import com.aguang.jinjuback.controllers.base.BaseController;
import com.aguang.jinjuback.model.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dingtalk")
public class DingTalkController extends BaseController {

    @Autowired
    private DingTalkRestClient dingTalkRestClient;

    /**
     * 发送钉钉消息
     * @return
     */
    @GetMapping("/sendMessage")
    public Result sendMessage() {
        DingTalkResult result = dingTalkRestClient.sendDingTalk();
        System.out.println(result);
        return Result.ok();
    }
}
