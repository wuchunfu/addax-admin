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