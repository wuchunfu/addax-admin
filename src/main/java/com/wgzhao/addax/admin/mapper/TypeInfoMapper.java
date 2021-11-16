package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.pojo.TypeInfo;
import org.apache.ibatis.annotations.Param;

public interface TypeInfoMapper
{
    int deleteByPrimaryKey(Integer dtype);

    int insert(TypeInfo record);

    int insertSelective(TypeInfo record);

    TypeInfo selectByPrimaryKey(Integer dtype);

    int updateByPrimaryKeySelective(TypeInfo record);

    int updateByPrimaryKey(TypeInfo record);

    TypeInfo selectTypeInfo(@Param("dtype")Integer dtype ,@Param("sqlTypeCode") String sqlTypeCode);

}