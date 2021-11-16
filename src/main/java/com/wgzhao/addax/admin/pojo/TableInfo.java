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

public class TableInfo {
    private String sourceId;

    private String dbName;

    private String tblName;

    private String colName;

    private String colType;

    private Integer colLength;

    private Integer colPrecision;

    private Integer colPos;

    private Integer colDelStatus;

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

    public String getColType() {
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType == null ? null : colType.trim();
    }

    public Integer getColLength() {
        return colLength;
    }

    public void setColLength(Integer colLength) {
        this.colLength = colLength;
    }

    public Integer getColPrecision() {
        return colPrecision;
    }

    public void setColPrecision(Integer colPrecision) {
        this.colPrecision = colPrecision;
    }

    public Integer getColPos() {
        return colPos;
    }

    public void setColPos(Integer colPos) {
        this.colPos = colPos;
    }

    public Integer getColDelStatus()
    {
        return colDelStatus;
    }

    public void setColDelStatus(Integer colDelStatus)
    {
        this.colDelStatus = colDelStatus;
    }
}