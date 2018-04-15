package com.aguang.jinjuback.config.exception;

/**
 * 自定义异常类
 */
public class CustomException extends RuntimeException {

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }
}
