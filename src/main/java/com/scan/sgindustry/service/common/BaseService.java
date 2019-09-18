package com.scan.sgindustry.service.common;

import java.util.List;

import tk.mybatis.mapper.entity.Example;

/**
 * 通用service，完成基本的增删改查功能
 * @author fx
 *
 * @param <T>
 */
public interface BaseService<T> {
	
    /**
     * 通过主键查询
     * @param key 主键
     * @return
     */
	T selectByKey(Object key);
	
	/**
	 * 通过条件查询多个对象
	 * @param entity
	 * @return
	 */
	List<T> list(T entity);

	/**
	 * 通过条件查询一个对象
	 * @param entity
	 * @return
	 */
    T get(T entity);

    /**
     * 更新
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 新增
     * @param entity
     * @return
     */
    int save(T entity);

    /**
     * 删除
     * @param entity
     * @return
     */
    int delete(T entity);
    
    /**
     * 通过Example设计自定义查询条件
     * @param example
     * @return
     */
    List<T> selectByExample(Example example);
    
    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<T> selectPage(int pageNum, int pageSize);

    /**
     * 分页条件查询
     * @param pageNum
     * @param pageSize
     * @param entity
     * @return
     */
    List<T> selectPage(int pageNum, int pageSize, T entity);

    /**
     * 分页条件自定义排序查询
     * @param pageNum 页号
     * @param pageSize 页大小
     * @param orderby 排序sql
     * @param entity 查询条件实体
     * @return
     */
    List<T> selectPage(int pageNum, int pageSize, String orderby, T entity);
    
    /**
     * 根据id批量更新,不更新非空字段
     * @return
     */
    Integer updateBatchByPrimaryKeySelective(List<T> list);
    
}
