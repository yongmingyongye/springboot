package com.scan.sgindustry.service;

import java.util.List;

import com.scan.sgindustry.entity.WeightProduceSummaryVO;
import com.scan.sgindustry.service.common.BaseService;

/**
 * 继承通用service接口
 * @author fx
 *
 * @param 
 */
public interface WeightProduceSummaryService extends BaseService<WeightProduceSummaryVO> {

    /**
     * 通过通知单号查询并按照炉号、钢号分组统计
     * @param noticeNumber 计量通知单号
     * @return
     */
    List<WeightProduceSummaryVO> selectWeightProduceSummaryByNoticeNumber(String noticeNumber);
    
}
