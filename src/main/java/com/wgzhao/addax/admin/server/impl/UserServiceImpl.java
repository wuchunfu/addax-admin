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

package com.wgzhao.addax.admin.server.impl;

import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.config.ConfigConstants;
import com.wgzhao.addax.admin.dto.QueryUserDto;
import com.wgzhao.addax.admin.dto.UserDto;
import com.wgzhao.addax.admin.enums.UserTypeEnum;
import com.wgzhao.addax.admin.mapper.UserCustomMapper;
import com.wgzhao.addax.admin.mapper.UserMapper;
import com.wgzhao.addax.admin.pojo.User;
import com.wgzhao.addax.admin.server.UserService;
import com.wgzhao.addax.admin.utils.TokenUtil;
import com.wgzhao.addax.admin.utils.UUIDUtil;
import com.wgzhao.addax.admin.vo.QueryUserVo;
import com.wgzhao.addax.admin.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author liuting
 */
@Service
//@DependsOn("hsCacheUtils")
public class UserServiceImpl
        implements UserService
{
    @Autowired
    private RedisHelperImpl redisHelper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserCustomMapper userCustomMapper;

    @Override
    public ServerResponse<String> addUser(UserDto userDto)
    {
        //校验用户名
        Boolean flag = this.checkUserName(userDto.getUserName());
        if (!flag) {
            return ServerResponse.createByErrorMessage("用户名已存在，请重新输入");
        }
        User user = new User();
        user.setId(UUIDUtil.generate());
        user.setUsername(userDto.getUserName());
        user.setUtype(UserTypeEnum.ADMIN.getCode());
        //MD5加密
        user.setPass(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        user.setCtime(new Date());
        user.setMtime(new Date());
        int resultCount = userMapper.insertSelective(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户注册失败");
        }
        return ServerResponse.createBySuccessMessage("用户注册成功");
    }

    @Override
    public ServerResponse<UserVo> login(String userName, String password)
    {
        Boolean flag = this.checkUserName(userName);
        if (flag) {
            return ServerResponse.createByErrorMessage("用户名不存在，请重新输入");
        }
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userCustomMapper.login(userName, md5Password);
        if (Objects.isNull(user)) {
            return ServerResponse.createByErrorMessage("密码错误，请重新输入");
        }
        //生成唯一token，缓存到redis
        String token = TokenUtil.token(userName, password);
        if (StringUtils.isBlank(token)) {
            return ServerResponse.createByErrorMessage("登录失败");
        }
        UserVo userVo = new UserVo();
        userVo.setUserId(user.getId());
        userVo.setUserName(user.getUsername());
        userVo.setUserType(user.getUtype());
        userVo.setToken(token);
        redisHelper.valuePut("ZEUS:" + token, userVo, ConfigConstants.tokenExpirationTime);
        return ServerResponse.createBySuccess("登录成功", userVo);
    }

    @Override
    public ServerResponse<PageInfo<QueryUserVo>> queryUserListWithPage(QueryUserDto queryUserDto)
    {
        Integer pageNum = queryUserDto.getPageNum();
        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }
        Integer pageSize = queryUserDto.getPageSize();
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<QueryUserVo> userList = userCustomMapper.queryUserListWithPage(queryUserDto);
        PageInfo<QueryUserVo> pageResult = new PageInfo<>(userList);
        pageResult.setList(userList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private Boolean checkUserName(String userName)
    {
        int resultCount = userCustomMapper.checkUserName(userName);
        if (resultCount > 0) {
            return false;
        }
        return true;
    }
}
