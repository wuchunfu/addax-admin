package com.wgzhao.addax.admin.pojo;

public class TypeInfo extends TypeInfoKey {
    private String colType;

    private String sqlTypeCode;

    private String createTableType;

    public String getColType() {
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType == null ? null : colType.trim();
    }

    public String getSqlTypeCode() {
        return sqlTypeCode;
    }

    public void setSqlTypeCode(String sqlTypeCode) {
        this.sqlTypeCode = sqlTypeCode == null ? null : sqlTypeCode.trim();
    }

    public String getCreateTableType() {
        return createTableType;
    }

    public void setCreateTableType(String createTableType) {
        this.createTableType = createTableType == null ? null : createTableType.trim();
    }
}