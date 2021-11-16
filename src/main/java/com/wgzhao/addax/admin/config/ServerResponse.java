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

package com.wgzhao.addax.admin.config;

import com.wgzhao.addax.admin.enums.ResponseEnum;

import java.io.Serializable;

/**
 * @author yangkai
 */

public class ServerResponse<T>
        implements Serializable
{

    private final String returnCode;

    private String returnMsg;

    private T data;

    private ServerResponse(String returnCode)
    {
        this.returnCode = returnCode;
    }

    private ServerResponse(String returnCode, T data)
    {
        this.returnCode = returnCode;
        this.data = data;
    }

    private ServerResponse(String returnCode, String returnMsg, T data)
    {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.data = data;
    }

    private ServerResponse(String returnCode, String returnMsg)
    {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    public String getReturnCode()
    {
        return returnCode;
    }

    public String getReturnMsg()
    {
        return returnMsg;
    }

    public T getData()
    {
        return data;
    }

    public static <T> ServerResponse<T> createBySuccess()
    {
        return new ServerResponse<>(ResponseEnum.SUCCESS.getReturnCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String returnMsg)
    {
        return new ServerResponse<>(ResponseEnum.SUCCESS.getReturnCode(), returnMsg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data)
    {
        return new ServerResponse<>(ResponseEnum.SUCCESS.getReturnCode(), ResponseEnum.SUCCESS.getReturnMsg(),
                data);
    }

    public static <T> ServerResponse<T> createBySuccess(String returnMsg, T data)
    {
        return new ServerResponse<>(ResponseEnum.SUCCESS.getReturnCode(), returnMsg, data);
    }

    public static <T> ServerResponse<T> createByError()
    {
        return new ServerResponse<>(ResponseEnum.ERROR.getReturnCode(), ResponseEnum.ERROR.getReturnCode());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage)
    {
        return new ServerResponse<>(ResponseEnum.ERROR.getReturnCode(), errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(String errorCode, String errorMessage)
    {
        return new ServerResponse<>(errorCode, errorMessage);
    }
}
