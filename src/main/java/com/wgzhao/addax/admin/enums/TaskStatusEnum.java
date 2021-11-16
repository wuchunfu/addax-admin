package com.wgzhao.addax.admin.enums;

/**
 * @author liuting
 */
public enum TaskStatusEnum
{
    INITIAL(0, "初始值"),

    ADD_FIELD_FINISHED(1, "字段信息入库完成"),

    ALL_FINISHED(2, "任务全部完成"),
    ;

    private final Integer code;

    private final String msg;

    TaskStatusEnum(Integer code, String msg) {
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
