package com.wgzhao.addax.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author liuting
 */
@ApiModel
public class HdfsSourceConfigVo
{
    @ApiModelProperty(value = "reader数据源信息")
    private SourceConfigVo readerSourceConfigVo;

    @ApiModelProperty(value = "writer数据源信息")
    private SourceConfigVo writerSourceConfigVo;

    public SourceConfigVo getReaderSourceConfigVo()
    {
        return readerSourceConfigVo;
    }

    public void setReaderSourceConfigVo(SourceConfigVo readerSourceConfigVo)
    {
        this.readerSourceConfigVo = readerSourceConfigVo;
    }

    public SourceConfigVo getWriterSourceConfigVo()
    {
        return writerSourceConfigVo;
    }

    public void setWriterSourceConfigVo(SourceConfigVo writerSourceConfigVo)
    {
        this.writerSourceConfigVo = writerSourceConfigVo;
    }
}
