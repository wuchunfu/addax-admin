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

package com.wgzhao.addax.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author liuting
 */
@ApiModel
public class SourceConfigDto
{
    @ApiModelProperty(value = "数据源ID(编辑时传入)")
    private String sourceConfigId;

    @NotBlank(message = "数据源不能为空")
    @ApiModelProperty(value = "数据源")
    private String sourceConfigName;

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String sourceConfigUserName;

    @ApiModelProperty(value = "密码")
    private String pass;

    @ApiModelProperty(value = "jdbcUrl")
    private String jdbcUrl;

    /**
     * 以下参数为HDFS类型时需要
     */
    @ApiModelProperty(value = "数据库类型(非关系型数据库才需要传此字段)7.Hive/HDFS;8.DBF;9.Hbase2.0withPhoenix;10.Hbase1.0withPhoenix")
    private Integer sourceConfigType;

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

    public Integer getIsEnableHa()
    {
        return isEnableHa;
    }

    public void setIsEnableHa(Integer isEnableHa)
    {
        this.isEnableHa = isEnableHa;
    }

    public String getHiveConnectStr()
    {
        return hiveConnectStr;
    }

    public void setHiveConnectStr(String hiveConnectStr)
    {
        this.hiveConnectStr = hiveConnectStr;
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

    public Integer getSourceConfigType()
    {
        return sourceConfigType;
    }

    public void setSourceConfigType(Integer sourceConfigType)
    {
        this.sourceConfigType = sourceConfigType;
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
}
