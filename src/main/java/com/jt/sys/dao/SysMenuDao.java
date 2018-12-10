package com.jt.sys.dao;

import java.util.List;
import java.util.Map;

import com.jt.common.vo.Node;
import com.jt.sys.entity.SysMenu;

public interface SysMenuDao {
	//行记录放Map中是最次的选择
	List<Map<String,Object>> findObjects();
	/**
	 * 统计该id对应的菜单的子元素的个数
	 * @param id
	 * @return
	 */
	int getChildCount(Integer id);
	/**
	 * 按id删除对应的菜单
	 * @param id
	 * @return
	 */
	int doDeleteObject(Integer id);
	/**
	 * zTree树
	 * @return
	 */
	List<Node> findZtreeMenuNodes();
	/**
	 * 添加菜单的保存
	 * @param sysMenu
	 * @return
	 */
	int doInsertObject(SysMenu sysMenu);
	
}
