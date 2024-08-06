//package com.shi.electronicdictionary.service;
//
///**
// * @author syc
// * @date 2024/08/02 16:07
// **/
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//
///**
// *
// * 动态数据源通知
// * @Order(-1) 保证在@Transactional之前执行
// */
//@Aspect
//@Order(-1)
//@Component
//@Slf4j
//public class DynamicDattaSourceAspect {
//
//
//    @Pointcut("@annotation(com.shi.electronicdictionary.service.DataSource)")
//    public void dataSourcePointCut() {
//    }
//
//    @Around(value = "dataSourcePointCut()")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        MethodSignature signature = (MethodSignature) point.getSignature();
//        Method method = signature.getMethod();
//
//        DataSource dataSource = method.getAnnotation(DataSource.class);
//        log.info("数据库 dataSource:{}", dataSource.toString());
//        if (dataSource == null) {
//            DynamicDataSource.setDataSource("master");
//        } else {
//            DynamicDataSource.setDataSource(dataSource.name());
//        }
//        try {
//            return point.proceed();
//        } finally {
//            DynamicDataSource.clearDataSource();
//        }
//    }
//}
