package com.wgzhao.addax.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author liuting
 */
@ApiModel
public class QuerySourceConfigDto
{
    @ApiModelProperty(value = "数据库类型（不传此参数时代表查全部）-1.关系型数据库;7.HDFS;8.DBF;9.Hbase2.0withPhoenix")
    private Integer sourceConfigType;

    @ApiModelProperty(value = "数据源")
    private String sourceConfigName;

    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "每页显示数")
    private Integer pageSize;

    public Integer getSourceConfigType()
    {
        return sourceConfigType;
    }

    public void setSourceConfigType(Integer sourceConfigType)
    {
        this.sourceConfigType = sourceConfigType;
    }

    public String getSourceConfigName()
    {
        return sourceConfigName;
    }

    public void setSourceConfigName(String sourceConfigName)
    {
        this.sourceConfigName = sourceConfigName;
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
}
