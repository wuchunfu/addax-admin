package com.wgzhao.addax.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author liuting
 */
@ApiModel
public class EditJsonDto
{
    @ApiModelProperty(value = "json-ID")
    @NotBlank(message = "ID不能为空")
    private String jsonId;

    @ApiModelProperty(value = "json内容")
    @NotBlank(message = "json不能为空")
    private String json;

    public String getJsonId()
    {
        return jsonId;
    }

    public void setJsonId(String jsonId)
    {
        this.jsonId = jsonId;
    }

    public String getJson()
    {
        return json;
    }

    public void setJson(String json)
    {
        this.json = json;
    }
}
