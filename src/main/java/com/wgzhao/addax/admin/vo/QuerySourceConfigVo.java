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

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author liuting
 */
@ApiModel
public class QuerySourceConfigVo
{
    @ApiModelProperty(value = "数据源id")
    private String sourceConfigId;

    @ApiModelProperty(value = "数据源")
    private String sourceConfigName;

    @ApiModelProperty(value = "数据库类型0.识别不到类型;1.RDBMS;2.Oracle;3.MySQL;4.SQLServer;5.PostgreSQL;6.ClickHouse7.HDFS;8.DBF;9.Hbase2.0withPhoenix")
    private Integer sourceConfigType;

    @ApiModelProperty(value = "用户名")
    private String sourceConfigUserName;

    @ApiModelProperty(value = "密码")
    private String pass;

    @ApiModelProperty(value = "jdbcUrl")
    private String jdbcUrl;

    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

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

    @ApiModelProperty(value = "hive连接串")
    private String hiveConnectStr;

    @ApiModelProperty(value = "hive用户名")
    private String hiveUserName;

    @ApiModelProperty(value = "hive密码")
    private String hivePass;

    @ApiModelProperty(value = "是否启用HA0:否；1:是")
    private Integer isEnableHa;

    @ApiModelProperty(value = "集群服务名")
    private String nameServices;

    @ApiModelProperty(value = "namenode名称，用逗号“,”分隔")
    private String nameNodes;

    @ApiModelProperty(value = "节点主机名，用逗号“,”分隔")
    private String nameNodeRpc;

    public String getHiveUserName()
    {
        return hiveUserName;
    }

    public void setHiveUserName(String hiveUserName)
    {
        this.hiveUserName = hiveUserName;
    }

    public String getHivePass()
    {
        return hivePass;
    }

    public void setHivePass(String hivePass)
    {
        this.hivePass = hivePass;
    }

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

    public String getHiveConnectStr()
    {
        return hiveConnectStr;
    }

    public void setHiveConnectStr(String hiveConnectStr)
    {
        this.hiveConnectStr = hiveConnectStr;
    }

    public Integer getIsEnableHa()
    {
        return isEnableHa;
    }

    public void setIsEnableHa(Integer isEnableHa)
    {
        this.isEnableHa = isEnableHa;
    }

    public String getNameServices()
    {
        return nameServices;
    }

    public void setNameServices(String nameServices)
    {
        this.nameServices = nameServices;
    }

    public String getNameNodes()
    {
        return nameNodes;
    }

    public void setNameNodes(String nameNodes)
    {
        this.nameNodes = nameNodes;
    }

    public String getNameNodeRpc()
    {
        return nameNodeRpc;
    }

    public void setNameNodeRpc(String nameNodeRpc)
    {
        this.nameNodeRpc = nameNodeRpc;
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

    public Integer getSourceConfigType()
    {
        return sourceConfigType;
    }

    public void setSourceConfigType(Integer sourceConfigType)
    {
        this.sourceConfigType = sourceConfigType;
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

    public String getSourceConfigUserName()
    {
        return sourceConfigUserName;
    }

    public void setSourceConfigUserName(String sourceConfigUserName)
    {
        this.sourceConfigUserName = sourceConfigUserName;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public String getJdbcUrl()
    {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl)
    {
        this.jdbcUrl = jdbcUrl;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }
}
