package com.scan.sgindustry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//开启Swagger
@EnableSwagger2
//开启定时任务的注解
@EnableScheduling
//开启对异步任务的支持
@EnableAsync
//注意要加上@ServletComponentScan注解，否则Servlet无法生效 -----Druid监控
@ServletComponentScan
@MapperScan("com.scan.sgindustry.mapper")
//@EnableRedisHttpSession
public class SpringBootDruidApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDruidApplication.class, args);
	}

}
