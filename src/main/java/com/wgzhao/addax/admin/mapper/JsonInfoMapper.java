package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.pojo.JsonInfo;

public interface JsonInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(JsonInfo record);

    int insertSelective(JsonInfo record);

    JsonInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JsonInfo record);

    int updateByPrimaryKey(JsonInfo record);
}