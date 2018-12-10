package com.jt.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.sys.dao.SysDeptDao;
import com.jt.sys.service.SysDeptService;

@Service
public class SysDeptServiceImpl implements SysDeptService{
	@Autowired
	private SysDeptDao sysDeptDao;

	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> list = sysDeptDao.findObjects();
		return list;
	}
}
