package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBeans {
    @Bean
    public MyBean exampleBean() {
//        return new MyBeanImpl();
        return new MyBeanTwoImpl();
    }

    @Bean
    public MyOperation operationBean() {
        return new MyOperationImpl();
    }

    @Bean
    public BeanWithDependency beanWithDependency(MyOperation myOperation) {
        return new BeanWithDependencyImpl(myOperation);
    }
}
