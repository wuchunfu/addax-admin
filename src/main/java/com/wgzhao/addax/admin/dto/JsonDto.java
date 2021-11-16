package com.wgzhao.addax.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

import java.util.List;

/**
 * @author liuting
 */
@ApiModel
public class JsonDto
{
    @ApiModelProperty(value = "读取数据源ID")
    @NotBlank(message = "读取数据源ID不能为空")
    private String readSourceConfigId;

    @ApiModelProperty(value = "目标数据源ID")
    @NotBlank(message = "目标数据源ID不能为空")
    private String targetSourceConfigId;

    @ApiModelProperty(value = "采集类型1:采集;2:数据服务")
    private Integer collectionType = 1;

    @ApiModelProperty(value = "reader-Json")
    @NotBlank(message = "reader不能为空")
    private String readerStr;

    @ApiModelProperty(value = "writer-Json")
    @NotBlank(message = "writer不能为空")
    private String writerStr;

    @ApiModelProperty(value = "源表字段")
    private List<SourceTableDto> sourceTableList;

    @ApiModelProperty(value = "复制数据列表")
    private List<TableDto> tableList;

    @ApiModelProperty(value = "分区字段")
    private List<SourceTableDto> partitionFieldList;

    public Integer getCollectionType()
    {
        return collectionType;
    }

    public void setCollectionType(Integer collectionType)
    {
        this.collectionType = collectionType;
    }

    public List<SourceTableDto> getPartitionFieldList()
    {
        return partitionFieldList;
    }

    public void setPartitionFieldList(List<SourceTableDto> partitionFieldList)
    {
        this.partitionFieldList = partitionFieldList;
    }

    public String getReadSourceConfigId()
    {
        return readSourceConfigId;
    }

    public void setReadSourceConfigId(String readSourceConfigId)
    {
        this.readSourceConfigId = readSourceConfigId;
    }

    public String getTargetSourceConfigId()
    {
        return targetSourceConfigId;
    }

    public void setTargetSourceConfigId(String targetSourceConfigId)
    {
        this.targetSourceConfigId = targetSourceConfigId;
    }

    public String getReaderStr()
    {
        return readerStr;
    }

    public void setReaderStr(String readerStr)
    {
        this.readerStr = readerStr;
    }

    public String getWriterStr()
    {
        return writerStr;
    }

    public void setWriterStr(String writerStr)
    {
        this.writerStr = writerStr;
    }

    public List<SourceTableDto> getSourceTableList()
    {
        return sourceTableList;
    }

    public void setSourceTableList(List<SourceTableDto> sourceTableList)
    {
        this.sourceTableList = sourceTableList;
    }

    public List<TableDto> getTableList()
    {
        return tableList;
    }

    public void setTableList(List<TableDto> tableList)
    {
        this.tableList = tableList;
    }
}
