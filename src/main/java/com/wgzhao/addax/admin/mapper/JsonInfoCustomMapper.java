package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.dto.QueryJsonDto;
import com.wgzhao.addax.admin.pojo.JsonInfo;
import com.wgzhao.addax.admin.vo.JsonTaskVo;
import com.wgzhao.addax.admin.vo.JsonVo;
import com.wgzhao.addax.admin.vo.QueryJsonVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsonInfoCustomMapper
{
    List<QueryJsonVo> queryJsonListWithPage(QueryJsonDto queryJsonDto);

    List<JsonInfo> getJsonListByTaskId(String taskId);

    List<JsonVo> getJsonAndUserListByTaskId(String taskId);

    List<JsonTaskVo> getNeedUpdateList(@Param("sourceId") String sourceId, @Param("dbName") String dbName,
            @Param("tblName") String tblName);
}