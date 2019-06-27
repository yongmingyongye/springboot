package com.scan.sgindustry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scan.sgindustry.entity.CopyBrandDetails;
import com.scan.sgindustry.mapper.CopyBrandDetailsMapper;
import com.scan.sgindustry.service.CopyBrandDetailsService;
import com.scan.sgindustry.service.impl.common.BaseServiceImpl;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class CopyBrandDetailsServiceImpl extends BaseServiceImpl<CopyBrandDetails> implements CopyBrandDetailsService {

	@Autowired
	private CopyBrandDetailsMapper copyBrandDetailsMapper;
	
	public List<CopyBrandDetails> selectByNoticeNumber(String  noticeNumber) {
		//写自定义查询条件
		Example example = new Example(CopyBrandDetails.class);
		Criteria criteria = example.createCriteria();
		//判断是否存在该二维信息
		criteria.andEqualTo("noticeNumber", noticeNumber);
		criteria.andNotEqualTo("status", "99");
		example.setOrderByClause("scanTime desc");
		return copyBrandDetailsMapper.selectByExample(example);
	}

    
}
