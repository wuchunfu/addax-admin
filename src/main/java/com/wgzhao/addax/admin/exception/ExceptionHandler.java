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
