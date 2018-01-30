package com.aguang.jinjuback.controllers.chat;

import com.aguang.jinjuback.pojo.Result;
import com.aguang.jinjuback.services.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取游客Id
     * @return
     */
    @GetMapping("/getVisitorId")
    public Result getVisitorId() {
        Result result = new Result();

        try {
            result.setSuccess(chatService.nextVisitorId(), "获取成功");
        } catch (Exception e) {
            result.setError("获取失败");
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 获取历史消息
     * @return
     */
    @GetMapping("/getHistoryMessage")
    public Result getHistoryMessage(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                    @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

        Result result = new Result();

        if(pageIndex <1) {
            result.setError("pageIndex必须大于0");
            return result;
        }

        try {
            List<String>  chatMessages = chatService.getHistoryMessage(pageIndex, pageSize);
            result.setSuccess(chatMessages, "获取成功");
        } catch (Exception ex) {
            result.setError("获取失败");
            ex.printStackTrace();
        }

        return result;
    }
}
