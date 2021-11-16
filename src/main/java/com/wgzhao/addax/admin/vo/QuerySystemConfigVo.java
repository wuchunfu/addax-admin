package com.wgzhao.addax.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author liuting
 */
@ApiModel
public class QuerySystemConfigVo
{
    @ApiModelProperty(value = "系统配置id")
    private String configId;

    @ApiModelProperty(value = "系统配置code")
    private String configCode;

    @ApiModelProperty(value = "配置名")
    private String configName;

    @ApiModelProperty(value = "配置策略")
    private String content;

    @ApiModelProperty(value = "是否开启1:开启；0:关闭")
    private Integer isOpen;

    @ApiModelProperty(value = "重复周期0:无；1:每天")
    private Integer cycle;

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

    public String getConfigName()
    {
        return configName;
    }

    public void setConfigName(String configName)
    {
        this.configName = configName;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Integer getIsOpen()
    {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen)
    {
        this.isOpen = isOpen;
    }

    public Integer getCycle()
    {
        return cycle;
    }

    public void setCycle(Integer cycle)
    {
        this.cycle = cycle;
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
