package com.scan.sgindustry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//开启定时任务的注解
@EnableScheduling
//开启对异步任务的支持
@EnableAsync
//注意要加上@ServletComponentScan注解，否则Servlet无法生效 -----Druid监控
@ServletComponentScan
public class SpringBootDruidApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDruidApplication.class, args);
	}

}
