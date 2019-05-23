package com.scan.sgindustry.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scan.sgindustry.entity.User;
import com.scan.sgindustry.service.UserService;
import com.scan.sgindustry.tools.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="/user", tags="/user")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@ApiOperation(value="查询用户", notes="根据Id查询用户")
	@RequestMapping(value = "getOneById", method = RequestMethod.GET)
    public User getOneById(@RequestParam(value = "id") String id) {
        return userService.selectByKey(id);
    }
	
	@ApiOperation(value = "查询用户", notes = "根据条件查询多个用户")
	@RequestMapping(value = "list", method = RequestMethod.POST)
    public List<User> list(@RequestBody User user) {
        return userService.list(user);
    }

    @ApiOperation(value = "查询用户", notes = "根据条件查询单个用户")
	@RequestMapping(value = "get", method = RequestMethod.POST)
    public User get(@RequestBody User user) {
        return userService.get(user);
    }

    @ApiOperation(value = "更新用户", notes = "更新用户信息")
	@RequestMapping(value = "update", method = RequestMethod.POST)
    public int update(@RequestBody User user) {
        return userService.update(user);
    }

    @ApiOperation(value = "保存用户", notes = "保存用户信息")
	@RequestMapping(value = "save", method = RequestMethod.POST)
    public int save(@RequestBody User user) {
        return userService.save(user);
    }

    @ApiOperation(value = "删除用户", notes = "删除用户信息")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
    public int delete(@RequestBody User user) {
        return userService.delete(user);
    }
    
    @ApiOperation(value = "查询用户", notes = "分页查询用户信息")
	@RequestMapping(value = "selectPage", method = RequestMethod.POST)
    public List<User> selectPage(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestBody User user) {
    	return userService.selectPage(pageNum, pageSize, user);
    }
    
    @ApiOperation(value = "用户登录", notes = "用户登录")
	@RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<JsonResult<Object>> login(HttpServletRequest request, @RequestBody User user) {
		
    	if(user == null || StringUtils.isBlank(user.getLoginName()) || StringUtils.isBlank(user.getPassword())) {
    		return ResponseEntity.ok(new JsonResult<>(1, "用户名或密码不为空", null));
		}
    	User userOld = userService.get(user);
    	if(userOld == null) {
    		return ResponseEntity.ok(new JsonResult<>(1, "用户名或密码错误", user));
    	}
    	if(StringUtils.isBlank(userOld.getPassword()) || !user.getPassword().equals(userOld.getPassword())) {
    		return ResponseEntity.ok(new JsonResult<>(1, "数据库用户异常，请与管理员联系", userOld));
    	}
    	request.getSession().setAttribute("user", userOld);
    	Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
    	return ResponseEntity.ok(new JsonResult<>(0, "登录成功", map));
    }

}
