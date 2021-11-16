package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.pojo.SubTaskInfo;

public interface SubTaskInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SubTaskInfo record);

    int insertSelective(SubTaskInfo record);

    SubTaskInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SubTaskInfo record);

    int updateByPrimaryKey(SubTaskInfo record);
}