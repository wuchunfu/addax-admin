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
import com.wgzhao.addax.admin.mapper.TableInfoCustomMapper;
import com.wgzhao.addax.admin.pojo.TableInfo;
import com.wgzhao.addax.admin.server.DataChangeRecordService;
import com.wgzhao.addax.admin.server.TableService;
import com.wgzhao.addax.admin.vo.TableInfoVo;
import com.wgzhao.addax.admin.vo.TableMainVo;
import com.wgzhao.addax.admin.vo.TreeNodeVo;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liuting
 */
@Service
@Log4j2
public class TableServiceImpl
        implements TableService
{
    @Resource
    private TableInfoCustomMapper tableInfoCustomMapper;

    @Resource
    private DataChangeRecordService dataChangeRecordService;

    @Override
    public List<TableInfo> getAllTableInfoList(String sourceConfigId, String sourceTableSchema,
            String sourceTableName)
    {
        return tableInfoCustomMapper.getAllTableInfoList(sourceConfigId, sourceTableSchema, sourceTableName);
    }

    @Override
    public ServerResponse<List<TreeNodeVo>> queryTreeNodes()
    {
        //获取根节点
        List<TreeNodeVo> rootMenu = tableInfoCustomMapper.queryAllSourceConfigs();
        //最后的结果
        List<TreeNodeVo> resultList = new ArrayList<>();
        // 先找到所有的一级菜单
        for (int i = 0; i < rootMenu.size(); i++) {
            // 一级菜单没有parentCode
            if (StringUtils.isBlank(rootMenu.get(i).getParentCode())) {
                resultList.add(rootMenu.get(i));
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (TreeNodeVo vo : resultList) {
            vo.setChildMenus(getChild(vo.getNodeCode(), rootMenu, 0));
        }
        return ServerResponse.createBySuccess("成功", resultList);
    }

    @Override
    public ServerResponse<TableMainVo> getTableInfo(String nodeCode)
    {
        //获取表最近变更时间
        Date updateTime = dataChangeRecordService.getUpdateTimeByNodeCode(nodeCode);
        TableMainVo tableMainVo = new TableMainVo();
        tableMainVo.setUpdateTime(updateTime);
        //获取所有表字段
        List<TableInfoVo> tableInfoVoList = tableInfoCustomMapper.getTableInfoByNodeCode(nodeCode);
        tableMainVo.setTableInfoList(tableInfoVoList);
        return ServerResponse.createBySuccess("成功", tableMainVo);
    }

    @Override
    public List<TableInfo> getTableInfoBySubTaskId(String subTaskId, String sourceId, String db,
            String tbl)
    {
        return tableInfoCustomMapper.getTableInfoBySubTaskId(subTaskId, sourceId, db, tbl);
    }

    /**
     * 递归查找子节点
     *
     * @param nodeCode 当前节点code
     * @param rootMenu 要查找的列表
     * @return List<TreeNodeVo>
     */
    private List<TreeNodeVo> getChild(String nodeCode, List<TreeNodeVo> rootMenu, Integer isTableLevelFlag)
    {
        // 定义子节点
        List<TreeNodeVo> childList = new ArrayList<>();
        for (TreeNodeVo menu : rootMenu) {
            // 遍历所有节点，将父节点code与传过来的code比较
            if (StringUtils.isNotBlank(menu.getParentCode())) {
                if (menu.getParentCode().equals(nodeCode)) {
                    childList.add(menu);
                    menu.setIsTableLevelFlag(isTableLevelFlag);
                }
            }
        }
        // 把子节点的子节点再循环一遍
        for (TreeNodeVo menu : childList) {
            // 递归
            menu.setChildMenus(getChild(menu.getNodeCode(), rootMenu, 1));
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
