package com.scan.sgindustry.tools;

import java.util.List;

/**
 * 通用service
 * @author fx
 *
 * @param <T>
 */
public interface BaseService<T> {
	
	T selectByKey(Object key);
	
	List<T> list(T entity);

    T get(T entity);

    int update(T entity);

    int save(T entity);

    int delete(T entity);
    
    List<T> selectPage(int pageNum, int pageSize);

    List<T> selectPage(int pageNum, int pageSize, T entity);

}
