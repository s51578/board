package com.connect.brick.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.connect.brick.config.RequestMappingConstants;

public class UserLoginFailureHandler implements AuthenticationFailureHandler {
	
	private String badCredentials = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
	private String disaled = "계정이 비활성화되었습니다. 관리자에게 문의하세요.";
	private String credentialsExpired = "비밀번호 유효기간이 만료 되었습니다. 관리자에게 문의하세요.";
	private String locked = "계정이 잠겨있습니다. 관리자에게 문의하세요.";
	private String expired = "계정이 만료되었습니다. 관리자에게 문의하세요.";
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String errormsg = exception.getMessage();
		
		if (exception instanceof BadCredentialsException) {
			errormsg = badCredentials;
		} else if (exception instanceof InternalAuthenticationServiceException) {
			errormsg = badCredentials;
		} else if (exception instanceof DisabledException) {
			errormsg = disaled;
		} else if (exception instanceof CredentialsExpiredException) {
			errormsg = credentialsExpired;
		} else if (exception instanceof LockedException) {
			errormsg = locked;
		} else if (exception instanceof AccountExpiredException) {
			errormsg = expired;
		} 

		request.setAttribute("msg", errormsg);
		
		request.getRequestDispatcher(RequestMappingConstants._ACCESS_LOGIN_ERROR).forward(request, response);
	}
}
