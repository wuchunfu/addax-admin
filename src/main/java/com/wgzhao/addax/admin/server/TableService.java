package com.wgzhao.addax.admin.server;

import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.pojo.TableInfo;
import com.wgzhao.addax.admin.vo.TableMainVo;
import com.wgzhao.addax.admin.vo.TreeNodeVo;

import java.util.List;

/**
 * @author liuting
 */
public interface TableService
{
    List<TableInfo> getAllTableInfoList(String sourceConfigId, String sourceTableSchema,
            String sourceTableName);

    ServerResponse<List<TreeNodeVo>> queryTreeNodes();

    ServerResponse<TableMainVo> getTableInfo(String nodeCode);

    List<TableInfo> getTableInfoBySubTaskId(String subTaskId, String sourceId,String db ,
            String tbl);
}
