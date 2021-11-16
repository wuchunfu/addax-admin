package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.pojo.TaskInfo;

public interface TaskInfoMapper {
    int insert(TaskInfo record);

    int insertSelective(TaskInfo record);

    int update(String id);
}