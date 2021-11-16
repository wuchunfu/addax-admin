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

public class TypeInfo
{
    private Integer dtype;

    private String colType;

    private String sqlType;

    private String sqlTypeCode;

    public Integer getDtype() {
        return dtype;
    }

    public void setDtype(Integer dtype) {
        this.dtype = dtype;
    }

    public String getColType() {
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType == null ? null : colType.trim();
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType == null ? null : sqlType.trim();
    }

    public String getSqlTypeCode() {
        return sqlTypeCode;
    }

    public void setSqlTypeCode(String sqlTypeCode) {
        this.sqlTypeCode = sqlTypeCode == null ? null : sqlTypeCode.trim();
    }
}