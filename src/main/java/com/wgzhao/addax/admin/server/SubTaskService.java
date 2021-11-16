package com.wgzhao.addax.admin.server;

import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.dto.QuerySubTaskDto;
import com.wgzhao.addax.admin.dto.TableDto;
import com.wgzhao.addax.admin.pojo.SubTaskInfo;
import com.wgzhao.addax.admin.vo.ProcessTableVo;
import com.wgzhao.addax.admin.vo.QuerySubTaskVo;
import com.wgzhao.addax.admin.vo.UserVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author liuting
 */
public interface SubTaskService
{
    void saveSubTaskInfo(String sourceId, TableDto item, String targetId, String taskId,
            Integer collectionType);

    void updateAddJsonStatus(SubTaskInfo subTaskInfo, Integer code, String s);

    int getNotFinishedCountByTaskId(String taskId);

    List<ProcessTableVo> getAllJsonProcessByTaskId(String taskId);

    ServerResponse<PageInfo<QuerySubTaskVo>> querySubTaskListWithPage(QuerySubTaskDto dto,
            UserVo userVo);

    List<SubTaskInfo> findAllByTaskId(String taskId);

    SubTaskInfo getSubTaskInfoById(String id);
}
