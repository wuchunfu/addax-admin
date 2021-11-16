package com.wgzhao.addax.admin.enums;

/**
 * @author liuting
 */
public enum SubTaskStatusEnum
{
    INITIAL(0, "初始值"),

    SUCCEED(1, "成功"),

    FAILED(2, "失败"),
    ;

    private final Integer code;

    private final String msg;

    SubTaskStatusEnum(Integer code, String msg) {
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
