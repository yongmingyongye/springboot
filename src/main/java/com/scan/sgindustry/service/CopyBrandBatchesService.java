package com.scan.sgindustry.service;

import java.util.List;

import com.scan.sgindustry.entity.CopyBrandBatches;
import com.scan.sgindustry.service.common.BaseService;

public interface CopyBrandBatchesService extends BaseService<CopyBrandBatches> {
    
    /**
     * 根据计量通知单号并按id倒序查询抄牌所有批次
     * @param noticeNumber
     * @return List<CopyBrandBatches>
     */
    List<CopyBrandBatches> selectByNoticeNumber(String noticeNumber);
    
    /**
     * 根据自动生成的抄牌批次编号查询抄牌批次信息
     * @param noticeNumber
     * @return CopyBrandBatches
     */
    CopyBrandBatches selectByBatcheId(String batcheId);
    
    /**
     * 重写保存方法，实现保存时id自增
     */
    int save(CopyBrandBatches entity);

}
