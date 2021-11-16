package com.wgzhao.addax.admin.common;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author liuting
 */
@RestControllerAdvice
public class ExceptionControllerAdvice
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ServerResponse<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e)
    {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return ServerResponse.createByErrorMessage(objectError.getDefaultMessage());
    }
}
