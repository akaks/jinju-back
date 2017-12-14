package com.aguang.jinjuback.controllers;

import com.aguang.jinjuback.pojo.Jinju;
import com.aguang.jinjuback.pojo.Result;
import com.aguang.jinjuback.services.JinjuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class JinjuController {

    @Autowired
    private JinjuService jinjuService;

    @PostMapping("/createJinju")
    public Result createJinju(@RequestBody Jinju jinju) {
        return jinjuService.createJinju(jinju);
    }

    @GetMapping("/getJinju/{id}")
    public Jinju getJinju(@PathVariable("id") int id) {
        return jinjuService.getJinju(id);
    }

    @GetMapping("/getJinjuList")
    public Result getJinjuList(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
        return jinjuService.getJinjuList(pageIndex, pageSize);
    }
}
