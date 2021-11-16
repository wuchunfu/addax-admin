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
import com.wgzhao.addax.admin.dto.SystemConfigDto;
import com.wgzhao.addax.admin.server.SystemConfigService;
import com.wgzhao.addax.admin.vo.QuerySystemConfigVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/systemConfig")
public class SystemConfigController
{

    @Resource
    private SystemConfigService systemConfigService;

    @ApiOperation("系统配置列表")
    @GetMapping(value = "querySystemConfigs")
    public ServerResponse<List<QuerySystemConfigVo>> querySystemConfigs()
    {
        return systemConfigService.querySystemConfigs();
    }

    @ApiOperation("编辑系统配置")
    @PostMapping(value = "updateSystemConfig")
    public ServerResponse<String> updateSystemConfig(@RequestBody @Valid SystemConfigDto dto)
    {
        return systemConfigService.updateSystemConfig(dto);
    }
}
