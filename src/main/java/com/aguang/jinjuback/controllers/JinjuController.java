package com.aguang.jinjuback.controllers;

import com.aguang.jinjuback.pojo.Jinju;
import com.aguang.jinjuback.services.JinjuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JinjuController {

    @Autowired
    private JinjuService jinjuService;

    @PostMapping("/createJinju")
    public void createJinju(Jinju jinju){
        jinjuService.createJinju(jinju);
    }

    @GetMapping("/getJinju/{id}")
    public Jinju getJinju(@PathVariable("id") Integer id){
        return jinjuService.getJinju(id);
    }
}
