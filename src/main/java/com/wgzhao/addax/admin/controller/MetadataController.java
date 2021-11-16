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

package com.wgzhao.addax.admin.controller;

import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.dto.NodeCodeDto;
import com.wgzhao.addax.admin.dto.QueryChangeRecordDto;
import com.wgzhao.addax.admin.server.DataChangeRecordService;
import com.wgzhao.addax.admin.server.TableService;
import com.wgzhao.addax.admin.utils.EscapeUtil;
import com.wgzhao.addax.admin.vo.QueryChangeRecordVo;
import com.wgzhao.addax.admin.vo.TableMainVo;
import com.wgzhao.addax.admin.vo.TreeNodeVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;

/**
 * @author liuting
 */
@RestController
@RequestMapping("/metadata")
public class MetadataController
{
    @Resource
    private TableService tableService;

    @Resource
    private DataChangeRecordService dataChangeRecordService;

    @ApiOperation("元数据库树形菜单")
    @GetMapping(value = "queryTreeNodes")
    public ServerResponse<List<TreeNodeVo>> queryTreeNodes()
    {
        return tableService.queryTreeNodes();
    }

    @ApiOperation("获取表字段信息")
    @PostMapping(value = "getTableInfo")
    public ServerResponse<TableMainVo> getTableInfo(@RequestBody @Valid NodeCodeDto dto)
    {
        return tableService.getTableInfo(dto.getNodeCode());
    }

    @ApiOperation("元数据变更记录列表-分页")
    @PostMapping(value = "queryChangeRecordListWithPage")
    public ServerResponse<PageInfo<QueryChangeRecordVo>> queryChangeRecordListWithPage(@RequestBody QueryChangeRecordDto dto)
    {
        if (StringUtils.isNotBlank(dto.getQueryContent())) {
            dto.setQueryContent("%" + EscapeUtil.modifyLikeSql(dto.getQueryContent()) + "%");
        }
        return dataChangeRecordService.queryChangeRecordListWithPage(dto);
    }

    @ApiOperation(value = "元数据变更记录列表-导出")
    @PostMapping(value = "exportChangeRecordListWithPage")
    public void exportChangeRecordListWithPage(HttpServletResponse response,
            @RequestBody QueryChangeRecordDto dto)
    {
        dataChangeRecordService.exportChangeRecordListWithPage(response, dto);
    }
}
