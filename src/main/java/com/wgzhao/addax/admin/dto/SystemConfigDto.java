/*
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
