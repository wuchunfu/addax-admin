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

package com.wgzhao.addax.admin.server;

import com.wgzhao.addax.admin.pojo.SourceConfig;

/**
 * @author yangkai
 */
public interface SourceService
{
    /**
     * ID获取数据源信息并连接数据源
     *
     * @param sourceId 数据源ID
     * @param taskId 任务ID
     * @param mainTaskId 主任务ID
     * @param taskType 任务类型
     */
    void connectDataSource(String sourceId, String taskId, String mainTaskId, String taskType);

    /**
     * ID获取数据源信息
     *
     * @param sourceId 数据源ID
     * @return SourceConfig
     */
    SourceConfig getSourceConfig(String sourceId);

    /**
     * 删除jdbc连接信息
     * @param sourceId 数据源ID
     */
    void delDataSource(String sourceId);
}
