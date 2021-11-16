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

    private String defaultfs;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getDefaultfs() {
        return defaultfs;
    }

    public void setDefaultfs(String defaultfs) {
        this.defaultfs = defaultfs == null ? null : defaultfs.trim();
    }

    public Integer getHaveKerberos() {
        return haveKerberos;
    }

    public void setHaveKerberos(Integer haveKerberos) {
        this.haveKerberos = haveKerberos;
    }

    public String getKerberosKeytabFilePath() {
        return kerberosKeytabFilePath;
    }

    public void setKerberosKeytabFilePath(String kerberosKeytabFilePath) {
        this.kerberosKeytabFilePath = kerberosKeytabFilePath == null ? null : kerberosKeytabFilePath.trim();
    }

    public String getKerberosPrincipal() {
        return kerberosPrincipal;
    }

    public void setKerberosPrincipal(String kerberosPrincipal) {
        this.kerberosPrincipal = kerberosPrincipal == null ? null : kerberosPrincipal.trim();
    }

    public String getHadoopConfig() {
        return hadoopConfig;
    }

    public void setHadoopConfig(String hadoopConfig) {
        this.hadoopConfig = hadoopConfig == null ? null : hadoopConfig.trim();
    }

    public String getHiveUserName() {
        return hiveUserName;
    }

    public void setHiveUserName(String hiveUserName) {
        this.hiveUserName = hiveUserName == null ? null : hiveUserName.trim();
    }

    public String getHivePass() {
        return hivePass;
    }

    public void setHivePass(String hivePass) {
        this.hivePass = hivePass == null ? null : hivePass.trim();
    }

    public String getHiveConnectStr() {
        return hiveConnectStr;
    }

    public void setHiveConnectStr(String hiveConnectStr) {
        this.hiveConnectStr = hiveConnectStr == null ? null : hiveConnectStr.trim();
    }

    public Integer getIsEnableHa() {
        return isEnableHa;
    }

    public void setIsEnableHa(Integer isEnableHa) {
        this.isEnableHa = isEnableHa;
    }

    public String getNameServices() {
        return nameServices;
    }

    public void setNameServices(String nameServices) {
        this.nameServices = nameServices == null ? null : nameServices.trim();
    }

    public String getNameNodes() {
        return nameNodes;
    }

    public void setNameNodes(String nameNodes) {
        this.nameNodes = nameNodes == null ? null : nameNodes.trim();
    }

    public String getNameNodeRpc() {
        return nameNodeRpc;
    }

    public void setNameNodeRpc(String nameNodeRpc) {
        this.nameNodeRpc = nameNodeRpc == null ? null : nameNodeRpc.trim();
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
}