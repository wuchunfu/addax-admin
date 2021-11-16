package com.wgzhao.addax.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author liuting
 */
@ApiModel
public class JsonTaskVo
{
    @ApiModelProperty(value = "json-id")
    private String jsonId;

    @ApiModelProperty(value = "子任务id")
    private String subTaskId;

    @ApiModelProperty(value = "json内容")
    private String jsonContent;

    @ApiModelProperty(value = "源数据源id")
    private String sourceId;

    @ApiModelProperty(value = "源库")
    private String sourceDb;

    @ApiModelProperty(value = "源表")
    private String sourceTbl;

    @ApiModelProperty(value = "目标数据源id")
    private String targetId;

    @ApiModelProperty(value = "目标库")
    private String targetDb;

    @ApiModelProperty(value = "目标表")
    private String targetTbl;

    @ApiModelProperty(value = "采集类型1:采集;2:数据服务")
    private Integer collectType;

    public Integer getCollectType()
    {
        return collectType;
    }

    public void setCollectType(Integer collectType)
    {
        this.collectType = collectType;
    }

    public String getSourceDb()
    {
        return sourceDb;
    }

    public void setSourceDb(String sourceDb)
    {
        this.sourceDb = sourceDb;
    }

    public String getSourceTbl()
    {
        return sourceTbl;
    }

    public void setSourceTbl(String sourceTbl)
    {
        this.sourceTbl = sourceTbl;
    }

    public String getTargetDb()
    {
        return targetDb;
    }

    public void setTargetDb(String targetDb)
    {
        this.targetDb = targetDb;
    }

    public String getTargetTbl()
    {
        return targetTbl;
    }

    public void setTargetTbl(String targetTbl)
    {
        this.targetTbl = targetTbl;
    }

    public String getSourceId()
    {
        return sourceId;
    }

    public void setSourceId(String sourceId)
    {
        this.sourceId = sourceId;
    }

    public String getTargetId()
    {
        return targetId;
    }

    public void setTargetId(String targetId)
    {
        this.targetId = targetId;
    }

    public String getJsonId()
    {
        return jsonId;
    }

    public void setJsonId(String jsonId)
    {
        this.jsonId = jsonId;
    }

    public String getSubTaskId()
    {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId)
    {
        this.subTaskId = subTaskId;
    }

    public String getJsonContent()
    {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent)
    {
        this.jsonContent = jsonContent;
    }
}
