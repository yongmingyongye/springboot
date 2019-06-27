package com.scan.sgindustry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scan.sgindustry.entity.MeterageNotice;
import com.scan.sgindustry.mapper.MeterageNoticeMapper;
import com.scan.sgindustry.service.MeterageNoticeService;
import com.scan.sgindustry.service.impl.common.BaseServiceImpl;

@Service
public class MeterageNoticeServiceImpl extends BaseServiceImpl<MeterageNotice> implements MeterageNoticeService {
	
	@Autowired
	private MeterageNoticeMapper meterageNoticeMapper;
	
	public MeterageNotice selectByLastId(String xsccId) {
		return meterageNoticeMapper.selectByLastId(xsccId);
	}

}
