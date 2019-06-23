package com.scan.sgindustry.service;

import com.scan.sgindustry.entity.MeterageNotice;
import com.scan.sgindustry.tools.BaseService;

/**
 * 继承通用service接口
 * @author fx
 *
 * @param 
 */
public interface MeterageNoticeService extends BaseService<MeterageNotice> {
	
	/**
	 * 通过计量通知单编号的后6位进行查询
	 * @param xsccId 计量通知单编号
	 * @return
	 */
	MeterageNotice selectByLastId(String xsccId);

}
