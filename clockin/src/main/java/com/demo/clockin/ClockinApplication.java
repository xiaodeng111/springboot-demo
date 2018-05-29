package com.demo.clockin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
//@EnableAutoConfiguration//自动载入应用程序所需的所有Bean，当使用Exclude这个属性时，是禁止自动配置某个类
//@ComponentScan
@ServletComponentScan //加载注解@WebServlet, @WebFilter, and @WebListener
@MapperScan("com.demo.clockin.dao")
public class ClockinApplication extends SpringBootServletInitializer {//SpringBootServletInitializer加载解析web.xml应用的filter,listener,interceptor

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ClockinApplication.class);
    }

    public static void main(String[] args) throws Exception{
        SpringApplication.run(ClockinApplication.class, args);
    }

}
