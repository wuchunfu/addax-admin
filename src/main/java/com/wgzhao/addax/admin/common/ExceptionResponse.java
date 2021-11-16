package com.wgzhao.addax.admin.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yangkai
 */
@ApiModel
public class ExceptionResponse<T> implements Serializable
{

    @ApiModelProperty(value = "返回code")
    private String returnCode;

    @ApiModelProperty(value = "返回信息")
    private String returnMsg;

    public String getReturnCode()
    {
        return returnCode;
    }

    public void setReturnCode(String returnCode)
    {
        this.returnCode = returnCode;
    }

    public String getReturnMsg()
    {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg)
    {
        this.returnMsg = returnMsg;
    }

    public ExceptionResponse(String returnCode, String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    public static <T>ExceptionResponse<T> createByErrorCodeMessage(String errorCode, String errorMessage) {
        return new ExceptionResponse<>(errorCode, errorMessage);
    }
}
