package com.wgzhao.addax.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
/**
 * @author liuting
 */
@ApiModel
public class HdfsSourceConfigDto
{
    @ApiModelProperty(value = "reader数据源id")
    @NotBlank(message = "reader数据源id不能为空")
    private String readerSourceConfigId;

    @ApiModelProperty(value = "writer数据源id")
    @NotBlank(message = "writer数据源id不能为空")
    private String writerSourceConfigId;

    public String getReaderSourceConfigId()
    {
        return readerSourceConfigId;
    }

    public void setReaderSourceConfigId(String readerSourceConfigId)
    {
        this.readerSourceConfigId = readerSourceConfigId;
    }

    public String getWriterSourceConfigId()
    {
        return writerSourceConfigId;
    }

    public void setWriterSourceConfigId(String writerSourceConfigId)
    {
        this.writerSourceConfigId = writerSourceConfigId;
    }
}
