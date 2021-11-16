package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.dto.QueryChangeRecordDto;
import com.wgzhao.addax.admin.pojo.DataChangeRecord;
import com.wgzhao.addax.admin.vo.QueryChangeRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DataChangeRecordCustomMapper
{
    List<QueryChangeRecordVo> queryChangeRecordListWithPage(QueryChangeRecordDto dto);

    List<DataChangeRecord> getRecordsByBeginAndEndTime(@Param("beginTime") String beginTime,
            @Param("endTime") String endTime);

    Date getUpdateTimeByNodeCode(String nodeCode);
}