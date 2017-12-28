package com.aguang.jinjuback.model;

/**
 * 金句表（jinju）
 */
public class Jinju {

    /* 主键 */
    private int jinjuId;

    /* 类型 */
    private int type;

    /* 内容 */
    private String content;

    /* 点赞总数 */
    private int upVoteCount;

    /* 点踩总睡 */
    private int downVoteCount;

    /* 收藏总数 */
    private int collectCount;

    /* 用户Id */
    private int userId;

    /* 创建时间 */
    private int createTime;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }
}
