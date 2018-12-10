package com.jt.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jt.common.anno.DataFilter;
import com.jt.common.anno.RequiresLog;
import com.jt.common.exception.ServiceException;
import com.jt.common.vo.Node;
import com.jt.sys.dao.SysMenuDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.entity.SysMenu;
import com.jt.sys.service.SysMenuService;
@Service
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	SysMenuDao sysMenuDao;
	public List<Map<String, Object>> findObjects() {	
		List<Map<String, Object>> list = sysMenuDao.findObjects();
		return list;
	}
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@RequiresLog("菜单删除")
	@DataFilter
	public int doDeleteObject(Integer id) {
		int count = sysMenuDao.getChildCount(id);
		if(count>1) 
			throw new ServiceException("请先删除子菜单");
		int row =
				sysMenuDao.doDeleteObject(id);
		if(row==0) throw new ServiceException("可能记录已经不存在");
		sysRoleMenuDao.doDeleteObject(id);
		return row;
	}
	public List<Node> findZtreeMenuNodes() {
		List<Node> list = sysMenuDao.findZtreeMenuNodes();
		return list;
	}
	public int doInsertObject(SysMenu sysMenu){
		int row = sysMenuDao.doInsertObject(sysMenu);
		return row;
	}

}
