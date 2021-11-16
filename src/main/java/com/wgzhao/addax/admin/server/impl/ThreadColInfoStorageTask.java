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
import com.wgzhao.addax.admin.enums.TaskStatusEnum;
import com.wgzhao.addax.admin.mapper.TableInfoMapper;
import com.wgzhao.addax.admin.mapper.TypeInfoMapper;
import com.wgzhao.addax.admin.pojo.SubTaskInfo;
import com.wgzhao.addax.admin.pojo.TableInfo;
import com.wgzhao.addax.admin.pojo.TypeInfo;
import com.wgzhao.addax.admin.server.TaskInfoService;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;

/**
 * @author yangkai
 */
public class ThreadColInfoStorageTask
        implements Runnable
{

    private CountDownLatch countDownLatch;

    private Connection connection;

    private SubTaskInfo subTaskInfo;

    private Integer dtype;

    private final TaskInfoService taskInfoService;

    private final TableInfoMapper tableInfoMapper;

    private final TypeInfoMapper typeInfoMapper;

    ThreadColInfoStorageTask(Connection connection, SubTaskInfo subTaskInfo, TaskInfoService taskInfoService,
            TableInfoMapper tableInfoMapper,TypeInfoMapper typeInfoMapper,Integer dtype, CountDownLatch countDownLatch)
    {
        this.connection = connection;
        this.subTaskInfo = subTaskInfo;
        this.taskInfoService = taskInfoService;
        this.tableInfoMapper = tableInfoMapper;
        this.typeInfoMapper = typeInfoMapper;
        this.dtype = dtype;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run()
    {
        Statement statement = null;
        try {
            //获取原表信息
            String sql = String.format("select * from %s where 1=2", subTaskInfo.getSourceDb() + "." + subTaskInfo.getSourceTbl());
            statement = connection.createStatement();
            ResultSetMetaData rsd = statement.executeQuery(sql).getMetaData();
            for (int i = 0; i < rsd.getColumnCount(); i++) {
                //设置源表信息
                TableInfo tableInfo = new TableInfo();
                tableInfo.setSourceId(subTaskInfo.getSourceId());
                tableInfo.setDbName(subTaskInfo.getSourceDb());
                tableInfo.setTblName(subTaskInfo.getSourceTbl());
                tableInfo.setColName(rsd.getColumnName(i + 1));
                tableInfo.setColType(rsd.getColumnTypeName(i + 1));
                tableInfo.setColLength(rsd.getPrecision(i + 1));
                tableInfo.setColPrecision(rsd.getScale(i + 1));
                tableInfo.setColPos(i + 1);
                //设置目标表信息
                TableInfo targetTableInfo = new TableInfo();
                targetTableInfo.setSourceId(subTaskInfo.getTargetId());
                targetTableInfo.setDbName(subTaskInfo.getTargetDb());
                targetTableInfo.setTblName(subTaskInfo.getTargetTbl());
                targetTableInfo.setColName(rsd.getColumnName(i + 1));
                //字段类型需要转换
                int cokType = rsd.getColumnType(i + 1);
                TypeInfo typeInfo = typeInfoMapper.selectTypeInfo(dtype, String.valueOf(cokType));
                String colType = "";
                if (typeInfo != null) {
                    colType = typeInfo.getColType();
                }
                else {
                    taskInfoService.updateTargetMappingTask( subTaskInfo.getId(), TaskStatusEnum.FAIL.getCode(), "目标表字段类型映射失败");
                }
                targetTableInfo.setColType(colType);
                targetTableInfo.setColLength(rsd.getPrecision(i + 1));
                targetTableInfo.setColPrecision(rsd.getScale(i + 1));
                targetTableInfo.setColPos(i + 1);
                tableInfoMapper.insert(tableInfo);
                tableInfoMapper.insert(targetTableInfo);
                taskInfoService.updateReadingSourceTask(null, subTaskInfo.getId(), null, TaskStatusEnum.SUCCESS.getCode(), null);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            taskInfoService.updateReadingSourceTask(null, subTaskInfo.getId(), null, TaskStatusEnum.FAIL.getCode(), throwables.toString());
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
        this.dtype = null;
    }
}
