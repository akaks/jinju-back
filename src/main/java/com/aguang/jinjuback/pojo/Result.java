package com.aguang.jinjuback.pojo;

public class Result {

    public final static Integer OK = 0;
    public final static Integer NG = -1;

    private Integer code;
    private Object data;
    private String message;

    public Result() {
        this.code = OK;
    }

    public Result(Integer code) {
        this.code = code;
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

}
