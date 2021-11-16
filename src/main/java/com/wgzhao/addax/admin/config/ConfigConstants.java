package com.wgzhao.addax.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

/**
 * 系统配置常量
 *
 * @author aiwenjie
 */
@Component
public class ConfigConstants
{
    public static Long tokenExpirationTime;

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static String taskInfoUrl;

    public static Long timeOut;

    @Value("${zeus-token-expiration-time}")
    public void setTokenExpirationTime(Long tokenExpirationTime)
    {
        ConfigConstants.tokenExpirationTime = tokenExpirationTime;
    }

    @Value("${zeus-time-out}")
    public void setTimeOut(Long timeOut)
    {
        ConfigConstants.timeOut = timeOut;
    }

    @Value("${zeus-task-url}")
    public void setTaskInfoUrl(String taskInfoUrl)
    {
        ConfigConstants.taskInfoUrl = taskInfoUrl;
    }
}
