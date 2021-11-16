package com.wgzhao.addax.admin.enums;

/**
 * @author liuting
 */
public enum ColChangeTypeEnum
{
    ADD(1, "新增"),

    DELETE(2, "删除"),

    UPDATE(3, "修改"),

    DELETE_ALL(4, "整表删除");

    private final Integer code;

    private final String msg;

    ColChangeTypeEnum(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getMsg()
    {
        return msg;
    }
}
