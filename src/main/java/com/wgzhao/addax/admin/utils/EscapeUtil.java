package com.wgzhao.addax.admin.utils;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class EscapeUtil
{
    /**
     * mysql的模糊查询时特殊字符转义
     *
     * @param parameter
     * @return
     */
    public static String modifyLikeSql(String parameter)
    {
        log.info("转义前：{}", parameter);
        if (parameter.contains("_") || parameter.contains("\\") || parameter.contains("%")) {
            parameter = EscapeUtil.escapeChar(parameter);
        }
        log.info("转义后：{}", parameter);
        return parameter;
    }

    public static String escapeChar(String before)
    {
        if (StringUtils.isNotBlank(before)) {
            before = before.replaceAll("\\\\", "\\\\\\\\");
            before = before.replaceAll("_", "\\\\_");
            before = before.replaceAll("%", "\\\\%");
        }
        return before;
    }
}
