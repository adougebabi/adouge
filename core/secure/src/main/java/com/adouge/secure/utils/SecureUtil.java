package com.adouge.secure.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.extra.spring.SpringUtil;
import com.adouge.core.launch.constant.TokenConstant;
import com.adouge.core.tool.constant.GlobalConstant;
import com.adouge.core.tool.constant.StringConstant;
import com.adouge.core.tool.utils.WebUtil;
import com.adouge.secure.AdougeUser;
import com.adouge.secure.TokenInfo;
import com.adouge.secure.client.IClientDetails;
import com.adouge.secure.client.IClientDetailsService;
import com.adouge.secure.constant.SecureConstant;
import com.adouge.secure.exception.SecureException;
import com.google.common.base.Charsets;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * @author : Vinson
 * @date : 2020/5/31 2:08 下午
 */
public class SecureUtil {
    private static final String USER_REQUEST_ATTR = "USER_REQUEST_ATTR_";

    private final static String BASE64_SECURITY = Base64.encode(TokenConstant.SIGN_KEY.getBytes(StandardCharsets.UTF_8));
    private final static String AES_KEY = "adougebabiniubia";

    private static final IClientDetailsService clientDetailsService;

    static {
        clientDetailsService = SpringUtil.getBean(IClientDetailsService.class);
    }

