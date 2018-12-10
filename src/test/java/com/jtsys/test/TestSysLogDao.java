package com.jtsys.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.jt.sys.dao.SysLogDao;
import com.jt.sys.entity.SysLog;

public class TestSysLogDao extends TestBase {
	@Test
	public void testGetRowCount1() {
		SysLogDao dao = context.getBean("sysLogDao", SysLogDao.class);
		System.out.println(dao.getClass().getName());
		int rowCount = dao.getRowCount("admin");
		System.out.println("rowCount=" + rowCount);
	}
	@Test
	public void testGetRowCount2(){
		SqlSessionFactory factory = context.getBean("sqlSessionFactory",
				SqlSessionFactory.class);
		SqlSession session = factory.openSession();
		String stmt="com.jt.sys.dao.SysLogDao.getRowCount";
		int rowCount = session.selectOne(stmt,"admin");
		System.out.println(rowCount);
	}
}
