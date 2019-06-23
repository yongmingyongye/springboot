package com.scan.sgindustry.service;

import com.scan.sgindustry.entity.CopyBrand;
import com.scan.sgindustry.tools.BaseService;

/**
 * 继承通用service接口
 * @author fx
 *
 * @param 
 */
public interface CopyBrandService extends BaseService<CopyBrand> {

	CopyBrand selectByNoticeNumber(String noticeNumber);
	
	Integer saveAutoId(CopyBrand copyBrand);
	
}
