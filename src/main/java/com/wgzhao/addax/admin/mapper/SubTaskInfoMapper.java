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

package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.pojo.SubTaskInfo;

import java.util.List;

public interface SubTaskInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SubTaskInfo record);

    int insertSelective(SubTaskInfo record);

    SubTaskInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SubTaskInfo record);

    int updateByPrimaryKey(SubTaskInfo record);

    int update(SubTaskInfo record);

    /**
     * 主任务ID查询未执行的读源任务(根据源ID分组)
     */
    List<SubTaskInfo> getGroupColInfoStorageTasks(String mainTaskId);

    /**
     * 主任务ID查询未执行的读源任务
     */
    List<SubTaskInfo> getColInfoStorageTasks(String mainTaskId);

    /**
     * 主任务ID查询需要建表并且源信息任务已完成的 (根据目标源ID分组)
     */
    List<SubTaskInfo> getGroupCreateTableTas(String mainTaskId);

    /**
     * 主任务ID查询需要建表并且源信息任务已完成的
     */
    List<SubTaskInfo> getCreateTableTas(String mainTaskId);



}