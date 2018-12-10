package com.jt.common.web;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
@ControllerAdvice
public class GlobalExceptionHandler {
		@ResponseBody
		@ExceptionHandler(RuntimeException.class)
		public JsonResult doHandler(RuntimeException e){
			e.printStackTrace();
			return new JsonResult(e);
		}
		@ResponseBody
		@ExceptionHandler(AuthenticationException.class)
		public JsonResult doHandlerShiroException(ShiroException e){
			e.printStackTrace();
			JsonResult r = new JsonResult();
			r.setState(0);
			if(e instanceof UnknownAccountException ){
				r.setMessage("用户不存在");
			}else if(e instanceof LockedAccountException){
				r.setMessage("用户已被禁用");
			}else if(e instanceof IncorrectCredentialsException ){
				r.setMessage("密码不正确");
			}
			return r;
		}
}
