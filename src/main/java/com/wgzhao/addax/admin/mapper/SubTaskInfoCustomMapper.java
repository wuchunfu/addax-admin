package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.dto.QuerySubTaskDto;
import com.wgzhao.addax.admin.pojo.SubTaskInfo;
import com.wgzhao.addax.admin.vo.ProcessTableVo;
import com.wgzhao.addax.admin.vo.QuerySubTaskVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubTaskInfoCustomMapper
{
    int getNotFinishedCountByTaskId(String taskId);

    List<ProcessTableVo> getAllJsonProcessByTaskId(String taskId);

    List<QuerySubTaskVo> querySubTaskListWithPage(@Param("dto") QuerySubTaskDto dto,
            @Param("userId") String userId);

    List<SubTaskInfo> findAllByTaskId(@Param("taskId") String taskId);
}