package com.wgzhao.addax.admin.server.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.config.ConfigConstants;
import com.wgzhao.addax.admin.dto.EditJsonDto;
import com.wgzhao.addax.admin.dto.JsonDto;
import com.wgzhao.addax.admin.dto.JsonTaskDto;
import com.wgzhao.addax.admin.dto.QueryJsonDto;
import com.wgzhao.addax.admin.dto.RandomStrDto;
import com.wgzhao.addax.admin.dto.SourceTableDto;
import com.wgzhao.addax.admin.dto.TableDto;
import com.wgzhao.addax.admin.enums.ColChangeTypeEnum;
import com.wgzhao.addax.admin.enums.DataBaseTypeEnum;
import com.wgzhao.addax.admin.enums.JsonCollectionTypeEnum;
import com.wgzhao.addax.admin.enums.ProcessStatusEnum;
import com.wgzhao.addax.admin.enums.ResponseEnum;
import com.wgzhao.addax.admin.enums.SubTaskStatusEnum;
import com.wgzhao.addax.admin.enums.SystemConfigTypeConstant;
import com.wgzhao.addax.admin.exception.UnifiedException;
import com.wgzhao.addax.admin.mapper.JsonInfoCustomMapper;
import com.wgzhao.addax.admin.mapper.JsonInfoMapper;
import com.wgzhao.addax.admin.pojo.DataChangeRecord;
import com.wgzhao.addax.admin.pojo.JsonInfo;
import com.wgzhao.addax.admin.pojo.SourceConfig;
import com.wgzhao.addax.admin.pojo.SubTaskInfo;
import com.wgzhao.addax.admin.pojo.SystemConfig;
import com.wgzhao.addax.admin.pojo.TableInfo;
import com.wgzhao.addax.admin.pojo.TableInfoKey;
import com.wgzhao.addax.admin.pojo.TaskInfo;
import com.wgzhao.addax.admin.server.DataChangeRecordService;
import com.wgzhao.addax.admin.server.JsonService;
import com.wgzhao.addax.admin.server.SourceConfigService;
import com.wgzhao.addax.admin.server.SubTaskService;
import com.wgzhao.addax.admin.server.SystemConfigService;
import com.wgzhao.addax.admin.server.TableMainService;
import com.wgzhao.addax.admin.server.TableService;
import com.wgzhao.addax.admin.server.TaskService;
import com.wgzhao.addax.admin.utils.DateUtil;
import com.wgzhao.addax.admin.utils.HttpUtil;
import com.wgzhao.addax.admin.utils.UUIDUtil;
import com.wgzhao.addax.admin.vo.JsonProcessVo;
import com.wgzhao.addax.admin.vo.JsonTaskVo;
import com.wgzhao.addax.admin.vo.JsonVo;
import com.wgzhao.addax.admin.vo.ProcessTableVo;
import com.wgzhao.addax.admin.vo.QueryJsonVo;
import com.wgzhao.addax.admin.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liuting
 */
