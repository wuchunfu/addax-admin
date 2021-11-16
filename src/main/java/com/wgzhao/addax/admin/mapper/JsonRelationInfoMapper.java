package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.pojo.JsonRelationInfo;

public interface JsonRelationInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(JsonRelationInfo record);

    int insertSelective(JsonRelationInfo record);

    JsonRelationInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JsonRelationInfo record);

    int updateByPrimaryKey(JsonRelationInfo record);
}