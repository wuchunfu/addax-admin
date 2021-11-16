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

package com.wgzhao.addax.admin.support;

import com.wgzhao.addax.admin.config.ConfigConstants;
import com.wgzhao.addax.admin.enums.ResponseEnum;
import com.wgzhao.addax.admin.exception.UnifiedException;
import com.wgzhao.addax.admin.utils.RedisUtil;
import com.wgzhao.addax.admin.vo.UserVo;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Objects;

/**
 * The class Base controller.
 *
 * @author liuting
 */
public class BaseController
{
//    private final HSCache hsCache = HSCacheUtils.get("default").getCache();
    private final RedisUtil redisUtil = new RedisUtil();

    @Resource
    protected HttpServletRequest request;

    /**
     * Gets login auth info.
     *
     * @return UserVo
     */
    protected UserVo getLoginAuthUser()
    {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new UnifiedException(ResponseEnum.TOKEN_INVALID.getReturnCode(), ResponseEnum.TOKEN_INVALID.getReturnMsg());
        }
        UserVo loginUser = redisUtil.get("ZEUS:" + token, UserVo.class);
        if (Objects.isNull(loginUser)) {
            throw new UnifiedException(ResponseEnum.TOKEN_INVALID.getReturnCode(), ResponseEnum.TOKEN_INVALID.getReturnMsg());
        }
        //重新设置token两小时失效
        redisUtil.set("ZEUS:" + token, loginUser, ConfigConstants.tokenExpirationTime);
        return loginUser;
    }
}
  