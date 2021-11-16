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
public class SourceConfigSelectVo
{
    @ApiModelProperty(value = "数据源id")
    private String sourceConfigId;

    @ApiModelProperty(value = "数据源")
    private String sourceConfigName;

    @ApiModelProperty(value = "数据库类型1.RDBMS;2.Oracle;3.MySQL;4.SQLServer;5.PostgreSQL;6.ClickHouse7.HDFS;8.DBF;9.Hbase2.0withPhoenix")
    private String dataBaseType;

    public String getSourceConfigId()
    {
        return sourceConfigId;
    }

    public void setSourceConfigId(String sourceConfigId)
    {
        this.sourceConfigId = sourceConfigId;
    }

    public String getSourceConfigName()
    {
        return sourceConfigName;
    }

    public void setSourceConfigName(String sourceConfigName)
    {
        this.sourceConfigName = sourceConfigName;
    }

    public String getDataBaseType()
    {
        return dataBaseType;
    }

    public void setDataBaseType(String dataBaseType)
    {
        this.dataBaseType = dataBaseType;
    }
}
