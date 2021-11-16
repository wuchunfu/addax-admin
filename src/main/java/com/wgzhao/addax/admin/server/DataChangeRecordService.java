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

package com.wgzhao.addax.admin.server;

import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.dto.QueryChangeRecordDto;
import com.wgzhao.addax.admin.pojo.DataChangeRecord;
import com.wgzhao.addax.admin.vo.QueryChangeRecordVo;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.List;

/**
 * @author liuting
 */
public interface DataChangeRecordService
{

    ServerResponse<PageInfo<QueryChangeRecordVo>> queryChangeRecordListWithPage(QueryChangeRecordDto dto);

    void exportChangeRecordListWithPage(HttpServletResponse response, QueryChangeRecordDto dto);

    List<DataChangeRecord> getRecordsByBeginAndEndTime(String beginTime, String endTime);

    Date getUpdateTimeByNodeCode(String nodeCode);
}
