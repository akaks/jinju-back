package com.aguang.jinjuback.controllers;

import com.aguang.jinjuback.model.Jinju;
import com.aguang.jinjuback.pojo.Result;
import com.aguang.jinjuback.services.JinjuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jinju")
public class JinjuController extends BaseController {

    @Autowired
    private JinjuService jinjuService;

    /**
     * 创建金句
     * @param jinju
     * @return
     */
    @PostMapping("/create")
    public Result createJinju(@RequestBody Jinju jinju) {
        return jinjuService.createJinju(jinju);
    }

    @GetMapping("/getJinju/{id}")
    public Jinju getJinju(@PathVariable("id") int id) {
        return jinjuService.getJinju(id);
    }

    @GetMapping("/getJinjuList")
    public Result getJinjuList(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
        return jinjuService.getJinjuList(pageIndex, pageSize, getUserId());
    }

    @GetMapping("/list")
    public List<Jinju> getList() {
        return jinjuService.getList();
    }

    /**
     * 点赞
     * @return
     */
    @GetMapping("/upVote/{jijuId}")
    public Result upVote(@PathVariable("jijuId") Integer jijuId) {
        return jinjuService.upVote(jijuId, getUserId());
    }

    /**
     * 取消点赞
     * @return
     */
    @GetMapping("/cancelUpVote/{jijuId}")
    public Result cancelUpVote(@PathVariable("jijuId") Integer jijuId) {
        return jinjuService.cancelUpVote(jijuId, getUserId());
    }

    /**
     * 点踩
     * @return
     */
    @GetMapping("/downVote/{jijuId}")
    public Result downVote(@PathVariable("jijuId") Integer jijuId) {
        return jinjuService.downVote(jijuId, getUserId());
    }

    /**
     * 取消点踩
     * @return
     */
    @GetMapping("/cancelDownVote/{jijuId}")
    public Result cancelDownVote(@PathVariable("jijuId") Integer jijuId) {
        return jinjuService.cancelDownVote(jijuId, getUserId());
    }

    /**
     * 点赞变点踩
     * @return
     */
    @GetMapping("/upVoteToDown/{jijuId}")
    public Result upVoteToDown(@PathVariable("jijuId") Integer jijuId) {
        return jinjuService.upVoteToDown(jijuId, getUserId());
    }

    /**
     * 点踩变点赞
     * @return
     */
    @GetMapping("/downVoteToUp/{jijuId}")
    public Result downVoteToUp(@PathVariable("jijuId") Integer jijuId) {
        return jinjuService.downVoteToUp(jijuId, getUserId());
    }

    /**
     * 收藏
     * @return
     */
    @GetMapping("/collect/{jijuId}")
    public Result collect() {
        return null;
    }

    /**
     * 取消收藏
     * @return
     */
    @GetMapping("/cancelCollect/{jijuId}")
    public Result cancelCollect() {
        return null;
    }
}
