package com.jt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysRoleDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.entity.SysLog;
import com.jt.sys.entity.SysRole;
import com.jt.sys.service.SysRoleService;
@Service
@Transactional(readOnly=false,rollbackFor=Throwable.class)
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Transactional(readOnly=true,isolation=Isolation.READ_COMMITTED)
	public PageObject findPageObjects(String username, Integer pageCurrent) {
		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("当前页码不正确");
		// 2.基于条件查询总记录数
		// 2.1) 执行查询
		int rowCount = sysRoleDao.getRowCount(username);
		// 2.2) 验证查询结果，假如结果为0不再执行如下操作
		if (rowCount == 0)
			throw new ServiceException("系统没有查到对应记录");
		// 3.基于条件查询当前页记录(pageSize定义为2)
		// 3.1)定义pageSize
		int pageSize = 5;
		// 3.2)计算startIndex
		int startIndex = (pageCurrent - 1) * pageSize;
		// 3.3)执行当前数据的查询操作
		List<SysRole> records = sysRoleDao.findPageObjects(username, startIndex, pageSize);
		// 4.对分页信息以及当前页记录进行封装
		// 4.1)构建PageObject对象
		PageObject pageObject = new PageObject();
		// 4.2)封装数据
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageCount((rowCount - 1) / pageSize + 1);
		// 5.返回封装结果。
		return pageObject;
	}
	public int insertObject(SysRole sysRole, Integer[] menuIds) {
		if(sysRole==null) throw new ServiceException(" 保存的数据不能为空");
		if(StringUtils.isEmpty(sysRole.getName())) throw new ServiceException("角色名不能为空");
		if(menuIds==null||menuIds.length==0) throw new ServiceException("必须为角色赋予权限");
		int rows = sysRoleDao.insertObject(sysRole);
		sysRoleMenuDao.insertObject(sysRole.getId(), menuIds); 
		return rows;
	}
}
