package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.pojo.SystemConfig;
import com.wgzhao.addax.admin.vo.QuerySystemConfigVo;

import java.util.List;

public interface SystemConfigCustomMapper
{

    List<QuerySystemConfigVo> querySystemConfigs();

    SystemConfig getSystemConfigByCode(String code);
}