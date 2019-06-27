package com.scan.sgindustry.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.UpdateProvider;

import com.scan.sgindustry.mapper.provider.BatchExampleProvider;

import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 批量更新通用接口
 * @author fx
 *
 * @param <T> 不能为空
 */
//自动注册该接口到通用 Mapper（不需要配置 mappers 参数指定该接口了）
@RegisterMapper
public interface UpdateBatchMapper<T> {
    
    /**
     * 批量更新，不能更新为空字段，主键必须为id
     * @param recordList
     * @return
     */
    @UpdateProvider(type = BatchExampleProvider.class, method = "dynamicSQL")
    Integer updateBatchByPrimaryKeySelective(List<? extends T> recordList);
    
    
}
