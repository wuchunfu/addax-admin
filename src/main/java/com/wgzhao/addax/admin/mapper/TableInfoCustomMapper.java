package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.pojo.TableInfo;
import com.wgzhao.addax.admin.vo.TableInfoVo;
import com.wgzhao.addax.admin.vo.TreeNodeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TableInfoCustomMapper
{
    List<TableInfo> getAllTableInfoList(@Param("sourceConfigId") String sourceConfigId,
            @Param("sourceTableSchema") String sourceTableSchema,
            @Param("sourceTableName") String sourceTableName);

    List<TreeNodeVo> queryAllSourceConfigs();

    List<TableInfoVo> getTableInfoByNodeCode(String nodeCode);

    List<TableInfo> getTableInfoBySubTaskId(@Param("subTaskId") String subTaskId,
            @Param("sourceId") String sourceId, @Param("db") String db,
            @Param("tbl") String tbl);
}