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

package com.wgzhao.addax.admin.enums;

/**
 * @author yangkai
 */
public enum DataBaseTypeEnum
{
    //数据库类型1.RDBMS;2.Oracle;3.MySQL;4.SQLServer;5.PostgreSQL;6.ClickHouse7.HDFS;8.DBF;9.Hbase2.0withPhoenix
    RDBMS_TYPE(1, "RDBMS"),

    ORACLE_TYPE(2, "Oracle"),

    MYSQL_TYPE(3, "MySQL"),

    SQLSERVER_TYPE(4, "SQLServer"),

    POSTGRESQL_TYPE(5, "PostgreSQL"),

    CLICKHOUSE_TYPE(6, "ClickHouse"),

    HIVE_HDFS_TYPE(7, "HDFS"),

    DBF_TYPE(8, "DBF"),

    HBASE2_TYPE(9, "Hbase20Xsql"),

    HBASE1_TYPE(10, "Hbase10Xsql");

    private final Integer code;

    private final String msg;

    DataBaseTypeEnum(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getMsg()
    {
        return msg;
    }

    public static Integer getCode(String msg)
    {
        msg = msg.toLowerCase();
        for (DataBaseTypeEnum ele : DataBaseTypeEnum.values()) {
            if (msg.contains(ele.getMsg().toLowerCase())) {
                return ele.getCode();
            }
        }
        //rdbms单独处理
        String RDBMS_1 = "jdbc:presto";
        String RDBMS_2 = "jdbc:inceptor";
        String RDBMS_3 = "jdbc:kingbase";
        String RDBMS_4 = "jdbc:db2";
        String RDBMS_5 = "jdbc:dm";
        String RDBMS_6 = "jdbc:gbasedbt";
        String RDBMS_7 = "jdbc:oscar";
        String RDBMS_8 = "jdbc:highgo";
        if (msg.contains(RDBMS_1) || msg.contains(RDBMS_2) || msg.contains(RDBMS_3) || msg.contains(RDBMS_4) ||
                msg.contains(RDBMS_5) || msg.contains(RDBMS_6) || msg.contains(RDBMS_7) || msg.contains(RDBMS_8)) {
            return RDBMS_TYPE.getCode();
        }
        return 0;
    }

    public static String getMsg(Integer code)
    {
        for (DataBaseTypeEnum ele : DataBaseTypeEnum.values()) {
            if (code.equals(ele.getCode())) {
                return ele.getMsg();
            }
        }
        return "";
    }
}
