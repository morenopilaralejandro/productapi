package com.product.api.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class CntcstateNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(CntcstateNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String cntcstateNotFoundExceptionNotFoundHandler(CntcstateNotFoundException ex) {
		return ex.getMessage();
	}
}
