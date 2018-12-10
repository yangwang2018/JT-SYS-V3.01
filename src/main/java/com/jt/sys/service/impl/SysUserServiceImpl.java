package com.jt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.jt.common.anno.RequiresLog;
import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageObject;
import com.jt.common.vo.SysUserDeptResult;
import com.jt.sys.dao.SysUserDao;
import com.jt.sys.service.SysUserService;
@Service
public class SysUserServiceImpl implements SysUserService{
	@Autowired
	private SysUserDao sysUserDao;
	@RequiresLog("查询")
	public PageObject<SysUserDeptResult> findPageObjects(String username, Integer pageCurrent) {
		if(pageCurrent==null || pageCurrent<1)throw new ServiceException("输入页码有误");
		int rowCount = sysUserDao.getRowCount(username);
		if(rowCount==0) throw new ServiceException("系统没有查询到记录");
		int pageSize=5;
		int startIndex=(pageCurrent - 1) * pageSize;
		List<SysUserDeptResult> records = sysUserDao.findPageObjects(username, startIndex, pageSize);
		PageObject pageObject = new PageObject();
		pageObject.setRecords(records);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setPageCount((rowCount - 1) / pageSize + 1);
		return pageObject;
	}
	public int validById(Integer id, Integer valid, String modifiedUser) {
		if(id==null || id<=0) throw new ServiceException("参数不合法,id="+id);
		if(valid!=1&&valid!=0)throw new ServiceException("参数不合法,valid="+valid);
		if(StringUtils.isEmpty(modifiedUser))throw new ServiceException("修改用户不能为空");
		int rows=0;
		try{
			rows=sysUserDao.validById(id, valid, modifiedUser);
		}catch(Throwable e){
			e.printStackTrace();
			throw new ServiceException("底层正在维护");
		}
		if(rows==0) throw new ServiceException("此记录可能已经不存在");
		return rows;
	}

}
