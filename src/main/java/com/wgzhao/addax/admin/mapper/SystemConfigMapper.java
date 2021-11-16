package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.pojo.SystemConfig;

public interface SystemConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemConfig record);

    int insertSelective(SystemConfig record);

    SystemConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemConfig record);

    int updateByPrimaryKey(SystemConfig record);
}