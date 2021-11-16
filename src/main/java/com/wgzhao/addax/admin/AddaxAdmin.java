/*
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.wgzhao.addax.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yangkai
 */
@MapperScan(value = {"com.wgzhao.addax.admin.mapper"})
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
