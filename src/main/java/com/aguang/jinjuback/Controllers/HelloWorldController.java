package com.aguang.jinjuback.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Value("${info.name}")
    private String name;

    @Value("${info.age}")
    private Integer age;

    @Value("${info.height}")
    private Integer height;

    @Autowired
    private TestProperties testProperties;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello JinJu";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello api";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info() {
        return this.name + "-" + this.age + "-" + this.height;
    }

    @RequestMapping(value = "/properties", method = RequestMethod.GET)
    public TestProperties getTestProperties() {
        return this.testProperties;
    }
}
