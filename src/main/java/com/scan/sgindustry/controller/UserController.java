package com.scan.sgindustry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scan.sgindustry.entity.User;
import com.scan.sgindustry.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("getOneById")
    public User delete(@RequestParam(value = "id") String id) {
        return userService.selectByKey(id);
    }
	
	@RequestMapping("list")
    public List<User> list(@RequestBody User user) {
        return userService.list(user);
    }

    @RequestMapping("get")
    public User get(@RequestBody User user) {
        return userService.get(user);
    }

    @RequestMapping("update")
    public int update(@RequestBody User user) {
        return userService.update(user);
    }

    @RequestMapping("save")
    public int save(@RequestBody User user) {
    	
    	System.out.println(user);
        return userService.save(user);
    }

    @RequestMapping("delete")
    public int delete(@RequestBody User user) {
        return userService.delete(user);
    }
    
    @RequestMapping("pageList")
    public List<User> selectPage(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestBody User user) {
    	return userService.selectPage(pageNum, pageSize, user);
    }
    

}
