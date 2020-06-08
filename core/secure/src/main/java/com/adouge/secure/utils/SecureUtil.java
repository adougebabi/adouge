package com.adouge.secure.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Dict;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.adouge.secure.TokenInfo;
import com.adouge.core.launch.constant.TokenConstant;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;


import java.util.Calendar;
import java.util.Date;


/**
 * @author : Vinson
 * @date : 2020/5/31 2:08 下午
 */
public class SecureUtil {
    private final static String BASE64_SECURITY = Base64.encode(TokenConstant.SIGN_KEY.getBytes(StandardCharsets.UTF_8));
    private final static String AES_KEY = "adougebabiniubia";

    public static TokenInfo createJwtToken(Dict user, String audience, String issuer, String tokenType) {

        //TODO 校验客户端信息
        /*String[] tokens = extractAndDecodeHeader();
        assert tokens.length == 2;
        String clientId = tokens[0];
        String clientSecret = tokens[1];

        // 获取客户端信息
        IClientDetails clientDetails = clientDetails(clientId);

        // 校验客户端信息
        if (!validateClient(clientDetails, clientId, clientSecret)) {
            throw new SecureException("客户端认证失败!");
        }*/

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = java.util.Base64.getDecoder().decode(BASE64_SECURITY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的类
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JsonWebToken")
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);

        //设置JWT参数
        user.forEach(builder::claim);

        //设置应用id
//        builder.claim(CLIENT_ID, clientId);

        //添加Token过期时间
        long expireMillis;
        if (tokenType.equals(TokenConstant.ACCESS_TOKEN)) {
//            expireMillis = clientDetails.getAccessTokenValidity() * 1000;
            expireMillis = 60 * 60 * 1000;
        } else if (tokenType.equals(TokenConstant.REFRESH_TOKEN)) {
//            expireMillis = clientDetails.getRefreshTokenValidity() * 1000;
            expireMillis = 60 * 60 * 1000;
        } else {
            expireMillis = getExpire();
        }
        long expMillis = nowMillis + expireMillis;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp).setNotBefore(now);

        // 组装Token信息
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setToken(builder.compact());
        tokenInfo.setExpire((int) expireMillis / 1000);

        return tokenInfo;
    }

    /**
     * 获取过期时间
     *
     * @return 时间
     */
    public static long getExpire() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 4);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() - System.currentTimeMillis();
    }


    public static String decodeAes(String str) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, AES_KEY.getBytes(StandardCharsets.UTF_8), AES_KEY.getBytes(StandardCharsets.UTF_8));
        return aes.decryptStr(str);
    }
}
