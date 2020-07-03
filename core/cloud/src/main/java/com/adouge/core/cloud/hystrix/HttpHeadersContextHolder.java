package com.adouge.core.cloud.hystrix;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.adouge.core.cloud.props.HystrixHeadersProperties;
import com.adouge.core.tool.utils.WebUtil;
import com.adouge.secure.AdougeUser;
import com.adouge.secure.utils.SecureUtil;
import com.google.common.base.Charsets;
import org.springframework.core.NamedThreadLocal;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/17 4:50 下午
 */
public class HttpHeadersContextHolder {
    private static final ThreadLocal<HttpHeaders> HTTP_HEADERS_HOLDER = new NamedThreadLocal<>("hystrix HttpHeaders");

    /**
     * 请求和转发的ip
     */
    private static final String[] ALLOW_HEADS = new String[]{
            "X-Real-IP", "x-forwarded-for", "authorization", "adouge-auth", "Authorization", "Adouge-Auth"
    };

    static void set(HttpHeaders httpHeaders) {
        HTTP_HEADERS_HOLDER.set(httpHeaders);
    }

    @Nullable
    public static HttpHeaders get() {
        return HTTP_HEADERS_HOLDER.get();
    }

    static void remove() {
        HTTP_HEADERS_HOLDER.remove();
    }

    @Nullable
    public static HttpHeaders toHeaders(HystrixHeadersProperties properties) {
        HttpServletRequest request = WebUtil.getRequest();
        if (request == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        String accountHeaderName = properties.getAccount();
        AdougeUser account = SecureUtil.getUser();
        assert account != null;
        String xAccount = String.format("%s[%s]", account.getUserId(), account.getUserName());

        String xAccountHeader = URLUtil.encode(xAccount, Charsets.UTF_8);
        if (StrUtil.isNotBlank(xAccountHeader)) {
            headers.add(accountHeaderName, xAccountHeader);
        }
        List<String> allowHeadsList = new ArrayList<>(Arrays.asList(ALLOW_HEADS));
        // 如果有传递 account header 继续往下层传递
        allowHeadsList.add(accountHeaderName);
        // 传递请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            List<String> allowed = properties.getAllowed();
            String pattern = properties.getPattern();
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                // 只支持配置的 header
                if (allowHeadsList.contains(key) || allowed.contains(key) || PatternMatchUtils.simpleMatch(pattern, key)) {
                    String values = request.getHeader(key);
                    // header value 不为空的 传递
                    if (StrUtil.isNotBlank(values)) {
                        headers.add(key, values);
                    }
                }

            }
        }
        return headers.isEmpty() ? null : headers;
    }
}
