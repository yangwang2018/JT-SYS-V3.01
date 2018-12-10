package com.jtsys.test;


import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBase {
	protected ClassPathXmlApplicationContext context;
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("spring-configs.xml");	
	}	
	@After
	public void close(){//释放资源(@Test之后)
		context.close();
	}
}
