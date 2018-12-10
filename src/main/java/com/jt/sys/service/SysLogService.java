package com.jt.sys.service;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysLog;

public interface SysLogService {
	 /** 通过此方法实现分页查询操作
     * @param name 基于条件查询时的参数名
     * @param pageCurrent 当前的页码值
     * @return 当前页记录+分页信息
     */
	 PageObject findPageObjects(String name,Integer pageCurrent);
	 int deleteObjects(Integer...ids);
}
