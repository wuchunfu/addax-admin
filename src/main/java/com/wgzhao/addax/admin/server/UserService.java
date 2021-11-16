package com.wgzhao.addax.admin.server;

import com.wgzhao.addax.admin.common.ServerResponse;
import com.wgzhao.addax.admin.dto.QueryUserDto;
import com.wgzhao.addax.admin.dto.UserDto;
import com.wgzhao.addax.admin.vo.QueryUserVo;
import com.wgzhao.addax.admin.vo.UserVo;
import com.github.pagehelper.PageInfo;

/**
 * @author liuting
 */
public interface UserService
{
    /**
     * 添加用户
     * @param userDto 用户信息
     * @return ServerResponse<String>
     */
    ServerResponse<String> addUser(UserDto userDto);

    /**
     * 登录
     * @param userName 用户名
     * @param password 密码
     * @return ServerResponse<UserVo>
     */
    ServerResponse<UserVo> login(String userName, String password);

    /**
     * 用户列表
     * @param queryUserDto 查询参数
     * @return ServerResponse<PageInfo<QueryUserVo>>
     */
    ServerResponse<PageInfo<QueryUserVo>> queryUserListWithPage(QueryUserDto queryUserDto);
}
