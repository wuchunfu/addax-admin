package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.pojo.TableInfo;
import com.wgzhao.addax.admin.pojo.TableInfoKey;

public interface TableInfoMapper {
    int deleteByPrimaryKey(TableInfoKey key);

    int insert(TableInfo record);

    int insertSelective(TableInfo record);

    TableInfo selectByPrimaryKey(TableInfoKey key);

    int updateByPrimaryKeySelective(TableInfo record);

    int updateByPrimaryKey(TableInfo record);
}