package com.xiaoazhai.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

@Component
public class SpringBeanUtil implements ApplicationContextAware {

    /**
     * 直接通过Spring 上下文获取SpringBean,用于多线程环境
     * by lida @20170629
     */


    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
            SpringBeanUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象 这里重写了bean方法，起主要作用
     * example: getBean("userService")//注意： 类名首字母一定要小写！
     */
    public static <T> T getBean(String beanId) throws BeansException {
        return (T) applicationContext.getBean(beanId);
    }

    /**
     * 获取对象  byType
     *
     * @param beanClass :
     * @return T
     * @throws
     * @Author loujinglong
     * @Date 2019/9/11
     */
    public static <T> T getBean(Class<T> beanClass) throws BeansException {
        return applicationContext.getBean(beanClass);
    }

    public static Map<String, Object> getBeanByAnnotation(Class<? extends Annotation> anno) {
        return applicationContext.getBeansWithAnnotation(anno);
    }
}
