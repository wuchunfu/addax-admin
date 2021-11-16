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
import com.wgzhao.addax.admin.dto.HdfsSourceConfigDto;
import com.wgzhao.addax.admin.dto.QuerySourceConfigDto;
import com.wgzhao.addax.admin.dto.SourceConfigDto;
import com.wgzhao.addax.admin.dto.SourceConfigIdDto;
import com.wgzhao.addax.admin.pojo.SourceConfig;
import com.wgzhao.addax.admin.vo.HdfsSourceConfigVo;
import com.wgzhao.addax.admin.vo.QuerySourceConfigVo;
import com.wgzhao.addax.admin.vo.SourceConfigSelectVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author liuting
 */
public interface SourceConfigService
{

    /**
     * 数据源列表
     * @param querySourceConfigDto 查询参数
     * @return ServerResponse<PageInfo<QuerySourceConfigVo>>
     */
    ServerResponse<PageInfo<QuerySourceConfigVo>> querySourceConfigListWithPage(QuerySourceConfigDto querySourceConfigDto);

    /**
     * 删除数据源
     * @param dto id
     * @return ServerResponse<String>
     */
    ServerResponse<String> delSourceConfig(SourceConfigIdDto dto);

    /**
     * 新增/编辑数据源
     * @param dto 参数
     * @return ServerResponse<String>
     */
    ServerResponse<String> saveOrUpdateSourceConfig(SourceConfigDto dto);

    /**
     * 下拉-获取所有数据源
     * @return ServerResponse<List<SourceConfigSelectVo>>
     */
    ServerResponse<List<SourceConfigSelectVo>> getAllSourceConfig();

    /**
     * 根据id获取数据源信息
     * @param id ID
     * @return SourceConfig
     */
    SourceConfig getSourceConfigById(String id);

    /**
     * 构建json-hdfs数据源信息
     * @param dto 参数
     * @return ServerResponse<HdfsSourceConfigVo>
     */
    ServerResponse<HdfsSourceConfigVo> getHdfsSourceConfigInfo(HdfsSourceConfigDto dto);
}
