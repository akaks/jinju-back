package com.aguang.jinjuback.pojo;

public class Jinju {
    private int jinju_id;
    private int user_id;
    private String content;
    private int type;
    private Long create_time;
    private String username;

    public int getJinju_id() {
        return jinju_id;
    }

    public void setJinju_id(int jinju_id) {
        this.jinju_id = jinju_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
