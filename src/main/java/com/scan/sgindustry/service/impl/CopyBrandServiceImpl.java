package com.scan.sgindustry.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scan.sgindustry.entity.CopyBrand;
import com.scan.sgindustry.entity.TBWeightProduce;
import com.scan.sgindustry.mapper.CopyBrandMapper;
import com.scan.sgindustry.service.CopyBrandService;
import com.scan.sgindustry.service.impl.common.BaseServiceImpl;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class CopyBrandServiceImpl extends BaseServiceImpl<CopyBrand> implements CopyBrandService {

	@Autowired
	private CopyBrandMapper copyBrandMapper;
	
	public CopyBrand selectByNoticeNumber(String noticeNumber) {
		return copyBrandMapper.selectByNoticeNumber(noticeNumber);
	}
	
	public Integer saveAutoId(CopyBrand copyBrand) {
		return copyBrandMapper.saveAutoId(copyBrand);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
	}

    @Override
    public List<CopyBrand> selectAll() {
        //写自定义查询条件
        Example example = new Example(TBWeightProduce.class);
        Criteria criteria = example.createCriteria();
        //判断是否存在该二维信息
        criteria.andNotEqualTo("status", "99");
        example.setOrderByClause("createTime desc");
        return copyBrandMapper.selectByExample(example);
    }

    @Override
    public List<CopyBrand> selectAll(CopyBrand copyBrand) {
      //写自定义查询条件
        Example example = new Example(TBWeightProduce.class);
        Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(copyBrand.getUserNumber())) {
            criteria.andNotEqualTo("userNumber", copyBrand.getUserNumber());
        }
        if(StringUtils.isNotBlank(copyBrand.getVehicleNumber())) {
            criteria.andNotEqualTo("vehicleNumber", copyBrand.getVehicleNumber());
        }
        criteria.andNotEqualTo("status", "99");
        example.setOrderByClause("createTime desc");
        return copyBrandMapper.selectByExample(example);
    }

    @Override
    public Integer updateBatchByPrimaryKeySelective(List<CopyBrand> list) {
        return copyBrandMapper.updateBatchByPrimaryKeySelective(list);
    }

}
