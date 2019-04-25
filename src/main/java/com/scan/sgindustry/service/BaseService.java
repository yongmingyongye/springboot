package com.scan.sgindustry.service;

import java.util.List;

/**
 * 通用service接口
 * @author fx
 *
 * @param <T>
 */
public interface BaseService<T> {

	T selectByKey(Object key);
    
    T selectOne(T entity);

    int save(T entity);

    int delete(Object key);

    int updateAll(T entity);

    int updateNotNull(T entity);

    List<T> selectPage(int pageNum, int pageSize);

    List<T> selectPage(int pageNum, int pageSize, T entity);
}
