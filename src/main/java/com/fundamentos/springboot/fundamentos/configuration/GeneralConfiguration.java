package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithPropertiesImpl;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:connection.properties")
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {

    @Value("${example.value.name}")
    private String name;

    @Value("${example.value.lastname}")
    private String lastname;

    @Value("${example.value.random}")
    private String random;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    public MyBeanWithProperties function() {
        return new MyBeanWithPropertiesImpl(name, lastname);
    }

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(jdbcUrl);
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
}
