package com.scan.sgindustry.common.druidConfig;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * 配置WebServlet【监控视图配置】
 * @author fx
 *
 */

@WebServlet(urlPatterns = "/druid/*",
	initParams = {
			@WebInitParam(name = "allow", value = ""),// IP白名单 (没有配置或者为空，则允许所有访问)
//	        @WebInitParam(name="deny",value="172.22.0.0"),// IP黑名单 (存在共同时，deny优先于allow)  
	        @WebInitParam(name="loginUsername",value="admin"),// druid监控页面登陆用户名  
	        @WebInitParam(name="loginPassword",value="admin"),// druid监控页面登陆密码  
	        @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
	}
)

public class DruidStatViewServlet extends StatViewServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
