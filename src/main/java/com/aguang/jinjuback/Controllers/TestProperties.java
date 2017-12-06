package com.aguang.jinjuback.Controllers;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "info")
public class TestProperties {

    private String name;
    private Integer age;
    private Integer height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String toString() {
        return "JinJuProperties{" +
                "name='" + this.name + '\'' +
                ",age='" + this.age + '\'' +
                ",height='" + this.height +
                '}';
    }

}
