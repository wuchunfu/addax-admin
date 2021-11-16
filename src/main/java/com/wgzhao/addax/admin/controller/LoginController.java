package com.wgzhao.addax.admin.controller;

import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.dto.UserDto;
import com.wgzhao.addax.admin.server.UserService;
import com.wgzhao.addax.admin.vo.UserVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author liuting
 */
@RestController
@RequestMapping("/login")
public class LoginController
{
    @Resource
    private UserService userService;

    @ApiOperation("注册接口")
    @PostMapping(value = "register")
    public ServerResponse<String> register(@RequestBody @Valid UserDto userDto)
    {
        return userService.addUser(userDto);
    }

    @ApiOperation("用户登录接口")
    @PostMapping(value = "login")
    public ServerResponse<UserVo> login(@RequestBody UserDto userDto)
    {
        return userService.login(userDto.getUserName(), userDto.getPassword());
    }
}
