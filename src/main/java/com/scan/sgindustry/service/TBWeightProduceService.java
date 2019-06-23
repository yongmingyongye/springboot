package com.scan.sgindustry.service;

import java.util.List;

import com.scan.sgindustry.entity.TBWeightProduce;
import com.scan.sgindustry.tools.BaseService;

public interface TBWeightProduceService extends BaseService<TBWeightProduce> {

	List<TBWeightProduce> selectByIdAndReqcodeIsNull(Integer weightProduceId);
	
}
