package com.aguang.jinjuback.model.pojo;

/**
 * 返回数据类
 */
public class Result {

    public final static Integer OK = 0;
    public final static Integer NG = -1;

    /* 返回编号 */
    private Integer code;

    /* 返回消息 */
    private String message;

    /* 数据 */
    private Object data;

    public Result() {}

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(String message){
        this.code = OK;
        this.message = message;
    }

    public void setSuccess(Object data,String message){
        this.code = OK;
        this.data = data;
        this.message = message;
    }

    public void setError(String message){
        this.code = NG;
        this.message = message;
    }

    public void setError(Object data,String message){
        this.code = NG;
        this.data = data;
        this.message = message;
    }

    public static Result ok() {
        return new Result(OK, "操作成功");
    }

    public static Result error() {
        return new Result(NG, "操作失败");
    }

    public static Result error(String message) {
        return new Result(NG, message);
    }
}
