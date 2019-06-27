package com.scan.sgindustry.service;

import java.util.List;

import com.scan.sgindustry.entity.CopyBrandDetails;
import com.scan.sgindustry.service.common.BaseService;

/**
 * 继承通用service接口
 * @author fx
 *
 * @param 
 */
public interface CopyBrandDetailsService extends BaseService<CopyBrandDetails> {

	
	public List<CopyBrandDetails> selectByNoticeNumber(String  noticeNumber);
	
	
	
}
