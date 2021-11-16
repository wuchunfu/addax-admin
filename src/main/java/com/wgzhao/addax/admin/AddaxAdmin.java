package com.wgzhao.addax.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yangkai
 */
@SpringBootApplication
public class AddaxAdmin
{
    public static void main(String[] args) {
        SpringApplication.run(AddaxAdmin.class, args);
    }

    /**
     * 配置mybatis的分页插件pageHelper
     */
//    @Bean
//    public PageHelper pageHelper() {
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("offsetAsPageNum", "true");
//        properties.setProperty("rowBoundsWithCount", "true");
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("dialect", "mysql");
//        pageHelper.setProperties(properties);
//        new SqlSessionFactoryBean().setPlugins(new Interceptor[]{pageHelper});
//        return pageHelper;
//    }
}
