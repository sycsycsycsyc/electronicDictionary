package com.shi.electronicdictionary.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author syc
 * @date 2024/08/02 15:44
 **/
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }


    /**
     * 根据类获取Bean
     * @param clazz 例如 String.class
     */
    public static DataSource getBean(String clazz){
        return (DataSource) applicationContext.getBean(clazz);
    }
}

