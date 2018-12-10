package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.entity.SysRole;

public interface SysRoleDao {
	List<SysRole> findPageObjects(@Param("username")String username,
								  @Param("startIndex")Integer startIndex,
								  @Param("pageSize")Integer pageSize);
	int getRowCount(@Param("username")String username);
	int insertObject(SysRole sysRole);
}