@Service
@Log4j2
@DependsOn("hsCacheUtils")
public class JsonServiceImpl
        implements JsonService
{
    @Resource
    private JsonInfoMapper jsonInfoMapper;

    @Resource
    private JsonInfoCustomMapper jsonInfoCustomMapper;

    @Resource
    private SourceConfigService sourceConfigService;

    @Resource
    private TaskService taskService;

    @Resource
    private SubTaskService subTaskService;

    @Resource
    private TableService tableService;

    @Resource
    private SystemConfigService systemConfigService;

    @Resource
    private DataChangeRecordService dataChangeRecordService;

    @Resource
    private TableMainService tableMainService;

    private final HSCache hsCache = HSCacheUtils.get("default").getCache();

    private final HSCache hsCacheNew = HSCacheUtils.get("trading").getCache();

    /**
     * 按每100个一组分割
     */
    private static final Integer MAX_NUMBER = 100;

    private static Pattern STR_PATTERN = Pattern.compile("(?<!\\\\)\\$\\{\\w+\\}");

    private static final String varcharStr = "varchar";

    private static final String stringStr = "string";

    @Override
    public ServerResponse<PageInfo<QueryJsonVo>> queryJsonListWithPage(QueryJsonDto queryJsonDto)
    {
        Integer pageNum = queryJsonDto.getPageNum();
        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }
        Integer pageSize = queryJsonDto.getPageSize();
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<QueryJsonVo> jsonList = jsonInfoCustomMapper.queryJsonListWithPage(queryJsonDto);
        PageInfo<QueryJsonVo> pageResult = new PageInfo<>(jsonList);
        pageResult.setList(jsonList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<String> updateJsonInfo(EditJsonDto editJsonDto)
    {
        JsonInfo jsonInfo = jsonInfoMapper.selectByPrimaryKey(editJsonDto.getJsonId());
        if (Objects.isNull(jsonInfo)) {
            return ServerResponse.createByErrorMessage("查询不到json信息");
        }
        jsonInfo.setContent(editJsonDto.getJson());
        jsonInfo.setMtime(new Date());
        jsonInfoMapper.updateByPrimaryKeySelective(jsonInfo);
        return ServerResponse.createBySuccessMessage("编辑成功");
    }

    @Override
    @Async
    public ServerResponse<String> generateJson(JsonDto jsonDto, UserVo userVo, String randomStr)
    {
        List<TableDto> tableList = jsonDto.getTableList();
        //读取数据源
        SourceConfig readSourceConfig =
                sourceConfigService.getSourceConfigById(jsonDto.getReadSourceConfigId());
        //目标数据源
        SourceConfig targetSourceConfig =
                sourceConfigService.getSourceConfigById(jsonDto.getTargetSourceConfigId());
        //创建主任务表记录
        TaskInfo taskInfo = taskService.saveTaskInfo(userVo.getUserId());
        if (Objects.isNull(taskInfo)) {
            return ServerResponse.createByErrorMessage("主任务记录生成失败");
        }
        hsCache.put("ZEUS:JSON:" + randomStr, taskInfo.getId(), 7200);
        //循环创建任务子表记录
        int limit = countStep(tableList.size());
        Stream.iterate(0, n -> n + 1).limit(limit).forEach(i -> {
            tableList.stream().skip(i * MAX_NUMBER).limit(MAX_NUMBER).forEach(item -> {
                subTaskService.saveSubTaskInfo(readSourceConfig.getId(),
                        item,
                        targetSourceConfig.getId(),
                        taskInfo.getId(),
                        jsonDto.getCollectionType());
            });
        });
        //调用读源、建表任务
        boolean flag = callTask(jsonDto, taskInfo);
        List<SubTaskInfo> subTaskInfoList = subTaskService.findAllByTaskId(taskInfo.getId());
        //循环生成JSON
        Stream.iterate(0, n -> n + 1).limit(limit).forEach(i -> {
            subTaskInfoList.stream().skip(i * MAX_NUMBER).limit(MAX_NUMBER).forEach(item -> {
                SubTaskInfo subTaskInfo = item;
                if (flag) {
                    subTaskInfo.setAddFieldStatus(SubTaskStatusEnum.FAILED.getCode());
                    subTaskService.updateAddJsonStatus(subTaskInfo, SubTaskStatusEnum.FAILED.getCode(), "构建JSON任务：读源异常导致json构建失败");
                    return;
                }
                //如果子任务读源状态为初始值需要循环获取读源状态
                if (SubTaskStatusEnum.INITIAL.getCode().equals(item.getAddFieldStatus())) {
                    long timeoutExpiredMs = System.currentTimeMillis() + ConfigConstants.timeOut;
                    do {
                        try {
                            Thread.sleep(10);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //查询子任务字段入库状态是否为初始值，如果是，一直循环;如果失败不构建JSON
                        subTaskInfo = subTaskService.getSubTaskInfoById(subTaskInfo.getId());
                        long waitMs = timeoutExpiredMs - System.currentTimeMillis();
                        if (waitMs <= 0) {
                            log.info("已经超时了,子任务id：{}，读源状态：{}", subTaskInfo.getId(),
                                    subTaskInfo.getAddFieldStatus());
                            subTaskInfo.setAddFieldStatus(SubTaskStatusEnum.FAILED.getCode());
                            subTaskService.updateAddJsonStatus(subTaskInfo,
                                    SubTaskStatusEnum.FAILED.getCode(), "构建JSON任务：构建超时，读源状态一直未更新");
                            return;
                        }
                    }
                    while (Objects.isNull(subTaskInfo) || SubTaskStatusEnum.INITIAL.getCode().equals(subTaskInfo.getAddFieldStatus()));
                }
                if (SubTaskStatusEnum.FAILED.getCode().equals(subTaskInfo.getAddFieldStatus())) {
                    subTaskService.updateAddJsonStatus(subTaskInfo,
                            SubTaskStatusEnum.FAILED.getCode(), null);
                    return;
                }
                String json = "";
                try {
                    List<TableInfo> tableResultList = new ArrayList<>();
                    if (jsonDto.getCollectionType().equals(JsonCollectionTypeEnum.DATA_SERVICE.getCode())) {
                        //处理采集类型是数据服务的字段信息
                        String sourceDb = item.getSourceDb();
                        String sourceTbl = item.getSourceTbl();
                        //reader-获取同一数据源，同一库，同一表的所有字段信息
                        List<TableInfo> tableInfoList = tableService.getAllTableInfoList(readSourceConfig.getId(), sourceDb, sourceTbl);
                        if (CollectionUtils.isEmpty(tableInfoList)) {
                            subTaskService.updateAddJsonStatus(subTaskInfo,
                                    SubTaskStatusEnum.FAILED.getCode(), "构建JSON任务：reader-表字段信息尚未生成");
                            return;
                        }
                        String targetDb = item.getTargetDb();
                        String targetTbl = item.getTargetTbl();
                        //writer-获取同一数据源，同一库，同一表的所有字段信息
                        List<TableInfo> targetTableInfoList =
                                tableService.getAllTableInfoList(targetSourceConfig.getId(), targetDb, targetTbl);
                        if (CollectionUtils.isEmpty(targetTableInfoList)) {
                            subTaskService.updateAddJsonStatus(subTaskInfo,
                                    SubTaskStatusEnum.FAILED.getCode(), "构建JSON任务：writer-表字段信息尚未生成");
                            return;
                        }
                        //获取reader-writer相同的column
                        tableResultList = tableInfoList.stream().filter(
                                (tableInfo) -> targetTableInfoList.stream().map(TableInfo::getColName).collect(Collectors.toList()).contains(tableInfo.getColName())
                        ).collect(Collectors.toList());
                    }
                    //构建reader
                    String reader = generateReaderJson(jsonDto, readSourceConfig, item, tableResultList);
                    log.info("生成的reader-json:{}", reader);
                    if (StringUtils.isBlank(reader)) {
                        subTaskService.updateAddJsonStatus(subTaskInfo,
                                SubTaskStatusEnum.FAILED.getCode(), "构建JSON任务：reader-表字段信息尚未生成");
                        return;
                    }
                    //构建writer
                    String writer = generateWriterJson(jsonDto, targetSourceConfig, item, tableResultList);
                    log.info("生成的writer-json:{}", writer);
                    if (StringUtils.isBlank(writer)) {
                        subTaskService.updateAddJsonStatus(subTaskInfo,
                                SubTaskStatusEnum.FAILED.getCode(), "构建JSON任务：writer-表字段信息尚未生成");
                        return;
                    }
                    //拼成一个json,正则去除为空value的key
                    json = createJson(reader, writer).replaceAll("((?<=\\{)\"\\w+\":\"\",|,*\"\\w+\":\"\")", "")
                            .replaceAll("((?<=\\{)\"\\w+\":\\[\"\"],|,*\"\\w+\":\\[\"\"])", "");
                    log.info("构建好的完整的的json:{}", json);
                }
                catch (Exception ex) {
                    subTaskService.updateAddJsonStatus(subTaskInfo,
                            SubTaskStatusEnum.FAILED.getCode(), "构建JSON任务：" + ex.getMessage());
                    return;
                }
                //保存JsonInfo
                int j = saveJsonInfo(userVo, readSourceConfig, targetSourceConfig, item, json,
                        taskInfo.getId(), jsonDto.getPartitionFieldList());
                if (j > 0) {
                    //将子任务生成json状态置为成功
                    subTaskService.updateAddJsonStatus(subTaskInfo,
                            SubTaskStatusEnum.SUCCEED.getCode(), null);
                }
                else {
                    subTaskService.updateAddJsonStatus(subTaskInfo,
                            SubTaskStatusEnum.FAILED.getCode(), "构建JSON任务：保存json信息失败");
                }
            });
        });
        return ServerResponse.createBySuccess();
    }

    private boolean callTask(JsonDto jsonDto, TaskInfo taskInfo)
    {
        String uri = ConfigConstants.taskInfoUrl + taskInfo.getId();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("sourceTableList", jsonDto.getSourceTableList());
        paramMap.put("taskId", taskInfo.getId());
        paramMap.put("partitionFieldList", jsonDto.getPartitionFieldList());
        String str = HttpUtil.getData(uri, paramMap, "post");
        if ("接口调用失败".equals(str)) {
            return true;
        }
        JSONObject resultJson = JSON.parseObject(str);
        String code = resultJson.getString("returnCode");
        log.info("调用任务请求响应码:{}", code);
        if (StringUtils.isBlank(code) || code.equals(ResponseEnum.ERROR.getReturnCode())) {
            return true;
        }
        return false;
    }

    @Override
    public ServerResponse<String> updateJsonDesc(JsonTaskDto dto)
    {
        if (StringUtils.isBlank(dto.getJsonDesc())) {
            return ServerResponse.createBySuccess();
        }
        //获取任务下所有的json记录
        List<JsonInfo> jsonInfos = jsonInfoCustomMapper.getJsonListByTaskId(dto.getTaskId());
        if (CollectionUtils.isEmpty(jsonInfos)) {
            return ServerResponse.createBySuccessMessage("没有找到json记录");
        }
        jsonInfos.forEach(item -> {
            item.setTitle(dto.getJsonDesc());
            item.setMtime(new Date());
            jsonInfoMapper.updateByPrimaryKeySelective(item);
        });
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse<JsonProcessVo> getAllGenerateJsonProcess(RandomStrDto dto)
    {
        String taskId = hsCache.get("ZEUS:JSON:" + dto.getRandomStr(), String.class);
        if (StringUtils.isBlank(taskId)) {
            return ServerResponse.createBySuccessMessage("传参有误");
        }
        JsonProcessVo vo = new JsonProcessVo();
        //根据主任务id查询子任务是否还有未完成状态的
        int notFinishedCount = subTaskService.getNotFinishedCountByTaskId(taskId);
        if (notFinishedCount == 0) {
            vo.setProcessStatus(ProcessStatusEnum.FINISHED.getCode());
        }
        vo.setTaskId(taskId);
        //根据主任务id查询子任务信息
        List<ProcessTableVo> processVoList = subTaskService.getAllJsonProcessByTaskId(taskId);
        vo.setProcessVoList(processVoList);
        return ServerResponse.createBySuccess("成功", vo);
    }

    @Override
    public ServerResponse<List<JsonVo>> getAllGenerateJson(JsonTaskDto dto)
    {
        return ServerResponse.createBySuccess("成功",
                jsonInfoCustomMapper.getJsonAndUserListByTaskId(dto.getTaskId()));
    }

    @Override
    public void verification(JsonDto jsonDto)
    {
        if (CollectionUtils.isEmpty(jsonDto.getTableList())) {
            throw new UnifiedException(ResponseEnum.ERROR.getReturnCode(), "查询不到复制数据的列表信息");
        }
        //读取数据源
        SourceConfig readSourceConfig =
                sourceConfigService.getSourceConfigById(jsonDto.getReadSourceConfigId());
        if (Objects.isNull(readSourceConfig)) {
            throw new UnifiedException(ResponseEnum.ERROR.getReturnCode(), "查询不到读取数据源的信息");
        }
        //目标数据源
        SourceConfig targetSourceConfig =
                sourceConfigService.getSourceConfigById(jsonDto.getTargetSourceConfigId());
        if (Objects.isNull(targetSourceConfig)) {
            throw new UnifiedException(ResponseEnum.ERROR.getReturnCode(), "查询不到目标数据源的信息");
        }
    }

    /**
     * 拼接完整json
     *
     * @param reader
     * @param writer
     * @return
     */
    private String createJson(String reader, String writer)
    {
        JSONObject thirdFloor = new JSONObject(true);
        thirdFloor.put("channel", 1);

        JSONObject secondFloorSetting = new JSONObject(true);
        secondFloorSetting.put("speed", thirdFloor);

        JSONObject secondFloorContent = new JSONObject(true);
        secondFloorContent.put("reader", JSONObject.parseObject(reader, Feature.OrderedField));
        secondFloorContent.put("writer", JSONObject.parseObject(writer, Feature.OrderedField));

        JSONArray contentArray = new JSONArray();
        contentArray.add(secondFloorContent);

        JSONObject secondFloor = new JSONObject(true);
        secondFloor.put("setting", secondFloorSetting);
        secondFloor.put("content", contentArray);

        JSONObject firstFloor = new JSONObject(true);
        firstFloor.put("job", secondFloor);
        return JSONObject.toJSONString(firstFloor);
    }

    private int saveJsonInfo(UserVo userVo, SourceConfig readSourceConfig,
            SourceConfig targetSourceConfig, SubTaskInfo item, String json, String taskId,
            List<SourceTableDto> partitionFieldList)
    {
        String jsonId = UUIDUtil.generate();
        JsonInfo jsonInfo = new JsonInfo();
        jsonInfo.setId(jsonId);
        jsonInfo.setSubTaskId(item.getId());
        String jsonName = readSourceConfig.getName() + "_" + item.getSourceDb() + "_" + item.getSourceTbl() +
                "_" + "2"
                + "_" + targetSourceConfig.getName() + "_" + item.getTargetDb() + "_" + item.getTargetTbl();
        if (DataBaseTypeEnum.HIVE_HDFS_TYPE.getCode().equals(readSourceConfig.getDtype())) {
            //如果reader是hdfs
            String path = item.getSourceDb() + item.getSourceTbl();
            jsonName = readSourceConfig.getName() + path.substring(0,
                    path.lastIndexOf("/")) + "_2" +
                    "_" + targetSourceConfig.getName() + "_" + item.getTargetDb() + "_" + item.getTargetTbl();
        }
        else if (DataBaseTypeEnum.HIVE_HDFS_TYPE.getCode().equals(targetSourceConfig.getDtype()) &&
                !CollectionUtils.isEmpty(partitionFieldList)) {
            //如果writer是hdfs
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(jsonName);
            partitionFieldList.forEach(val -> {
                stringBuilder.append("_").append(val.getFieldName()).append("=").append(val.getFieldValue());
            });
            jsonName = stringBuilder.toString();
        }
        jsonInfo.setName(jsonName.replaceAll("/", "_"));
        jsonInfo.setUid(userVo.getUserId());
        jsonInfo.setCtime(new Date());
        jsonInfo.setMtime(new Date());
        jsonInfo.setContent(json);
        jsonInfo.setTaskId(taskId);
        return jsonInfoMapper.insertSelective(jsonInfo);
    }

    private Map<String, Object> toMap(TableInfo tableInfo)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("index", tableInfo.getColPos());
        map.put("type", tableInfo.getColType().toLowerCase());
        return map;
    }

    private Map<String, Object> toMapOther(TableInfo tableInfo)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("name", tableInfo.getColName());
        map.put("type", tableInfo.getColType().toLowerCase());
        return map;
    }

    private String generateReaderJson(JsonDto jsonDto, SourceConfig readSourceConfig,
            SubTaskInfo item, List<TableInfo> tableInfoList)
    {
        String sourceDb = item.getSourceDb();
        String sourceTbl = item.getSourceTbl();
        //如果字段信息为空
        //重新获取同一数据源，同一库，同一表的所有字段信息
        if (CollectionUtils.isEmpty(tableInfoList)) {
            tableInfoList = tableService.getAllTableInfoList(readSourceConfig.getId(), sourceDb, sourceTbl);
            if (CollectionUtils.isEmpty(tableInfoList)) {
                log.info("reader:数据源id:{},数据库名：{}，表名：{}查询不到字段信息", readSourceConfig.getId(), sourceDb, sourceTbl);
                return null;
            }
        }
        log.info("reader:数据源id:{},数据库名：{}，表名：{}表的字段有：{}个", readSourceConfig.getId(), sourceDb, sourceTbl, tableInfoList.size());
        //第一层
        JSONObject readerJson = JSON.parseObject(jsonDto.getReaderStr());
        readerJson.put("name", DataBaseTypeEnum.getMsg(readSourceConfig.getDtype()).toLowerCase() + "reader");
        //第二层
        JSONObject parameterReaderJson = readerJson.getJSONObject("parameter");
        List<SourceTableDto> sourceTableList = jsonDto.getSourceTableList();
        if (DataBaseTypeEnum.HIVE_HDFS_TYPE.getCode().equals(readSourceConfig.getDtype())) {
//            String path = parameterReaderJson.getString("path");
//            if (StringUtils.isNotBlank(path)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(sourceDb).append(sourceTbl);
//                if (!CollectionUtils.isEmpty(jsonDto.getPartitionFieldList())) {
//                    jsonDto.getPartitionFieldList().forEach(value -> {
//                        stringBuilder.append("/").append(value.getFieldName()).append("=").append(value.getFieldValue());
//                    });
//                }
            parameterReaderJson.put("path", stringBuilder);
//            }
            JSONObject hadoopConfig = parameterReaderJson.getJSONObject("hadoopConfig");
            if (!Objects.isNull(hadoopConfig)) {
                parameterReaderJson.put("hadoopConfig", hadoopConfig);
            }
            String fieldDelimiter = parameterReaderJson.getString("fieldDelimiter");
            if (StringUtils.isNotBlank(fieldDelimiter)) {
                parameterReaderJson.put("fieldDelimiter",
                        StringEscapeUtils.unescapeJava(fieldDelimiter));
            }
            List<Map<String, Object>> columnList = tableInfoList.stream()
                    .map(this::toMap)
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(sourceTableList)) {
                for (int i = 0; i < sourceTableList.size(); i++) {
                    Map<String, Object> map = new HashMap<>();
                    String type = sourceTableList.get(i).getFieldType();
                    String value = sourceTableList.get(i).getFieldValue();
                    if (stringStr.equals(type.toLowerCase()) || varcharStr.equals(type.toLowerCase())) {
                        value = "'" + value + "'";
                        type = stringStr;
                    }
                    map.put("value", value);
                    map.put("type", type);
                    columnList.add(map);
                }
            }
            //设置表字段
            parameterReaderJson.put("column", columnList);
        }
        else {
            //获取所有的column
            String[] first = tableInfoList.stream().map(x -> x.getColName()).toArray(String[]::new);
            String[] second = new String[sourceTableList.size()];
            if (!CollectionUtils.isEmpty(sourceTableList)) {
                for (int i = 0; i < sourceTableList.size(); i++) {
                    String fieldValue = sourceTableList.get(i).getFieldValue();
                    String fieldType = sourceTableList.get(i).getFieldType();
                    if (varcharStr.equals(fieldType.toLowerCase())) {
                        fieldValue = "'" + fieldValue + "'";
                    }
                    second[i] = fieldValue;
                }
            }
            String[] column = (String[]) ArrayUtils.addAll(first, second);
            //设置表字段
            parameterReaderJson.put("column", column);
            JSONArray sessionArray = parameterReaderJson.getJSONArray("session");
            if (sessionArray != null && StringUtils.isNotBlank(sessionArray.getString(0))) {
                String[] session = sessionArray.getString(0).replace("\n", "")
                        .replace("\t", "").replace("\r", "").split(",");
                parameterReaderJson.put("session", session);
            }
            String querySql = parameterReaderJson.getString("querySql");
            if (StringUtils.isNotBlank(querySql)) {
                parameterReaderJson.put("querySql", processTemplate(querySql, createMap(sourceDb, sourceTbl)));
            }
            parameterReaderJson.put("username", readSourceConfig.getUser());
            parameterReaderJson.put("password", StringUtils.isNotBlank(readSourceConfig.getPass()) ?
                    new String(Base64.getDecoder().decode(readSourceConfig.getPass())) : null);
            //第三层
            JSONArray connectionJson = parameterReaderJson.getJSONArray("connection");
            JSONObject connectionJsonObject = new JSONObject();
            JSONArray tableArrayJson = new JSONArray();
            tableArrayJson.add(sourceDb + "." + sourceTbl);
            JSONArray jdbcUrlArrayJson = new JSONArray();
            jdbcUrlArrayJson.add(readSourceConfig.getDsn());
            connectionJsonObject.put("table", tableArrayJson);
            connectionJsonObject.put("jdbcUrl", jdbcUrlArrayJson);
            connectionJson.add(connectionJsonObject);
        }
        return JSONObject.toJSONString(readerJson);
    }

    /**
     * map模板
     *
     * @return
     */
    public Map<String, Object> createMap(String db, String table)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("${DB}", db);
        map.put("${TABLE}", table);
        String td = hsCacheNew.get("param.LTD", String.class);
        String cd = DateUtil.parseLocalDateTimeToStr(DateUtil.DATE_FORMAT_YYYYMMDD, LocalDate.now());
        map.put("${TD}", td);
        map.put("${CD}", cd);
        return map;
    }

    /**
     * 格式化path和defaultFS
     *
     * @param template 模版
     * @param params 参数
     * @return
     */
    public static String processTemplate(String template, Map<String, Object> params)
    {
        if (template == null || params == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        Matcher matcher = STR_PATTERN.matcher(template);
        while (matcher.find()) {
            String param = matcher.group();
            Object value = params.get(param);
            matcher.appendReplacement(sb, value == null ? "" : value.toString());
        }
        matcher.appendTail(sb);
        return sb.toString().replaceAll("\\\\", "");
    }

    private String generateWriterJson(JsonDto jsonDto, SourceConfig targetSourceConfig,
            SubTaskInfo item, List<TableInfo> tableInfoList)
    {
        String targetDb = item.getTargetDb();
        String targetTbl = item.getTargetTbl();
        //如果字段信息为空
        //获取同一数据源，同一库，同一表的所有字段信息
        if (CollectionUtils.isEmpty(tableInfoList)) {
            tableInfoList = tableService.getAllTableInfoList(targetSourceConfig.getId(), targetDb, targetTbl);
            if (CollectionUtils.isEmpty(tableInfoList)) {
                log.info("writer:数据源id:{},数据库名：{}，表名：{}查询不到字段信息", targetSourceConfig.getId(), targetDb, targetTbl);
                return null;
            }
        }
        log.info("writer:数据源id:{},数据库名：{}，表名：{}表的字段有：{}个", targetSourceConfig.getId(), targetDb, targetTbl, tableInfoList.size());
        //第一层
        JSONObject writerJson = JSONObject.parseObject(jsonDto.getWriterStr());
        writerJson.put("name", DataBaseTypeEnum.getMsg(targetSourceConfig.getDtype()).toLowerCase() + "writer");
        //第二层
        JSONObject parameterWriterJson = writerJson.getJSONObject("parameter");
        List<SourceTableDto> sourceTableList = jsonDto.getSourceTableList();
        if (DataBaseTypeEnum.HIVE_HDFS_TYPE.getCode().equals(targetSourceConfig.getDtype())) {
            String path = parameterWriterJson.getString("path");
            if (StringUtils.isNotBlank(path)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(path).append("/").append(targetDb).append("/").append(targetTbl);
                if (!CollectionUtils.isEmpty(jsonDto.getPartitionFieldList())) {
                    jsonDto.getPartitionFieldList().forEach(value -> {
                        stringBuilder.append("/").append(value.getFieldName()).append("=").append(value.getFieldValue());
                    });
                }
                parameterWriterJson.put("path", stringBuilder);
            }
            String hadoopConfig = parameterWriterJson.getString("hadoopConfig");
            if (!Objects.isNull(hadoopConfig)) {
                LinkedHashMap<String, Object> jsonMap = JSON.parseObject(hadoopConfig, LinkedHashMap.class, Feature.OrderedField);
                JSONObject jsonObject = new JSONObject(true);
                jsonObject.putAll(jsonMap);
                parameterWriterJson.put("hadoopConfig", jsonObject);
            }
            String fieldDelimiter = parameterWriterJson.getString("fieldDelimiter");
            if (StringUtils.isNotBlank(fieldDelimiter)) {
                parameterWriterJson.put("fieldDelimiter",
                        StringEscapeUtils.unescapeJava(fieldDelimiter));
            }
            List<Map<String, Object>> columnList = tableInfoList.stream()
                    .map(this::toMapOther)
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(sourceTableList)) {
                for (int i = 0; i < sourceTableList.size(); i++) {
                    Map<String, Object> map = new HashMap<>();
                    String type = sourceTableList.get(i).getFieldType();
                    if (stringStr.equals(type.toLowerCase()) || varcharStr.equals(type.toLowerCase())) {
                        type = stringStr;
                    }
                    map.put("name", sourceTableList.get(i).getFieldName());
                    map.put("type", type);
                    columnList.add(map);
                }
            }
            //设置表字段
            parameterWriterJson.put("column", columnList);
        }
        else {
            //获取所有的column
            String[] first = tableInfoList.stream().map(x -> x.getColName()).toArray(String[]::new);
            String[] second = new String[sourceTableList.size()];
            if (!CollectionUtils.isEmpty(sourceTableList)) {
                second = sourceTableList.stream().map(x -> x.getFieldName()).toArray(String[]::new);
            }
            String[] column = (String[]) ArrayUtils.addAll(first, second);
            //设置表字段
            parameterWriterJson.put("column", column);
            JSONArray sessionArray = parameterWriterJson.getJSONArray("session");
            if (sessionArray != null && StringUtils.isNotBlank(sessionArray.getString(0))) {
                String[] session = sessionArray.getString(0).replace("\n", "")
                        .replace("\t", "").replace("\r", "").split(",");
                parameterWriterJson.put("session", session);
            }
            JSONArray preSql = parameterWriterJson.getJSONArray("preSql");
            if (preSql != null && StringUtils.isNotBlank(preSql.getString(0))) {
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(processTemplate(preSql.getString(0), createMap(targetDb, targetTbl)));
                parameterWriterJson.put("preSql", jsonArray);
            }
            JSONArray postSql = parameterWriterJson.getJSONArray("postSql");
            if (postSql != null && StringUtils.isNotBlank(postSql.getString(0))) {
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(processTemplate(postSql.getString(0), createMap(targetDb, targetTbl)));
                parameterWriterJson.put("postSql", jsonArray);
            }
            parameterWriterJson.put("username", targetSourceConfig.getUser());
            parameterWriterJson.put("password", StringUtils.isNotBlank(targetSourceConfig.getPass()) ?
                    new String(Base64.getDecoder().decode(targetSourceConfig.getPass())) : null);
            //第三层
            JSONArray connectionJson = parameterWriterJson.getJSONArray("connection");
            JSONObject connectionJsonObject = new JSONObject();
            JSONArray tableArrayJson = new JSONArray();
            tableArrayJson.add(targetDb + "." + targetTbl);
            connectionJsonObject.put("table", tableArrayJson);
            connectionJsonObject.put("jdbcUrl", targetSourceConfig.getDsn());
            connectionJson.add(connectionJsonObject);
        }
        return JSONObject.toJSONString(writerJson);
    }

    /**
     * 计算切分次数
     */
    private static Integer countStep(Integer size)
    {
        return (size + MAX_NUMBER - 1) / MAX_NUMBER;
    }

    @Override
    public ServerResponse<String> updateJson()
    {
        SystemConfig systemConfig =
                systemConfigService.getSystemConfigByCode(SystemConfigTypeConstant.SystemConfigTypeEnum.SCAN_TIME.getCode());
        if (Objects.isNull(systemConfig)) {
            return ServerResponse.createByErrorMessage("查询不到扫描时间设置");
        }
        //获取配置的更新时间
        String configureDate = " " + systemConfig.getConfigTime();
        String beginTime =
                DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYY_MM_DD) + configureDate;
        String endTime = DateUtil.parseDateToStr(new Date(), DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
        //获取当天配置时间到当前时间之前的元数据变更记录
        List<DataChangeRecord> recordList =
                dataChangeRecordService.getRecordsByBeginAndEndTime(beginTime, endTime);
        if (CollectionUtils.isEmpty(recordList)) {
            return ServerResponse.createBySuccessMessage("无元数据变更记录,无需变更json");
        }
        recordList.forEach(record -> {
            if (ColChangeTypeEnum.DELETE_ALL.getCode().equals(record.getChangeType())) {
                log.info("id={}整表删除,json不需要变更", record.getId());
                return;
            }
            //根据数据源id,库名,表名查询需要修改的json-list
            List<JsonTaskVo> jsonInfoList =
                    jsonInfoCustomMapper.getNeedUpdateList(record.getSourceId(), record.getDbName(),
                            record.getTblName());
            if (CollectionUtils.isEmpty(jsonInfoList)) {
                log.info("{},{},{}没有需要修改的json", record.getSourceId(), record.getDbName(),
                        record.getTblName());
                return;
            }
            jsonInfoList.forEach(json -> {
                if (json.getCollectType().equals(JsonCollectionTypeEnum.DATA_SERVICE.getCode())) {
                    return;
                }
                String jsonContent = json.getJsonContent();
                if (StringUtils.isBlank(jsonContent)) {
                    return;
                }
                //重新构建json
                String content = rebuildJson(record, json);
                if (StringUtils.isBlank(content)) {
                    return;
                }
                JsonInfo jsonInfo = jsonInfoMapper.selectByPrimaryKey(json.getJsonId());
                //更新t_json_info表信息
                jsonInfo.setMtime(new Date());
                jsonInfo.setContent(content);
                jsonInfoMapper.updateByPrimaryKeySelective(jsonInfo);
            });
        });
        return ServerResponse.createBySuccessMessage("成功");
    }

    private String rebuildJson(DataChangeRecord record, JsonTaskVo json)
    {
        //解析json
        JSONObject jsonobj = JSON.parseObject(json.getJsonContent());
        JSONArray jsonArray = jsonobj.getJSONObject("job").getJSONArray("content");
        JSONObject readerJson =
                jsonArray.getJSONObject(0).getJSONObject("reader").getJSONObject(
                        "parameter");
        JSONObject writerJson =
                jsonArray.getJSONObject(0).getJSONObject("writer").getJSONObject(
                        "parameter");
        if (!record.getSourceId().equals(json.getSourceId())) {
            return null;
        }
        //源数据源信息
        SourceConfig sourceConfig = sourceConfigService.getSourceConfigById(json.getSourceId());
        if (Objects.isNull(sourceConfig)) {
            return null;
        }
        //目标数据源信息
        SourceConfig targetSourceConfig =
                sourceConfigService.getSourceConfigById(json.getTargetId());
        if (Objects.isNull(targetSourceConfig)) {
            return null;
        }
        //源表字段
        List<TableInfo> sourceTableInfoList =
                tableService.getTableInfoBySubTaskId(json.getSubTaskId(),
                        json.getSourceId(), json.getSourceDb(), json.getSourceTbl());
        //目标表字段
        List<TableInfo> targetTableInfoList =
                tableService.getTableInfoBySubTaskId(json.getSubTaskId(), json.getTargetId(),
                        json.getTargetDb(), json.getTargetTbl());
        if (ColChangeTypeEnum.DELETE.getCode().equals(record.getChangeType())) {
            //处理reader
            if (!DataBaseTypeEnum.HIVE_HDFS_TYPE.getCode().equals(sourceConfig.getDtype())) {
                //reader-column处理
                sourceTableInfoList.forEach(tableInfo -> {
                    if (tableInfo.getColDelStatus() == 2) {
                        tableInfo.setColName("'null'");
                    }
                });
                String[] columnReader =
                        sourceTableInfoList.stream().map(TableInfoKey::getColName).toArray(String[]::new);
                readerJson.put("column", columnReader);
                //处理writer
                if (!DataBaseTypeEnum.HIVE_HDFS_TYPE.getCode().equals(targetSourceConfig.getDtype())) {
                    //writer-column处理
                    targetTableInfoList.forEach(tableInfo -> {
                        if (tableInfo.getColDelStatus() == 2) {
                            tableInfo.setColName("'null'");
                        }
                    });
                    String[] columnWriter =
                            targetTableInfoList.stream().map(TableInfoKey::getColName).toArray(String[]::new);
                    writerJson.put("column", columnWriter);
                }
            }
        }
        else {
            //源表新增,修改字段
            //reader-过滤掉已删除的字段信息
            List<TableInfo> readerTableList =
                    sourceTableInfoList.stream().filter(item -> item.getColDelStatus() != 2).collect(Collectors.toList());
            //处理reader
            if (DataBaseTypeEnum.HIVE_HDFS_TYPE.getCode().equals(sourceConfig.getDtype())) {
                //reader-column处理
                List<Map<String, Object>> columnReaderList =
                        readerTableList.stream().map(this::toMap).collect(Collectors.toList());
                readerJson.put("column", columnReaderList);
            }
            else if (!DataBaseTypeEnum.HIVE_HDFS_TYPE.getCode().equals(sourceConfig.getDtype())) {
                //reader-column处理
                String[] columnReader =
                        readerTableList.stream().map(TableInfoKey::getColName).toArray(String[]::new);
                readerJson.put("column", columnReader);
            }
            //writer-过滤掉已删除的字段信息
            List<TableInfo> writerTableList =
                    targetTableInfoList.stream().filter(item -> item.getColDelStatus() != 2).collect(Collectors.toList());
            //处理writer
            if (DataBaseTypeEnum.HIVE_HDFS_TYPE.getCode().equals(targetSourceConfig.getDtype())) {
                //writer-column处理
                List<Map<String, Object>> columnWriterList = writerTableList.stream()
                        .map(this::toMapOther)
                        .collect(Collectors.toList());
                writerJson.put("column", columnWriterList);
            }
            else if (!DataBaseTypeEnum.HIVE_HDFS_TYPE.getCode().equals(targetSourceConfig.getDtype())) {
                String[] columnWriter =
                        writerTableList.stream().map(TableInfoKey::getColName).toArray(String[]::new);
                writerJson.put("column", columnWriter);
            }
        }
        log.info("修改后的json:{}", JSONObject.toJSONString(jsonobj));
        return JSONObject.toJSONString(jsonobj);
    }
}
