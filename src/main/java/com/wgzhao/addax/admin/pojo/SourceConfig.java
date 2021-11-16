package com.wgzhao.addax.admin.pojo;

import java.util.Date;

public class SourceConfig {
    private String id;

    private String name;

    private String user;

    private String pass;

    private String dsn;

    private Integer dtype;

    private Integer dstatus;

    private String path;

    private String defaultFS;

    private Integer haveKerberos;

    private String kerberosKeytabFilePath;

    private String kerberosPrincipal;

    private String hadoopConfig;

    private String hiveUserName;

    private String hivePass;

    private String hiveConnectStr;

    private Integer isEnableHa;

    private String nameServices;

    private String nameNodes;

    private String nameNodeRpc;

    private Date ctime;

    private Date mtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass == null ? null : pass.trim();
    }

    public String getDsn() {
        return dsn;
    }

    public void setDsn(String dsn) {
        this.dsn = dsn == null ? null : dsn.trim();
    }

    public Integer getDtype() {
        return dtype;
    }

    public void setDtype(Integer dtype) {
        this.dtype = dtype;
    }

    public Integer getDstatus() {
        return dstatus;
    }

    public void setDstatus(Integer dstatus) {
        this.dstatus = dstatus;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
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

    public String getHadoopConfig()
    {
        return hadoopConfig;
    }

    public void setHadoopConfig(String hadoopConfig)
    {
        this.hadoopConfig = hadoopConfig;
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

    public void setIsEnableHa(Integer enableHa)
    {
        isEnableHa = enableHa;
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

    public String getNameServices()
    {
        return nameServices;
    }

    public void setNameServices(String nameServices)
    {
        this.nameServices = nameServices;
    }

}