package com.wgzhao.addax.admin.server.impl;

import com.wgzhao.addax.admin.enums.TaskStatusEnum;
import com.wgzhao.addax.admin.mapper.TaskInfoMapper;
import com.wgzhao.addax.admin.pojo.TaskInfo;
import com.wgzhao.addax.admin.server.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author liuting
 */
@Service
public class TaskServiceImpl
        implements TaskService
{
    @Resource
    private TaskInfoMapper taskInfoMapper;

    @Override
    public TaskInfo saveTaskInfo(String userId)
    {
        String id =  DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setId(id);
        taskInfo.setTaskStatus(TaskStatusEnum.INITIAL.getCode());
        taskInfo.setUid(userId);
        taskInfo.setCtime(new Date());
        taskInfo.setMtime(new Date());
        int i = taskInfoMapper.insertSelective(taskInfo);
        return i > 0 ? taskInfo : null;
    }
}
