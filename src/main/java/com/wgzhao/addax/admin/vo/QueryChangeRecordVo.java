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

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author liuting
 */
@ApiModel
public class QueryChangeRecordVo
{
    @ApiModelProperty(value = "数据源")
    @ExcelProperty(value = "数据源", index = 0)
    private String sourceName;

    @ApiModelProperty(value = "数据库")
    @ExcelProperty(value = "数据库", index = 1)
    private String dbName;

    @ApiModelProperty(value = "表")
    @ExcelProperty(value = "表", index = 2)
    private String tblName;

    @ApiModelProperty(value = "1:新增；2:删除；3:修改；4:失败")
    @ExcelIgnore
    private Integer changeType;

    @ApiModelProperty(value = "描述：1:新增；2:删除；3:修改；4:失败")
    @ExcelProperty(value = "变更类型", index = 3)
    private String changeTypeDesc;

    @ExcelProperty(value = "变更时间", index = 4)
    @ApiModelProperty(value = "变更时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date changeTime;

    @ApiModelProperty(value = "变更内容")
    @ExcelProperty(value = "变更内容", index = 5)
    private String changeContent;

    @ApiModelProperty(value = "数据库执行语句")
    @ExcelProperty(value = "数据库执行语句", index = 6)
    private String sqlContent;

    @ApiModelProperty(value = "目标表状态0:初始状态；1:成功；2:失败")
    @ExcelIgnore
    private Integer targetTableStatus;

    @ApiModelProperty(value = "描述：目标表状态0:初始状态；1:成功；2:失败")
    @ExcelProperty(value = "目标表", index = 7)
    private String targetTableStatusDesc;

    @ApiModelProperty(value = "构建Json状态0:初始状态；1:成功；2:失败")
    @ExcelIgnore
    private Integer jsonStatus;

    @ApiModelProperty(value = "描述：构建Json状态0:初始状态；1:成功；2:失败")
    @ExcelProperty(value = "构建JSON", index = 8)
    private String jsonStatusDesc;

    public String getChangeTypeDesc()
    {
        return changeTypeDesc;
    }

    public void setChangeTypeDesc(String changeTypeDesc)
    {
        this.changeTypeDesc = changeTypeDesc;
    }

    public String getTargetTableStatusDesc()
    {
        return targetTableStatusDesc;
    }

    public void setTargetTableStatusDesc(String targetTableStatusDesc)
    {
        this.targetTableStatusDesc = targetTableStatusDesc;
    }

    public String getJsonStatusDesc()
    {
        return jsonStatusDesc;
    }

    public void setJsonStatusDesc(String jsonStatusDesc)
    {
        this.jsonStatusDesc = jsonStatusDesc;
    }

    public String getSourceName()
    {
        return sourceName;
    }

    public void setSourceName(String sourceName)
    {
        this.sourceName = sourceName;
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

    public Integer getChangeType()
    {
        return changeType;
    }

    public void setChangeType(Integer changeType)
    {
        this.changeType = changeType;
    }

    public Date getChangeTime()
    {
        return changeTime;
    }

    public void setChangeTime(Date changeTime)
    {
        this.changeTime = changeTime;
    }

    public String getChangeContent()
    {
        return changeContent;
    }

    public void setChangeContent(String changeContent)
    {
        this.changeContent = changeContent;
    }

    public String getSqlContent()
    {
        return sqlContent;
    }

    public void setSqlContent(String sqlContent)
    {
        this.sqlContent = sqlContent;
    }

    public Integer getTargetTableStatus()
    {
        return targetTableStatus;
    }

    public void setTargetTableStatus(Integer targetTableStatus)
    {
        this.targetTableStatus = targetTableStatus;
    }

    public Integer getJsonStatus()
    {
        return jsonStatus;
    }

    public void setJsonStatus(Integer jsonStatus)
    {
        this.jsonStatus = jsonStatus;
    }
}
