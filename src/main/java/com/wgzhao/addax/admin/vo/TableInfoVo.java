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
public class TableInfoVo
{
    @ApiModelProperty(value = "数据源id")
    private String sourceId;

    @ApiModelProperty(value = "库名")
    private String dbName;

    @ApiModelProperty(value = "表名")
    private String tblName;

    @ApiModelProperty(value = "字段名称")
    private String colName;

    @ApiModelProperty(value = "字段类型")
    private String colType;

    @ApiModelProperty(value = "字段长度")
    private Integer colLength;

    @ApiModelProperty(value = "精度")
    private Integer colPrecision;

    @ApiModelProperty(value = "是否为空")
    private Integer isNull;

    @ApiModelProperty(value = "注释")
    private String colNotes;

    public String getSourceId()
    {
        return sourceId;
    }

    public void setSourceId(String sourceId)
    {
        this.sourceId = sourceId;
    }

    public String getDbName()
    {
        return dbName;
    }

    public void setDbName(String dbName)
    {
        this.dbName = dbName;
    }

    public String getTblName()
    {
        return tblName;
    }

    public void setTblName(String tblName)
    {
        this.tblName = tblName;
    }

    public String getColName()
    {
        return colName;
    }

    public void setColName(String colName)
    {
        this.colName = colName;
    }

    public String getColType()
    {
        return colType;
    }

    public void setColType(String colType)
    {
        this.colType = colType;
    }

    public Integer getColLength()
    {
        return colLength;
    }

    public void setColLength(Integer colLength)
    {
        this.colLength = colLength;
    }

    public Integer getColPrecision()
    {
        return colPrecision;
    }

    public void setColPrecision(Integer colPrecision)
    {
        this.colPrecision = colPrecision;
    }

    public Integer getIsNull()
    {
        return isNull;
    }

    public void setIsNull(Integer isNull)
    {
        this.isNull = isNull;
    }

    public String getColNotes()
    {
        return colNotes;
    }

    public void setColNotes(String colNotes)
    {
        this.colNotes = colNotes;
    }
}
