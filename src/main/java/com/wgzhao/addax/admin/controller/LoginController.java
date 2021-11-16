/*
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
