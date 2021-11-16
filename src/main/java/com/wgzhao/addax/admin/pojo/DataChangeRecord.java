package com.wgzhao.addax.admin.pojo;

import java.util.Date;

public class DataChangeRecord {
    private String id;

    private String sourceId;

    private String dbName;

    private String tblName;

    private String colName;

    private Integer changeType;

    private Date changeTime;

    private String changeContent;

    private String sqlContent;

    private Integer targetTableStatus;

    private Integer jsonStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId == null ? null : sourceId.trim();
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName == null ? null : dbName.trim();
    }

    public String getTblName() {
        return tblName;
    }

    public void setTblName(String tblName) {
        this.tblName = tblName == null ? null : tblName.trim();
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName == null ? null : colName.trim();
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getChangeContent() {
        return changeContent;
    }

    public void setChangeContent(String changeContent) {
        this.changeContent = changeContent == null ? null : changeContent.trim();
    }

    public String getSqlContent() {
        return sqlContent;
    }

    public void setSqlContent(String sqlContent) {
        this.sqlContent = sqlContent == null ? null : sqlContent.trim();
    }

    public Integer getTargetTableStatus() {
        return targetTableStatus;
    }

    public void setTargetTableStatus(Integer targetTableStatus) {
        this.targetTableStatus = targetTableStatus;
    }

    public Integer getJsonStatus() {
        return jsonStatus;
    }

    public void setJsonStatus(Integer jsonStatus) {
        this.jsonStatus = jsonStatus;
    }
}