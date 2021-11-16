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
