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

/**
 * @author liuting
 */
@ApiModel
public class TableDto
{
    @ApiModelProperty(value = "源表schema")
    private String sourceTableSchema;

    @ApiModelProperty(value = "源表名称")
    private String sourceTableName;

    @ApiModelProperty(value = "目标表schema")
    private String targetTableSchema;

    @ApiModelProperty(value = "目标表名称")
    private String targetTableName;

    @ApiModelProperty(value = "是否新增目标表 1:是；0:否")
    private Integer isAddTargetTableFlag;

    public String getSourceTableSchema()
    {
        return sourceTableSchema;
    }

    public void setSourceTableSchema(String sourceTableSchema)
    {
        this.sourceTableSchema = sourceTableSchema;
    }

    public String getSourceTableName()
    {
        return sourceTableName;
    }

    public void setSourceTableName(String sourceTableName)
    {
        this.sourceTableName = sourceTableName;
    }

    public String getTargetTableSchema()
    {
        return targetTableSchema;
    }

    public void setTargetTableSchema(String targetTableSchema)
    {
        this.targetTableSchema = targetTableSchema;
    }

    public String getTargetTableName()
    {
        return targetTableName;
    }

    public void setTargetTableName(String targetTableName)
    {
        this.targetTableName = targetTableName;
    }

    public Integer getIsAddTargetTableFlag()
    {
        return isAddTargetTableFlag;
    }

    public void setIsAddTargetTableFlag(Integer isAddTargetTableFlag)
    {
        this.isAddTargetTableFlag = isAddTargetTableFlag;
    }
}
