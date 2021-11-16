package com.wgzhao.addax.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author liuting
 */
@ApiModel
public class RandomStrDto
{
    @ApiModelProperty(value = "生成json接口返回的随机字符串")
    @NotBlank(message = "randomStr不能为空")
    private String randomStr;

    public String getRandomStr()
    {
        return randomStr;
    }

    public void setRandomStr(String randomStr)
    {
        this.randomStr = randomStr;
    }
}
