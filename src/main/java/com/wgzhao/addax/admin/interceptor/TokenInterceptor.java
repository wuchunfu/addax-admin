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

package com.wgzhao.addax.admin.interceptor;

import com.wgzhao.addax.admin.server.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class Token interceptor.
 *
 * @author liuting
 */
@Slf4j
@Component
//@DependsOn("hsCacheUtils")
public class TokenInterceptor
        implements HandlerInterceptor
{
   @Autowired RedisHelper redisHelper;

    private static final String AUTH_PATH1 = "json";
    private static final String AUTH_PATH2 = "sourceConfig";
    private static final String AUTH_PATH3 = "user";
    private static final String AUTH_PATH4 = "metadata";
    private static final String AUTH_PATH5 = "systemConfig";

    /**
     * After completion.
     *
     * @param request the request
     * @param response the response
     * @param arg2 the arg 2
     * @param ex the ex
     * @throws Exception the exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object arg2, Exception ex)
            throws Exception
    {
    }

    /**
     * Post handle.
     *
     * @param request the request
     * @param response the response
     * @param arg2 the arg 2
     * @param mv the mv
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2,
            ModelAndView mv)
            throws Exception
    {
    }

    /**
     * Pre handle boolean.
     *
     * @param request the request
     * @param response the response
     * @param handler the handler
     * @return the boolean
     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//    {
//        String uri = request.getRequestURI();
//        log.info("<== preHandle - 权限拦截器.  url={}", uri);
//        if (!uri.contains(AUTH_PATH1) && !uri.contains(AUTH_PATH2) && !uri.contains(AUTH_PATH3) && !uri.contains(AUTH_PATH4)
//                && !uri.contains(AUTH_PATH5)) {
//            log.info("<== preHandle - 配置URL不走认证.  url={}", uri);
//            return true;
//        }
//        UserVo loginUser = null;
//        String token = request.getHeader("token");
//        if (StringUtils.isBlank(token)) {
//            throw new UnifiedException(ResponseEnum.ARGUMENTS_IS_NULL.getReturnCode(),
//                    ResponseEnum.ARGUMENTS_IS_NULL.getReturnMsg());
//        }
//        log.info("<== preHandle - 权限拦截器.  token={}", token);
//        loginUser = redisUtil.get("ZEUS:" + token, UserVo.class);
//        log.info("<== preHandle - 权限拦截器.  loginUser={}", loginUser);
//        if (Objects.isNull(loginUser)) {
//            log.error("<== preHandle - 获取用户信息失败, 不允许操作，url=", uri);
//            throw new UnifiedException(ResponseEnum.TOKEN_INVALID.getReturnCode(),
//                    ResponseEnum.TOKEN_INVALID.getReturnMsg());
//        }
//        //重新设置token两小时失效
//        redisUtil.set("ZEUS:" + token, loginUser, ConfigConstants.tokenExpirationTime);
//        return true;
//    }
}