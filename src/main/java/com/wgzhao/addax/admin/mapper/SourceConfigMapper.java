package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.pojo.SourceConfig;

public interface SourceConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(SourceConfig record);

    int insertSelective(SourceConfig record);

    SourceConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SourceConfig record);

    int updateByPrimaryKey(SourceConfig record);
}