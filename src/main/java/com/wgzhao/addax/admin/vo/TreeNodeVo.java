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

package com.wgzhao.addax.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author liuting
 */
@ApiModel
public class TreeNodeVo
{
    @ApiModelProperty(value = "节点编码")
    private String nodeCode;

    @ApiModelProperty(value = "节点名称")
    private String nodeName;

    @ApiModelProperty(value = "父菜单编码")
    private String parentCode;

    @ApiModelProperty(value = "子节点")
    private List<TreeNodeVo> childMenus;

    @ApiModelProperty(value = "是否是表层级0:否；1:是")
    private Integer isTableLevelFlag = 0;

    public Integer getIsTableLevelFlag()
    {
        return isTableLevelFlag;
    }

    public void setIsTableLevelFlag(Integer isTableLevelFlag)
    {
        this.isTableLevelFlag = isTableLevelFlag;
    }

    public String getNodeCode()
    {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode)
    {
        this.nodeCode = nodeCode;
    }

    public String getNodeName()
    {
        return nodeName;
    }

    public void setNodeName(String nodeName)
    {
        this.nodeName = nodeName;
    }

    public String getParentCode()
    {
        return parentCode;
    }

    public void setParentCode(String parentCode)
    {
        this.parentCode = parentCode;
    }

    public List<TreeNodeVo> getChildMenus()
    {
        return childMenus;
    }

    public void setChildMenus(List<TreeNodeVo> childMenus)
    {
        this.childMenus = childMenus;
    }
}
