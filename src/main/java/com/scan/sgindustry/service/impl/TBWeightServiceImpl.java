package com.scan.sgindustry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scan.sgindustry.entity.TBWeight;
import com.scan.sgindustry.mapper.TBWeightMapper;
import com.scan.sgindustry.service.TBWeightService;
import com.scan.sgindustry.service.impl.common.BaseServiceImpl;

@Service
public class TBWeightServiceImpl extends BaseServiceImpl<TBWeight> implements TBWeightService {
	
	@Autowired
	private TBWeightMapper tBWeightMapper;
	

	@Override
	public List<TBWeight> selectByReqcode(String reqcode) {
		return tBWeightMapper.selectByReqcode(reqcode);
	}

}
