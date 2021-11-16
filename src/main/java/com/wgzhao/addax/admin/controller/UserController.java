package com.wgzhao.addax.admin.controller;

import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.dto.QueryUserDto;
import com.wgzhao.addax.admin.server.UserService;
import com.wgzhao.addax.admin.vo.QueryUserVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuting
 */
@RestController
@RequestMapping("/user")
public class UserController
{
    @Resource
    private UserService userService;

    @ApiOperation("用户列表-分页")
    @PostMapping(value = "queryUserListWithPage")
    public ServerResponse<PageInfo<QueryUserVo>> queryUserListWithPage(@RequestBody QueryUserDto queryUserDto)
    {
        return userService.queryUserListWithPage(queryUserDto);
    }
}
