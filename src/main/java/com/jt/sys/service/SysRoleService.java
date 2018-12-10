package com.jt.sys.service;

import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysRole;

public interface SysRoleService {
	PageObject findPageObjects(String username,Integer pageCurrent);
	int insertObject(SysRole sysRole,Integer[] menuIds);
}
