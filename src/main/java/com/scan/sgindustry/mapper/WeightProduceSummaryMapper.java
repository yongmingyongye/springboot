package com.scan.sgindustry.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.scan.sgindustry.entity.WeightProduceSummaryVO;
import com.scan.sgindustry.tools.MyBaseMapper;

public interface WeightProduceSummaryMapper extends MyBaseMapper<WeightProduceSummaryVO> {

    /**
     * 通过通知单号查询并按照炉号、钢号分组统计
     * @param noticeNumber 计量通知单号
     * @return
     */
    @Select("select STOVENO, STEELNO, count(STOVENO) num, sum(QUANTITY) QUANTITY, sum(SUTTLE) SUTTLES"
            + " from TB_WEIGHT_PRODUCE where by1 = #{noticeNumber} GROUP BY(STOVENO,STEELNO)")
    List<WeightProduceSummaryVO> selectWeightProduceSummaryByNoticeNumber(String noticeNumber);
    
}
