package com.wgzhao.addax.admin.server.impl;

import com.alibaba.fastjson.JSONObject;
import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.dto.HdfsSourceConfigDto;
import com.wgzhao.addax.admin.dto.QuerySourceConfigDto;
import com.wgzhao.addax.admin.dto.SourceConfigDto;
import com.wgzhao.addax.admin.dto.SourceConfigIdDto;
import com.wgzhao.addax.admin.enums.DataBaseTypeEnum;
import com.wgzhao.addax.admin.enums.ResponseEnum;
import com.wgzhao.addax.admin.enums.SourceConfigStatusEnum;
import com.wgzhao.addax.admin.exception.UnifiedException;
import com.wgzhao.addax.admin.mapper.SourceConfigCustomMapper;
import com.wgzhao.addax.admin.mapper.SourceConfigMapper;
import com.wgzhao.addax.admin.pojo.SourceConfig;
import com.wgzhao.addax.admin.server.SourceConfigService;
import com.wgzhao.addax.admin.utils.UUIDUtil;
import com.wgzhao.addax.admin.vo.HdfsSourceConfigVo;
import com.wgzhao.addax.admin.vo.QuerySourceConfigVo;
import com.wgzhao.addax.admin.vo.SourceConfigSelectVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Base64;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liuting
 */
