package com.adouge.core.cloud.feign;

import com.adouge.core.tool.utils.WebUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author : Vinson
 * @date : 2020/6/17 4:18 下午
 */
public class FeignRequestHeaderInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = WebUtil.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                requestTemplate.header(name, values);
            }
        }
//        HttpHeaders headers = HttpHeadersContextHolder.get();
//        if (headers != null && !headers.isEmpty()) {
//            headers.forEach((key, values) -> values.forEach(value -> requestTemplate.header(key, value)));
//        }
    }
}
