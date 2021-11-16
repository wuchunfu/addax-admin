package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.dto.QuerySourceConfigDto;
import com.wgzhao.addax.admin.vo.QuerySourceConfigVo;
import com.wgzhao.addax.admin.vo.SourceConfigSelectVo;
import com.wgzhao.addax.admin.vo.SourceConfigVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SourceConfigCustomMapper
{
    List<QuerySourceConfigVo> querySourceConfigListWithPage(QuerySourceConfigDto querySourceConfigDto);

    List<SourceConfigSelectVo> getAllSourceConfig();

    SourceConfigVo selectBySourceCongigId(@Param("sId") String sId);
}