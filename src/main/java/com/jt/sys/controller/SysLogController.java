package com.jt.sys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysLog;
import com.jt.sys.service.SysLogService;
import com.jt.sys.service.impl.SysLogServiceImpl;
@Controller
@RequestMapping("/log/")
public class SysLogController {
	@Autowired 
	private SysLogService sysLogService;
	
	//Ajax请求获取到的是界面写String
	@RequestMapping("doLogListUI.do")
	public String doLogListUI(){
		return "sys/log_list";
	}
	//Ajax请求获取获取数据的路径写JsonResult
	@RequestMapping("doFindPageObjects.do")
	@ResponseBody
	public JsonResult doFindPageObjects(String name, 
			Integer pageCurrent) {
		PageObject<SysLog> pageObject = 
				sysLogService.findPageObjects(name, pageCurrent);		
		return new JsonResult(pageObject);		
	}
	@ResponseBody
	@RequestMapping("doDeleteObjects")
	public JsonResult doDeleteObjects(Integer...ids) {
		sysLogService.deleteObjects(ids);		
		return new JsonResult("delete ok");	
	}
	
}
