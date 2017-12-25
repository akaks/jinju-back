package com.aguang.jinjuback.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class JinjuInfo {

    // 金句id
    private int jinjuId;

    // 金句类型
    private int type;

    // 金句内容
    private String content;

    // 点赞总数
    private int upVoteCount;

    // 点踩总数
    private int downVoteCount;

    // 收藏总数
    private int collectCount;

    // 点赞或踩  1：赞、2：踩、其它
    private int upOrDownVote;

    // 是否收藏
    @JSONField(name = "isCollect")
    private boolean isCollect;

    // 用户id
    private int userId;

    // 用户名
    private String username;

    // 金句创建时间
    private long createTime;

    // 金句更新时间
    private long updateTime;

    public int getJinjuId() {
        return jinjuId;
    }

    public void setJinjuId(int jinjuId) {
        this.jinjuId = jinjuId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUpVoteCount() {
        return upVoteCount;
    }

    public void setUpVoteCount(int upVoteCount) {
        this.upVoteCount = upVoteCount;
    }

    public int getDownVoteCount() {
        return downVoteCount;
    }

    public void setDownVoteCount(int downVoteCount) {
        this.downVoteCount = downVoteCount;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public int getUpOrDownVote() {
        return upOrDownVote;
    }

    public void setUpOrDownVote(int upOrDownVote) {
        this.upOrDownVote = upOrDownVote;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
