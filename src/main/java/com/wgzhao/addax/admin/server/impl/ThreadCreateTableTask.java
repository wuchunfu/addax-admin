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

package com.wgzhao.addax.admin.server.impl;

import com.wgzhao.addax.admin.enums.TaskStatusEnum;
import com.wgzhao.addax.admin.mapper.TableInfoMapper;
import com.wgzhao.addax.admin.pojo.SubTaskInfo;
import com.wgzhao.addax.admin.pojo.TableInfo;
import com.wgzhao.addax.admin.server.TaskInfoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author yangkai
 */
public class ThreadCreateTableTask
        implements Runnable
{

    private CountDownLatch countDownLatch;

    private Connection connection;

    private SubTaskInfo subTaskInfo;

    private final TaskInfoService taskInfoService;

    private final TableInfoMapper tableInfoMapper;

    ThreadCreateTableTask(Connection connection, SubTaskInfo subTaskInfo,
            TaskInfoService taskInfoService,TableInfoMapper tableInfoMapper,
            CountDownLatch countDownLatch)
    {
        this.connection = connection;
        this.subTaskInfo = subTaskInfo;
        this.taskInfoService = taskInfoService;
        this.tableInfoMapper = tableInfoMapper;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run()
    {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String creatable = String.format("CREATE TABLE %s", subTaskInfo.getTargetDb() + "." + subTaskInfo.getTargetTbl());
            //sql拼接
            StringBuilder sql = new StringBuilder();
            TableInfo tableInfo = new TableInfo();
            tableInfo.setSourceId(subTaskInfo.getTargetId());
            tableInfo.setDbName(subTaskInfo.getTargetDb());
            tableInfo.setTblName(subTaskInfo.getTargetTbl());
            List<TableInfo> tableInfoList = tableInfoMapper.getTableInfos(tableInfo);
            for (TableInfo table : tableInfoList) {
                sql.append(table.getColName()).append(" ");
                //判断精度是否大于0
                if (table.getColPrecision() > 0) {
                    sql.append(table.getColType()).append(" (").append(table.getColLength()).append(",").append(table.getColPrecision()).append("),");
                }
                else {
                    sql.append(table.getColType()).append(" (").append(table.getColLength()).append("),");
                }
            }
            sql.deleteCharAt(sql.length() - 1);
            creatable += "(" + sql + ")";
            statement.executeUpdate(creatable);
            taskInfoService.updateCreateTableTask(null, subTaskInfo.getId(), null, TaskStatusEnum.SUCCESS.getCode(), null);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            taskInfoService.updateCreateTableTask(null, subTaskInfo.getId(), null, TaskStatusEnum.FAIL.getCode(), throwables.toString());
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            countDownLatch.countDown();
        }
        this.connection = null;
        this.subTaskInfo = null;
    }
}