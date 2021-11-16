package com.wgzhao.addax.admin.enums;

/**
 * @author liuting
 */
public enum SourceConfigStatusEnum
{
    /**
     * 删除
     */
    DELETED_STATE(0, "删除"),
    /**
     * 启用
     */
    ENABLE_STATUS(1, "启用"),
    /**
     * 停用
     */
    INACTIVE_STATUS(1, "停用")
    ;

    private final Integer code;

    private final String msg;

    SourceConfigStatusEnum(Integer code, String msg) {
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
