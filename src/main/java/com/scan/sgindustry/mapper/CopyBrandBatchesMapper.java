package com.scan.sgindustry.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Component;

import com.scan.sgindustry.entity.CopyBrandBatches;
import com.scan.sgindustry.tools.MyBaseMapper;

/**
 * 继承通用Mapper获取CURD方法
 * 
 * @author fx
 *
 */
@Component
public interface CopyBrandBatchesMapper extends MyBaseMapper<CopyBrandBatches> {

    /**
     * 保存抄牌批次信息，自动生成Id
     * 
     * @return
     */
    @SelectKey(keyColumn = "id", keyProperty = "id", before = true, resultType = int.class, statement = "select erp_copybrandbatches_seq.nextval as id from dual")
    @Insert("insert into t_erp_copybrand_batches(id,batche_id,notice_number,operator,operator_name,operator_time,status)"
            + "values(#{id},#{batcheId},#{noticeNumber},#{operator},#{operatorName},#{operationTime},#{status})")
    @Options(useGeneratedKeys = true)
    int save(CopyBrandBatches copyBrandBatches);

}
