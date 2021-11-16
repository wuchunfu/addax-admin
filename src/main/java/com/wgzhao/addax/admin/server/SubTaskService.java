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

import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.dto.QuerySubTaskDto;
import com.wgzhao.addax.admin.dto.TableDto;
import com.wgzhao.addax.admin.pojo.SubTaskInfo;
import com.wgzhao.addax.admin.vo.ProcessTableVo;
import com.wgzhao.addax.admin.vo.QuerySubTaskVo;
import com.wgzhao.addax.admin.vo.UserVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author liuting
 */
public interface SubTaskService
{
    void saveSubTaskInfo(String sourceId, TableDto item, String targetId, String taskId,
            Integer collectionType);

    void updateAddJsonStatus(SubTaskInfo subTaskInfo, Integer code, String s);

    int getNotFinishedCountByTaskId(String taskId);

    List<ProcessTableVo> getAllJsonProcessByTaskId(String taskId);

    ServerResponse<PageInfo<QuerySubTaskVo>> querySubTaskListWithPage(QuerySubTaskDto dto,
            UserVo userVo);

    List<SubTaskInfo> findAllByTaskId(String taskId);

    SubTaskInfo getSubTaskInfoById(String id);
}
