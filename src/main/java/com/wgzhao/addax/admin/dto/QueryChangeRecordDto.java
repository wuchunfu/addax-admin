package com.wgzhao.addax.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author liuting
 */
@ApiModel
public class QueryChangeRecordDto
{
    @ApiModelProperty(value = "数据源、库名、表名")
    private String queryContent;

    @ApiModelProperty(value = "1:新增；2:删除；3:修改；4:失败")
    private Integer changeType;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "每页显示数")
    private Integer pageSize;

    public String getQueryContent()
    {
        return queryContent;
    }

    public void setQueryContent(String queryContent)
    {
        this.queryContent = queryContent;
    }

    public Integer getChangeType()
    {
        return changeType;
    }

    public void setChangeType(Integer changeType)
    {
        this.changeType = changeType;
    }

    public Integer getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(Integer pageNum)
    {
        this.pageNum = pageNum;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }
}
