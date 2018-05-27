package com.aguang.jinjuback.controllers.spider;

import com.aguang.jinjuback.controllers.base.BaseController;
import com.aguang.jinjuback.model.pojo.Result;
import com.aguang.jinjuback.services.spider.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spider")
public class SpiderController extends BaseController {

    @Autowired
    private SpiderService spiderService;

    /**
     * 内涵段子
     * @return
     */
    @GetMapping("/neihan")
    public Result neihan() {
        spiderService.doNeihan();
        return Result.ok();
    }

    /**
     * 百思不得姐
     * @return
     */
    @GetMapping("/budejie")
    public Result budejie() {
        spiderService.doBudejie();
        return Result.ok();
    }

    /**
     * 捧腹网
     * @return
     */
    @GetMapping("/pengfu")
    public Result pengfu() {
        spiderService.doPengfu();
        return Result.ok();
    }

    /**
     *
     * @return
     */
    @GetMapping("/douluo")
    public Result doDouluo() {
        spiderService.doDouluo();
        return Result.ok();
    }
}
