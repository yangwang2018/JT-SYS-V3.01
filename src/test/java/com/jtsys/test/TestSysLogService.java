package com.jtsys.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysLog;
import com.jt.sys.service.SysLogService;

public class TestSysLogService extends TestBase {
	@Test
	public void testFindPageObjects() {
		// 1.获取service对象
		SysLogService logService = context.getBean("sysLogServiceImpl", SysLogService.class);
		// 测试logService值是否不等于空
		Assert.assertNotEquals(null, // unexpected(不期望出现的值)
				logService);// actual(实际值)
		// 2.调用service对象方法
		PageObject<SysLog> po = logService.findPageObjects("admin",2);
		// 3.输出结果
		List<SysLog> records = po.getRecords();	 
		for (SysLog sysLog : records) {
			Integer id = sysLog.getId();
			String ip = sysLog.getIp();
			String method = sysLog.getMethod();
			String operation = sysLog.getOperation();
			String params = sysLog.getParams();
			Long time = sysLog.getTime();
			String username = sysLog.getUsername();
			System.out.println("{"+"\""+"id"+"\""+":"+"\""+id+"\""+","
								+"\""+"ip"+"\""+":"+"\""+ip+"\""+","
								+"\""+"method"+"\""+":"+"\""+method+"\""+","
								+"\""+"operation"+"\""+":"+"\""+operation+"\""	
								+"}"
					);		
		}
	}
	
}
