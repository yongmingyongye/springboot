package com.scan.sgindustry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scan.sgindustry.entity.TBWeightProduce;
import com.scan.sgindustry.mapper.TBWeightProduceMapper;
import com.scan.sgindustry.service.TBWeightProduceService;
import com.scan.sgindustry.tools.BaseServiceImpl;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class TBWeightProduceServiceImpl extends BaseServiceImpl<TBWeightProduce> implements TBWeightProduceService {

	@Autowired
	private TBWeightProduceMapper tbWeightProduceMapper;
	
	@Override
	public List<TBWeightProduce> selectByIdAndReqcodeIsNull(Integer weightProduceId) {
		//写自定义查询条件
		Example example = new Example(TBWeightProduce.class);
		Criteria criteria = example.createCriteria();
		//判断是否存在该二维信息
		criteria.andEqualTo("id", weightProduceId);
		criteria.andNotEqualTo("status", "99");
		criteria.andIsNull("reqcode");
		return tbWeightProduceMapper.selectByExample(example);
	}
	

}
