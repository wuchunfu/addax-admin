package com.wgzhao.addax.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author liuting
 */
@ApiModel
public class SourceConfigVo
{
    @ApiModelProperty(value = "数据源id")
    private String sourceConfigId;

    @ApiModelProperty(value = "数据源")
    private String sourceConfigName;

    @ApiModelProperty(value = "数据库类型1.RDBMS;2.Oracle;3.MySQL;4.SQLServer;5.PostgreSQL;6.ClickHouse7.HDFS;8.DBF;9.Hbase2.0withPhoenix")
    private String dataBaseType;

    @ApiModelProperty(value = "要读取的文件路径")
    private String path;

    @ApiModelProperty(value = "Hadoop hdfs 文件系统namenode节点地址")
    private String defaultFS;

    @ApiModelProperty(value = "是否有Kerberos认证0:否；1:是")
    private Integer haveKerberos;

    @ApiModelProperty(value = "Kerberos认证 keytab文件路径")
    private String kerberosKeytabFilePath;

    @ApiModelProperty(value = "Kerberos认证Principal名")
    private String kerberosPrincipal;

    @ApiModelProperty(value = "Hadoop 相关的高级参数")
    private String hadoopConfig;

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getDefaultFS()
    {
        return defaultFS;
    }

    public void setDefaultFS(String defaultFS)
    {
        this.defaultFS = defaultFS;
    }

    public Integer getHaveKerberos()
    {
        return haveKerberos;
    }

    public void setHaveKerberos(Integer haveKerberos)
    {
        this.haveKerberos = haveKerberos;
    }

    public String getKerberosKeytabFilePath()
    {
        return kerberosKeytabFilePath;
    }

    public void setKerberosKeytabFilePath(String kerberosKeytabFilePath)
    {
        this.kerberosKeytabFilePath = kerberosKeytabFilePath;
    }

    public String getKerberosPrincipal()
    {
        return kerberosPrincipal;
    }

    public void setKerberosPrincipal(String kerberosPrincipal)
    {
        this.kerberosPrincipal = kerberosPrincipal;
    }

    public String getHadoopConfig()
    {
        return hadoopConfig;
    }

    public void setHadoopConfig(String hadoopConfig)
    {
        this.hadoopConfig = hadoopConfig;
    }

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
