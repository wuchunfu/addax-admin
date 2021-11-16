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
