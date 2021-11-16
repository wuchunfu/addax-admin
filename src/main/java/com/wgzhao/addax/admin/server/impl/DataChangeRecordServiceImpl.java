package com.wgzhao.addax.admin.server.impl;

import com.alibaba.excel.EasyExcel;
import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.dto.QueryChangeRecordDto;
import com.wgzhao.addax.admin.enums.ResponseEnum;
import com.wgzhao.addax.admin.enums.SystemConfigTypeConstant;
import com.wgzhao.addax.admin.exception.UnifiedException;
import com.wgzhao.addax.admin.mapper.DataChangeRecordCustomMapper;
import com.wgzhao.addax.admin.pojo.DataChangeRecord;
import com.wgzhao.addax.admin.pojo.SystemConfig;
import com.wgzhao.addax.admin.server.DataChangeRecordService;
import com.wgzhao.addax.admin.server.SystemConfigService;
import com.wgzhao.addax.admin.vo.QueryChangeRecordVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author liuting
 */
@Service
@Log4j2
public class DataChangeRecordServiceImpl
        implements DataChangeRecordService
{
    @Resource
    private DataChangeRecordCustomMapper dataChangeRecordCustomMapper;

    @Resource
    private SystemConfigService systemConfigService;

    @Override
    public ServerResponse<PageInfo<QueryChangeRecordVo>> queryChangeRecordListWithPage(QueryChangeRecordDto dto)
    {
        if (Objects.isNull(dto.getStartTime()) || Objects.isNull(dto.getEndTime())) {
            throw new UnifiedException(ResponseEnum.ERROR.getReturnCode(), "请选择变更日期");
        }
        Integer pageNum = dto.getPageNum();
        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }
        Integer pageSize = dto.getPageSize();
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        SystemConfig systemConfig =
                systemConfigService.getSystemConfigByCode(SystemConfigTypeConstant.SystemConfigTypeEnum.SCAN_TIME.getCode());
        if (Objects.isNull(systemConfig)) {
            throw new UnifiedException(ResponseEnum.ERROR.getReturnCode(), "查询不到扫描时间设置");
        }
        //获取配置的更新时间
        String configureDate = " " + systemConfig.getConfigTime();
        dto.setStartTime(dto.getStartTime() + configureDate);
        dto.setEndTime(dto.getEndTime() + configureDate);
        PageHelper.startPage(pageNum, pageSize);
        List<QueryChangeRecordVo> changeRecordVoList =
                dataChangeRecordCustomMapper.queryChangeRecordListWithPage(dto);
        PageInfo<QueryChangeRecordVo> pageResult = new PageInfo<>(changeRecordVoList);
        pageResult.setList(changeRecordVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public void exportChangeRecordListWithPage(HttpServletResponse response, QueryChangeRecordDto dto)
    {
        if (Objects.isNull(dto.getStartTime()) || Objects.isNull(dto.getEndTime())) {
            throw new UnifiedException(ResponseEnum.ERROR.getReturnCode(), "变更时间不允许为空");
        }
        dto.setPageSize(null);
        dto.setPageNum(null);
        SystemConfig systemConfig =
                systemConfigService.getSystemConfigByCode(SystemConfigTypeConstant.SystemConfigTypeEnum.SCAN_TIME.getCode());
        if (Objects.isNull(systemConfig)) {
            throw new UnifiedException(ResponseEnum.ERROR.getReturnCode(), "查询不到扫描时间设置");
        }
        //获取配置的更新时间
        String configureDate = " " + systemConfig.getConfigTime();
        dto.setStartTime(dto.getStartTime() + configureDate);
        dto.setEndTime(dto.getEndTime() + configureDate);
        List<QueryChangeRecordVo> changeRecordVoList =
                dataChangeRecordCustomMapper.queryChangeRecordListWithPage(dto);
        if (CollectionUtils.isEmpty(changeRecordVoList)) {
            throw new UnifiedException(ResponseEnum.ERROR.getReturnCode(), "未查询到元数据变更记录，不允许导出");
        }
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("元数据变更记录", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), QueryChangeRecordVo.class).sheet().doWrite(changeRecordVoList);
        }
        catch (Exception e) {
            log.error("下载报表异常：{}", e.getMessage());
            throw new RuntimeException("下载报表异常");
        }
    }

    @Override
    public List<DataChangeRecord> getRecordsByBeginAndEndTime(String beginTime, String endTime)
    {
        return dataChangeRecordCustomMapper.getRecordsByBeginAndEndTime(beginTime, endTime);
    }

    @Override
    public Date getUpdateTimeByNodeCode(String nodeCode)
    {
        return dataChangeRecordCustomMapper.getUpdateTimeByNodeCode(nodeCode);
    }
}
