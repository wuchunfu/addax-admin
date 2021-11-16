package com.wgzhao.addax.admin.controller;

import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.server.JsonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuting
 */
@RestController
@RequestMapping("/update")
public class JsonUpdateController
{
    @Resource
    private JsonService jsonService;

    @ApiOperation("元数据变更更新json")
    @GetMapping(value = "updateJsonInfo")
    public ServerResponse<String> updateJson()
    {
        return jsonService.updateJson();
    }
}
