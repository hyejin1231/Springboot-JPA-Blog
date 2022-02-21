package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // Exception이 발생하면 모두 이리로 들어옴 
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value = IllegalArgumentException.class)
	public String handleIllArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
}
