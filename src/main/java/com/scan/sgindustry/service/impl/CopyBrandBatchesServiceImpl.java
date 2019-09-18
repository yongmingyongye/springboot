package com.scan.sgindustry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scan.sgindustry.entity.CopyBrandBatches;
import com.scan.sgindustry.mapper.CopyBrandBatchesMapper;
import com.scan.sgindustry.service.CopyBrandBatchesService;
import com.scan.sgindustry.service.impl.common.BaseServiceImpl;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class CopyBrandBatchesServiceImpl extends BaseServiceImpl<CopyBrandBatches> implements CopyBrandBatchesService {

    @Autowired
    private CopyBrandBatchesMapper copyBrandBatchesMapper;

    @Override
    public List<CopyBrandBatches> selectByNoticeNumber(String noticeNumber) {
        // 自定义查询条件
        Example example = new Example(CopyBrandBatches.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("noticeNumber", noticeNumber).andNotEqualTo("status", "99");
        example.setOrderByClause("id desc");
        return copyBrandBatchesMapper.selectByExample(example);
    }

    @Override
    public CopyBrandBatches selectByBatcheId(String batcheId) {
        Example example = new Example(CopyBrandBatches.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("batcheId", batcheId).andNotEqualTo("status", "99");
        return copyBrandBatchesMapper.selectOneByExample(example);
    }

    @Override
    public int save(CopyBrandBatches entity) {
        return copyBrandBatchesMapper.save(entity);
    }
    
    

}
