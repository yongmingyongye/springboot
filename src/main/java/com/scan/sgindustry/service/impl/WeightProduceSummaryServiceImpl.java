package com.scan.sgindustry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scan.sgindustry.entity.WeightProduceSummaryVO;
import com.scan.sgindustry.mapper.WeightProduceSummaryMapper;
import com.scan.sgindustry.service.WeightProduceSummaryService;
import com.scan.sgindustry.service.impl.common.BaseServiceImpl;

@Service
public class WeightProduceSummaryServiceImpl extends BaseServiceImpl<WeightProduceSummaryVO> implements WeightProduceSummaryService {

    @Autowired
    private WeightProduceSummaryMapper weightProduceSummaryMapper;
    
    @Override
    public List<WeightProduceSummaryVO> selectWeightProduceSummaryByNoticeNumber(String noticeNumber) {
        return weightProduceSummaryMapper.selectWeightProduceSummaryByNoticeNumber(noticeNumber);
    }

}
