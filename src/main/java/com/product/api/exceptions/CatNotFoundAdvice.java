package com.product.api.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class CatNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(CatNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String catNotFoundHandler(CatNotFoundException ex) {
		return ex.getMessage();
	}
}
