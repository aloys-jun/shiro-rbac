package com.aloys.rbac.Exception;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
	
	
	@ExceptionHandler(value=UnauthorizedException.class)
	public String unauthorizedHandler(){
		return "403";
	}
	
	@ExceptionHandler(value=AuthenticationException.class)
	public String authenticationHandler(){
		
		return "error";
	}

}
