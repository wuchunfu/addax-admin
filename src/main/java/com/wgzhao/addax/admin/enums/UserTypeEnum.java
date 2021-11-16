package com.wgzhao.addax.admin.enums;

/**
 * @author liuting
 */
public enum UserTypeEnum
{
    /**
     * 管理员
     */
    ADMIN(1, "管理员"),
    /**
     * 普通用户
     */
    USER(2, "普通用户"),
    ;

    private final Integer code;

    private final String msg;

    UserTypeEnum(Integer code, String msg) {
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
