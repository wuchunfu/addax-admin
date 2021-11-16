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

import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.dto.SystemConfigDto;
import com.wgzhao.addax.admin.enums.ResponseEnum;
import com.wgzhao.addax.admin.enums.SystemConfigTypeConstant;
import com.wgzhao.addax.admin.exception.UnifiedException;
import com.wgzhao.addax.admin.mapper.SystemConfigCustomMapper;
import com.wgzhao.addax.admin.mapper.SystemConfigMapper;
import com.wgzhao.addax.admin.pojo.SystemConfig;
import com.wgzhao.addax.admin.server.SystemConfigService;
import com.wgzhao.addax.admin.vo.QuerySystemConfigVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author liuting
 */
@Service
@Log4j2
public class SystemConfigServiceImpl
        implements SystemConfigService
{
    @Resource
    private SystemConfigCustomMapper systemConfigCustomMapper;

    @Resource
    private SystemConfigMapper systemConfigMapper;

    @Override
    public ServerResponse<List<QuerySystemConfigVo>> querySystemConfigs()
    {
        return ServerResponse.createBySuccess("成功", systemConfigCustomMapper.querySystemConfigs());
    }

    @Override
    public ServerResponse<String> updateSystemConfig(SystemConfigDto dto)
    {
        SystemConfig systemConfig = systemConfigMapper.selectByPrimaryKey(dto.getConfigId());
        if (Objects.isNull(systemConfig)) {
            throw new UnifiedException(ResponseEnum.ERROR.getReturnCode(), "系统配置信息异常");
        }
        systemConfig.setMtime(new Date());
        systemConfig.setIsOpen(dto.getIsOpen());
        if (SystemConfigTypeConstant.SystemConfigTypeEnum.TARGET_TABLE_AUTO_UPDATE.getCode().equals(dto.getConfigCode())) {
            systemConfig.setContent(SystemConfigTypeConstant.SystemConfigStatusEnum.getValue(dto.getIsOpen()));
        }
        else if (SystemConfigTypeConstant.SystemConfigTypeEnum.SCAN_TIME.getCode().equals(dto.getConfigCode())) {
            systemConfig.setConfigTime(dto.getConfigTime());
            systemConfig.setContent(SystemConfigTypeConstant.CycleEnum.getValue(systemConfig.getCycle())
                    + " " + dto.getConfigTime());
        }
        systemConfigMapper.updateByPrimaryKeySelective(systemConfig);
        return ServerResponse.createBySuccessMessage("成功");
    }

    @Override
    public SystemConfig getSystemConfigByCode(String code)
    {
        return systemConfigCustomMapper.getSystemConfigByCode(code);
    }
}


