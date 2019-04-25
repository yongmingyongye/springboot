package com.scan.sgindustry.common.druidConfig;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;


@WebFilter(filterName="druidStatFilter",urlPatterns="/*",
	initParams= {
			@WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源
	}
)
/**
 * 配置WebFilter【过滤不需要监控的后缀】
 * @author fx
 *	
 */
public class DruidStatFilter extends WebStatFilter {

}
