package com.demo.clockin.controller.conf;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
public class DatasourceConfiguration {


    @Bean(name = "dataSource")
    @Qualifier(value = "dataSource")//限定描述符除了能根据名字进行注入，但能进行更细粒度的控制如何选择候选者
    @Primary//用@Primary区分主数据源
    @ConfigurationProperties(prefix = "spring.datasource")//指定配置文件中，前缀为c3p0的属性值
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
    }



    public static final String transactionExecution = "execution(* com.demo.clockin.service.*.*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;


    /*事务拦截器*/
    @Bean
    public TransactionInterceptor transactionInterceptor() {
        Properties attributes = new Properties();
        attributes.setProperty("get*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        attributes.setProperty("find*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        attributes.setProperty("select*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        attributes.setProperty("do*", "PROPAGATION_REQUIRED,-Exception");
        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, attributes);
        return txAdvice;
    }

    /**切面拦截规则 参数会自动从容器中注入*/
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(transactionExecution);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        Properties attributes = new Properties();
        attributes.setProperty("get*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        attributes.setProperty("find*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        attributes.setProperty("select*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        attributes.setProperty("do*", "PROPAGATION_REQUIRED,-Exception");
        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, attributes);
        advisor.setAdvice(txAdvice);
        return advisor;
    }
}
