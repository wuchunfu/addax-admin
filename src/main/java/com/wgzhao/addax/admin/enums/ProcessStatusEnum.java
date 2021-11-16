package com.wgzhao.addax.admin.enums;

/**
 * @author liuting
 */
public enum ProcessStatusEnum
{
    NOT_FINISHED(0, "未完成"),

    FINISHED(1, "已完成"),
    ;

    private final Integer code;

    private final String msg;

    ProcessStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
