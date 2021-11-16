package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.pojo.TypeInfo;
import com.wgzhao.addax.admin.pojo.TypeInfoKey;

public interface TypeInfoMapper {
    int deleteByPrimaryKey(TypeInfoKey key);

    int insert(TypeInfo record);

    int insertSelective(TypeInfo record);

    TypeInfo selectByPrimaryKey(TypeInfoKey key);

    int updateByPrimaryKeySelective(TypeInfo record);

    int updateByPrimaryKey(TypeInfo record);
}