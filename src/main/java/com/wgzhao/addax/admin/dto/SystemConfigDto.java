package com.wgzhao.addax.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author liuting
 */
@ApiModel
public class SystemConfigDto
{
    @ApiModelProperty(value = "系统配置id")
    @NotBlank(message = "系统配置id不能为空")
    private String configId;

    @ApiModelProperty(value = "系统配置code")
    @NotBlank(message = "系统配置code不能为空")
    private String configCode;

    @ApiModelProperty(value = "是否开启1:开启；0:关闭")
    private Integer isOpen;

    @ApiModelProperty(value = "配置的时间")
    private String configTime;

    public String getConfigCode()
    {
        return configCode;
    }

    public void setConfigCode(String configCode)
    {
        this.configCode = configCode;
    }

    public String getConfigId()
    {
        return configId;
    }

    public void setConfigId(String configId)
    {
        this.configId = configId;
    }

    public Integer getIsOpen()
    {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen)
    {
        this.isOpen = isOpen;
    }

    public String getConfigTime()
    {
        return configTime;
    }

    public void setConfigTime(String configTime)
    {
        this.configTime = configTime;
    }
}
