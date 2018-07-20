package com.aguang.jinjuback.controllers;

import com.aguang.jinjuback.model.TestInfo;
import com.aguang.jinjuback.model.pojo.Result;
import com.aguang.jinjuback.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    /**
     * 列表
     * @return
     */
    @GetMapping("/list")
    public Result list() {
        Result result = new Result();
        result.setCode(0);
        result.setData(testService.list());
        return result;
    }

    @GetMapping("/create")
    public Result create() {

        TestInfo test = new TestInfo();
        test.setName("haha");

        testService.create(test);

        Result result = new Result();
        result.setCode(0);
        result.setData("OK");
        return result;
    }

    @GetMapping("/create2")
    public Result create2() {
        testService.create2();
        return null;
    }

    @GetMapping("/create3")
    public Result create3() {
        testService.create3();
        return null;
    }
}
