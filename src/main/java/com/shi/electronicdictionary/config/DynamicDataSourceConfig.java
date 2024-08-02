//package com.shi.electronicdictionary.config;
//
///**
// * @author syc
// * @date 2024/08/02 16:04
// **/
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DynamicDataSourceConfig {
//
//    @Autowired
//    private DataSourceProperties dataSourceProperties;
//
//    @Bean
//    public DataSource master() {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        Map<String, String> master = dataSourceProperties.getMaster();
//        druidDataSource.setUsername(master.get("username"));
//        druidDataSource.setPassword(master.get("password"));
//        druidDataSource.setUrl(master.get("url"));
//        //其他参数配置 省略
//        return druidDataSource;
//    }
//
//    @Bean
//    public DataSource slave() {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        Map<String, String> slave = dataSourceProperties.getSlave();
//        druidDataSource.setUsername(slave.get("username"));
//        druidDataSource.setPassword(slave.get("password"));
//        druidDataSource.setUrl(slave.get("url"));
//        //其他参数配置 省略
//        return druidDataSource;
//    }
//
//    @Bean
//    @Primary
//    public DynamicDataSource dataSource(DataSource master, DataSource slave) {
//        Map<Object, Object> map = new HashMap<>(4);
//        map.put("master", master);
//        map.put("slave", slave);
//        return new DynamicDataSource(master, map);
//    }
//}
