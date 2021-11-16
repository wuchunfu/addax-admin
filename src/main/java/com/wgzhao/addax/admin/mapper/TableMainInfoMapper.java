package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.pojo.TableMainInfo;

public interface TableMainInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(TableMainInfo record);

    int insertSelective(TableMainInfo record);

    TableMainInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TableMainInfo record);

    int updateByPrimaryKey(TableMainInfo record);
}