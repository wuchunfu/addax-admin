package com.wgzhao.addax.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author liuting
 */
@ApiModel
public class QueryJsonVo
{
    @ApiModelProperty(value = "json-ID")
    private String jsonId;

    @ApiModelProperty(value = "json文件名")
    private String jsonName;

    @ApiModelProperty(value = "创建人")
    private String userName;

    @ApiModelProperty(value = "json文件描述")
    private String jsonDesc;

    @ApiModelProperty(value = "json生成时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "json内容")
    private String json;

    public String getJsonId()
    {
        return jsonId;
    }

    public void setJsonId(String jsonId)
    {
        this.jsonId = jsonId;
    }

    public String getJson()
    {
        return json;
    }

    public void setJson(String json)
    {
        this.json = json;
    }

    public String getJsonName()
    {
        return jsonName;
    }

    public void setJsonName(String jsonName)
    {
        this.jsonName = jsonName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getJsonDesc()
    {
        return jsonDesc;
    }

    public void setJsonDesc(String jsonDesc)
    {
        this.jsonDesc = jsonDesc;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
}
