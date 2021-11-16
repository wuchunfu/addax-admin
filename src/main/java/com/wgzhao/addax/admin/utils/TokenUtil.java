package com.wgzhao.addax.admin.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangkai
 */
public class TokenUtil
{

    private TokenUtil()
    {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 设置过期时间(毫秒)
     */
    private static final long EXPIRE_DATE = 10 * 60 * 1000L;
    /**
     * token秘钥
     */
    private static final String TOKEN_SECRET = "QmWnEbRvTcYxUz!@#$%)(*&^=-+_";

    public static String token(String username, String password)
    {
        String token;
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带username，password信息，生成签名
            token = JWT.create().withHeader(header).withClaim("username", username).withClaim("password", password).withExpiresAt(date).sign(algorithm);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }
}
