package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.pojo.DataChangeRecord;

public interface DataChangeRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(DataChangeRecord record);

    int insertSelective(DataChangeRecord record);

    DataChangeRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DataChangeRecord record);

    int updateByPrimaryKey(DataChangeRecord record);
}