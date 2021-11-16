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
