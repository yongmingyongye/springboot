package com.scan.sgindustry.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.scan.sgindustry.entity.User;
import com.scan.sgindustry.tools.MyBaseMapper;

/**
 * 继承通用Mapper获取CURD方法
 * @author Administrator
 *
 */
@Mapper
@Component
public interface UserMapper extends MyBaseMapper<User> {

}
