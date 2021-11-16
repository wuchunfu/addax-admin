package com.wgzhao.addax.admin.enums;

/**
 * @author liuting
 */
public enum JsonCollectionTypeEnum
{
    COLLECTION(1, "采集"),

    DATA_SERVICE(2, "数据服务");

    private final Integer code;

    private final String msg;

    JsonCollectionTypeEnum(Integer code, String msg)
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
