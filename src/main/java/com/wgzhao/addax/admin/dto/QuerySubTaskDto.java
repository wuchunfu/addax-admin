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

package com.wgzhao.addax.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author liuting
 */
@ApiModel
public class QuerySubTaskDto
{
    @ApiModelProperty(value = "任务批次号")
    private String subTaskId;

    @ApiModelProperty(value = "表schema")
    private String tableSchema;

    @ApiModelProperty(value = "表名称")
    private String tableName;

    @ApiModelProperty(value = "执行结果 1:成功；2:失败；0:未完成")
    private Integer resultStatus;

    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "每页显示数")
    private Integer pageSize;

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

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public String getSubTaskId()
    {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId)
    {
        this.subTaskId = subTaskId;
    }

    public String getTableSchema()
    {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema)
    {
        this.tableSchema = tableSchema;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public Integer getResultStatus()
    {
        return resultStatus;
    }

    public void setResultStatus(Integer resultStatus)
    {
        this.resultStatus = resultStatus;
    }
}
