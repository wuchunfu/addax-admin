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
import com.wgzhao.addax.admin.dto.EditJsonDto;
import com.wgzhao.addax.admin.dto.JsonDto;
import com.wgzhao.addax.admin.dto.JsonTaskDto;
import com.wgzhao.addax.admin.dto.QueryJsonDto;
import com.wgzhao.addax.admin.dto.QuerySubTaskDto;
import com.wgzhao.addax.admin.dto.RandomStrDto;
import com.wgzhao.addax.admin.server.JsonService;
import com.wgzhao.addax.admin.server.SubTaskService;
import com.wgzhao.addax.admin.support.BaseController;
import com.wgzhao.addax.admin.utils.EscapeUtil;
import com.wgzhao.addax.admin.utils.UUIDUtil;
import com.wgzhao.addax.admin.vo.JsonProcessVo;
import com.wgzhao.addax.admin.vo.JsonVo;
import com.wgzhao.addax.admin.vo.QueryJsonVo;
import com.wgzhao.addax.admin.vo.QuerySubTaskVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.List;

/**
 * @author liuting
 */
@RestController
@RequestMapping("/json")
//@DependsOn("hsCacheUtils")
public class JsonController
        extends BaseController
{
    @Resource
    private JsonService jsonService;

    @Resource
    private SubTaskService subTaskService;

    @ApiOperation("json列表-分页")
    @PostMapping(value = "queryJsonListWithPage")
    public ServerResponse<PageInfo<QueryJsonVo>> queryJsonListWithPage(@RequestBody QueryJsonDto queryJsonDto)
    {
        if (StringUtils.isNotBlank(queryJsonDto.getJsonName())) {
            queryJsonDto.setJsonName("%" + EscapeUtil.modifyLikeSql(queryJsonDto.getJsonName()) + "%");
        }
        if (StringUtils.isNotBlank(queryJsonDto.getUserName())) {
            queryJsonDto.setUserName("%" + EscapeUtil.modifyLikeSql(queryJsonDto.getUserName()) + "%");
        }
        return jsonService.queryJsonListWithPage(queryJsonDto);
    }

    @ApiOperation("编辑json")
    @PostMapping(value = "updateJsonInfo")
    public ServerResponse<String> updateJsonInfo(@RequestBody @Valid EditJsonDto editJsonDto)
    {
        return jsonService.updateJsonInfo(editJsonDto);
    }

    @ApiOperation("生成json")
    @PostMapping(value = "generateJson")
    public ServerResponse<String> generateJson(@RequestBody @Valid JsonDto jsonDto)
    {
        //构建json前的校验
        jsonService.verification(jsonDto);
        //构建json
        String randomStr = UUIDUtil.getStringRandom(10);
        jsonService.generateJson(jsonDto, getLoginAuthUser(), randomStr);
        return ServerResponse.createBySuccess(randomStr);
    }

    @ApiOperation("查询构建json进程-轮询")
    @PostMapping(value = "getAllGenerateJsonProcess")
    public ServerResponse<JsonProcessVo> getAllGenerateJsonProcess(@RequestBody @Valid RandomStrDto dto)
    {
        return jsonService.getAllGenerateJsonProcess(dto);
    }

    @ApiOperation("查询已构建的json")
    @PostMapping(value = "getAllGenerateJson")
    public ServerResponse<List<JsonVo>> getAllGenerateJson(@RequestBody @Valid JsonTaskDto dto)
    {
        return jsonService.getAllGenerateJson(dto);
    }

    @ApiOperation("编辑json文件描述")
    @PostMapping(value = "updateJsonDesc")
    public ServerResponse<String> updateJsonDesc(@RequestBody @Valid JsonTaskDto dto)
    {
        return jsonService.updateJsonDesc(dto);
    }

    @ApiOperation("构建json进度列表-分页")
    @PostMapping(value = "querySubTaskListWithPage")
    public ServerResponse<PageInfo<QuerySubTaskVo>> querySubTaskListWithPage(@RequestBody QuerySubTaskDto dto)
    {
        if (StringUtils.isNotBlank(dto.getSubTaskId())) {
            dto.setSubTaskId("%" + EscapeUtil.modifyLikeSql(dto.getSubTaskId()) + "%");
        }
        if (StringUtils.isNotBlank(dto.getTableSchema())) {
            dto.setTableSchema("%" + EscapeUtil.modifyLikeSql(dto.getTableSchema()) + "%");
        }
        if (StringUtils.isNotBlank(dto.getTableName())) {
            dto.setTableName("%" + EscapeUtil.modifyLikeSql(dto.getTableName()) + "%");
        }
        return subTaskService.querySubTaskListWithPage(dto, getLoginAuthUser());
    }
}
