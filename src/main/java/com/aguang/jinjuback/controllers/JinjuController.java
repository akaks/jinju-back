package com.aguang.jinjuback.controllers;

import com.aguang.jinjuback.pojo.Jinju;
import com.aguang.jinjuback.services.JinjuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JinjuController {

    @Autowired
    private JinjuService jinjuService;

    @PostMapping("/createJinju")
    public void createJinju(Jinju jinju) {
        jinjuService.createJinju(jinju);
    }

    @GetMapping("/getJinju/{id}")
    public Jinju getJinju(@PathVariable("id") Integer id) {
        return jinjuService.getJinju(id);
    }

    @GetMapping("/getJinjuList")
    public List<Jinju> getJinjuList(@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
        return jinjuService.getJinjuList(pageIndex, pageSize);
    }
}