@Service
public class SourceConfigServiceImpl
        implements SourceConfigService
{
    @Resource
    private SourceConfigCustomMapper sourceConfigCustomMapper;

    @Resource
    private SourceConfigMapper sourceConfigMapper;

    @Override
    public ServerResponse<PageInfo<QuerySourceConfigVo>> querySourceConfigListWithPage(QuerySourceConfigDto querySourceConfigDto)
    {
        Integer pageNum = querySourceConfigDto.getPageNum();
        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }
        Integer pageSize = querySourceConfigDto.getPageSize();
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<QuerySourceConfigVo> sourceConfigList = sourceConfigCustomMapper.querySourceConfigListWithPage(querySourceConfigDto);
        sourceConfigList.forEach(item -> {
            if (StringUtils.isNotBlank(item.getPass())) {
                item.setPass(new String(Base64.getDecoder().decode(item.getPass())));
            }
        });
        PageInfo<QuerySourceConfigVo> pageResult = new PageInfo<>(sourceConfigList);
        pageResult.setList(sourceConfigList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<String> delSourceConfig(SourceConfigIdDto dto)
    {
        SourceConfig sourceConfig = sourceConfigMapper.selectByPrimaryKey(dto.getSourceConfigId());
        if (Objects.isNull(sourceConfig)) {
            return ServerResponse.createByErrorMessage("查询不到数据源信息");
        }
        sourceConfig.setMtime(new Date());
        sourceConfig.setDstatus(SourceConfigStatusEnum.DELETED_STATE.getCode());
        sourceConfigMapper.updateByPrimaryKeySelective(sourceConfig);
        return ServerResponse.createBySuccessMessage("成功");
    }

    @Override
    public ServerResponse<String> saveOrUpdateSourceConfig(SourceConfigDto dto)
    {
        SourceConfig sourceConfig = null;
        if (StringUtils.isNotBlank(dto.getSourceConfigId())) {
            //编辑
            sourceConfig = sourceConfigMapper.selectByPrimaryKey(dto.getSourceConfigId());
            if (Objects.isNull(sourceConfig)) {
                return ServerResponse.createByErrorMessage("查询不到数据源信息");
            }
            saveSourceConfigInfo(dto, sourceConfig);
            sourceConfigMapper.updateByPrimaryKey(sourceConfig);
        }
        else {
            //新增
            String id = UUIDUtil.generate();
            sourceConfig = new SourceConfig();
            sourceConfig.setId(id);
            sourceConfig.setCtime(new Date());
            sourceConfig.setDstatus(SourceConfigStatusEnum.ENABLE_STATUS.getCode());
            saveSourceConfigInfo(dto, sourceConfig);
            sourceConfigMapper.insertSelective(sourceConfig);
        }
        return ServerResponse.createBySuccessMessage("成功");
    }

    @Override
    public ServerResponse<List<SourceConfigSelectVo>> getAllSourceConfig()
    {
        return ServerResponse.createBySuccess("成功", sourceConfigCustomMapper.getAllSourceConfig());
    }

    @Override
    public SourceConfig getSourceConfigById(String id)
    {
        return sourceConfigMapper.selectByPrimaryKey(id);
    }

    @Override
    public ServerResponse<HdfsSourceConfigVo> getHdfsSourceConfigInfo(HdfsSourceConfigDto dto)
    {
        HdfsSourceConfigVo hdfsSourceConfigVo = new HdfsSourceConfigVo();
        hdfsSourceConfigVo.setReaderSourceConfigVo(sourceConfigCustomMapper.selectBySourceCongigId(dto.getReaderSourceConfigId()));
        hdfsSourceConfigVo.setWriterSourceConfigVo(sourceConfigCustomMapper.selectBySourceCongigId(dto.getWriterSourceConfigId()));
        return ServerResponse.createBySuccess("成功", hdfsSourceConfigVo);
    }

    private void saveSourceConfigInfo(SourceConfigDto dto, SourceConfig sourceConfig)
    {
        Integer dType = 0;
        if (StringUtils.isNotBlank(dto.getJdbcUrl())) {
            dType = DataBaseTypeEnum.getCode(dto.getJdbcUrl());
            if (dType.equals(0)) {
                throw new UnifiedException(ResponseEnum.ERROR.getReturnCode(), "新增数据源失败！无法识别jdbc" +
                        "信息");
            }
        }
        sourceConfig.setMtime(new Date());
        sourceConfig.setUser(dto.getSourceConfigUserName());
        sourceConfig.setPass(StringUtils.isNotBlank(dto.getPass()) ?
                Base64.getEncoder().encodeToString(dto.getPass().getBytes()) : null);
        sourceConfig.setDsn(dto.getJdbcUrl());
        sourceConfig.setDtype(dto.getSourceConfigType() != null ? dto.getSourceConfigType() : dType);
        sourceConfig.setName(dto.getSourceConfigName());
        sourceConfig.setPath(dto.getPath());
        sourceConfig.setDefaultfs(dto.getDefaultFS());
        sourceConfig.setHaveKerberos(dto.getHaveKerberos());
        sourceConfig.setKerberosKeytabFilePath(dto.getKerberosKeytabFilePath());
        sourceConfig.setKerberosPrincipal(dto.getKerberosPrincipal());
        sourceConfig.setHiveConnectStr(dto.getHiveConnectStr());
        sourceConfig.setHiveUserName(dto.getHiveUserName());
        sourceConfig.setHivePass(dto.getHivePass());
        sourceConfig.setNameServices(dto.getNameServices());
        sourceConfig.setNameNodes(dto.getNameNodes());
        sourceConfig.setNameNodeRpc(dto.getNameNodeRpc());
        sourceConfig.setIsEnableHa(dto.getIsEnableHa());
        if (dto.getIsEnableHa() != null && dto.getIsEnableHa().equals(1)) {
            JSONObject jsonObject = new JSONObject(new LinkedHashMap<>());
            jsonObject.put("dfs.nameservices", dto.getNameServices());
            jsonObject.put("dfs.ha.namenodes." + dto.getNameServices(), dto.getNameNodes());
            List<String> collect = Stream.of(dto.getNameNodeRpc().split(",")).collect(Collectors.toList());
            List<String> nodes = Stream.of(dto.getNameNodes().split(",")).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(collect) || CollectionUtils.isEmpty(collect)) {
                throw new UnifiedException(ResponseEnum.ERROR.getReturnCode(), "namenode" +
                        "名称或者节点主机名不能为空");
            }
            if (collect.size() != nodes.size()) {
                throw new UnifiedException(ResponseEnum.ERROR.getReturnCode(), "namenode" +
                        "名称和节点主机名数量不匹配");
            }
            for (int i = 0; i < collect.size(); i++) {
                jsonObject.put("dfs.namenode.rpc-address." + dto.getNameServices() + "." + nodes.get(i), collect.get(i));
            }
            jsonObject.put("dfs.client.failover.proxy.provider." + dto.getNameServices(), "org.apache.hadoop.hdfs.server" +
                    ".namenode.ha.ConfiguredFailoverProxyProvider");
            sourceConfig.setHadoopConfig(JSONObject.toJSONString(jsonObject));
        }
    }
}
