package com.jt.common.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.anno.RequiresLog;
import com.jt.common.utils.IPUtils;
import com.jt.sys.dao.SysLogDao;
import com.jt.sys.entity.SysLog;
@Service
@Aspect//切面
public class SysLogAspect {
	//切入点
	//@Pointcut("bean(sysUserServiceImpl)")//这个bean所有的业务执行时将执行如下around方法
	@Pointcut("@annotation(com.jt.common.anno.RequiresLog)")//没有注解不用写入日志
	public void doLogPointCut(){
		
	}
	@Around("doLogPointCut()")//方法执行前后都要执行的称为环绕通知
	public Object around(ProceedingJoinPoint jp) throws Throwable{//该方法的切入点变为有RequiresLog这个注解的方法
		long t1=System.currentTimeMillis();
		Object result = jp.proceed();//调用目标方法
		long t2=System.currentTimeMillis();
		saveObject(jp,t2-t1);
		return result; 
	}
	public void saveObject(ProceedingJoinPoint jp, long time) throws Exception{
		Class<? extends Object> targetCls = jp.getTarget().getClass();
		MethodSignature ms = (MethodSignature)jp.getSignature();
		Method method = targetCls.getDeclaredMethod(ms.getName(),ms.getParameterTypes());
		String methodName=targetCls.getName()+"."+method.getName();
		System.out.println(methodName+".totalTime="+time);
		RequiresLog requires = method.getDeclaredAnnotation(RequiresLog.class);//获取方法上定义的注解
		String operation = requires.value();
		System.out.println(operation);
		Object[] args = jp.getArgs();
		//5.获取操作该方法的用户，通过shiro的 共同
		String ip = IPUtils.getIpAddr();
		//封装操作数据
		SysLog log=new SysLog();
		log.setParams(Arrays.toString(args));
		log.setOperation(operation);	
		log.setCreatedTime(new Date());
		log.setMethod(methodName);
		log.setIp(ip);
		log.setTime(time);
		sysLogDao.insertObject(log);
	}
	@Autowired
	private SysLogDao sysLogDao;
	
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          