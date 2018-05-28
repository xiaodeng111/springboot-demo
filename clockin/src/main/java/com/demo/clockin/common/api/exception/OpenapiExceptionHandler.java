package com.demo.clockin.common.api.exception;


import com.demo.clockin.common.lang.WebUtil;
import com.demo.clockin.common.api.ApiError;
import com.demo.clockin.common.lang.WebUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 全局异常处理
 *@desc: 使用 @ontrollerAdvice (或@RestControllerAdvice)， @ExceptionHandler 注解实现统一异常处理；
 *@author: dengrq
 *@time: 2018/5/25
 */
@ControllerAdvice
public class OpenapiExceptionHandler implements HandlerExceptionResolver {

	@ExceptionHandler(value = OpenapiException.class)
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		if (ex instanceof OpenapiException) {
			OpenapiException exception = (OpenapiException) ex;
			ApiError error = new ApiError(0, exception.getDesc(), String.valueOf(exception.getNum()));
			try {
				WebUtil.outPutJsonResult(response, Charset.defaultCharset(), error);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	} 

}
