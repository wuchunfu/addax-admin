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
