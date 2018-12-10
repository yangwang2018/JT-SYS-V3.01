package com.jtsys.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.jt.sys.dao.SysMenuDao;

public class TestSysMenuDao extends TestBase {
	@Test
	public void findObjects(){
		SysMenuDao sysMenuDao = context.getBean("sysMenuDao",SysMenuDao.class);
		List<Map<String, Object>> list = sysMenuDao.findObjects();
		System.out.println(list);
	}
}
