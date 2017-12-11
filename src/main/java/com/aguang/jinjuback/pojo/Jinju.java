package com.aguang.jinjuback.pojo;

public class Jinju {
    private Integer jinju_id;
    private Integer user_id;
    private String content;
    private Integer type;
    private Long create_time;

    public Integer getJinju_id() {
        return jinju_id;
    }

    public void setJinju_id(Integer jinju_id) {
        this.jinju_id = jinju_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }
}
