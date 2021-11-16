package com.wgzhao.addax.admin.config;

import com.wgzhao.addax.admin.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author yangkai
 */
@Configuration
public class CoreConfig
        implements WebMvcConfigurer
{

    @Resource
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry)
    {
        // 允许跨域访问的路径
        corsRegistry.addMapping("/**")
                // 允许跨域访问的源
                .allowedOrigins("*")
                // 允许请求方法
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                // 预检间隔时间
                .maxAge(168000)
                // 允许头部设置
                .allowedHeaders("*")
                // 是否发送cookie
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        //token 验证拦截器
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
    }
}