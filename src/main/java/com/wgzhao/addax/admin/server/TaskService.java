package com.wgzhao.addax.admin.server;

import com.wgzhao.addax.admin.pojo.TaskInfo;

/**
 * @author liuting
 */
public interface TaskService
{
    TaskInfo saveTaskInfo(String userId);
}
