package com.wgzhao.addax.admin.pojo;

import java.util.Date;

public class JsonRelationInfo {
    private String id;

    private String sourceId;

    private String sourceDb;

    private String sourceTbl;

    private String targetId;

    private String targetDb;

    private String targetTbl;

    private String jid;

    private Date ctime;

    private Date mtime;

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

    public String getSourceDb() {
        return sourceDb;
    }

    public void setSourceDb(String sourceDb) {
        this.sourceDb = sourceDb == null ? null : sourceDb.trim();
    }

    public String getSourceTbl() {
        return sourceTbl;
    }

    public void setSourceTbl(String sourceTbl) {
        this.sourceTbl = sourceTbl == null ? null : sourceTbl.trim();
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId == null ? null : targetId.trim();
    }

    public String getTargetDb() {
        return targetDb;
    }

    public void setTargetDb(String targetDb) {
        this.targetDb = targetDb == null ? null : targetDb.trim();
    }

    public String getTargetTbl() {
        return targetTbl;
    }

    public void setTargetTbl(String targetTbl) {
        this.targetTbl = targetTbl == null ? null : targetTbl.trim();
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid == null ? null : jid.trim();
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