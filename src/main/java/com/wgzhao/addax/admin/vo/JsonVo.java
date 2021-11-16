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
public class JsonVo
{
    @ApiModelProperty(value = "json-id")
    private String jsonId;

    @ApiModelProperty(value = "json文件名")
    private String jsonName;

    @ApiModelProperty(value = "创建人")
    private String userName;

    @ApiModelProperty(value = "json内容")
    private String json;

    public String getJsonId()
    {
        return jsonId;
    }

    public void setJsonId(String jsonId)
    {
        this.jsonId = jsonId;
    }

    public String getJsonName()
    {
        return jsonName;
    }

    public void setJsonName(String jsonName)
    {
        this.jsonName = jsonName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
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
