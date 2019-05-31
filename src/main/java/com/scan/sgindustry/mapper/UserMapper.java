package com.scan.sgindustry.mapper;

import org.springframework.stereotype.Component;

import com.scan.sgindustry.entity.User;
import com.scan.sgindustry.tools.MyBaseMapper;

/**
 * 继承通用Mapper获取CURD方法
 * @author Administrator
 *
 */
@Component
public interface UserMapper extends MyBaseMapper<User> {

}
