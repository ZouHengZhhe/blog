package com.zhhe.blog.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;


/*
 *Author:ZouHeng
 *Des:
 *Date:2019-12-12  14:20
 */
@Configuration
@PropertySource(value = "classpath:druid.yml")
public class DruidConfig
{
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean druidServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //IP白名单
        bean.addInitParameter("allow","127.0.0.1");
        //IP黑名单(共同存在时，deny优先于allow)
        bean.addInitParameter("deny","192.168.1.100");
//        是否能够重置数据 禁用HTML页面上的“Reset All”功能
        bean.addInitParameter("resetEnable","false");
//        控制台管理用户
        bean.addInitParameter("loginUsername","admin");
        bean.addInitParameter("loginPassword","admin");
        return bean;
    }

    @Bean
    public FilterRegistrationBean statFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
//        添加过滤规则
        bean.addUrlPatterns("/*");
//        添加不需要忽略的格式信息
        bean.addInitParameter("exclusions","*.js,*.gif,/druid/*");
        return bean;
    }
}
