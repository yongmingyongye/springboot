package com.scan.sgindustry.tools;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.common.Mapper;

/**
 * 封装常用CRUD方法，多表及复杂查询请使用sql
 * @author Administrator
 *
 * @param <T>
 */
public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	private Mapper<T> mapper;//泛型装配
	
	@Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }
	
	@Override
	public List<T> list(T entity) {
		return mapper.select(entity);
	}

	@Override
	public T get(T entity) {
		return mapper.selectOne(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class) //事务回滚
	public int update(T entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int save(T entity) {
		return mapper.insertSelective(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(T entity) {
		return mapper.deleteByPrimaryKey(entity);
	}
	
	@Override
    public List<T> selectPage(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.selectAll();
    }

    @Override
    public List<T> selectPage(int pageNum, int pageSize, T entity) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.select(entity);
    }

}
