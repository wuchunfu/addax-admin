package com.wgzhao.addax.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author liuting
 */
@ApiModel
public class TableMainVo
{
    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "表字段信息")
    private List<TableInfoVo> tableInfoList;

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public List<TableInfoVo> getTableInfoList()
    {
        return tableInfoList;
    }

    public void setTableInfoList(List<TableInfoVo> tableInfoList)
    {
        this.tableInfoList = tableInfoList;
    }
}
