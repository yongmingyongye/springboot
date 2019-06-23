package com.scan.sgindustry.service;

import java.util.List;

import com.scan.sgindustry.entity.TBWeight;
import com.scan.sgindustry.tools.BaseService;

public interface TBWeightService extends BaseService<TBWeight> {

	List<TBWeight> selectByReqcode(String reqcode);
	
}
