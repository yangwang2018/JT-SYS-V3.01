package com.jt.common.aspect;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class DataFilterAspect {
	@Pointcut("@annotation(com.jt.common.anno.DataFilter)")
	public void doFilterPointCut(){}
	
/*	@Around("doFilterPointCut()")
	public Object aroundMethod(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("DataFilter");
		Object[] args = jp.getArgs();
		if(args[0]==null) throw new IllegalArgumentException("非法参数");
		Object obj = jp.proceed();
		return obj;
	}	*/
	@Before("doFilterPointCut()")//目标方法执行之前执行该方法，对项目中的数据做统一的处理
	public void beforeMethod(JoinPoint jp) throws Throwable {
		System.out.println("DataFilter");
	/*	Object[] args = jp.getArgs();
		if(args[0]==null) */throw new IllegalArgumentException("非法参数");
	}	
}
