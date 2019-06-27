package com.scan.sgindustry.tools;

import com.scan.sgindustry.mapper.common.UpdateBatchMapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用mapper接口类，不能被@MapperScan扫描到
 * @author fx
 *
 * @param <T>
 */
public interface MyBaseMapper<T> extends Mapper<T>, MySqlMapper<T>, UpdateBatchMapper<T> {

}
