package com.jt.sys.dao;

import java.util.List;
import java.util.Map;

import com.jt.sys.entity.SysDept;

public interface SysDeptDao {
	List<Map<String,Object>> findObjects();
	SysDept findById(Integer id);
}
