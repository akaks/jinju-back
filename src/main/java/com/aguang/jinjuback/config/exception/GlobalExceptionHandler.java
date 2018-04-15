package com.aguang.jinjuback.config.exception;

import com.aguang.jinjuback.model.pojo.Result;
import com.aguang.jinjuback.services.AreaInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常拦截器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	public final static Logger logger = LoggerFactory.getLogger(AreaInfoService.class);

	/**
	 * 业务异常.
	 */
	@ExceptionHandler(CustomException .class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Result businessException(CustomException e) {
		logger.error("业务异常={}", e.getMessage(), e);
		return Result.error(e.getMessage());
	}

	/**
	 * 全局异常.
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Result exception(Exception e) {
		logger.info("保存全局异常信息 ex={}", e.getMessage(), e);
		return Result.error("系统错误，请稍后再试");
	}
}
