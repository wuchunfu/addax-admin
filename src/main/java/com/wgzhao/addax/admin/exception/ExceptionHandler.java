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

package com.wgzhao.addax.admin.exception;

import com.wgzhao.addax.admin.common.ExceptionResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangkai
 */
@Log4j2
@ControllerAdvice
public class ExceptionHandler
{

    /**
     * 自定义异常
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(UnifiedException.class)
    @ResponseBody
    public ExceptionResponse<String> handleStudentException(HttpServletRequest request, UnifiedException ex)
    {
        return ExceptionResponse.createByErrorCodeMessage(ex.getReturnCode(), ex.getReturnMsg());
    }

//    /**
//     * 程序异常
//     */
//    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
//    @ResponseBody
//    public ExceptionResponse<String> handleException(HttpServletRequest request, Exception ex) {
//        log.error(ex);
//        return ExceptionResponse.createByErrorCodeMessage(ResponseEnum.SYSTEM_ERROR.getReturnCode(), ResponseEnum.SYSTEM_ERROR.getReturnMsg());
//    }


//    @org.springframework.web.bind.annotation.ExceptionHandler(BaseCommonException.class)
    @ResponseBody
    public ExceptionResponse<String> baseCommonException(HttpServletRequest request, BaseCommonException ex)
    {
        return ExceptionResponse.createByErrorCodeMessage(ex.getErrorCode(), ex.getErrorMessage());
    }
}
