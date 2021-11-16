package com.wgzhao.addax.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author liuting
 */
@ApiModel
public class SourceConfigIdDto
{
    @ApiModelProperty(value = "数据源ID")
    @NotBlank(message = "ID不能为空")
    private String sourceConfigId;

    public String getSourceConfigId()
    {
        return sourceConfigId;
    }

    public void setSourceConfigId(String sourceConfigId)
    {
        this.sourceConfigId = sourceConfigId;
    }
}
