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
