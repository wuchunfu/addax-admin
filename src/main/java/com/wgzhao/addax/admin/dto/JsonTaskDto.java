package com.wgzhao.addax.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author liuting
 */
@ApiModel
public class JsonTaskDto
{
    @ApiModelProperty(value = "总任务ID")
    @NotBlank(message = "总任务ID不能为空")
    private String taskId;

    @ApiModelProperty(value = "编辑json需要传的参数-Json描述")
    private String jsonDesc;

    public String getJsonDesc()
    {
        return jsonDesc;
    }

    public void setJsonDesc(String jsonDesc)
    {
        this.jsonDesc = jsonDesc;
    }

    public String getTaskId()
    {
        return taskId;
    }

    public void setTaskId(String taskId)
    {
        this.taskId = taskId;
    }
}
