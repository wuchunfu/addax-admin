package com.wgzhao.addax.admin.server;

import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.dto.SystemConfigDto;
import com.wgzhao.addax.admin.pojo.SystemConfig;
import com.wgzhao.addax.admin.vo.QuerySystemConfigVo;

import java.util.List;

/**
 * @author liuting
 */
public interface SystemConfigService
{
    ServerResponse<List<QuerySystemConfigVo>> querySystemConfigs();

    ServerResponse<String> updateSystemConfig(SystemConfigDto dto);

    SystemConfig getSystemConfigByCode(String code);
}