    public static TokenInfo createJwtToken(Dict user, String audience, String issuer, String tokenType) {

        String[] tokens = extractAndDecodeHeader();
        assert tokens.length == 2;
        String clientId = tokens[0];
        String clientSecret = tokens[1];

        // 获取客户端信息
        IClientDetails clientDetails = clientDetails(clientId);

        // 校验客户端信息
        if (!validateClient(clientDetails, clientId, clientSecret)) {
            throw new SecureException("客户端认证失败!");
        }

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
        builder.claim(TokenConstant.CLIENT_ID, clientId);

        //添加Token过期时间
        long expireMillis;
        if (tokenType.equals(TokenConstant.ACCESS_TOKEN)) {
            expireMillis = clientDetails.getAccessTokenTime() * 1000;
//            expireMillis = 60 * 60 * 1000;
        } else if (tokenType.equals(TokenConstant.REFRESH_TOKEN)) {
            expireMillis = clientDetails.getRefreshTokenTime() * 1000;
//            expireMillis = 60 * 60 * 1000;
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

    /**
     * 获取用户
     *
     * @return user
     */
    public static AdougeUser getUser() {
        HttpServletRequest request = WebUtil.getRequest();
        if (request == null) {
            return null;
        }
        // 优先从 request 中获取
        Object user = request.getAttribute(USER_REQUEST_ATTR);
        if (user == null) {
            user = getUser(request);
            if (user != null) {
                // 设置到 request 中
                request.setAttribute(USER_REQUEST_ATTR, user);
            }
        }
        return (AdougeUser) user;
    }

    /**
     * 获取用户
     *
     * @param request request
     * @return user
     */
    public static AdougeUser getUser(HttpServletRequest request) {
        Claims claims = getClaims(request);
        if (claims == null) {
            return null;
        }
        String clientId = claims.get(TokenConstant.CLIENT_ID).toString();
        Long userId = Long.decode(claims.get(TokenConstant.USER_ID).toString());
        String tenantId = claims.get(TokenConstant.TENANT_ID).toString();
        String account = claims.get(TokenConstant.ACCOUNT).toString();
        String userName = claims.get(TokenConstant.USER_NAME).toString();

        List<Long> roleId = (List<Long>) claims.get(TokenConstant.ROLE_ID);
        List<String> roleName = (List<String>) claims.get(TokenConstant.ROLE_NAME);

        List<Long> deptId = (List<Long>) claims.get(TokenConstant.DEPT_ID);
        List<String> deptName = (List<String>) claims.get(TokenConstant.DEPT_NAME);

        AdougeUser user = new AdougeUser();
        user.setClientId(clientId);
        user.setUserId(userId);
        user.setTenantId(tenantId);
        user.setAccount(account);
        user.setUserName(userName);
        user.setRoleId(roleId);
        user.setRoleName(roleName);
        user.setDeptId(deptId);
        user.setDeptName(deptName);

        return user;
    }

    /**
     * 获取Claims
     *
     * @param request request
     * @return Claims
     */
    public static Claims getClaims(HttpServletRequest request) {
        String auth = request.getHeader(SecureConstant.BASIC_HEADER_KEY);
        if (StrUtil.isNotBlank(auth) && auth.length() > TokenConstant.AUTH_LENGTH) {
            String headStr = auth.substring(0, 6).toLowerCase();
            if (headStr.compareTo(TokenConstant.BEARER) == 0) {
                auth = auth.substring(7);
                return SecureUtil.parseJWT(auth);
            }
        } else {
            String parameter = request.getParameter(TokenConstant.HEADER);
            if (StrUtil.isNotBlank(parameter)) {
                return SecureUtil.parseJWT(parameter);
            }
        }
        return null;
    }

    /**
     * 解析token
     *
     * @param token token
     * @return Claims
     */
    public static Claims parseJWT(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(Base64.decode(BASE64_SECURITY))
                    .parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 客户端信息解码
     */
    @SneakyThrows
    public static String[] extractAndDecodeHeader() {
        // 获取请求头客户端信息
        String header = Objects.requireNonNull(WebUtil.getRequest()).getHeader(TokenConstant.HEADER);
        header = header.replace(SecureConstant.BASIC_HEADER_PREFIX_EXT, SecureConstant.BASIC_HEADER_PREFIX);
        if (!header.startsWith(SecureConstant.BASIC_HEADER_PREFIX)) {
            throw new SecureException("No client information in request header");
        }
        byte[] base64Token = header.substring(6).getBytes(Charsets.UTF_8);

        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e1) {
            throw new RuntimeException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, Charsets.UTF_8);
        int index = token.indexOf(StringConstant.COLON);
        if (index == -1) {
            throw new RuntimeException("Invalid basic authentication token");
        } else {
            return new String[]{token.substring(0, index), token.substring(index + 1)};
        }
    }

    /**
     * 获取请求头中的客户端id
     */
    public static String getClientIdFromHeader() {
        String[] tokens = extractAndDecodeHeader();
        assert tokens.length == 2;
        return tokens[0];
    }

    /**
     * 获取客户端信息
     *
     * @param clientId 客户端id
     * @return clientDetails
     */
    private static IClientDetails clientDetails(String clientId) {
        return clientDetailsService.loadClientByClientId(clientId);
    }

    /**
     * 校验Client
     *
     * @param clientId     客户端id
     * @param clientSecret 客户端密钥
     * @return boolean
     */
    private static boolean validateClient(IClientDetails clientDetails, String clientId, String clientSecret) {
        if (clientDetails != null) {
            return clientId.equalsIgnoreCase(clientDetails.getClientId()) && clientSecret.equalsIgnoreCase(clientDetails.getClientSecret());
        }
        return false;
    }
    /**
     * 获取用户角色
     *
     * @return 角色名称
     */
    public static List<String> getUserRole() {
        AdougeUser user = getUser();
        return (null == user) ? CollUtil.newArrayList() : user.getRoleName();
    }
    /**
     * 获取租户id
     * @return 租户id
     */
    public static String getTenantId() {
        AdougeUser user = getUser();
        return null == user ? "" : user.getTenantId();
    }
    /**
     * 获取租户id
     * @return 租户id
     */
    public static String getTenantId(HttpServletRequest request) {
        AdougeUser user = getUser(request);
        return null == user ? "" : user.getTenantId();
    }

    /**
     * 判断是否管理员
     */
    public static boolean isAdministrator() {
        return getUserRole().contains(GlobalConstant.ADMIN);
    }
}
