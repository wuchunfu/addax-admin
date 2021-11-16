/*
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.wgzhao.addax.admin.server.impl;

import com.wgzhao.addax.admin.enums.TaskStatusEnum;
import com.wgzhao.addax.admin.mapper.SubTaskInfoMapper;
import com.wgzhao.addax.admin.mapper.TaskInfoMapper;
import com.wgzhao.addax.admin.pojo.SubTaskInfo;
import com.wgzhao.addax.admin.server.TaskInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yangkai
 */
@Service
public class TaskInfoServiceImpl
        implements TaskInfoService
{
    @Autowired
    private SubTaskInfoMapper subTaskInfoMapper;

    @Autowired
    private TaskInfoMapper taskInfoMapper;

    @Override
    public void updateReadingSourceTask(String mainTaskId, String taskId, String sourceId, Integer taskStatus, String massage)
    {
        SubTaskInfo subTaskInfo = new SubTaskInfo();
        if (StringUtils.isNotBlank(taskId)) {
            subTaskInfo.setId(taskId);
        }
        if (StringUtils.isNotBlank(mainTaskId)) {
            subTaskInfo.setTaskId(mainTaskId);
        }
        subTaskInfo.setMtime(new Date());
        if (TaskStatusEnum.FAIL.getCode().equals(taskStatus)) {
            if (StringUtils.isNotBlank(sourceId)) {
                subTaskInfo.setSourceId(sourceId);
            }
            subTaskInfo.setAddFieldStatus(taskStatus);
            subTaskInfo.setAddFieldReason("读取源表信息信息任务失败：" + massage);
        }
        else {
            subTaskInfo.setAddFieldStatus(taskStatus);
        }
        subTaskInfoMapper.update(subTaskInfo);
    }

    @Override
    public void updateTargetMappingTask(String taskId, Integer taskStatus, String massage)
    {
        SubTaskInfo subTaskInfo = new SubTaskInfo();
        subTaskInfo.setId(taskId);
        subTaskInfo.setMtime(new Date());
        subTaskInfo.setTargetMappingStatus(taskStatus);
        subTaskInfo.setTargetMappingReason("字段类型映射失败：" + massage);
        subTaskInfoMapper.update(subTaskInfo);
    }

    @Override
    public void updateCreateTableTask(String mainTaskId, String taskId, String sourceId, Integer taskStatus, String massage)
    {
        SubTaskInfo subTaskInfo = new SubTaskInfo();
        if (StringUtils.isNotBlank(taskId)) {
            subTaskInfo.setId(taskId);
        }
        if (StringUtils.isNotBlank(mainTaskId)) {
            subTaskInfo.setTaskId(mainTaskId);
        }
        subTaskInfo.setMtime(new Date());
        if (TaskStatusEnum.FAIL.getCode().equals(taskStatus)) {
            if (StringUtils.isNotBlank(sourceId)) {
                subTaskInfo.setTargetId(sourceId);
            }
            subTaskInfo.setAddTargetTblStatus(taskStatus);
            subTaskInfo.setAddTargetTblReason("建表任务失败：" + massage);
        }
        else {
            subTaskInfo.setAddTargetTblStatus(taskStatus);
        }
        subTaskInfoMapper.update(subTaskInfo);
    }

    @Override
    public List<SubTaskInfo> getGroupColInfoStorageTasks(String mainTaskId)
    {
        return subTaskInfoMapper.getGroupColInfoStorageTasks(mainTaskId);
    }

    @Override
    public List<SubTaskInfo> getColInfoStorageTasks(String mainTaskId)
    {
        return subTaskInfoMapper.getColInfoStorageTasks(mainTaskId);
    }

    @Override
    public List<SubTaskInfo> getGroupCreateTableTas(String mainTaskId)
    {
        return subTaskInfoMapper.getGroupCreateTableTas(mainTaskId);
    }

    @Override
    public List<SubTaskInfo> getCreateTableTas(String mainTaskId)
    {
        return subTaskInfoMapper.getCreateTableTas(mainTaskId);
    }

    @Override
    public void updateMainTaskStatus(String mainTaskId)
    {
        taskInfoMapper.update(mainTaskId);
    }
}
