package com.wgzhao.addax.admin.server;

import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.dto.QueryChangeRecordDto;
import com.wgzhao.addax.admin.pojo.DataChangeRecord;
import com.wgzhao.addax.admin.vo.QueryChangeRecordVo;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.List;

/**
 * @author liuting
 */
public interface DataChangeRecordService
{

    ServerResponse<PageInfo<QueryChangeRecordVo>> queryChangeRecordListWithPage(QueryChangeRecordDto dto);

    void exportChangeRecordListWithPage(HttpServletResponse response, QueryChangeRecordDto dto);

    List<DataChangeRecord> getRecordsByBeginAndEndTime(String beginTime, String endTime);

    Date getUpdateTimeByNodeCode(String nodeCode);
}
