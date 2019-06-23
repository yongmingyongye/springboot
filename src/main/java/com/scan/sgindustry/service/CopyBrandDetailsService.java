package com.scan.sgindustry.service;

import java.util.List;

import com.scan.sgindustry.entity.CopyBrandDetails;
import com.scan.sgindustry.tools.BaseService;

/**
 * 继承通用service接口
 * @author fx
 *
 * @param 
 */
public interface CopyBrandDetailsService extends BaseService<CopyBrandDetails> {

	Integer saveAutoId(CopyBrandDetails copyBrandDetails);
	
	public List<CopyBrandDetails> selectByProduceId(Integer weightProduceId);
	
}
