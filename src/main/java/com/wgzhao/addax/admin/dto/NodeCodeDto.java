package com.wgzhao.addax.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author liuting
 */
@ApiModel
public class NodeCodeDto
{
    @ApiModelProperty(value = "节点id")
    @NotBlank(message = "节点id不能为空")
    private String nodeCode;

    public String getNodeCode()
    {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode)
    {
        this.nodeCode = nodeCode;
    }
}
