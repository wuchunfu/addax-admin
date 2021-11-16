package com.wgzhao.addax.admin.pojo;

public class TypeInfoKey {
    private Integer dtype;

    private String sqlType;

    public Integer getDtype() {
        return dtype;
    }

    public void setDtype(Integer dtype) {
        this.dtype = dtype;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType == null ? null : sqlType.trim();
    }
}