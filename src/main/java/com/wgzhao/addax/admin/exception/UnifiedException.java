package com.wgzhao.addax.admin.exception;

/**
 * @author yangkai
 */
public class UnifiedException extends RuntimeException
{

    private final String returnCode;

    private final String returnMsg;

    public UnifiedException(String returnCode, String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }
}
