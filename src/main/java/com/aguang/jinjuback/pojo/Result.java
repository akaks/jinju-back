package com.aguang.jinjuback.pojo;

public class Result {

    private Integer code;
    private Object data;
    private String message;

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

    public void setSuccess(Object data,String message){
        this.code = 0;
        this.data = data;
        this.message = message;
    }

    public void setError(Object data,String message){
        this.code = -1;
        this.data = data;
        this.message = message;
    }

}
