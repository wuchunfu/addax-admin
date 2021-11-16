package com.wgzhao.addax.admin.pojo;

public class TableInfo extends TableInfoKey {
    private String colType;

    private Integer colLength;

    private Integer colPrecision;

    private Integer colPos;

    private Integer isNull;

    private String colNotes;

    private Integer colDelStatus;

    private String tableMainId;

    private String createTableType;

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

    public Integer getIsNull() {
        return isNull;
    }

    public void setIsNull(Integer isNull) {
        this.isNull = isNull;
    }

    public String getColNotes() {
        return colNotes;
    }

    public void setColNotes(String colNotes) {
        this.colNotes = colNotes == null ? null : colNotes.trim();
    }

    public Integer getColDelStatus() {
        return colDelStatus;
    }

    public void setColDelStatus(Integer colDelStatus) {
        this.colDelStatus = colDelStatus;
    }

    public String getTableMainId() {
        return tableMainId;
    }

    public void setTableMainId(String tableMainId) {
        this.tableMainId = tableMainId == null ? null : tableMainId.trim();
    }

    public String getCreateTableType() {
        return createTableType;
    }

    public void setCreateTableType(String createTableType) {
        this.createTableType = createTableType == null ? null : createTableType.trim();
    }
}