package com.jt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysRole;
import com.jt.sys.service.SysRoleService;

@Controller
@RequestMapping("/role/")
public class SysRoleContorller {
	@RequestMapping("doRoleListUI")
	public String doRoleListUI(){
		return "sys/role_list";
	}
	@RequestMapping("doRoleEditUI")
	public String doRoleEditUI(){
		return "sys/role_edit";
	}
	@Autowired
	private SysRoleService sysRoleService;
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObject(String username,Integer pageCurrent){
		PageObject pageObject = sysRoleService.findPageObjects(username, pageCurrent);
		return new JsonResult(pageObject);
	}
	@RequestMapping("doInsertObject")
	@ResponseBody
	public JsonResult doInsertObject(SysRole sysRole,Integer[] menuIds){
		int row = sysRoleService.insertObject(sysRole, menuIds);
		return new JsonResult("save ok");
	}
}
