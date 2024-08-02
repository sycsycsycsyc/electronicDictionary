//package com.shi.electronicdictionary.service;
//
///**
// * @author syc
// * @date 2024/08/02 16:03
// **/
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
//import javax.sql.DataSource;
//import java.util.Map;
//
//
///**
// * 动态数据源切换
// * AbstractRoutingDataSource(每执行一次数据库，动态获取DataSource)
// */
//@Slf4j
//public class DynamicDataSource extends AbstractRoutingDataSource {
//
//    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
//
//    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
//        super.setDefaultTargetDataSource(defaultTargetDataSource);
//        super.setTargetDataSources(targetDataSources);
//        super.afterPropertiesSet();
//    }
//
//
//    @Override
//    protected Object determineCurrentLookupKey() {
//        Object dataSoruce = getDataSource();
//        return dataSoruce;
//    }
//
//    public static void setDataSource(String dataSource) {
//        contextHolder.set(dataSource);
//    }
//
//    public static String getDataSource() {
//        return contextHolder.get();
//    }
//
//    public static void clearDataSource() {
//        contextHolder.remove();
//    }
//}
