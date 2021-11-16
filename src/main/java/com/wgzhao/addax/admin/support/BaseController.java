package com.wgzhao.addax.admin.support;

import com.wgzhao.addax.admin.config.ConfigConstants;
import com.wgzhao.addax.admin.enums.ResponseEnum;
import com.wgzhao.addax.admin.exception.UnifiedException;
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
    private final HSCache hsCache = HSCacheUtils.get("default").getCache();

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
        UserVo loginUser = hsCache.get("ZEUS:" + token, UserVo.class);
        if (Objects.isNull(loginUser)) {
            throw new UnifiedException(ResponseEnum.TOKEN_INVALID.getReturnCode(), ResponseEnum.TOKEN_INVALID.getReturnMsg());
        }
        //重新设置token两小时失效
        hsCache.put("ZEUS:" + token, loginUser, ConfigConstants.tokenExpirationTime);
        return loginUser;
    }
}
  