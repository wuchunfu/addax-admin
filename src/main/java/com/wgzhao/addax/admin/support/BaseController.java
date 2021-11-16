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
  