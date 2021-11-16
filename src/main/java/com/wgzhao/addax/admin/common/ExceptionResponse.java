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
