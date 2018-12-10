package com.jtsys.test;

import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.junit.Test;

import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysRole;
import com.jt.sys.service.SysRoleService;

public class TestSysRoleService extends TestBase {
	@Test
	public void doInsertObject(){
		SysRoleService sysRoleService = context.getBean("sysRoleServiceImpl",SysRoleService.class);
		SysRole sysRole = new SysRole();
		sysRole.setName("总裁");
		sysRole.setNote("牛逼");
		int row = sysRoleService.insertObject(sysRole,new Integer[]{0});
		System.out.println(row);
		PageObject list = sysRoleService.findPageObjects(null,1);
		List<SysRole> records = list.getRecords();
		for (SysRole sysRole2 : records) {
			System.out.println(sysRole2);
		}
	}
}
