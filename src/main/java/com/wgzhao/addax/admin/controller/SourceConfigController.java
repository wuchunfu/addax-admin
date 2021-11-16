package com.wgzhao.addax.admin.controller;

import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.dto.HdfsSourceConfigDto;
import com.wgzhao.addax.admin.dto.QuerySourceConfigDto;
import com.wgzhao.addax.admin.dto.SourceConfigDto;
import com.wgzhao.addax.admin.dto.SourceConfigIdDto;
import com.wgzhao.addax.admin.server.SourceConfigService;
import com.wgzhao.addax.admin.utils.EscapeUtil;
import com.wgzhao.addax.admin.vo.HdfsSourceConfigVo;
import com.wgzhao.addax.admin.vo.QuerySourceConfigVo;
import com.wgzhao.addax.admin.vo.SourceConfigSelectVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/sourceConfig")
public class SourceConfigController
{
    @Resource
    private SourceConfigService sourceConfigService;

    @ApiOperation("数据源管理列表-分页")
    @PostMapping(value = "querySourceConfigListWithPage")
    public ServerResponse<PageInfo<QuerySourceConfigVo>> querySourceConfigListWithPage(@RequestBody QuerySourceConfigDto querySourceConfigDto)
    {
        if (StringUtils.isNotBlank(querySourceConfigDto.getSourceConfigName())) {
            querySourceConfigDto.setSourceConfigName("%" + EscapeUtil.modifyLikeSql(querySourceConfigDto.getSourceConfigName()) + "%");
        }
        return sourceConfigService.querySourceConfigListWithPage(querySourceConfigDto);
    }

    @ApiOperation("删除数据源")
    @PostMapping(value = "delSourceConfig")
    public ServerResponse<String> delSourceConfig(@RequestBody @Valid SourceConfigIdDto dto)
    {
        return sourceConfigService.delSourceConfig(dto);
    }

    @ApiOperation("新增/编辑数据源")
    @PostMapping(value = "saveOrUpdateSourceConfig")
    public ServerResponse<String> saveOrUpdateSourceConfig(@RequestBody @Valid SourceConfigDto dto)
    {
        return sourceConfigService.saveOrUpdateSourceConfig(dto);
    }

    @ApiOperation("下拉-获取数据源")
    @GetMapping(value = "getAllSourceConfig")
    public ServerResponse<List<SourceConfigSelectVo>> getAllSourceConfig()
    {
        return sourceConfigService.getAllSourceConfig();
    }

    @ApiOperation("构建json-hdfs数据源信息")
    @PostMapping(value = "getHdfsSourceConfigInfo")
    public ServerResponse<HdfsSourceConfigVo> getHdfsSourceConfigInfo(@RequestBody @Valid HdfsSourceConfigDto dto)
    {
        return sourceConfigService.getHdfsSourceConfigInfo(dto);
    }
}
