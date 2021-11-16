package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.pojo.TableInfo;

import java.util.List;

public interface TableInfoMapper {
    int insert(TableInfo record);

    int insertSelective(TableInfo record);

    /**
     * 查询字段表
     * @param record 查询条件
     * @return list
     */
    List<TableInfo> getTableInfos(TableInfo record);
}