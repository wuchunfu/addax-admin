package com.wgzhao.addax.admin.server.impl;

import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.dto.QuerySubTaskDto;
import com.wgzhao.addax.admin.dto.TableDto;
import com.wgzhao.addax.admin.enums.SubTaskStatusEnum;
import com.wgzhao.addax.admin.mapper.SubTaskInfoCustomMapper;
import com.wgzhao.addax.admin.mapper.SubTaskInfoMapper;
import com.wgzhao.addax.admin.pojo.SubTaskInfo;
import com.wgzhao.addax.admin.server.SubTaskService;
import com.wgzhao.addax.admin.utils.UUIDUtil;
import com.wgzhao.addax.admin.vo.ProcessTableVo;
import com.wgzhao.addax.admin.vo.QuerySubTaskVo;
import com.wgzhao.addax.admin.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
 * @author liuting
 */
@Service
public class SubTaskServiceImpl
        implements SubTaskService
{
    @Resource
    private SubTaskInfoMapper subTaskInfoMapper;

    @Resource SubTaskInfoCustomMapper subTaskInfoCustomMapper;

    @Override
    public void saveSubTaskInfo(String sourceId, TableDto item, String targetId, String taskId,
            Integer collectionType)
    {
        String id = UUIDUtil.generate();
        SubTaskInfo subTaskInfo = new SubTaskInfo();
        subTaskInfo.setId(id);
        subTaskInfo.setSourceId(sourceId);
        subTaskInfo.setSourceDb(item.getSourceTableSchema());
        subTaskInfo.setSourceTbl(item.getSourceTableName());
        subTaskInfo.setTargetId(targetId);
        subTaskInfo.setTargetDb(item.getTargetTableSchema());
        subTaskInfo.setTargetTbl(item.getTargetTableName());
        subTaskInfo.setIsAddTargetTbl(item.getIsAddTargetTableFlag());
        subTaskInfo.setAddTargetTblStatus(item.getIsAddTargetTableFlag().equals(0) ?
                SubTaskStatusEnum.SUCCEED.getCode() : SubTaskStatusEnum.INITIAL.getCode());
        subTaskInfo.setAddFieldStatus(SubTaskStatusEnum.INITIAL.getCode());
        subTaskInfo.setAddJsonStatus(SubTaskStatusEnum.INITIAL.getCode());
        subTaskInfo.setTargetMappingStatus(SubTaskStatusEnum.SUCCEED.getCode());
        subTaskInfo.setTaskId(taskId);
        subTaskInfo.setCollectType(collectionType);
        subTaskInfo.setCtime(new Date());
        subTaskInfoMapper.insertSelective(subTaskInfo);
    }

    @Override
    public void updateAddJsonStatus(SubTaskInfo subTaskInfo, Integer code, String s)
    {
        subTaskInfo.setAddTargetTblStatus(null);
        subTaskInfo.setAddJsonStatus(code);
        subTaskInfo.setAddJsonReason(s);
        subTaskInfo.setMtime(new Date());
        subTaskInfoMapper.updateByPrimaryKeySelective(subTaskInfo);
    }

    @Override
    public int getNotFinishedCountByTaskId(String taskId)
    {
        return subTaskInfoCustomMapper.getNotFinishedCountByTaskId(taskId);
    }

    @Override
    public List<ProcessTableVo> getAllJsonProcessByTaskId(String taskId)
    {
        return subTaskInfoCustomMapper.getAllJsonProcessByTaskId(taskId);
    }

    @Override
    public ServerResponse<PageInfo<QuerySubTaskVo>> querySubTaskListWithPage(QuerySubTaskDto dto,
            UserVo userVo)
    {
        Integer pageNum = dto.getPageNum();
        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }
        Integer pageSize = dto.getPageSize();
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<QuerySubTaskVo> jsonList = subTaskInfoCustomMapper.querySubTaskListWithPage(dto,
                userVo.getUserId());
        PageInfo<QuerySubTaskVo> pageResult = new PageInfo<>(jsonList);
        pageResult.setList(jsonList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public List<SubTaskInfo> findAllByTaskId(String taskId)
    {
        return subTaskInfoCustomMapper.findAllByTaskId(taskId);
    }

    @Override
    public SubTaskInfo getSubTaskInfoById(String id)
    {
        return subTaskInfoMapper.selectByPrimaryKey(id);
    }
}