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

import com.wgzhao.addax.admin.dto.QueryJsonDto;
import com.wgzhao.addax.admin.pojo.JsonInfo;
import com.wgzhao.addax.admin.vo.JsonTaskVo;
import com.wgzhao.addax.admin.vo.JsonVo;
import com.wgzhao.addax.admin.vo.QueryJsonVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsonInfoCustomMapper
{
    List<QueryJsonVo> queryJsonListWithPage(QueryJsonDto queryJsonDto);

    List<JsonInfo> getJsonListByTaskId(String taskId);

    List<JsonVo> getJsonAndUserListByTaskId(String taskId);

    List<JsonTaskVo> getNeedUpdateList(@Param("sourceId") String sourceId, @Param("dbName") String dbName,
            @Param("tblName") String tblName);
}