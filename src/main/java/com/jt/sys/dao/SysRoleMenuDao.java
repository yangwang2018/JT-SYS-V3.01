package com.jt.sys.dao;

import org.apache.ibatis.annotations.Param;

public interface SysRoleMenuDao {
	int doDeleteObject(Integer id);
	int insertObject(
			@Param("roleId")Integer roleId,
			@Param("menuIds")Integer[] menuIds);

}
