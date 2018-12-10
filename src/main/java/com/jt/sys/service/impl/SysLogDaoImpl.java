package com.jt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.jt.sys.dao.SysLogDao;
import com.jt.sys.entity.SysLog;
@Repository("sysLogDaoImpl")//告诉spring创建一个sysLogDaoImpl的实现类
public class SysLogDaoImpl implements SysLogDao {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	public List<SysLog> findPageObjects(String username, Integer startIndex, Integer pageSize) {
		SqlSession session = sqlSessionFactory.openSession();
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("usernmae",username);
		map.put("startIndex",startIndex);
		map.put("pageSize",pageSize);
		System.out.println("111");
		List<SysLog> list = session.selectList("com.jt.sys.dao.SysLogDao.findPageObjects",map);
		return list;
	}

	public int getRowCount(String username) {		
	SqlSession session = sqlSessionFactory.openSession();
	Map<String,Object> map= new HashMap<String,Object>();
	map.put("username",username);//相当于@Param("username")
	int rowConut=session.selectOne("com.jt.sys.dao.SysLogDao.getRowCount",map);
		return rowConut;
	}

	public int deleteObjects(Integer... ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertObject(SysLog sysLog) {
		// TODO Auto-generated method stub
		return 0;
	}

}
